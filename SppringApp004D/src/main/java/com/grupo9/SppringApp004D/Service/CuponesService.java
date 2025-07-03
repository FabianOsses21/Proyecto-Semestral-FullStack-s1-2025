package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Cupones;
import com.grupo9.SppringApp004D.Repository.CuponesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CuponesService {
    @Autowired
    CuponesRepository cuponesRepository;

    public List<Cupones> getAllCupones() {
        return cuponesRepository.findAll();
    }

    public Cupones getCupon(int id) {
        return cuponesRepository.findById(id).orElse(null);
    }

    public Cupones addCupones(Cupones cupon) {
        return cuponesRepository.save(cupon);
    }

    public void removeCupon(int id) {
        cuponesRepository.deleteById(id);
    }

    public Cupones updateCupon(int id, Cupones cupon) {
        Cupones cu = cuponesRepository.findById(id).orElse(null);
        if (cu != null) {
            cu.setDescripcion(cupon.getDescripcion());
            cu.setDescuento(cupon.getDescuento());
            cu.setFechaValida(cupon.getFechaValida());
            cuponesRepository.save(cu);
        }
        return cu;
    }
}