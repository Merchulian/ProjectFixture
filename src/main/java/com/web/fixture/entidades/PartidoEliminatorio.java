/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.fixture.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PartidoEliminatorio {

    //saque el generate value para ver si era el error
    @Id
    @GeneratedValue
    private Integer idPartido;

    @ManyToOne
    private Equipo equipo1;

    @ManyToOne
    private Equipo equipo2;

    
    @Temporal(TemporalType.DATE)
    private Date fecha;

    private Integer golesEquipo1;

    private Integer golesEquipo2;

    private String fase;

    private Integer golesComplementario1;
    private Integer golesComplementario2;
    private Integer penales1;
    private Integer penales2;

    /**
     * @return the idPartido
     */
    public Integer getIdPartido() {
        return idPartido;
    }

    /**
     * @param idPartido the idPartido to set
     */
    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
    }

    /**
     * @return the equipo1
     */
    public Equipo getEquipo1() {
        return equipo1;
    }

    /**
     * @param equipo1 the equipo1 to set
     */
    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    /**
     * @return the equipo2
     */
    public Equipo getEquipo2() {
        return equipo2;
    }

    /**
     * @param equipo2 the equipo2 to set
     */
    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the golesEquipo1
     */
    public Integer getGolesEquipo1() {
        return golesEquipo1;
    }

    /**
     * @param golesEquipo1 the golesEquipo1 to set
     */
    public void setGolesEquipo1(Integer golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    /**
     * @return the golesEquipo2
     */
    public Integer getGolesEquipo2() {
        return golesEquipo2;
    }

    /**
     * @param golesEquipo2 the golesEquipo2 to set
     */
    public void setGolesEquipo2(Integer golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    /**
     * @return the fase
     */
    public String getFase() {
        return fase;
    }

    /**
     * @param fase the fase to set
     */
    public void setFase(String fase) {
        this.fase = fase;
    }

    /**
     * @return the golesComplementario1
     */
    public Integer getGolesComplementario1() {
        return golesComplementario1;
    }

    /**
     * @param golesComplementario1 the golesComplementario1 to set
     */
    public void setGolesComplementario1(Integer golesComplementario1) {
        this.golesComplementario1 = golesComplementario1;
    }

    /**
     * @return the golesComplementario2
     */
    public Integer getGolesComplementario2() {
        return golesComplementario2;
    }

    /**
     * @param golesComplementario2 the golesComplementario2 to set
     */
    public void setGolesComplementario2(Integer golesComplementario2) {
        this.golesComplementario2 = golesComplementario2;
    }

    /**
     * @return the penales1
     */
    public Integer getPenales1() {
        return penales1;
    }

    /**
     * @param penales1 the penales1 to set
     */
    public void setPenales1(Integer penales1) {
        this.penales1 = penales1;
    }

    /**
     * @return the penales2
     */
    public Integer getPenales2() {
        return penales2;
    }

    /**
     * @param penales2 the penales2 to set
     */
    public void setPenales2(Integer penales2) {
        this.penales2 = penales2;
    }

    @Override
    public String toString() {
        return "PartidoEliminatorio{" + "idPartido=" + idPartido + ", equipo1=" + equipo1 + ", equipo2=" + equipo2 + ", fecha=" + fecha + ", golesEquipo1=" + golesEquipo1 + ", golesEquipo2=" + golesEquipo2 + ", fase=" + fase + ", golesComplementario1=" + golesComplementario1 + ", golesComplementario2=" + golesComplementario2 + ", penales1=" + penales1 + ", penales2=" + penales2 + '}';
    }
    
    

}
