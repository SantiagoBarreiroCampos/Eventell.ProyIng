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
		Evento evAux = new Evento();
		try {
			// Poner estas lineas sin cambiar nada antes de cada vez que se quiera tocar algo de la BD
			File tablaEventos = new File("eventos.csv");				
			BufferedWriter bw;
			PrintWriter pw;
			bw = new BufferedWriter(new FileWriter(tablaEventos, true));
			pw = new PrintWriter(bw);
			// Hasta aqui las lineas que hay que copiar
			
			Scanner reader = new Scanner(tablaEventos);  //Le paso como parámetro el fichero que quiero leer
		
		    //Leemos cada línea
		    //boolean encontrado = false;
			
		    while(reader.hasNextLine())
		    {
		    	String linea = reader.nextLine();
		    	String[] eventoDividido = linea.split(",");
		    	//System.out.println(eventoDividido[0]);
		    	if(eventoDividido[0].equals(id))
		    	{
		    		System.out.println("ENCONTRADO: " + id +" "+ eventoDividido[1]); 
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
		     //System.out.println("Iniciando sesion...\n"); 
		    pw.flush();
	 	    pw.close(); 
	 		bw.close();
	 		reader.close();
	 		return evAux;
		   
		}catch(Exception e) {}
	    
	    // Poner estas lineas sin cambiar nada despues de cada vez que se quiera tocar algo de la B
 		// Hasta aqui las lineas que hay que copiar
		
		return evAux;
	}
	
	
	public Vector<Evento> buscarEventosPorArtista(String Artista) {
		Vector<Evento> eventos = new Vector<Evento>();
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
		    
		    	System.out.println(eventoDividido[1]);
		    	if(eventoDividido[1].equals(Artista))
		    	{
		    		System.out.println("ENCONTRADO: " + Artista +" "+ eventoDividido[0]); 
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
		     //System.out.println("Iniciando sesion...\n"); 
	 		reader.close();
	 		return eventos;
		   
		}catch(Exception e) {}
	    
	    // Poner estas lineas sin cambiar nada despues de cada vez que se quiera tocar algo de la B
 		// Hasta aqui las lineas que hay que copiar
		
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
					System.out.println("Compratiendo con un amigo");//falta esta parte
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