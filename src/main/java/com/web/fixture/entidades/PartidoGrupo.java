package com.web.fixture.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.web.fixture.entidades.Equipo;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity
public class PartidoGrupo {

    //saque el generate value para ver si era el error
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //este id es para determinar el partido de manera unica: un id distinto
    //para cada fixture, para cad usuario
    private Integer idPartido;
    
    @ManyToOne
    private Equipo equipo1;
    
    @ManyToOne
    private Equipo equipo2;
    
    // cambiado el tipo a DATE
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    private String grupo;
    // nuevo tag
    private String tag;

    // resultado partido
    private Integer golesEquipo1;

    private Integer golesEquipo2;


//  Constructores

    public PartidoGrupo() {
    }

    public PartidoGrupo(Integer idPartido, Equipo equipo1, Equipo equipo2, Date fecha, String grupo, String tag, Integer golesEquipo1, Integer golesEquipo2) {
        this.idPartido = idPartido;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.fecha = fecha;
        this.grupo = grupo;
        this.tag = tag;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }
// Getters

    public Integer getIdPartido() {
        return idPartido;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getTag() {
        return tag;
    }

    public Integer getGolesEquipo1() {
        return golesEquipo1;
    }

    public Integer getGolesEquipo2() {
        return golesEquipo2;
    }

//  Setters

    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setGolesEquipo1(Integer golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public void setGolesEquipo2(Integer golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

}