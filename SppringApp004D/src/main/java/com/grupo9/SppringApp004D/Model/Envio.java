package com.grupo9.SppringApp004D.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Envio {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Pedido pedido;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
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
