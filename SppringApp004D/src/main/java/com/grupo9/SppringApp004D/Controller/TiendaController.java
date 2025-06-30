package com.grupo9.SppringApp004D.Controller;


import com.grupo9.SppringApp004D.Model.Tienda;
import com.grupo9.SppringApp004D.Service.TiendaService;
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
@RequestMapping("/tienda")
public class TiendaController {

    @Autowired
    TiendaService tiendaService;

    @GetMapping
    @Operation(summary = "Obtener Tiendas",description = "Obtiene la lista completa de tiendas registradas en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de tiendas"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public ResponseEntity<List<Tienda>> getAllTiendas(){
        List<Tienda> lista = tiendaService.getAllTiendas();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Tienda por ID",description = "Segun un ID entregado, obtiene a la tienda registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Tienda"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID de la tienda", example = "123")
    public ResponseEntity<Tienda> getTienda(@PathVariable int id){
        Tienda tr = tiendaService.getTienda(id);
        if(tr!=null){
            return new ResponseEntity<>(tr,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Tienda",description = "Permite registrar una tienda en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Tienda creada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tienda.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<Tienda> addTienda(@RequestBody Tienda tienda){
        tiendaService.addTienda(tienda);
        if(tiendaService.getTienda(tienda.getId())!=null){
            return new ResponseEntity<>(tienda,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar Tienda por ID",description = "Elimina una tienda segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Tienda Eliminada"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID de la Tienda", example = "123")
    public ResponseEntity<Void> deleteTienda(@PathVariable int id){
        if(tiendaService.getTienda(id)!=null){
            tiendaService.removeTienda(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Tienda",description = "Permite actualizar los datos de una tienda segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Tienda creada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Tienda.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID de la tienda", example = "123")
    public ResponseEntity<Tienda> updateTienda(@PathVariable int id,@RequestBody Tienda tienda){
        if(tiendaService.getTienda(id)!=null){
            tiendaService.updateTienda(id,tienda);
            return  new ResponseEntity<>(tienda,HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
