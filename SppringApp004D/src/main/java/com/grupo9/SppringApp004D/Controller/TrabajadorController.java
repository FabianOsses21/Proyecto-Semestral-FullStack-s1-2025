package com.grupo9.SppringApp004D.Controller;


import com.grupo9.SppringApp004D.Model.Trabajador;
import com.grupo9.SppringApp004D.Service.TrabajadorService;
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
@RequestMapping("/trabajador")
public class TrabajadorController {

    @Autowired
    TrabajadorService trabajadorService;

    @GetMapping
    @Operation(summary = "Obtener Trabajadores",description = "Obtiene la lista completa de trabajadores registrados en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de trabajadores"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public ResponseEntity<List<Trabajador>> getAllTrabajadores(){
        List<Trabajador> lista = trabajadorService.getAllTrabajadores();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Trabajador por ID",description = "Segun un ID entregado, obtiene al trabajador registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Trabajador"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del trabajador", example = "123")
    public ResponseEntity<Trabajador> getTrabajador(@PathVariable int id){
        Trabajador tr = trabajadorService.getTrabajador(id);
        if(tr!=null){
            return new ResponseEntity<>(tr,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Trabajador",description = "Permite registrar un trabajador en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Trabajador creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trabajador.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<Trabajador> addTrabajador(@RequestBody Trabajador trabajador){
        trabajadorService.addTrabajador(trabajador);
        if(trabajadorService.getTrabajador(trabajador.getId())!=null){
            return new ResponseEntity<>(trabajador,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar Trabajador por ID",description = "Elimina un trabajador segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Trabajador Eliminado"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del trabajador", example = "123")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        if(trabajadorService.getTrabajador(id)!=null){
            trabajadorService.removeTrabajador(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Trabajador",description = "Permite actualizar los datos de un trabajador segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Trabajador creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trabajador.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del trabajador", example = "123")
    public ResponseEntity<Trabajador> updateTrabajador(@PathVariable int id,@RequestBody Trabajador trabajador){
        if(trabajadorService.getTrabajador(id)!=null){
            trabajadorService.updateTrabajador(id,trabajador);
            return  new ResponseEntity<>(trabajador,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
