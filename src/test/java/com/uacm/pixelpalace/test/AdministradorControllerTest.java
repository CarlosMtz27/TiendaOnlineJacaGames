package com.uacm.pixelpalace.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.hasSize; // Importa hasSize de Hamcrest

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.uacm.pixelpalace.controller.AdministradorController;
import com.uacm.pixelpalace.model.Producto;
import com.uacm.pixelpalace.model.Usuario;
import com.uacm.pixelpalace.service.IUsuarioService;
import com.uacm.pixelpalace.service.IVentaService;
import com.uacm.pixelpalace.service.ProductoService;

@WebMvcTest(AdministradorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AdministradorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @MockBean
    private IUsuarioService usuarioService;

    @MockBean
    private IVentaService ventaService;

    @Test
    public void testHomeEndpoint() throws Exception {
        // Configura los productos de prueba
        Producto producto1 = new Producto(1, "Cyberpunk 2077", "Descripción", "imagen.jpg", 
                                        59.99, 10, "RPG", new Usuario());
        Producto producto2 = new Producto(2, "The Witcher 3", "Descripción", "imagen.jpg", 
                                        39.99, 5, "RPG", new Usuario());

        // Simula el servicio
        when(productoService.findAll()).thenReturn(List.of(producto1, producto2));

        // Ejecuta y verifica
        mockMvc.perform(get("/administrador"))
               .andExpect(status().isOk())
               .andExpect(view().name("administrador/home"))
               .andExpect(model().attributeExists("productos"))
               .andExpect(model().attribute("productos", hasSize(2))); // Usa hasSize de Hamcrest
    }
}