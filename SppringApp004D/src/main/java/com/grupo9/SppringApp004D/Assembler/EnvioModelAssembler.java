package com.grupo9.SppringApp004D.Assembler;

import com.grupo9.SppringApp004D.Controller.EnvioController;
import com.grupo9.SppringApp004D.Model.Envio;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EnvioModelAssembler implements RepresentationModelAssembler<Envio, EntityModel<Envio>> {

    @Override
    public EntityModel<Envio> toModel(Envio envio) {
        return EntityModel.of(envio,
                linkTo(methodOn(EnvioController.class).getEnvio(envio.getId())).withSelfRel(),
                linkTo(methodOn(EnvioController.class).getAllEnvios()).withRel("GET"),
                linkTo(methodOn(EnvioController.class).addEnvio(envio)).withRel("POST"),
                linkTo(methodOn(EnvioController.class).updateEnvio(envio.getId(), envio)).withRel("PUT"),
                linkTo(methodOn(EnvioController.class).deleteEnvio(envio.getId())).withRel("DELETE")
        );
    }
}
