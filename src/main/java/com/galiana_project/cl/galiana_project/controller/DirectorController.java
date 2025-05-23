package com.galiana_project.cl.galiana_project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.galiana_project.cl.galiana_project.model.Director;
import com.galiana_project.cl.galiana_project.service.DirectorService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/directores")
public class DirectorController {
    @Autowired
    private DirectorService directorService;

    @GetMapping()
    public ResponseEntity<List<Director>> listarDirectores() {
        List<Director> directores = directorService.findAll();
        if (directores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(directores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> buscarDirectorPorId(@RequestParam Long id) {
        try {
            Director director = directorService.findById(id);
            return ResponseEntity.ok(director);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Director> guardar(@RequestBody Director director) {
        Director directorNuevo = directorService.save(director);
        return ResponseEntity.status(HttpStatus.CREATED).body(directorNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Director> actualizar(@PathVariable Long id, @RequestBody Director director) {
        try {
            Director directorActualizado = directorService.updateDirector(id, director);
            return ResponseEntity.ok(directorActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Director> actualizarParcial(@PathVariable Long id, @RequestBody Director directorParcial) {
        Director directorActualizado = directorService.patchDirector(id, directorParcial);
        if (directorActualizado != null) {
            return ResponseEntity.ok(directorActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            directorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
