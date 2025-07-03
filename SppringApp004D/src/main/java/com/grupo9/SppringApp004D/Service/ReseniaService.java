package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Resenia;
import com.grupo9.SppringApp004D.Repository.ReseniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReseniaService {
    @Autowired
    ReseniaRepository reseniaRepository;

    public List<Resenia> getAllResenias() {
        return reseniaRepository.findAll();
    }

    public Resenia getResenia(int id) {
        return reseniaRepository.findById(id).orElse(null);
    }

    public Resenia addResenia(Resenia resenia) {
        return reseniaRepository.save(resenia);
    }

    public void removeResenia(int id) {
        reseniaRepository.deleteById(id);
    }

    public Resenia updateResenia(int id, Resenia resenia) {
        Resenia re = reseniaRepository.findById(id).orElse(null);
        if (re != null) {
            re.setComentario(resenia.getComentario());
            re.setCalificacion(resenia.getCalificacion());
            re.setUsuario(resenia.getUsuario());
            re.setProducto(resenia.getProducto());
            reseniaRepository.save(re);
        }
        return re;
    }

    public List<Resenia> getReseniasByProducto(int idProducto) {
        return reseniaRepository.findAll()
                .stream()
                .filter(r -> r.getProducto().getId() == idProducto)
                .toList();
    }

    public List<Resenia> getReseniasByUsuario(int idUsuario) {
        return reseniaRepository.findAll()
                .stream()
                .filter(r -> r.getUsuario().getId() == idUsuario)
                .toList();
    }
}