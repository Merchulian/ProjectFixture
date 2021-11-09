
package com.web.fixture.controladores;

import com.web.fixture.entidades.Usuario;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")
    @GetMapping("/editar-perfil")
    public String editarPerfil(@RequestParam String id, ModelMap model){
        
        try{
            Usuario usuario = usuarioServicio.buscarPorId(id);
            model.addAttribute("perfil", usuario);
        }catch(ErrorServicio e){
            model.addAttribute("error", e.getMessage());
        }
        
        return "perfil.html";
    }
    
}
