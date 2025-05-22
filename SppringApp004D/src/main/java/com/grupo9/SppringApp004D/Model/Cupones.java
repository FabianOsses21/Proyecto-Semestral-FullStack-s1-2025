package com.grupo9.SppringApp004D.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor

public class Cupones {
    private int idCupon;
    private String descripcion;
    //Es Decimal, valores entre 0 y 1
    private double descuento;
    private Date fechaValida;
}
    