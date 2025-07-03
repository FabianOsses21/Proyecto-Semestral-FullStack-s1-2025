package com.grupo9.SppringApp004D;

import com.grupo9.SppringApp004D.Model.Reclamo;
import com.grupo9.SppringApp004D.Service.ReclamoService;
import com.grupo9.SppringApp004D.Repository.ReclamoRepository;
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
public class ReclamoTest {

    @Autowired
    private ReclamoRepository reclamoRepository;

    @MockitoBean
    private ReclamoService reclamoService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void createReclamoTest() {
        Reclamo reclamo = new Reclamo();
        reclamo.setId(1);
        reclamo.setDescripcion("Producto defectuoso");

        Mockito.when(reclamoService.addReclamo(Mockito.any(Reclamo.class))).thenReturn(reclamo);

        Reclamo guardado = reclamoService.addReclamo(reclamo);

        assertNotNull(guardado);
        assertEquals(1, guardado.getId());
    }

    @Test
    public void getAllReclamosRepositoryTest() {
        List<Reclamo> reclamos = reclamoRepository.findAll();
        assertNotNull(reclamos);
        assertEquals(1, reclamos.size());
    }

    @Test
    public void getAllReclamosTest() {
        Reclamo reclamo = new Reclamo();
        reclamo.setId(1);
        Mockito.when(reclamoService.getAllReclamos()).thenReturn(List.of(reclamo));
        List<Reclamo> reclamos = reclamoService.getAllReclamos();
        assertNotNull(reclamos);
        assertEquals(1, reclamos.size());
    }

    @Test
    public void updateReclamoTest() {
        Reclamo reclamo = new Reclamo();
        reclamo.setId(1);
        reclamo.setDescripcion("Producto defectuoso");
        Reclamo reclamoActualizado = new Reclamo();
        reclamoActualizado.setId(1);
        reclamoActualizado.setDescripcion("Producto cambiado");

        Mockito.when(reclamoService.getReclamo(1)).thenReturn(reclamo);
        Mockito.when(reclamoService.addReclamo(Mockito.any(Reclamo.class))).thenReturn(reclamoActualizado);

        Reclamo paraActualizar = reclamoService.getReclamo(1);
        paraActualizar.setDescripcion("Producto cambiado");
        Reclamo resultado = reclamoService.addReclamo(paraActualizar);

        assertNotNull(resultado);
        assertEquals("Producto cambiado", resultado.getDescripcion());
    }

    @Test
    public void getReclamoByIdTest() {
        Reclamo reclamo = new Reclamo();
        reclamo.setId(1);
        Mockito.when(reclamoService.getReclamo(1)).thenReturn(reclamo);

        Reclamo obtenido = reclamoService.getReclamo(1);

        assertNotNull(obtenido);
        assertEquals(1, obtenido.getId());
    }

    @Test
    public void getAllReclamosControllerTest() throws Exception {
        Reclamo reclamo = new Reclamo();
        reclamo.setId(1);
        reclamo.setDescripcion("Producto defectuoso");
        Mockito.when(reclamoService.getAllReclamos()).thenReturn(List.of(reclamo));

        mockMvc.perform(get("/reclamo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].descripcion").value("Producto defectuoso"));
    }
}