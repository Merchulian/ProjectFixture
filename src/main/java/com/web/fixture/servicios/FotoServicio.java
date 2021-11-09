/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.servicios;

import com.web.fixture.entidades.Foto;
import com.web.fixture.errores.ErrorServicio;
import com.web.fixture.repositorios.FotoRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoServicio {
    @Autowired
    private FotoRepositorio fotoRep;
    
    @Transactional        
    public Foto guardar(MultipartFile archivo) throws ErrorServicio {

        if (archivo != null) {
            try {
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRep.save(foto);
            } catch (Exception e) {
                System.out.println("////////////////////////");
                System.out.println("////////////////////////");
                System.err.print(e.getMessage());
                System.out.println("////////////////////////");
                System.out.println("////////////////////////");
            }
        }
        return null;

    }
    
    @Transactional
    public Foto actualizar(String idFoto, MultipartFile archivo) throws ErrorServicio {
        
            if (archivo != null) {
            try {
                Foto foto = new Foto();
                
                if (idFoto != null) {
                    
                    Optional<Foto> rta = fotoRep.findById(idFoto);
                    
                    if (rta.isPresent()) {
                        
                        foto = rta.get();
                    }
                    
                }
                
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRep.save(foto);
            } catch (Exception e) {
                
                System.err.print(e.getMessage());
            }
        }
        
        
        return null;
        
    }
}
