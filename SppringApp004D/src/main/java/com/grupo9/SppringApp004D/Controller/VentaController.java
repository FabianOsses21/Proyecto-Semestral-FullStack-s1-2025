package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Venta;
import com.grupo9.SppringApp004D.Service.VentaService;
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
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    VentaService ventaService;

    @GetMapping
    @Operation(summary = "Obtener Ventas",description = "Obtiene la lista completa de ventas registradas en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de ventas"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public ResponseEntity<List<Venta>> getAllVentas(){
        List<Venta> lista = ventaService.getAllVentas();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener Ventas",description = "Obtiene la lista de ventas registradas en el sistema con ese ID de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista de ventas"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public ResponseEntity<List<Venta>> getVentasByUsuario(int id){
        List<Venta> lista = ventaService.getVentasByUsuario(id);
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Venta por ID",description = "Segun un ID entregado, obtiene la venta registrada con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Venta"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID de la venta", example = "123")
    public ResponseEntity<Venta> getVenta(@PathVariable int id){
        Venta pr = ventaService.getVenta(id);
        if(pr!=null){
            return new ResponseEntity<>(pr,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Venta",description = "Permite registrar una venta en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Venta creada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Venta.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<Venta> addVenta(@RequestBody Venta venta){
        ventaService.addVenta(venta);
        if(ventaService.getVenta(venta.getId())!=null){
            return new ResponseEntity<>(venta,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar Venta por ID",description = "Elimina una venta segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Venta Eliminada"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID de la venta", example = "123")
    public ResponseEntity<Void> deleteVenta(@PathVariable int id){
        if(ventaService.getVenta(id)!=null){
            ventaService.removeVenta(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Venta",description = "Permite actualizar los datos de una venta segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Venta creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Venta.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID de la venta", example = "123")
    public ResponseEntity<Venta> updateUser(@PathVariable int id,@RequestBody Venta venta){
        if(ventaService.getVenta(id)!=null){
            ventaService.updateVenta(id,venta);
            return  new ResponseEntity<>(venta,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
