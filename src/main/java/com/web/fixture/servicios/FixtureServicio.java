package com.web.fixture.servicios;

import com.web.fixture.entidades.CalendarioEliminatorio;
import com.web.fixture.entidades.CalendarioGrupo;
import com.web.fixture.entidades.Equipo;
import com.web.fixture.entidades.Fixture;
import com.web.fixture.entidades.PartidoEliminatorio;
import com.web.fixture.entidades.PartidoGrupo;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.CalendarioEliminatorioRepositorio;
import com.web.fixture.repositorios.CalendarioGrupoRepositorio;
import com.web.fixture.repositorios.EquipoRepositorio;
import com.web.fixture.repositorios.FixtureRepositorio;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.fixture.repositorios.PartidoGrupoRepositorio;
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
    private EquipoRepositorio equipoRepositorio;

    @Transactional
    public Fixture creaFixture(String idUsuario) throws ErrorServicio {

        //traigo todos los datos del calendario para crear automaticamente el fixture
        List<CalendarioGrupo> listaGrupo = calendarioGrupoRep.findAll();
        List<CalendarioEliminatorio> listaEliminatorio = calendarioEliminatorioRep.findAll();
        
        // armo una lista para los partidos de grupo y los eliminatorios
        List<PartidoGrupo> listaPartidoGrupo = new ArrayList();
        List<PartidoEliminatorio> listapartidoEliminatorio = new ArrayList();
        for (CalendarioGrupo cGrupo : listaGrupo ) {
            PartidoGrupo pg = new PartidoGrupo();
            pg.setEquipo1(cGrupo.getEquipo1());
            pg.setEquipo2(cGrupo.getEquipo2());
            pg.setFecha((Date) cGrupo.getFecha());
            pg.setTag(cGrupo.getIdPartGrupo().toString());
            pg.setGrupo(cGrupo.getGrupo());
            listaPartidoGrupo.add(pg);
        }
        for (CalendarioEliminatorio cEliminatorio : listaEliminatorio) {
            PartidoEliminatorio pe = new PartidoEliminatorio();
            
            pe.setFecha((Date) cEliminatorio.getFecha());
            pe.setLetraID(cEliminatorio.getIdPartido().toString());
            pe.setFase(cEliminatorio.getFase());
            listapartidoEliminatorio.add(pe);
        }
        
        Fixture fixture = new Fixture();
        fixture.setListaPartidosGrupos(listaPartidoGrupo);
        fixture.setListaPartidosEliminatorio(listapartidoEliminatorio); //Al tener dos entidades diferentes hay que setear 

        fixtureRepositorio.save(fixture);
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
}


