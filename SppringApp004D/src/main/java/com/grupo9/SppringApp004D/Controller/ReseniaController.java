package com.grupo9.SppringApp004D.Controller;


import com.grupo9.SppringApp004D.Model.Resenia;
import com.grupo9.SppringApp004D.Model.Trabajador;
import com.grupo9.SppringApp004D.Service.ReseniaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
    @Operation(summary = "Obtener Reseñas",description = "Obtiene la lista completa de reseñas escritas en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de reseñas escritas"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos de las reseñas")
    })
    public ResponseEntity<List<Resenias>> getAllReseñas(){
        List<Resenia> lista = reseniaService.getAllResenias();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener reseña por ID",description = "Segun un ID entregado, obtiene la reseña agregada con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna reseña"),
            @ApiResponse(responseCode = "404",description = "No se encuentra reseña escrita")
    })
    @Parameter(description = "El ID de reseña", example = "123")
    public ResponseEntity<Resenia> getResenia(@PathVariable int id){
        Resenia tr = reseniaService.getResenia(id);
        if(tr!=null){
            return new ResponseEntity<>(tr,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    }


    @GetMapping("/producto/{idProducto}")
    public String getReseniaByProducto(@PathVariable int idProducto) {
        return reseniaService.getReseniasByIdProducto(idProducto);
    }
    @GetMapping("/usuario/{idUsuario}")
    public String getReseniaByUsuario(@PathVariable int idUsuario) {
        return reseniaService.getReseniasByIdUsuario(idUsuario);
    }


    @PostMapping
    @Operation(summary = "Agregar cupon", description = "Permite agregar un cupon en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cupon agregado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resenia.class))),
            @ApiResponse(responseCode = "204", description = "No hay informacion")
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
    @Operation(summary = "Actualizar Reseñas", description = "Permite actualizar las reseñas escritas segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reseña actualizada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Resenia.class))),
            @ApiResponse(responseCode = "204", description = "No hay informacion en las reseñas")
    })
    @Parameter(description = "El ID de la reseña", example = "123")
    public ResponseEntity<Resenia> updateTrabajador(@PathVariable int id, @RequestBody Resenia resenia) {
        if (reseniaService.getTrabajador(id) != null) {
            reseniaService.updateTrabajador(id, resenia);
            return new ResponseEntity<>(resenia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar reseña por ID", description = "Elimina una reseña segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseña eliminada"),
            @ApiResponse(responseCode = "404", description = "No se encuentra informacion")
    })
    @Parameter(description = "El ID de la reseña", example = "123")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (reseniaService.getResenia(id) != null) {
            reseniaService.removeResenia(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    }
