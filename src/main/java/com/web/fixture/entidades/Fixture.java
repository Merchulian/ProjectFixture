package com.web.fixture.entidades;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Fixture{

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
   
   @OneToMany
   (cascade = {CascadeType.ALL})
   private List<Equipo> listaEquipos;
    
    //Faltan listas de partidos de fase eliminatoria y faltaría elegir un ganador.
    
    @OneToOne
    private Equipo ganador;
    
    
//   ===========================================================================
//Constructores
    public Fixture() {
    }
    
//  Getters
    public String getId() {
        return id;
    }

    public List<PartidoGrupo> getListaPartidosGrupos() {
        return listaPartidosGrupos;
    }

    public List<PartidoEliminatorio> getListaPartidosEliminatorio() {
        return listaPartidosEliminatorio;
    }

    public List<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public Equipo getGanador() {
        return ganador;
    }

//  Setters    
    public void setId(String id) {
        this.id = id;
    }

    public void setListaPartidosGrupos(List<PartidoGrupo> listaPartidosGrupos) {
        this.listaPartidosGrupos = listaPartidosGrupos;
    }

    public void setListaPartidosEliminatorio(List<PartidoEliminatorio> listaPartidosEliminatorio) {
        this.listaPartidosEliminatorio = listaPartidosEliminatorio;
    }

    public void setListaEquipos(List<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    public void setGanador(Equipo ganador) {
        this.ganador = ganador;
    }
   
}
