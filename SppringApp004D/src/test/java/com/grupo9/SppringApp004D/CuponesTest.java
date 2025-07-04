package com.grupo9.SppringApp004D;

import com.grupo9.SppringApp004D.Model.Cupones;
import com.grupo9.SppringApp004D.Service.CuponesService;
import com.grupo9.SppringApp004D.Repository.CuponesRepository;
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
public class CuponesTest {

    @Autowired
    private CuponesRepository cuponesRepository;

    @MockitoBean
    private CuponesService cuponesService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void createCuponTest() {
        Cupones cupon = new Cupones();
        cupon.setId(1);
        cupon.setDescripcion("Descuento 10%");
        cupon.setDescuento(10);

        Mockito.when(cuponesService.addCupones(Mockito.any(Cupones.class))).thenReturn(cupon);

        Cupones guardado = cuponesService.addCupones(cupon);

        assertNotNull(guardado);
        assertEquals(1, guardado.getId());
    }

    @Test
    public void getAllCuponesRepositoryTest() {
        List<Cupones> cupones = cuponesRepository.findAll();
        assertNotNull(cupones);
        assertEquals(1, cupones.size());
    }

    @Test
    public void getAllCuponesTest() {
        Cupones cupon = new Cupones();
        cupon.setId(1);
        Mockito.when(cuponesService.getAllCupones()).thenReturn(List.of(cupon));
        List<Cupones> cupones = cuponesService.getAllCupones();
        assertNotNull(cupones);
        assertEquals(1, cupones.size());
    }

    @Test
    public void updateCuponTest() {
        Cupones cupon = new Cupones();
        cupon.setId(1);
        cupon.setDescripcion("Descuento 10%");
        Cupones cuponActualizado = new Cupones();
        cuponActualizado.setId(1);
        cuponActualizado.setDescripcion("Descuento 20%");

        Mockito.when(cuponesService.getCupon(1)).thenReturn(cupon);
        Mockito.when(cuponesService.addCupones(Mockito.any(Cupones.class))).thenReturn(cuponActualizado);

        Cupones paraActualizar = cuponesService.getCupon(1);
        paraActualizar.setDescripcion("Descuento 20%");
        Cupones resultado = cuponesService.addCupones(paraActualizar);

        assertNotNull(resultado);
        assertEquals("Descuento 20%", resultado.getDescripcion());
    }

    @Test
    public void getCuponByIdTest() {
        Cupones cupon = new Cupones();
        cupon.setId(1);
        Mockito.when(cuponesService.getCupon(1)).thenReturn(cupon);

        Cupones obtenido = cuponesService.getCupon(1);

        assertNotNull(obtenido);
        assertEquals(1, obtenido.getId());
    }

    @Test
    public void getAllCuponesControllerTest() throws Exception {
        Cupones cupon = new Cupones();
        cupon.setId(1);
        cupon.setDescripcion("Descuento 10%");
        Mockito.when(cuponesService.getAllCupones()).thenReturn(List.of(cupon));

        mockMvc.perform(get("/cupon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].descripcion").value("Descuento 10%"));
    }
}