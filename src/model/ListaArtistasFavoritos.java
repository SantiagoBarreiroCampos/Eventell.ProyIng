package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class ListaArtistasFavoritos
{
	private String user_;
	private Vector<String> artistas_;
	
//	public ListaArtistasFavoritos(String user, Vector<Artista> artistas)
//	{
//		this.user_ = user;
//		this.artistas_ = artistas;
//	}
	
	public String getUser()
	{
		return user_;
	}
	public Vector<String> getArtistas()
	{
		return artistas_;
	}
	
	public ListaArtistasFavoritos buscarArtistas(Usuario user) {
		try {
			// Poner estas lineas sin cambiar nada antes de cada vez que se quiera tocar algo de la BD
			File tablaArtista = new File("artistasFavoritos.csv");				
			BufferedWriter bw;
			PrintWriter pw;
			bw = new BufferedWriter(new FileWriter(tablaArtista, true));
			pw = new PrintWriter(bw);
			// Hasta aqui las lineas que hay que copiar
			
			Scanner reader = new Scanner(tablaArtista);  //Le paso como parámetro el fichero que quiero leer
		
		    //Leemos cada línea
		    
		    Vector<String> artistas = new Vector<String>();
		    //boolean encontrado = false;
		    while(reader.hasNextLine())
		    {
		    	String linea = reader.nextLine();
		    	String[] artistaDividido = linea.split(",");
		    	if(artistaDividido[0].equals(user.getUser()))
		    	{
		    		artistas.add(artistaDividido[1]);
		    	}
		       
		    }
		    this.user_ = user.getUser();
		    this.artistas_ = artistas;
		     //System.out.println("Iniciando sesion...\n"); 
		    pw.flush();
	 	    pw.close(); 
	 		bw.close();
		   
		}catch(Exception e) {}
	    
	    // Poner estas lineas sin cambiar nada despues de cada vez que se quiera tocar algo de la B
 		// Hasta aqui las lineas que hay que copiar
		
		return this;
	}
	
	public void mostrarArtistas() {
		int i =0;
		System.out.println("\n-----LISTA DE ARTISTAS FAVORITOS-----");
		if(artistas_.size()<=0) {
			System.out.println("No tiene artistas en su lista");
		}
		else {
			for(i=0; i<artistas_.size(); i++) {
				System.out.println((i+1)+" " +artistas_.get(i));
			}
		}
		System.out.println();
	}
}