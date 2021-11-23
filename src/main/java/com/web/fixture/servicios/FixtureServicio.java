package com.web.fixture.servicios;

import com.web.fixture.entidades.CalendarioEliminatorio;
import com.web.fixture.entidades.CalendarioGrupo;
import com.web.fixture.entidades.Equipo;
import com.web.fixture.entidades.Fixture;
import com.web.fixture.entidades.ListaEquipos;
import com.web.fixture.entidades.PartidoEliminatorio;
import com.web.fixture.entidades.PartidoGrupo;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.CalendarioEliminatorioRepositorio;
import com.web.fixture.repositorios.CalendarioGrupoRepositorio;
import com.web.fixture.repositorios.EquipoRepositorio;
import com.web.fixture.repositorios.FixtureRepositorio;
import com.web.fixture.repositorios.ListaEquipoRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class FixtureServicio {

    @Autowired
    private CalendarioEliminatorioRepositorio calendarioEliminatorioRep;
    @Autowired
    private CalendarioGrupoRepositorio calendarioGrupoRep;
    @Autowired
    private FixtureRepositorio fixtureRepositorio;
    @Autowired
    private EquipoServicio equipoServicio;
    @Autowired
    private EquipoRepositorio equipoRepositorio;
    @Autowired
    private ListaEquipoRepositorio listaEquiposRepositorio;

    @Transactional
    public Fixture creaFixture(String idUsuario) throws ErrorServicio {

        //traigo todos los datos del calendario para rellenar los datos fijos de
        // los partidos y los equipos

        List<CalendarioGrupo> listaGrupo = calendarioGrupoRep.findAll();
        List<CalendarioEliminatorio> listaEliminatorio = calendarioEliminatorioRep.findAll();
        List<ListaEquipos> listaEquipos = listaEquiposRepositorio.findAll();
        Fixture fixture = new Fixture();
        //
        for (ListaEquipos listaEquipo : listaEquipos) {
            listaEquipo.toString();    
        }
        //
        //En estas listas guardo los datos
        List<PartidoGrupo> listaPartidoGrupo = new ArrayList();
        List<PartidoEliminatorio> listapartidoEliminatorio = new ArrayList();
        List<Equipo> equiposfixture = new ArrayList();
        
        //creacion de listas de equipos
        
        for (ListaEquipos item : listaEquipos) {
            System.out.println(item.toString());
            Equipo equipo = new Equipo();
           
            equipo.setPais(item.getPais());
            equipo.setGrupo(item.getGrupo());
            equipo.setGolesContra(0);
            equipo.setGolesFavor(0);
            equipo.setPuntaje(0);
            Integer idItem = item.getIdEquipo();
            System.out.println(idItem);
            equipo.setNumeroEquipo(idItem);
            equiposfixture.add(equipo);
            equipoRepositorio.save(equipo);
            System.out.println(equipo.toString());
        }
        
        fixture.setListaEquipos(equiposfixture);
        
        
  // armo los partidos de grupo
        for (CalendarioGrupo cGrupo : listaGrupo ) {
            PartidoGrupo pg = new PartidoGrupo();
            pg.setFecha((Date) cGrupo.getFecha());
            pg.setTag(cGrupo.getIdPartGrupo().toString());
            pg.setGrupo(cGrupo.getGrupo());
            //armo los equipos a partir de ListaEquipos
            pg.setEquipo1(cGrupo.getEquipo1());
            pg.setEquipo2(cGrupo.getEquipo2());
            listaPartidoGrupo.add(pg);
            }

        //  armo los partidos eliminatorios   
        for (CalendarioEliminatorio cEliminatorio : listaEliminatorio) {
            PartidoEliminatorio pe = new PartidoEliminatorio();
            pe.setFecha((Date) cEliminatorio.getFecha());
            pe.setLetraID(cEliminatorio.getIdPartido().toString());
            pe.setFase(cEliminatorio.getFase());
            listapartidoEliminatorio.add(pe);
        }

        fixture.setListaPartidosGrupos(listaPartidoGrupo);
        fixture.setListaPartidosEliminatorio(listapartidoEliminatorio); //Al tener dos entidades diferentes hay que setear 
        return fixture;
    }

    public Equipo encontrarGanador() throws ErrorServicio {

        Optional<Equipo> rta = equipoRepositorio.findById(equipoRepositorio.getIdGanador());

        if (rta.isPresent()) {
            Equipo equipo = rta.get();
            return equipo;
            
        } else {
            throw new ErrorServicio("No hay un equipo ganador todavía. Cargue los resultados de los partidos anteriores.");

        }

    }
    public void modificarFixture(Integer idPartido, Integer golesEquipo1, Integer golesEquipo2) throws ErrorServicio {
        
        validar(golesEquipo1, golesEquipo2, idPartido);
        
        /*
        Optional<Partido> demo = partidoRepositorio.findById(idPartido);
        
            if (demo.isPresent()) {
            Partido partido = demo.get();
        boolean partidosLlenos= true;
                if (partido.getFase.equals("Grupos") ) {
        //VALIDAR UN DATO PARA SABER SI EL RESTO ESTÁ COMPLETO FORE
        //List<partidos> listagrupos = partidoRepositorio.buscarPorFase("Grupos")
     
            for (listagrupos aux: Partido) {
                if (aux.getGolesEquipo1 == null || aux.getGolesEquipo2 == null){
                     partidosLlenos == false
                     break;
                } 
            } if (partidosLlenos= true) {
                
        
            }
        
            } else {
        
         throw new ErrorServicio("El partido no se encuentra en la base de datos.");
        }
        
        */
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
     
//   ====    Buscar un equipo dentro de la lista de equipos del fixture    =====
     
     public Equipo buscarEquipoPorNumeroEquipo(Integer numeroEquipo, Fixture fixture){
         List<Equipo> lista = fixture.getListaEquipos();
         Equipo equipo = null;
         for (Equipo item : lista) {
             if(item.getNumeroEquipo() == numeroEquipo){
                 equipo = item;
                 break;
             }  
         }
        return equipo;
     }
}


