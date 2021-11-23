package com.web.fixture.entidades;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idUsuario;

    private String nombre;

    private String apellido;

    private String email;
    
    private String clave;
    
    @Temporal(TemporalType.DATE)
    private Date alta;
    
    @Temporal(TemporalType.DATE)
    private Date baja;

    @OneToOne(cascade = {CascadeType.ALL})
    private Fixture fixture;
   
    @OneToOne
    private Foto foto;


//  Getters
    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getClave() {
        return clave;
    }

    public Date getAlta() {
        return alta;
    }

    public Date getBaja() {
        return baja;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public Foto getFoto() {
        return foto;
    }
//  Setters
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public void setBaja(Date baja) {
        this.baja = baja;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }
    
    // TO String
    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + getIdUsuario() + ", nombre=" + getNombre() + ", apellido=" + getApellido() + ", email=" + getEmail() + ", alta=" + getAlta() + ", baja=" + getBaja() + ", fixture=" + getFixture() + '}';
    }

}
