package com.example.decode.controller;

import com.example.decode.model.SequenciaRequest;
import com.example.decode.model.SequenciaResultado;
import com.example.decode.service.SequenciaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SequenciaControllerTest {

    private MockMvc mockMvc;
    private SequenciaService service;
    private SequenciaController controller;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        service = mock(SequenciaService.class); // manual mock
        controller = new SequenciaController(service); // injetando mock manualmente
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testResolverExpandido() throws Exception {
        SequenciaRequest request = new SequenciaRequest(); // construtor sem argumentos
        request.setDados(List.of("2", "4", "6"));           // configura os dados
        SequenciaResultado resultadoSimulado = new SequenciaResultado(List.of("2", "4", "6", "8", "10"));

        when(service.resolverExpandido(request.getDados())).thenReturn(resultadoSimulado);

        mockMvc.perform(post("/api/resolver")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dados[4]").value("10"));

        verify(service, times(1)).resolverExpandido(request.getDados());
    }

    @Test
    void testConsultaHistorico() throws Exception {
        List<String> sequencia = List.of("1", "2", "3", "4", "5");
        when(service.getHistorico()).thenReturn(List.of(new SequenciaResultado(sequencia)));

        mockMvc.perform(get("/api/historico"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].dados[4]").value("5"));

        verify(service, times(1)).getHistorico();
    }
}
