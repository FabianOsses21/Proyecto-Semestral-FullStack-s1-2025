package com.grupo9.SppringApp004D.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

// Todavia falta cupón para terminarla, por lo demas esta bien.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private int id;
    private int idTienda;
    private ArrayList<DetallePedido> productos;
    private int estado;
    private String fecha;
    private int idUsuario;

    public int calcularTotal() {
        int total = 0;
        for (DetallePedido detallePedido : productos) {
            total += detallePedido.getProducto().getPrecio() * detallePedido.getCantidad();
        }
        //Este IF va a cambiar cuando la clase cupon este lista, usara el metodo de abajo
        if (total > 1000) {
            total = (int) (total * 0.9);
        }
        return total;
    }

/*
    public boolean AplicaCupon(Cupon cupon){
        if (cupon.getEstado == "activo"){
            return true;
        }else{
            return false;
        }
    }
*/
}
