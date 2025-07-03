package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Envio;
import com.grupo9.SppringApp004D.Service.EnvioService;
import com.grupo9.SppringApp004D.Assembler.EnvioModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/envio")
@Tag(name = "Controlador Envio", description = "Servicio Rest para la gestión de Envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @Autowired
    private EnvioModelAssembler envioModelAssembler;

    @GetMapping
    @Operation(summary = "Obtener Envios", description = "Obtiene la lista completa de envíos registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de envíos"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Envio>>> getAllEnvios() {
        List<Envio> lista = envioService.getAllEnvios();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(envioModelAssembler.toCollectionModel(lista), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Envío por ID", description = "Obtiene el envío registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Envío"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del envío", example = "123")
    public ResponseEntity<EntityModel<Envio>> getEnvio(@PathVariable int id) {
        Envio envio = envioService.getEnvio(id);
        if (envio != null) {
            return new ResponseEntity<>(envioModelAssembler.toModel(envio), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Agregar Envío", description = "Permite registrar un envío en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Envío creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Envio.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Envio>> addEnvio(@RequestBody Envio envio) {
        envioService.addEnvio(envio);
        Envio creado = envioService.getEnvio(envio.getId());
        if (creado != null) {
            return new ResponseEntity<>(envioModelAssembler.toModel(creado), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Envío por ID", description = "Elimina un envío según el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del envío", example = "123")
    public ResponseEntity<Void> deleteEnvio(@PathVariable int id) {
        if (envioService.getEnvio(id) != null) {
            envioService.removeEnvio(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Envío", description = "Permite actualizar los datos de un envío según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Envio.class))),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del envío", example = "123")
    public ResponseEntity<EntityModel<Envio>> updateEnvio(@PathVariable int id, @RequestBody Envio envio) {
        Envio actualizado = envioService.updateEnvio(id, envio);
        if (actualizado != null) {
            return new ResponseEntity<>(envioModelAssembler.toModel(actualizado), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}