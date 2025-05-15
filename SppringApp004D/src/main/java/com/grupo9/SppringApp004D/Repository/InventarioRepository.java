package com.grupo9.SppringApp004D.Repository;

import com.grupo9.SppringApp004D.Model.Inventario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class InventarioRepository {
    private ArrayList<Inventario> listaInventario = new ArrayList<>();
    public InventarioRepository() {
    }

    public String getAllInventario() {
        String output = " ";
        for (Inventario temp: listaInventario) {
            output += "Id del producto: " + temp.getIdProducto() + "\n";
            output += "Id de la tienda: " + temp.getIdTienda() + "\n";
            output += "Cantidad: " + temp.getCantidad() + "\n";
            output += "Fecha de actualización: " + temp.getFechaActualizacion() + "\n";
        }
        if (!output.isEmpty()){
            return output;

        }else {
            return "No se encontró inventario de ningun producto en tienda";
        }
    }

    public String getInventario(int idProducto, int idTienda){
        String output = "";
        for (Inventario temp: listaInventario) {
            if (temp.getIdProducto() == idProducto && temp.getIdTienda() == idTienda) {
                output += "Id del producto: " + temp.getIdProducto() + "\n";
                output += "Id de la tienda: " + temp.getIdTienda() + "\n";
                output += "Cantidad: " + temp.getCantidad() + "\n";
                output += "Fecha de actualización: " + temp.getFechaActualizacion() + "\n";
            }
        }
        if (!output.isEmpty()){
            return output;

        }else {
            return "No existe la tienda en el inventario";
        }
    }
    public String addInventario(Inventario inventario) {
        listaInventario.add(inventario);
        return "Inventario añadido con éxito";
    }

    public String updateInventario(int idProducto, int idTienda, Inventario inventario) {
        for (int i = 0; i < listaInventario.size(); i++) {
            if (listaInventario.get(i).getIdProducto() == idProducto && listaInventario.get(i).getIdTienda() == idTienda) {
                listaInventario.set(i, inventario);
                return "Inventario modificado con éxito";
            }
        }
        return "No se encontró el inventario con id de Producto: " + idProducto + " y id de Tienda: "+ idTienda;
    }

    public String deleteInventario(int idProducto, int idTienda) {
        for (int i = 0; i < listaInventario.size(); i++) {
            if (listaInventario.get(i).getIdProducto() == idProducto && listaInventario.get(i).getIdTienda() == idTienda) {
                listaInventario.remove(i);
                return "Inventario eliminado con éxito";
            }
        }
        return "No se encontró el inventario con id de Producto: " + idProducto + " y id de Tienda: "+ idTienda;
    }

    public String getInventarioByTienda(int idTienda) {
        String output = "";
        for (Inventario temp: listaInventario) {
            if (temp.getIdTienda() == idTienda) {
                output += "Id del producto: " + temp.getIdProducto() + "\n";
                output += "Id de la tienda: " + temp.getIdTienda() + "\n";
                output += "Cantidad: " + temp.getCantidad() + "\n";
                output += "Fecha de actualización: " + temp.getFechaActualizacion() + "\n";
            }
        }
        if (!output.isEmpty()){
            return output;

        }else {
            return "No existe la tienda en el inventario";
        }
    }

    public String getInventarioByProducto(int idProducto) {
        String output = "";
        for (Inventario temp: listaInventario) {
            if (temp.getIdProducto() == idProducto) {
                output += "Id del producto: " + temp.getIdProducto() + "\n";
                output += "Id de la tienda: " + temp.getIdTienda() + "\n";
                output += "Cantidad: " + temp.getCantidad() + "\n";
                output += "Fecha de actualización: " + temp.getFechaActualizacion() + "\n";
            }
        }
        if (!output.isEmpty()){
            return output;

        }else {
            return "No existe el producto en el inventario";
        }
    }

}
