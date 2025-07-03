package com.grupo9.SppringApp004D.Assembler;

import com.grupo9.SppringApp004D.Controller.InventarioController;
import com.grupo9.SppringApp004D.Model.Inventario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InventarioModelAssembler implements RepresentationModelAssembler<Inventario, EntityModel<Inventario>> {

    @Override
    public EntityModel<Inventario> toModel(Inventario inventario) {
        return EntityModel.of(inventario,
                linkTo(methodOn(InventarioController.class).getInventario(inventario.getId())).withSelfRel(),
                linkTo(methodOn(InventarioController.class).getAllInventario()).withRel("GET"),
                linkTo(methodOn(InventarioController.class).addInventario(inventario)).withRel("POST"),
                linkTo(methodOn(InventarioController.class).updateInventario(inventario.getId(), inventario)).withRel("PUT"),
                linkTo(methodOn(InventarioController.class).deleteInventario(inventario.getId())).withRel("DELETE")
        );
    }
}
