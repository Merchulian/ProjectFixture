/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.servicios;

import com.web.fixture.entidades.Equipo;
import org.springframework.stereotype.Service;

@Service
public class Utilidades {

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
