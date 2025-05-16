package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Reclamo;
import com.grupo9.SppringApp004D.Repository.ReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReclamoService {
    @Autowired
    private ReclamoRepository reclamoRepository;

    public String getAllReclamos() {
        return reclamoRepository.getAllReclamos();
    }
    public String getReclamo(int id) {
        return reclamoRepository.getReclamo(id);
    }
    public String addReclamo(Reclamo reclamo) {
        return reclamoRepository.addReclamo(reclamo);
    }
    public String updateReclamo(int id, Reclamo reclamo) {
        return reclamoRepository.updateReclamo(id, reclamo);
    }
    public String deleteReclamo(int id) {
        return reclamoRepository.deleteReclamo(id);
    }
    public String getReclamoByUsuario(int idUsuario) {
        return reclamoRepository.getReclamosByIdUsuario(idUsuario);
    }
    public String getReclamoByEstado(String estado) {
        return reclamoRepository.getReclamosByEstado(estado);
    }
    public String getReclamoByUsuarioAndEstado(int idUsuario, String estado) {
        return reclamoRepository.getReclamosByIdUsuarioAndEstado(idUsuario, estado);
    }
}
