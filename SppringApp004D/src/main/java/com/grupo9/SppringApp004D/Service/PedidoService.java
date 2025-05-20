package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Pedido;
import com.grupo9.SppringApp004D.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;

    public String getAllPedidos() {

        return pedidoRepository.getAllPedidos();
    }
    public String getPedido(int id) {

        return pedidoRepository.getPedido(id);
    }
    public String addPedido(Pedido pedido) {
        int totalConDescuento = calcularTotalConDescuento(pedido);
        pedido.setTotal(totalConDescuento);
        return pedidoRepository.addPedido(pedido);
    }
    public String updatePedido(int id, Pedido pedido) {
        int totalConDescuento = calcularTotalConDescuento(pedido);
        pedido.setTotal(totalConDescuento);
        return pedidoRepository.updatePedido(id, pedido);
    }
    public String deletePedido(int id) {
        return pedidoRepository.deletePedido(id);
    }
    public int calcularTotal(Pedido pedido) {
        if (pedido.getDetalles() == null) return 0;

        return pedido.getDetalles().stream()
                .mapToInt(d -> d.getPrecioUnitario() * d.getCantidad())
                .sum();
    }

    public int calcularTotalConDescuento(Pedido pedido) {
        int total = calcularTotal(pedido);

        if (total > 1000) {
            return (int)(total * 0.9); // Aplica 10% de descuento
        }

        return total;
    }

}
