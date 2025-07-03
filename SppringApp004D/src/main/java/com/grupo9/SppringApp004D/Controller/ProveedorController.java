package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Proveedor;
import com.grupo9.SppringApp004D.Service.ProveedorService;
import com.grupo9.SppringApp004D.Assembler.ProveedorModelAssembler;
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
@RequestMapping("/proveedor")
@Tag(name = "Controlador Proveedor", description = "Servicio Rest para la gestión de Proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ProveedorModelAssembler proveedorModelAssembler;

    @GetMapping
    @Operation(summary = "Obtener Proveedores", description = "Obtiene la lista completa de proveedores registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de proveedores"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Proveedor>>> getAllProveedores() {
        List<Proveedor> lista = proveedorService.getAllProveedores();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(proveedorModelAssembler.toCollectionModel(lista), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Proveedor por ID", description = "Obtiene el proveedor registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Proveedor"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del proveedor", example = "123")
    public ResponseEntity<EntityModel<Proveedor>> getProveedor(@PathVariable int id) {
        Proveedor proveedor = proveedorService.getProveedor(id);
        if (proveedor != null) {
            return new ResponseEntity<>(proveedorModelAssembler.toModel(proveedor), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Agregar Proveedor", description = "Permite registrar un proveedor en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proveedor creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Proveedor.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Proveedor>> addProveedor(@RequestBody Proveedor proveedor) {
        proveedorService.addProveedor(proveedor);
        Proveedor creado = proveedorService.getProveedor(proveedor.getId());
        if (creado != null) {
            return new ResponseEntity<>(proveedorModelAssembler.toModel(creado), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Proveedor por ID", description = "Elimina un proveedor según el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del proveedor", example = "123")
    public ResponseEntity<Void> deleteProveedor(@PathVariable int id) {
        if (proveedorService.getProveedor(id) != null) {
            proveedorService.removeProveedor(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Proveedor", description = "Permite actualizar los datos de un proveedor según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proveedor actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Proveedor.class))),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del proveedor", example = "123")
    public ResponseEntity<EntityModel<Proveedor>> updateProveedor(@PathVariable int id, @RequestBody Proveedor proveedor) {
        Proveedor actualizado = proveedorService.updateProveedor(id, proveedor);
        if (actualizado != null) {
            return new ResponseEntity<>(proveedorModelAssembler.toModel(actualizado), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}