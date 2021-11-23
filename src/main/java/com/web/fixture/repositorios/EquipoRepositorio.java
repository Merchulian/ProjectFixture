
package com.web.fixture.repositorios;

import com.web.fixture.entidades.Equipo;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EquipoRepositorio  extends JpaRepository<Equipo, Integer>{
   
//              esta voy a usar
    @Query(value="SELECT * FROM equipo WHERE grupo = :grupo AND id_equipo IN(:id1 ,:id2 , :id3 , :id4) ORDER BY puntaje DESC",
    nativeQuery = true)
    public ArrayList<Equipo> buscarPorGrupo(@Param("grupo") String grupo, @Param("id1")Integer id1,@Param("id2")Integer id2,@Param("id3")Integer id3,@Param("id4")Integer id4 );
    
    
    
    
    
    
    
    @Query("SELECT c.id FROM Equipo c WHERE c.fase = 'ganador' ") //Chequear esta QUERY CUANDO TENGAMOS LAS VISTAS O CARGADA LA BD
    public Integer getIdGanador();
    
        @Query("SELECT c FROM Equipo c WHERE c.fase = :fase")
    public ArrayList<Equipo> buscarPorFase(@Param("fase") String fase);
    
}
