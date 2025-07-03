package com.grupo9.SppringApp004D;

import com.grupo9.SppringApp004D.Model.Proveedor;
import com.grupo9.SppringApp004D.Repository.ProveedorRepository;
import com.grupo9.SppringApp004D.Service.ProveedorService;
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
public class ProveedorTest {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @MockitoBean
    private ProveedorService proveedorService;

    @Autowired
    MockMvc mockMvc;

    @Test //1
    public void createProveedorTest() {
        Proveedor nuevoProveedor = new Proveedor();
        nuevoProveedor.setId(222);
        nuevoProveedor.setProveRut("Nuevo Proveedor");
        nuevoProveedor.setProveNombre("123456789");
        nuevoProveedor.setProveEmail("");
        nuevoProveedor.setProductos(null);

        Mockito.when(proveedorService.addProveedor(Mockito.any(Proveedor.class))).thenReturn(nuevoProveedor);

        Proveedor proveedorGuardado = proveedorService.addProveedor(nuevoProveedor);

        assertNotNull(proveedorGuardado);
        assertEquals(222, proveedorGuardado.getId());
    }

    @Test //2
    public void getAllProveedoresTest() {
        List<Proveedor> proveedores = proveedorRepository.findAll();
        assertNotNull(proveedores);
        assertEquals(1, proveedores.size());
    }

    @Test //3
    public void updateProveedorTest() {
        Proveedor proveedor = new Proveedor();
        proveedor.setId(222);
        proveedor.setProveNombre("Proveedor");
        Proveedor proveedorActualizado = new Proveedor();
        proveedorActualizado.setId(222);
        proveedorActualizado.setProveNombre("Proveedor Actualizado");

        Mockito.when(proveedorService.getProveedor(222)).thenReturn(proveedor);
        Mockito.when(proveedorService.addProveedor(Mockito.any(Proveedor.class))).thenReturn(proveedorActualizado);

        Proveedor proveedorParaActualizar = proveedorService.getProveedor(222);
        proveedorParaActualizar.setProveNombre("Proveedor Actualizado");
        Proveedor resultado = proveedorService.addProveedor(proveedorParaActualizar);

        assertNotNull(resultado);
        assertEquals("Proveedor Actualizado", resultado.getProveNombre());
    }

    @Test //4
    public void getProveedorByIdTest() {
        Proveedor proveedor = new Proveedor();
        proveedor.setId(222);
        Mockito.when(proveedorService.getProveedor(222)).thenReturn(proveedor);

        Proveedor proveedorObtenido = proveedorService.getProveedor(222);

        assertNotNull(proveedorObtenido);
        assertEquals(222, proveedorObtenido.getId());
    }
    @Test //5
    public void getAllProveedoresControllerTest() throws Exception {
        Proveedor proveedor = new Proveedor();
        proveedor.setId(222);
        proveedor.setProveNombre("Proveedor Test");
        Mockito.when(proveedorService.getAllProveedores()).thenReturn(List.of(proveedor));

        mockMvc.perform(get("/proveedor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(222))
                .andExpect(jsonPath("$[0].proveNombre").value("Proveedor Test"));
    }
}