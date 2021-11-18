
package com.web.fixture.repositorios;

import com.web.fixture.entidades.PartidoGrupo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PartidoGrupoRepositorio extends  JpaRepository<PartidoGrupo, Integer>{
    
    @Query(value ="SELECT lista_partidos_grupos_id_partido FROM fixture_lista_partidos_grupos WHERE fixture_id = :id",
            nativeQuery = true)
    public String[] listarPGdelFixture(@Param("id") String idFixture);
    
}
