
package com.web.fixture.repositorios;

import com.web.fixture.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String>{
    
        @Query("SELECT c FROM Usuario c WHERE c.email = :email")
    public Usuario buscarPorMail(@Param("email") String email);
}
