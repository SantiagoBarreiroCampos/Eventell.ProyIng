package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class ListaEventosFavoritos {

	private String user_;
	private Vector<Evento> eventos_;
	
	public ListaEventosFavoritos buscarEventos(Usuario user) {
		try {
			// Poner estas lineas sin cambiar nada antes de cada vez que se quiera tocar algo de la BD
			File tablaEventoF = new File("eventosFavoritos.csv");				
			BufferedWriter bw;
			PrintWriter pw;
			bw = new BufferedWriter(new FileWriter(tablaEventoF, true));
			pw = new PrintWriter(bw);
			// Hasta aqui las lineas que hay que copiar
			
			Scanner reader = new Scanner(tablaEventoF);  //Le paso como par�metro el fichero que quiero leer
			
			
		    //Leemos cada l�nea
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
		    		idEventos.add(evFavDividido[1]);
		    	}
		       
		    }
//		    System.out.println(idEventos.get(2));
//		    Vector<Evento> eventosF = new Vector<Evento>();
		    System.out.println("-----LISTA DE EVENTOS FAVORITOS-----");
		    for(i =0; i<idEventos.size(); i++) {
		    	eventoAux2 = eventoAux.buscarEvento(idEventos.get(i).toString());
		    	
		    	System.out.println(eventoAux2.infoEvento());
		    	
//		    	eventosF.add(eventoAux2);
//		    	System.out.println(eventosF.get(i).getArtista());
		    }
//		    System.out.println("XXXXX" + eventosF.get(0).infoEvento());
//		    for(i =0; i<eventos_.size(); i++) {
//		    	System.out.println(eventos_.get(i).getArtista());
//		    }
		    
		    this.user_ = user.getUser();
//		    this.eventos_ = eventosF;
		     //System.out.println("Iniciando sesion...\n"); 
		    pw.flush();
	 	    pw.close(); 
	 		bw.close();
		   
		}catch(Exception e) {}
	    
	    // Poner estas lineas sin cambiar nada despues de cada vez que se quiera tocar algo de la B
 		// Hasta aqui las lineas que hay que copiar
		
		return this;
	}
	
	
	public void mostrarEventos() {
		int i =0;
		System.out.println("-----LISTA DE EVENTOS FAVORITOS-----");
		if(eventos_.size()<=0) {
			System.out.println("No tiene eventos en su lista");
		}
		else {
			for(i=0; i<eventos_.size(); i++) {
				System.out.println((i+1)+" " + eventos_.get(i).infoEvento());
			}
		}
		System.out.println();
	}
}
