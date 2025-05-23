package com.galiana_project.cl.galiana_project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.galiana_project.cl.galiana_project.model.Asiento;
import com.galiana_project.cl.galiana_project.service.AsientoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/asientos")
public class AsientoController {
    @Autowired
    private AsientoService asientoService;

    @GetMapping()
    public ResponseEntity<List<Asiento>> listarAsientos() {
        List<Asiento> asientos = asientoService.findAll();
        if (asientos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(asientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asiento> buscarAsientoPorId(@PathVariable Long id) {
        try {
            Asiento asiento = asientoService.findById(id);
            return ResponseEntity.ok(asiento);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Asiento> guardar(@RequestBody Asiento asiento) {
        Asiento asientoNuevo = asientoService.save(asiento);
        return ResponseEntity.status(HttpStatus.CREATED).body(asientoNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asiento> actualizar(@PathVariable Long id, @RequestBody Asiento asiento) {
        try {
            Asiento asientoActualizado = asientoService.updateAsiento(id, asiento);
            return ResponseEntity.ok(asientoActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Asiento> actualizarParcial(@PathVariable Long id, @RequestBody Asiento asientoParcial) {
        Asiento asientoActualizado = asientoService.patchAsiento(id, asientoParcial);
        if (asientoActualizado != null) {
            return ResponseEntity.ok(asientoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            asientoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
