package com.grupo9.SppringApp004D.Assembler;

import com.grupo9.SppringApp004D.Controller.ReclamoController;
import com.grupo9.SppringApp004D.Model.Reclamo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ReclamoModelAssembler implements RepresentationModelAssembler<Reclamo, EntityModel<Reclamo>> {

    @Override
    public EntityModel<Reclamo> toModel(Reclamo reclamo) {
        return EntityModel.of(reclamo,
                linkTo(methodOn(ReclamoController.class).getReclamo(reclamo.getId())).withSelfRel(),
                linkTo(methodOn(ReclamoController.class).getAllReclamos()).withRel("GET"),
                linkTo(methodOn(ReclamoController.class).addReclamo(reclamo)).withRel("POST"),
                linkTo(methodOn(ReclamoController.class).updateReclamo(reclamo.getId(), reclamo)).withRel("PUT"),
                linkTo(methodOn(ReclamoController.class).deleteReclamo(reclamo.getId())).withRel("DELETE")
        );
    }
}
