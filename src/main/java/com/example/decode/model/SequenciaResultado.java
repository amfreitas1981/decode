package com.example.decode.model;

import java.util.List;

public class SequenciaResultado {
    private List<String> dados;

    public SequenciaResultado(List<String> dados) {
        this.dados = dados;
    }

    public List<String> getDados() { return dados; }
    public void setDados(List<String> dados) { this.dados = dados; }
}
