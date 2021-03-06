package model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import application.Main;

public class Busquedas {
	
	private Scanner sc = new Scanner(System.in);
	
//En este metodo buscamos un usuario para a?adirlo a amigos 
	public void buscarUsuario(Usuario buscador)
	{
		System.out.println("Introduzca el username del amigo que busca: ");
		String buscado = sc.nextLine();
		
		Evento ev = new Evento();
		Usuario encontrado = ev.buscarUsuarios(buscado, buscador);		
		
		if(encontrado.getUser().equals("kkppqqss"))
		{
			System.out.println("Usuario no encontrado\nRegresando al men?...\n");
		}
		else
		{
			try
			{
				encontrado.mostrarFicha();				
			}
			catch(Exception e)
			{
				System.out.println("Error interpretando el usuario\nRegresando al men?...\n");
				e.printStackTrace();
			};
		}
	}
	
	//Hacemos una busqueda de eventos segun el nombre de algun artista
	public void buscarArtista() throws IOException
	{
		Scanner reader = new Scanner(new File("artistasFavoritos.csv"));
		
		System.out.println("Introduzca el nombre del artista");				
		String nombre = sc.nextLine();
		
		Evento ev = new Evento();
		Vector <Evento> evAux = ev.buscarEventosPorArtista(nombre);
		
		System.out.println("------FICHA DE ARTISTA------");
		System.out.println("Nombre: " + nombre);
		System.out.println("Eventos: ");
		for(int i = 0; i < evAux.size(); i++)
		{
			System.out.println("   (" + (i+1) + ") " + evAux.get(i).infoEvento());
		}
		System.out.println("\nPulse el n?mero del evento que desea consultar");
		
		boolean esFavorito = false;
		while (reader.hasNextLine())
		{
			String linea = reader.nextLine();
			String[] lineaDividida = linea.split(",");

			if(lineaDividida[0].equals(Main.getSesionIniciada().getUser()) && lineaDividida[1].equals(nombre))
			{
				esFavorito = true;
			}
		}
		if(esFavorito == true)
		{
			System.out.println("Pulse (0) para eliminar artista de favoritos");
		}
		else
		{
			System.out.println("Pulse (0) para a?adir artista a favoritos");
		}
		
		System.out.println("Pulse cualquier otra tecla para volver al men? principal");
		
		String eleccion = sc.nextLine();
		try
		{
			int numEleccion = Integer.parseInt(eleccion);
			
			if(numEleccion == 0)
			{
				EditarCSV editarFavoritos = new EditarCSV("artistasFavoritos.csv");
				boolean haCargado = editarFavoritos.cargarCSV();
				if(haCargado == true)
				{
					if(esFavorito == true)
					{
						int numFila = editarFavoritos.buscarCoindicencias(0, Main.getSesionIniciada().getUser(), 1, nombre);
						editarFavoritos.delFila(numFila-1);
						System.out.println("\nArtista eliminado de favoritos correctamente\nRegresando al men?...\n");
					}
					else
					{
						editarFavoritos.addFila(Main.getSesionIniciada().getUser(), nombre);
						System.out.println("\nUsuario a?adido a favoritos correctamente\nRegresando al men?...\n");
					}
				}
				else
				{
					System.out.println("Algo pas? con el fichero");
				}
			}
			else
			{
				if(numEleccion < 0 || numEleccion > evAux.size())
				{
					System.out.println("Dato introducido incorrecto\nRegresando al men?...\n");
				}
				else
				{
					Evento eve = new Evento();		
					Evento evAux2 = eve.buscarEvento(evAux.get(numEleccion - 1).getId_());					
					evAux2.mostrarFicha(Main.getSesionIniciada().getUser());			
				}				
			}
		}
		catch(Exception e)
		{
			System.out.println("Dato introducido incorrecto\nRegresando al men?...\n");
		}
		reader.close();
	}

	//Hacemos una busqueda de eventos segun la ciudad
	public void buscarCiudad() throws IOException
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
		try {
			int numEvento = sc.nextInt();
			if(numEvento <= 0 || numEvento > evAux.size())
			{
				
			}
			else
			{
				Evento eve = new Evento();		
				Evento evAux2 = eve.buscarEvento(evAux.get(numEvento - 1).getId_());			
				evAux2.mostrarFicha(Main.getSesionIniciada().getUser());
				
				System.out.println();
			}		
		}catch(Exception e) {
		}
	}
	
	//hacemos una busqueda de eventos segun el precio
	public void buscarPrecio() throws IOException
	{
		System.out.println("Introduzca el precio maximo");				
		String precio = sc.nextLine();
		
		int i;
		Evento ev = new Evento();
		Vector <Evento> evAux = ev.buscarEventosPorPrecio(precio);
		
		System.out.println("------EVENTOS POR "+precio+"?------");
		for(i =0; i<evAux.size(); i++)
		{
			System.out.println((i+1) +" "+ evAux.get(i).infoEvento());
		}
		
		System.out.println();
		
		System.out.println("Introduzca el numero del evento que desea consultar");
		System.out.println("Pulse cualquier otro n\u00famero para volver al menu principal");
		try {
			int numEvento = sc.nextInt();
			if(numEvento <= 0 || numEvento > evAux.size())
			{
				
			}
			else
			{
				Evento eve = new Evento();		
				Evento evAux2 = eve.buscarEvento(evAux.get(numEvento - 1).getId_());			
				evAux2.mostrarFicha(Main.getSesionIniciada().getUser());
				
				System.out.println();
			}	
		}catch(Exception e) {}
	}
	
	//Hacemos una busqueda de evnetos segun las fechas
	public void buscarFecha() throws IOException
	{
		System.out.println("Introduzca dos fechas entre las que buscar eventos:");	
		System.out.println("Fecha 1:");
		String fecha1 = sc.next();
		System.out.println("Fecha 2:");
		String fecha2 = sc.next();
		
		int i;
		Evento ev = new Evento();
		Vector <Evento> evAux = ev.buscarEventosPorFecha(fecha1, fecha2);
		
		System.out.println("------EVENTOS ENTRE "+fecha1+" Y "+fecha2+"------");
		for(i =0; i<evAux.size(); i++)
		{
			System.out.println((i+1) +" "+ evAux.get(i).infoEvento());
		}
		
		System.out.println();
		
		System.out.println("Introduzca el numero del evento que desea consultar");
		System.out.println("Pulse cualquier otro n\u00famero para volver al menu principal");
		try {
			int numEvento = sc.nextInt();
			if(numEvento <= 0 || numEvento > evAux.size())
			{
				
			}
			else
			{
				Evento eve = new Evento();		
				Evento evAux2 = eve.buscarEvento(evAux.get(numEvento - 1).getId_());			
				evAux2.mostrarFicha(Main.getSesionIniciada().getUser());
				
				System.out.println();
			}	
		}catch(Exception e) {}
	}
	
	//Hacemos una busqueda de eventos segun el genero 
	public void buscarGenero() throws IOException
	{
		int i;
		System.out.println("\nElija uno de los siguientes g?neros:"
				+ "\nPulse (1) hiphop/r&b"
				+ "\nPulse (2) reggaeton"
				+ "\nPulse (3) pop/rock"
				+ "\nPulse (4) hard rock/metal"
				+ "\nPulse (5) cl?sica"
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
			artistas = evento.buscarArtistasPorGenero("cl?sica");
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
		try {
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
				try {
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
						
						evAux2.mostrarFicha(Main.getSesionIniciada().getUser());
						
						System.out.println();
					}
				}catch(Exception e) {}
			}
		}catch(Exception e) {}
	}
}