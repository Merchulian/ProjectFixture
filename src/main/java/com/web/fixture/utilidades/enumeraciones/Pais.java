package com.web.fixture.utilidades.enumeraciones;

public enum Pais {

    ARGENTINA("Argentina"), BRASIL("Brasil");
    
    private String pais;

    private Pais(String pais) {
        this.pais = pais;
    }

    public String getPais() {
        return this.pais;
    }
}
