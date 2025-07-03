package com.grupo9.SppringApp004D;

import com.grupo9.SppringApp004D.Model.Venta;
import com.grupo9.SppringApp004D.Repository.VentaRepository;
import com.grupo9.SppringApp004D.Service.VentaService;
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
public class VentaTest {

    @Autowired
    private VentaRepository ventaRepository;

    @MockitoBean
    private VentaService ventaService;

    @Autowired
    MockMvc mockMvc;

    @Test //1
    public void createVentaTest() {
        Venta nuevaVenta = new Venta();
        nuevaVenta.setId(222);
        nuevaVenta.setUsuario(null);
        nuevaVenta.setPedido(null);
        nuevaVenta.setMetodoPago("Pago");
        nuevaVenta.setEstado("Pagado");
        nuevaVenta.setFechaPago("22-02-2025");
        nuevaVenta.setFechaReembolso("No Aplica");

        Mockito.when(ventaService.addVenta(Mockito.any(Venta.class))).thenReturn(nuevaVenta);

        Venta ventaGuardada = ventaService.addVenta(nuevaVenta);

        assertNotNull(ventaGuardada);
        assertEquals(222, ventaGuardada.getId());
    }

    @Test //2
    public void getAllVentasTest() {
        List<Venta> ventas = ventaRepository.findAll();
        assertNotNull(ventas);
        assertEquals(1, ventas.size());
    }

    @Test //3
    public void updateVentaTest() {
        Venta venta = new Venta();
        venta.setId(222);
        venta.setEstado("Pagado");
        venta.setFechaReembolso("No Aplica");
        Venta ventaActualizada = new Venta();
        ventaActualizada.setId(222);
        ventaActualizada.setEstado("Reembolsado");
        ventaActualizada.setFechaReembolso("02-07-2025");

        Mockito.when(ventaService.getVenta(222)).thenReturn(venta);
        Mockito.when(ventaService.addVenta(Mockito.any(Venta.class))).thenReturn(ventaActualizada);

        Venta ventaParaActualizar = ventaService.getVenta(222);
        ventaParaActualizar.setEstado("Reembolsado");
        ventaParaActualizar.setFechaReembolso("02-07-2025");
        Venta resultado = ventaService.addVenta(ventaParaActualizar);

        assertNotNull(resultado);
        assertEquals("Reembolsado", resultado.getEstado());
    }

    @Test //4
    public void getVentaByIdTest() {
        Venta venta = new Venta();
        venta.setId(222);
        Mockito.when(ventaService.getVenta(222)).thenReturn(venta);

        Venta ventaObtenida = ventaService.getVenta(222);

        assertNotNull(ventaObtenida);
        assertEquals(222, ventaObtenida.getId());
    }
    @Test //5
    public void getAllVentasControllerTest() throws Exception {
        Venta venta = new Venta();
        venta.setId(222);
        venta.setEstado("Pagado");
        Mockito.when(ventaService.getAllVentas()).thenReturn(List.of(venta));

        mockMvc.perform(get("/venta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(222))
                .andExpect(jsonPath("$[0].estado").value("Pagado"));
    }
}