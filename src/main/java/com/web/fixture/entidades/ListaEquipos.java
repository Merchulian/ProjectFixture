/*tabla con informacion constante, sirve para armar los equipos de cada usuario*/
package com.web.fixture.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ListaEquipos {


    @Id
    private Integer idEquipo;

    private String pais;

    private String grupo;

    
    
    // Getters
    public Integer getIdEquipo() {
        return idEquipo;
    }

    public String getPais() {
        return pais;
    }

    public String getGrupo() {
        return grupo;
    }
    
    
//  Setters
    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "ListaEquipos{" + "idEquipo=" + idEquipo + ", pais=" + pais + ", grupo=" + grupo + '}';
    }

}
