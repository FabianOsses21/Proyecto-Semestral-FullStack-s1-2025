package com.grupo9.SppringApp004D.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Trabajador {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String rol;
    private int tienda;
}
