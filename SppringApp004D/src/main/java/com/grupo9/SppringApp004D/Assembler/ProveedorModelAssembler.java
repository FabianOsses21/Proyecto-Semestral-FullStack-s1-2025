package com.grupo9.SppringApp004D.Assembler;

import com.grupo9.SppringApp004D.Controller.ProveedorController;
import com.grupo9.SppringApp004D.Model.Proveedor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<Proveedor, EntityModel<Proveedor>> {

    @Override
    public EntityModel<Proveedor> toModel(Proveedor proveedor) {
        return EntityModel.of(proveedor,
                linkTo(methodOn(ProveedorController.class).getProveedor(proveedor.getId())).withSelfRel(),
                linkTo(methodOn(ProveedorController.class).getAllProveedores()).withRel("GET"),
                linkTo(methodOn(ProveedorController.class).addProveedor(proveedor)).withRel("POST"),
                linkTo(methodOn(ProveedorController.class).updateProveedor(proveedor.getId(), proveedor)).withRel("PUT"),
                linkTo(methodOn(ProveedorController.class).deleteProveedor(proveedor.getId())).withRel("DELETE")
        );
    }
}
