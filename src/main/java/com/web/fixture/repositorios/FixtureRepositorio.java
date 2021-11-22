
package com.web.fixture.repositorios;

import com.web.fixture.entidades.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixtureRepositorio extends  JpaRepository<Fixture, String>{
    
}
