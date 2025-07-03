package com.grupo9.SppringApp004D;

import com.grupo9.SppringApp004D.Model.Producto;
import com.grupo9.SppringApp004D.Repository.ProductoRepository;
import com.grupo9.SppringApp004D.Service.ProductoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductoTest {

    @Autowired
    private ProductoRepository productoRepository;

    @MockitoBean
    private ProductoService productoService;

    @Autowired
    MockMvc mockMvc;

    @Test //1
    public void createProductoTest() {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setId(222);
        nuevoProducto.setNombre("Nuevo Producto");
        nuevoProducto.setPrecio(100);
        nuevoProducto.setDescripcion("Descripción del nuevo producto");

        Mockito.when(productoService.addProducto(Mockito.any(Producto.class))).thenReturn(nuevoProducto);

        Producto productoGuardado = productoService.addProducto(nuevoProducto);

        assertNotNull(productoGuardado);
        assertEquals("Nuevo Producto", productoGuardado.getNombre());
    }

    @Test //2
    public void getAllProductosTest() {
        List<Producto> productos = productoRepository.findAll();

        assertNotNull(productos);
    }

    @Test //3
    public void getProductoByIdTest() {
        Producto producto = new Producto(222, "Producto Test", "Desc", 100);
        Mockito.when(productoService.getProducto(222)).thenReturn(producto);

        Producto productoObtenido = productoService.getProducto(222);

        assertNotNull(productoObtenido);
        assertEquals(222, productoObtenido.getId());
    }

    @Test //4
    public void updateProductoTest() {
        Producto producto = new Producto(222, "Producto Test", "Desc", 100);
        Producto productoActualizado = new Producto(222, "Producto Actualizado", "Desc", 100);

        Mockito.when(productoService.getProducto(222)).thenReturn(producto);
        Mockito.when(productoService.addProducto(Mockito.any(Producto.class))).thenReturn(productoActualizado);

        Producto productoParaActualizar = productoService.getProducto(222);
        productoParaActualizar.setNombre("Producto Actualizado");
        Producto resultado = productoService.addProducto(productoParaActualizar);

        assertNotNull(resultado);
        assertEquals("Producto Actualizado", resultado.getNombre());
    }

    @Test
    void checkGetAllProductosService() {
        Mockito.when(productoService.getAllProductos().toString()).thenReturn("ListaCompleta");

        try {
            mockMvc.perform(get("/product"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("ListaCompleta"));
        } catch (Exception ex) {
            System.out.println("error: " + ex.getMessage());
            fail();
        }
    }

    @Test
    void checkGetProductoByIdService() {
        Producto producto = new Producto(222, "Producto 222", "Desc", 100);
        Mockito.when(productoService.getProducto(222)).thenReturn(producto);

        try {
            mockMvc.perform(get("/product/222"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Producto 222"));
        } catch (Exception ex) {
            System.out.println("error: " + ex.getMessage());
            fail();
        }
    }
    @Test //5
    public void getAllProductosControllerTest() throws Exception {
        Producto producto = new Producto(222, "Producto Test", "Desc", 100);
        Mockito.when(productoService.getAllProductos()).thenReturn(List.of(producto));

        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(222))
                .andExpect(jsonPath("$[0].nombre").value("Producto Test"));
    }
}
