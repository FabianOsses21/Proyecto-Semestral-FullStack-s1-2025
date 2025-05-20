package com.grupo9.SppringApp004D.Controller;


import com.grupo9.SppringApp004D.Model.Producto;
import com.grupo9.SppringApp004D.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping
    public String getAllProductos(){
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public String getProductosById(@PathVariable int id){
        return productoService.getProductoById(id);
    }

    @PostMapping
    public String addProducto(@RequestBody Producto producto){
        return productoService.addProducto(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProductoById(@PathVariable int id){
        return productoService.deleteProducto(id);
    }

    @PutMapping("/{id}")
    public String updateProducto(@PathVariable int id, @RequestBody Producto producto){
        return productoService.updateProducto(id, producto);
    }
}
