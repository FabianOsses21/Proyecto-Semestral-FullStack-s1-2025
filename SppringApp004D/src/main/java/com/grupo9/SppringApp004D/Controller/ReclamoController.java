package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Reclamo;
import com.grupo9.SppringApp004D.Model.Trabajador;
import com.grupo9.SppringApp004D.Service.ReclamoService;
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
@RequestMapping("/reclamo")
public class ReclamoController {
    @Autowired
    ReclamoService reclamoService;

    @GetMapping
    @Operation(summary = "Obtener reclamos",description = "Obtiene la lista completa de reclamos escritos en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de reclamos escritos"),
            @ApiResponse(responseCode = "404",description = "No se encuentra informacion")
    })
    public ResponseEntity<List<Reclamo>> getAllReclamos(){
        List<Reclamo> lista = reclamoService.getAllReclamos();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener reclamo por ID", description = "Segun un ID entregado, obtiene el reclamo registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna reclamo correspondiente al ID"),
            @ApiResponse(responseCode = "404", description = "No se encuentra informacion")
    })
    @Parameter(description = "El ID del reclamo", example = "123")
    public ResponseEntity<Reclamo> getReclamo(@PathVariable int id) {
        Trabajador tr = reclamoService.getReclamo(id);
        if (tr != null) {
            return new ResponseEntity<>(tr, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/usuario/{idUsuario}")
    public String getReclamoByUsuario(@PathVariable int idUsuario) {
        return reclamoService.getReclamosByIdUsuario(idUsuario);
    }
    @GetMapping("/estado/{estado}")
    public String getReclamoByEstado(@PathVariable String estado) {
        return reclamoService.getReclamosByEstado(estado);
    }


    @GetMapping("/usuario/{idUsuario}/estado/{estado}")
    public String getReclamoByUsuarioAndEstado(@PathVariable int idUsuario, @PathVariable String estado) {
        return reclamoService.getReclamosByIdUsuarioAndEstado(idUsuario, estado);
    }
    @PostMapping
    
    @Operation(summary = "Agregar Reclamo", description = "Permite registrar un reclamo nuevo en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reclamo agregado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reclamo.class))),
            @ApiResponse(responseCode = "204", description = "No hay hay informacion")
    })
    public ResponseEntity<Reclamo> addReclamo(@RequestBody Reclamo reclamo) {
        reclamoService.addReclamo(reclamo);
        if (reclamoService.getReclamo(reclamo.getId()) != null) {
            return new ResponseEntity<>(reclamo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar reclamos", description = "Permite ver el estado actualizado de los reclamos segun eñ ID")
    @ApiResponse(value = {
        @ApiResponse(responseCode = "201", description = "Reclamo agregado"),
                content = @Content(mediaType="application/json", 
                        schema = @Schema(implementation = Reclamo.class))),
        @ApiResponse(responseCode = "204",description = "No hay informacion en la solicitud")
    
}
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reclamo por ID", description = "Elimina un reclamo segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reclamo eliminadp"),
            @ApiResponse(responseCode = "404", description = "No se encuentra informacion")
    })
    @Parameter(description = "El ID del reclamo", example = "123")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (reclamoService.getReclamo(id) != null) {
            reclamoService.removeReclamo(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
