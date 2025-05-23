package com.grupo9.SppringApp004D.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Clase Auxiliar para tener cantidad de cada producto de un pedido
@Entity
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    Pedido pedido;
    @ManyToOne
    Producto producto;
    int cantidad;
}
