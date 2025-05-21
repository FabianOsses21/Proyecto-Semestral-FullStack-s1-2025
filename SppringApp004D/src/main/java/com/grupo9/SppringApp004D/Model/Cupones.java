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
    private String producto;
    private int descuento;
    private Date fechaValida;
}
    