package com.grupo9.SppringApp004D.Assembler;

import com.grupo9.SppringApp004D.Controller.PedidoController;
import com.grupo9.SppringApp004D.Model.Pedido;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {

    @Override
    public EntityModel<Pedido> toModel(Pedido pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(PedidoController.class).getPedido(pedido.getId())).withSelfRel(),
                linkTo(methodOn(PedidoController.class).getAllPedidos()).withRel("GET"),
                linkTo(methodOn(PedidoController.class).addPedido(pedido)).withRel("POST"),
                linkTo(methodOn(PedidoController.class).updatePedido(pedido.getId(), pedido)).withRel("PUT"),
                linkTo(methodOn(PedidoController.class).deletePedido(pedido.getId())).withRel("DELETE")
        );
    }
}
