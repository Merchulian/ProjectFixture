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

 //Tabla estatica que se arma con el archivo Partidos eliminatorios.sql
 // se ingresa la info de las fechas, se deja el blanco los equipos etc   
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

    public Equipo getEquipo2() {
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

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


}


