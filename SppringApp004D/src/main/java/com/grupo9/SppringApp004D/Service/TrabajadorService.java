package com.grupo9.SppringApp004D.Service;


import com.grupo9.SppringApp004D.Model.Trabajador;
import com.grupo9.SppringApp004D.Repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajadorService {

    @Autowired
    TrabajadorRepository trabajadorRepository;

    public String getAllTrabajadores() {
        String output = "";
        for (Trabajador temp : trabajadorRepository.findAll()) {
            output += "Id del trabajador: " + temp.getId() + "\n";
            output += "Nombre del trabajador: " + temp.getNombre() + "\n";
            output += "Apellido del trabajador: " + temp.getApellido() + "\n";
            output += "Email del trabajador: " + temp.getEmail() + "\n";
            output += "Rol del trabajador: " + temp.getRol() + "\n";
            output += "Tienda del trabajador: " + temp.getTienda() + "\n";
        }
        if (output.isEmpty()) {
            return "No existen trabajadores";
        } else {
            return output;
        }
    }

    public String getTrabajador(int id) {
        String output = "";
        if (trabajadorRepository.existsById(id)) {
            Trabajador buscado = trabajadorRepository.findById(id).get();
            output = "Id del trabajador: " + buscado.getId() + "\n";
            output += "Nombre del trabajador: " + buscado.getNombre() + "\n";
            output += "Apellido del trabajador: " + buscado.getApellido() + "\n";
            output += "Email del trabajador: " + buscado.getEmail() + "\n";
            output += "Rol del trabajador: " + buscado.getRol() + "\n";
            output += "Tienda del trabajador: " + buscado.getTienda() + "\n";
            return output;
        }
        else {
            return "No existe el trabajador con id: " + id;
        }
    }

    //Agregar trabajador
    public String addTrabajador(Trabajador trabajador) {
        trabajadorRepository.save(trabajador);
        return "Trabajador agregado con existo!";
    }

    public String removeTrabajador(int id) {
        if (trabajadorRepository.existsById(id)) {
            Trabajador buscado = trabajadorRepository.findById(id).get();
            trabajadorRepository.delete(buscado);
            return "Trabajador eliminado con exito!";
        } else {
            return "No existe el trabajador con id: " + id;
        }
    }

    public String updateTrabajador(int id, Trabajador trabajador) {
        if (trabajadorRepository.existsById(id)) {
            Trabajador buscado = trabajadorRepository.findById(id).get();
            buscado.setNombre(trabajador.getNombre());
            buscado.setApellido(trabajador.getApellido());
            buscado.setEmail(trabajador.getEmail());
            buscado.setRol(trabajador.getRol());
            buscado.setTienda(trabajador.getTienda());
            trabajadorRepository.save(buscado);
            return "Trabajador actualizado con exito!";
        } else {
            return "No existe el trabajador con id: " + id;
        }
    }
}
