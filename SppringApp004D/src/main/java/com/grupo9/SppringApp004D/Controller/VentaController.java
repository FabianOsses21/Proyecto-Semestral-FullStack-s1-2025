package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Venta;
import com.grupo9.SppringApp004D.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    VentaService ventaService;

    @GetMapping
    public String getAllVentas() {
        return ventaService.getAllVentas();
    }
    @GetMapping("/{id}")
    public String getVenta(@PathVariable int id) {
        return ventaService.getVenta(id);
    }
    @GetMapping("/usuario/{idUsuario}")
    public String getVentasByUsuario(@PathVariable int idUsuario) {
        return ventaService.getVentasByUsuario(idUsuario);
    }
    @PostMapping
    public String addVenta(@RequestBody Venta venta) {
        return ventaService.addVenta(venta);
    }
    @DeleteMapping("/{id}")
    public String removeVenta(@PathVariable int id) {
        return ventaService.removeVenta(id);
    }
    @PutMapping("/{id}")
    public String updateVenta(@PathVariable int id, @RequestBody Venta venta) {
        return ventaService.updateVenta(id, venta);
    }
}
