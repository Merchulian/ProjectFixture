
package com.web.fixture.controladores;

import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.PartidoEliminatorioRepositorio;
import com.web.fixture.repositorios.PartidoGrupoRepositorio;
import com.web.fixture.servicios.FixtureServicio;
import com.web.fixture.servicios.PartidoEliminatorioServicio;
import com.web.fixture.servicios.PartidoGrupoServicio;
import com.web.fixture.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
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
    
    @GetMapping("/")
    public String index() {
		
        return "fixture.html";
    }
        
//	
//            	@GetMapping("/login")
//	public String login() {
//		
//		return "login.html";
//	}
//	
//            	@GetMapping("/registro")
//	public String registro() {
//		
//		return "registro.html";
//	}
//	
        
        @GetMapping("/registro")
    public String registro(ModelMap modelo) {

        return "registro.html";
    }

    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, MultipartFile archivo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {

        try {
            UsuarioServicio.registrar(archivo, nombre, apellido, mail, clave1, clave2);
            return "registro.html";
        } catch (ErrorServicio ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("mail", mail);
            modelo.put("clave1", clave1);
            modelo.put("clave2", clave2);
             
            
            System.out.println("//////////////////////////");
            System.out.println("//////////////////////////");
            System.out.println(ex.getMessage());
            System.out.println("//////////////////////////");
            System.out.println("//////////////////////////");
            return "registro.html";
        }
        
    }
    
//    @GetMapping("/fixture")
//    public String fixture() {
//		
//        return "fixture.html";
//    }
  
//    @PostMapping("/guardaDatosFaseGrupos")
//    public String guardaDatosFaseGrupos(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String clave1, @RequestParam String clave2) {
//
//        try {
//            partidoGrupoServicio.definirPartido(Integer.SIZE, Integer.SIZE, Integer.SIZE);
//            
//            
//        } catch (ErrorServicio ex) {
//
//            modelo.put("error", ex.getMessage());
//            modelo.put("nombre", nombre);
//            modelo.put("apellido", apellido);
//            modelo.put("mail", mail);
//            modelo.put("clave1", clave1);
//            modelo.put("clave2", clave2);
//             
//            
//            System.out.println("//////////////////////////");
//            System.out.println("//////////////////////////");
//            System.out.println(ex.getMessage());
//            System.out.println("//////////////////////////");
//            System.out.println("//////////////////////////");
//            return "fixture.html";
//        }
//        return "fixture.html";
//    }
}
