package com.grupo9.SppringApp004D.Assembler;

import com.grupo9.SppringApp004D.Controller.ReseniaController;
import com.grupo9.SppringApp004D.Model.Resenia;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ReseniaModelAssembler implements RepresentationModelAssembler<Resenia, EntityModel<Resenia>> {

    @Override
    public EntityModel<Resenia> toModel(Resenia resenia) {
        return EntityModel.of(resenia,
                linkTo(methodOn(ReseniaController.class).getResenia(resenia.getId())).withSelfRel(),
                linkTo(methodOn(ReseniaController.class).getAllResenias()).withRel("GET"),
                linkTo(methodOn(ReseniaController.class).addResenia(resenia)).withRel("POST"),
                linkTo(methodOn(ReseniaController.class).updateResenia(resenia.getId(), resenia)).withRel("PUT"),
                linkTo(methodOn(ReseniaController.class).deleteResenia(resenia.getId())).withRel("DELETE")
        );
    }
}
