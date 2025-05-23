package com.grupo9.SppringApp004D.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Proveedor {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String ProveRut;
    private String ProveNombre;
    private String ProveEmail;
    @ManyToMany
    private List<Producto> productos;
}
