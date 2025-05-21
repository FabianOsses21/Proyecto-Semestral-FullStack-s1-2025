package com.grupo9.SppringApp004D.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private int precio;
}
