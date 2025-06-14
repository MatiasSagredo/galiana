package com.galiana_project.cl.galiana_project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ResponseEntity<Comuna> guardar(@RequestBody Comuna comuna) {
        Comuna comunaNuevo = comunaService.save(comuna);
        return ResponseEntity.status(HttpStatus.CREATED).body(comunaNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comuna> actualizar(@PathVariable Long id, @RequestBody Comuna comuna) {
        try {
            Comuna comunaActualizada = comunaService.updateComuna(id, comuna);
            return ResponseEntity.ok(comunaActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Comuna> actualizarParcial(@PathVariable Long id, @RequestBody Comuna comunaParcial) {
        try {
            Comuna comunaActualizada = comunaService.patchComuna(id, comunaParcial);
            return ResponseEntity.ok(comunaActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            comunaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
