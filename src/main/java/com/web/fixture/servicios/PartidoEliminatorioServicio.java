/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.servicios;

import com.web.fixture.entidades.Equipo;
import com.web.fixture.entidades.Fixture;
import com.web.fixture.entidades.ListaEquipos;
import com.web.fixture.entidades.PartidoEliminatorio;
import com.web.fixture.entidades.PartidoGrupo;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.EquipoRepositorio;
import com.web.fixture.repositorios.FixtureRepositorio;
import com.web.fixture.repositorios.PartidoEliminatorioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoEliminatorioServicio {

    @Autowired
    private PartidoEliminatorioRepositorio partidoRep;
//    @Autowired
//    private EquipoServicio equipoServicio;
    @Autowired
    private FixtureRepositorio fixtureRepositorio;
    @Autowired
    private Utilidades utiles;
    
     @Autowired
    private PartidoEliminatorioRepositorio partidoEliminatorioRepositorio;
    
    //             ====    Traer un partidoGrupo de un fixture existente   ==== 
    
    public PartidoEliminatorio traerPartido(String idFixture , String letraID) throws ErrorServicio{
        Fixture fixture = fixtureRepositorio.getById(idFixture);
        PartidoEliminatorio partido = null;
        List<PartidoEliminatorio> lista = fixture.getListaPartidosEliminatorio();
        
        for (PartidoEliminatorio partidoE : lista) {
            if(partidoE.getLetraID().equals(letraID)){
                partido = partidoE;
                break;
            }
        }
        return partido;

    }
    
    //   verificar si en cuartos los partidos estan completos
    public Boolean cuartosCompletada(String fixtureId) throws ErrorServicio{
    Boolean rta = true;
    Fixture fixture = fixtureRepositorio.findById(fixtureId).get();
    List<PartidoEliminatorio> listaE = fixture.getListaPartidosEliminatorio();
        for (Integer i =1 ; i<= 4 ; i++){
            PartidoEliminatorio pe =traerPartido(fixtureId , i.toString());
            if(pe.getGolesEquipo1() == null || pe.getGolesEquipo2() == null ){
            rta =false;
            break;
            } 
        }
    return rta;

    }
    
    public Boolean semisCompletada(String idFixture) throws ErrorServicio{
    Boolean rta = true;
    Fixture fixture = fixtureRepositorio.findById(idFixture).get();
    List<PartidoEliminatorio> listaE = fixture.getListaPartidosEliminatorio();
        for (Integer i =5 ; i<= 6 ; i++){
            PartidoEliminatorio pe =traerPartido(idFixture , i.toString());
            if(pe.getGolesEquipo1() == null || pe.getGolesEquipo2() == null ){
            rta =false;
            break;
            } 
        }
    return rta;
    
    }
    
//                   DEFINIR CUARTOS

    public void definirCuartos(String fixtureId) throws ErrorServicio{
    Fixture fixture = fixtureRepositorio.findById(fixtureId).get();
    
        //Partidos cuartos 1 y 2:
            PartidoEliminatorio pe1 = traerPartido(fixtureId , "1");
            PartidoEliminatorio pe2 = traerPartido(fixtureId , "2");
            ListaEquipos equipo1 = definirGanador(pe1);
            ListaEquipos equipo2 = definirGanador(pe2);
            PartidoEliminatorio semi1 = traerPartido(fixtureId , "5");
            semi1.setEquipo1(equipo1);
            semi1.setEquipo2(equipo2);
            System.out.println("partido 5 Definido " + semi1.getEquipo1().getPais());
            System.out.println("partido 5 Definido " + semi1.getEquipo2().getPais());
            partidoEliminatorioRepositorio.save(semi1);
            
            
           //Partidos cuartos 3 y 4:
            PartidoEliminatorio pe3 = traerPartido(fixtureId , "3");
            PartidoEliminatorio pe4 = traerPartido(fixtureId , "4");
            ListaEquipos equipo3 = definirGanador(pe3);
            ListaEquipos equipo4 = definirGanador(pe4);
            PartidoEliminatorio semi2 = traerPartido(fixtureId , "6");
            semi2.setEquipo1(equipo3);
            semi2.setEquipo2(equipo4);
            System.out.println("partido 6 Definido " + semi2.getEquipo1().getPais());
            System.out.println("partido 6 Definido " + semi2.getEquipo2().getPais());
            partidoEliminatorioRepositorio.save(semi1);   
            
            
            

    }    
    
    //              definir ganador
    public ListaEquipos definirGanador(PartidoEliminatorio partido){
        ListaEquipos ganador = null;
        if(partido.getGolesEquipo1() > partido.getGolesEquipo2()){
            ganador = partido.getEquipo1();
        }else{
        ganador = partido.getEquipo2();
        }
        return ganador;
    }

    
}