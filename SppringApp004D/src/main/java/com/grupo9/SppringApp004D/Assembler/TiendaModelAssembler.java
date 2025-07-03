package com.grupo9.SppringApp004D.Assembler;

import com.grupo9.SppringApp004D.Controller.TiendaController;
import com.grupo9.SppringApp004D.Model.Tienda;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TiendaModelAssembler implements RepresentationModelAssembler<Tienda, EntityModel<Tienda>> {

    @Override
    public EntityModel<Tienda> toModel(Tienda tienda) {
        return EntityModel.of(tienda,
                linkTo(methodOn(TiendaController.class).getTienda(tienda.getId())).withSelfRel(),
                linkTo(methodOn(TiendaController.class).getAllTiendas()).withRel("GET"),
                linkTo(methodOn(TiendaController.class).addTienda(tienda)).withRel("POST"),
                linkTo(methodOn(TiendaController.class).updateTienda(tienda.getId(), tienda)).withRel("PUT"),
                linkTo(methodOn(TiendaController.class).deleteTienda(tienda.getId())).withRel("DELETE")
        );
    }
}
