
package com.web.fixture.repositorios;

import com.web.fixture.entidades.PartidoGrupo;
import com.web.fixture.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PartidoGrupoRepositorio extends  JpaRepository<PartidoGrupo, Integer>{
    

    
}
