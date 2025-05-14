package com.grupo9.SppringApp004D.Repository;


import com.grupo9.SppringApp004D.Model.Tienda;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TiendaRepository {

    private List<Tienda> tiendas = new ArrayList<>();

    public TiendaRepository() {

    }

    public String getAllTiendas(){
        String output = "";
        for (Tienda temp: tiendas) {
            output += "Id de la tienda: " + temp.getId() + "\n";
            output += "Nombre: " + temp.getNombre() + "\n";
            output += "Direccion: " + temp.getDireccion() + "\n";
            output += "Comuna: " + temp.getComuna() + "\n";
        }
        if(output.isEmpty()){
            return "No hay tiendas encontradas con la id: "+id;
        }
        else{
            return output;
        }
    }

    public String getTienda(int id){
        String output = "";
        for (Tienda temp: tiendas) {
            if(temp.getId() == id){
                output += "Id de la tienda: " + temp.getId() + "\n";
                output += "Nombre: " + temp.getNombre() + "\n";
                output += "Direccion: " + temp.getDireccion() + "\n";
                output += "Comuna: " + temp.getComuna() + "\n";
                return output;
            }
        }
        return "No existe la tienda con id: "+id;
    }

    public String addTienda(Tienda tienda){
        tiendas.add(tienda);
        return "Tienda agregada correctamente";
    }

    public String removeTienda(int id){
        for (Tienda temp: tiendas){
            if(temp.getId() == id){
                tiendas.remove(temp);
                return "Tienda eliminada correctamente";
            }
        }
        return "No exite la tienda con id: "+id;
    }

    public String updateTienda(int id, Tienda tienda){
        int index = 0;
        for (Tienda temp: tiendas){
            if(temp.getId() == id){
                index = tiendas.indexOf(temp);
            }
        }
        if(index != -1){
            return "No existe la tienda";
        }else{
            tiendas.set(index, tienda);
            return "Tienda actualizada correctamente";
        }
    }
}
