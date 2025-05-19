package com.galiana_project.cl.galiana_project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galiana_project.cl.galiana_project.model.Obra;
import com.galiana_project.cl.galiana_project.service.ObraService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/obras")
public class ObraController {
    @Autowired
    private ObraService obraService;

    @GetMapping()
    public ResponseEntity<List<Obra>> listarObras() {
        List<Obra> obras = obraService.findAll();
        if (obras.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(obras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Obra> buscarObraPorId(@PathVariable Long id) {
        try {
            Obra obra = obraService.findById(id);
            return ResponseEntity.ok(obra);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Obra> guardar(@PathVariable Obra obra) {
        Obra obraNuevo = obraService.save(obra);
        return ResponseEntity.status(HttpStatus.CREATED).body(obraNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obra> actualizar(@PathVariable Long id, @RequestBody Obra obra) {
        try {
            Obra obraActualizada = obraService.updateObra(id, obra);
            return ResponseEntity.ok(obraActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Obra> actualizarParcial(@PathVariable Long id, @RequestBody Obra obraParcial) {
        Obra obraActualizada = obraService.patchObra(id, obraParcial);
        if (obraActualizada != null) {
            return ResponseEntity.ok(obraActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            obraService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/director/{id}")
    public ResponseEntity<List<Obra>> listarObrasDeDirector(@PathVariable Long id) {
        List<Obra> obras = obraService.findObrasDelDirectorId(id);
        if (obras.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(obras);
    }
}
