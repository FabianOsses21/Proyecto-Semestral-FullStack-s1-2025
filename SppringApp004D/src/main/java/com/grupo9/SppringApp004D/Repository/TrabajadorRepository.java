package com.grupo9.SppringApp004D.Repository;


import com.grupo9.SppringApp004D.Model.Trabajador;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TrabajadorRepository {

    private List<Trabajador> listaTrabajadores = new ArrayList<>();

    public TrabajadorRepository() {

    }

    public String getAllTrabajadores() {
        String output = "";
        for (Trabajador temp : listaTrabajadores) {
            output += "Id del trabajador" + temp.getId() + "\n";
            output += "Nombre del trabajador" + temp.getNombre() + "\n";
            output += "Apellido del trabajador" + temp.getApellido() + "\n";
            output += "Email del trabajador" + temp.getEmail() + "\n";
            output += "Rol del trabajador" + temp.getRol() + "\n";
            output += "Tienda del trabajador" + temp.getTienda() + "\n";
        }
        if(output.isEmpty()){
            return "No se encontro el trabajador";
        }
        else{
            return output;
        }

    }

    public String getTrabajador(int id) {
        String output = "";
        for (Trabajador temp : listaTrabajadores) {
            if (temp.getId() == id) {
                output += "Id del trabajador" + temp.getId() + "\n";
                output += "Nombre del trabajador" + temp.getNombre() + "\n";
                output += "Apellido del trabajador" + temp.getApellido() + "\n";
                output += "Email del trabajador" + temp.getEmail() + "\n";
                output += "Rol del trabajador" + temp.getRol() + "\n";
                output += "Tienda del trabajador" + temp.getTienda() + "\n";
                return output;
            }
        }
        return "No existe el trabajador";
    }

    //Agregar trabajador
    public String addTrabajador(Trabajador trabajador) {
        listaTrabajadores.add(trabajador);
        return "Trabajador agregado con existo!";
    }

    public String removeTrabajador(int id) {
        for (Trabajador temp : listaTrabajadores) {
            if (temp.getId() == id) {
                listaTrabajadores.remove(temp);
                return "Trabajador eliminado";
            }
        }
    }

    public String updateTrabajador(int id, Trabajador trabajador) {
        int index = 0;
        for (Trabajador temp : listaTrabajadores) {
            if (temp.getId() == id) {
                index = listaTrabajadores.indexOf(temp);
            }
        }
        if (index != -1) {
            return "No existe el trabajador con id: " +id;
        }else{
            listaTrabajadores.set(index, trabajador);
            return "Trabajador actualizado con exito!";
        }
    }
}
