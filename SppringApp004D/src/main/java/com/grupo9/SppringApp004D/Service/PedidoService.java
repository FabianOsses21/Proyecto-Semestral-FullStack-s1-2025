package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Pedido;
import com.grupo9.SppringApp004D.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido getPedido(int id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido addPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void removePedido(int id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido updatePedido(int id, Pedido pedido) {
        Pedido pe = pedidoRepository.findById(id).orElse(null);
        if (pe != null) {
            pe.setIdTienda(pedido.getIdTienda());
            pe.setProductos(pedido.getProductos());
            pe.setEstado(pedido.getEstado());
            pe.setFecha(pedido.getFecha());
            pe.setUsuario(pedido.getUsuario());
            pe.setCupon(pedido.getCupon());
            pedidoRepository.save(pe);
        }
        return pe;
    }

    public List<Pedido> getPedidosByUsuario(int idUsuario) {
        return pedidoRepository.findAll()
                .stream()
                .filter(p -> p.getUsuario() != null && p.getUsuario().getId() == idUsuario)
                .toList();
    }
}