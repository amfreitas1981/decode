package com.example.decode.service;

import com.example.decode.model.SequenciaResultado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SequenciaServiceTest {

    private SequenciaService service;

    @BeforeEach
    void setUp() {
        service = new SequenciaService();
    }

    @Test
    void testProgressaoAritmetica() {
        List<String> entrada = List.of("10", "15", "20");
        SequenciaResultado resultado = service.resolverExpandido(entrada);

        assertEquals(List.of("10", "15", "20", "25", "30"), resultado.getDados());
    }

    @Test
    void testAlternanciaFixa() {
        List<String> entrada = List.of("9", "4", "10", "4", "11", "4");
        SequenciaResultado resultado = service.resolverExpandido(entrada);

        assertEquals(List.of("9", "4", "10", "4", "11", "4", "12", "4"), resultado.getDados());
    }

    @Test
    void testMultiplicacaoSequencial() {
        List<String> entrada = List.of("2", "4", "12", "48");
        SequenciaResultado resultado = service.resolverExpandido(entrada);

        assertEquals(List.of("2", "4", "12", "48", "240", "1440"), resultado.getDados());
    }

    @Test
    void testSequenciaLetras() {
        List<String> entrada = List.of("A", "C", "E");
        SequenciaResultado resultado = service.resolverExpandido(entrada);

        assertEquals(List.of("A", "C", "E", "G", "I"), resultado.getDados());
    }

    @Test
    void testSequenciaDesconhecida() {
        List<String> entrada = List.of("10", "8", "15", "9", "27");
        SequenciaResultado resultado = service.resolverExpandido(entrada);

        assertEquals(List.of("10", "8", "15", "9", "27", "?", "?"), resultado.getDados());
    }

    @Test
    void testHistoricoRegistrado() {
        List<String> entrada1 = List.of("1", "2", "3");
        List<String> entrada2 = List.of("A", "B", "C");

        service.resolverExpandido(entrada1);
        service.resolverExpandido(entrada2);

        List<SequenciaResultado> historico = service.getHistorico();

        assertEquals(2, historico.size());
        assertEquals(List.of("1", "2", "3", "4", "5"), historico.get(0).getDados());
        assertEquals(List.of("A", "B", "C", "D", "E"), historico.get(1).getDados());
    }
}
