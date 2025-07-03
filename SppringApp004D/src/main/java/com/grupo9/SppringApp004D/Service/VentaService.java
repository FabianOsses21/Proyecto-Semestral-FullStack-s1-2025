package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Venta;
import com.grupo9.SppringApp004D.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {
    @Autowired
    VentaRepository ventaRepository;

    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }
    public Venta getVenta(int id) {
        return ventaRepository.findById(id).get();
    }
    public Venta addVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public void removeVenta(int id) {
        ventaRepository.deleteById(id);
    }

    public Venta updateVenta(int id, Venta venta) {
        Venta ve =  ventaRepository.findById(id).get();
        ve.setPedido(venta.getPedido());
        ve.setUsuario(venta.getUsuario());
        ve.setMetodoPago(venta.getMetodoPago());
        ve.setFechaPago(venta.getFechaPago());
        ve.setEstado(venta.getEstado());
        ve.setFechaReembolso(venta.getFechaReembolso());
        ventaRepository.save(ve);
        return ve;
    }
    public List<Venta> getVentasByUsuario(int id) {
        return ventaRepository.findAll()
                .stream()
                .filter(v -> v.getUsuario().getId() == id)
                .toList();
    }
}
