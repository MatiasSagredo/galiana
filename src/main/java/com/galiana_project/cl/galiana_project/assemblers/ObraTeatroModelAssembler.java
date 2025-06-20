package com.galiana_project.cl.galiana_project.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.galiana_project.cl.galiana_project.controller.V2.ObraTeatroControllerV2;
import com.galiana_project.cl.galiana_project.model.ObraTeatro;

@Component
public class ObraTeatroModelAssembler implements RepresentationModelAssembler<ObraTeatro, EntityModel<ObraTeatro>> {

    @Override
    public EntityModel<ObraTeatro> toModel(ObraTeatro obraTeatro) {
        return EntityModel.of(obraTeatro,
                linkTo(methodOn(ObraTeatroControllerV2.class).getObraTeatroById(obraTeatro.getId().longValue()))
                        .withSelfRel(),
                linkTo(methodOn(ObraTeatroControllerV2.class).getAllObrasTeatro()).withRel("obraTeatro"),
                linkTo(methodOn(ObraTeatroControllerV2.class).updateObraTeatro(obraTeatro.getId().longValue(),
                        obraTeatro)).withRel("actualizar"),
                linkTo(methodOn(ObraTeatroControllerV2.class).deleteObraTeatro(obraTeatro.getId().longValue()))
                        .withRel("eliminar"),
                linkTo(methodOn(ObraTeatroControllerV2.class).patchObraTeatro(obraTeatro.getId().longValue(),
                        obraTeatro)).withRel("actualizar-parcial"));
    }
}
