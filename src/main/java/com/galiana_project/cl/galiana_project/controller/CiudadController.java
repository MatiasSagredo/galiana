package com.galiana_project.cl.galiana_project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galiana_project.cl.galiana_project.model.Ciudad;
import com.galiana_project.cl.galiana_project.service.CiudadService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

}
