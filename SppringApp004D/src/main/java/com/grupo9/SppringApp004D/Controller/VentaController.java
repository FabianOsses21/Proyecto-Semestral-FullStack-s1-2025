package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Venta;
import com.grupo9.SppringApp004D.Service.VentaService;
import com.grupo9.SppringApp004D.Assembler.VentaModelAssembler;
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
@RequestMapping("/venta")
@Tag(name = "Controlador Venta", description = "Servicio Rest para la gestión de Ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private VentaModelAssembler ventaModelAssembler;

    @GetMapping
    @Operation(summary = "Obtener Ventas", description = "Obtiene la lista completa de ventas registradas en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de ventas"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Venta>>> getAllVentas() {
        List<Venta> lista = ventaService.getAllVentas();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ventaModelAssembler.toCollectionModel(lista), HttpStatus.OK);
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener Ventas por Usuario", description = "Obtiene la lista de ventas registradas por un usuario según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de ventas del usuario"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del usuario", example = "123")
    public ResponseEntity<CollectionModel<EntityModel<Venta>>> getVentasByUsuario(@PathVariable int idUsuario) {
        List<Venta> lista = ventaService.getVentasByUsuario(idUsuario);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ventaModelAssembler.toCollectionModel(lista), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Venta por ID", description = "Obtiene la venta registrada con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Venta"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID de la venta", example = "123")
    public ResponseEntity<EntityModel<Venta>> getVenta(@PathVariable int id) {
        Venta venta = ventaService.getVenta(id);
        if (venta != null) {
            return new ResponseEntity<>(ventaModelAssembler.toModel(venta), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Agregar Venta", description = "Permite registrar una venta en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venta creada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Venta.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Venta>> addVenta(@RequestBody Venta venta) {
        ventaService.addVenta(venta);
        Venta creada = ventaService.getVenta(venta.getId());
        if (creada != null) {
            return new ResponseEntity<>(ventaModelAssembler.toModel(creada), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Venta por ID", description = "Elimina una venta según el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta eliminada"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID de la venta", example = "123")
    public ResponseEntity<Void> deleteVenta(@PathVariable int id) {
        if (ventaService.getVenta(id) != null) {
            ventaService.removeVenta(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Venta", description = "Permite actualizar los datos de una venta según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venta actualizada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Venta.class))),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID de la venta", example = "123")
    public ResponseEntity<EntityModel<Venta>> updateVenta(@PathVariable int id, @RequestBody Venta venta) {
        Venta actualizada = ventaService.updateVenta(id, venta);
        if (actualizada != null) {
            return new ResponseEntity<>(ventaModelAssembler.toModel(actualizada), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}