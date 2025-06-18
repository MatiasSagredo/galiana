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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.galiana_project.cl.galiana_project.assemblers.CiudadModelAssembler;
import com.galiana_project.cl.galiana_project.model.Ciudad;
import com.galiana_project.cl.galiana_project.service.CiudadService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v2/ciudades")
public class CiudadControllerV2 {

    @Autowired
    private CiudadService ciudadService;

    @Autowired
    private CiudadModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Ciudad>> getAllCiudades() {
        List<EntityModel<Ciudad>> ciudades = ciudadService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
            return CollectionModel.of(ciudades,
                    linkTo(methodOn(CiudadControllerV2.class).getAllCiudades()).withSelfRel());           
    } 

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Ciudad> getCiudadById(@PathVariable Long id) {
        Ciudad ciudad = ciudadService.findById(id);
        return assembler.toModel(ciudad);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Ciudad>> createCiudad(@RequestBody Ciudad ciudad) {
        Ciudad newCiudad = ciudadService.save(ciudad);
        return ResponseEntity
                .created(linkTo(methodOn(CiudadControllerV2.class).getCiudadById(newCiudad.getId().longValue())).toUri())
                .body(assembler.toModel(newCiudad));
    }   

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Ciudad>> updateCiudad(@PathVariable Long id, @RequestBody Ciudad ciudad) {
        ciudad.setId(id.intValue());
        // asiento.setId(id);
        Ciudad updatedCiudad = ciudadService.save(ciudad);
        return ResponseEntity
                .ok(assembler.toModel(updatedCiudad));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Ciudad>> patchCiudad(@PathVariable Long id, @RequestBody Ciudad ciudad) {
        Ciudad updatedCiudad = ciudadService.patchCiudad(id, ciudad);
        if (updatedCiudad == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedCiudad));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCiudad(@PathVariable Long id) {
        ciudadService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
