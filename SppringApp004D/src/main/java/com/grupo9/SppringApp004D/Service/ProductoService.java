package com.grupo9.SppringApp004D.Service;


import com.grupo9.SppringApp004D.Model.Producto;
import com.grupo9.SppringApp004D.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public String getAllProductos() {
        String output = "";
        for (Producto temp: productoRepository.findAll()) {
            output += "Id del producto: " + temp.getId() + "\n";
            output += "Nombre del producto: " + temp.getNombre() + "\n";
            output += "Descripcion del producto: " + temp.getDescripcion() + "\n";
            output += "Precio del producto: " + temp.getPrecio() + "\n";
        }
        if (output.isEmpty()) {
            return "No existen productos";
        } else {
            return output;
        }
    }

    public String getProducto(int id) {
        String output = "";
        if (productoRepository.existsById(id)) {
            Producto buscado = productoRepository.findById(id).get();
            output += "Id del producto: " + buscado.getId() + "\n";
            output += "Nombre del producto: " + buscado.getNombre() + "\n";
            output += "Descripcion del producto: " + buscado.getDescripcion() + "\n";
            output += "Precio del producto: " + buscado.getPrecio() + "\n";
            return output;
        } else {
            return "No existe el producto con id: " + id;
        }
    }

    public String addProducto(Producto producto) {
        productoRepository.save(producto);
        return "Producto agregado correctamente";
    }

    public String removeProducto(int id) {
        if (productoRepository.existsById(id)) {
            Producto buscado = productoRepository.findById(id).get();
            productoRepository.delete(buscado);
            return "Producto eliminado correctamente";
        } else {
            return "No existe el producto con id: " + id;
        }
    }

    public String updateProducto(int id, Producto producto) {
        if (productoRepository.existsById(id)) {
            Producto buscado = productoRepository.findById(id).get();
            buscado.setNombre(producto.getNombre());
            buscado.setDescripcion(producto.getDescripcion());
            buscado.setPrecio(producto.getPrecio());
            productoRepository.save(buscado);
            return "Producto actualizado correctamente";
        } else {
            return "No existe el producto con id: " + id;
        }
    }
}