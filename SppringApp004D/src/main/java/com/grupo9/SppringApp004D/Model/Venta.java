package com.grupo9.SppringApp004D.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venta {
    private int id;
    private int idPedido;
    private int idUsuario;
    private String metodoPago; // Tarjeta de credito, debito, efectivo
    private String fechaPago;
    private String estado; // Pagado, reembolsado
    private String fechaReembolso;
}
