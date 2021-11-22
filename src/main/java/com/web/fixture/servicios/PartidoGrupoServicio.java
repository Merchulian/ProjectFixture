/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.servicios;

import com.web.fixture.entidades.Equipo;
import com.web.fixture.entidades.Fixture;
import com.web.fixture.entidades.PartidoGrupo;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.FixtureRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.fixture.repositorios.PartidoGrupoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
public class PartidoGrupoServicio {

    @Autowired
    private PartidoGrupoRepositorio partidoGrupoRepositorio;
    @Autowired
    private EquipoServicio equipoServicio;
    @Autowired
    private FixtureRepositorio fixtureRepositorio;
    
//             ====    Traer un partidoGrupo de un fixture existente   ==== 
    public PartidoGrupo traerPartido(String idFixture , String idPartido) throws ErrorServicio{
        Fixture fixture = fixtureRepositorio.getById(idFixture);
        PartidoGrupo partido = new PartidoGrupo();
        List<PartidoGrupo> lista = fixture.getListaPartidosGrupos();
        for (PartidoGrupo partidoGrupo : lista) {
            if(partidoGrupo.getTag().equals(idPartido)){
                partido = partidoGrupo;
                break;
            }
        }
        System.out.println("Me traje el partido nro: " + partido.getTag());
        return partido;

    }
//                 ====    Guardar un Partido    ====
    @Transactional
    public void guardarPartido(String fixtureId ,String idPartido ,Integer golesEquipo1,Integer golesEquipo2) throws ErrorServicio{
        try{
            System.out.println("has ingresado golesEquipo1: " + golesEquipo1);
            System.out.println("has ingresado golesEquipo2: " + golesEquipo2);
            
        PartidoGrupo partido = traerPartido(fixtureId, idPartido);
        partido.setGolesEquipo1(golesEquipo1);
        partido.setGolesEquipo2(golesEquipo2);
        System.out.println("goles equipo 1: " + partido.getEquipo1());
        System.out.println("goles equipo 2: " + partido.getEquipo2());
        /*persistir los datos cargados*/
        partidoGrupoRepositorio.save(partido);
        System.out.println("partido "+partido.getIdPartido()+ " guardado!!!");
        }catch(ErrorServicio ex){
        ex.getMessage();
        }
    
    }
    
    
    
//                  ====    Definir Partido    ====
@Transactional        
    public void definirPartido(Integer golesEquipo1, Integer golesEquipo2, Integer idPartido) throws ErrorServicio {

        validar(golesEquipo1, golesEquipo2, idPartido);

        Optional<PartidoGrupo> demo = partidoGrupoRepositorio.findById(idPartido);

        if (demo.isPresent()) {
            PartidoGrupo partido = demo.get();

            if (golesEquipo1 > golesEquipo2) { //si gana por goles equipo 1, suma 3 puntos a su puntaje

                partido.getEquipo1().setPuntaje(partido.getEquipo1().getPuntaje() + 3);

            } else if (golesEquipo1 < golesEquipo2) { //si gana por goles equipo 2, suma 3 puntos a su puntaje

                partido.getEquipo2().setPuntaje(partido.getEquipo2().getPuntaje() + 3);

            } else { //si ninguno gana, es que empataron, suman 1 pto cada uno

                partido.getEquipo1().setPuntaje(partido.getEquipo1().getPuntaje() + 1);
                partido.getEquipo2().setPuntaje(partido.getEquipo2().getPuntaje() + 1);
            }

            equipoServicio.estadisticaPartido(partido.getEquipo1(), partido.getEquipo2(), golesEquipo1, golesEquipo2);
            partidoGrupoRepositorio.save(partido);

        } else {
            throw new ErrorServicio("El partido no se encuentra en la base de datos.");
        }

    }
//                             ====    Definir Grupo    ====
    public void definirGrupo(String grupo, String idFixture ){
        Optional<Fixture> rta = fixtureRepositorio.findById(idFixture);
        if(rta.isPresent()){
            Fixture fixture = rta.get();
            List<PartidoGrupo> listaG = fixture.getListaPartidosGrupos();
            ArrayList<PartidoGrupo> grupoADefinir = new ArrayList();
            for (PartidoGrupo partidoGrupo : listaG) {
                if( partidoGrupo.getGrupo().equalsIgnoreCase(grupo) ){
                    listaG.add(partidoGrupo);
                }
            }
            for (PartidoGrupo partidoGrupo : grupoADefinir) {
                Equipo primerEquipo = null;
                Equipo SegundoEquipo = null;
                
                
            }
        
        }
        
    
    
    }
    
    
//                            ====    Validar datos    ====
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
