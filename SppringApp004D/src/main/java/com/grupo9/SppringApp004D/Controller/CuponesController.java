package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Cupones;
import com.grupo9.SppringApp004D.Model.Trabajador;
import com.grupo9.SppringApp004D.Service.CuponesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cupon")

public class CuponesController {

    @Autowired
    CuponesService cuponesService;

    @GetMapping
    @Operation(summary = "Obtener Cupones",description = "Obtiene la lista completa de cupones registrados en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de cupones"),
            @ApiResponse(responseCode = "404",description = "No se encuentran los datos")
    })
    public ResponseEntity<List<Cupones>> getAllCupones(){
        List<Trabajador> lista = cuponesService.getAllCupones();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
    }

    

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Cupon por ID",description = "Segun un ID entregado, obtiene el cupon registrado")
    @ApiResponse(value = {
        @ApiResponse(responseCode = "200",description = "Retornar cupon"),
        @ApiResponse(responseCode = "404",description = "No se encuentra informacion")
    })
    @Parameter(description = "ID del cupon", example = "123")
    public ResponseEntity<Cupones> getCupones(@PathVariable int id){
        Cupones tr = cuponesService.getAllCupones(id);
        if(tr!=null){
            return new ResponseEntity<>(tr,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    @Operation(summary = "Agregar cupon", description = "Permite registrar un cupon")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201",description = "Cupon creado",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Cupones.class))),
        @ApiResponse(responseCode = "204", description = "No hay contenido dentro de la solicitud")
    })
    public ResponseEntity<Trabajador> addTrabajador(@RequestBody Trabajador trabajador){
        cuponesService.addCupones(cupon);
        if(cuponService.getCupones (cupon.getId())!=null){
            return new ResponseEntity<>(cupon,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT;)
        }
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Cupon por ID", description = "Elimina un cupon segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cupon Eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos del cupon")
    })
    @Parameter(description = "El ID del cupon", example = "123")
    public ResponseEntity<Void> deleteCupon(@PathVariable int id) {
        if (cuponService.getCupon(id) != null) {
            cuponService.removeCupon(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar informacion de cupon", description = "Permite actualizar los datos de los cupones segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cupon agregado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cupones.class))),
            @ApiResponse(responseCode = "204", description = "No hay informacion en la solicitud")
    })
    @Parameter(description = "El ID de cupon", example = "123")
    public ResponseEntity<Cupones> updateCupon(@PathVariable int id, @RequestBody Cupones cupones) {
        if (cuponesService.getCupon(id) != null) {
            cuponesService.updateCupon(id, cupon);
            return new ResponseEntity<>(cupon, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

}
