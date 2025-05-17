package com.grupo9.SppringApp004D.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reclamo {
    private int id;
    private int idUsuario;
    private String descripcion;
    private String fecha;
    private String estado;
}
