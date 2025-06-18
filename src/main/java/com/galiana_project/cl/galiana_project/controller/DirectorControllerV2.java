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

import com.galiana_project.cl.galiana_project.assemblers.DirectorModelAssembler;
import com.galiana_project.cl.galiana_project.model.Director;
import com.galiana_project.cl.galiana_project.service.DirectorService;

@RestController
@RequestMapping("/api/v2/directores")
public class DirectorControllerV2 {
    
    @Autowired
    private DirectorService directorService;

    @Autowired
    private DirectorModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Director>> getAllDirectores() {
        List<EntityModel<Director>> directores = directorService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(directores,
                linkTo(methodOn(DirectorControllerV2.class).getAllDirectores()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Director> getDirectorById(@PathVariable Long id) {
        Director director = directorService.findById(id);
        return assembler.toModel(director);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Director>> createDirector(@RequestBody Director director) {
        Director newDirector = directorService.save(director);
        return ResponseEntity
                .created(linkTo(methodOn(DirectorControllerV2.class).getDirectorById(newDirector.getId().longValue())).toUri())
                .body(assembler.toModel(newDirector));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Director>> updateDirector(@PathVariable Long id, @RequestBody Director director) {
        director.setId(id.intValue());
        // asiento.setId(id);
        Director updatedDirector = directorService.save(director);
        return ResponseEntity
                .ok(assembler.toModel(updatedDirector));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Director>> patchDirector(@PathVariable Long id, @RequestBody Director director) {
        Director updatedDirector = directorService.patchDirector(id, director);
        if (updatedDirector == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedDirector));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteDirector(@PathVariable Long id) {
        directorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
