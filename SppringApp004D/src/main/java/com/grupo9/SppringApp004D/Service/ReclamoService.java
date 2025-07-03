package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Reclamo;
import com.grupo9.SppringApp004D.Repository.ReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReclamoService {
    @Autowired
    ReclamoRepository reclamoRepository;

    public List<Reclamo> getAllReclamos() {
        return reclamoRepository.findAll();
    }

    public Reclamo getReclamo(int id) {
        return reclamoRepository.findById(id).orElse(null);
    }

    public Reclamo addReclamo(Reclamo reclamo) {
        return reclamoRepository.save(reclamo);
    }

    public void removeReclamo(int id) {
        reclamoRepository.deleteById(id);
    }

    public Reclamo updateReclamo(int id, Reclamo reclamo) {
        Reclamo re = reclamoRepository.findById(id).orElse(null);
        if (re != null) {
            re.setDescripcion(reclamo.getDescripcion());
            re.setEstado(reclamo.getEstado());
            re.setUsuario(reclamo.getUsuario());
            reclamoRepository.save(re);
        }
        return re;
    }

    public List<Reclamo> getReclamosByUsuario(int idUsuario) {
        return reclamoRepository.findAll()
                .stream()
                .filter(r -> r.getUsuario().getId() == idUsuario)
                .toList();
    }

    public List<Reclamo> getReclamosByEstado(String estado) {
        return reclamoRepository.findAll()
                .stream()
                .filter(r -> r.getEstado().equalsIgnoreCase(estado))
                .toList();
    }

    public List<Reclamo> getReclamosByUsuarioAndEstado(int idUsuario, String estado) {
        return reclamoRepository.findAll()
                .stream()
                .filter(r -> r.getUsuario().getId() == idUsuario && r.getEstado().equalsIgnoreCase(estado))
                .toList();
    }
}
