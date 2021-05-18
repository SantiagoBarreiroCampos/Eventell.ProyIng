package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;
import java.io.IOException;
import java.security.InvalidParameterException;
import javax.mail.MessagingException;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class Evento
{
	Scanner sc = new Scanner(System.in);
	
	private String id_;
	private String artista_;
	private String ciudad_;
	private String lugar_;
	private String fecha_;
	private String precioMax_;
	private String precioMin_;
	private String genero_;
	

	public String infoEvento() {
		return (artista_ + " en " + lugar_ + ", " + ciudad_ + " el día " + fecha_);
	}
	
	public Evento buscarEvento(String id) {
		BufferedReader reader = null;
		String line = "";
		String cvsSplit = ",";
		String csvFile = "eventos.csv";
		
		Evento evAux = new Evento();
		
		int encontrado = 0;
	
		try {
			   reader = new BufferedReader(new FileReader(csvFile));
			   while ((line = reader.readLine()) != null) {      
			       String[] eventoDividido = line.split(cvsSplit);
		    
		    	if(eventoDividido[0].equals(id))
		    	{ 
		    		evAux.setId_(eventoDividido[0]);
		    		evAux.setArtista_(eventoDividido[1]);
		    		evAux.setCiudad_(eventoDividido[2]);
		    		evAux.setFecha_(eventoDividido[3]);
		    		evAux.setPrecioMin_(eventoDividido[4]);
		    		evAux.setPrecioMax_(eventoDividido[5]);
		    		evAux.setLugar_(eventoDividido[6]);
		    		evAux.setGenero_(eventoDividido[7]);
		    	}		       
		    }
	 		reader.close();
	 		return evAux;
		   
		}catch(Exception e) {}
	    
		return evAux;
	}
		
	public Vector<Evento> buscarEventosPorArtista(String Artista) {
		Vector<Evento> eventos = new Vector<Evento>();
		BufferedReader reader = null;
		String line = "";
		String cvsSplit = ",";
		String csvFile = "eventos.csv";
			
		int encontrado = 0;
	
		try
		{
		   reader = new BufferedReader(new FileReader(csvFile));
		   
		   while ((line = reader.readLine()) != null)
		   {      
			   String[] eventoDividido = line.split(cvsSplit);
	    
			   if(eventoDividido[1].equals(Artista))
			   {
				   Evento evAux = new Evento();
				   evAux.setId_(eventoDividido[0]);
				   evAux.setArtista_(eventoDividido[1]);
				   evAux.setCiudad_(eventoDividido[2]);
				   evAux.setFecha_(eventoDividido[3]);
				   evAux.setPrecioMin_(eventoDividido[4]);
				   evAux.setPrecioMax_(eventoDividido[5]);
				   evAux.setLugar_(eventoDividido[6]);
				   evAux.setGenero_(eventoDividido[7]);
				   eventos.add(evAux);
			   }
		   }
		   reader.close();
		   return eventos;
		   
		}catch(Exception e) {}
	    	
		return eventos;
	}
		
	public void mostrarFicha(String user) throws IOException
	{
		// Poner estas lineas sin cambiar nada antes de cada vez que se quiera tocar algo de la BD
		File tablaEventos = new File("eventosFavoritos.csv");
		Scanner reader = new Scanner(tablaEventos);
		BufferedWriter bw;
		PrintWriter pw;
		bw = new BufferedWriter(new FileWriter(tablaEventos, true));
		pw = new PrintWriter(bw);
		// Hasta aqui las lineas que hay que copiar
		
		boolean puedeSalir = false;
		do
		{
			System.out.println("\n- - - - - - FICHA DE EVENTO - - - - - -");
			System.out.println("Genero: " + this.getGenero_());
			System.out.println("Artista: " + this.getArtista_());
			System.out.println("Ciudad: " + this.getCiudad_());
			System.out.println("Lugar: " + this.getLugar_());
			System.out.println("Fecha: " + this.getFecha_());
			System.out.println("Precios: " + this.getPrecioMin_() +"€ - "+ this.getPrecioMax_() +"€");
					
			//faltaría opcion de enviarle por correo uno de tus eventos favoritos
			
			boolean esFavorito = false;
			while(reader.hasNextLine())
		    {
		    	String linea = reader.nextLine();
		    	String[] eventoDividida = linea.split(",");
		    	
		    	if(eventoDividida[0].equals(user) && eventoDividida[1].equals(this.getId_()))
		    	{	    		
		    			esFavorito = true;	    		
		    	}
		    }
			
			if(esFavorito == true)
			{
				System.out.println("\nPulsa (1) para eliminar este evento de favoritos");
			}
			else
			{
				System.out.println("\nPulsa (1) para añadir este evento a favoritos");
			}
			
			System.out.println("Pulsa (2) para abrir el link de compra");
			System.out.println("Pulsa (3) para compartir el evento con un amigo");			
			System.out.println("Pulsa (0) para regresar al menú");
			
			String eleccion = sc.nextLine();
			
			switch(eleccion)
			{
				case "0":
					puedeSalir = true;
					break;
				case "1":
					if(esFavorito == true)
					{
						// eliminar evento, pendientes de como hacer eso en POO
					}
					else
					{						
						String nuevaLinea = user + "," + this.getId_();
						System.out.println("Aquí tendría que añadir una fila con: " + nuevaLinea);
						//pw.println(nuevaLinea);
					}
					//puedeSalir = false;
					break;								
				case "2":
					System.out.println("Abriendo link");//falta esta parte
					break;
				case "3":
					EnviarEvento();
					break;				
				default:
					System.out.println("Dato introducido incorrecto. Intente de nuevo");
					puedeSalir = false;
					break;
			}
		} while(puedeSalir == false);
		
		// Poner estas lineas sin cambiar nada despues de cada vez que se quiera tocar algo de la BD
		pw.flush();
	    pw.close(); 
		bw.close();
		// Hasta aqui las lineas que hay que copiar
	}
	
	public void MostrarPorBusqueda(int fila, String user) throws Exception
	{
		// Poner estas lineas sin cambiar nada antes de cada vez que se quiera tocar algo de la BD
		BufferedReader reader = null;
		BufferedReader reader2 = null;
		String line = "";
		String line2 = "";
		String cvsSplit = ",";
		String csvFile = "eventos.csv";
		reader = new BufferedReader(new FileReader(csvFile));
		// Hasta aqui las lineas que hay que copiar
		
		while ((line = reader.readLine()) != null)
	    {
	    	String linea = reader.readLine();
	    	String[] eventoDividido = linea.split(",");
	    	
	    	if(eventoDividido[0].equals(String.valueOf(fila)))
	    	{    		
	    		System.out.println("\n- - - - - - FICHA DE EVENTO - - - - - -");
	    		System.out.println("Genero: " + eventoDividido[7]);
	    		System.out.println("Artista: " + eventoDividido[1]);
	    		System.out.println("Ciudad: " + eventoDividido[2]);
	    		System.out.println("Lugar: " + eventoDividido[6]);
	    		System.out.println("Fecha: " + eventoDividido[3]);
	    		System.out.println("Precios entre " + eventoDividido[4] +"€ y "+ eventoDividido[5] +"€");
	    	}	    	
	    }		
	}
	
	void EnviarEvento()
	{
		String destinatario =  "pgonzalezs1999@gmail.com"; //A quien le quieres escribir.
	    String asunto = "Correo de prueba enviado desde Java";
	    String cuerpo = "Esta es una prueba de correo...";

	    Mail.enviarConGMail(destinatario, asunto, cuerpo);
	}

	public String getId_() {
		return id_;
	}

	public void setId_(String id_) {
		this.id_ = id_;
	}

	public String getArtista_() {
		return artista_;
	}

	public void setArtista_(String artista_) {
		this.artista_ = artista_;
	}

	public String getCiudad_() {
		return ciudad_;
	}

	public void setCiudad_(String ciudad_) {
		this.ciudad_ = ciudad_;
	}

	public String getLugar_() {
		return lugar_;
	}

	public void setLugar_(String lugar_) {
		this.lugar_ = lugar_;
	}

	public String getFecha_() {
		return fecha_;
	}

	public void setFecha_(String fecha_) {
		this.fecha_ = fecha_;
	}

	public String getPrecioMax_() {
		return precioMax_;
	}

	public void setPrecioMax_(String precioMax_) {
		this.precioMax_ = precioMax_;
	}

	public String getPrecioMin_() {
		return precioMin_;
	}

	public void setPrecioMin_(String precioMin_) {
		this.precioMin_ = precioMin_;
	}

	public String getGenero_() {
		return genero_;
	}

	public void setGenero_(String genero_) {
		this.genero_ = genero_;
	}
}