package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Tienda;
import com.grupo9.SppringApp004D.Repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiendaService {

    @Autowired
    TiendaRepository tiendaRepository;


    public String getAllTiendas(){
        String output = "";
        for (Tienda temp: tiendaRepository.findAll()) {
            output += "Id de la tienda: " + temp.getId() + "\n";
            output += "Nombre: " + temp.getNombre() + "\n";
            output += "Direccion: " + temp.getDireccion() + "\n";
            output += "Comuna: " + temp.getComuna() + "\n";
        }
        if (output.isEmpty()) {
            return "No existen tiendas";
        } else {
            return output;
        }
    }

    public String getTienda(int id){
        String output = "";
        if (tiendaRepository.existsById(id)) {
            Tienda buscado = tiendaRepository.findById(id).get();
            output = "Id de la tienda: " + buscado.getId() + "\n";
            output += "Nombre: " + buscado.getNombre() + "\n";
            output += "Direccion: " + buscado.getDireccion() + "\n";
            output += "Comuna: " + buscado.getComuna() + "\n";
            return output;
        } else {
            return "No existe la tienda con id: " + id;
        }
    }

    public String addTienda(Tienda tienda){
        tiendaRepository.save(tienda);
        return "Tienda agregada correctamente";
    }

    public String removeTienda(int id){
        if (tiendaRepository.existsById(id)) {
            Tienda buscado = tiendaRepository.findById(id).get();
            tiendaRepository.delete(buscado);
            return "Tienda eliminada correctamente";
        } else {
            return "No existe la tienda con id: " + id;
        }
    }

    public String updateTienda(int id, Tienda tienda){
        if (tiendaRepository.existsById(id)) {
            Tienda buscado = tiendaRepository.findById(id).get();
            buscado.setNombre(tienda.getNombre());
            buscado.setDireccion(tienda.getDireccion());
            buscado.setComuna(tienda.getComuna());
            tiendaRepository.save(buscado);
            return "Tienda actualizada correctamente";
        } else {
            return "No existe la tienda con id: " + id;
        }
    }
}
