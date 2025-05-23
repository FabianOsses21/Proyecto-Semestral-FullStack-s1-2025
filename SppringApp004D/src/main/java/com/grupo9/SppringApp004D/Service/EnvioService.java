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
        String output = "";
        for(Envio temp: envioRepository.findAll()) {
            output = "Id del envio: " + temp.getId() + "\n";
            output += "Destinatario: " + temp.getUsuario().getNombre() + "\n";
            output += "Direccion de origen: " + temp.origen() + "\n";
            output += "Direccion de envio: " + temp.destino() + "\n";
            output += "Estado del envio " + temp.getEstado() + "\n";
            output += "Fecha de envio: " + temp.getFechaEnvio() + "\n";
            output += "Fecha de entrega estimada: " + temp.getFechaEntregaEstimada() + "\n";
            if (temp.getFechaEntregaReal() == null) {
                output += "Fecha de entrega real: No disponible\n";
            } else {
                output += "Fecha de entrega real: " + temp.getFechaEntregaReal() + "\n";
            }
        }
        if (output.isEmpty()) {
            return "No existen envios";
        } else {
            return output;
        }
    }

    public String getEnvio(int id) {
        String output = "";
        if (envioRepository.existsById(id)){
            Envio buscado = envioRepository.findById(id).get();
            output = "Id del envio: " + buscado.getId() + "\n";
            output += "Destinatario: " + buscado.getUsuario().getNombre() + "\n";
            output += "Direccion de origen: " + buscado.origen() + "\n";
            output += "Direccion de envio: " + buscado.destino() + "\n";
            output += "Estado del envio " + buscado.getEstado() + "\n";
            output += "Fecha de envio: " + buscado.getFechaEnvio() + "\n";
            output += "Fecha de entrega estimada: " + buscado.getFechaEntregaEstimada() + "\n";
            if (buscado.getFechaEntregaReal() == null) {
                output += "Fecha de entrega real: No disponible\n";
            } else {
                output += "Fecha de entrega real: " + buscado.getFechaEntregaReal() + "\n";
            }
            return output;
        }
        else{
            return "No existe el envio con id: " + id;
        }
    }
    public String addEnvio(Envio envio) {
        envioRepository.save(envio);
        return "Envio añadido con éxito";
    }
    public String updateEnvio(int id, Envio envio) {
        if (envioRepository.existsById(id)) {
            Envio buscado = envioRepository.findById(id).get();
            buscado.setUsuario(envio.getUsuario());
            buscado.setEstado(envio.getEstado());
            buscado.setFechaEnvio(envio.getFechaEnvio());
            buscado.setFechaEntregaEstimada(envio.getFechaEntregaEstimada());
            envioRepository.save(buscado);
            return "Envio actualizado con éxito";
        } else {
            return "No se encontró el envio con id: " + id;
        }
    }
    public String deleteEnvio(int id) {
        if (envioRepository.existsById(id)) {
            Envio buscado = envioRepository.findById(id).get();
            envioRepository.delete(buscado);
            return "Envio eliminado con éxito";
        } else {
            return "No se encontró el envio con id: " + id;
        }
    }

    public String getEnviosByUsuario(int id) {
        String output = "";
        for (Envio temp: envioRepository.findAll()) {
            if (temp.getUsuario().getId() == id) {
                output = "Id del envio: " + temp.getId() + "\n";
                output += "Destinatario: " + temp.getUsuario().getNombre() + "\n";
                output += "Direccion de origen: " + temp.origen() + "\n";
                output += "Direccion de envio: " + temp.destino() + "\n";
                output += "Estado del envio " + temp.getEstado() + "\n";
                output += "Fecha de envio: " + temp.getFechaEnvio() + "\n";
                output += "Fecha de entrega estimada: " + temp.getFechaEntregaEstimada() + "\n";
                if (temp.getFechaEntregaReal() == null) {
                    output += "Fecha de entrega real: No disponible\n";
                } else {
                    output += "Fecha de entrega real: " + temp.getFechaEntregaReal() + "\n";
                }
            }
        }if (output.isEmpty()) {
            return "No existen envios para el usuario con id: " + id;
        } else {
            return output;
        }
    }

    public String getEnviosByEstado(String estado) {
        String output = "";
        for (Envio temp: envioRepository.findAll()) {
            if (temp.getEstado().equalsIgnoreCase(estado)) {
                output = "Id del envio: " + temp.getId() + "\n";
                output += "Destinatario: " + temp.getUsuario().getNombre() + "\n";
                output += "Direccion de origen: " + temp.origen() + "\n";
                output += "Direccion de envio: " + temp.destino() + "\n";
                output += "Estado del envio " + temp.getEstado() + "\n";
                output += "Fecha de envio: " + temp.getFechaEnvio() + "\n";
                output += "Fecha de entrega estimada: " + temp.getFechaEntregaEstimada() + "\n";
                if (temp.getFechaEntregaReal() == null) {
                    output += "Fecha de entrega real: No disponible\n";
                } else {
                    output += "Fecha de entrega real: " + temp.getFechaEntregaReal() + "\n";
                }
            }
        }
        if (output.isEmpty()) {
            return "No existen envios con el estado: " + estado;
        } else {
            return output;
        }
    }

}
