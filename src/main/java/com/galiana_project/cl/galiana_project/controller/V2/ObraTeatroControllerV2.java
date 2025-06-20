package com.galiana_project.cl.galiana_project.controller.V2;

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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.galiana_project.cl.galiana_project.assemblers.ObraTeatroModelAssembler;
import com.galiana_project.cl.galiana_project.model.ObraTeatro;
import com.galiana_project.cl.galiana_project.service.ObraTeatroService;

@RestController
@RequestMapping("/api/v2/obrasTeatro")
public class ObraTeatroControllerV2 {

    @Autowired
    private ObraTeatroService obraTeatroService;

    @Autowired
    private ObraTeatroModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<ObraTeatro>> getAllObrasTeatro() {
        List<EntityModel<ObraTeatro>> obrasTeatro = obraTeatroService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(obrasTeatro,
                linkTo(methodOn(ObraTeatroControllerV2.class).getAllObrasTeatro()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<ObraTeatro> getObraTeatroById(@PathVariable Long id) {
        ObraTeatro obraTeatro = obraTeatroService.findById(id);
        return assembler.toModel(obraTeatro);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ObraTeatro>> createObraTeatro(@RequestBody ObraTeatro obraTeatro) {
        ObraTeatro newObraTeatro = obraTeatroService.save(obraTeatro);
        return ResponseEntity
                .created(linkTo(
                        methodOn(ObraTeatroControllerV2.class).getObraTeatroById(newObraTeatro.getId().longValue()))
                        .toUri())
                // .created(linkTo(methodOn(AsientoControllerV2.class).getAsientoById(newAsiento.getId())).toUri())
                .body(assembler.toModel(newObraTeatro));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ObraTeatro>> updateObraTeatro(@PathVariable Long id,
            @RequestBody ObraTeatro obraTeatro) {
        obraTeatro.setId(id.intValue());
        // asiento.setId(id);
        ObraTeatro updatedObraTeatro = obraTeatroService.save(obraTeatro);
        return ResponseEntity
                .ok(assembler.toModel(updatedObraTeatro));
    }

    @PatchMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ObraTeatro>> patchObraTeatro(@PathVariable Long id,
            @RequestBody ObraTeatro obraTeatro) {
        ObraTeatro updatedObraTeatro = obraTeatroService.patchObraTeatro(id, obraTeatro);
        if (updatedObraTeatro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(updatedObraTeatro));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteObraTeatro(@PathVariable Long id) {
        obraTeatroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
