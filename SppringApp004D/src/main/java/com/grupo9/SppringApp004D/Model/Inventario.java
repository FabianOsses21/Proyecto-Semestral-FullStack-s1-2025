package com.grupo9.SppringApp004D.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {
    private int idProducto;
    private int idTienda;
    private int cantidad;
    private String fechaActualizacion;

}