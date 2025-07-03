package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Cupones;
import com.grupo9.SppringApp004D.Service.CuponesService;
import com.grupo9.SppringApp004D.Assembler.CuponesModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cupon")
@Tag(name = "Controlador Cupones", description = "Servicio Rest para la gestión de Cupones")
public class CuponesController {

    @Autowired
    CuponesService cuponesService;

    @Autowired
    CuponesModelAssembler cuponesModelAssembler;

    @GetMapping
    @Operation(summary = "Obtener Cupones", description = "Obtiene la lista completa de cupones registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de cupones"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Cupones>>> getAllCupones() {
        List<Cupones> lista = cuponesService.getAllCupones();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cuponesModelAssembler.toCollectionModel(lista), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Cupon por ID", description = "Obtiene el cupon registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Cupon"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del cupon", example = "1")
    public ResponseEntity<EntityModel<Cupones>> getCupon(@PathVariable int id) {
        Cupones cupon = cuponesService.getCupon(id);
        if (cupon != null) {
            return new ResponseEntity<>(cuponesModelAssembler.toModel(cupon), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Agregar Cupon", description = "Permite registrar un cupon en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cupon creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cupones.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Cupones>> addCupon(@RequestBody Cupones cupon) {
        cuponesService.addCupones(cupon);
        Cupones creado = cuponesService.getCupon(cupon.getId());
        if (creado != null) {
            return new ResponseEntity<>(cuponesModelAssembler.toModel(creado), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Cupon por ID", description = "Elimina un cupon según el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupon eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del cupon", example = "1")
    public ResponseEntity<Void> deleteCupon(@PathVariable int id) {
        if (cuponesService.getCupon(id) != null) {
            cuponesService.removeCupon(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Cupon", description = "Permite actualizar los datos de un cupon según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupon actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cupones.class))),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del cupon", example = "1")
    public ResponseEntity<EntityModel<Cupones>> updateCupon(@PathVariable int id, @RequestBody Cupones cupon) {
        if (cuponesService.getCupon(id) != null) {
            cuponesService.updateCupon(id, cupon);
            Cupones actualizado = cuponesService.getCupon(id);
            return new ResponseEntity<>(cuponesModelAssembler.toModel(actualizado), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}