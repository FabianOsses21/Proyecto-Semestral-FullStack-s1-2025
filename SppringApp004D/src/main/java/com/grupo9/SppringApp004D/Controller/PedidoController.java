package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Pedido;
import com.grupo9.SppringApp004D.Service.PedidoService;
import com.grupo9.SppringApp004D.Assembler.PedidoModelAssembler;
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
@RequestMapping("/pedido")
@Tag(name = "Controlador Pedido", description = "Servicio Rest para la gestión de Pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @GetMapping
    @Operation(summary = "Obtener Pedidos", description = "Obtiene la lista completa de pedidos registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de pedidos"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Pedido>>> getAllPedidos() {
        List<Pedido> lista = pedidoService.getAllPedidos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pedidoModelAssembler.toCollectionModel(lista), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Pedido por ID", description = "Obtiene el pedido registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Pedido"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del pedido", example = "123")
    public ResponseEntity<EntityModel<Pedido>> getPedido(@PathVariable int id) {
        Pedido pedido = pedidoService.getPedido(id);
        if (pedido != null) {
            return new ResponseEntity<>(pedidoModelAssembler.toModel(pedido), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Agregar Pedido", description = "Permite registrar un pedido en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Pedido>> addPedido(@RequestBody Pedido pedido) {
        pedidoService.addPedido(pedido);
        Pedido creado = pedidoService.getPedido(pedido.getId());
        if (creado != null) {
            return new ResponseEntity<>(pedidoModelAssembler.toModel(creado), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Pedido por ID", description = "Elimina un pedido según el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del pedido", example = "123")
    public ResponseEntity<Void> deletePedido(@PathVariable int id) {
        if (pedidoService.getPedido(id) != null) {
            pedidoService.removePedido(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Pedido", description = "Permite actualizar los datos de un pedido según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del pedido", example = "123")
    public ResponseEntity<EntityModel<Pedido>> updatePedido(@PathVariable int id, @RequestBody Pedido pedido) {
        Pedido actualizado = pedidoService.updatePedido(id, pedido);
        if (actualizado != null) {
            return new ResponseEntity<>(pedidoModelAssembler.toModel(actualizado), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener Pedidos por Usuario", description = "Obtiene la lista de pedidos registrados por un usuario según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de pedidos del usuario"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del usuario", example = "123")
    public ResponseEntity<CollectionModel<EntityModel<Pedido>>> getPedidosByUsuario(@PathVariable int idUsuario) {
        List<Pedido> lista = pedidoService.getPedidosByUsuario(idUsuario);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pedidoModelAssembler.toCollectionModel(lista), HttpStatus.OK);
    }
}