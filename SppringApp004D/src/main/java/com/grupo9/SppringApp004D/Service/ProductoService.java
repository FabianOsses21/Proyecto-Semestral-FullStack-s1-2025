package com.grupo9.SppringApp004D.Service;


import com.grupo9.SppringApp004D.Model.Producto;
import com.grupo9.SppringApp004D.Model.Usuario;
import com.grupo9.SppringApp004D.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProducto(int id) {
        return productoRepository.findById(id).get();
    }

    public Producto addProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void removeProducto(int id) {
        productoRepository.deleteById(id);
    }

    public Producto updateProducto(int id, Producto producto) {
        Producto pr =  productoRepository.findById(id).get();
        pr.setNombre(producto.getNombre());
        pr.setPrecio(producto.getPrecio());
        productoRepository.save(pr);
        return pr;
    }
}