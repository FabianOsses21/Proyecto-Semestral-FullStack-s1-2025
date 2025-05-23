package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Proveedor;
import com.grupo9.SppringApp004D.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proveedor")

public class ProveedorController {

    @Autowired
    ProveedorService proveedorService;

    @GetMapping
    public String getAllProveedores(){
        return proveedorService.getAllProveedores();
    }

    @GetMapping("/{id}")
    public String getProveedorById(@PathVariable int id){
        return proveedorService.getProveedor(id);
    }

    @PostMapping
    public String addProveedor(@RequestBody Proveedor proveedor){
        return proveedorService.addProveedor(proveedor);
    }

    @DeleteMapping("/{id}")
    public String deleteProveedorById(@PathVariable int id){
        return proveedorService.removeProveedor(id);

    }

    @PutMapping("/{id}")
    public String updateProveedor(@PathVariable int id, @RequestBody Proveedor proveedor){
        return proveedorService.updateProveedor(id, proveedor);
    }
}
