package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Reclamo;
import com.grupo9.SppringApp004D.Service.ReclamoService;
import com.grupo9.SppringApp004D.Assembler.ReclamoModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reclamo")
public class ReclamoController {
    @Autowired
    ReclamoService reclamoService;

    @Autowired
    ReclamoModelAssembler reclamoModelAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Reclamo>>> getAllReclamos() {
        List<Reclamo> lista = reclamoService.getAllReclamos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reclamoModelAssembler.toCollectionModel(lista), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Reclamo>> getReclamo(@PathVariable int id) {
        Reclamo reclamo = reclamoService.getReclamo(id);
        if (reclamo != null) {
            return new ResponseEntity<>(reclamoModelAssembler.toModel(reclamo), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Reclamo>> addReclamo(@RequestBody Reclamo reclamo) {
        reclamoService.addReclamo(reclamo);
        Reclamo creado = reclamoService.getReclamo(reclamo.getId());
        if (creado != null) {
            return new ResponseEntity<>(reclamoModelAssembler.toModel(creado), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Reclamo>> updateReclamo(@PathVariable int id, @RequestBody Reclamo reclamo) {
        if (reclamoService.getReclamo(id) != null) {
            reclamoService.updateReclamo(id, reclamo);
            Reclamo actualizado = reclamoService.getReclamo(id);
            return new ResponseEntity<>(reclamoModelAssembler.toModel(actualizado), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReclamo(@PathVariable int id) {
        if (reclamoService.getReclamo(id) != null) {
            reclamoService.removeReclamo(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}