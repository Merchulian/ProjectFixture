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
    private PartidoEliminatorioServicio partidoEliminatorioServicio;
    @Autowired
    private PartidoEliminatorioRepositorio partidoEliminatorioRepositorio;
    @Autowired
    private EquipoServicio equipoServicio;
    @Autowired
    private FixtureRepositorio fixtureRepositorio;
    @Autowired
    private EquipoRepositorio equipoRepositorio;
    @Autowired
    private Utilidades utilidades;
    
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
//           ====    METODOS DE DEFINICION DE FASES    ====     
// ============================================================================= 

// 1) ESTADISTICAS PARA DETERMINAR QUIENES PASAN
    
/*  Esta funcion la llamo al invocar la vista del fixture que se fije si puede 
definir los ganadores cada vez que recarga la pagina. */    
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
                //guardo esta info en la base de datos solo si estan guardados todos los partidos:
                
                    System.out.println("fase de grupos completada");
                    equipo.setGolesFavor(golesFavor);
                    equipo.setGolesContra(golesContra);
                    equipo.setPuntaje(puntaje);
                    System.out.println("3) " + equipo.toString());
                    equipoRepositorio.save(equipo);       
            }//end If equipo != null  (sigue con el siguiente equipoS)
        }  
    }

// 2)    PASE A CUARTOS
/*Aca pasan los dos mejores de cada grupo (definen por puntos o por goles)*/

public void definirCuartos(String idFixture) throws ErrorServicio{
    Fixture fixture = fixtureRepositorio.findById(idFixture).get();
    // veo la lista de equipos y defino quienes son los primeros ganadores de cada grupo
    List<Equipo> listaCompleta = fixture.getListaEquipos();
    //para buscar los 4 paises de cada grupo
    Integer id1;
    Integer id2;
    Integer id3;
    Integer id4;
    
    
  //grupo A:      
    
    id1= buscarPorTag(idFixture , "1").getIdEquipo();
    id2= buscarPorTag(idFixture , "2").getIdEquipo();
    id3= buscarPorTag(idFixture , "3").getIdEquipo();
    id4= buscarPorTag(idFixture , "4").getIdEquipo();
    System.out.println(id1 + "  " + id2 + "  " + id3+ "  " + id4);
    ArrayList<Equipo> grupoAorden = equipoRepositorio.buscarPorGrupo("A", id1, id2, id3, id4);
    Equipo equipoA1 =null;
    Equipo equipoA2 = null;
    //definir ganadores del grupo A:
    if(grupoAorden.get(0).getPuntaje()>grupoAorden.get(1).getPuntaje()){
        equipoA1 =grupoAorden.get(0);
        if(grupoAorden.get(1).getPuntaje()>grupoAorden.get(2).getPuntaje()){
            equipoA2 =grupoAorden.get(1);
        }else if(grupoAorden.get(1).getPuntaje() == grupoAorden.get(2).getPuntaje()){
            Integer diferencia1= grupoAorden.get(1).getGolesFavor() - grupoAorden.get(1).getGolesContra();
            Integer diferencia2= grupoAorden.get(2).getGolesFavor() - grupoAorden.get(2).getGolesContra();
            if(diferencia1 > diferencia2){
                equipoA2 = grupoAorden.get(1);
            }else{equipoA2 = grupoAorden.get(2);}
                
            }
        } else if(grupoAorden.get(0).getPuntaje()==grupoAorden.get(1).getPuntaje()){
        Integer diferencia1= grupoAorden.get(0).getGolesFavor() - grupoAorden.get(0).getGolesContra();
            Integer diferencia2= grupoAorden.get(1).getGolesFavor() - grupoAorden.get(1).getGolesContra();
            if(diferencia1 > diferencia2){
                equipoA1 = grupoAorden.get(0);
                equipoA2 = grupoAorden.get(1);
            }else{equipoA1 = grupoAorden.get(1);
                equipoA2 = grupoAorden.get(0);}
        }//fin Definicion de ganadores
    System.out.println("ganadores del grupo A: ");
    System.out.println("equipo1:"+equipoA1.getPais()+ " " + equipoA1.toString());
    System.out.println("equipo2:"+equipoA2.getPais()+ " " + equipoA2.toString());
    

    // Definir Grupo B
       
    id1= buscarPorTag(idFixture , "5").getIdEquipo();
    id2= buscarPorTag(idFixture , "6").getIdEquipo();
    id3= buscarPorTag(idFixture , "7").getIdEquipo();
    id4= buscarPorTag(idFixture , "8").getIdEquipo();
    System.out.println(id1 + "  " + id2 + "  " + id3+ "  " + id4);
    ArrayList<Equipo> grupoBorden = equipoRepositorio.buscarPorGrupo("B", id1, id2, id3, id4);
    Equipo equipoB1 =null;
    Equipo equipoB2 = null;
    //definir ganadores del grupo B:
    if(grupoBorden.get(0).getPuntaje()>grupoBorden.get(1).getPuntaje()){
        equipoB1 =grupoBorden.get(0);
        if(grupoBorden.get(1).getPuntaje()>grupoBorden.get(2).getPuntaje()){
            equipoB2 =grupoBorden.get(1);
        }else if(grupoBorden.get(1).getPuntaje() == grupoBorden.get(2).getPuntaje()){
            Integer diferencia1= grupoBorden.get(1).getGolesFavor() - grupoBorden.get(1).getGolesContra();
            Integer diferencia2= grupoBorden.get(2).getGolesFavor() - grupoBorden.get(2).getGolesContra();
            if(diferencia1 > diferencia2){
                equipoB2 = grupoBorden.get(1);
            }else{equipoB2 = grupoBorden.get(2);}
                
            }
        } else if(grupoBorden.get(0).getPuntaje()==grupoBorden.get(1).getPuntaje()){
        Integer diferencia1= grupoBorden.get(0).getGolesFavor() - grupoBorden.get(0).getGolesContra();
            Integer diferencia2= grupoBorden.get(1).getGolesFavor() - grupoBorden.get(1).getGolesContra();
            if(diferencia1 > diferencia2){
                equipoB1 = grupoBorden.get(0);
                equipoB2 = grupoBorden.get(1);
            }else{equipoB1 = grupoBorden.get(1);
                  equipoB2 = grupoBorden.get(0);  }
        }//fin Definicion de ganadores
    System.out.println("ganadores del grupo B: ");
    System.out.println("equipo1:"+equipoB1.getPais()+ " " + equipoB1.toString());
    System.out.println("equipo2:"+equipoB2.getPais()+ " " + equipoB2.toString());
    
    //  GRUPO c:  
    id1= buscarPorTag(idFixture , "9").getIdEquipo();
    id2= buscarPorTag(idFixture , "10").getIdEquipo();
    id3= buscarPorTag(idFixture , "11").getIdEquipo();
    id4= buscarPorTag(idFixture , "12").getIdEquipo();
    System.out.println(id1 + "  " + id2 + "  " + id3+ "  " + id4);
    
    ArrayList<Equipo> grupoCorden = equipoRepositorio.buscarPorGrupo("C", id1, id2, id3, id4);
    Equipo equipoC1 =null;
    Equipo equipoC2 = null;
    
    
    //definir ganadores del grupo C:
    if(grupoCorden.get(0).getPuntaje()>grupoCorden.get(1).getPuntaje()){
        equipoC1 =grupoCorden.get(0);
        if(grupoCorden.get(1).getPuntaje()>grupoCorden.get(2).getPuntaje()){
            equipoC2 =grupoCorden.get(1);
        }else if(grupoCorden.get(1).getPuntaje() == grupoCorden.get(2).getPuntaje()){
            Integer diferencia1= grupoCorden.get(1).getGolesFavor() - grupoCorden.get(1).getGolesContra();
            Integer diferencia2= grupoCorden.get(2).getGolesFavor() - grupoCorden.get(2).getGolesContra();
            if(diferencia1 > diferencia2){
                equipoC2 = grupoCorden.get(1);
            }else{equipoC2 = grupoCorden.get(2);}
                
            }
        } else if(grupoCorden.get(0).getPuntaje()==grupoCorden.get(1).getPuntaje()){
        Integer diferencia1= grupoCorden.get(0).getGolesFavor() - grupoCorden.get(0).getGolesContra();
            Integer diferencia2= grupoCorden.get(1).getGolesFavor() - grupoCorden.get(1).getGolesContra();
            if(diferencia1 > diferencia2){
                equipoC1 = grupoCorden.get(0);
                equipoC2 = grupoCorden.get(1);
            }else{equipoC1 = grupoCorden.get(1);
                  equipoC2 = grupoCorden.get(0);  }
        }//fin Definicion de ganadores
    System.out.println("ganadores del grupo C: ");
    System.out.println("equipo1:"+equipoC1.getPais()+ " " + equipoC1.toString());
    System.out.println("equipo2:"+equipoC2.getPais()+ " " + equipoC2.toString());
    
       //  GRUPO D:  
    id1= buscarPorTag(idFixture , "13").getIdEquipo();
    id2= buscarPorTag(idFixture , "14").getIdEquipo();
    id3= buscarPorTag(idFixture , "15").getIdEquipo();
    id4= buscarPorTag(idFixture , "16").getIdEquipo();
    System.out.println(id1 + "  " + id2 + "  " + id3+ "  " + id4);
    
    ArrayList<Equipo> grupoDorden = equipoRepositorio.buscarPorGrupo("D", id1, id2, id3, id4);
    Equipo equipoD1 =null;
    Equipo equipoD2 = null;
    
    
    //definir ganadores del grupo D:
    if(grupoDorden.get(0).getPuntaje()>grupoDorden.get(1).getPuntaje()){
        equipoD1 =grupoDorden.get(0);
        if(grupoDorden.get(1).getPuntaje()>grupoDorden.get(2).getPuntaje()){
            equipoD2 =grupoDorden.get(1);
        }else if(grupoDorden.get(1).getPuntaje() == grupoDorden.get(2).getPuntaje()){
            Integer diferencia1= grupoDorden.get(1).getGolesFavor() - grupoDorden.get(1).getGolesContra();
            Integer diferencia2= grupoDorden.get(2).getGolesFavor() - grupoDorden.get(2).getGolesContra();
            if(diferencia1 > diferencia2){
                equipoD2 = grupoDorden.get(1);
            }else{equipoD2 = grupoDorden.get(2);}
                
            }
        } else if(grupoDorden.get(0).getPuntaje()==grupoDorden.get(1).getPuntaje()){
        Integer diferencia1= grupoDorden.get(0).getGolesFavor() - grupoDorden.get(0).getGolesContra();
            Integer diferencia2= grupoDorden.get(1).getGolesFavor() - grupoDorden.get(1).getGolesContra();
            if(diferencia1 > diferencia2){
                equipoD1 = grupoDorden.get(0);
                equipoD2 = grupoDorden.get(1);
            }else{equipoD1 = grupoDorden.get(1);
                  equipoD2 = grupoDorden.get(0);  }
        }//fin Definicion de ganadores
    System.out.println("ganadores del grupo D: ");
    System.out.println("equipo1:"+equipoD1.getPais()+ " " + equipoD1.toString());
    System.out.println("equipo2:"+equipoD2.getPais()+ " " + equipoD2.toString());
    //===========================================================================
    //===========================================================================
    //===========================================================================
    

// armo los partidos de cuartos
    
    List<PartidoEliminatorio> listaElim = fixture.getListaPartidosEliminatorio();
    //Cuartos 1:
    PartidoEliminatorio pe1 = partidoEliminatorioServicio.traerPartido(fixture.getId() , "1");
        ListaEquipos eqPartA1= utilidades.traerListaEquipo(equipoA1);
        ListaEquipos eqPartB2= utilidades.traerListaEquipo(equipoB2);
        //El partido 1 lo juega el mejor del A con el segundo del B
        pe1.setEquipo1(eqPartA1);
        pe1.setEquipo2(eqPartB2);
        System.out.println(pe1.getEquipo1() + "  " + pe1.getEquipo2());
        partidoEliminatorioRepositorio.save(pe1);
    //Cuartos 2:
    PartidoEliminatorio pe2 = partidoEliminatorioServicio.traerPartido(fixture.getId() , "2");
        ListaEquipos eqPartA2= utilidades.traerListaEquipo(equipoA2);
        ListaEquipos eqPartB1= utilidades.traerListaEquipo(equipoB1);
        //El partido 2 lo juega el segundo del A con el primero del B
        pe2.setEquipo1(eqPartA2);
        pe2.setEquipo2(eqPartB1);
        System.out.println(pe1.getEquipo2() + "  " + pe2.getEquipo2());
        partidoEliminatorioRepositorio.save(pe2);

    //Cuartos 3:
    PartidoEliminatorio pe3 = partidoEliminatorioServicio.traerPartido(fixture.getId() , "3");
    ListaEquipos eqPartC1= utilidades.traerListaEquipo(equipoC1);
    ListaEquipos eqPartD2= utilidades.traerListaEquipo(equipoD2);
        //El partido 2 lo juega el segundo del A con el primero del B
        pe3.setEquipo1(eqPartC1);
        pe3.setEquipo2(eqPartD2);
        System.out.println(pe3.getEquipo1() + "  " + pe3.getEquipo2());
        partidoEliminatorioRepositorio.save(pe3);

//Cuartos 4:
    PartidoEliminatorio pe4 = partidoEliminatorioServicio.traerPartido(fixture.getId() , "4");
    ListaEquipos eqPartC2= utilidades.traerListaEquipo(equipoC2);
    ListaEquipos eqPartD1= utilidades.traerListaEquipo(equipoD1);
        //El partido 2 lo juega el segundo del A con el primero del B
        pe4.setEquipo1(eqPartC2);
        pe4.setEquipo2(eqPartD1);
        partidoEliminatorioRepositorio.save(pe4);
    }
    
    
    
    
    
    
      
        
    

    



// =============================================================================
//                  ====    Monitores de data Entry    ====        
// =============================================================================s
    public Boolean faseGruposCompletada(String fixtureId){
    Boolean rta =true;
    Fixture fixture = fixtureRepositorio.findById(fixtureId).get();
    List<PartidoGrupo> lista = fixture.getListaPartidosGrupos();
        for (PartidoGrupo pg : lista) {
            if(pg.getGolesEquipo1() == null || pg.getGolesEquipo2() == null ){
            rta =false;
            break;
            } 
        }
    return rta;}
    
// =============================================================================
//                    =====    UTILIDADES     =====
// =============================================================================    
//             ==== buscar por tagPartido dentro de un fixture    ====        
    private Equipo buscarPorTag(String idFixture, String tag){
    Equipo equipo = null;
    Fixture fixture = fixtureRepositorio.findById(idFixture).get();
    List<Equipo> equipos = fixture.getListaEquipos();
        
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
