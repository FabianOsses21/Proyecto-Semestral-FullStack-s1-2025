package com.grupo9.SppringApp004D.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedido {
    private Producto producto;
    private int cantidad;
    private int precioUnitario;
}
