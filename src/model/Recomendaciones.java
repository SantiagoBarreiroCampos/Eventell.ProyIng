package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

import application.Main;

public class Recomendaciones {
	private String ciudad;
	private Usuario user;
	private ListaArtistasFavoritos lista;
	private GeneroFavorito genero;
	private Vector<String>idEventos = new Vector<String>();
	
	
public Recomendaciones(String ciudad, Usuario user) {
	this.ciudad = ciudad;
	this.user = user;
	ListaArtistasFavoritos listaAux = new ListaArtistasFavoritos();
	this.lista = listaAux.buscarArtistas(user);
	GeneroFavorito generoAux = new GeneroFavorito();
	this.genero = generoAux.buscarGenero(user);
}


public Vector<Evento> ListarEventos() throws IOException
	{  
	Vector<Evento> eventos = new Vector<Evento>();
	Vector<EventoRecomendado> eventosRecomendados = new Vector<EventoRecomendado>();
	BufferedReader reader1 = null;
	String line = "";
	String cvsSplit = ",";
	String csvFile = "eventos.csv";
	int resultado;

	try
	{
		reader1 = new BufferedReader(new FileReader(csvFile));

		while ((line = reader1.readLine()) != null)
		{      
		
			String[] eventoDividido = line.split(cvsSplit);
						
			
				Evento evAux = new Evento();
				evAux.setId_(eventoDividido[0]);
				evAux.setArtista_(eventoDividido[1]);
				evAux.setCiudad_(eventoDividido[2]);
				evAux.setFecha_(eventoDividido[3]);
				evAux.setPrecioMin_(eventoDividido[4]);
				evAux.setPrecioMax_(eventoDividido[5]);
				evAux.setLugar_(eventoDividido[6]);
				evAux.setGenero_(eventoDividido[7]);
				eventos.add(evAux);
				
		}
		reader1.close();
		
		
		for(int i = 0; i < eventos.size();i++) {
			resultado = ComprobarCiudad(eventos.get(i)) + ComprobarArtista(eventos.get(i)) + ComprobarGenero(eventos.get(i));
			EventoRecomendado eventorecomendado = new EventoRecomendado(eventos.get(i), resultado);
			eventosRecomendados.add(eventorecomendado);
			
		}
	Comparator<EventoRecomendado> compareById = (EventoRecomendado o1, EventoRecomendado o2) -> o1.getResultado() - ( o2.getResultado());
		Collections.sort(eventosRecomendados, compareById);
		//Collections.sort(eventosRecomendados, Collections.reverseOrder());
		for(int i = eventosRecomendados.size()-1; i > eventosRecomendados.size() - 6; i --) {
			//System.out.println(eventosRecomendados.get(i).getResultado());
			//System.out.println(eventosRecomendados.get(i).getEvento().infoEvento());
			idEventos.add(eventosRecomendados.get(i).getEvento().getId_());
		}
		
		mostrarEventos(user.getUser(), eventosRecomendados);
		
	}catch(Exception e) {
		
	}
	return eventos;
	
} 
public int ComprobarCiudad (Evento evento){
	//buscamos la ciudad del usuario que se corresponde con los eventos
			if(evento.getCiudad_().equals(ciudad)) {
				
				return 1;
			}
			
			return 0;
}
public int ComprobarArtista(Evento evento) throws IOException {
	for(int i = 0; i<lista.getArtistas().size(); i++) {
		if(lista.getArtistas().get(i).equals(evento.getArtista_())) {
			
			return 1;
		}
	}
	return 0;
}
public int ComprobarGenero(Evento evento)  {
	for(int i = 0; i<genero.getGeneros().size(); i++) {
		if (genero.getGeneros().get(i).equals(evento.getGenero_())) {
		return 1;
		}
	}
	return 0;
}
public void mostrarEventos(String user, Vector<EventoRecomendado>eventos) throws IOException {
	Scanner sc = new Scanner(System.in);
	int i =0;
	int num = 1;
	System.out.println("\n-----LISTA DE EVENTOS RECOMENDADOS-----");
	if(eventos.size()<=0) {
		System.out.println("No tiene eventos en su lista");
	}
	else {
		
		for( i = eventos.size()-1; i > eventos.size() - 6; i --) {
			System.out.println((num)+" " + eventos.get(i).getEvento().infoEvento());
			num++;
		}
	}
	System.out.println();
	
	System.out.println("Introduzca el n\u00famero del evento que desea consultar");
	System.out.println("Pulse cualquier otro n\u00famero para volver al menu principal");
	int numEvento = sc.nextInt();
	if(numEvento <= 0 || numEvento > eventos.size()) {
		
	}
	else {
		Evento ev = new Evento();
		
		Evento evAux = ev.buscarEvento(idEventos.get(numEvento - 1));
		
		evAux.mostrarFicha(user);
		
		System.out.println();
	}
}
}