package model;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import control.CodigoController;

public class Mail {
	
	
	 public static String codigoEnviado = "";
	 
	 
	 public void enviarCorreo(String recepient, String amigo, Evento evento) {
	    Properties propiedades = new Properties();
	     
	   	propiedades.put("mail.smtp.auth", true);
	   	propiedades.put("mail.smtp.starttls.enable", true);
	   	propiedades.put("mail.smtp.host", "smtp.gmail.com");
	   	propiedades.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	   	propiedades.put("mail.smtp.port", "587");
	    	
    	String miCorreo = "uemeventell@gmail.com";
    	String miPassword = "eventell2021";

	    	
	   	Session session = Session.getInstance(propiedades, new Authenticator() {
	   		@Override
	   		protected PasswordAuthentication getPasswordAuthentication() {
	   			return new PasswordAuthentication(miCorreo, miPassword);
    		}
	   	});
	    	
	   	Message mensaje = prepararMensaje(session, miCorreo, recepient, amigo, evento);
	   	
	   	try {
			Transport.send(mensaje);
			System.out.println("Mensaje enviado con exito");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("Error en el envio del mensaje");
		}
	   	
	 }
	    
	 private static Message prepararMensaje(Session session, String miCorreo, String recepient, String amigo, Evento evento) {
		try {		
			//codigoEnviado = CodigoController.generarCodigo(7);
			
			Message mensaje = new MimeMessage(session);
			mensaje.setFrom(new InternetAddress(miCorreo));
			mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			mensaje.setSubject("Evento enviado - Eventell");
			mensaje.setContent("<p>Tu amigo "+amigo+" quiere invitarte a este evento:</p>"
								+ "<p>"+evento.infoEvento()+"</p>"
								+ "<p>¡Entra a Eventell para ver más información!</p>","text/html");
			
			return mensaje;
						
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return null;
    }
	    
}