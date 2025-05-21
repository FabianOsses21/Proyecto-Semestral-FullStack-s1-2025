package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Cupones;
import com.grupo9.SppringApp004D.Repository.CuponesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CuponesService {
    @Autowired
    CuponesRepository cuponesRepository;

    public String addCupones(Cupones cupones){
        return cuponesRepository.addCupones(cupones);
    }

    public String deleteCupones(int id){
        cuponesRepository.removeCupones(id);
        return "Cupones eliminados correctamente";
    }

    public String getAllCupones(){
        return cuponesRepository.getAllCupones();
    }

    public String getCuponesById(int id){
        return cuponesRepository.getCupon(id);
    }

    public String updateCupon(int id, Cupones cupones){
        return cuponesRepository.updateCupones(id, cupones);
    }

}
