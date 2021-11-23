package com.web.fixture.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    private ListaEquipos equipo1;
    
    @ManyToOne
    private ListaEquipos equipo2;
    
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

// Getters

    /**
     * @return the idPartido
     */
    public Integer getIdPartido() {
        return idPartido;
    }

    /**
     * @return the equipo1
     */
    public ListaEquipos getEquipo1() {
        return equipo1;
    }

    /**
     * @return the equipo2
     */
    public ListaEquipos getEquipo2() {
        return equipo2;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @return the golesEquipo1
     */
    public Integer getGolesEquipo1() {
        return golesEquipo1;
    }

    /**
     * @return the golesEquipo2
     */
    public Integer getGolesEquipo2() {
        return golesEquipo2;
    }

    /**
     * @param idPartido the idPartido to set
     */
    public void setIdPartido(Integer idPartido) {
        this.idPartido = idPartido;
    }

    /**
     * @param equipo1 the equipo1 to set
     */
    public void setEquipo1(ListaEquipos equipo1) {
        this.equipo1 = equipo1;
    }

    /**
     * @param equipo2 the equipo2 to set
     */
    public void setEquipo2(ListaEquipos equipo2) {
        this.equipo2 = equipo2;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setGolesEquipo1(Integer golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    /**
     * @param golesEquipo2 the golesEquipo2 to set
     */
    public void setGolesEquipo2(Integer golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    @Override
    public String toString() {
        return "PartidoGrupo{" + "idPartido=" + idPartido + ", equipo1=" + equipo1 + ", equipo2=" + equipo2 + ", tag=" + tag + ", golesEquipo1=" + golesEquipo1 + ", golesEquipo2=" + golesEquipo2 + '}';
    }
    
    

 
}