/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.controladores;

import com.web.fixture.entidades.Equipo;
import com.web.fixture.entidades.Fixture;
import com.web.fixture.entidades.PartidoEliminatorio;
import com.web.fixture.entidades.PartidoGrupo;
import com.web.fixture.entidades.Usuario;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.servicios.FixtureServicio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fixture")
public class FixtureControlador {
    @Autowired
    private FixtureServicio fixtureServicio;
    
    @GetMapping("/fixture")
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
    public String fixture( ModelMap model, HttpSession session) throws ErrorServicio{
        //recupero la info del usuario logueado
        Usuario login = (Usuario) session.getAttribute("usuariosession");
        // recurro al Fixture del usuario para mostrar en pantalla la info 
        //que ya haya ingresado
        Fixture fixture = login.getFixture();
        List<PartidoGrupo> listaGrupo = new ArrayList();
        List<PartidoEliminatorio> listaEliminatorio = new ArrayList();
        // mostrar fase de grupos
        //armar fase eliminatorias
        //mostrar fase eliminatoria
        return "fixture.html";
 
    }
    
    /* MÉTODO GANADOR VA A UTILIZAR UNA QUERY DE EQUIPOREP para buscar el equipo que tiene como fase "ganador". 
    Este método debe ejecutarse una vez que se reciban los datos del último partido. Devuelve un objeto Equipo 
    que es luego mostrado en las vistas. Desde las vistas se podría hacer un if (si los campos de fase final 
    están llenos, entonces que llame a este método > /ganador {ganador*/ 
    @GetMapping("/ganador") ///REVER METODO.
    public Equipo ganador(){
        
        try {
            Equipo equipo = fixtureServicio.encontrarGanador();
            return equipo;
        } catch (Exception e) {
        System.err.print(e.getMessage());

        }
        return null;
    }
    
    @PostMapping("/definir-grupo-A")
    public String definirA(){
    return "redirect:/fixture";
    }
        @PostMapping("/definir-grupo-B")
    public String definirB(){
    return "redirect:/fixture";
    }
        @PostMapping("/definir-grupo-C")
    public String definirC(){
    return "redirect:/fixture";
    }
        @PostMapping("/definir-grupo-D")
    public String definirD(){
    return "redirect:/fixture";
    }
    
       
    
    
}
