/*clase que sirve para guardar la info de los equipos, luego cada fizture posee }
una lista propia de equipos con sus puntajes etc*/

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
    
    // Este numero coincide con el id de los equipos listados
    
    private Integer numeroEquipo;
    
    private String fase;
    private String grupo;

//  Constructores

    public Equipo() {
    }

    public Equipo(String pais, String grupo, String fixtureId) {
        this.pais = pais;
        this.grupo = grupo;
    }
    
    
    
    //Getters
    public Integer getIdEquipo() {
        return idEquipo;
    }


    public String getPais() {
        return pais;
    }

    public Date getBaja() {
        return baja;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public Integer getGolesFavor() {
        return golesFavor;
    }

    public Integer getGolesContra() {
        return golesContra;
    }
    
    public Integer getNumeroEquipo(){
        return numeroEquipo;
    }
    
    public String getFase() {
        return fase;
    }

    public String getGrupo() {
        return grupo;
    }

    // Setters
    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setBaja(Date baja) {
        this.baja = baja;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public void setGolesFavor(Integer golesFavor) {
        this.golesFavor = golesFavor;
    }

    public void setGolesContra(Integer golesContra) {
        this.golesContra = golesContra;
    }

    public void setNumeroEquipo(Integer numeroEquipo){
        this.numeroEquipo = numeroEquipo;
    }
    
    public void setFase(String fase) {
        this.fase = fase;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "Equipo{" + "idEquipo=" + idEquipo + ", puntaje=" + puntaje + ", golesFavor=" + golesFavor + ", golesContra=" + golesContra + ", numeroEquipo=" + numeroEquipo + '}';
    }



   
}
