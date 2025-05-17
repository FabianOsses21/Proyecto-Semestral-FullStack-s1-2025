package com.grupo9.SppringApp004D.Repository;

import com.grupo9.SppringApp004D.Model.Reclamo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ReclamoRepository {
    private ArrayList<Reclamo> listaReclamos = new ArrayList<>();
    public ReclamoRepository() {
    }
    public String getAllReclamos(){
        String output = "";
        for (Reclamo temp: listaReclamos) {
            output += "Id del reclamo: " + temp.getId() + "\n";
            output += "Id del usuario: " + temp.getIdUsuario() + "\n";
            output += "Descripcion: " + temp.getDescripcion() + "\n";
            output += "Fecha: " + temp.getFecha() + "\n";
            output += "Estado: " + temp.getEstado() + "\n";
        }
        if (!output.isEmpty()){
            return output;

        }else {
            return "No se encontraron reclamos";
        }
    }
    public String getReclamo(int id){
        String output ="";
        for (Reclamo temp: listaReclamos) {
            if (temp.getId() == id) {
                output = "Id del reclamo: " + temp.getId() + "\n";
                output += "Id del usuario: " + temp.getIdUsuario() + "\n";
                output += "Descripcion: " + temp.getDescripcion() + "\n";
                output += "Fecha: " + temp.getFecha() + "\n";
                output += "Estado: " + temp.getEstado() + "\n";
                return output;
            }
        }
        return "No se encontro el reclamo con id: "+id;
    }
    public String addReclamo(Reclamo reclamo) {
        listaReclamos.add(reclamo);
        return "Reclamo añadido con éxito";
    }
    public String updateReclamo(int id, Reclamo reclamo) {
        for (int i = 0; i < listaReclamos.size(); i++) {
            if (listaReclamos.get(i).getId() == id) {
                listaReclamos.set(i, reclamo);
                return "Reclamo modificado con éxito";
            }
        }
        return "No se encontró el reclamo con id: "+id;
    }
    public String deleteReclamo(int id){
        for (int i = 0; i < listaReclamos.size(); i++) {
            if (listaReclamos.get(i).getId() == id) {
                listaReclamos.remove(i);
                return "Reclamo eliminado con éxito";
            }
        }
        return "No se encontró el reclamo con id: "+id;
    }
    public String getReclamosByIdUsuario(int idUsuario){
        String output = "";
        for (Reclamo temp: listaReclamos) {
            if (temp.getIdUsuario() == idUsuario) {
                output += "Id del reclamo: " + temp.getId() + "\n";
                output += "Id del usuario: " + temp.getIdUsuario() + "\n";
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
        for (Reclamo temp: listaReclamos) {
            if (temp.getEstado().equals(estado)) {
                output += "Id del reclamo: " + temp.getId() + "\n";
                output += "Id del usuario: " + temp.getIdUsuario() + "\n";
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
        for (Reclamo temp: listaReclamos) {
            if (temp.getIdUsuario() == idUsuario && temp.getEstado().equals(estado)) {
                output += "Id del reclamo: " + temp.getId() + "\n";
                output += "Id del usuario: " + temp.getIdUsuario() + "\n";
                output += "Descripcion: " + temp.getDescripcion() + "\n";
                output += "Fecha: " + temp.getFecha() + "\n";
                output += "Estado: " + temp.getEstado() + "\n";
            }
        }
        if (!output.isEmpty()){
            return output;

        }else {
            return "No se encontraron reclamos para el usuario con id: "+idUsuario+" y el estado: "+estado;
        }
    }
}
