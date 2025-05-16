package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Resenia;
import com.grupo9.SppringApp004D.Repository.ReseniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReseniaService {
    @Autowired
    ReseniaRepository reseniaRepository;

    public String getAllResenias() {
        return reseniaRepository.getAllResenias();
    }
    public String getResenia(int idProducto, int idUsuario) {
        return reseniaRepository.getResenia(idProducto, idUsuario);
    }
    public String addResenia(Resenia resenia) {
        return reseniaRepository.addResenia(resenia);
    }
    public String updateResenia(int idProducto, int idUsuario, Resenia resenia) {
        return reseniaRepository.updateResenia(idProducto, idUsuario, resenia);
    }
    public String deleteResenia(int idProducto, int idUsuario) {
        return reseniaRepository.deleteResenia(idProducto, idUsuario);
    }
    public String getReseniaByProducto(int idProducto) {
        return reseniaRepository.getReseniasByIdProducto(idProducto);
    }
    public String getReseniaByUsuario(int idUsuario) {
        return reseniaRepository.getReseniasByIdUsuario(idUsuario);
    }
}
