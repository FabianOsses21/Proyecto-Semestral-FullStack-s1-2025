package com.grupo9.SppringApp004D;

import com.grupo9.SppringApp004D.Model.Usuario;
import com.grupo9.SppringApp004D.Repository.UsuarioRepository;
import com.grupo9.SppringApp004D.Service.UsuarioService;
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
public class UsuarioTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @MockitoBean
    private UsuarioService usuarioService;

    @Autowired
    MockMvc mockMvc;

    @Test //1
    public void createUsuarioTest() {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setId(222);
        nuevoUsuario.setNombre("Nombre Usuario");
        nuevoUsuario.setEmail("usuario@email.com");
        nuevoUsuario.setPassword("password123");

        Mockito.when(usuarioService.addUsuario(Mockito.any(Usuario.class))).thenReturn(nuevoUsuario);

        Usuario usuarioGuardado = usuarioService.addUsuario(nuevoUsuario);

        assertNotNull(usuarioGuardado);
        assertEquals(222, usuarioGuardado.getId());
    }

    @Test //2
    public void getAllUsuariosTest() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test //3
    public void updateUsuarioTest() {
        Usuario usuario = new Usuario();
        usuario.setId(222);
        usuario.setNombre("Usuario");
        Usuario usuarioActualizado = new Usuario();
        usuarioActualizado.setId(222);
        usuarioActualizado.setNombre("Usuario Actualizado");

        Mockito.when(usuarioService.getUsuario(222)).thenReturn(usuario);
        Mockito.when(usuarioService.addUsuario(Mockito.any(Usuario.class))).thenReturn(usuarioActualizado);

        Usuario usuarioParaActualizar = usuarioService.getUsuario(222);
        usuarioParaActualizar.setNombre("Usuario Actualizado");
        Usuario resultado = usuarioService.addUsuario(usuarioParaActualizar);

        assertNotNull(resultado);
        assertEquals("Usuario Actualizado", resultado.getNombre());
    }

    @Test //4
    public void getUsuarioByIdTest() {
        Usuario usuario = new Usuario();
        usuario.setId(222);
        Mockito.when(usuarioService.getUsuario(222)).thenReturn(usuario);

        Usuario usuarioObtenido = usuarioService.getUsuario(222);

        assertNotNull(usuarioObtenido);
        assertEquals(222, usuarioObtenido.getId());
    }
    @Test //5
    public void getAllUsuariosControllerTest() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(222);
        usuario.setNombre("Nombre Usuario");
        usuario.setEmail("usuario@email.com");
        usuario.setPassword("password123");

        Mockito.when(usuarioService.getAllUsuarios()).thenReturn(List.of(usuario));

        mockMvc.perform(get("/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(222))
                .andExpect(jsonPath("$[0].nombre").value("Nombre Usuario"));
    }
}