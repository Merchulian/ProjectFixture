/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.servicios;

import com.web.fixture.entidades.Equipo;
import com.web.fixture.entidades.Fixture;
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

    
}