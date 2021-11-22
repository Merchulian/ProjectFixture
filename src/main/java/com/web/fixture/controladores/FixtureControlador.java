/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.controladores;

import com.web.fixture.entidades.Fixture;
import com.web.fixture.entidades.Usuario;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.FixtureRepositorio;
import com.web.fixture.repositorios.PartidoGrupoRepositorio;
import com.web.fixture.servicios.FixtureServicio;
import com.web.fixture.servicios.PartidoEliminatorioServicio;
import com.web.fixture.servicios.PartidoGrupoServicio;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/fixture")
public class FixtureControlador {
    
    @Autowired
    private FixtureServicio fixtureServicio;
    @Autowired
    private PartidoGrupoServicio partidoGrupoSevicio ;
    @Autowired
    private PartidoGrupoRepositorio partidoGrupoRepositorio ;
    @Autowired
    private FixtureRepositorio fixtureRepositorio ;
    
    @Autowired
    private PartidoEliminatorioServicio partidoEliminatorioServicio;
    
   //@GetMapping("/")
   //@PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")
    //public String fixture(HttpSession session, ModelMap model) {
        /*Rescato la sesdion del usuario*/
      //  Usuario usuario = (Usuario) session.getAttribute("usuariosession");
       // Fixture fixture = usuario.getFixture();
       // model.put("golesEquipo1_1",9);
       // model.put("golesEquipo2_1",9);

        //de la db rescato la info de los partidos guardada y la pongo en el document
        //return "fixture.html";
    //}

    @PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")   
    @PostMapping("/definir-partido")
    /*depende cual boton aprieto, llega como id el del partido y a partir de alli guardo el partido*/
    public String definirPartido(HttpSession session, ModelMap model ,@RequestParam String id ,@RequestParam Integer golesEquipo1,@RequestParam Integer golesEquipo2) throws ErrorServicio {
        System.out.println("id: " + id);
        /*Recupero la session del usuario logueado*/
        try{
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
            System.out.println("el id del Usuario es " + usuario.getIdUsuario());
            
            System.out.println("idFixture :" + usuario.getFixture().getId());
    /*Traer el fixture del usuario*/
    
            Fixture fixture = usuario.getFixture();
            System.out.println("idFixture:" + fixture.getId());
    /*modificar el partido que corresponde, segun el id*/
        partidoGrupoSevicio.guardarPartido(fixture.getId() , id , golesEquipo1, golesEquipo2);
        }catch(ErrorServicio e){
        e.getMessage();
        }finally{
        return "redirect:/fixture";
        /* el controlador de la accion /fixture esta en PortalControlador*/
        }
    }
    
    @GetMapping("/guardar")
    public String guardar(ModelMap model, @RequestParam Integer idPartido, @RequestParam String letraID, @RequestParam Integer goles1, @RequestParam Integer goles2, Integer complementario1, Integer complementario2, Integer penales1, Integer penales2) throws ErrorServicio {
        
        try {
            
            partidoEliminatorioServicio.guardarPartido(idPartido, letraID, goles1, goles2, complementario1, complementario2, penales1, penales2);
            
            
            return "fixture.html";
            
        } catch (Exception e) {
            
            System.out.println(e);
           
            ErrorServicio err = new ErrorServicio("Los cambios del partido no se guardaron debido a un error. Verifique los datos ingresados e intente guardarlos nuevamente.");
            model.put("error", err);
            
            return "fixture.html";
        }
        
    }
    
}
