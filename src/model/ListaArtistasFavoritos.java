package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class ListaArtistasFavoritos
{
	private String user_;
	private Vector<String> artistas_;
	
	public String getUser()
	{
		return user_;
	}
	public Vector<String> getArtistas()
	{
		return artistas_;
	}
	
	public ListaArtistasFavoritos buscarArtistas(Usuario user)
	{
		try
		{
			
			File tablaArtista = new File("artistasFavoritos.csv");				
			BufferedWriter bw;
			PrintWriter pw;
			bw = new BufferedWriter(new FileWriter(tablaArtista, true));
			pw = new PrintWriter(bw);
			
			
			Scanner reader = new Scanner(tablaArtista);  //Le paso como parámetro el fichero que quiero leer
		
		    //Leemos cada línea
		    
		    Vector<String> artistas = new Vector<String>();
		    //boolean encontrado = false;
		    while(reader.hasNextLine())
		    {
		    	String linea = reader.nextLine();
		    	String[] artistaDividido = linea.split(",");
		    	if(artistaDividido[0].equals(user.getUser()))
		    	{
		    		//System.out.println(artistaDividido[1]);
		    		artistas.add(artistaDividido[1]);
		    	}		       
		    }
		    this.user_ = user.getUser();
		    this.artistas_ = artistas;
		     //System.out.println("Iniciando sesion...\n"); 
		    pw.flush();
	 	    pw.close(); 
	 		bw.close();
		   
		}catch(Exception e) {}
	    
	    
		
		return this;
	}
	
	public void mostrarArtistas(String user) throws IOException {
		Scanner sc = new Scanner(System.in);
		int i =0;
		System.out.println("\n-----LISTA DE ARTISTAS FAVORITOS-----");
		if(artistas_.size()<=0) {
			System.out.println("No tiene artistas en su lista");
		}
		else {
			for(i=0; i<artistas_.size(); i++) {
				System.out.println((i+1)+" " +artistas_.get(i));
			}
		}
		System.out.println();
		
		System.out.println("Introduzca el numero del Artista que desea consultar");
		System.out.println("Pulse cualquier otro numero para volver al menu principal");
		String numArtista = sc.next();
		
		for (i=0; i<artistas_.size()+1; i++) {
			String num = String.valueOf(i);
			if(num.equals(numArtista)) {
				Evento eve = new Evento();
				
				//System.out.println("Se va a consultar: " + amigos_.get(numAmigo - 1));
				if(i!=0) {
					Evento ev = new Evento();
					
					Vector <Evento> evAux = ev.buscarEventosPorArtista(artistas_.get(i - 1));
					
					
					System.out.println("------FICHA DE ARTISTA------");
					System.out.println("Nombre :" + artistas_.get(i - 1));
					System.out.println("Eventos: ");
					for(i =0; i<evAux.size(); i++) {
						System.out.println("	-" + (i+1) +" "+ evAux.get(i).infoEvento());
					}
					
					System.out.println();
					
					System.out.println("Introduzca el numero del evento que desea consultar");
					System.out.println("Introduzca cualquier otra tecla para volver al menu principal");
					String numEvento = sc.next();
					
					for (i=0; i<evAux.size()+1; i++) {
						String nume = String.valueOf(i);
						if(nume.equals(numEvento)) {
							Evento even = new Evento();
							
							//System.out.println("Se va a consultar: " + amigos_.get(numAmigo - 1));
							if(i!=0) {
								Evento evAux2 = even.buscarEvento(evAux.get(i-1).getId_());
								
								
								//System.out.println("Se ha encontrado: " + user.getNombre());
								
								evAux2.mostrarFicha(user);
								
								System.out.println();
							}
						}
					}
				}
			}
		}
		

	}
}