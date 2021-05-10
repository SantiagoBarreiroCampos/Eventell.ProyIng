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
			// Poner estas lineas sin cambiar nada antes de cada vez que se quiera tocar algo de la BD
			File tablaEventoF = new File("eventosFavoritos.csv");				
			BufferedWriter bw;
			PrintWriter pw;
			bw = new BufferedWriter(new FileWriter(tablaEventoF, true));
			pw = new PrintWriter(bw);
			// Hasta aqui las lineas que hay que copiar
			
			Scanner reader = new Scanner(tablaEventoF);  //Le paso como parámetro el fichero que quiero leer
						
		    //Leemos cada línea
		    Evento eventoAux = new Evento();
		    Evento eventoAux2 = new Evento();
		    Vector<String> idEventos = new Vector<String>();
		    
		    int i =0;
		    //boolean encontrado = false;
		    
		    
		    while(reader.hasNextLine())
		    {
		    	String linea = reader.nextLine();
		    	String[] evFavDividido = linea.split(",");
		    	if(evFavDividido[0].equals(user.getUser()))
		    	{
		    		String cadena = evFavDividido[1];
		    		
		    		cadena = cadena.substring(0, cadena.length());
//		    		System.out.println("CADENA "+cadena);
		    		idEventos.add(cadena);
		    	}
		       
		    }
//		    for(i =0; i<idEventos.size(); i++) {
//		    	System.out.println(idEventos.get(i));
//		    }
		    
//		    System.out.println(idEventos.get(2));
		    Vector<Evento> eventosF = new Vector<Evento>();
		    
		    
		    
		   // System.out.println("-----BUSCANDO EVENTOS FAVORITOS-----");
		    for(i =0; i<idEventos.size(); i++) {
		    	
		    	//System.out.println(idEventos.get(i));
		    	eventoAux2 = eventoAux.buscarEvento(idEventos.get(i));
		    	
		    	//System.out.println(eventoAux2.infoEvento());
		    	
		    	eventosF.add(eventoAux2);
		    	//eventosF.add(new Evento().buscarEvento(idEventos.get(i).toString()));
		    	//System.out.println(eventosF.get(i).getArtista());
		    }
		    
		    
//		    System.out.println("XXXXX" + eventosF.get(0).infoEvento());
//		    for(i =0; i<eventos_.size(); i++) {
//		    	System.out.println(eventos_.get(i).getArtista());
//		    }
		    
		    this.user_ = user.getUser();
		    this.eventos_ = eventosF;
		    this.idEventos_ = idEventos;
		     //System.out.println("Iniciando sesion...\n"); 
		    pw.flush();
	 	    pw.close(); 
	 		bw.close();
		   
		}catch(Exception e) {}
	    
	    // Poner estas lineas sin cambiar nada despues de cada vez que se quiera tocar algo de la B
 		// Hasta aqui las lineas que hay que copiar
		
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
			//System.out.println("Tiene");
			for(i=0; i<eventos_.size(); i++) {
				System.out.println((i+1)+" " + eventos_.get(i).infoEvento());
			}
		}
		System.out.println();
		
		System.out.println("Introduzca el numero del evento que desea consultar");
		System.out.println("Pulse cualquier otro numero para volver al menu principal");
		int numEvento = sc.nextInt();
		if(numEvento <= 0 || numEvento > eventos_.size()) {
			
		}
		else {
			Evento ev = new Evento();
			
			//System.out.println("Se va a consultar: " + amigos_.get(numAmigo - 1));
			
			Evento evAux = ev.buscarEvento(idEventos_.get(numEvento - 1));
			
			//System.out.println("Se ha encontrado: " + user.getNombre());
			
			evAux.mostrarFicha(user);
			
			System.out.println();
		}
	}
}
