package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class Evento
{
	private String id_;
	private String artista_;
	private String ciudad_;
	private String lugar_;
	private String fecha_;
	private String precioMax_;
	private String precioMin_;
	private String genero_;
	
	public void setId(String id) {
		this.id_ = id;
	}
	
	public void setArtista(String artista) {
		this.artista_ = artista;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad_ = ciudad;
	}
	
	public void setLugar(String lugar) {
		this.lugar_ = lugar;
	}
	public void setFecha(String fecha) {
		this.fecha_ = fecha;
	}
	public void setPrecioMax(String precio) {
		this.precioMax_ = precio;
	}
	public void setPrecioMin(String precio) {
		this.precioMin_ = precio;
	}
	public void setGenero(String genero) {
		this.genero_ = genero;
	}
	
	public String getArtista() {
		return this.artista_;
	}
	
	public String infoEvento() {
		return (artista_ + " en " + lugar_ + ", " + ciudad_ + " el día " + fecha_);
	}
	
	public Evento buscarEvento(String id) {
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
		    	if(eventoDividido[0].equals(id))
		    	{
		    		setId(eventoDividido[0]);
	    			setArtista(eventoDividido[1]);
	    			setCiudad(eventoDividido[2]);
	    			setFecha(eventoDividido[3]);
	    			setPrecioMin(eventoDividido[4]);
	    			setPrecioMax(eventoDividido[5]);
	    			setLugar(eventoDividido[6]);
	    			setGenero(eventoDividido[7]);
		    	}
		       
		    }
		     //System.out.println("Iniciando sesion...\n"); 
		    pw.flush();
	 	    pw.close(); 
	 		bw.close();
	 		reader.close();
		   
		}catch(Exception e) {}
	    
	    // Poner estas lineas sin cambiar nada despues de cada vez que se quiera tocar algo de la B
 		// Hasta aqui las lineas que hay que copiar
		
		return this;
	}
}