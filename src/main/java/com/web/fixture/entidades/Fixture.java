package com.web.fixture.entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Fixture {

  

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToMany
    (cascade = {CascadeType.ALL})
    private List<PartidoGrupo> listaPartidosGrupos;
   @OneToMany
   (cascade = {CascadeType.ALL})
    private List<PartidoEliminatorio> listaPartidosEliminatorio;
    
    //Faltan listas de partidos de fase eliminatoria y faltaría elegir un ganador.
    
    @OneToOne
    private Equipo ganador;










// metodos
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * @return the listaPartidosGrupos
     */
    public List<PartidoGrupo> getListaPartidosGrupos() {
        return listaPartidosGrupos;
    }

    /**
     * @param listaPartidosGrupos the listaPartidosGrupos to set
     */
    public void setListaPartidosGrupos(List<PartidoGrupo> listaPartidosGrupos) {
        this.listaPartidosGrupos = listaPartidosGrupos;
    }

    /**
     * @return the listaPartidosEliminatorio
     */
    public List<PartidoEliminatorio> getListaPartidosEliminatorio() {
        return listaPartidosEliminatorio;
    }

    /**
     * @param listaPartidosEliminatorio the listaPartidosEliminatorio to set
     */
    public void setListaPartidosEliminatorio(List<PartidoEliminatorio> listaPartidosEliminatorio) {
        this.listaPartidosEliminatorio = listaPartidosEliminatorio;
    }

    /**
     * @return the ganador
     */
    public Equipo getGanador() {
        return ganador;
    }

    /**
     * @param ganador the ganador to set
     */
    public void setGanador(Equipo ganador) {
        this.ganador = ganador;
    }

    @Override
    public String toString() {
        return "Fixture{" + "id=" + id + ", listaPartidosGrupos=" + listaPartidosGrupos + ", listaPartidosEliminatorio=" + listaPartidosEliminatorio + ", ganador=" + ganador + '}';
    }

    
}
