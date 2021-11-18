package com.web.fixture.controladores;



import com.web.fixture.entidades.Usuario;
import com.web.fixture.repositorios.FixtureRepositorio;
import com.web.fixture.repositorios.PartidoGrupoRepositorio;
import com.web.fixture.servicios.FixtureServicio;

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
    private FixtureRepositorio fixtureRepositorio;
    @Autowired
    private PartidoGrupoRepositorio partidoGrupoRepositorio;
    @GetMapping("/")
    
    public String fixture(HttpSession session , @RequestParam String id) {
        Usuario login= (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getIdUsuario().equals(id))  {
        return "redirect:/inicio";}
        else{
            return "fixture.html";}
    }
    
    @GetMapping("/mostrar-Fixture")
    public String mostrarFixture(){
    /*model.put(datos previamente guardado )*/
    
    return "fixture.html";}
    
    
    @PostMapping("/definir-partido")
    public String definirPartidoGrupo(HttpSession session, ModelMap model ,@RequestParam String id, @RequestParam Integer golesEquipo1 , @RequestParam Integer golesEquipo2 ){
        
        /*testear metodos para elegir ganandor de la fase de grupos*/
        /*      persistir datos del usuario
          buscar el fixture del usuario
          traer el partido que corresponde  
        
        */
        
        
        
        System.out.println("id: " + id +"\n" + "||"  +  golesEquipo1 + " || " + golesEquipo2);
        return "redirect:/fixture";
        }
    
    
}
        

