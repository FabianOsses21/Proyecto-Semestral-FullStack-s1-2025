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
    private Cupones cupon;

    //Ahora si se pasa null como parametro, no se aplica cupón, si se pasa uno se aplica.
    public int calcularTotal() {
        int total = 0;
        for (DetallePedido detallePedido : productos) {
            total += detallePedido.getProducto().getPrecio() * detallePedido.getCantidad();
        }
        if (aplicaCupon()) {
            total = Math.toIntExact(Math.round(total * (1-getCupon().getDescuento())));
        }
        return total;
    }

    public boolean aplicaCupon() {
        java.util.Date hoy = new java.util.Date();
        if (getCupon() == null) {
            return false;
        }else{
            if (hoy.before(getCupon().getFechaValida()) || hoy.equals(getCupon().getFechaValida())) {
                return true;
            } else {
                return false;
            }
        }
    }
}
