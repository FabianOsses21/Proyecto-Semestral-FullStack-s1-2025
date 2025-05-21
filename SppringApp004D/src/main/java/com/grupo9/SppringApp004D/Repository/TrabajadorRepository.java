package com.grupo9.SppringApp004D.Repository;


import com.grupo9.SppringApp004D.Model.Trabajador;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrabajadorRepository {

    private List<Trabajador> trabajadors = new ArrayList<>();

    public TrabajadorRepository() {

    }

    public String getAllTrabajadores() {
        String output = "";
        for (Trabajador temp : trabajadors) {
            output += "Id del trabajador: " + temp.getId() + "\n";
            output += "Nombre del trabajador: " + temp.getNombre() + "\n";
            output += "Apellido del trabajador: " + temp.getApellido() + "\n";
            output += "Email del trabajador: " + temp.getEmail() + "\n";
            output += "Rol del trabajador: " + temp.getRol() + "\n";
            output += "Tienda del trabajador: " + temp.getTienda() + "\n";
        }
        if(output.isEmpty()){
            return "No se encontraron trabajadores";
        }
        else{
            return output;
        }

    }

    public String getTrabajador(int id) {
        String output = "";
        for (Trabajador temp : trabajadors) {
            if (temp.getId() == id) {
                output += "Id del trabajador " + temp.getId() + "\n";
                output += "Nombre del trabajador " + temp.getNombre() + "\n";
                output += "Apellido del trabajador " + temp.getApellido() + "\n";
                output += "Email del trabajador " + temp.getEmail() + "\n";
                output += "Rol del trabajador " + temp.getRol() + "\n";
                output += "Tienda del trabajador " + temp.getTienda() + "\n";
                return output;
            }
        }
        return "No existe el trabajador";
    }

    //Agregar trabajador
    public String addTrabajador(Trabajador trabajador) {
        trabajadors.add(trabajador);
        return "Trabajador agregado con existo!";
    }

    public String removeTrabajador(int id) {
        for (Trabajador temp : trabajadors) {
            if (temp.getId() == id) {
                trabajadors.remove(temp);
                return "Trabajador eliminado";
            }
        }
        return "No existe el trabajador";
    }

    public String updateTrabajador(int id, Trabajador trabajador) {
        int index = 0;
        for (Trabajador temp : trabajadors) {
            if (temp.getId() == id) {
                index = trabajadors.indexOf(temp);
            }
        }
        if (index != -1) {
            return "No existe el trabajador con id: " +id;
        }else{
            trabajadors.set(index, trabajador);
            return "Trabajador actualizado con exito!";
        }
    }
}
