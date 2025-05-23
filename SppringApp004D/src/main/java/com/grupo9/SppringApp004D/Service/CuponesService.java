package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Cupones;
import com.grupo9.SppringApp004D.Repository.CuponesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CuponesService {
    @Autowired
    CuponesRepository cuponesRepository;


    public String getAllCupones(){
        String output = "";
        for (Cupones temp: cuponesRepository.findAll()){
            output += "Id del cupon" + temp.getId() + "\n";
            output += "Descripcion" + temp.getDescripcion() + "\n";
            output += "Cantidad de descuento" + temp.getDescuento() + "\n";
            output += "Fecha valida del cupon" + temp.getFechaValida() + "\n";
        }
        if (output.isEmpty()){
            return "No existen cupones";
        }else{
            return output;
        }
    }

    public String getCupon(int id){
        String output = " ";
        if (cuponesRepository.existsById(id)){
            Cupones buscado = cuponesRepository.findById(id).get();
            output += "Id del cupon" + buscado.getId() + "\n";
            output += "Descripcion" + buscado.getDescripcion() + "\n";
            output += "Cantidad de descuento" + buscado.getDescuento() + "\n";
            output += "Fecha valida del cupon" + buscado.getFechaValida() + "\n";
            return output;
        }
        else{
            return "No existe el cupon con id: " + id;
        }
    }

    public String addCupones(Cupones cupon){
        cuponesRepository.save(cupon);
        return "Informacion agregada con exito";
    }

    public String removeCupones(int id){
        for(Cupones temp: cuponesRepository.findAll()){
            if(temp.getId() == id){
                cuponesRepository.delete(temp);
                return "Informacion eliminada con exito";
            }
        }
        return "No existe la informacion o no esta escrito";
    }

    public String updateCupones(int id, Cupones cupon){
        if (cuponesRepository.existsById(id)){
            Cupones buscado = cuponesRepository.findById(id).get();
            buscado.setDescripcion(cupon.getDescripcion());
            buscado.setDescuento(cupon.getDescuento());
            buscado.setFechaValida(cupon.getFechaValida());
            cuponesRepository.save(buscado);
            return "Informacion actualizada con exito";
        }else{
            return "No existe la informacion o no esta escrito";
        }
    }
}
