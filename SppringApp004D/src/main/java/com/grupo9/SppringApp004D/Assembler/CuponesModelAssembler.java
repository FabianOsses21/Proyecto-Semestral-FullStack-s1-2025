package com.grupo9.SppringApp004D.Assembler;


import com.grupo9.SppringApp004D.Controller.CuponesController;
import com.grupo9.SppringApp004D.Model.Cupones;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CuponesModelAssembler implements RepresentationModelAssembler<Cupones, EntityModel<Cupones>> {

    @Override
    public EntityModel<Cupones> toModel(Cupones cupones) {
        return EntityModel.of(cupones,
                linkTo(methodOn(CuponesController.class).getCupon(cupones.getId())).withSelfRel(),
                linkTo(methodOn(CuponesController.class).getAllCupones()).withRel("GET"),
                linkTo(methodOn(CuponesController.class).addCupon(cupones)).withRel("POST"),
                linkTo(methodOn(CuponesController.class).updateCupon(cupones.getId(), cupones)).withRel("PUT"),
                linkTo(methodOn(CuponesController.class).deleteCupon(cupones.getId())).withRel("DELETE")
        );
    }
}
