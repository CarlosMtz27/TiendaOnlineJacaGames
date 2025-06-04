package com.uacm.pixelpalace.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.uacm.pixelpalace.controller.HomeController;

class HomeControllerTest {

    @Test
    void testGenerarCodigoJuego() {
        HomeController controller = new HomeController();
        String codigo = controller.generarCodigoJuego();
        
        // Verifica que el código tenga el formato correcto (ej: "ABCD-EFGH-IJKL-MNOP")
        assertTrue(codigo.matches("[A-Z]{4}-[A-Z]{4}-[A-Z]{4}-[A-Z]{4}"));
    }
    
}
