package com.grupo9.SppringApp004D.Service;


import com.grupo9.SppringApp004D.Model.Trabajador;
import com.grupo9.SppringApp004D.Repository.TrabajadorRepository;
import com.grupo9.SppringApp004D.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajadorService {

    @Autowired
    TrabajadorRepository trabajadorRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String addTrabajador(Trabajador trabajador){
        return trabajadorRepository.addTrabajador(trabajador);
    }

    public String deleteTrabajador(int id){
        trabajadorRepository.removeTrabajador(id);
        return "Trabajador eliminado";
    }

    public String getAllTrabajadores(){
        return trabajadorRepository.getAllTrabajadores();
    }

    public String getTrabajadorById(int id){
        return trabajadorRepository.getTrabajador(id);
    }

    public String updateTrabajador(int id, Trabajador trabajador){
        return trabajadorRepository.updateTrabajador(id, trabajador);
    }
}
