package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Tienda;
import com.grupo9.SppringApp004D.Service.TiendaService;
import com.grupo9.SppringApp004D.Assembler.TiendaModelAssembler;
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
@RequestMapping("/tienda")
@Tag(name = "Controlador Tienda", description = "Servicio Rest para la gestión de Tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @Autowired
    private TiendaModelAssembler tiendaModelAssembler;

    @GetMapping
    @Operation(summary = "Obtener Tiendas", description = "Obtiene la lista completa de tiendas registradas en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de tiendas"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Tienda>>> getAllTiendas() {
        List<Tienda> lista = tiendaService.getAllTiendas();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tiendaModelAssembler.toCollectionModel(lista), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Tienda por ID", description = "Obtiene la tienda registrada con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Tienda"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID de la tienda", example = "123")
    public ResponseEntity<EntityModel<Tienda>> getTienda(@PathVariable int id) {
        Tienda tienda = tiendaService.getTienda(id);
        if (tienda != null) {
            return new ResponseEntity<>(tiendaModelAssembler.toModel(tienda), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Agregar Tienda", description = "Permite registrar una tienda en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tienda creada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tienda.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Tienda>> addTienda(@RequestBody Tienda tienda) {
        tiendaService.addTienda(tienda);
        Tienda creada = tiendaService.getTienda(tienda.getId());
        if (creada != null) {
            return new ResponseEntity<>(tiendaModelAssembler.toModel(creada), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Tienda por ID", description = "Elimina una tienda según el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tienda eliminada"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID de la tienda", example = "123")
    public ResponseEntity<Void> deleteTienda(@PathVariable int id) {
        if (tiendaService.getTienda(id) != null) {
            tiendaService.removeTienda(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Tienda", description = "Permite actualizar los datos de una tienda según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tienda actualizada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tienda.class))),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID de la tienda", example = "123")
    public ResponseEntity<EntityModel<Tienda>> updateTienda(@PathVariable int id, @RequestBody Tienda tienda) {
        Tienda actualizada = tiendaService.updateTienda(id, tienda);
        if (actualizada != null) {
            return new ResponseEntity<>(tiendaModelAssembler.toModel(actualizada), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}