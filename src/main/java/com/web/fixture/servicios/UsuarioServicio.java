/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.servicios;

import com.web.fixture.entidades.Fixture;
import com.web.fixture.entidades.Foto;
import com.web.fixture.entidades.Usuario;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServicio implements UserDetailsService {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private FotoServicio fotoServicio;
    
    @Autowired
    private FixtureServicio fixtureServicio;


    @Transactional
    public void registrar(MultipartFile archivo, String nombre, String apellido, String email, String clave, String clave2) throws ErrorServicio {

        validar(nombre, apellido, email, clave, clave2);
        System.out.println("datos validados");
        Usuario usuario = new Usuario(); //Creamos un objeto usuario
        usuario.setNombre(nombre); // lo llenamos con los datos que nos llega del registro web
        usuario.setApellido(apellido);
        usuario.setEmail(email);

        String encriptada = new BCryptPasswordEncoder().encode(clave);
        usuario.setClave(encriptada);
        System.out.println("clave guardada!!!");
        usuario.setAlta(new Date()); 

        /* Foto foto = fotoServicio.guardar(archivo);
        usuario.setFoto(foto);*/

        //creo y seteo el fixture en usuario y en fixture servicio tmb le enlazo el usuario y queda completa la relacion fixture/usuario
 
       // usuarioRepositorio.save(usuario); //Le decimos al repositorio que lo guarde en la base de datos. El repositorio es el encargado de transformar ese objeto en una o más tablas de la base de datos
    
        usuario.setFixture(fixtureServicio.creaFixture());
        System.out.println("fixture creado");
   /**/ System.out.println(usuario.getFixture().toString());
        usuarioRepositorio.save(usuario);
    }
               
    @Transactional
    public void modificar(MultipartFile archivo, String idUsuario, String nombre, String apellido, String email, String clave1, String clave2, String zona) throws ErrorServicio {

        validar(nombre, apellido, email, clave1, clave2);
        
        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario); //mediante la clase Optional nos fijamos si con el id devuelve un usuario
        
        if (respuesta.isPresent()) { //si respuesta devuelve un usuario entonces modificalo
            Usuario usuario = respuesta.get();
            usuario.setNombre(nombre); // seteamos el nuevo dato
            usuario.setApellido(apellido);
            usuario.setEmail(email);
            String encriptada = new BCryptPasswordEncoder().encode(clave1);
            usuario.setClave(encriptada);

            String idFoto = null;

            if (usuario.getFoto() != null) { // si el usuario tiene una foto asignada 

                idFoto = usuario.getFoto().getId();   // dale a id foto el identificador para que se lo pase al siguiente método

            }
            Foto foto = fotoServicio.actualizar(idFoto, archivo); //Si yo le mando un id nulo igual me va a traer el objeto foto con un id generado automáticamente y el resto de los atributos
            usuario.setFoto(foto);

            usuarioRepositorio.save(usuario); //Le decimos al repositorio que lo guarde en la base de datos. El repositorio es el encargado de transformar ese objeto en una o más tablas de la base de datos

        } else {
            throw new ErrorServicio("El usuario solicitado no se encuentra registrado");
        }
    }

    @Transactional
    public void deshabilitar(String idUsuario) throws ErrorServicio {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario); //mediante la clase Optional nos fijamos si con el id devuelve un usuario
        if (respuesta.isPresent()) { //si respuesta devuelve un usuario entonces modificalo
            Usuario usuario = respuesta.get();
            usuario.setBaja(new Date());

            usuarioRepositorio.save(usuario); //Le decimos al repositorio que lo guarde en la base de datos. El repositorio es el encargado de transformar ese objeto en una o más tablas de la base de datos

        } else {
            throw new ErrorServicio("El usuario solicitado no se encuentra registrado.");
        }
    }

    @Transactional
    public void habilitar(String idUsuario) throws ErrorServicio {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(idUsuario); //mediante la clase Optional nos fijamos si con el id devuelve un usuario
        if (respuesta.isPresent()) { //si respuesta devuelve un usuario entonces modificalo
            Usuario usuario = respuesta.get();
            usuario.setBaja(null); //borramos la fecha de baja

            usuarioRepositorio.save(usuario); //Le decimos al repositorio que lo guarde en la base de datos. El repositorio es el encargado de transformar ese objeto en una o más tablas de la base de datos

        } else {
            throw new ErrorServicio("El usuario solicitado no se encuentra registrado.");
        }
    }
//
    private void validar(String nombre, String apellido, String email, String clave, String clave2) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El campo 'nombre' no puede estar vacío.");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El campo 'apellido' no puede estar vacío.");
        }
        if (email == null || email.isEmpty()) {
            throw new ErrorServicio("El campo 'correo electrónico' no puede estar vacío.");
        }

        if (clave == null || clave.length() < 6) {
            throw new ErrorServicio("La clave debe tener al menos 6 caracteres.");
        }
        if (!clave.equals(clave2)) {
            throw new ErrorServicio(("Las claves no coinciden."));
        }

    }
//
//    //Este método me permite autenticar el usuario de forma segura//
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.buscarPorMail(email); // busco al usuario por mail

        if (usuario != null) { // si existe
            //Esto es lo que le da los permisos al usuario, a que modulos puede acceder
            List<GrantedAuthority> permisos = new ArrayList();  // le crea permisos

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_USUARIO_REGISTRADO");
            permisos.add(p1);

//Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); //Esta llamada va a recuperar los atributos del request
            HttpSession session = attr.getRequest().getSession(true); // una vez que trae la solicitud va a solicitar los datos de sesion de esa solicitud de http
            session.setAttribute("usuariosession", usuario); // en los datos de sesion vioy a guardar un atributo que se va a llamar usuario session. Ese atributo va a guardar el objeto usuario que acabo de ir a buscar a la BD

            User user = new User(usuario.getEmail(), usuario.getClave(), permisos);

            return user;

        } else {
            return null;
        }
    }

    public Usuario buscarPorId(String id) throws ErrorServicio {
        Optional<Usuario> respuesta = usuarioRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            return usuario;
        } else {

            throw new ErrorServicio("No se encuentra el ID del usuario.");
        }
    }


}
