package com.grupo9.SppringApp004D.Repository;

import com.grupo9.SppringApp004D.Model.Cupones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CuponesRepository extends JpaRepository<Cupones, Integer> {
}

