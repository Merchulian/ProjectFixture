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

    private String fase;
    // nuevo atributo
    private String tag;

    // para definir el numero de goles
    private Integer golesEquipo1;

    private Integer golesEquipo2;

    private Integer golesComplementario1;
    
    private Integer golesComplementario2;
    
    private Integer penales1;
    
    private Integer penales2;

//  Constructores

    public PartidoEliminatorio() {
    }

    public PartidoEliminatorio(Integer idPartido, Equipo equipo1, Equipo equipo2, Date fecha, Integer golesEquipo1, Integer golesEquipo2, String fase, String tag, Integer golesComplementario1, Integer golesComplementario2, Integer penales1, Integer penales2) {
        this.idPartido = idPartido;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.fecha = fecha;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
        this.fase = fase;
        this.tag = tag;
        this.golesComplementario1 = golesComplementario1;
        this.golesComplementario2 = golesComplementario2;
        this.penales1 = penales1;
        this.penales2 = penales2;
    }

//  Setters

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

    public Integer getGolesEquipo1() {
        return golesEquipo1;
    }

    public Integer getGolesEquipo2() {
        return golesEquipo2;
    }

    public String getFase() {
        return fase;
    }

    public String getTag() {
        return tag;
    }

    public Integer getGolesComplementario1() {
        return golesComplementario1;
    }

    public Integer getGolesComplementario2() {
        return golesComplementario2;
    }

    public Integer getPenales1() {
        return penales1;
    }

    public Integer getPenales2() {
        return penales2;
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

    public void setGolesEquipo1(Integer golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public void setGolesEquipo2(Integer golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setGolesComplementario1(Integer golesComplementario1) {
        this.golesComplementario1 = golesComplementario1;
    }

    public void setGolesComplementario2(Integer golesComplementario2) {
        this.golesComplementario2 = golesComplementario2;
    }

    public void setPenales1(Integer penales1) {
        this.penales1 = penales1;
    }

    public void setPenales2(Integer penales2) {
        this.penales2 = penales2;
    }
    
}
