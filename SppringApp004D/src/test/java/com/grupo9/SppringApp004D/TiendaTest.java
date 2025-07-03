package com.grupo9.SppringApp004D;

import com.grupo9.SppringApp004D.Model.Tienda;
import com.grupo9.SppringApp004D.Repository.TiendaRepository;
import com.grupo9.SppringApp004D.Service.TiendaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class TiendaTest {

    @Autowired
    private TiendaRepository tiendaRepository;

    @MockitoBean
    private TiendaService tiendaService;

    @Autowired
    MockMvc mockMvc;

    @Test //1
    public void createTiendaTest() {
        Tienda nuevaTienda = new Tienda();
        nuevaTienda.setId(222);
        nuevaTienda.setNombre("Nueva Tienda");
        nuevaTienda.setDireccion("Dirección de la tienda");
        nuevaTienda.setComuna("Comuna de la tienda");

        Mockito.when(tiendaService.addTienda(Mockito.any(Tienda.class))).thenReturn(nuevaTienda);

        Tienda tiendaGuardada = tiendaService.addTienda(nuevaTienda);

        assertNotNull(tiendaGuardada);
        assertEquals(222, tiendaGuardada.getId());
    }

    @Test //2
    public void getAllTiendasTest() {
        List<Tienda> tiendas = tiendaRepository.findAll();
        assertNotNull(tiendas);
        assertEquals(1, tiendas.size());
    }

    @Test //3
    public void updateTiendaTest() {
        Tienda tienda = new Tienda();
        tienda.setId(222);
        tienda.setNombre("Tienda");
        Tienda tiendaActualizada = new Tienda();
        tiendaActualizada.setId(222);
        tiendaActualizada.setNombre("Tienda Actualizada");

        Mockito.when(tiendaService.getTienda(222)).thenReturn(tienda);
        Mockito.when(tiendaService.addTienda(Mockito.any(Tienda.class))).thenReturn(tiendaActualizada);

        Tienda tiendaParaActualizar = tiendaService.getTienda(222);
        tiendaParaActualizar.setNombre("Tienda Actualizada");
        Tienda resultado = tiendaService.addTienda(tiendaParaActualizar);

        assertNotNull(resultado);
        assertEquals("Tienda Actualizada", resultado.getNombre());
    }

    @Test //4
    public void getTiendaByIdTest() {
        Tienda tienda = new Tienda();
        tienda.setId(222);
        Mockito.when(tiendaService.getTienda(222)).thenReturn(tienda);

        Tienda tiendaObtenida = tiendaService.getTienda(222);

        assertNotNull(tiendaObtenida);
        assertEquals(222, tiendaObtenida.getId());
    }
    @Test //5
    public void getAllTiendasControllerTest() throws Exception {
        Tienda tienda = new Tienda();
        tienda.setId(222);
        tienda.setNombre("Nueva Tienda");
        tienda.setDireccion("Dirección de la tienda");
        tienda.setComuna("Comuna de la tienda");

        Mockito.when(tiendaService.getAllTiendas()).thenReturn(List.of(tienda));

        mockMvc.perform(get("/tienda"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(222))
                .andExpect(jsonPath("$[0].nombre").value("Nueva Tienda"));
    }
}