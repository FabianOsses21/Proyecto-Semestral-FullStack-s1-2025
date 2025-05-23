package com.grupo9.SppringApp004D.Repository;

import com.grupo9.SppringApp004D.Model.Resenia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReseniaRepository extends JpaRepository<Resenia, Integer> {
}
