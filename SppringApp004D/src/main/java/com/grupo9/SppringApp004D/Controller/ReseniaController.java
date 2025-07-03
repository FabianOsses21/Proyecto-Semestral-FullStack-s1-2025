package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Resenia;
import com.grupo9.SppringApp004D.Service.ReseniaService;
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
@RequestMapping("/resenia")
public class ReseniaController {
    @Autowired
    ReseniaService reseniaService;

    @GetMapping
    @Operation(summary = "Obtener Reseñas", description = "Obtiene la lista completa de reseñas escritas en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de reseñas escritas"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos de las reseñas")
    })
    public ResponseEntity<List<Resenia>> getAllResenias() {
        List<Resenia> lista = reseniaService.getAllResenias();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @GetMapping("/producto/{idProducto}")
    @Operation(summary = "Obtener reseñas por producto", description = "Obtiene la lista de reseñas asociadas a un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de reseñas"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<List<Resenia>> getReseniasByProducto(@PathVariable int idProducto) {
        List<Resenia> lista = reseniaService.getReseniasByProducto(idProducto);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(summary = "Obtener reseñas por usuario", description = "Obtiene la lista de reseñas asociadas a un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista de reseñas"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<List<Resenia>> getReseniasByUsuario(@PathVariable int idUsuario) {
        List<Resenia> lista = reseniaService.getReseniasByUsuario(idUsuario);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener reseña por ID", description = "Según un ID entregado, obtiene la reseña agregada con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna reseña"),
            @ApiResponse(responseCode = "404", description = "No se encuentra reseña escrita")
    })
    @Parameter(description = "El ID de reseña", example = "123")
    public ResponseEntity<Resenia> getResenia(@PathVariable int id) {
        Resenia resenia = reseniaService.getResenia(id);
        if (resenia != null) {
            return new ResponseEntity<>(resenia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar reseña", description = "Permite agregar una reseña en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reseña agregada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resenia.class))),
            @ApiResponse(responseCode = "204", description = "No hay información")
    })
    public ResponseEntity<Resenia> addResenia(@RequestBody Resenia resenia) {
        reseniaService.addResenia(resenia);
        if (reseniaService.getResenia(resenia.getId()) != null) {
            return new ResponseEntity<>(resenia, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Reseñas", description = "Permite actualizar las reseñas escritas según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reseña actualizada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resenia.class))),
            @ApiResponse(responseCode = "204", description = "No hay información en las reseñas")
    })
    @Parameter(description = "El ID de la reseña", example = "123")
    public ResponseEntity<Resenia> updateResenia(@PathVariable int id, @RequestBody Resenia resenia) {
        if (reseniaService.getResenia(id) != null) {
            reseniaService.updateResenia(id, resenia);
            return new ResponseEntity<>(resenia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reseña por ID", description = "Elimina una reseña según el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseña eliminada"),
            @ApiResponse(responseCode = "404", description = "No se encuentra información")
    })
    @Parameter(description = "El ID de la reseña", example = "123")
    public ResponseEntity<Void> deleteResenia(@PathVariable int id) {
        if (reseniaService.getResenia(id) != null) {
            reseniaService.removeResenia(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}