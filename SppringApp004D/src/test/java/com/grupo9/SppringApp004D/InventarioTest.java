package com.grupo9.SppringApp004D;

import com.grupo9.SppringApp004D.Model.Inventario;
import com.grupo9.SppringApp004D.Repository.InventarioRepository;
import com.grupo9.SppringApp004D.Service.InventarioService;
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
public class InventarioTest {

    @Autowired
    private InventarioRepository inventarioRepository;

    @MockitoBean
    private InventarioService inventarioService;

    @Autowired
    MockMvc mockMvc;

    @Test //1
    public void createInventarioTest() {
        Inventario nuevoInventario = new Inventario();
        nuevoInventario.setId(222);
        nuevoInventario.setProducto(null);
        nuevoInventario.setTienda(null);
        nuevoInventario.setCantidad(10);
        nuevoInventario.setFechaActualizacion("01-01-2025");

        Mockito.when(inventarioService.addInventario(Mockito.any(Inventario.class))).thenReturn(nuevoInventario);

        Inventario inventarioGuardado = inventarioService.addInventario(nuevoInventario);

        assertNotNull(inventarioGuardado);
        assertEquals(222, inventarioGuardado.getId());
    }

    @Test //2
    public void getAllInventariosTest() {
        List<Inventario> inventarios = inventarioRepository.findAll();
        assertNotNull(inventarios);
        assertEquals(1, inventarios.size());
    }

    @Test //3
    public void updateInventarioTest() {
        Inventario inventario = new Inventario();
        inventario.setId(222);
        inventario.setCantidad(10);
        inventario.setFechaActualizacion("01-01-2025");
        Inventario inventarioActualizado = new Inventario();
        inventarioActualizado.setId(222);
        inventarioActualizado.setCantidad(20);
        inventarioActualizado.setFechaActualizacion("02-01-2025");

        Mockito.when(inventarioService.getInventario(222)).thenReturn(inventario);
        Mockito.when(inventarioService.addInventario(Mockito.any(Inventario.class))).thenReturn(inventarioActualizado);

        Inventario inventarioParaActualizar = inventarioService.getInventario(222);
        inventarioParaActualizar.setCantidad(20);
        inventarioParaActualizar.setFechaActualizacion("02-01-2025");
        Inventario resultado = inventarioService.addInventario(inventarioParaActualizar);

        assertNotNull(resultado);
        assertEquals(20, resultado.getCantidad());
    }

    @Test //4
    public void getInventarioByIdTest() {
        Inventario inventario = new Inventario();
        inventario.setId(222);
        Mockito.when(inventarioService.getInventario(222)).thenReturn(inventario);

        Inventario inventarioObtenido = inventarioService.getInventario(222);

        assertNotNull(inventarioObtenido);
        assertEquals(222, inventarioObtenido.getId());
    }
    @Test //5
    public void getAllInventariosControllerTest() throws Exception {
        Inventario inventario = new Inventario();
        inventario.setId(222);
        inventario.setCantidad(10);
        Mockito.when(inventarioService.getAllInventario()).thenReturn(List.of(inventario));

        mockMvc.perform(get("/inventario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(222))
                .andExpect(jsonPath("$[0].cantidad").value(10));
    }
}