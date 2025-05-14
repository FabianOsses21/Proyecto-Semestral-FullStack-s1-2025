package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Tienda;
import com.grupo9.SppringApp004D.Repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiendaService {

    @Autowired
    TiendaRepository tiendaRepository;

    public String addTienda(Tienda tienda){
        return tiendaRepository.addTienda(tienda);
    }

    public String deleteTienda(int id){
        tiendaRepository.removeTienda(id);
        return "Tienda eliminada correctamente";
    }

    public String getAllTiendas(){
        return tiendaRepository.getAllTiendas();
    }

    public String getTiendaById(int id){
        return tiendaRepository.getTienda(id);
    }

    public String updateTienda(int id, Tienda tienda){
        return tiendaRepository.updateTienda(id, tienda);
    }
}
