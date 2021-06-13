package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
		//en esta funcion buscamos amigo por su usuario
	public ListaAmigos buscarAmigos(Usuario user)
	{
		try
		{
			
			File tablaAmistad = new File("amistades.csv");				
			BufferedWriter bw;
			PrintWriter pw;
			bw = new BufferedWriter(new FileWriter(tablaAmistad, true));
			pw = new PrintWriter(bw);
			
			
			Scanner reader = new Scanner(tablaAmistad);  //Le paso como par?metro el fichero que quiero leer
		
		    //Leemos cada l?nea
		    
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
		    	else if(amigoDividido[1].equals(user.getUser())) {
		    		amigos.add(amigoDividido[0]);
		    	}
		    }
		    this.usuario = user.getUser();
		    this.amigos_ = amigos;
		     //System.out.println("Iniciando sesion...\n"); 
		    pw.flush();
	 	    pw.close(); 
	 		bw.close();
	 		reader.close();
		   
		}catch(Exception e) {}
	    
		
		return this;
	}
	
	//mostramos la lista de amigos del usuario
	public void mostrarAmigos() throws IOException
	{
		int i = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("\n-----LISTA DE AMIGOS-----");
		if(amigos_.size()<=0)
		{
			System.out.println("No tiene amigos en su lista");
		}
		else
		{
			for(i=0; i<amigos_.size(); i++)
			{
				System.out.println((i+1)+" " +amigos_.get(i));
			}
		}
		System.out.println("\nIntroduzca el numero del amigo que desea consultar");
		System.out.println("Pulse cualquier otro numero para volver al menu principal");
		String numAmigo = sc.next();
		
		for (i=0; i<amigos_.size()+1; i++) {
			String num = String.valueOf(i);
			if(num.equals(numAmigo)) {
				if(i!=0) {
					Usuario user = new Usuario();			
					user.buscarUsuarioPorUser(amigos_.get(i - 1));			
					user.mostrarFicha();		
					System.out.println();
				}
			}
		}
		//sc.close();
	}
	
	
	public String consultaCorreoDeAmigos() throws IOException
	{
		int i = 0;
		String correo = null;
		Scanner sc = new Scanner(System.in);
		boolean correcto = false;
		do {
			System.out.println("\n-----LISTA DE AMIGOS-----");
			if(amigos_.size()<=0) {
				System.out.println("No tiene amigos en su lista");
			}
			else {
				for(i=0; i<amigos_.size(); i++) {
					System.out.println((i+1)+" " +amigos_.get(i));
				}
			}
			System.out.println("\nIntroduzca el numero del amigo al que desea enviarle el evento");
			//System.out.println("Pulse cualquier otro numero para volver al menu principal");
			String numAmigo = sc.next();
			
			for (i=0; i<amigos_.size()+1; i++) {
				String num = String.valueOf(i);
				if(num.equals(numAmigo)) {
					if(i!=0) {
						correcto = true;
						Usuario user = new Usuario();		
						user.buscarUsuarioPorUser(amigos_.get(i - 1));
						correo = user.getCorreo();	
						System.out.println();
					}
				}
			}
			if(correcto == false) {
				System.out.println("El numero introducido no es correcto");
			}
		}while(correcto  == false);
		
		return correo;
	}	
}