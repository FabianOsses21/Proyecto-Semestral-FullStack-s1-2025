package com.grupo9.SppringApp004D.Service;


import com.grupo9.SppringApp004D.Model.Producto;
import com.grupo9.SppringApp004D.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public String addProducto(Producto producto){
        return productoRepository.addProducto(producto);
    }

    public String deleteProducto(int id){
        productoRepository.removeProducto(id);
        return "Producto eliminado con exito";
    }

    public String getAllProductos(){
        return productoRepository.getAllProductos();
    }

    public String getProductoById(int id){
        return productoRepository.getProducto(id);
    }

    public String updateProducto(int id, Producto producto){
        return productoRepository.updateProducto(id, producto);
    }
}
