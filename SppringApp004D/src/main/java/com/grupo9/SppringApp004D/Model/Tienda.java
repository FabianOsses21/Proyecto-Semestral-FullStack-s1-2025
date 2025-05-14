package com.grupo9.SppringApp004D.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tienda {
    private int id;
    private String nombre;
    private String direccion;
    private String comuna;
}
