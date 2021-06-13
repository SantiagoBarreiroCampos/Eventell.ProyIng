package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class ListaEventosFavoritos {

	private String user_;
	private Vector<Evento> eventos_;
	private Vector<String> idEventos_;
	
	public ListaEventosFavoritos buscarEventos(Usuario user) {
		try {
			
			File tablaEventoF = new File("eventosFavoritos.csv");				
			BufferedWriter bw;
			PrintWriter pw;
			bw = new BufferedWriter(new FileWriter(tablaEventoF, true));
			pw = new PrintWriter(bw);
			
			
			Scanner reader = new Scanner(tablaEventoF);  //Le paso como parámetro el fichero que quiero leer
						
		    //Leemos cada línea
		    Evento eventoAux = new Evento();
		    Evento eventoAux2 = new Evento();
		    Vector<String> idEventos = new Vector<String>();
		    
		    int i =0;
		    
		    while(reader.hasNextLine())
		    {
		    	String linea = reader.nextLine();
		    	String[] evFavDividido = linea.split(",");
		    	if(evFavDividido[0].equals(user.getUser()))
		    	{
		    		String cadena = evFavDividido[1];
		    		
		    		cadena = cadena.substring(0, cadena.length());
		    		idEventos.add(cadena);
		    	}
		       
		    }
		    Vector<Evento> eventosF = new Vector<Evento>();
		    
		    for(i =0; i<idEventos.size(); i++) {
		    	
		    	eventoAux2 = eventoAux.buscarEvento(idEventos.get(i));
		    	
		    	
		    	eventosF.add(eventoAux2);
		    }
		    
		    this.user_ = user.getUser();
		    this.eventos_ = eventosF;
		    this.idEventos_ = idEventos;
		    
		    pw.flush();
	 	    pw.close(); 
	 		bw.close();
		   
		}catch(Exception e) {}
		return this;
	}
	
	
	public void mostrarEventos(String user) throws IOException {
		Scanner sc = new Scanner(System.in);
		int i =0;
		System.out.println("\n-----LISTA DE EVENTOS FAVORITOS-----");
		if(eventos_.size()<=0) {
			System.out.println("No tiene eventos en su lista");
		}
		else {
			for(i=0; i<eventos_.size(); i++) {
				System.out.println((i+1)+" " + eventos_.get(i).infoEvento());
			}
		}
		System.out.println();
		
		System.out.println("Introduzca el n\u00famero del evento que desea consultar");
		System.out.println("Pulse cualquier otro n\u00famero para volver al menu principal");
		String numEvento = sc.next();
		
		for (i=0; i<eventos_.size()+1; i++) {
			String num = String.valueOf(i);
			if(num.equals(numEvento)) {
				
				//System.out.println("Se va a consultar: " + amigos_.get(numAmigo - 1));
				if(i!=0) {
					Evento ev = new Evento();
					
					Evento evAux = ev.buscarEvento(idEventos_.get(i - 1));
					
					evAux.mostrarFicha(user);
					
					System.out.println();
				}
			}
		}

	}
}
