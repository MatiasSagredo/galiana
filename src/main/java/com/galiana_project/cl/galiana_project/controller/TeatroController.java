package com.galiana_project.cl.galiana_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.galiana_project.cl.galiana_project.model.Teatro;
import com.galiana_project.cl.galiana_project.service.TeatroService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/teatros")
public class TeatroController {
    @Autowired
    private TeatroService teatroService;

    @GetMapping()
    public ResponseEntity<List<Teatro>> listarTeatros() {
        List<Teatro> teatros = teatroService.findAll();
        if (teatros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(teatros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teatro> buscarTeatroPorId(@PathVariable Long id) {
        try {
            Teatro teatro = teatroService.findById(id);
            return ResponseEntity.ok(teatro);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Teatro> guardar(@RequestBody Teatro teatro) {
        Teatro teatroNuevo = teatroService.save(teatro);
        return ResponseEntity.status(HttpStatus.CREATED).body(teatroNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teatro> actualizar(@PathVariable Long id, @RequestBody Teatro teatro) {
        try {
            Teatro teatroActualizado = teatroService.updateTeatro(id, teatro);
            return ResponseEntity.ok(teatroActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Teatro> actualizarParcial(@PathVariable Long id, @RequestBody Teatro teatroParcial) {
        Teatro teatroActualizado = teatroService.patchTeatro(id, teatroParcial);
        if (teatroActualizado != null) {
            return ResponseEntity.ok(teatroActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            teatroService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/comuna/{id}")
    public ResponseEntity<List<Teatro>> listarTeatrosDeComuna(@PathVariable Long id) {
        List<Teatro> teatros = teatroService.findTeatrosFromComuna(id);
        if (teatros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(teatros);
    }
}
