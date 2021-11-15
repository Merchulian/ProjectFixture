/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.controladores;

import com.web.fixture.entidades.Equipo;
import com.web.fixture.repositorios.FixtureRepositorio;
import com.web.fixture.servicios.FixtureServicio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fixture")
public class FixtureControlador {
    
    private FixtureServicio fixtureServicio;
    
    @GetMapping("/fixture")
    public String fixture() {

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
    
       
    
    
}
