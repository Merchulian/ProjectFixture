package com.web.fixture.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Equipo {


    @Id
    @GeneratedValue
    private Integer idEquipo;

    private String pais;

    @Temporal(TemporalType.DATE)
    private Date baja;

    private Integer puntaje;
    private Integer golesFavor;
    private Integer golesContra;
//    private Integer diferenciaGoles;

    private String fase;
    private String grupo;


    /**
     * @return the idEquipo
     */
    public Integer getIdEquipo() {
        return idEquipo;
    }

    /**
     * @param idEquipo the idEquipo to set
     */
    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the baja
     */
    public Date getBaja() {
        return baja;
    }

    /**
     * @param baja the baja to set
     */
    public void setBaja(Date baja) {
        this.baja = baja;
    }

    /**
     * @return the puntaje
     */
    public Integer getPuntaje() {
        return puntaje;
    }

    /**
     * @param puntaje the puntaje to set
     */
    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * @return the golesFavor
     */
    public Integer getGolesFavor() {
        return golesFavor;
    }

    /**
     * @param golesFavor the golesFavor to set
     */
    public void setGolesFavor(Integer golesFavor) {
        this.golesFavor = golesFavor;
    }

    /**
     * @return the golesContra
     */
    public Integer getGolesContra() {
        return golesContra;
    }

    /**
     * @param golesContra the golesContra to set
     */
    public void setGolesContra(Integer golesContra) {
        this.golesContra = golesContra;
    }

//    /**
//     * @return the diferenciaGoles
//     */
//    public Integer getDiferenciaGoles() {
//        return diferenciaGoles;
//    }
//
//    /**
//     * @param diferenciaGoles the diferenciaGoles to set
//     */
//    public void setDiferenciaGoles(Integer diferenciaGoles) {
//        this.diferenciaGoles = diferenciaGoles;
//    }

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
     * @return the grupo
     */
    public String getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
}
