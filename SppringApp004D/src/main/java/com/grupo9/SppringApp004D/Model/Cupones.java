package com.grupo9.SppringApp004D.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@NoArgsConstructor
@Data
@AllArgsConstructor

@Entity
public class Cupones {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;

    private String descripcion;
    //Es Decimal, valores entre 0 y 1
    private double descuento;
    private java.time.LocalDate fechaValida;
}
    