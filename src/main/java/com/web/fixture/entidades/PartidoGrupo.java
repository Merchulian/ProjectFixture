package com.web.fixture.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.web.fixture.entidades.Equipo;
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
    private Equipo equipo1;
    
    @ManyToOne
    private Equipo equipo2;
    
    // dijimos que la fecha sería una buena id así que lo cambié y comenté el idPartido
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    private String grupo;

    private Integer golesEquipo1;

    private Integer golesEquipo2;

     

    /**
     * @return the idPartido
     */
    public Integer getIdPartido() {
        return idPartido;
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
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
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
     * @param golesEquipo1 the golesEquipo1 to set
     */
    public void setGolesEquipo1(Integer golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    /**
     * @param golesEquipo2 the golesEquipo2 to set
     */
    public void setGolesEquipo2(Integer golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }


}