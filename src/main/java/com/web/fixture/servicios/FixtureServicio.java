package com.web.fixture.servicios;

import com.web.fixture.entidades.CalendarioEliminatorio;
import com.web.fixture.entidades.CalendarioGrupo;
import com.web.fixture.entidades.Equipo;
import com.web.fixture.entidades.Fixture;
import com.web.fixture.entidades.PartidoEliminatorio;
import com.web.fixture.entidades.PartidoGrupo;
import com.web.fixture.entidades.Usuario;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.CalendarioEliminatorioRepositorio;
import com.web.fixture.repositorios.CalendarioGrupoRepositorio;
import com.web.fixture.repositorios.EquipoRepositorio;
import com.web.fixture.repositorios.FixtureRepositorio;
import com.web.fixture.repositorios.PartidoEliminatorioRepositorio;
import com.web.fixture.repositorios.UsuarioRepositorio;
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

//    @Autowired
//    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private FixtureRepositorio fixtureRepositorio;

    @Autowired
    private PartidoGrupoRepositorio partidoRepositorio;

    @Autowired
    private PartidoEliminatorioRepositorio partidoEliminatorioRepositorio;

    @Autowired
    private EquipoRepositorio equipoRepositorio;
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    //agregados
    @Autowired
    private CalendarioGrupoRepositorio CGRep;
    @Autowired
    private CalendarioEliminatorioRepositorio CERep;

    @Transactional
    public Fixture creaFixture() throws ErrorServicio {

        //traigo todos los datos del calendario y armo la lista de partidos
        List<PartidoGrupo> listaPartidoGrupo = new ArrayList();
        List<PartidoEliminatorio> listaPartidoEliminatorio = new ArrayList();
        List<CalendarioGrupo> listaGrupos = CGRep.findAll();
        List<CalendarioEliminatorio> listaEliminatorio = CERep.findAll();
        
        /* Busco la info de los partidos de grupo*/
        for(CalendarioGrupo partidoG : listaGrupos) {
            PartidoGrupo part = new PartidoGrupo();
            part.setFecha( (Date) partidoG.getFecha());
            part.setEquipo1(partidoG.getEquipo1());
            part.setEquipo2(partidoG.getEquipo2());
            part.setGrupo(partidoG.getGrupo());
            listaPartidoGrupo.add(part);
        }
        
        for (CalendarioGrupo Grupo : listaGrupos) {
            System.out.println(Grupo);
        }
        
        /* Busco la info de los partidos Eliminatorios*/
        for(CalendarioEliminatorio partidoE : listaEliminatorio){
            PartidoEliminatorio part = new PartidoEliminatorio();
            part.setFecha((Date) partidoE.getFecha());
            part.setEquipo1(partidoE.getEquipo1());
            part.setEquipo2(partidoE.getEquipo2());
            part.setFase(partidoE.getFase());
            listaPartidoEliminatorio.add(part);

        }
        Fixture fixture = new Fixture();
        
        fixture.setListaPartidosGrupos(listaPartidoGrupo);
        fixture.setListaPartidosEliminatorio(listaPartidoEliminatorio); 
        
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
    //traer de la base de datos la lista completa de partidos, el usuario modificaria unicamente 
}


