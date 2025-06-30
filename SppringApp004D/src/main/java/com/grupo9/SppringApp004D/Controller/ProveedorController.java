package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Proveedor;
import com.grupo9.SppringApp004D.Service.ProveedorService;
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
@RequestMapping("/proveedor")

public class ProveedorController {

    @Autowired
    ProveedorService proveedorService;

    @GetMapping
    @Operation(summary = "Obtener Proveedores",description = "Obtiene la lista completa de proveedores registrados en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de proveedores"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public ResponseEntity<List<Proveedor>> getAllProveedores(){
        List<Proveedor> lista = proveedorService.getAllProveedores();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Proveedor por ID",description = "Segun un ID entregado, obtiene al proveedor registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Proveedor"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del proveedor", example = "123")
    public ResponseEntity<Proveedor> getProveedor(@PathVariable int id){
        Proveedor tr = proveedorService.getProveedor(id);
        if(tr!=null){
            return new ResponseEntity<>(tr,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Proveedor",description = "Permite registrar un proveedor en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Proveedor creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Proveedor.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<Proveedor> addTrabajador(@RequestBody Proveedor proveedor){
        proveedorService.addProveedor(proveedor);
        if(proveedorService.getProveedor(proveedor.getId())!=null){
            return new ResponseEntity<>(proveedor,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar Proveedor por ID",description = "Elimina un proveedor segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Proveedor Eliminado"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del proveedor", example = "123")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        if(proveedorService.getProveedor(id)!=null){
            proveedorService.removeProveedor(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Proveedor",description = "Permite actualizar los datos de un proveedor segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Proveedor creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Proveedor.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del proveedor", example = "123")
    public ResponseEntity<Proveedor> updateTrabajador(@PathVariable int id,@RequestBody Proveedor proveedor){
        if(proveedorService.getProveedor(id)!=null){
            proveedorService.updateProveedor(id,proveedor);
            return  new ResponseEntity<>(proveedor,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
