
package com.web.fixture.controladores;

import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.PartidoEliminatorioRepositorio;
import com.web.fixture.repositorios.PartidoGrupoRepositorio;
import com.web.fixture.servicios.FixtureServicio;
import com.web.fixture.servicios.PartidoEliminatorioServicio;
import com.web.fixture.servicios.PartidoGrupoServicio;
import com.web.fixture.servicios.UsuarioServicio;
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
    private PartidoEliminatorioServicio partidoEliServicio;
    
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
    public String registrar(ModelMap modelo, MultipartFile archivo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {

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
    
    //EL FIXTURE SOLO PODRA INGRESAR UN USUARIO LOGUEADO.
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")
    @GetMapping("/fixture")
    public String fixture() {
		
        return "fixture.html";
    }
    
//     A ESTADISTICAS SOLO PODRA INGRESAR UN USUARIO LOGUEADO, DEBIDO QUE LAS MISMAS SE CREAN EN BASE AL FIXTURE COMPLETADO
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")
    @GetMapping("/estadisticas")
    public String estadisticas() {
		
        return "estadisticas.html";
    }
  

    
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio.html";
    }
}
