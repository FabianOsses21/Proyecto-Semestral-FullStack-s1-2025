package com.grupo9.SppringApp004D;

import com.grupo9.SppringApp004D.Model.Envio;
import com.grupo9.SppringApp004D.Repository.EnvioRepository;
import com.grupo9.SppringApp004D.Service.EnvioService;
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
public class EnvioTest {

    @Autowired
    private EnvioRepository envioRepository;

    @MockitoBean
    private EnvioService envioService;

    @Autowired
    MockMvc mockMvc;

    @Test //1
    public void createEnvioTest() {
        Envio nuevoEnvio = new Envio();
        nuevoEnvio.setId(222);
        nuevoEnvio.setUsuario(null);
        nuevoEnvio.setTienda(null);
        nuevoEnvio.setEstado("Pendiente");
        nuevoEnvio.setFechaEnvio("01-01-2025");

        Mockito.when(envioService.addEnvio(Mockito.any(Envio.class))).thenReturn(nuevoEnvio);

        Envio envioGuardado = envioService.addEnvio(nuevoEnvio);

        assertNotNull(envioGuardado);
        assertEquals(222, envioGuardado.getId());
    }

    @Test //2
    public void getAllEnviosTest() {
        List<Envio> envios = envioRepository.findAll();
        assertNotNull(envios);
        assertEquals(1, envios.size());
    }

    @Test //3
    public void updateEnvioTest() {
        Envio envio = new Envio();
        envio.setId(222);
        envio.setEstado("Pendiente");
        envio.setFechaEnvio("01-01-2025");
        Envio envioActualizado = new Envio();
        envioActualizado.setId(222);
        envioActualizado.setEstado("Entregado");
        envioActualizado.setFechaEnvio("02-01-2025");

        Mockito.when(envioService.getEnvio(222)).thenReturn(envio);
        Mockito.when(envioService.addEnvio(Mockito.any(Envio.class))).thenReturn(envioActualizado);

        Envio envioParaActualizar = envioService.getEnvio(222);
        envioParaActualizar.setEstado("Entregado");
        envioParaActualizar.setFechaEnvio("02-01-2025");
        Envio resultado = envioService.addEnvio(envioParaActualizar);

        assertNotNull(resultado);
        assertEquals("Entregado", resultado.getEstado());
    }

    @Test //4
    public void getEnvioByIdTest() {
        Envio envio = new Envio();
        envio.setId(222);
        Mockito.when(envioService.getEnvio(222)).thenReturn(envio);

        Envio envioObtenido = envioService.getEnvio(222);

        assertNotNull(envioObtenido);
        assertEquals(222, envioObtenido.getId());
    }
    @Test //5
    public void getAllEnviosControllerTest() throws Exception {
        Envio envio = new Envio();
        envio.setId(222);
        envio.setEstado("Pendiente");
        Mockito.when(envioService.getAllEnvios()).thenReturn(List.of(envio));

        mockMvc.perform(get("/envio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(222))
                .andExpect(jsonPath("$[0].estado").value("Pendiente"));
    }
}