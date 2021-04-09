package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;

public class Main
{
	public static void Registrarse() throws IOException
	{
		Fichero();
	}
	public static void LogIn() throws IOException
	{
		String ruta = "usuarios.csv"; // Se supone que ya está creado, si no lo está se crea usando createNewFile()
		File usuariosCSV = new File(ruta);
		Scanner in = new Scanner(System.in);
		
		BufferedWriter bw;
		PrintWriter pw;
		bw = new BufferedWriter(new FileWriter(usuariosCSV, true));
		pw = new PrintWriter(bw); 

		System.out.println("Nombre de usuario:");
		String username = in.next();
		System.out.println("Contraseña:");
		String contrasena = in.next();
		
		pw.flush(); //Esto limpia el stream para que pueda ser utilizado más adelante si se quiere
			
		//Leer datos en el fichero		
		Scanner reader = new Scanner(usuariosCSV);  //Le paso como parámetro el fichero que quiero leer
	
	    //Leemos cada línea
	    int lineNumber = 0;
	    boolean encontrado = false;
	    while(reader.hasNextLine())
	    {
	    	String linea = reader.nextLine();
	    	String[] usuarioDividido = linea.split(",");
	    	if(usuarioDividido[5].equals(username))
	    	{
	    		if(usuarioDividido[6].equals(contrasena))
	    		{
	    			encontrado = true;
	    			Menu();	    			
	    		}
	    	}
	        lineNumber++;
	    }
	    if (encontrado == false) { System.out.println("\nUsuario o contraseña incorrectos. Va a regresar al menu de inicio\n"); }	    
	    
	    pw.close(); // Cerramos todos los objetos input y output
		bw.close();
		//in.close();
		//reader.close();
	}
	public static void LogOut()
		{
			System.out.println("Estas en la funcion LogOut(). Pulsa cualquier tecla para volver al menu");
			Scanner sc = new Scanner(System.in);
			String a = sc.nextLine();
			Menu();
		}
	public static void Menu()
	{
		System.out.println(" - - - - - MENU - - - - -");
	}
	public static String concatenar(String e[], String delimitador)
	{
		String linea="";
		for(int i = 0; i < e.length; i++)
		{
			if(i!=e.length-1)
				linea = linea + e[i] + delimitador;
			else
				linea = linea + e[i];	
		}
		return linea;
	}
	public static void Fichero() throws IOException
	{
		String ruta = "usuarios.csv"; // Se supone que ya está creado, si no lo está se crea usando createNewFile()
		File usuariosCSV = new File(ruta);
		Scanner in = new Scanner(System.in);
		
		BufferedWriter bw;
		PrintWriter pw;
		bw = new BufferedWriter(new FileWriter(usuariosCSV, true));
		pw = new PrintWriter(bw); 
		
		String[] usuarioDividido = new String[8];
		// Guardamos datos en el fichero
		String linea;
		System.out.println("Introduzca los datos del estudiante: ");
		System.out.println("Nombre:");
		usuarioDividido[0] = in.next();			
		System.out.println("Apellidos:");
		usuarioDividido[1] = in.next();	
		System.out.println("Correo:");
		usuarioDividido[2] = in.next();	
		System.out.println("Fecha de nacimiento:");
		usuarioDividido[3] = in.next();
		System.out.println("Ciudad:");
		usuarioDividido[4] = in.next();
		System.out.println("Nombre de usuario:");
		usuarioDividido[5] = in.next();
		System.out.println("Contraseña:");
		usuarioDividido[6] = in.next();
		System.out.println("Sexo:");
		usuarioDividido[7] = in.next();

		linea = concatenar(usuarioDividido,","); // Concatenamos para guardar con un ; como delimitador
		pw.println(linea);			
		pw.flush(); //Esto limpia el stream para que pueda ser utilizado más adelante si se quiere
	    
	    // Cerramos todos los objetos input y output
	    pw.close(); 
		bw.close();
		//in.close();
		//reader.close();	
	}	
	public static void main(String args[]) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		String eleccion;
		do
		{
			System.out.println("BIENVENIDO A EVENTELL");
			System.out.println("Pulse (1) para registrarse\nPulse (2) para iniciar sesion");
				eleccion = sc.nextLine();
				
			switch(eleccion)
			{
				case "1":
					Registrarse();
					break;
				case "2":
					LogIn();
					break;
				default:
					System.out.println("El valor introducido no es correcto. Por favor, intentelo de nuevo\n");
			}
		} while(1 > 0); // Esto es provisional para que no se me salga del programa
	}
}