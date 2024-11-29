package com.uacm.pixelpalace.service;

import org.springframework.stereotype.Service;

@Service
public class EmailBodyBuilderCompra {
	public String buildBody(String body) {
        String htmlBody = "<!DOCTYPE html>"//Creamos la estructura del cuerpo del correo
                + "<html lang=\"es\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Compra en JacaGames</title>"
                + "</head>"
                + "<body>"
                + "<div style=\"text-align: center; padding: 20px;\">"
                + "</div>"
                + "<div style=\"background-color: #f4f4f4; padding: 20px; border-radius: 5px;\">"
                + "<h2>¡Gracias por tu compra!</h2>"
                + "<p>Estimado usuario, aqui estan los detalles de su compra</p>"+
                body
                +"<p>¡Gracias!</p>"
                + "<p><em>Jaca Softworks</em></p>"
                + "</div>"
                + "</body>"
                + "</html>";

        return htmlBody;
    }
}
