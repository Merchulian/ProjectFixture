/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.controladores;

import com.web.fixture.entidades.Fixture;
import com.web.fixture.entidades.PartidoEliminatorio;
import com.web.fixture.entidades.Usuario;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.FixtureRepositorio;
import com.web.fixture.repositorios.PartidoEliminatorioRepositorio;
import com.web.fixture.repositorios.PartidoGrupoRepositorio;
import com.web.fixture.servicios.FixtureServicio;
import com.web.fixture.servicios.PartidoEliminatorioServicio;
import com.web.fixture.servicios.PartidoGrupoServicio;
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

@Controller
@RequestMapping("/fixture")
public class FixtureControlador {
    
    @Autowired
    private FixtureServicio fixtureServicio;
    @Autowired
    private PartidoGrupoServicio partidoGrupoServicio ;
    @Autowired
    private PartidoGrupoRepositorio partidoGrupoRepositorio ;
    @Autowired
    private FixtureRepositorio fixtureRepositorio ;
    
    @Autowired
    private PartidoEliminatorioServicio partidoEliminatorioServicio;
    @Autowired
    private PartidoEliminatorioRepositorio partidoEliminatorioRepositorio;
    
    
//             ====    guardar formularios de los partidos de Grupos   ====
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")   
    @PostMapping("/definir-partido")
    /*depende cual boton aprieto, llega como id el del partido y a partir de alli guardo el partido*/
    public String definirPartido(HttpSession session, ModelMap model ,@RequestParam String id ,@RequestParam(required = false) Integer golesEquipo1,@RequestParam(required = false) Integer golesEquipo2) throws ErrorServicio {
        /*Recupero la session del usuario logueado*/
        try{
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");

            //Traer el fixture del usuario    
            Fixture fixture = usuario.getFixture();
            //modificar el partido que corresponde, segun el id*/
            
                partidoGrupoServicio.guardarPartido(fixture.getId() , id /*id del usuario*/, golesEquipo1, golesEquipo2);
                
              
                
        }catch(ErrorServicio e){
            System.out.println(e.getMessage());
        }finally{
        return "redirect:/fixture";
        /* el controlador de la accion /fixture esta en PortalControlador*/
        }
    }
    
    //             ====    guardar formularios de los partidos de Cuartos   ====
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")   
    @PostMapping("/definir-cuartos")
    /*deben estar precargados los 4 partidos con goles para continuar*/
    public String definirCuartos(HttpSession session, ModelMap model ,@RequestParam Integer golesPe1Eq1 ,@RequestParam Integer golesPe1Eq2,@RequestParam Integer golesPe2Eq1 ,@RequestParam Integer golesPe2Eq2 , @RequestParam Integer golesPe3Eq1 ,@RequestParam Integer golesPe3Eq2 , @RequestParam Integer golesPe4Eq1 ,@RequestParam Integer golesPe4Eq2) throws ErrorServicio {
        /*Recupero la session del usuario logueado*/
        Usuario usuario = (Usuario) session.getAttribute("usuariosession");
        //Traer el fixture del usuario
        Fixture fixture = usuario.getFixture();
        //Llamo a los 4 patidos de cuartos les pongo los goles y los guardo*/
        
        PartidoEliminatorio pe1 = partidoEliminatorioServicio.traerPartido(fixture.getId(),"1");
        pe1.setGolesEquipo1(golesPe1Eq1);
        pe1.setGolesEquipo2(golesPe1Eq2);
        partidoEliminatorioRepositorio.save(pe1);
            
        PartidoEliminatorio pe2 = partidoEliminatorioServicio.traerPartido(fixture.getId(),"2");
        pe2.setGolesEquipo1(golesPe2Eq1);
        pe2.setGolesEquipo2(golesPe2Eq2);
        partidoEliminatorioRepositorio.save(pe2);
        
        PartidoEliminatorio pe3 = partidoEliminatorioServicio.traerPartido(fixture.getId(),"3");
        pe3.setGolesEquipo1(golesPe3Eq1);
        pe3.setGolesEquipo2(golesPe3Eq2);
        partidoEliminatorioRepositorio.save(pe3);
            
        PartidoEliminatorio pe4 = partidoEliminatorioServicio.traerPartido(fixture.getId(),"4");
        pe4.setGolesEquipo1(golesPe4Eq1);
        pe4.setGolesEquipo2(golesPe4Eq2);
        partidoEliminatorioRepositorio.save(pe4);
        
        
        
        //SdefinirPartidosCuartos();
        return "redirect:/fixture";
        /* el controlador de la accion /fixture esta en PortalControlador*/
    }
    
    
    
    
    @GetMapping("/guardar")
    public String guardar(ModelMap model, @RequestParam Integer idPartido, @RequestParam String letraID, @RequestParam Integer goles1, @RequestParam Integer goles2, Integer complementario1, Integer complementario2, Integer penales1, Integer penales2) throws ErrorServicio {
        
        try {
            
           // partidoEliminatorioServicio.guardarPartido(idPartido, letraID, goles1, goles2, complementario1, complementario2, penales1, penales2);
            
            
            return "fixture.html";
            
        } catch (Exception e) {
            
            System.out.println(e);
           
            ErrorServicio err = new ErrorServicio("Los cambios del partido no se guardaron debido a un error. Verifique los datos ingresados e intente guardarlos nuevamente.");
            model.put("error", err);
            
            return "fixture.html";
        }
        
    }
    
}
