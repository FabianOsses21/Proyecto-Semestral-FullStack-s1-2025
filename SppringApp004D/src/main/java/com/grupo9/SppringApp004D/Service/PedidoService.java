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
        String output = "";
        for (Pedido temp: pedidoRepository.findAll()) {
            output += "Id del pedido: " + temp.getId() + "\n";
            output += "Lista de productos: " + temp.getProductos() + "\n";
            output += "Total del pedido: " + temp.calcularTotal() + "\n";
            output += "Estado del pedido: " + temp.getEstado() + "\n";
            output += "Fecha del pedido: " + temp.getFecha() + "\n";
            output += "Id del usuario: " + temp.getUsuario().getId() + "\n";
        }
        if(output.isEmpty()){
            return "No se encontraron pedidos";
        }
        else {
            return output;
        }
    }
    public String getPedido(int id) {
        String output = "";
        if (pedidoRepository.existsById(id)) {
            Pedido buscado = pedidoRepository.findById(id).get();
            output = "Id del pedido: " + buscado.getId() + "\n";
            output += "Lista de productos: " + buscado.getProductos() + "\n";
            output += "Total del pedido: " + buscado.calcularTotal() + "\n";
            output += "Estado del pedido: " + buscado.getEstado() + "\n";
            output += "Fecha del pedido: " + buscado.getFecha() + "\n";
            output += "Id del usuario: " + buscado.getUsuario().getId() + "\n";
            return output;
        }
        else{
            return "No existe el pedido con id: " + id;
        }
    }
    public String addPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
        return "Pedido añadido con éxito";
    }

    public String updatePedido(int id, Pedido pedido) {
        if (pedidoRepository.existsById(id)) {
            Pedido buscado = pedidoRepository.findById(id).get();
            buscado.setProductos(pedido.getProductos());
            buscado.setEstado(pedido.getEstado());
            buscado.setFecha(pedido.getFecha());
            pedidoRepository.save(buscado);
            return "Pedido actualizado con éxito";
        } else {
            return "No se encontró el pedido con id: " + id;
        }
    }

    public String deletePedido(int id) {
        if (pedidoRepository.existsById(id)) {
            Pedido buscado = pedidoRepository.findById(id).get();
            pedidoRepository.delete(buscado);
            return "Pedido eliminado con éxito";
        } else {
            return "No se encontró el pedido con id: " + id;
        }
    }
}
