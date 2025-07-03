package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Trabajador;
import com.grupo9.SppringApp004D.Service.TrabajadorService;
import com.grupo9.SppringApp004D.Assembler.TrabajadorModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trabajador")
@Tag(name = "Controlador Trabajador", description = "Servicio Rest para la gestión de Trabajadores")
public class TrabajadorController {

    @Autowired
    private TrabajadorService trabajadorService;

    @Autowired
    private TrabajadorModelAssembler trabajadorModelAssembler;

    @GetMapping
    @Operation(summary = "Obtener Trabajadores", description = "Obtiene la lista completa de trabajadores registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de trabajadores"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Trabajador>>> getAllTrabajadores() {
        List<Trabajador> lista = trabajadorService.getAllTrabajadores();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trabajadorModelAssembler.toCollectionModel(lista), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Trabajador por ID", description = "Obtiene el trabajador registrado con ese ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Trabajador"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del trabajador", example = "123")
    public ResponseEntity<EntityModel<Trabajador>> getTrabajador(@PathVariable int id) {
        Trabajador tr = trabajadorService.getTrabajador(id);
        if (tr != null) {
            return new ResponseEntity<>(trabajadorModelAssembler.toModel(tr), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Agregar Trabajador", description = "Permite registrar un trabajador en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Trabajador creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trabajador.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Trabajador>> addTrabajador(@RequestBody Trabajador trabajador) {
        trabajadorService.addTrabajador(trabajador);
        Trabajador creado = trabajadorService.getTrabajador(trabajador.getId());
        if (creado != null) {
            return new ResponseEntity<>(trabajadorModelAssembler.toModel(creado), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Trabajador por ID", description = "Elimina un trabajador según el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trabajador eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del trabajador", example = "123")
    public ResponseEntity<Void> deleteTrabajador(@PathVariable int id) {
        if (trabajadorService.getTrabajador(id) != null) {
            trabajadorService.removeTrabajador(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Trabajador", description = "Permite actualizar los datos de un trabajador según su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trabajador actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trabajador.class))),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del trabajador", example = "123")
    public ResponseEntity<EntityModel<Trabajador>> updateTrabajador(@PathVariable int id, @RequestBody Trabajador trabajador) {
        Trabajador actualizado = trabajadorService.updateTrabajador(id, trabajador);
        if (actualizado != null) {
            return new ResponseEntity<>(trabajadorModelAssembler.toModel(actualizado), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}