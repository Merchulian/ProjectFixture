
package com.web.fixture.controladores;

import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.PartidoEliminatorioRepositorio;
import com.web.fixture.repositorios.PartidoGrupoRepositorio;
import com.web.fixture.servicios.FixtureServicio;
import com.web.fixture.servicios.PartidoEliminatorioServicio;
import com.web.fixture.servicios.PartidoGrupoServicio;
import com.web.fixture.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/noticias")
public class NoticiasControlador {
    
    @Autowired
    private UsuarioServicio UsuarioServicio;
    
    @Autowired
    private FixtureServicio fixtureServicio;
    
    @Autowired
    private PartidoEliminatorioServicio partidoEliServicio;
    
    @Autowired
    private PartidoGrupoServicio partidoGrupoServicio;
    
    @Autowired
    private PartidoGrupoRepositorio partidoGrupoRepo;
    
    @Autowired
    private PartidoEliminatorioRepositorio partidoEliminatorioRepo;
 
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")
    @GetMapping("/ver")
    public String verNoticias(){
    
    return "noticias.html";
    }
}
