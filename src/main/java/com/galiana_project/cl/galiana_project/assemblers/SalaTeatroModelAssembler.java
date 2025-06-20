package com.galiana_project.cl.galiana_project.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.galiana_project.cl.galiana_project.controller.V2.SalaTeatroControllerV2;
import com.galiana_project.cl.galiana_project.model.SalaTeatro;

@Component
public class SalaTeatroModelAssembler implements RepresentationModelAssembler<SalaTeatro, EntityModel<SalaTeatro>> {

    @Override
    public EntityModel<SalaTeatro> toModel(SalaTeatro obraTeatro) {
        return EntityModel.of(obraTeatro,
                linkTo(methodOn(SalaTeatroControllerV2.class).getObraTeatroById(obraTeatro.getId().longValue()))
                        .withSelfRel(),
                linkTo(methodOn(SalaTeatroControllerV2.class).getAllObrasTeatro()).withRel("obraTeatro"),
                linkTo(methodOn(SalaTeatroControllerV2.class).updateObraTeatro(obraTeatro.getId().longValue(),
                        obraTeatro)).withRel("actualizar"),
                linkTo(methodOn(SalaTeatroControllerV2.class).deleteObraTeatro(obraTeatro.getId().longValue()))
                        .withRel("eliminar"),
                linkTo(methodOn(SalaTeatroControllerV2.class).patchObraTeatro(obraTeatro.getId().longValue(),
                        obraTeatro)).withRel("actualizar-parcial"));
    }
}
