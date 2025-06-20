package com.galiana_project.cl.galiana_project.controller.V2;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galiana_project.cl.galiana_project.assemblers.AsientoBoletaModelAssembler;
import com.galiana_project.cl.galiana_project.model.AsientoBoleta;
import com.galiana_project.cl.galiana_project.service.AsientoBoletaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v2/asientosBoleta")
public class AsientoBoletaControllerV2 {

    @Autowired
    private AsientoBoletaService asientoBoletaService;

    @Autowired
    private AsientoBoletaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<AsientoBoleta>> getAllAsientosBoleta() {
        List<EntityModel<AsientoBoleta>> asientosBoleta = asientoBoletaService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(asientosBoleta,
                linkTo(methodOn(AsientoBoletaControllerV2.class).getAllAsientosBoleta()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<AsientoBoleta> getAsientoBoletaById(@PathVariable Long id) {
        AsientoBoleta asientoBoleta = asientoBoletaService.findById(id);
        return assembler.toModel(asientoBoleta);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<AsientoBoleta>> createAsientoBoleta(@RequestBody AsientoBoleta asientoBoleta) {
        AsientoBoleta newAsientoBoleta = asientoBoletaService.save(asientoBoleta);
        return ResponseEntity
                .created(linkTo(methodOn(AsientoBoletaControllerV2.class)
                        .getAsientoBoletaById(newAsientoBoleta.getId().longValue())).toUri())
                .body(assembler.toModel(newAsientoBoleta));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<AsientoBoleta>> updateAsientoBoleta(@PathVariable Long id,
            @RequestBody AsientoBoleta asientoBoleta) {
        asientoBoleta.setId(id.intValue());
        AsientoBoleta updatedAsientoBoleta = asientoBoletaService.save(asientoBoleta);
        return ResponseEntity
                .ok(assembler.toModel(updatedAsientoBoleta));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<AsientoBoleta>> patchAsientoBoleta(@PathVariable Long id,
            @RequestBody AsientoBoleta asientoBoleta) {
        AsientoBoleta updatedAsientoBoleta = asientoBoletaService.patchAsientoBoleta(id, asientoBoleta);
        if (updatedAsientoBoleta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedAsientoBoleta));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteAsientoBoleta(@PathVariable Long id) {
        asientoBoletaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
