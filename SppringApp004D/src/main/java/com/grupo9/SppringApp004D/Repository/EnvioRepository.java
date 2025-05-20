package com.grupo9.SppringApp004D.Repository;

import com.grupo9.SppringApp004D.Model.Envio;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class EnvioRepository {
    private ArrayList<Envio> envios = new ArrayList<>();

    public EnvioRepository() {
    }

    public String getAllEnvios() {
        String output = "";
        for (Envio temp: envios) {
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
        if(output.isEmpty()){
            return "No se encontraron envios";
        }
        else {
            return output;
        }
    }

    public String getEnvio(int id) {
        String output = "";
        for (Envio temp: envios) {
            if (temp.getId() == id) {
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
                return output;
            }
        }
        return "No existe el envio";
    }
    public String addEnvio(Envio envio) {
        envios.add(envio);
        return "Envio añadido con éxito";
    }
    public String updateEnvio(int id, Envio envio) {
        for (int i = 0; i < envios.size(); i++) {
            if (envios.get(i).getId() == id) {
                envios.set(i, envio);
                return "Envio actualizado con éxito";
            }
        }
        return "No se encontró el envio con id: " + id;
    }
    public String deleteEnvio(int id) {
        for (int i = 0; i < envios.size(); i++) {
            if (envios.get(i).getId() == id) {
                envios.remove(i);
                return "Envio eliminado con exito";
            }
        }
        return "No se encontró el envio con id: " + id;
    }

    public String getEnviosByUsuario(int id) {
        String output = "";
        for (Envio temp: envios) {
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
        }
        if(output.isEmpty()){
            return "No se encontraron envios para el usuario con id: "+id;
        }
        else {
            return output;
        }
    }

    public String getEnviosByEstado(String estado) {
        String output = "";
        for (Envio temp: envios) {
            if (temp.getEstado().equals(estado)) {
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
        if(output.isEmpty()){
            return "No se encontraron envios con el estado: "+estado;
        }
        else {
            return output;
        }
    }

}
