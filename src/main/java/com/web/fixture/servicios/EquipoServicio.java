/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.servicios;

import com.web.fixture.entidades.Equipo;
import com.web.fixture.repositorios.EquipoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipoServicio {

    @Autowired
    private EquipoRepositorio equipoRep;
    
    public void estadisticaPartido(Equipo equipo1, Equipo equipo2, Integer golesEquipo1, Integer golesEquipo2) {

        equipo1.setGolesContra(++golesEquipo2); // Los goles en contra se deben sumar, luego restaremos en el método
        equipo1.setGolesFavor(++golesEquipo1);
        equipo2.setGolesFavor(++golesEquipo2);
        equipo2.setGolesContra(++golesEquipo1);
        
        //QUIZÁS CONVENGA ELIMINAR ESTE DATO Y LUEGO UTILIZAR LA DIF DE GOLES DIRECTAMENTE EN EL MÉTODO OCTAVOS
//        equipo1.setDiferenciaGoles(equipo1.getGolesFavor()-equipo1.getGolesContra()); 
//        equipo2.setDiferenciaGoles(equipo2.getGolesFavor()-equipo2.getGolesContra());
//        
        
        equipoRep.save(equipo1);
        equipoRep.save(equipo2);

        
      
        
    }

}
