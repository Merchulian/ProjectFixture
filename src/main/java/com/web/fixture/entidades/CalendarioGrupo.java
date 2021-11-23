
package com.web.fixture.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class CalendarioGrupo {

    @Id
    private Integer idPartGrupo;
    
    @ManyToOne
    private ListaEquipos equipo1;
    
    @ManyToOne
    private ListaEquipos equipo2;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    private String grupo;

    private String tag;

// Setters
    public Integer getIdPartGrupo() {
        return idPartGrupo;
    }

    public ListaEquipos getEquipo1() {
        return equipo1;
    }

    public ListaEquipos getEquipo2() {
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

    public void setEquipo1(ListaEquipos equipo1) {
        this.equipo1 = equipo1;
    }

    public void setEquipo2(ListaEquipos equipo2) {
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