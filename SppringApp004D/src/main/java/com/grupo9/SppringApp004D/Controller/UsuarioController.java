package com.grupo9.SppringApp004D.Controller;


import com.grupo9.SppringApp004D.Model.Usuario;
import com.grupo9.SppringApp004D.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@Tag(name="Controlador Usuarios",description = "Servicio Rest Para la gestion de Usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Obtener Usuarios",description = "Obtiene la lista completa de usuarios registrados en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de usuarios"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        List<Usuario> lista = usuarioService.getAllUsuarios();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Usuario por ID",description = "Segun un ID entregado, obtiene al usuario registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Usuario"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del usuario", example = "123")
    public ResponseEntity<Usuario> getUsuario(@PathVariable int id){
        Usuario us = usuarioService.getUsuario(id);
        if(us!=null){
            return new ResponseEntity<>(us,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Usuario",description = "Permite registrar un usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Usuario creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<Usuario> addUser(@RequestBody Usuario user){
        usuarioService.addUsuario(user);
        if(usuarioService.getUsuario(user.getId())!=null){
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar Usuario por ID",description = "Elimina un usuario segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Usuario"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del usuario", example = "123")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        if(usuarioService.getUsuario(id)!=null){
            usuarioService.removeUsuario(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Usuario",description = "Permite actualizar los datos de un usuario segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Usuario creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del usuario", example = "123")
    public ResponseEntity<Usuario> updateUser(@PathVariable int id,@RequestBody Usuario usuario){
        if(usuarioService.getUsuario(id)!=null){
            usuarioService.updateUsuario(id,usuario);
            return  new ResponseEntity<>(usuario,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
