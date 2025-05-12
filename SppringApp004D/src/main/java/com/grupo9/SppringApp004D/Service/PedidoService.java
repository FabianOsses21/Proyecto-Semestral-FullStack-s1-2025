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
        return pedidoRepository.addPedido(pedido);
    }
    public String updatePedido(int id, Pedido pedido) {
        return pedidoRepository.updatePedido(id, pedido);
    }
    public String deletePedido(int id) {
        return pedidoRepository.deletePedido(id);
    }

}
