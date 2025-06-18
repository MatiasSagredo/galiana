package com.galiana_project.cl.galiana_project.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.galiana_project.cl.galiana_project.assemblers.AsientoModelAssembler;
import com.galiana_project.cl.galiana_project.model.Asiento;
import com.galiana_project.cl.galiana_project.service.AsientoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.hateoas.MediaTypes;

@RestController
@RequestMapping("/api/v2/asientos")
public class AsientoControllerV2 {

    @Autowired
    private AsientoService asientoService;
    
    @Autowired
    private AsientoModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Asiento>> getAllAsientos() {
        List<EntityModel<Asiento>> asientos = asientoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(asientos,
                linkTo(methodOn(AsientoControllerV2.class).getAllAsientos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Asiento> getAsientoById(@PathVariable Long id) {
        Asiento asiento = asientoService.findById(id);
        return assembler.toModel(asiento);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Asiento>> createAsiento(@RequestBody Asiento asiento) {
        Asiento newAsiento = asientoService.save(asiento);
        return ResponseEntity
                .created(linkTo(methodOn(AsientoControllerV2.class).getAsientoById(newAsiento.getId().longValue())).toUri())
                // .created(linkTo(methodOn(AsientoControllerV2.class).getAsientoById(newAsiento.getId())).toUri())
                .body(assembler.toModel(newAsiento));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Asiento>> updateAsiento(@PathVariable Long id, @RequestBody Asiento asiento) {
        asiento.setId(id.intValue());
        // asiento.setId(id);
        Asiento updatedAsiento = asientoService.save(asiento);
        return ResponseEntity
                .ok(assembler.toModel(updatedAsiento));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Asiento>> patchAsiento(@PathVariable Long id, @RequestBody Asiento asiento) {
        Asiento updatedAsiento = asientoService.patchAsiento(id, asiento);
        if (updatedAsiento == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedAsiento));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteAsiento(@PathVariable Long id) {
        asientoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
