package com.example.decode.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SequenciaResultadoTest {

    @Test
    void testGetterSetter() {
        SequenciaResultado resultado = new SequenciaResultado(List.of("A", "B", "C"));
        assertEquals(List.of("A", "B", "C"), resultado.getDados());

        resultado.setDados(List.of("X", "Y"));
        assertEquals(List.of("X", "Y"), resultado.getDados());
    }
}
