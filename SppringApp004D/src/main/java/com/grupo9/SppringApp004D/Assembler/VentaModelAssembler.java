package com.grupo9.SppringApp004D.Assembler;

import com.grupo9.SppringApp004D.Controller.VentaController;
import com.grupo9.SppringApp004D.Model.Venta;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class VentaModelAssembler implements RepresentationModelAssembler<Venta, EntityModel<Venta>> {

    @Override
    public EntityModel<Venta> toModel(Venta venta) {
        return EntityModel.of(venta,
                linkTo(methodOn(VentaController.class).getVenta(venta.getId())).withSelfRel(),
                linkTo(methodOn(VentaController.class).getAllVentas()).withRel("GET"),
                linkTo(methodOn(VentaController.class).addVenta(venta)).withRel("POST"),
                linkTo(methodOn(VentaController.class).updateVenta(venta.getId(), venta)).withRel("PUT"),
                linkTo(methodOn(VentaController.class).deleteVenta(venta.getId())).withRel("DELETE")
        );
    }

}
