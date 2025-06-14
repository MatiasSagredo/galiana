package com.galiana_project.cl.galiana_project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.galiana_project.cl.galiana_project.model.Region;
import com.galiana_project.cl.galiana_project.service.RegionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/regiones")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @GetMapping()
    public ResponseEntity<List<Region>> listarRegiones() {
        List<Region> regiones = regionService.findAll();
        if (regiones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(regiones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> buscarRegionPorId(@PathVariable Long id) {
        try {
            Region region = regionService.findById(id);
            return ResponseEntity.ok(region);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Region> guardar(@RequestBody Region region) {
        Region regionNuevo = regionService.save(region);
        return ResponseEntity.status(HttpStatus.CREATED).body(regionNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> actualizar(@PathVariable Long id, @RequestBody Region region) {
        try {
            Region regionActualizada = regionService.updateRegion(id, region);
            return ResponseEntity.ok(regionActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Region> actualizarParcial(@PathVariable Long id, @RequestBody Region region) {
        try {
            Region regionActualizada = regionService.patchRegion(id, region);
            return ResponseEntity.ok(regionActualizada);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            regionService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
