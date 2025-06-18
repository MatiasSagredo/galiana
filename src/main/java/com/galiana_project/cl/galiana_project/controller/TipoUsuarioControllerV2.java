package com.galiana_project.cl.galiana_project.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
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

import com.galiana_project.cl.galiana_project.assemblers.TipoUsuarioModelAssembler;
import com.galiana_project.cl.galiana_project.model.TipoUsuario;
import com.galiana_project.cl.galiana_project.service.TipoUsuarioService;


@RestController
@RequestMapping("/api/v2/tiposUsuario")
public class TipoUsuarioControllerV2 {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @Autowired
    private TipoUsuarioModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<TipoUsuario>> getAllTiposUsuario() {
        List<EntityModel<TipoUsuario>> tiposUsuario = tipoUsuarioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(tiposUsuario,
                linkTo(methodOn(TipoUsuarioControllerV2.class).getAllTiposUsuario()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<TipoUsuario> getTipoUsuarioById(@PathVariable Long id) {
        TipoUsuario tipoUsuario = tipoUsuarioService.findById(id);
        return assembler.toModel(tipoUsuario);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<TipoUsuario>> createTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario newTipoUsuario = tipoUsuarioService.save(tipoUsuario);
        return ResponseEntity
                .created(linkTo(methodOn(TipoUsuarioControllerV2.class).getTipoUsuarioById(newTipoUsuario.getId().longValue())).toUri())
                // .created(linkTo(methodOn(AsientoControllerV2.class).getAsientoById(newAsiento.getId())).toUri())
                .body(assembler.toModel(newTipoUsuario));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<TipoUsuario>> updateTipoUsuario(@PathVariable Long id, @RequestBody TipoUsuario tipoUsuario) {
        tipoUsuario.setId(id.intValue());
        // asiento.setId(id);
        TipoUsuario updatedTipoUsuario = tipoUsuarioService.save(tipoUsuario);
        return ResponseEntity
                .ok(assembler.toModel(updatedTipoUsuario));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<TipoUsuario>> patchTipoUsuario(@PathVariable Long id, @RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario updatedTipoUsuario = tipoUsuarioService.patchTipoUsuario(id, tipoUsuario);
        if (updatedTipoUsuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedTipoUsuario));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteTipoUsuario(@PathVariable Long id) {
        tipoUsuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
