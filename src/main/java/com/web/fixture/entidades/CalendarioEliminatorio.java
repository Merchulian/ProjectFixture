/*            El nuevo atributo se llama tag   */
package com.web.fixture.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class CalendarioEliminatorio {
    @Id
    private Integer idPartido;

    private String fase;

    private String tag;

    @ManyToOne
    private ListaEquipos equipo1;
    
    @ManyToOne
    private ListaEquipos equipo2;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    
// Getters
    public Integer getIdPartido() {
        return idPartido;
    }

    public String getFase() {
        return fase;
    }

    public String getTag() {
        return tag;
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

//  Setters
    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

  
}
