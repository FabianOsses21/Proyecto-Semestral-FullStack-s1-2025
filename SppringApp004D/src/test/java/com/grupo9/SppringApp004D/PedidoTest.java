package com.grupo9.SppringApp004D;

import com.grupo9.SppringApp004D.Model.Pedido;
import com.grupo9.SppringApp004D.Repository.PedidoRepository;
import com.grupo9.SppringApp004D.Service.PedidoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PedidoTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @MockitoBean
    private PedidoService pedidoService;

    @Autowired
    MockMvc mockMvc;

    @Test //1
    public void createPedidoTest() {
        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setId(222);
        nuevoPedido.setIdTienda(1);
        nuevoPedido.setProductos(null);
        nuevoPedido.setEstado(0);
        nuevoPedido.setFecha("01-01-2025");
        nuevoPedido.setUsuario(null);
        nuevoPedido.setCupon(null);

        Mockito.when(pedidoService.addPedido(Mockito.any(Pedido.class))).thenReturn(nuevoPedido);

        Pedido pedidoGuardado = pedidoService.addPedido(nuevoPedido);

        assertNotNull(pedidoGuardado);
        assertEquals(222, pedidoGuardado.getId());
    }

    @Test //2
    public void getAllPedidosTest() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
    }

    @Test //3
    public void updatePedidoTest() {
        Pedido pedido = new Pedido();
        pedido.setId(222);
        pedido.setEstado(0);
        pedido.setFecha("01-01-2025");
        Pedido pedidoActualizado = new Pedido();
        pedidoActualizado.setId(222);
        pedidoActualizado.setEstado(1);
        pedidoActualizado.setFecha("02-01-2025");

        Mockito.when(pedidoService.getPedido(222)).thenReturn(pedido);
        Mockito.when(pedidoService.addPedido(Mockito.any(Pedido.class))).thenReturn(pedidoActualizado);

        Pedido pedidoParaActualizar = pedidoService.getPedido(222);
        pedidoParaActualizar.setEstado(1);
        pedidoParaActualizar.setFecha("02-01-2025");
        Pedido resultado = pedidoService.addPedido(pedidoParaActualizar);

        assertNotNull(resultado);
        assertEquals(1, resultado.getEstado());
    }

    @Test //4
    public void getPedidoByIdTest() {
        Pedido pedido = new Pedido();
        pedido.setId(222);
        Mockito.when(pedidoService.getPedido(222)).thenReturn(pedido);

        Pedido pedidoObtenido = pedidoService.getPedido(222);

        assertNotNull(pedidoObtenido);
        assertEquals(222, pedidoObtenido.getId());
    }
    @Test //5
    public void getAllPedidosControllerTest() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId(222);
        pedido.setEstado(1);
        Mockito.when(pedidoService.getAllPedidos()).thenReturn(List.of(pedido));

        mockMvc.perform(get("/pedido"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(222))
                .andExpect(jsonPath("$[0].estado").value(1));
    }
}