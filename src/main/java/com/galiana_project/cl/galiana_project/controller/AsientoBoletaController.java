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

import com.galiana_project.cl.galiana_project.model.AsientoBoleta;
import com.galiana_project.cl.galiana_project.service.AsientoBoletaService;

@RestController
@RequestMapping("/api/v1/asientoBoleta")
public class AsientoBoletaController {

    @Autowired
    private AsientoBoletaService asientoBoletaService;

    @GetMapping()
    public ResponseEntity<List<AsientoBoleta>> listarAsientosBoleta() {
        List<AsientoBoleta> asientoBoletas = asientoBoletaService.findAll();
        if (asientoBoletas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(asientoBoletas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsientoBoleta> buscarAsientoBoletaPorId(@PathVariable Long id) {
        try {
            AsientoBoleta asientoBoleta = asientoBoletaService.findById(id);
            return ResponseEntity.ok(asientoBoleta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AsientoBoleta> guardar(@RequestBody AsientoBoleta asientoBoleta) {
        AsientoBoleta asientoBoletaNuevo = asientoBoletaService.save(asientoBoleta);
        return ResponseEntity.status(HttpStatus.CREATED).body(asientoBoletaNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsientoBoleta> actualizar(@PathVariable Long id, @RequestBody AsientoBoleta asientoBoleta) {
        try {
            AsientoBoleta asientoBoletaActualizada = asientoBoletaService.updateAsientoBoleta(id, asientoBoleta);
            return ResponseEntity.ok(asientoBoletaActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AsientoBoleta> actualizarParcial(@PathVariable Long id,
            @RequestBody AsientoBoleta asientoBoletaParcial) {
        try {
            AsientoBoleta asientoBoletaActualizada = asientoBoletaService.patchAsientoBoleta(id, asientoBoletaParcial);
            return ResponseEntity.ok(asientoBoletaActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            asientoBoletaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
