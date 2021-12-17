/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.controladores;

import com.web.fixture.entidades.Usuario;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author mateo
 */
@Controller
public class EstadisticaControlador {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
     
    @PreAuthorize("hasAnyRole('ROLE_USUARIO_AUTORIZADO')")
    @GetMapping("/estadisticas")
	public String estadistica(ModelMap model, HttpSession session,@RequestParam String id) {
		
            //esto evita que una persona que sabe un id de otra pueda entrar y modificar sus datos, habilitar cuando se habilite el preAuthorize
        Usuario login= (Usuario) session.getAttribute("usuariosession");
        if (login == null || !login.getIdUsuario().equals(id))  {
             return "redirect:/inicio";
        }
            
        //faltaria cambiar todo lo  que es el html en teoria, ver
            try{
               Usuario usuario = usuarioServicio.buscarPorId(id);
               model.addAttribute("perfil", usuario);
            }catch(ErrorServicio e){
               model.addAttribute("error", e.getMessage());
            }
            
            return "estadisticas.html";
        }
}
