package com.galiana_project.cl.galiana_project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.galiana_project.cl.galiana_project.model.Comuna;
import com.galiana_project.cl.galiana_project.service.ComunaService;

@RestController
@RequestMapping("/api/v1/comunas")
public class ComunaController {
    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<Comuna>> listarComunas() {
        List<Comuna> comunas = comunaService.findAll();
        if (comunas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comunas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comuna> buscarComunaPorId(@PathVariable Long id) {
        try {
            Comuna comuna = comunaService.findById(id);
            return ResponseEntity.ok(comuna);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
