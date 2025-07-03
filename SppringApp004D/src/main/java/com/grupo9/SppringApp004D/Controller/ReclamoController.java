package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Reclamo;
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
    @Operation(summary = "Obtener reclamos", description = "Obtiene la lista completa de reclamos escritos en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de reclamos escritos"),
            @ApiResponse(responseCode = "404", description = "No se encuentra información")
    })
    public ResponseEntity<List<Reclamo>> getAllReclamos() {
        List<Reclamo> lista = reclamoService.getAllReclamos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener reclamos por usuario", description = "Obtiene la lista de reclamos asociados a un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de reclamos"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<List<Reclamo>> getReclamosByUsuario(@PathVariable int idUsuario) {
        List<Reclamo> lista = reclamoService.getReclamosByUsuario(idUsuario);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Obtener reclamos por estado", description = "Obtiene la lista de reclamos según su estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de reclamos"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<List<Reclamo>> getReclamosByEstado(@PathVariable String estado) {
        List<Reclamo> lista = reclamoService.getReclamosByEstado(estado);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @GetMapping("/usuario/{idUsuario}/estado/{estado}")
    @Operation(summary = "Obtener reclamos por usuario y estado", description = "Obtiene la lista de reclamos según usuario y estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de reclamos"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<List<Reclamo>> getReclamosByUsuarioAndEstado(@PathVariable int idUsuario, @PathVariable String estado) {
        List<Reclamo> lista = reclamoService.getReclamosByUsuarioAndEstado(idUsuario, estado);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener reclamo por ID", description = "Según un ID entregado, obtiene el reclamo registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna reclamo correspondiente al ID"),
            @ApiResponse(responseCode = "404", description = "No se encuentra información")
    })
    @Parameter(description = "El ID del reclamo", example = "123")
    public ResponseEntity<Reclamo> getReclamo(@PathVariable int id) {
        Reclamo reclamo = reclamoService.getReclamo(id);
        if (reclamo != null) {
            return new ResponseEntity<>(reclamo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Reclamo", description = "Permite registrar un reclamo nuevo en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reclamo agregado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reclamo.class))),
            @ApiResponse(responseCode = "204", description = "No hay información")
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
    @Operation(summary = "Actualizar reclamos", description = "Permite ver el estado actualizado de los reclamos según el ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reclamo actualizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reclamo.class))),
            @ApiResponse(responseCode = "204", description = "No hay información en la solicitud")
    })
    @Parameter(description = "El ID del reclamo", example = "123")
    public ResponseEntity<Reclamo> updateReclamo(@PathVariable int id, @RequestBody Reclamo reclamo) {
        if (reclamoService.getReclamo(id) != null) {
            reclamoService.updateReclamo(id, reclamo);
            return new ResponseEntity<>(reclamo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reclamo por ID", description = "Elimina un reclamo según el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reclamo eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentra información")
    })
    @Parameter(description = "El ID del reclamo", example = "123")
    public ResponseEntity<Void> deleteReclamo(@PathVariable int id) {
        if (reclamoService.getReclamo(id) != null) {
            reclamoService.removeReclamo(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}