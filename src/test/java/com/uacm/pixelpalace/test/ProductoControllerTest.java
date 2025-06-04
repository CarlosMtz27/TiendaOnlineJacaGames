package com.uacm.pixelpalace.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.uacm.pixelpalace.controller.ProductoController;
import com.uacm.pixelpalace.model.Producto;
import com.uacm.pixelpalace.model.Usuario;
import com.uacm.pixelpalace.service.IUsuarioService;
import com.uacm.pixelpalace.service.ProductoService;
import com.uacm.pixelpalace.service.UploadFileService;

@WebMvcTest(ProductoController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @MockBean
    private IUsuarioService usuarioService;

    @MockBean
    private UploadFileService uploadFileService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockHttpSession session;

    @BeforeEach
    public void setup() {
        // Configuración básica
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        
        // Configuración de la sesión
        session = new MockHttpSession();
        session.setAttribute("idusuario", "1"); // ID como String para simular HttpSession
        
        // Configuración del mock de usuarioService
        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(1);
        usuarioMock.setNombre("Admin");
        usuarioMock.setTipo("ADMIN");
        
        // Corrección clave aquí:
        when(usuarioService.findById(1)).thenReturn(Optional.of(usuarioMock));
        
    }

    @Test
    public void testShow() throws Exception {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Producto 1", "Descripción", "imagen.jpg", 100.0, 10, "Genero", null));
        productos.add(new Producto(2, "Producto 2", "Descripción", "imagen.jpg", 200.0, 20, "Genero", null));

        when(productoService.findAll()).thenReturn(productos);

        mockMvc.perform(get("/productos"))
               .andExpect(status().isOk())
               .andExpect(view().name("productos/show"))
               .andExpect(model().attributeExists("juegos"))
               .andExpect(model().attributeExists("productos"));
    }

    @Test
    public void testCreate() throws Exception {
        mockMvc.perform(get("/productos/create"))
               .andExpect(status().isOk())
               .andExpect(view().name("productos/create"));
    }

    @Test
    public void testSave() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
            "img", 
            "test.jpg", 
            "image/jpeg",  // cambio de direcion en donde se alamsena el imagen
            "test image content".getBytes()
        );

        Producto producto = new Producto();
        producto.setId(1);
        producto.setNombre("Nuevo Producto");
        
        when(productoService.save(any(Producto.class))).thenReturn(producto);
        when(uploadFileService.saveImage(any())).thenReturn("test.jpg");

        mockMvc.perform(multipart("/productos/save")
                .file(file)
                .session(session)
                .flashAttr("producto", producto))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/productos"));
    }

    @Test
    public void testEdit() throws Exception {
        Producto producto = new Producto(1, "Producto", "Descripción", "imagen.jpg", 100.0, 10, "Genero", null);
        
        when(productoService.get(1)).thenReturn(Optional.of(producto));

        mockMvc.perform(get("/productos/edit/1"))
               .andExpect(status().isOk())
               .andExpect(view().name("productos/edit"))
               .andExpect(model().attributeExists("producto"));
    }

    @Test
    public void testUpdate_WithoutNewImage() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
            "img", 
            "", 
            "image/jpeg", // cambio de direcion en donde se alamsena el imagen 
            new byte[0]
        );

        Producto productoExistente = new Producto(1, "Existente", "Desc", "old.jpg", 100.0, 10, "Genero", null);
        Producto productoActualizado = new Producto(1, "Actualizado", "Desc", "old.jpg", 150.0, 15, "Genero", null);

        when(productoService.get(1)).thenReturn(Optional.of(productoExistente));
        when(productoService.update(any(Producto.class))).thenReturn(productoActualizado);

        mockMvc.perform(multipart("/productos/update")
                .file(file)
                .flashAttr("producto", productoActualizado))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/productos"));
    }

    private OngoingStubbing<Optional<Usuario>> when(Object update) {
		// TODO Auto-generated method stub
		return null;
	}

	@Test
    public void testUpdate_WithNewImage() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
            "img", 
            "new.jpg", 
            "image/jpeg",  // cambio de direcion en donde se alamsena el imagen
            "new image content".getBytes()
        );

        Producto productoExistente = new Producto(1, "Existente", "Desc", "old.jpg", 100.0, 10, "Genero", null);
        Producto productoActualizado = new Producto(1, "Actualizado", "Desc", "new.jpg", 150.0, 15, "Genero", null);

        when(productoService.get(1)).thenReturn(Optional.of(productoExistente));
        when(productoService.update(any(Producto.class))).thenReturn(productoActualizado);
        when(uploadFileService.saveImage(any())).thenReturn("new.jpg");
        doNothing().when(uploadFileService).deleteImage(anyString());

        mockMvc.perform(multipart("/productos/update")
                .file(file)
                .flashAttr("producto", productoActualizado))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/productos"));
    }

    @Test
    public void testDelete() throws Exception {
        Producto producto = new Producto(1, "Producto", "Desc", "imagen.jpg", 100.0, 10, "Genero", null);
        
        when(productoService.get(1)).thenReturn(Optional.of(producto));
        doNothing().when(productoService).delete(1);
        doNothing().when(uploadFileService).deleteImage(anyString());

        mockMvc.perform(get("/productos/delete/1"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/productos"));
    }

    @Test
    public void testObtenerJuegos() throws Exception {
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Juego 1", "Descripción", "imagen.jpg", 100.0, 10, "Genero", null));
        
        when(productoService.findAll()).thenReturn(productos);

        mockMvc.perform(get("/productos/juegos"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].nombre").value("Juego 1"));
    }
}
