package com.galiana_project.cl.galiana_project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.galiana_project.cl.galiana_project.model.Ciudad;
import com.galiana_project.cl.galiana_project.service.CiudadService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/ciudades")
public class CiudadController {
    @Autowired
    private CiudadService ciudadService;

    @GetMapping()
    public ResponseEntity<List<Ciudad>> listarCiudades() {
        List<Ciudad> ciudades = ciudadService.findAll();
        if (ciudades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ciudades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> buscarCiudadPorId(@PathVariable Long id) {
        try {
            Ciudad ciudad = ciudadService.findById(id);
            return ResponseEntity.ok(ciudad);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Ciudad> guardar(@RequestBody Ciudad ciudad) {
        Ciudad ciudadNuevo = ciudadService.save(ciudad);
        return ResponseEntity.status(HttpStatus.CREATED).body(ciudadNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ciudad> actualizar(@PathVariable Long id, @RequestBody Ciudad ciudad) {
        try {
            Ciudad ciudadActualizada = ciudadService.updateCiudad(id, ciudad);
            return ResponseEntity.ok(ciudadActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ciudad> actualizarParcial(@PathVariable Long id, @RequestBody Ciudad ciudadParcial) {
        try {
            Ciudad ciudadActualizada = ciudadService.patchCiudad(id, ciudadParcial);
            return ResponseEntity.ok(ciudadActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            ciudadService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
