package com.galiana_project.cl.galiana_project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galiana_project.cl.galiana_project.model.Boleta;
import com.galiana_project.cl.galiana_project.service.BoletaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/boletas")
public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    @GetMapping
    public ResponseEntity<List<Boleta>> listar() {
        List<Boleta> boletas = boletaService.findAll();
        if (boletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(boletas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleta> buscarPorId(@PathVariable Long id) {
        try {
            Boleta boleta = boletaService.findById(id);
            return ResponseEntity.ok(boleta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Boleta> guardar(@RequestBody Boleta boleta) {
        Boleta boletaNueva = boletaService.save(boleta);
        return ResponseEntity.status(HttpStatus.CREATED).body(boletaNueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boleta> actualizar(@PathVariable Long id, @RequestBody Boleta boleta) {
        try {
            Boleta boletaActualizada = boletaService.updateBoleta(id, boleta);
            return ResponseEntity.ok(boletaActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boleta> actualizarParcial(@PathVariable Long id, @RequestBody Boleta boletaParcial) {
        Boleta boletaActualizada = boletaService.patchBoleta(id, boletaParcial);
        if (boletaActualizada != null) {
            return ResponseEntity.ok(boletaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            boletaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
