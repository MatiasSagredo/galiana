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

import com.galiana_project.cl.galiana_project.model.ObraSala;
import com.galiana_project.cl.galiana_project.service.ObraSalaService;

@RestController
@RequestMapping("/api/v1/obraSala")
public class ObraSalaController {

    @Autowired
    private ObraSalaService obraSalaService;

    @GetMapping()
    public ResponseEntity<List<ObraSala>> listarObrasSala() {
        List<ObraSala> obraSalas = obraSalaService.findAll();
        if (obraSalas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(obraSalas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObraSala> buscarObraSalaPorId(@PathVariable Long id) {
        try {
            ObraSala obraSala = obraSalaService.findById(id);
            return ResponseEntity.ok(obraSala);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ObraSala> guardar(@RequestBody ObraSala obraSala) {
        ObraSala obraSalaNuevo = obraSalaService.save(obraSala);
        return ResponseEntity.status(HttpStatus.CREATED).body(obraSalaNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObraSala> actualizar(@PathVariable Long id, @RequestBody ObraSala obraSala) {
        try {
            ObraSala obraSalaActualizada = obraSalaService.updateObraSala(id, obraSala);
            return ResponseEntity.ok(obraSalaActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ObraSala> actualizarParcial(@PathVariable Long id, @RequestBody ObraSala obraSalaParcial) {
        try {
            ObraSala obraSalaActualizada = obraSalaService.patchObraSala(id, obraSalaParcial);
            return ResponseEntity.ok(obraSalaActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            obraSalaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
