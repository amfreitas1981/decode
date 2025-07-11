package com.example.decode.controller;

import com.example.decode.model.SequenciaRequest;
import com.example.decode.model.SequenciaResultado;
import com.example.decode.service.SequenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SequenciaController {

    private final SequenciaService service;

    public SequenciaController(SequenciaService service) {
        this.service = service;
    }

    @PostMapping("/resolver")
    public ResponseEntity<SequenciaResultado> resolver(@RequestBody SequenciaRequest request) {
        return ResponseEntity.ok(service.resolverExpandido(request.getDados()));
    }

    @GetMapping("/historico")
    public ResponseEntity<List<SequenciaResultado>> consultarHistorico() {
        return ResponseEntity.ok(service.getHistorico());
    }
}
