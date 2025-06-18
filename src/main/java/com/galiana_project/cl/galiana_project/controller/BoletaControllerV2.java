package com.galiana_project.cl.galiana_project.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import com.galiana_project.cl.galiana_project.assemblers.BoletaModelAssembler;
import com.galiana_project.cl.galiana_project.model.Boleta;
import com.galiana_project.cl.galiana_project.service.BoletaService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v2/boletas")
public class BoletaControllerV2 {

    @Autowired
    private BoletaService boletaService;

    @Autowired
    private BoletaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Boleta>> getAllBoletas() {
        List<EntityModel<Boleta>> boletas = boletaService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(boletas,
                linkTo(methodOn(BoletaControllerV2.class).getAllBoletas()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Boleta> getBoletaById(@PathVariable Long id) {
        Boleta boleta = boletaService.findById(id);
        return assembler.toModel(boleta);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Boleta>> createBoleta(@RequestBody Boleta boleta) {
        Boleta newBoleta = boletaService.save(boleta);
        return ResponseEntity
                .created(linkTo(methodOn(BoletaControllerV2.class).getBoletaById(newBoleta.getId().longValue())).toUri())
                .body(assembler.toModel(newBoleta));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Boleta>> updateBoleta(@PathVariable Long id, @RequestBody Boleta boleta) {
        boleta.setId(id.intValue());
        Boleta updatedBoleta = boletaService.save(boleta);
        return ResponseEntity
                .ok(assembler.toModel(updatedBoleta));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Boleta>> patchBoleta(@PathVariable Long id, @RequestBody Boleta boleta) {
        Boleta updatedBoleta = boletaService.patchBoleta(id, boleta);
        if (updatedBoleta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedBoleta));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteBoleta(@PathVariable Long id) {
        boletaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
