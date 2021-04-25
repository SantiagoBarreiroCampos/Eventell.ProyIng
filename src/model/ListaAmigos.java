package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class ListaAmigos {

	private String usuario;
	private Vector<String> amigos_;
	
	public Vector<String> getAmigos(){
		return this.amigos_;
	}
	public String getUsuario(){
		return this.usuario;
	}
	public void setUsuario(String usuario){
		this.usuario = usuario;
	}
	public void setAmigos(Vector<String> amigos){
		this.amigos_ = amigos;
	}
	
	
	public ListaAmigos buscarAmigos(Usuario user) {
		try {
			// Poner estas lineas sin cambiar nada antes de cada vez que se quiera tocar algo de la BD
			File tablaAmistad = new File("amistades.csv");				
			BufferedWriter bw;
			PrintWriter pw;
			bw = new BufferedWriter(new FileWriter(tablaAmistad, true));
			pw = new PrintWriter(bw);
			// Hasta aqui las lineas que hay que copiar
			
			Scanner reader = new Scanner(tablaAmistad);  //Le paso como parámetro el fichero que quiero leer
		
		    //Leemos cada línea
		    
		    Vector<String> amigos = new Vector<String>();
		    //boolean encontrado = false;
		    while(reader.hasNextLine())
		    {
		    	String linea = reader.nextLine();
		    	String[] amigoDividido = linea.split(",");
		    	if(amigoDividido[0].equals(user.getUser()))
		    	{
		    		amigos.add(amigoDividido[1]);
		    	}
		       
		    }
		    this.usuario = user.getUser();
		    this.amigos_ = amigos;
		     //System.out.println("Iniciando sesion...\n"); 
		    pw.flush();
	 	    pw.close(); 
	 		bw.close();
		   
		}catch(Exception e) {}
	    
	    // Poner estas lineas sin cambiar nada despues de cada vez que se quiera tocar algo de la B
 		// Hasta aqui las lineas que hay que copiar
		
		return this;
	}
	
	public void mostrarAmigos() {
		int i =0;
		System.out.println("-----LISTA DE AMIGOS-----");
		if(amigos_.size()<=0) {
			System.out.println("No tiene amigos en su lista");
		}
		else {
			for(i=0; i<amigos_.size(); i++) {
				System.out.println((i+1)+" " +amigos_.get(i));
			}
		}
		System.out.println();
	}
	
	
}
