package com.grupo9.SppringApp004D.Repository;

import com.grupo9.SppringApp004D.Model.Cupones;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository

public class CuponesRepository {
    private List<Cupones> cupones = new ArrayList<>();

    public CuponesRepository(){

    }

    public String getAllCupones(){
        String output = " ";
        for (Cupones temp: cupones){
            output += output + ("Id del cupon" + temp.getIdCupon() + "\n");
            output += "Cupon" + temp.getProducto() + "\n";
            output += "Cantidad de descuento" + temp.getDescuento() + "\n";
            output += "Fecha valida del cupon" + temp.getFechaValida() + "\n";
        }
        if(output.isEmpty()){
            return "No se encontro el cupon";
        }else{
            return output;
        }
    }

    public String getCupon(int id){
        String output = " ";
        for (Cupones temp: cupones){
            if(temp.getIdCupon() == id){
                output += "Id del cupon" + temp.getIdCupon() + "\n";
                output += "Cupon" + temp.getProducto() + "\n";
                output += "Cantidad de descuento" + temp.getDescuento() + "\n";
                output += "Fecha valida del cupon" + temp.getFechaValida() + "\n";
                return output;
            }
        }
        return "No existe el cupon";
    }

    public String addCupones(Cupones cupon){
        cupones.add(cupon);
        return "Informacion agregada con exito";
    }

    public String removeCupones(int id){
        for(Cupones temp: cupones){
            if(temp.getIdCupon() == id){
                cupones.remove(temp);
                return "Informacion eliminada con exito";
            }
        }
        return "No existe la informacion o no esta escrito";
    }

    public String updateCupones(int id, Cupones cupon){
        int index = 0;
        for(Cupones temp: cupones){
            if(temp.getIdCupon() == id){
                index = cupones.indexOf(temp);
            }
        }
        if(index != -1){
            return "No existe la informacion";
        }else{
            cupones.set(index, cupon);
            return "Informacion actualizada";
        }

    }
}

