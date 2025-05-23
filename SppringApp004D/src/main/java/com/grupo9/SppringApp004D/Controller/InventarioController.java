package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Inventario;
import com.grupo9.SppringApp004D.Service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventario")
public class InventarioController {
    @Autowired
    InventarioService inventarioService;

    @GetMapping
    public String getAllInventario() {
        return inventarioService.getAllInventario();
    }

    @GetMapping("/{id}")
    public String getInventario(@PathVariable int id) {
        return inventarioService.getInventario(id);
    }

    @GetMapping("/tienda/{idTienda}")
    public String getInventarioByTienda(@PathVariable int idTienda) {
        return inventarioService.getInventarioByTienda(idTienda);
    }
    @GetMapping("/producto/{idProducto}")
    public String getInventarioByProducto(@PathVariable int idProducto) {
        return inventarioService.getInventarioByProducto(idProducto);
    }

    @PostMapping
    public String addInventario(@RequestBody Inventario inventario) {
        return inventarioService.addInventario(inventario);
    }

    @PutMapping("/{id}")
    public String updateInventario(@PathVariable int id, @RequestBody Inventario inventario) {
        return inventarioService.updateInventario(id, inventario);
    }

    @DeleteMapping("/{id}")
    public String deleteInventario(@PathVariable int id) {
        return inventarioService.deleteInventario(id);
    }

}
