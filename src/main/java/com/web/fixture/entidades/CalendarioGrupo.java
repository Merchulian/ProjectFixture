/*
El nuevo atributo se llama tag
 */

package com.web.fixture.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CalendarioGrupo {
    //Estos datos se guardan con el archivo SQL Partidos Fase grupos.sql
    // a partir de esta tabla y la de fase eliminatorias se arma cada partido del
    // nmundial para cada fixture
    @Id
    private Integer idPartGrupo;
    
    @ManyToOne
    private Equipo equipo1;
    
    @ManyToOne
    private Equipo equipo2;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    private String grupo;

    private String tag;
    
//  Constructores

    public CalendarioGrupo() {
    }

    public CalendarioGrupo(Integer idPartGrupo, Equipo equipo1, Equipo equipo2, Date fecha, String grupo, String tag) {
        this.idPartGrupo = idPartGrupo;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.fecha = fecha;
        this.grupo = grupo;
        this.tag = tag;
    }

    public Integer getIdPartGrupo() {
        return idPartGrupo;
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

//  Setters    
    
    public void setIdPartGrupo(Integer idPartGrupo) {
        this.idPartGrupo = idPartGrupo;
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

}