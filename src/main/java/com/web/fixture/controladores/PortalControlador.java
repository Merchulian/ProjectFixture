
package com.web.fixture.controladores;

import com.web.fixture.entidades.Fixture;
import com.web.fixture.entidades.PartidoEliminatorio;
import com.web.fixture.entidades.PartidoGrupo;
import com.web.fixture.entidades.Usuario;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.PartidoEliminatorioRepositorio;
import com.web.fixture.repositorios.PartidoGrupoRepositorio;
import com.web.fixture.servicios.FixtureServicio;
import com.web.fixture.servicios.PartidoEliminatorioServicio;
import com.web.fixture.servicios.PartidoGrupoServicio;
import com.web.fixture.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @Autowired
    private UsuarioServicio UsuarioServicio;
    
    @Autowired
    private FixtureServicio fixtureServicio;
    
    @Autowired
    private PartidoEliminatorioServicio partidoEliminatorioServicio;
    
    @Autowired
    private PartidoGrupoServicio partidoGrupoServicio;
    
    @Autowired
    private PartidoGrupoRepositorio partidoGrupoRepo;
    
    @Autowired
    private PartidoEliminatorioRepositorio partidoEliminatorioRepo;
    
    @GetMapping("/")
    public String index() {
		
        return "index.html";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")
    @GetMapping("/inicio")
    public String inicio() {
		
        return "inicio.html";
    }
    
    @GetMapping("/noticias")
    public String verNoticias() {
		
        return "Noticias.html";
    }
    
    @GetMapping("/login")
    public String login(ModelMap modelo, @RequestParam(required = false) String error, @RequestParam(required = false) String logout) {
        if (error != null) {
            modelo.put("error", "Usuario o Clave incorrectos");
        }
        //esto es para que tire un mensaje una vez salido de la plataforma, falta agregar en el index o modifcar las opciones dependiendo si el usuario esta logueado poner logout o si no se logueo poner login
        if (logout != null) {
            modelo.put("logout","Ha salido correctamente");
        }
        return "login.html";
    }
    
        
    @GetMapping("/registro")
    public String registro(ModelMap modelo) {

        return "registro.html";
    }

    @PostMapping("/registrar")
    public String registrar(ModelMap modelo,@RequestParam(required = false) MultipartFile archivo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {

        try {
            UsuarioServicio.registrar(archivo, nombre, apellido, mail, clave1, clave2);
  //el titulo y la descripcion hay que ponerlo en el index con timeleft
            modelo.put("titulo", "Bienvenido, completa tu FIXTURE QATAR 2022");
            modelo.put("descripcion","El usuario fue registrado con exito");
            return "/index";
        } catch (ErrorServicio ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("mail", mail);
            modelo.put("clave1", clave1);
            modelo.put("clave2", clave2);

            return "registro.html";
        }
        
    }
//                      ====    vISTA fIXTURE    ====    
//EL FIXTURE SOLO PODRA INGRESAR UN USUARIO LOGUEADO.

    @PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")
    @GetMapping("/fixture")
   public String fixture(ModelMap model , HttpSession session,@RequestParam(required = false) String msg) throws ErrorServicio {
	Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        
        
        if(usuario != null){
            Fixture fixture = usuario.getFixture();
            String idFixture = fixture.getId();
            
            String goles1="";
            String goles2="";
            for (Integer i = 1; i <= 24; i++) {
                PartidoGrupo partido = partidoGrupoServicio.traerPartido(fixture.getId() ,i.toString());
                goles1 ="golesEquipo1_" + i;
                goles2 ="golesEquipo2_" + i;
                if(partido.getGolesEquipo1()!= null && partido.getGolesEquipo2() !=null){
                    model.put(goles1 ,partido.getGolesEquipo1());
                    model.put(goles2 , partido.getGolesEquipo2());
                }
            }
            
            if(partidoGrupoServicio.faseGruposCompletada(idFixture)){
            partidoGrupoServicio.guardarEstadisticas(idFixture);
            
            partidoGrupoServicio.definirCuartos(idFixture);
            // me traigo la lista con los cuartos de final y los muestro en la pantalla:
            for (Integer i = 1; i <=4; i++) {
                PartidoEliminatorio pe = partidoEliminatorioServicio.traerPartido(idFixture, i.toString());
                model.put("pe"+i.toString()+"Eq1", pe.getEquipo1().getPais()  );
                model.put("golesPe"+i.toString()+"Eq1", pe.getGolesEquipo1());
                model.put("pe"+i.toString()+"Eq2" , pe.getEquipo2().getPais()  );
                model.put("golesPe"+i.toString()+"Eq2", pe.getGolesEquipo2());

                }
            if(msg != ""){
                model.put("msg", msg);
            }
                if(partidoEliminatorioServicio.cuartosCompletada(idFixture)){
                    partidoEliminatorioServicio.definirCuartos(idFixture);
                    PartidoEliminatorio pe5= partidoEliminatorioServicio.traerPartido(idFixture, "5");
                    PartidoEliminatorio pe6 =partidoEliminatorioServicio.traerPartido(idFixture, "6");
                    
                    
                    model.put("semi1Eq1", pe5.getEquipo1().getPais());
                    model.put("semi1Eq2", pe5.getEquipo2().getPais());
                    model.put("semi2Eq1", pe6.getEquipo1().getPais());
                    model.put("semi2Eq2", pe6.getEquipo2().getPais());
                    
                    if(pe5.getGolesEquipo1() != null && pe5.getGolesEquipo2() != null){
                        model.put("golesSemi1Eq1" , pe5.getGolesEquipo1());
                        model.put("golesSemi1Eq2" , pe5.getGolesEquipo2());
                    }
                    if(pe6.getGolesEquipo1() != null && pe6.getGolesEquipo2() != null){ 
                        model.put("golesSemi2Eq1" , pe6.getGolesEquipo1());
                        model.put("golesSemi2Eq2" , pe6.getGolesEquipo2());
                    }
                    if(partidoEliminatorioServicio.semisCompletada(idFixture)){
                        
                           
                    model.put("finalEq1", partidoEliminatorioServicio.definirGanador(pe5).getPais());
                    model.put("finalEq2", partidoEliminatorioServicio.definirGanador(pe6).getPais());
                        
                    }
                }

            //aca van unos model.put para mostrar la info de cuartos
            //if(partidoEliminatorio.cuartosCompleatdos()){ definirSemis() y model.put}
            //if(semisCompletadas){partidoEliminatorio.definirFinal() y model.put()}

            }else{System.out.println("todavia falta rellenar partidos de fase de grupos");}

            return "fixture.html";
        }else{
            return "redirect:/";}
    }
   
   
   
 //  ===========================================================================   
//     A ESTADISTICAS SOLO PODRA INGRESAR UN USUARIO LOGUEADO, DEBIDO QUE LAS MISMAS SE CREAN EN BASE AL FIXTURE COMPLETADO
//    @PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")
//    @GetMapping("/estadisticas")
//    public String estadisticas() {
//		
//        return "estadisticas.html";
//    }
    
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")
    @GetMapping("/noticias/login")
    public String noticiaslog() {
		
        return "NoticiasLogueado.html";
    }
}
