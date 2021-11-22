/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.repositorios;

import com.web.fixture.entidades.PartidoEliminatorio;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidoEliminatorioRepositorio extends JpaRepository<PartidoEliminatorio, Integer>{
        
    @Query("SELECT c FROM PartidoEliminatorio c WHERE c.fase = :fase ORDER BY c.idPartido asc")
    public ArrayList<PartidoEliminatorio> buscarPartidosxFase(@Param("fase") String fase);
    
    @Query("SELECT c FROM PartidoEliminatorio c WHERE c.letraID LIKE %:letraID% ORDER BY c.letraID ASC")
    public ArrayList<PartidoEliminatorio> buscarNextPartidoXletraID(@Param("letraID") String letraID);
    
//   @Query("SELECT c FROM PartidoEliminatorio c WHERE c.letraID LIKE %:letraID% AND LENGTH(c.letraID)= aux")
//    public PartidoEliminatorio buscarNextPartidoXletraID(@Param("letraID") String letraID);

}
