package com.grupo9.SppringApp004D.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.grupo9.SppringApp004D.Model.Venta;


@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
}
