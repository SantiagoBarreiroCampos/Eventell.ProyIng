package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class GeneroFavorito{
private String user_;
private Vector<String> generos;

public String getUser()
{
	return user_;
}
public Vector<String> getGeneros()
{
	return generos;
}

//hacemos una busqueda segun el genero favorito
public GeneroFavorito buscarGenero(Usuario user)
{
	try
	{
		
		File tablaGenero = new File("generosfavoritos.csv");				
		BufferedWriter bw;
		PrintWriter pw;
		bw = new BufferedWriter(new FileWriter(tablaGenero, true));
		pw = new PrintWriter(bw);
		
		
		Scanner reader = new Scanner(tablaGenero);  //Le paso como parámetro el fichero que quiero leer
	
	    //Leemos cada línea
	    
	    Vector<String> generos = new Vector<String>();
	    //boolean encontrado = false;
	    while(reader.hasNextLine())
	    {
	    	String linea = reader.nextLine();
	    	String[] generoDividido = linea.split(",");
	    	if(generoDividido[0].equals(user.getUser()))
	    	{
	    		//System.out.println(artistaDividido[1]);
	    		generos.add(generoDividido[1]);
	    	}		       
	    }
	    this.user_ = user.getUser();
	    this.generos = generos;
	     //System.out.println("Iniciando sesion...\n"); 
	    pw.flush();
 	    pw.close(); 
 		bw.close();
	   
	}catch(Exception e) {}
 
	
	return this;
}
}

