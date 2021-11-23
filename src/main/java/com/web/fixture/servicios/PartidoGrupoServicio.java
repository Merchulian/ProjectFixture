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
import com.web.fixture.repositorios.EquipoRepositorio;
import com.web.fixture.repositorios.FixtureRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.fixture.repositorios.PartidoGrupoRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    @Autowired
    private EquipoRepositorio equipoRepositorio;
    
//             ====    Traer un partidoGrupo de un fixture existente   ==== 
    
    public PartidoGrupo traerPartido(String idFixture , String tagPartido) throws ErrorServicio{
        Fixture fixture = fixtureRepositorio.getById(idFixture);
        PartidoGrupo partido = null;
        List<PartidoGrupo> lista = fixture.getListaPartidosGrupos();
        
        for (PartidoGrupo partidoGrupo : lista) {
            if(partidoGrupo.getTag().equals(tagPartido)){
                partido = partidoGrupo;
                break;
            }
        }
        return partido;

    }
//                 ====    Guardar un Partido    ====
    @Transactional
    public void guardarPartido(String fixtureId ,String tagPartido ,Integer golesEquipo1,Integer golesEquipo2) throws ErrorServicio{
        try{
            // traigo el partido correspondiente    
            PartidoGrupo partido = traerPartido(fixtureId, tagPartido);
            if(partido !=null){
                partido.setGolesEquipo1(golesEquipo1);
                partido.setGolesEquipo2(golesEquipo2);
                System.out.println(partido.toString());
                partidoGrupoRepositorio.save(partido);
                System.out.println(partidoGrupoRepositorio.findById(partido.getIdPartido()).toString());
            }
            }catch(ErrorServicio ex){
        ex.getMessage();
        }
    
    }
    
    
// =============================================================================    
//           ====    guardar los puntos de la fase de grupos    ====
    
    
    
//  Esta funcion deberia llamarla al invocar la vista del fixture
//  que se fije si puede definir los ganadores cada vez que recarga la pagina.    
// =============================================================================    
@Transactional       
public void guardarEstadisticas(String fixtureId) throws ErrorServicio {
        Fixture fixture = fixtureRepositorio.findById(fixtureId).get();
        System.out.println("===================================================");
        System.out.println("entre a estadisticas: " +fixture.getId());
        
        
        List<PartidoGrupo> partidosG = fixture.getListaPartidosGrupos();
        System.out.println("traigo lista de partidos:   " + partidosG.size());
        // con este if, voy barriendo los tagEquipos
        for (Integer i = 1; i <= 16; i++) {
            System.out.println("=====================================================");
            System.out.println(i+"° iteracion: \n");
            Equipo equipo = buscarPorTag(fixtureId, i.toString());
            
            if(equipo !=null){
            System.out.println(equipo.toString());
            
            Integer puntaje=0;
            Integer golesFavor=0;
            Integer golesContra=0;
            for (PartidoGrupo partido : partidosG) {
                if(partido.getGolesEquipo1() != null && partido.getGolesEquipo1() !=null){
                // me fijo si el equipo jugó el partido
                 // Primer if: si fue el equipo1
                if(partido.getEquipo1().getIdEquipo().equals(i)){
                    System.out.println("partido: "+ partido.getTag() +" "+ equipo.getPais() + " fue el 1er equipo");
                    
                    golesFavor = golesFavor + partido.getGolesEquipo1();
                    golesContra = golesContra + partido.getGolesEquipo2();
                    //depende si gano o no le mando los puntos
                    if(partido.getGolesEquipo1() > partido.getGolesEquipo2()){
                        System.out.println("y gano");
                        puntaje = puntaje +3;  // si gana le doy 3 puntos
                    }else if( Objects.equals(partido.getGolesEquipo1(), partido.getGolesEquipo2()) ){
                    puntaje = puntaje + 1;  // si empata le doy 1 punto
                    }
                }
                // Primer if: si fue el segundo Equipo
                else if(partido.getEquipo2().getIdEquipo().equals(i)){
                    System.out.println("partido: "+ partido.getTag() +" "+ equipo.getPais() + " fue el 2d0 equipo");
                    golesFavor = golesFavor + partido.getGolesEquipo2();
                    golesContra = golesContra + partido.getGolesEquipo1();
                    //depende si gano o no le mando los puntos
                    if(partido.getGolesEquipo2() > partido.getGolesEquipo1()){
                        System.out.println("y gano");
                        puntaje = puntaje +3;  // si gana le doy 3 puntos
                    }else if( partido.getGolesEquipo1() == partido.getGolesEquipo2() ){
                    puntaje = puntaje + 1;  // si empata le doy 1 punto
                    }
                }else{         //si no aparece 
                    continue;
                }
                }else{continue;} //si hay goles nulos...
                }//end Fore 
                //en este punto deberia haber acumulado los goles y puntaje de un equipo en poarticular)
                //guardo esta info en la base de datos:
                equipo.setGolesFavor(golesFavor);
                equipo.setGolesContra(golesContra);
                equipo.setPuntaje(puntaje);
                System.out.println("3) " + equipo.toString());
                equipoRepositorio.save(equipo);
            }//end If equipo != null  (sigue con el siguiente equipoS)
        }
        
        
    }
        
    
        
    private Equipo buscarPorTag(String idFixture, String tag){
    Equipo equipo = null;
    Fixture fixture = fixtureRepositorio.findById(idFixture).get();
    List<Equipo> equipos = fixture.getListaEquipos();
        System.out.println("======");
        System.out.println("lista equipos fixture: " + equipos.size());
        System.out.println("======");
        
        for (Equipo item : equipos ) {
            if( item.getNumeroEquipo().toString().equals(tag) ){
                equipo = item;
                break;
            }
        }
    return equipo;
    }

        
        
    
       
// ==============================================================================    
// =========================    Definir Grupo    ================================
// ==============================================================================
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
