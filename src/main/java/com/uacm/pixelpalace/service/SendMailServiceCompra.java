package com.uacm.pixelpalace.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendMailServiceCompra {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private EmailBodyBuilderCompra bodyBuilder;
	
	 @Autowired
	 private EmailStyleManager styleManager;
	 
	 public void sendStyledMail(String from, String to, String subject, String mensaje) {
	        MimeMessage message = javaMailSender.createMimeMessage();

	        try {
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);
	            helper.setFrom(from);
	            helper.setTo(to);
	            helper.setSubject(subject);

	            String styledBody = styleManager.addStylesToBody(bodyBuilder.buildBody(mensaje));//Mandamos a construir el cuerpo 

	            helper.setText(styledBody, true); // Habilitar HTML

	            javaMailSender.send(message);
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
}
