package com.grupo9.SppringApp004D.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Proveedor {
    private int id;
    private String ProveNombre;
    private String ProveEmail;
    private String producto;
    private int TiendaNombre;
}
