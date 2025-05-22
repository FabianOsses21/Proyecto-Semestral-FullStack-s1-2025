package com.grupo9.SppringApp004D.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Pasarlo a @Entity
/*
arriba de id:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


 */
public class Usuario {
    private int id;
    private String nombre;
    private String password;
    private String email;
    private String direccion;
}
