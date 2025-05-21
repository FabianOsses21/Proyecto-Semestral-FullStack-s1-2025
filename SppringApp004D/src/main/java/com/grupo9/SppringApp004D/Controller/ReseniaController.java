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
    @GetMapping("/{idProducto}/{idUsuario}")
    public String getResenia(@PathVariable int idProducto, int idUsuario) {
        return reseniaService.getResenia(idProducto, idUsuario);
    }
    @GetMapping("/producto/{idProducto}")
    public String getReseniaByProducto(@PathVariable int idProducto) {
        return reseniaService.getReseniaByProducto(idProducto);
    }
    @GetMapping("/usuario/{idUsuario}")
    public String getReseniaByUsuario(@PathVariable int idUsuario) {
        return reseniaService.getReseniaByUsuario(idUsuario);
    }
    @PostMapping
    public String addResenia(@RequestBody Resenia resenia) {
        return reseniaService.addResenia(resenia);
    }
    @PutMapping("/{idProducto}/{idUsuario}")
    public String updateResenia(@PathVariable int idProducto, @PathVariable int idUsuario, @RequestBody Resenia resenia) {
        return reseniaService.updateResenia(idProducto, idUsuario, resenia);
    }
    @DeleteMapping("/{idProducto}/{idUsuario}")
    public String deleteResenia(@PathVariable int idProducto, @PathVariable int idUsuario) {
        return reseniaService.deleteResenia(idProducto, idUsuario);
    }



}
