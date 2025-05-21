package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Envio;
import com.grupo9.SppringApp004D.Repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {
    @Autowired
    private EnvioRepository envioRepository;

    public String getAllEnvios() {
        return envioRepository.getAllEnvios();
    }

    public String getEnvio(int id) {
        return envioRepository.getEnvio(id);
    }

    public String addEnvio(Envio envio) {
        return envioRepository.addEnvio(envio);
    }

    public String updateEnvio(int id, Envio envio) {
        return envioRepository.updateEnvio(id, envio);
    }

    public String deleteEnvio(int id) {
        return envioRepository.deleteEnvio(id);
    }

    public String getEnviosByUsuario(int id) {
        return envioRepository.getEnviosByUsuario(id);
    }

    public String getEnviosByEstado(String estado) {
        return envioRepository.getEnviosByEstado(estado);
    }

}
