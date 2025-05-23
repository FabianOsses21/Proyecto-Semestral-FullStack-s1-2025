package com.grupo9.SppringApp004D.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Inventario {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Producto producto;
    @ManyToOne
    private Tienda tienda;
    private int cantidad;
    private String fechaActualizacion;

}