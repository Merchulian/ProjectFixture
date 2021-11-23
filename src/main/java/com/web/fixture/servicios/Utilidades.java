/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.servicios;

import com.web.fixture.entidades.Equipo;
import com.web.fixture.entidades.ListaEquipos;
import com.web.fixture.repositorios.ListaEquipoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Utilidades {
    @Autowired
    private ListaEquipoRepositorio listaEquiposRepositorio;
    
    
    
    public ListaEquipos traerListaEquipo(Equipo equipo){
        Integer tag = equipo.getNumeroEquipo();
        ListaEquipos eq = null;
        List<ListaEquipos> lista = listaEquiposRepositorio.findAll();
        for (ListaEquipos item : lista) {
            if(item.getIdEquipo().equals(tag)){
            eq = item;
            }
            
        }
    return eq;
    }

    public Integer convertirStringenInt(String string) {

        try {
            Integer num = Integer.valueOf(string);

            return num;

        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }

    }

    public String convertirIntEnString(int num) {

        try {
            String str = String.valueOf(num);

            return str;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public Equipo elegirGanador(Equipo equipo1, Equipo equipo2, Integer goles1, Integer goles2) {

        if (goles1 > goles2) {

            return equipo1;
        } else {

            return equipo2;

        }
    }
}
