/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.repositorios;

import com.web.fixture.entidades.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mateo
 */

@Repository
public interface FixtureRepositorio extends  JpaRepository<Fixture, String>{
    
}
