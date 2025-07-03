package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Cupones;
import com.grupo9.SppringApp004D.Model.Reclamo;
import com.grupo9.SppringApp004D.Repository.CuponesRepository;
import com.grupo9.SppringApp004D.Repository.ReclamoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReclamoService {
    @Autowired
    private ReclamoRepository reclamoRepository;

    public List<Reclamo> getAllReclamos() {
        return reclamoRepository.findAll();
    }

    public Reclamo getReclamo(int id) {
        return reclamoRepository.findById(id).get();
    }

    public Reclamo addReclamo(Reclamo reclamo) {
        return reclamoRepository.save(reclamo);
    }

    public Reclamo getReclamos(int id) {
        return reclamoRepository.findById(id).get();
    }

    public Reclamo addReclamos(Reclamo reclamo) {
        return reclamoRepository.save(reclamo);
    }

    public void removeReclamos(int id) {
        reclamoRepository.deleteById(id);
    }

    public Reclamo updateReclamos(int id, Reclamo reclamo) {
        Reclamo tr =  reclamoRepository.findById(id).get();
        tr.setId(reclamo.getId());
        tr.setDescripcion(reclamo.getDescripcion());
        tr.setEstado(reclamo.getEstado());
        tr.setFecha(reclamo.getFecha());
        tr.setUsuario(reclamo.getUsuario());
        return reclamoRepository.save(tr);
    }
}

