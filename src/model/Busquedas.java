package model;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Busquedas {
	
	private Scanner sc = new Scanner(System.in);

	public void buscarUsuario(Usuario buscador)
	{
		System.out.println("Introduzca el username del amigo que busca: ");
		String buscado = sc.nextLine();
		
		Evento ev = new Evento();
		Usuario encontrado = ev.buscarUsuarios(buscado, buscador);		
		
		if(encontrado.getUser().equals("kkppqqss"))
		{
			System.out.println("Usuario no encontrado\nRegresando al menú...\n");
		}
		else
		{
			try
			{
				//encontrado.MostrarInfo();
				//System.out.println("El buscador es: " + buscador.getUser());
				encontrado.mostrarFicha();				
			}
			catch(Exception e)
			{
				System.out.println("Error interpretando el usuario\nRegresando al menú...\n");
				e.printStackTrace();
			};
		}
	}
	
	public void buscarArtista(String user) throws IOException
	{
		int i;
		System.out.println("Introduzca el nombre del artista");				
		String nombre = sc.nextLine();
		
		Evento ev = new Evento();
		Vector <Evento> evAux = ev.buscarEventosPorArtista(nombre);
		
		System.out.println("------FICHA DE ARTISTA------");
		System.out.println("Nombre: " + nombre);
		System.out.println("Eventos: ");
		for(i = 0; i<evAux.size(); i++)
		{
			System.out.println("   (" + (i+1) + ") "+ evAux.get(i).infoEvento());
		}
		
		System.out.println();
		
		System.out.println("Introduzca el numero del evento que desea consultar");
		System.out.println("Pulse cualquier otro n\u00famero para volver al menu principal");
		int numEvento = sc.nextInt();
		if(numEvento <= 0 || numEvento > evAux.size())
		{
			
		}
		else
		{
			Evento eve = new Evento();
			
			Evento evAux2 = eve.buscarEvento(evAux.get(numEvento - 1).getId_());
			
			evAux2.mostrarFicha(user);
			
			System.out.println();
		}
	}

	public void buscarCiudad(String user) throws IOException
	{
		System.out.println("Introduzca el nombre de la cuidad");				
		String city = sc.nextLine();
		
		int i;
		Evento ev = new Evento();
		Vector <Evento> evAux = ev.buscarEventosPorCiudad(city);
		
		System.out.println("------EVENTOS EN "+city+"------");
		for(i =0; i<evAux.size(); i++)
		{
			System.out.println((i+1) +" "+ evAux.get(i).infoEvento());
		}
		
		System.out.println();
		
		System.out.println("Introduzca el numero del evento que desea consultar");
		System.out.println("Pulse cualquier otro n\u00famero para volver al menu principal");
		int numEvento = sc.nextInt();
		if(numEvento <= 0 || numEvento > evAux.size())
		{
			
		}
		else
		{
			Evento eve = new Evento();
			
			Evento evAux2 = eve.buscarEvento(evAux.get(numEvento - 1).getId_());
			
			evAux2.mostrarFicha(user);
			
			System.out.println();
		}		
	}
	
	public void buscarGenero(String user) throws IOException
	{
		int i;
		System.out.println("\nElija uno de los siguientes géneros:"
				+ "\nPulse (1) hiphop/r&b"
				+ "\nPulse (2) reggaeton"
				+ "\nPulse (3) pop/rock"
				+ "\nPulse (4) hard rock/metal"
				+ "\nPulse (5) clásica"
				+ "\nPulse (6) jazz/blues"
				+ "\nPulse (7) world"
				+ "\nPulse (8) dance/electronica"
				+ "\nPulse (9) flamenco/rumba"
				+ "\nPulse (10) indie/alternativo");
				
		String genero = sc.nextLine();
		Vector<String> artistas = new Vector<String>();
		Evento evento = new Evento();
		
		if(genero.equals("1")) {
			artistas = evento.buscarArtistasPorGenero("hiphop/r&b");
		}
		else if(genero.equals("2")) {
			artistas = evento.buscarArtistasPorGenero("reggaeton");
		}
		else if(genero.equals("3")) {
			artistas = evento.buscarArtistasPorGenero("pop/rock");
		}
		else if(genero.equals("4")) {
			artistas = evento.buscarArtistasPorGenero("hard rock/metal");
		}
		else if(genero.equals("5")) {
			artistas = evento.buscarArtistasPorGenero("clásica");
		}
		else if(genero.equals("6")) {
			artistas = evento.buscarArtistasPorGenero("jazz/blues");
		}
		else if(genero.equals("7")) {
			artistas = evento.buscarArtistasPorGenero("world");
		}
		else if(genero.equals("8")) {
			artistas = evento.buscarArtistasPorGenero("dance/electronica");
		}
		else if(genero.equals("9")) {
			artistas = evento.buscarArtistasPorGenero("flamenco/rumba");
		}
		else if(genero.equals("10")) {
			artistas = evento.buscarArtistasPorGenero("indie/alternativo");
		}
		
		System.out.println("\n-----LISTA DE ARTISTAS POR GENERO-----");
		if(artistas.size()<=0)
		{
			System.out.println("No hay artistas por este genero");
		}
		else
		{
			for(i=0; i<artistas.size(); i++)
			{
				System.out.println((i+1)+" " +artistas.get(i));
			}
		}
		System.out.println();
		
		System.out.println("Introduzca el numero del Artista que desea consultar");
		System.out.println("Pulse cualquier otro numero para volver al menu principal");
		int numArtista = sc.nextInt();
		
		if(numArtista <= 0 || numArtista > artistas.size())
		{
			
		}
		else
		{
			Evento ev = new Evento();
			//System.out.println("Todo bien");
			//System.out.println("Se va a consultar: " + amigos_.get(numAmigo - 1));
			//System.out.println(artistas_.get(numArtista - 1));
			Vector <Evento> evAux = ev.buscarEventosPorArtista(artistas.get(numArtista - 1));
			
			//System.out.println("Se ha encontrado: " + user);
			
			System.out.println("------FICHA DE ARTISTA------");
			System.out.println("Nombre :" + artistas.get(numArtista - 1));
			System.out.println("Eventos: ");
			for(i =0; i<evAux.size(); i++)
			{
				System.out.println("	-" + (i+1) +" "+ evAux.get(i).infoEvento());
			}
			
			System.out.println();
			
			System.out.println("Introduzca el numero del evento que desea consultar");
			System.out.println("Pulse cualquier otro n\u00famero para volver al menu principal");
			int numEvento = sc.nextInt();
			if(numEvento <= 0 || numEvento > evAux.size())
			{
				
			}
			else
			{
				Evento eve = new Evento();
				
				//System.out.println("Se va a consultar: " + amigos_.get(numAmigo - 1));
				
				Evento evAux2 = eve.buscarEvento(evAux.get(numEvento - 1).getId_());
				
				//System.out.println("Se ha encontrado: " + user.getNombre());
				
				evAux2.mostrarFicha(user);
				
				System.out.println();
			}
		}
	}
	public void buscarPrecio()
	{
		
	}
}