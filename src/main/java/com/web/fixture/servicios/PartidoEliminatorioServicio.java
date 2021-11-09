/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.servicios;

import com.web.fixture.entidades.Equipo;
import com.web.fixture.entidades.PartidoEliminatorio;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.EquipoRepositorio;
import com.web.fixture.repositorios.PartidoEliminatorioRepositorio;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoEliminatorioServicio {

    @Autowired
    private PartidoEliminatorioRepositorio partidoRep;
    @Autowired
    private EquipoServicio equipoServicio;
    @Autowired
    private EquipoRepositorio equipoRepositorio;
    
    public  void pase3and4yPaseFinal(){
        ArrayList<PartidoEliminatorio> Partidos3ery4to = partidoRep.buscarPartidosxFase("3ER Y 4TO Puesto");
      ArrayList<PartidoEliminatorio> PartidosFinal = partidoRep.buscarPartidosxFase("Final");
       ArrayList<PartidoEliminatorio> PartidosSemi = partidoRep.buscarPartidosxFase("Semifinales");
    
        for (PartidoEliminatorio partidoSemi : PartidosSemi) {
            if (partidoSemi.getIdPartido() == 5) {
                if (partidoSemi.getEquipo1().getFase().equals("Final")) {
                    for (PartidoEliminatorio partido : PartidosFinal) {
                        partido.setEquipo1(partidoSemi.getEquipo1());
                        partidoRep.save(partido);
                    }
                    for (PartidoEliminatorio partido3ery4to : Partidos3ery4to) {
                        partido3ery4to.setEquipo1(partidoSemi.getEquipo2());                   
                        partidoRep.save(partido3ery4to);
                    }
                }else{
                   for (PartidoEliminatorio partido : PartidosFinal) {
                        partido.setEquipo1(partidoSemi.getEquipo2());
                        partidoRep.save(partido);
                    }
                    for (PartidoEliminatorio partido3ery4to : Partidos3ery4to) {
                        partido3ery4to.setEquipo1(partidoSemi.getEquipo1());                   
                        partidoRep.save(partido3ery4to);
                    }
                }
            }
            if (partidoSemi.getIdPartido() == 6) {
                if (partidoSemi.getEquipo1().getFase().equals("Final")) {
                    for (PartidoEliminatorio partido : PartidosFinal) {
                        partido.setEquipo2(partidoSemi.getEquipo1());
                        partidoRep.save(partido);
                    }
                    for (PartidoEliminatorio partido3ery4to : Partidos3ery4to) {
                        partido3ery4to.setEquipo2(partidoSemi.getEquipo2());                   
                        partidoRep.save(partido3ery4to);
                    }
                }else{
                   for (PartidoEliminatorio partido : PartidosFinal) {
                        partido.setEquipo2(partidoSemi.getEquipo2());
                        partidoRep.save(partido);
                    }
                    for (PartidoEliminatorio partido3ery4to : Partidos3ery4to) {
                        partido3ery4to.setEquipo2(partidoSemi.getEquipo1());                   
                        partidoRep.save(partido3ery4to);
                    }
                }
            }
        }
        
    }

    public  void paseSemiFinales(){
       ArrayList<PartidoEliminatorio> PartidosCuartos = partidoRep.buscarPartidosxFase("Cuartos");
       ArrayList<PartidoEliminatorio> PartidosSemi = partidoRep.buscarPartidosxFase("Semifinales");
       
        for (PartidoEliminatorio PartidoCuartos : PartidosCuartos) {
            if (PartidoCuartos.getIdPartido() == 1) {
                if (PartidoCuartos.getEquipo1().getFase().equals("Semifinal")) {
                    for (PartidoEliminatorio PartidoSemi : PartidosSemi) {
                        if (PartidoSemi.getIdPartido() == 5 ) {
                            PartidoSemi.setEquipo1(PartidoCuartos.getEquipo1());
                            partidoRep.save(PartidoSemi);
                        }
                    }
                }else{
                    for (PartidoEliminatorio PartidoSemi : PartidosCuartos) {
                        if (PartidoSemi.getIdPartido() == 5 ) {
                            PartidoSemi.setEquipo1(PartidoCuartos.getEquipo2());
                            partidoRep.save(PartidoCuartos);
                        }
                    } 
                }
            }
            if (PartidoCuartos.getIdPartido() == 2) {
                if (PartidoCuartos.getEquipo1().getFase().equals("Semifinal")) {
                    for (PartidoEliminatorio PartidoSemi : PartidosSemi) {
                        if (PartidoSemi.getIdPartido() == 5 ) {
                            PartidoSemi.setEquipo2(PartidoCuartos.getEquipo1());
                            partidoRep.save(PartidoSemi);
                        }
                    }
                }else{
                    for (PartidoEliminatorio PartidoSemi : PartidosCuartos) {
                        if (PartidoSemi.getIdPartido() == 5 ) {
                            PartidoSemi.setEquipo2(PartidoCuartos.getEquipo2());
                            partidoRep.save(PartidoCuartos);
                        }
                    } 
                }
            }
            if (PartidoCuartos.getIdPartido() == 3) {
                if (PartidoCuartos.getEquipo1().getFase().equals("Semifinal")) {
                    for (PartidoEliminatorio PartidoSemi : PartidosSemi) {
                        if (PartidoSemi.getIdPartido() == 6 ) {
                            PartidoSemi.setEquipo1(PartidoCuartos.getEquipo1());
                            partidoRep.save(PartidoSemi);
                        }
                    }
                }else{
                    for (PartidoEliminatorio PartidoSemi : PartidosCuartos) {
                        if (PartidoSemi.getIdPartido() == 6 ) {
                            PartidoSemi.setEquipo1(PartidoCuartos.getEquipo2());
                            partidoRep.save(PartidoCuartos);
                        }
                    } 
                }
            }
            if (PartidoCuartos.getIdPartido() == 4) {
                if (PartidoCuartos.getEquipo1().getFase().equals("Semifinal")) {
                    for (PartidoEliminatorio PartidoSemi : PartidosSemi) {
                        if (PartidoSemi.getIdPartido() == 6 ) {
                            PartidoSemi.setEquipo2(PartidoCuartos.getEquipo1());
                            partidoRep.save(PartidoSemi);
                        }
                    }
                }else{
                    for (PartidoEliminatorio PartidoSemi : PartidosCuartos) {
                        if (PartidoSemi.getIdPartido() == 6 ) {
                            PartidoSemi.setEquipo2(PartidoCuartos.getEquipo2());
                            partidoRep.save(PartidoCuartos);
                        }
                    } 
                }
            }
       }
       
   
   }
    
    public void paseCuartos(){
       ArrayList<Equipo> listaGrupoA=  equipoRepositorio.buscarPorGrupo("grupo A");
        ArrayList<Equipo> listaGrupoB=  equipoRepositorio.buscarPorGrupo("grupo B");
        ArrayList<Equipo> listaGrupoC=  equipoRepositorio.buscarPorGrupo("grupo C");
        ArrayList<Equipo> listaGrupoD=  equipoRepositorio.buscarPorGrupo("grupo D");
        
        ArrayList<PartidoEliminatorio> PartidosOctavos = partidoRep.buscarPartidosxFase("Cuartos");
        
        for (PartidoEliminatorio Partido : PartidosOctavos) { 
            if (Partido.getIdPartido() == 1) {
                Partido.setEquipo1(listaGrupoA.get(0));
                Partido.setEquipo2(listaGrupoB.get(1));
                cambioFaseOctavos(listaGrupoA.get(0));
                cambioFaseOctavos(listaGrupoB.get(1));
                partidoRep.save(Partido);
            }
            if (Partido.getIdPartido() == 2) {
                Partido.setEquipo1(listaGrupoC.get(0));
                Partido.setEquipo2(listaGrupoD.get(1));
                cambioFaseOctavos(listaGrupoC.get(0));
                cambioFaseOctavos(listaGrupoD.get(1));
                partidoRep.save(Partido);
            }
             if (Partido.getIdPartido() == 3) {
                Partido.setEquipo1(listaGrupoB.get(0));
                Partido.setEquipo2(listaGrupoA.get(1));
                cambioFaseOctavos(listaGrupoB.get(0));
                cambioFaseOctavos(listaGrupoA.get(1));
                partidoRep.save(Partido);
            }
            if (Partido.getIdPartido() == 4) {
                Partido.setEquipo1(listaGrupoD.get(0));
                Partido.setEquipo2(listaGrupoC.get(1));
                cambioFaseOctavos(listaGrupoD.get(0));
                cambioFaseOctavos(listaGrupoC.get(1));
                partidoRep.save(Partido);
            }
        }
    }

    public void cambioFaseOctavos(Equipo equipo) {
         equipo.setFase("Cuartos");
         equipoRepositorio.save(equipo);   
    }

    public void definirPartido(Integer golesEquipo1, Integer golesEquipo2, Integer idPartido, Integer golesComplementario1, Integer golesComplementario2, Integer penales1, Integer penales2) throws ErrorServicio {

        validar(golesEquipo1, golesEquipo2, idPartido);

        Optional<PartidoEliminatorio> demo = partidoRep.findById(idPartido);

        if (demo.isPresent()) {
            PartidoEliminatorio partido = demo.get();

            if (golesEquipo1 > golesEquipo2) {
                partido.getEquipo2().setBaja(partido.getFecha());      
                cambioFase(partido.getEquipo1());

            } else if (golesEquipo1 < golesEquipo2) { 
                partido.getEquipo1().setBaja(partido.getFecha());
               cambioFase(partido.getEquipo2());

            } else if (golesEquipo1 == golesEquipo2) {

                if (golesComplementario1 > golesComplementario2) {
                    partido.getEquipo2().setBaja(partido.getFecha());
                    cambioFase(partido.getEquipo1());

                }else if (golesComplementario1 < golesComplementario2) {
                    partido.getEquipo1().setBaja(partido.getFecha());
                    cambioFase(partido.getEquipo2());

                }else if (golesComplementario1 == golesComplementario2) {

                    if (penales1 > penales2) {
                    partido.getEquipo2().setBaja(partido.getFecha());
                    cambioFase(partido.getEquipo1());
                    }else{
                    partido.getEquipo1().setBaja(partido.getFecha());
                    cambioFase(partido.getEquipo2());
                    }
                }
            }
            partidoRep.save(partido);
        } else {
            throw new ErrorServicio("El partido no se encuentra en la base de datos.");
        }
    }
    
    public void cambioFase(Equipo equipo) {
        
//Habría que validar algún dato acá???? Me trae una copia del objeto equipo. le seteo la fase.

        if (equipo.getFase().equals("Cuartos")) {
             equipo.setFase("Semifinal");
        }else if (equipo.getFase().equals("Semifinal")) {
             equipo.setFase("Final");
        }else if (equipo.getFase().equals("Final")) {
             equipo.setFase("Ganador");
        }
        
        equipoRepositorio.save(equipo); //persisto el equipo en BD
    }

    private void validar(Integer golesEquipo1, Integer golesEquipo2, Integer idPartido) throws ErrorServicio {

        if (golesEquipo1 == null || golesEquipo2 == null) {

            throw new ErrorServicio("Debe completar la cantidad de goles.");
        }

        if (idPartido == null) {
            System.out.println("el ID del partido es nulo.");
            throw new ErrorServicio("Error interno.");
        }

    }
}
