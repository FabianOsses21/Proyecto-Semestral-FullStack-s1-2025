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
        return ventaRepository.getAllVentas();
    }
    public String getVenta(int id) {
        return ventaRepository.getVenta(id);
    }
    public String addVenta(Venta venta) {
        return ventaRepository.addVenta(venta);
    }
    public String removeVenta(int id) {
        return ventaRepository.removeVenta(id);
    }
    public String updateVenta(int id, Venta venta) {
        return ventaRepository.updateVenta(id, venta);
    }
    public String getVentasByUsuario(int idUsuario) {
        return ventaRepository.getVentasByUsuario(idUsuario);
    }
}
