package com.galiana_project.cl.galiana_project.controller;

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
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.galiana_project.cl.galiana_project.assemblers.SalaModelAssembler;
import com.galiana_project.cl.galiana_project.model.Sala;
import com.galiana_project.cl.galiana_project.service.SalaService;

@RestController
@RequestMapping("/api/v2/salas")
public class SalaControllerV2 {

    @Autowired
    private SalaService salaService;

    @Autowired
    private SalaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Sala>> getAllSalas() {
        List<EntityModel<Sala>> salas = salaService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(salas,
                linkTo(methodOn(SalaControllerV2.class).getAllSalas()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Sala> getSalaById(@PathVariable Long id) {
        Sala sala = salaService.findById(id);
        return assembler.toModel(sala);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Sala>> createSala(@RequestBody Sala sala) {
        Sala newSala = salaService.save(sala);
        return ResponseEntity
                .created(linkTo(methodOn(SalaControllerV2.class).getSalaById(newSala.getId().longValue())).toUri())
                // .created(linkTo(methodOn(AsientoControllerV2.class).getAsientoById(newAsiento.getId())).toUri())
                .body(assembler.toModel(newSala));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Sala>> updateSala(@PathVariable Long id, @RequestBody Sala sala) {
        sala.setId(id.intValue());
        // asiento.setId(id);
        Sala updatedSala = salaService.save(sala);
        return ResponseEntity
                .ok(assembler.toModel(updatedSala));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Sala>> patchSala(@PathVariable Long id, @RequestBody Sala sala) {
        Sala updatedSala = salaService.patchSala(id, sala);
        if (updatedSala == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedSala));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteSala(@PathVariable Long id) {
        salaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
