package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Resenia;
import com.grupo9.SppringApp004D.Service.ReseniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resenia")
public class ReseniaController {
    @Autowired
    ReseniaService reseniaService;

    @GetMapping
    public String getAllResenias() {
        return reseniaService.getAllResenias();
    }
    @GetMapping("/{id}")
    public String getResenia(@PathVariable int id) {
        return reseniaService.getResenia(id);
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
    public String addResenia(@RequestBody Resenia resenia) {
        return reseniaService.addResenia(resenia);
    }
    @PutMapping("/{id}")
    public String updateResenia(@PathVariable int id, @RequestBody Resenia resenia) {
        return reseniaService.updateResenia(id, resenia);
    }
    @DeleteMapping("/{id}")
    public String deleteResenia(@PathVariable int id) {
        return reseniaService.deleteResenia(id);
    }



}
