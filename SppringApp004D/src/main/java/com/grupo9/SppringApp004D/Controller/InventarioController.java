package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Inventario;
import com.grupo9.SppringApp004D.Service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventario")
public class InventarioController {
    @Autowired
    InventarioService inventarioService;

    @GetMapping
    public String getAllInventario() {
        return inventarioService.getAllInventario();
    }

    @GetMapping("/{idProducto}/{idTienda}")
    public String getInventario(@PathVariable int idProducto, @PathVariable int idTienda) {
        return inventarioService.getInventario(idProducto, idTienda);
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

    @PutMapping("/{idProducto}/{idTienda}")
    public String updateInventario(@PathVariable int idProducto, @PathVariable int idTienda, @RequestBody Inventario inventario) {
        return inventarioService.updateInventario(idProducto, idTienda, inventario);
    }

    @DeleteMapping("/{idProducto}/{idTienda}")
    public String deleteInventario(@PathVariable int idProducto, @PathVariable int idTienda) {
        return inventarioService.deleteInventario(idProducto, idTienda);
    }

}
