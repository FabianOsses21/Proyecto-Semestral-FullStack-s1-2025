package com.grupo9.SppringApp004D.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Pedido pedido;
    @ManyToOne
    private Usuario usuario;
    private String metodoPago; // Tarjeta de credito, debito, efectivo
    private String fechaPago;
    private String estado; // Pagado, reembolsado
    private String fechaReembolso;
}
