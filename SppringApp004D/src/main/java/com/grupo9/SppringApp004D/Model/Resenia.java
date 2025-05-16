package com.grupo9.SppringApp004D.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Resenia {
    private int idProducto;
    private String comentario;
    private int calificacion;
    private int idUsuario;
}
