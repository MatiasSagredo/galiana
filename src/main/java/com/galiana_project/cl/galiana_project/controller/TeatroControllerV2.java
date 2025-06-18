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
import com.galiana_project.cl.galiana_project.assemblers.TeatroModelAssembler;
import com.galiana_project.cl.galiana_project.model.Teatro;
import com.galiana_project.cl.galiana_project.service.TeatroService;

@RestController
@RequestMapping("/api/v2/teatros")
public class TeatroControllerV2 {

    @Autowired
    private TeatroService teatroService;

    @Autowired
    private TeatroModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Teatro>> getAllTeatros() {
        List<EntityModel<Teatro>> teatros = teatroService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(teatros,
                linkTo(methodOn(TeatroControllerV2.class).getAllTeatros()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Teatro> getTeatroById(@PathVariable Long id) {
        Teatro teatro = teatroService.findById(id);
        return assembler.toModel(teatro);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Teatro>> createTeatro(@RequestBody Teatro teatro) {
        Teatro newTeatro = teatroService.save(teatro);
        return ResponseEntity
                .created(linkTo(methodOn(TeatroControllerV2.class).getTeatroById(newTeatro.getId().longValue())).toUri())
                // .created(linkTo(methodOn(AsientoControllerV2.class).getAsientoById(newAsiento.getId())).toUri())
                .body(assembler.toModel(newTeatro));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Teatro>> updateTeatro(@PathVariable Long id, @RequestBody Teatro teatro) {
        teatro.setId(id.intValue());
        // asiento.setId(id);
        Teatro updatedTeatro = teatroService.save(teatro);
        return ResponseEntity
                .ok(assembler.toModel(updatedTeatro));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Teatro>> patchTeatro(@PathVariable Long id, @RequestBody Teatro teatro) {
        Teatro updatedTeatro = teatroService.patchTeatro(id, teatro);
        if (updatedTeatro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedTeatro));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteTeatro(@PathVariable Long id) {
        teatroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
