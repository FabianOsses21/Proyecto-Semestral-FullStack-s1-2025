package com.grupo9.SppringApp004D.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Faltan que se creen las clases producto y tienda para terminar pedido
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private int id;
    private int total;
    private int estado;
    private String fecha;
    private int idUsuario;
}
