package com.grupo9.SppringApp004D.Controller;


import com.grupo9.SppringApp004D.Model.Producto;
import com.grupo9.SppringApp004D.Service.ProductoService;
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
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping
    @Operation(summary = "Obtener Productos",description = "Obtiene la lista completa de productos registrados en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de productos"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public ResponseEntity<List<Producto>> getAllProductos(){
        List<Producto> lista = productoService.getAllProductos();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Producto por ID",description = "Segun un ID entregado, obtiene al producto registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Producto"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del producto", example = "123")
    public ResponseEntity<Producto> getProducto(@PathVariable int id){
        Producto pr = productoService.getProducto(id);
        if(pr!=null){
            return new ResponseEntity<>(pr,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Producto",description = "Permite registrar un producto en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Producto creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<Producto> addUser(@RequestBody Producto producto){
        productoService.addProducto(producto);
        if(productoService.getProducto(producto.getId())!=null){
            return new ResponseEntity<>(producto,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar Producto por ID",description = "Elimina un producto segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Producto Eliminado"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del producto", example = "123")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        if(productoService.getProducto(id)!=null){
            productoService.removeProducto(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Producto",description = "Permite actualizar los datos de un producto segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Producto creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del producto", example = "123")
    public ResponseEntity<Producto> updateUser(@PathVariable int id,@RequestBody Producto producto){
        if(productoService.getProducto(id)!=null){
            productoService.updateProducto(id,producto);
            return  new ResponseEntity<>(producto,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
