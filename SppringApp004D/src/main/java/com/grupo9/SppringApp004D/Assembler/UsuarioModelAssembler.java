package com.grupo9.SppringApp004D.Assembler;

import com.grupo9.SppringApp004D.Controller.UsuarioController;
import com.grupo9.SppringApp004D.Model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).getUsuario(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("GET"),
                linkTo(methodOn(UsuarioController.class).addUsuario(usuario)).withRel("POST"),
                linkTo(methodOn(UsuarioController.class).updateUser(usuario.getId(), usuario)).withRel("PUT"),
                linkTo(methodOn(UsuarioController.class).deleteUsuario(usuario.getId())).withRel("DELETE")
        );
    }
}
