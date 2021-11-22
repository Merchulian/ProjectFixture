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
    private Equipo equipo1;
    
    @ManyToOne
    private Equipo equipo2;

    @Temporal(TemporalType.DATE)
    private Date fecha;

//  Constructors

    public CalendarioEliminatorio() {
    }

    public CalendarioEliminatorio(Integer idPartido, String fase, String tag, Equipo equipo1, Equipo equipo2, Date fecha) {
        this.idPartido = idPartido;
        this.fase = fase;
        this.tag = tag;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.fecha = fecha;
    }

// getters
    public Integer getIdPartido() {
        return idPartido;
    }
public String getFase() {
        return fase;
    }

    public String getTag() {
        return tag;
    }

    public Equipo getEquipo1() {
        return equipo1;
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

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }
public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    

}
