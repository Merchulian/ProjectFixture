/*
Es una tabla estatica con esta informacionse arma la fase de grupo para cada fixture
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

    /**
     * @return the idPartGrupo
     */
    public Integer getIdPartGrupo() {
        return idPartGrupo;
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
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param idPartGrupo the idPartGrupo to set
     */
    public void setIdPartGrupo(Integer idPartGrupo) {
        this.idPartGrupo = idPartGrupo;
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

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    
    
    
}