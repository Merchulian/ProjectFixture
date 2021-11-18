
package com.web.fixture.controladores;

import com.web.fixture.entidades.Usuario;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.servicios.UsuarioServicio;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/usuario")
public class UsuarioControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    //habilitar esto una vez que pueda pasar como parametro el id de la vista inicio
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")
    @GetMapping("/editar-perfil")
    public String editarPerfil( HttpSession session,@RequestParam String id, ModelMap model){
        
        //esto evita que una persona que sabe un id de otra pueda entrar y modificar sus datos, habilitar cuando se habilite el preAuthorize
        Usuario login= (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getIdUsuario().equals(id))  {
             return "redirect:/inicio";
        }
        
        try{
            Usuario usuario = usuarioServicio.buscarPorId(id);
            model.addAttribute("perfil", usuario);
        }catch(ErrorServicio e){
            model.addAttribute("error", e.getMessage());
        }
        
        return "actualizarPerfil.html";
    }
    
    
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")
    @PostMapping("/actualizar-perfil")
    public String registrar(ModelMap modelo,HttpSession session, MultipartFile archivo, @RequestParam String idUsuario,@RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String clave1, @RequestParam String clave2){
        Usuario usuario = new Usuario();
        try{
            
            usuario = usuarioServicio.buscarPorId(idUsuario);
            usuarioServicio.modificar(archivo, idUsuario, nombre, apellido, email, clave1, clave2);
            session.setAttribute("usuariosession", usuario);
            
            //PREGUNTAR POR QUE CON EL INCIO SOLO NO ANDA.
            return "redirect:/inicio";
        }catch(ErrorServicio e){
            modelo.put("error", e.getMessage());
            modelo.put("perfil", usuario);
            return "actualizarPerfil.html";
        }
    }
    
}
