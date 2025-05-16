package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Reclamo;
import com.grupo9.SppringApp004D.Service.ReclamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reclamo")
public class ReclamoController {
    @Autowired
    ReclamoService reclamoService;

    @GetMapping
    public String getAllReclamos() {
        return reclamoService.getAllReclamos();
    }
    @GetMapping("/{id}")
    public String getReclamo(@PathVariable int id) {
        return reclamoService.getReclamo(id);
    }
    @GetMapping("/usuario/{idUsuario}")
    public String getReclamoByUsuario(@PathVariable int idUsuario) {
        return reclamoService.getReclamoByUsuario(idUsuario);
    }
    @GetMapping("/estado/{estado}")
    public String getReclamoByEstado(@PathVariable String estado) {
        return reclamoService.getReclamoByEstado(estado);
    }
    @GetMapping("/usuario/{idUsuario}/estado/{estado}")
    public String getReclamoByUsuarioAndEstado(@PathVariable int idUsuario, @PathVariable String estado) {
        return reclamoService.getReclamoByUsuarioAndEstado(idUsuario, estado);
    }
    @PostMapping
    public String addReclamo(@RequestBody Reclamo reclamo) {
        return reclamoService.addReclamo(reclamo);
    }
    @PutMapping("/{id}")
    public String updateReclamo(@PathVariable int id, @RequestBody Reclamo reclamo) {
        return reclamoService.updateReclamo(id, reclamo);
    }
    @DeleteMapping("/{id}")
    public String deleteReclamo(@PathVariable int id) {
        return reclamoService.deleteReclamo(id);
    }
}
