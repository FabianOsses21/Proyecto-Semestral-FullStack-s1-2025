package com.grupo9.SppringApp004D.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Clase Auxiliar para tener cantidad de cada producto de un pedido
public class DetallePedido {
    int idPedido;
    Producto producto;
    int cantidad;
}
