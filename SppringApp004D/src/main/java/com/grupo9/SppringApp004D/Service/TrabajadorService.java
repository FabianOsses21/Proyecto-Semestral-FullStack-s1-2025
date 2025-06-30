package com.grupo9.SppringApp004D.Service;


import com.grupo9.SppringApp004D.Model.Trabajador;
import com.grupo9.SppringApp004D.Repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorService {

    @Autowired
    TrabajadorRepository trabajadorRepository;
    public List<Trabajador> getAllTrabajadores() {
        return trabajadorRepository.findAll();
    }

    public Trabajador getTrabajador(int id) {
        return trabajadorRepository.findById(id).get();
    }

    public Trabajador addTrabajador(Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }

    public void removeTrabajador(int id) {
        trabajadorRepository.deleteById(id);
    }

    public Trabajador updateTrabajador(int id, Trabajador trabajador) {
        Trabajador tr =  trabajadorRepository.findById(id).get();
        tr.setNombre(trabajador.getNombre());
        tr.setApellido(trabajador.getApellido());
        tr.setEmail(trabajador.getEmail());
        tr.setRol(trabajador.getRol());
        tr.setTienda(trabajador.getTienda());
        trabajadorRepository.save(tr);
        return tr;
    }
}
