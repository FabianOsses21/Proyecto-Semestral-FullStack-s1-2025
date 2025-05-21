package com.grupo9.SppringApp004D.Repository;

import com.grupo9.SppringApp004D.Model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class PedidoRepository {
    private ArrayList<Pedido> listaPedidos = new ArrayList<>();
    public PedidoRepository() {
    }

    public String getAllPedidos() {
        String output = " ";
        for (Pedido temp: listaPedidos) {
            output += "Id del pedido: " + temp.getId() + "\n";
            output += "Lista de productos: " + temp.getProductos() + "\n";
            output += "Total del pedido: " + temp.calcularTotal() + "\n";
            output += "Estado del pedido: " + temp.getEstado() + "\n";
            output += "Fecha del pedido: " + temp.getFecha() + "\n";
            output += "Id del usuario: " + temp.getIdUsuario() + "\n";
        }
        if (!output.isEmpty()){
            return output;

        }else {
            return "No existen pedidos";
        }
    }
    public String getPedido(int id) {
        String output = "";
        for (Pedido temp: listaPedidos) {
            if (temp.getId() == id) {
                output = "Id del pedido: " + temp.getId() + "\n";
                output += "Lista de productos: " + temp.getProductos() + "\n";
                output += "Total del pedido: " + temp.calcularTotal() + "\n";
                output += "Estado del pedido: " + temp.getEstado() + "\n";
                output += "Fecha del pedido: " + temp.getFecha() + "\n";
                output += "Id del usuario: " + temp.getIdUsuario() + "\n";
                return output;
            }
        }
        return "No existe el pedido";
    }
    public String addPedido(Pedido pedido) {
        listaPedidos.add(pedido);
        return "Pedido añadido con éxito";
    }

    public String updatePedido(int id, Pedido pedido) {
        for (int i = 0; i < listaPedidos.size(); i++) {
            if (listaPedidos.get(i).getId() == id) {
                listaPedidos.set(i, pedido);
                return "Pedido actualizado con éxito";
            }
        }
        return "No se encontró el pedido con id: " + id;
    }

    public String deletePedido(int id) {
        for (int i = 0; i < listaPedidos.size(); i++) {
            if (listaPedidos.get(i).getId() == id) {
                listaPedidos.remove(i);
                return "Pedido eliminado con exito";
            }
        }
        return "No se encontró el pedido con id: " + id;
    }

}