package com.grupo9.SppringApp004D;

import com.grupo9.SppringApp004D.Model.Resenia;
import com.grupo9.SppringApp004D.Repository.ReseniaRepository;
import com.grupo9.SppringApp004D.Service.ReseniaService;
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
public class ReseniaTest {

    @Autowired
    private ReseniaRepository reseniaRepository;

    @MockitoBean
    private ReseniaService reseniaService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void createReseniaTest() {
        Resenia resenia = new Resenia();
        resenia.setId(1);
        resenia.setComentario("Muy bueno");

        Mockito.when(reseniaService.addResenia(Mockito.any(Resenia.class))).thenReturn(resenia);

        Resenia guardada = reseniaService.addResenia(resenia);

        assertNotNull(guardada);
        assertEquals(1, guardada.getId());
    }

    @Test
    public void getAllReseniasRepositoryTest() {
        List<Resenia> resenias = reseniaRepository.findAll();
        assertNotNull(resenias);
        assertEquals(1, resenias.size());
    }

    @Test
    public void getAllReseniasTest() {
        Resenia resenia = new Resenia();
        resenia.setId(1);
        Mockito.when(reseniaService.getAllResenias()).thenReturn(List.of(resenia));
        List<Resenia> resenias = reseniaService.getAllResenias();
        assertNotNull(resenias);
        assertEquals(1, resenias.size());
    }

    @Test
    public void updateReseniaTest() {
        Resenia resenia = new Resenia();
        resenia.setId(1);
        resenia.setComentario("Bueno");
        Resenia reseniaActualizada = new Resenia();
        reseniaActualizada.setId(1);
        reseniaActualizada.setComentario("Excelente");

        Mockito.when(reseniaService.getResenia(1)).thenReturn(resenia);
        Mockito.when(reseniaService.addResenia(Mockito.any(Resenia.class))).thenReturn(reseniaActualizada);

        Resenia paraActualizar = reseniaService.getResenia(1);
        paraActualizar.setComentario("Excelente");
        Resenia resultado = reseniaService.addResenia(paraActualizar);

        assertNotNull(resultado);
        assertEquals("Excelente", resultado.getComentario());
    }

    @Test
    public void getReseniaByIdTest() {
        Resenia resenia = new Resenia();
        resenia.setId(1);
        Mockito.when(reseniaService.getResenia(1)).thenReturn(resenia);

        Resenia obtenida = reseniaService.getResenia(1);

        assertNotNull(obtenida);
        assertEquals(1, obtenida.getId());
    }

    @Test
    public void getAllReseniasControllerTest() throws Exception {
        Resenia resenia = new Resenia();
        resenia.setId(1);
        resenia.setComentario("Muy bueno");
        Mockito.when(reseniaService.getAllResenias()).thenReturn(List.of(resenia));

        mockMvc.perform(get("/resenia"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.reseniaList[0].id").value(1))
                .andExpect(jsonPath("$._embedded.reseniaList[0].comentario").value("Muy bueno"));
    }
}