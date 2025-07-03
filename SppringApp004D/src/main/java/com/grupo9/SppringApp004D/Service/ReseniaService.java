package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Resenia;
import com.grupo9.SppringApp004D.Repository.ReseniaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReseniaService {


    @Autowired
    ReseniaRepository reseniaRepository;
    public List<Resenia> getAllResenias() {
        return reseniaRepository.findAll();
    }

    public Resenia getResenia(int id) {
        return reseniaRepository.findById(id).get();
    }

    public Resenia addResenia(Resenia resenia) {
        return reseniaRepository.save(resenia);
    }

    public void removeResenia(int id) {
        reseniaRepository.deleteById(id);
    }

    public Resenia updateResenia(int id, Resenia resenia) {
        Resenia tr =  reseniaRepository.findById(id).get();
        tr.setId(resenia.getId());
        tr.setCalificacion(resenia.getCalificacion());
        tr.setComentario(resenia.getComentario());
        tr.getProducto().setId(resenia.getProducto().getId());
        return reseniaRepository.save(tr);
    }
}
