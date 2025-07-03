package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Envio;
import com.grupo9.SppringApp004D.Repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvioService {
    @Autowired
    private EnvioRepository envioRepository;


    public List<Envio> getAllEnvios() {
        return envioRepository.findAll();
    }

    public Envio getEnvio(int id) {
        return envioRepository.findById(id).get();
    }

    public Envio addEnvio(Envio envio) {
        return envioRepository.save(envio);
    }

    public void removeEnvio(int id) {
        envioRepository.deleteById(id);
    }

    public Envio updateEnvio(int id, Envio envio) {
        Envio en =  envioRepository.findById(id).get();
        en.setPedido(envio.getPedido());
        en.setUsuario(envio.getUsuario());
        en.setTienda(envio.getTienda());
        en.setFechaEnvio(envio.getFechaEnvio());
        en.setFechaEntregaEstimada(envio.getFechaEntregaEstimada());
        en.setFechaEntregaReal(envio.getFechaEntregaReal());
        envioRepository.save(en);
        return en;
    }

}
