package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Cupones;
import com.grupo9.SppringApp004D.Model.Tienda;
import com.grupo9.SppringApp004D.Repository.CuponesRepository;
import com.grupo9.SppringApp004D.Repository.TiendaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuponesService {

    @Autowired
    CuponesRepository cuponesRepository;
    public List<Cupones> getAllCupones() {
        return cuponesRepository.findAll();
    }

    public Cupones getCupones(int id) {
        return cuponesRepository.findById(id).get();
    }

    public Cupones addCupones(Cupones cupones) {
        return cuponesRepository.save(cupones);
    }

    public void removeCupones(int id) {
        cuponesRepository.deleteById(id);
    }

    public Cupones updateCupones(int id, Cupones cupones) {
        Cupones tr =  cuponesRepository.findById(id).get();
        tr.setId(cupones.getId());
        tr.setDescripcion(cupones.getDescripcion());
        tr.setDescuento(cupones.getDescuento());
        tr.setFechaValida(cupones.getFechaValida());
        return tr;
    }
}




