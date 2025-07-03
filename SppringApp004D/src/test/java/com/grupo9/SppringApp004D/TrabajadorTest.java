package com.grupo9.SppringApp004D;

import com.grupo9.SppringApp004D.Model.Trabajador;
import com.grupo9.SppringApp004D.Repository.TrabajadorRepository;
import com.grupo9.SppringApp004D.Service.TrabajadorService;
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
public class TrabajadorTest {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @MockitoBean
    private TrabajadorService trabajadorService;

    @Autowired
    MockMvc mockMvc;

    @Test //1
    public void createTrabajadorTest() {
        Trabajador nuevoTrabajador = new Trabajador();
        nuevoTrabajador.setId(222);
        nuevoTrabajador.setNombre("Nombre Trabajador");
        nuevoTrabajador.setApellido("Apellido Trabajador");
        nuevoTrabajador.setEmail("Email Trabajador");
        nuevoTrabajador.setRol("Cajero");

        Mockito.when(trabajadorService.addTrabajador(Mockito.any(Trabajador.class))).thenReturn(nuevoTrabajador);

        Trabajador trabajadorGuardado = trabajadorService.addTrabajador(nuevoTrabajador);

        assertNotNull(trabajadorGuardado);
        assertEquals(222, trabajadorGuardado.getId());
    }

    @Test //2
    public void getAllTrabajadoresTest() {
        List<Trabajador> trabajadores = trabajadorRepository.findAll();
        assertNotNull(trabajadores);
         assertEquals(1, trabajadores.size());
    }

    @Test //3
    public void updateTrabajadorTest() {
        Trabajador trabajador = new Trabajador();
        trabajador.setId(222);
        trabajador.setNombre("Trabajador");
        Trabajador trabajadorActualizado = new Trabajador();
        trabajadorActualizado.setId(222);
        trabajadorActualizado.setNombre("Trabajador Actualizado");

        Mockito.when(trabajadorService.getTrabajador(222)).thenReturn(trabajador);
        Mockito.when(trabajadorService.addTrabajador(Mockito.any(Trabajador.class))).thenReturn(trabajadorActualizado);

        Trabajador trabajadorParaActualizar = trabajadorService.getTrabajador(222);
        trabajadorParaActualizar.setNombre("Trabajador Actualizado");
        Trabajador resultado = trabajadorService.addTrabajador(trabajadorParaActualizar);

        assertNotNull(resultado);
        assertEquals("Trabajador Actualizado", resultado.getNombre());
    }

    @Test //4
    public void getTrabajadorByIdTest() {
        Trabajador trabajador = new Trabajador();
        trabajador.setId(222);
        Mockito.when(trabajadorService.getTrabajador(222)).thenReturn(trabajador);

        Trabajador trabajadorObtenido = trabajadorService.getTrabajador(222);

        assertNotNull(trabajadorObtenido);
        assertEquals(222, trabajadorObtenido.getId());
    }
    @Test //5
    public void getAllTrabajadoresControllerTest() throws Exception {
        Trabajador trabajador = new Trabajador();
        trabajador.setId(222);
        trabajador.setNombre("Trabajador Test");
        Mockito.when(trabajadorService.getAllTrabajadores()).thenReturn(List.of(trabajador));

        mockMvc.perform(get("/trabajador"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(222))
                .andExpect(jsonPath("$[0].nombre").value("Trabajador Test"));
    }
}