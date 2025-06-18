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

import com.galiana_project.cl.galiana_project.assemblers.ObraModelAssembler;

import com.galiana_project.cl.galiana_project.model.Obra;
import com.galiana_project.cl.galiana_project.service.ObraService;

@RestController
@RequestMapping("/api/v2/obras")
public class ObraControllerV2 {

    @Autowired
    private ObraService obraService;

    @Autowired
    private ObraModelAssembler assembler;

     @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Obra>> getAllObras() {
        List<EntityModel<Obra>> obras = obraService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(obras,
                linkTo(methodOn(ObraControllerV2.class).getAllObras()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Obra> getObraById(@PathVariable Long id) {
        Obra obra = obraService.findById(id);
        return assembler.toModel(obra);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Obra>> createObra(@RequestBody Obra obra) {
        Obra newObra = obraService.save(obra);
        return ResponseEntity
                .created(linkTo(methodOn(ObraControllerV2.class).getObraById(newObra.getId().longValue())).toUri())
                // .created(linkTo(methodOn(AsientoControllerV2.class).getAsientoById(newAsiento.getId())).toUri())
                .body(assembler.toModel(newObra));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Obra>> updateObra(@PathVariable Long id, @RequestBody Obra obra) {
        obra.setId(id.intValue());
        // asiento.setId(id);
        Obra updatedObra = obraService.save(obra);
        return ResponseEntity
                .ok(assembler.toModel(updatedObra));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Obra>> patchObra(@PathVariable Long id, @RequestBody Obra obra) {
        Obra updatedObra = obraService.patchObra(id, obra);
        if (updatedObra == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedObra));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteObra(@PathVariable Long id) {
        obraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
