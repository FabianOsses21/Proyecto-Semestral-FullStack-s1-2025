package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Pedido;
import com.grupo9.SppringApp004D.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public String getAllPedidos() {
        return pedidoService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public String getPedido(@PathVariable int id) {
        return pedidoService.getPedido(id);
    }

    @PostMapping
    public String addPedido(@RequestBody Pedido pedido) {
        return pedidoService.addPedido(pedido);
    }

    @PutMapping("/{id}")
    public String updatePedido(@PathVariable int id, @RequestBody Pedido pedido) {
        return pedidoService.updatePedido(id, pedido);
    }

    @DeleteMapping("/{id}")
    public String deletePedido(@PathVariable int id) {
        return pedidoService.deletePedido(id);
    }
}
