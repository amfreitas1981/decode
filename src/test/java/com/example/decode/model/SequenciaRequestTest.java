package com.example.decode.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SequenciaRequestTest {

    @Test
    void testGetterSetter() {
        SequenciaRequest request = new SequenciaRequest();
        request.setDados(List.of("10", "20", "30"));

        assertEquals(List.of("10", "20", "30"), request.getDados());
    }
}
