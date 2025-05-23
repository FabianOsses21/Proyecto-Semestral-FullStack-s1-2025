package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Reclamo;
import com.grupo9.SppringApp004D.Repository.ReclamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReclamoService {
    @Autowired
    private ReclamoRepository reclamoRepository;

    public String getAllReclamos(){
        String output = "";
        for (Reclamo temp: reclamoRepository.findAll()) {
            output += "Id del reclamo: " + temp.getId() + "\n";
            output += "Id del usuario: " + temp.getUsuario().getId() + "\n";
            output += "Descripcion: " + temp.getDescripcion() + "\n";
            output += "Fecha: " + temp.getFecha() + "\n";
            output += "Estado: " + temp.getEstado() + "\n";
        }
        if (output.isEmpty()) {
            return "No existen reclamos";
        } else {
            return output;
        }
    }
    public String getReclamo(int id){
        String output ="";
        if (reclamoRepository.existsById(id)) {
            Reclamo buscado = reclamoRepository.findById(id).get();
            output += "Id del reclamo: " + buscado.getId() + "\n";
            output += "Id del usuario: " + buscado.getUsuario().getId() + "\n";
            output += "Descripcion: " + buscado.getDescripcion() + "\n";
            output += "Fecha: " + buscado.getFecha() + "\n";
            output += "Estado: " + buscado.getEstado() + "\n";
            return output;
        } else {
            return "No existe el reclamo con id: "+id;
        }
    }
    public String addReclamo(Reclamo reclamo) {
        reclamoRepository.save(reclamo);
        return "Reclamo añadido con éxito";
    }
    public String updateReclamo(int id, Reclamo reclamo) {
        if (reclamoRepository.existsById(id)) {
            Reclamo buscado = reclamoRepository.findById(id).get();
            buscado.setDescripcion(reclamo.getDescripcion());
            buscado.setEstado(reclamo.getEstado());
            reclamoRepository.save(buscado);
            return "Reclamo actualizado con éxito";
        } else {
            return "No se encontró el reclamo con id: "+id;
        }
    }
    public String deleteReclamo(int id){
        if (reclamoRepository.existsById(id)) {
            Reclamo buscado = reclamoRepository.findById(id).get();
            reclamoRepository.delete(buscado);
            return "Reclamo eliminado con éxito";
        } else {
            return "No se encontró el reclamo con id: "+id;
        }
    }
    public String getReclamosByIdUsuario(int idUsuario){
        String output = "";
        for (Reclamo temp: reclamoRepository.findAll()) {
            if (temp.getUsuario().getId() == idUsuario) {
                output += "Id del reclamo: " + temp.getId() + "\n";
                output += "Id del usuario: " + temp.getUsuario().getId() + "\n";
                output += "Descripcion: " + temp.getDescripcion() + "\n";
                output += "Fecha: " + temp.getFecha() + "\n";
                output += "Estado: " + temp.getEstado() + "\n";
            }
        }
        if (!output.isEmpty()){
            return output;

        }else {
            return "No se encontraron reclamos para el usuario con id: "+idUsuario;
        }
    }
    public String getReclamosByEstado(String estado){
        String output = "";
        for (Reclamo temp: reclamoRepository.findAll()) {
            if (temp.getEstado().equals(estado)) {
                output += "Id del reclamo: " + temp.getId() + "\n";
                output += "Id del usuario: " + temp.getUsuario().getId() + "\n";
                output += "Descripcion: " + temp.getDescripcion() + "\n";
                output += "Fecha: " + temp.getFecha() + "\n";
                output += "Estado: " + temp.getEstado() + "\n";
            }
        }
        if (!output.isEmpty()){
            return output;
        }else {
            return "No se encontraron reclamos con el estado: "+estado;
        }
    }
    public String getReclamosByIdUsuarioAndEstado(int idUsuario, String estado){
        String output = "";
        for (Reclamo temp: reclamoRepository.findAll()) {
            if (temp.getUsuario().getId() == idUsuario && temp.getEstado().equals(estado)) {
                output += "Id del reclamo: " + temp.getId() + "\n";
                output += "Id del usuario: " + temp.getUsuario().getId() + "\n";
                output += "Descripcion: " + temp.getDescripcion() + "\n";
                output += "Fecha: " + temp.getFecha() + "\n";
                output += "Estado: " + temp.getEstado() + "\n";
            }
        }
        if (!output.isEmpty()){
            return output;
        }else {
            return "No se encontraron reclamos para el usuario con id: "+idUsuario+" y estado: "+estado;
        }
    }
}
