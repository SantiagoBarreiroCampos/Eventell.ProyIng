package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;

import javax.mail.MessagingException;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class Evento
{
	Scanner sc = new Scanner(System.in);

	private String id_;
	private String artista_;
	private String ciudad_;
	private String lugar_;
	private String fecha_;
	private String precioMax_;
	private String precioMin_;
	private String genero_;

	public String infoEvento()
	{
		return (artista_ + " en " + ciudad_ + ", día " + fecha_);
	}
	
	
	public int generarID()
	{
		int resultado = -665;
		try
		{
			Scanner reader = new Scanner(new File("eventos.csv"));
			while(reader.hasNextLine())
			{
				String linea = reader.nextLine();
				String[] eventoDividido = linea.split(",");
				
				int idAux = Integer.parseInt(eventoDividido[0]);
				if(idAux > resultado)
				{
					resultado = idAux;
				}
			}
			reader.close();
		}
		catch(Exception e) { System.out.println("Problemas con el fichero"); }
		return resultado+1;
	}
	public Evento buscarEvento(String id)
	{
		BufferedReader reader = null;
		String line = "";
		String cvsSplit = ",";
		String csvFile = "eventos.csv";

		Evento evAux = new Evento();
		try
		{
			reader = new BufferedReader(new FileReader(csvFile));
			while ((line = reader.readLine()) != null)
			{      
				String[] eventoDividido = line.split(cvsSplit);

				if(eventoDividido[0].equals(id))
				{ 
					evAux.setId_(eventoDividido[0]);
					evAux.setArtista_(eventoDividido[1]);
					evAux.setCiudad_(eventoDividido[2]);
					evAux.setFecha_(eventoDividido[3]);
					evAux.setPrecioMin_(eventoDividido[4]);
					evAux.setPrecioMax_(eventoDividido[5]);
					evAux.setLugar_(eventoDividido[6]);
					evAux.setGenero_(eventoDividido[7]);
				}		       
			}
			reader.close();
			return evAux;

		}catch(Exception e) { System.out.println("Problemas con el fichero"); }

		return evAux;
	}
	
	public int buscarEvento(String artista, String fecha)
	{
		int filaBorrar = -70;
		int contador = 0;
		try
		{
			Scanner reader = new Scanner(new File("eventos.csv"));
			while(reader.hasNextLine())
			{
				String linea = reader.nextLine();
				String[] eventoDividido = linea.split(",");
				
				if(eventoDividido[1].equals(artista) && eventoDividido[3].equals(fecha))
				{
					filaBorrar = contador; 
				}
				contador++;
			}
		} catch(Exception e) { System.out.println("Problemas con el fichero"); }
		
		return filaBorrar;
	}

	public Vector<Evento> buscarEventosPorArtista(String Artista)
	{
		Vector<Evento> eventos = new Vector<Evento>();
		BufferedReader reader = null;
		String line = "";
		String cvsSplit = ",";
		String csvFile = "eventos.csv";

		try
		{
			reader = new BufferedReader(new FileReader(csvFile));

			while ((line = reader.readLine()) != null)
			{      
				String[] eventoDividido = line.split(cvsSplit);

				if(eventoDividido[1].equals(Artista))
				{
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
			}
			reader.close();
			return eventos;

		}catch(Exception e) {}

		return eventos;
	}
	
	public Vector<Evento> buscarEventosPorPrecio(String Precio)
	{
		Vector<Evento> eventos = new Vector<Evento>();
		BufferedReader reader = null;
		String line = "";
		String cvsSplit = ",";
		String csvFile = "eventos.csv";

		try
		{
			reader = new BufferedReader(new FileReader(csvFile));

			while ((line = reader.readLine()) != null)
			{      
				String[] eventoDividido = line.split(cvsSplit);
				
				float precioEvento = Float.parseFloat(eventoDividido[5]);
				float precioIntroducido = Float.parseFloat(Precio);
				
				if(precioEvento <= precioIntroducido)
				{
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
			}
			reader.close();
			return eventos;

		}catch(Exception e) {
			System.out.println("ERROR");
		}

		return eventos;
	}
	
	public Vector<Evento> buscarEventosPorFecha(String fecha1, String fecha2)
	{
		Vector<Evento> eventos = new Vector<Evento>();
		BufferedReader reader = null;
		String line = "";
		String cvsSplit = ",";
		String csvFile = "eventos.csv";

		try
		{
			reader = new BufferedReader(new FileReader(csvFile));

			while ((line = reader.readLine()) != null)
			{      
				String[] eventoDividido = line.split(cvsSplit);
				
				
				Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(fecha1);  
				Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(fecha2); 
				Date dateAct=new SimpleDateFormat("dd/MM/yyyy").parse(eventoDividido[3]);  
				
				//System.out.println(dateAct);
				
				if(date2.after(dateAct) && date1.before(dateAct))
				{
					//System.out.println("ENTRA");
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
			}
			reader.close();
			return eventos;

		}catch(Exception e) {
			System.out.println("ERROR");
		}

		return eventos;
	}
	
	public Vector<Evento> buscarEventosPorCiudad(String Ciudad)
	{
		Vector<Evento> eventos = new Vector<Evento>();
		BufferedReader reader = null;
		String line = "";
		String cvsSplit = ",";
		String csvFile = "eventos.csv";

		try
		{
			reader = new BufferedReader(new FileReader(csvFile));

			while ((line = reader.readLine()) != null)
			{      
				String[] eventoDividido = line.split(cvsSplit);

				if(eventoDividido[2].equals(Ciudad))
				{
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
			}
			reader.close();
			return eventos;

		}catch(Exception e) {}

		return eventos;
	}
	
	public Vector<String> buscarArtistasPorGenero(String Genero)
	{
		Vector<String> artistas = new Vector<String>();
		BufferedReader reader = null;
		String line = "";
		String cvsSplit = ",";
		String csvFile = "eventos.csv";		
		String artistaActual = "";

		try
		{
			reader = new BufferedReader(new FileReader(csvFile));

			while ((line = reader.readLine()) != null)
			{      
				String[] eventoDividido = line.split(cvsSplit);

				if(eventoDividido[7].equals(Genero) && !(artistaActual.equals(eventoDividido[1])))
				{
					artistaActual = eventoDividido[1];
					artistas.add(artistaActual);
				}
			}
			reader.close();
			return artistas;

		}catch(Exception e) {}

		return artistas;
	}
	
	public Usuario buscarUsuarios(String buscado, Usuario buscador)
	{
		Usuario encontrado = new Usuario();
		encontrado.setUser("kkppqqss");
		encontrado.setUserMain(buscador);
		BufferedReader reader = null;
		String line = "";
		String cvsSplit = ",";
		String csvFile = "usuarios.csv";

		try
		{
			reader = new BufferedReader(new FileReader(csvFile));
			while ((line = reader.readLine()) != null)
			{      
				String[] usuarioDividido = line.split(cvsSplit);
				
				if(usuarioDividido[1].equals(buscado))
				{
					encontrado.setDisponible(true);
					encontrado.setUser(usuarioDividido[1]);
					encontrado.setNombre(usuarioDividido[3]);
					encontrado.setApellidos(usuarioDividido[4]);
					encontrado.setCorreo(usuarioDividido[5]);
					encontrado.setNacimiento(usuarioDividido[6]);
					encontrado.setCiudad(usuarioDividido[7]);
					encontrado.setUserMain(buscador);
				}
			}
			reader.close();
		}
		catch(Exception e) { System.out.println("Algo pasó con el fichero"); }

		return encontrado;
	}

	public void mostrarFicha(String user) throws IOException
	{
		// Poner estas lineas sin cambiar nada antes de cada vez que se quiera tocar algo de la BD
		Scanner sc2 = new Scanner(System.in);
		File tablaEventos = new File("eventosFavoritos.csv");
		Scanner reader = new Scanner(tablaEventos);
		BufferedWriter bw;
		PrintWriter pw;
		bw = new BufferedWriter(new FileWriter(tablaEventos, true));
		pw = new PrintWriter(bw);
		// Hasta aqui las lineas que hay que copiar

		boolean puedeSalir = false;
		do
		{
			System.out.println("\n- - - - - - FICHA DE EVENTO - - - - - -");
			System.out.println("Genero: " + this.getGenero_());
			System.out.println("Artista: " + this.getArtista_());
			System.out.println("Ciudad: " + this.getCiudad_());
			System.out.println("Lugar: " + this.getLugar_());
			System.out.println("Fecha: " + this.getFecha_());
			System.out.println("Precios: " + this.getPrecioMin_() +"€ - "+ this.getPrecioMax_() +"€");

			boolean esFavorito = false;
			while(reader.hasNextLine())
			{
				String linea = reader.nextLine();
				String[] eventoDividida = linea.split(",");

				if(eventoDividida[0].equals(user) && eventoDividida[1].equals(this.getId_()))
				{	    		
					esFavorito = true;	    		
				}
			}

			if(esFavorito == true)
			{
				System.out.println("\nPulsa (1) para eliminar de favoritos");
			}
			else
			{
				System.out.println("\nPulsa (1) para a\u00f1adir a favoritos");
			}

			System.out.println("Pulsa (2) para compartir el evento");			
			System.out.println("Pulsa (0) para regresar al men\u00fa");

			String eleccion = sc2.next();
			switch(eleccion)
			{
			case "0":
				puedeSalir = true;
				break;
			case "1":
				try
				{
					Usuario usuarioAux = new Usuario();
					int esFav = usuarioAux.ComprobarEventoFavorito(Integer.parseInt(this.getId_()), user);

					EditarCSV editarEvFavs = new EditarCSV("eventosFavoritos.csv");
					boolean haCargado = editarEvFavs.cargarCSV();

					if(haCargado == true)
					{
						if(esFav == -1) 
						{
							editarEvFavs.addFila(user, Integer.parseInt(this.getId_()));
							System.out.println("Evento añadido a favoritos correctamente\nRegresando al menú...\n");
						}
						else 
						{
							int numFila = editarEvFavs.buscarCoindicencias(0, user, 1, this.getId_());
							editarEvFavs.delFila(numFila-1);
							System.out.println("Evento eliminado de favoritos correctamente\nRegresando al menú...\n");
						}		    		
					}
					else
					{
						System.out.println("Lo sentimos, algo ha ocurrido con el fichero");
					}

				} catch (Exception e) { System.out.println("EXPLOSIOOOOOOOON"); }
				puedeSalir = true;
				break;								
			case "2":
				Mail correo = new Mail();
				ListaAmigos amigos = new ListaAmigos();
				Usuario userAux = new Usuario();
				userAux.buscarUsuarioPorUser(user);
				amigos.buscarAmigos(userAux);
				String correoAmigo = amigos.consultaCorreoDeAmigos();
				correo.enviarCorreo(correoAmigo, user, this);
				puedeSalir = true;
				break;				
			default:
				System.out.println("Dato introducido incorrecto. Intente de nuevo");
				puedeSalir = false;
				break;
			}
		} while(puedeSalir == false);

		// Poner estas lineas sin cambiar nada despues de cada vez que se quiera tocar algo de la BD
		pw.flush();
		pw.close(); 
		bw.close();
		// Hasta aqui las lineas que hay que copiar
	}

	public String getId_() {
		return id_;
	}

	public void setId_(String id_) {
		this.id_ = id_;
	}

	public String getArtista_() {
		return artista_;
	}

	public void setArtista_(String artista_) {
		this.artista_ = artista_;
	}

	public String getCiudad_() {
		return ciudad_;
	}

	public void setCiudad_(String ciudad_) {
		this.ciudad_ = ciudad_;
	}

	public String getLugar_() {
		return lugar_;
	}

	public void setLugar_(String lugar_) {
		this.lugar_ = lugar_;
	}

	public String getFecha_() {
		return fecha_;
	}

	public void setFecha_(String fecha_) {
		this.fecha_ = fecha_;
	}

	public String getPrecioMax_() {
		return precioMax_;
	}

	public void setPrecioMax_(String precioMax_) {
		this.precioMax_ = precioMax_;
	}

	public String getPrecioMin_() {
		return precioMin_;
	}

	public void setPrecioMin_(String precioMin_) {
		this.precioMin_ = precioMin_;
	}

	public String getGenero_() {
		return genero_;
	}

	public void setGenero_(String genero_) {
		this.genero_ = genero_;
	}
}