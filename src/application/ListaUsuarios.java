package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import model.Usuario;

public class ListaUsuarios
{
	private Vector<Usuario> usuarios = new Vector<Usuario>();
	
	public void RellenarVector() throws IOException
	{
		// Poner estas lineas sin cambiar nada antes de cada vez que se quiera tocar algo de la BD
		File tablaUsuarios = new File("usuarios.csv");
		Scanner reader = new Scanner(tablaUsuarios);
		BufferedWriter bw;
		PrintWriter pw;
		bw = new BufferedWriter(new FileWriter(tablaUsuarios, true));
		pw = new PrintWriter(bw);
		// Hasta aqui las lineas que hay que copiar
		
		while(reader.hasNextLine())
	    {
			Usuario aux = new Usuario();
			
	    	String linea = reader.nextLine();
	    	String[] usuarioDividido = linea.split(",");

	    	aux.setDisponible(usuarioDividido[0].equals("1"));
	    	aux.setUser(usuarioDividido[1]);
	    	aux.setPassword(usuarioDividido[2]);
	    	aux.setNombre(usuarioDividido[3]);
	    	aux.setApellidos(usuarioDividido[4]);
	    	aux.setCorreo(usuarioDividido[5]);
	    	aux.setNacimiento(usuarioDividido[6]);
	    	aux.setCiudad(usuarioDividido[7]);
	    	aux.setSexo(usuarioDividido[8]);
	    	aux.setEsAdmin(usuarioDividido[9]);
	    	
	    	usuarios.add(aux);
	    }
	}
	
	public void MostrarVector()
	{
		for(int i = 0; i < usuarios.capacity() - 1; i++)
		{
			System.out.println(usuarios.get(i).getUser());
		}
	}
	
	public Usuario getUsuario(int i)
	{
		return usuarios.get(i);
	}
	
	public int getCapacidad()
	{
		return usuarios.capacity();
	}
}