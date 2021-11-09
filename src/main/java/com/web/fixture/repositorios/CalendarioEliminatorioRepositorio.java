/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.web.fixture.repositorios;

import com.web.fixture.entidades.CalendarioEliminatorio;
import com.web.fixture.entidades.PartidoEliminatorio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CalendarioEliminatorioRepositorio extends JpaRepository<CalendarioEliminatorio, Integer> {

}
