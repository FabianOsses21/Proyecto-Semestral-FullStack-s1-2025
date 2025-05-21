package com.grupo9.SppringApp004D.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Envio {
    private int id;
    private Pedido pedido;
    private Usuario usuario;
    private Tienda tienda;
    private String estado; // En camino, entregado, cancelado
    private String fechaEnvio;
    private String fechaEntregaEstimada;
    private String fechaEntregaReal;

    public String origen() {
        return tienda.getDireccion();
    }

    public String destino() {
        return usuario.getDireccion();
    }
}
