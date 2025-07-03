package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Pedido;
import com.grupo9.SppringApp004D.Service.PedidoService;
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
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    @Operation(summary = "Obtener Pedidos",description = "Obtiene la lista completa de pedidos registrados en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de pedidos"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public ResponseEntity<List<Pedido>> getAllPedidos(){
        List<Pedido> lista = pedidoService.getAllPedidos();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Pedido por ID",description = "Segun un ID entregado, obtiene el pedido registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Pedido"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del pedido", example = "123")
    public ResponseEntity<Pedido> getPedido(@PathVariable int id){
        Pedido pr = pedidoService.getPedido(id);
        if(pr!=null){
            return new ResponseEntity<>(pr,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Pedido",description = "Permite registrar un pedido en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Pedido creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<Pedido> addPedido(@RequestBody Pedido pedido){
        pedidoService.addPedido(pedido);
        if(pedidoService.getPedido(pedido.getId())!=null){
            return new ResponseEntity<>(pedido,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar Pedido por ID",description = "Elimina un pedido segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Pedido Eliminado"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del pedido", example = "123")
    public ResponseEntity<Void> deletePedido(@PathVariable int id){
        if(pedidoService.getPedido(id)!=null){
            pedidoService.removePedido(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Pedido",description = "Permite actualizar los datos de un pedido segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Pedido creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del pedido", example = "123")
    public ResponseEntity<Pedido> updatePedido(@PathVariable int id,@RequestBody Pedido pedido){
        if(pedidoService.getPedido(id)!=null){
            pedidoService.updatePedido(id,pedido);
            return  new ResponseEntity<>(pedido,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener Pedidos por Usuario",description = "Obtiene la lista de pedidos registrados por un usuario segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista de pedidos del usuario"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del usuario", example = "123")
    public ResponseEntity<List<Pedido>> getPedidosByUsuario(@PathVariable int idUsuario){
        List<Pedido> lista = pedidoService.getPedidosByUsuario(idUsuario);
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
    }
}