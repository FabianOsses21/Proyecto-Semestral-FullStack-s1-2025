package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Tienda;
import com.grupo9.SppringApp004D.Repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiendaService {

    @Autowired
    TiendaRepository tiendaRepository;


    public List<Tienda> getAllTiendas() {
        return tiendaRepository.findAll();
    }

    public Tienda getTienda(int id) {
        return tiendaRepository.findById(id).get();
    }

    public Tienda addTienda(Tienda tienda) {
        return tiendaRepository.save(tienda);
    }

    public void removeTienda(int id) {
        tiendaRepository.deleteById(id);
    }

    public Tienda updateTienda(int id, Tienda tienda) {
        Tienda ti =  tiendaRepository.findById(id).get();
        ti.setNombre(tienda.getNombre());
        ti.setComuna(tienda.getComuna());
        ti.setDireccion(tienda.getDireccion());
        tiendaRepository.save(ti);
        return ti;
    }
}
