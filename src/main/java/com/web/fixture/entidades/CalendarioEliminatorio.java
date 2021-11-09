
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

    @ManyToOne
    private Equipo equipo1;

    @ManyToOne
    private Equipo equipo2;

    
    @Temporal(TemporalType.DATE)
    private Date fecha;

    /**
     * @return the idPartido
     */
    public Integer getIdPartido() {
        return idPartido;
    }

    /**
     * @return the fase
     */
    public String getFase() {
        return fase;
    }

    /**
     * @return the equipo1
     */
    public Equipo getEquipo1() {
        return equipo1;
    }

    /**
     * @return the equipo2
     */
    public Equipo getEquipo2() {
        return equipo2;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param idPartido the idPartido to set
     */
    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
    }

    /**
     * @param fase the fase to set
     */
    public void setFase(String fase) {
        this.fase = fase;
    }

    /**
     * @param equipo1 the equipo1 to set
     */
    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    /**
     * @param equipo2 the equipo2 to set
     */
    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
}


