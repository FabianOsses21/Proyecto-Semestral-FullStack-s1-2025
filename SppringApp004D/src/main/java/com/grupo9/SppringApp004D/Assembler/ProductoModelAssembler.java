package com.grupo9.SppringApp004D.Assembler;

import com.grupo9.SppringApp004D.Controller.ProductoController;
import com.grupo9.SppringApp004D.Model.Producto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {

    @Override
    public EntityModel<Producto> toModel(Producto producto) {
        return EntityModel.of(producto,
                linkTo(methodOn(ProductoController.class).getProducto(producto.getId())).withSelfRel(),
                linkTo(methodOn(ProductoController.class).getAllProductos()).withRel("GET"),
                linkTo(methodOn(ProductoController.class).addProducto(producto)).withRel("POST"),
                linkTo(methodOn(ProductoController.class).updateProducto(producto.getId(), producto)).withRel("PUT"),
                linkTo(methodOn(ProductoController.class).deleteProducto(producto.getId())).withRel("DELETE")
        );
    }

}
