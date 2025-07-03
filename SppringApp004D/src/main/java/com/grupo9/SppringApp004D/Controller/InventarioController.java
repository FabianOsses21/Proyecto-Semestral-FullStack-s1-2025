
package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Inventario;
import com.grupo9.SppringApp004D.Service.InventarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    InventarioService inventarioService;

    @GetMapping
    @Operation(summary = "Obtener Inventarios", description = "Obtiene la lista completa de inventarios registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de inventarios"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<List<Inventario>> getAllInventario() {
        List<Inventario> lista = inventarioService.getAllInventario();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Inventario por ID", description = "Obtiene el inventario registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Inventario"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del inventario", example = "123")
    public ResponseEntity<Inventario> getInventario(@PathVariable int id) {
        Inventario inv = inventarioService.getInventario(id);
        if (inv != null) {
            return new ResponseEntity<>(inv, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tienda/{idTienda}")
    @Operation(summary = "Obtener Inventarios por Tienda", description = "Obtiene los inventarios de una tienda específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de inventarios"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID de la tienda", example = "1")
    public ResponseEntity<List<Inventario>> getInventarioByTienda(@PathVariable int idTienda) {
        List<Inventario> lista = inventarioService.getInventarioByTienda(idTienda);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @GetMapping("/producto/{idProducto}")
    @Operation(summary = "Obtener Inventarios por Producto", description = "Obtiene los inventarios de un producto específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de inventarios"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del producto", example = "1")
    public ResponseEntity<List<Inventario>> getInventarioByProducto(@PathVariable int idProducto) {
        List<Inventario> lista = inventarioService.getInventarioByProducto(idProducto);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Inventario", description = "Permite registrar un inventario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventario creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<Inventario> addInventario(@RequestBody Inventario inventario) {
        inventarioService.addInventario(inventario);
        if (inventarioService.getInventario(inventario.getId()) != null) {
            return new ResponseEntity<>(inventario, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Inventario por ID", description = "Elimina un inventario según el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario Eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del inventario", example = "123")
    public ResponseEntity<Void> deleteInventario(@PathVariable int id) {
        if (inventarioService.getInventario(id) != null) {
            inventarioService.deleteInventario(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Inventario", description = "Permite actualizar los datos de un inventario según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventario actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inventario.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del inventario", example = "123")
    public ResponseEntity<Inventario> updateInventario(@PathVariable int id, @RequestBody Inventario inventario) {
        if (inventarioService.getInventario(id) != null) {
            inventarioService.updateInventario(id, inventario);
            return new ResponseEntity<>(inventario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}