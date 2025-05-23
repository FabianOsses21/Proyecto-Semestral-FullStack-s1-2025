package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Venta;
import com.grupo9.SppringApp004D.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService {
    @Autowired
    VentaRepository ventaRepository;

    public String getAllVentas() {
        String output = "";
        for (Venta temp : ventaRepository.findAll()) {
            output += "Id de la venta: " + temp.getId() + "\n";
            output += "Id del usuario: " + temp.getUsuario().getId() + "\n";
            output += "Id del pedido: " + temp.getPedido().getId() + "\n";
            output += "Metodo de pago: " + temp.getMetodoPago() + "\n";
            output += "Fecha de pago: " + temp.getFechaPago() + "\n";
            output += "Estado de la venta: " + temp.getEstado() + "\n";
            if (temp.getFechaReembolso() == null) {
                output += "Fecha de reembolso: No disponible\n";
            } else {
                output += "Fecha de reembolso: " + temp.getFechaReembolso() + "\n";
            }
        }
        if (output.isEmpty()) {
            return "No existen ventas";
        } else {
            return output;
        }
    }
    public String getVenta(int id) {
        String output = "";
        if (ventaRepository.existsById(id)) {
            Venta buscado = ventaRepository.findById(id).get();
            output = "Id de la venta: " + buscado.getId() + "\n";
            output += "Id del usuario: " + buscado.getUsuario().getId() + "\n";
            output += "Id del pedido: " + buscado.getPedido().getId() + "\n";
            output += "Metodo de pago: " + buscado.getMetodoPago() + "\n";
            output += "Fecha de pago: " + buscado.getFechaPago() + "\n";
            output += "Estado de la venta: " + buscado.getEstado() + "\n";
            if (buscado.getFechaReembolso() == null) {
                output += "Fecha de reembolso: No disponible\n";
            } else {
                output += "Fecha de reembolso: " + buscado.getFechaReembolso() + "\n";
            }
            return output;
        } else {
            return "No existe la venta con id: " + id;
        }
    }
    public String addVenta(Venta venta) {
        ventaRepository.save(venta);
        return "Venta agregada con exito!";
    }
    public String removeVenta(int id) {
        if (ventaRepository.existsById(id)) {
            Venta buscado = ventaRepository.findById(id).get();
            ventaRepository.delete(buscado);
            return "Venta eliminada con exito!";
        }
        else {
            return "No existe la venta con id: " + id;
        }
    }
    public String updateVenta(int id, Venta venta) {
        if(ventaRepository.existsById(id)) {
            Venta buscado = ventaRepository.findById(id).get();
            buscado.setUsuario(venta.getUsuario());
            buscado.setPedido(venta.getPedido());
            buscado.setMetodoPago(venta.getMetodoPago());
            buscado.setFechaPago(venta.getFechaPago());
            buscado.setEstado(venta.getEstado());
            buscado.setFechaReembolso(venta.getFechaReembolso());
            ventaRepository.save(buscado);
            return "Venta actualizada con exito!";
        }
        else {
            return "No existe la venta con id: " + id;
        }
    }
    public String getVentasByUsuario(int id) {
        String output = "";
        for (Venta temp : ventaRepository.findAll()) {
            if (temp.getUsuario().getId() == id) {
                output = "Id de la venta: " + temp.getId() + "\n";
                output += "Id del usuario: " + temp.getUsuario().getId() + "\n";
                output += "Id del pedido: " + temp.getPedido().getId() + "\n";
                output += "Metodo de pago: " + temp.getMetodoPago() + "\n";
                output += "Fecha de pago: " + temp.getFechaPago() + "\n";
                output += "Estado de la venta: " + temp.getEstado() + "\n";
                if (temp.getFechaReembolso() == null) {
                    output += "Fecha de reembolso: No disponible\n";
                } else {
                    output += "Fecha de reembolso: " + temp.getFechaReembolso() + "\n";
                }
            }
        }
        if (output.isEmpty()) {
            return "No se encontraron ventas para el usuario con id: " + id;
        } else {
            return output;
        }
    }
}
