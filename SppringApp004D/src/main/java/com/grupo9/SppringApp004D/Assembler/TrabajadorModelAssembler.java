package com.grupo9.SppringApp004D.Assembler;

import com.grupo9.SppringApp004D.Controller.TrabajadorController;
import com.grupo9.SppringApp004D.Model.Trabajador;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TrabajadorModelAssembler implements RepresentationModelAssembler<Trabajador, EntityModel<Trabajador>> {
    @Override
    public EntityModel<Trabajador> toModel(Trabajador trabajador) {
        return EntityModel.of(trabajador,
                linkTo(methodOn(TrabajadorController.class).getTrabajador(trabajador.getId())).withSelfRel(),
                linkTo(methodOn(TrabajadorController.class).getAllTrabajadores()).withRel("GET"),
                linkTo(methodOn(TrabajadorController.class).addTrabajador(trabajador)).withRel("POST"),
                linkTo(methodOn(TrabajadorController.class).updateTrabajador(trabajador.getId(), trabajador)).withRel("PUT"),
                linkTo(methodOn(TrabajadorController.class).deleteTrabajador(trabajador.getId())).withRel("DELETE")
                );
    }
}