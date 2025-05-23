package com.grupo9.SppringApp004D.Repository;

import com.grupo9.SppringApp004D.Model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
}