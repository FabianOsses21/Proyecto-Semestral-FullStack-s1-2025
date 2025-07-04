package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Resenia;
import com.grupo9.SppringApp004D.Service.ReseniaService;
import com.grupo9.SppringApp004D.Assembler.ReseniaModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/resenia")
public class ReseniaController {
    @Autowired
    ReseniaService reseniaService;

    @Autowired
    ReseniaModelAssembler reseniaModelAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Resenia>>> getAllResenias() {
        List<Resenia> lista = reseniaService.getAllResenias();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reseniaModelAssembler.toCollectionModel(lista), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Resenia>> getResenia(@PathVariable int id) {
        Resenia resenia = reseniaService.getResenia(id);
        if (resenia != null) {
            return new ResponseEntity<>(reseniaModelAssembler.toModel(resenia), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Resenia>> addResenia(@RequestBody Resenia resenia) {
        reseniaService.addResenia(resenia);
        Resenia creada = reseniaService.getResenia(resenia.getId());
        if (creada != null) {
            return new ResponseEntity<>(reseniaModelAssembler.toModel(creada), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Resenia>> updateResenia(@PathVariable int id, @RequestBody Resenia resenia) {
        if (reseniaService.getResenia(id) != null) {
            reseniaService.updateResenia(id, resenia);
            Resenia actualizada = reseniaService.getResenia(id);
            return new ResponseEntity<>(reseniaModelAssembler.toModel(actualizada), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResenia(@PathVariable int id) {
        if (reseniaService.getResenia(id) != null) {
            reseniaService.removeResenia(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}