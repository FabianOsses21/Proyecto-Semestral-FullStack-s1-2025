package com.grupo9.SppringApp004D.Repository;

import com.grupo9.SppringApp004D.Model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoRepository {

    private List<Producto> productos = new ArrayList<>();

    public ProductoRepository() {

    }

    public String getAllProductos() {
        String output = "";
        for (Producto temp: productos) {
            output += "Id del producto: " + temp.getId() + "\n";
            output += "Nombre del producto: " + temp.getNombre() + "\n";
            output += "Descripcion del producto: " + temp.getDescripcion() + "\n";
            output += "Cantidad del producto: " + temp.getCantidad() + "\n";
            output += "Precio del producto: " + temp.getPrecio() + "\n";
        }
        if(output.isEmpty()){
            return "No existe el producto con id: "+id;
        }
        else{
            return output;
        }
    }

    public String getProducto(int id) {
        String output = "";
        for (Producto temp: productos) {
            if(temp.getId() == id){
                output += "Id del producto: " + temp.getId() + "\n";
                output += "Nombre del producto: " + temp.getNombre() + "\n";
                output += "Descripcion del producto: " + temp.getDescripcion() + "\n";
                output += "Cantidad del producto: " + temp.getCantidad() + "\n";
                output += "Precio del producto: " + temp.getPrecio() + "\n";
                return output;
            }
        }
        return "No existe el producto con id: " + id;
    }

    public String addProducto(Producto producto) {
        productos.add(producto);
        return "Producto agregado correctamente";
    }

    public String removeProducto(int id) {
        for (Producto temp: productos) {
            if(temp.getId() == id){
                productos.remove(temp);
                return "Producto eliminado correctamente";
            }
        }
        return "No existe el producto con id: " +id;
    }

    public String updateProducto(int id, Producto producto) {
        int index = 0;
        for (Producto temp: productos) {
            if(temp.getId() == id){
                index= productos.indexOf(temp);
            }
        }
        if(index != -1){
            return "No existe el producto";
        }else{
            productos.set(index, producto);
            return "Producto actualizado correctamente";
        }
    }
}
