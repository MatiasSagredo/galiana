package com.galiana_project.cl.galiana_project.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.galiana_project.cl.galiana_project.model.TipoUsuario;
import com.galiana_project.cl.galiana_project.service.TipoUsuarioService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/tiposUsuario")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @GetMapping
    public ResponseEntity<List<TipoUsuario>> listarTiposUsuario(@PathVariable Long id) {
        List<TipoUsuario> tiposUsuario = tipoUsuarioService.findAll();
        if (tiposUsuario.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tiposUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> buscarTipoUsuarioPorId(@RequestParam Long id) {
        try {
            TipoUsuario tipoUsuario = tipoUsuarioService.findById(id);
            return ResponseEntity.ok(tipoUsuario);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoUsuario> guardar(@RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario tipoUsuarioNuevo = tipoUsuarioService.save(tipoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoUsuarioNuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> actualizar(@PathVariable Long id, @RequestBody TipoUsuario tipoUsuario) {
        try {
            TipoUsuario tipoUsuarioActualizado = tipoUsuarioService.updateTipoUsuario(id, tipoUsuario);
            return ResponseEntity.ok(tipoUsuarioActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TipoUsuario> patchTipoUsuario(@PathVariable Long id, @RequestBody TipoUsuario tipoUsuarioParcial) {
        try {
            TipoUsuario tipoUsuarioActualizado = tipoUsuarioService.patchTipoUsuario(id, tipoUsuarioParcial);
            return ResponseEntity.ok(tipoUsuarioActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            tipoUsuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
