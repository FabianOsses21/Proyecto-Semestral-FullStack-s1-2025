package com.grupo9.SppringApp004D.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    private int idTienda;
    @OneToMany(mappedBy= "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> productos;
    private int estado;
    private String fecha;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
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
    java.time.LocalDate hoy = java.time.LocalDate.now();
    if (getCupon() == null) {
        return false;
    } else {
        java.time.LocalDate fechaValida = getCupon().getFechaValida();
        if (hoy.isBefore(fechaValida) || hoy.isEqual(fechaValida)) {
            return true;
        } else {
            return false;
        }
    }
}
}
