package com.grupo9.SppringApp004D.Repository;

import org.springframework.stereotype.Repository;
import com.grupo9.SppringApp004D.Model.Venta;

import java.util.ArrayList;

@Repository
public class VentaRepository {
    private ArrayList<Venta> ventas = new ArrayList<>();
    public VentaRepository() {
    }

    public String getAllVentas() {
        String output = "";
        for (Venta temp: ventas) {
            output += "Id de la venta: " + temp.getId() + "\n";
            output += "Id del usuario: " + temp.getIdUsuario() + "\n";
            output += "Id del pedido: " + temp.getIdPedido() + "\n";
            output += "Metodo de pago: " + temp.getMetodoPago() + "\n";
            output += "Fecha de pago: " + temp.getFechaPago() + "\n";
            output += "Estado de la venta: " + temp.getEstado() + "\n";
            if (temp.getFechaReembolso() == null) {
                output += "Fecha de reembolso: No disponible\n";
            } else {
                output += "Fecha de reembolso: " + temp.getFechaReembolso() + "\n";
            }
        }
        if(output.isEmpty()){
            return "No se encontraron ventas";
        }
        else {
            return output;
        }
    }
    public String getVenta(int id) {
        String output = "";
        for (Venta temp: ventas) {
            if (temp.getId() == id) {
                output += "Id de la venta: " + temp.getId() + "\n";
                output += "Id del usuario: " + temp.getIdUsuario() + "\n";
                output += "Id del pedido: " + temp.getIdPedido() + "\n";
                output += "Metodo de pago: " + temp.getMetodoPago() + "\n";
                output += "Fecha de pago: " + temp.getFechaPago() + "\n";
                output += "Estado de la venta: " + temp.getEstado() + "\n";
                if (temp.getFechaReembolso() == null) {
                    output += "Fecha de reembolso: No disponible\n";
                } else {
                    output += "Fecha de reembolso: " + temp.getFechaReembolso() + "\n";
                }
                return output;
            }
        }
        return "No existe la venta";
    }
    public String addVenta(Venta venta) {
        ventas.add(venta);
        return "Venta agregada con exito!";
    }
    public String removeVenta(int id) {
        for (Venta temp: ventas) {
            if (temp.getId() == id) {
                ventas.remove(temp);
                return "Venta eliminada con exito";
            }
        }
        return "No existe la venta";
    }
    public String updateVenta(int id, Venta venta) {
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getId() == id) {
                ventas.set(i, venta);
                return "Venta actualizada con exito";
            }
        }
        return "No existe la venta";
    }
    public String getVentasByUsuario(int id) {
        String output = "";
        for (Venta temp: ventas) {
            if (temp.getIdUsuario() == id) {
                output += "Id de la venta: " + temp.getId() + "\n";
                output += "Id del usuario: " + temp.getIdUsuario() + "\n";
                output += "Id del pedido: " + temp.getIdPedido() + "\n";
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
        if(output.isEmpty()){
            return "No se encontraron ventas para el usuario con id: "+id;
        }else{
            return output;
        }
    }
}
