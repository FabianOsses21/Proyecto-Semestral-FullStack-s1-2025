package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Envio;
import com.grupo9.SppringApp004D.Service.EnvioService;
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
@RequestMapping("/envio")
public class EnvioController {
    @Autowired
    private EnvioService envioService;

    @GetMapping
    @Operation(summary = "Obtener Envios",description = "Obtiene la lista completa de envios registrados en sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna lista completa de envios"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    public ResponseEntity<List<Envio>> getAllEnvios(){
        List<Envio> lista = envioService.getAllEnvios();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(lista,HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Envio por ID",description = "Segun un ID entregado, obtiene el envio registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Retorna Envio"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del envio", example = "123")
    public ResponseEntity<Envio> getEnvio(@PathVariable int id){
        Envio en = envioService.getEnvio(id);
        if(en!=null){
            return new ResponseEntity<>(en,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Envio",description = "Permite registrar un envio en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Envio creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Envio.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<Envio> addEnvio(@RequestBody Envio envio){
        envioService.addEnvio(envio);
        if(envioService.getEnvio(envio.getId())!=null){
            return new ResponseEntity<>(envio,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Eliminar Envio por ID",description = "Elimina un envio segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Envio Eliminado"),
            @ApiResponse(responseCode = "404",description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del envio", example = "123")
    public ResponseEntity<Void> deleteEnvio(@PathVariable int id){
        if(envioService.getEnvio(id)!=null){
            envioService.removeEnvio(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Envio",description = "Permite actualizar los datos de un envio segun su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Envio creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Envio.class))),
            @ApiResponse(responseCode = "204",description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del producto", example = "123")
    public ResponseEntity<Envio> updateEnvio(@PathVariable int id,@RequestBody Envio envio){
        if(envioService.getEnvio(id)!=null){
            envioService.updateEnvio(id,envio);
            return  new ResponseEntity<>(envio,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
