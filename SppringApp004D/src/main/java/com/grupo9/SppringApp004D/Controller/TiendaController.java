package com.grupo9.SppringApp004D.Controller;


import com.grupo9.SppringApp004D.Model.Tienda;
import com.grupo9.SppringApp004D.Service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tienda")
public class TiendaController {

    @Autowired
    TiendaService tiendaService;

    @GetMapping
    public String getAllTiendas(){
        return tiendaService.getAllTiendas();
    }

    @GetMapping
    public String getTiendaById(@PathVariable int id){
        return tiendaService.getTiendaById(id);
    }

    @PostMapping
    public String addTienda(@RequestBody Tienda tienda){
        return tiendaService.addTienda(tienda);
    }

    @DeleteMapping("/{id}")
    public String deleteTienda(@PathVariable int id){
        return tiendaService.deleteTienda(id);
    }

    @PutMapping("/{id}")
    public String updateTienda(@PathVariable int id, @RequestBody Tienda tienda){
        return tiendaService.updateTienda(id, tienda);
    }
}
