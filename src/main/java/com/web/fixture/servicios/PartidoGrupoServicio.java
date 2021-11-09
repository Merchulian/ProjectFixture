/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.servicios;

import com.web.fixture.entidades.PartidoGrupo;
import com.web.fixture.errores.ErrorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import com.web.fixture.repositorios.PartidoGrupoRepositorio;
import java.util.Optional;

/**
 *
 * @author mateo
 */
@Service
public class PartidoGrupoServicio {

    @Autowired
    private PartidoGrupoRepositorio partidoRep;
    @Autowired
    private EquipoServicio equipoServicio;

    public void definirPartido(Integer golesEquipo1, Integer golesEquipo2, Integer idPartido) throws ErrorServicio {

        validar(golesEquipo1, golesEquipo2, idPartido);

        Optional<PartidoGrupo> demo = partidoRep.findById(idPartido);

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
            partidoRep.save(partido);

        } else {
            throw new ErrorServicio("El partido no se encuentra en la base de datos.");
        }

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
