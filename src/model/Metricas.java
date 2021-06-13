package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import application.Main;

public class Metricas {
	
	Scanner sc = new Scanner(System.in);
	Vector<Usuario> user = new Vector<Usuario>();
	
	public  Metricas(){
		this.user = new Vector<Usuario>();
	}
	public Metricas menuMetricas() throws IOException {
		String elec = "";
		
		do
		{
			System.out.println("Se ha detectado que su usuario es administrador");
			System.out.println("¿Qué desea hacer?");
			System.out.println("Pulse (0) para salir"
					+ "\nPulse (1) Saber cuantos usuarios hay registrados en la aplicación"
					+ "\nPulse (2) Saber cuantos eventos hay en la aplicación"
					+ "\nPulse (3) Saber la edad media de los usuarios");
			elec = sc.nextLine();
			switch(elec)
			{			
				case "1":
					usuarios();
					break;
				case "2":
					Eventos();
					break;
				case "3":
					edadmedia();
					break;
				default:
					System.out.println("Valor introducido incorrecto. Intente de nuevo\n");
					
			}			
		} while(elec == "0");
		
		
		return this;
	}
	// En este metodo sacamos el numero de usuarios que tenemos 
	public Metricas usuarios() throws IOException {
		FileReader fr = new FileReader("usuarios.csv");//leemos el fichero
		BufferedReader bf = new BufferedReader(fr);
		int numusuari = (int) bf.lines().count(); //cuenta las líneas del fichero 
		
		System.out.println("Hay un total de "+numusuari+" usuarios.");
		
		
		return this;
	}
	//En este metodo sacamos el numero total de eventos que tenmmos en nuestra aplicacion 
	public Metricas Eventos() throws IOException {
		FileReader fr = new FileReader("eventos.csv");//leemos el fichero
		BufferedReader bf = new BufferedReader(fr);
		int numevento = (int) bf.lines().count(); //contamos el numero de filas del fichero
		System.out.println("Hay un total de "+numevento+" eventos.");
	return this;	
	}
	
	//En este metodo sacamos la edad media de los usuarios
	public Metricas edadmedia() throws IOException {
		int posicion;
		int media;
		int edad = 0;
		int suma = 0;
		//leemos el el fichero
		File tablaUsuarios = new File("usuarios.csv");				
		BufferedWriter bw;
		PrintWriter pw;
		bw = new BufferedWriter(new FileWriter(tablaUsuarios, true));
		pw = new PrintWriter(bw);
		

		Scanner reader = new Scanner(tablaUsuarios); 

		int lineNumber = 0;
		

		while(reader.hasNextLine())
		{	    	
			String linea = reader.nextLine();
			String[] usuarioDividido = linea.split(",");
			Usuario usAux = new Usuario();
				
						
						usAux.setDisponible(true);
						usAux.setUser(usuarioDividido[1]);
						usAux.setPassword(usuarioDividido[2]);
						usAux.setNombre(usuarioDividido[3]);
						usAux.setApellidos(usuarioDividido[4]);
						usAux.setCorreo(usuarioDividido[5]);
						usAux.setNacimiento(usuarioDividido[6]); 
						usAux.setCiudad(usuarioDividido[7]);
						usAux.setSexo(usuarioDividido[8]);
						usAux.setEsAdmin(usuarioDividido[9]);
						user.add(usAux); //lo añadimos al vector user
				
			
			lineNumber++;
		}
		
		for(int i = 0 ; i < user.size(); i++) { //recorremos el vector
			String fecha = user.get(i).getNacimiento(); //sacamos la fecha de nacimiento
			posicion = fecha.indexOf("/");
			String dia = fecha.substring(0,posicion);
			fecha = fecha.substring(posicion+1);		//aqui se separa la fecha de nacimiento en dia mes y año
			posicion = fecha.indexOf("/");
			String mes = fecha.substring(0,posicion);
			fecha = fecha.substring(posicion+1);
			String anio = fecha;						
			int numEntero = Integer.parseInt(anio);		//lo pasamos a tipo int para poder operar
			edad =  2021 - numEntero;					//sacamos los años de cada usuario
			suma = suma + edad;							//sumamos todas las edades 
		}
		try{
			media = suma / user.size();					//hacemos la media
			System.out.println("La edad media de los usuarios es: "+ media);
		} catch(Exception e){
			
		}

		pw.flush();
		pw.close(); 
		bw.close();
		
		
		return this;
	}
	
	
}
