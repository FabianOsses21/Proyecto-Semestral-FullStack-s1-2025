package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo9.SppringApp004D.Model.Inventario;
@Service
public class InventarioService {
    @Autowired
    InventarioRepository inventarioRepository;

    public String getAllInventario() {
        String output = "";
        for (Inventario temp: inventarioRepository.findAll()){
            output += "Id del inventario: " +temp.getId()+ "\n";
            output += "Id del producto: " +temp.getProducto().getId()+"\n";
            output += "Id de la tienda: " +temp.getTienda().getId()+"\n";
            output += "Cantidad: " +temp.getCantidad()+"\n";
            output += "Fecha de actualizacion"+ temp.getFechaActualizacion()+"\n";
        }
        if (!output.isEmpty()){
            return output;
        }else{
            return "No se encontraron inventarios registrados";
        }
    }

    public String getInventario(int id){
        String output = "";
        if(inventarioRepository.existsById(id)){
            Inventario buscado = inventarioRepository.findById(id).get();
            output += "Id del inventario: " + buscado.getId() + "\n";
            output += "Id del producto: " + buscado.getProducto().getId() + "\n";
            output += "Id de la tienda: " + buscado.getTienda().getId() + "\n";
            output += "Cantidad: " + buscado.getCantidad() + "\n";
            output += "Fecha de actualizacion"+ buscado.getFechaActualizacion()+"\n";
            return output;
        }else{
            return "No existe el inventario con id: "+id;
        }
    }
    public String addInventario(Inventario inventario) {
        inventarioRepository.save(inventario);
        return "Inventario añadido con éxito";
    }

    public String updateInventario(int id, Inventario inventario) {
        if(inventarioRepository.existsById(id)){
            Inventario buscado = inventarioRepository.findById(id).get();
            buscado.setProducto(inventario.getProducto());
            buscado.setTienda(inventario.getTienda());
            buscado.setCantidad(inventario.getCantidad());
            buscado.setFechaActualizacion(inventario.getFechaActualizacion());
            return "Inventario Actualizado con exito";
        }
        else{
            return "Inventario con id: " + id + " No existe";
        }
    }

    public String deleteInventario(int id) {
        if(inventarioRepository.existsById(id)){
            Inventario buscado = inventarioRepository.findById(id).get();
            inventarioRepository.delete(buscado);
            return "Inventario eliminado con exito";
        }
        else{
            return "Inventario con id: "+id+ " no existe";
        }
    }

    public String getInventarioByTienda(int idTienda) {
        String output = "";
        for (Inventario temp: inventarioRepository.findAll()) {
            if (temp.getTienda().getId() == idTienda) {
                output += "Id del producto: " + temp.getProducto().getId() + "\n";
                output += "Id de la tienda: " + temp.getTienda().getId() + "\n";
                output += "Cantidad: " + temp.getCantidad() + "\n";
                output += "Fecha de actualización: " + temp.getFechaActualizacion() + "\n";
            }
        }if (!output.isEmpty()){
            return output;
        }else {
            return "No existe el inventario en la tienda";
        }
    }

    public String getInventarioByProducto(int idProducto) {
        String output = "";
        for (Inventario temp: inventarioRepository.findAll()) {
            if (temp.getProducto().getId() == idProducto) {
                output += "Id del producto: " + temp.getProducto().getId() + "\n";
                output += "Id de la tienda: " + temp.getTienda().getId() + "\n";
                output += "Cantidad: " + temp.getCantidad() + "\n";
                output += "Fecha de actualización: " + temp.getFechaActualizacion() + "\n";
            }
        }
        if (!output.isEmpty()){
            return output;
        }else {
            return "No existe el inventario para el producto";
        }
    }
}
