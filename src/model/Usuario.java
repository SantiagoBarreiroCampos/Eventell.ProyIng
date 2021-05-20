package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Usuario // La linea 1 explica como guardar y bajar usuarios de la BD. Desplegar para ver
{
	Scanner sc = new Scanner(System.in);
	
	private String nombre_;
	private String apellidos_;
	private String nacimiento_;
	private String correo_;
	private String ciudad_;
	private String sexo_;
	private String user_;
	private String password_;
	private String esAdmin_;
	private boolean disponible_;
	
	public void BuscarArtista(String nombre, Usuario user) throws IOException
	{
		BufferedReader reader = null;
		String line = "";
		String cvsSplit = ",";
		String csvFile = "eventos.csv";		
		int encontrados = 0;
		int numFila = 0;
		Scanner scanner = new Scanner(System.in);
		try
		{
			int[] numEventos = new int[150];
		    reader = new BufferedReader(new FileReader(csvFile));
		    while ((line = reader.readLine()) != null)
		    {
			   String[] ficha = line.split(cvsSplit);
		       
		       if(ficha[1].equalsIgnoreCase(nombre))
		       {
		    	   if (encontrados == 0)
		    	   {
		    		   System.out.println("\nEstos son los eventos de \"" + nombre + "\":");
		    	   }
		    	   numEventos[encontrados+1] = numFila;
		    	   encontrados++;		    	   
		    	   
		    	   System.out.println("(" +encontrados+ "): " +ficha[6]+ " (" +ficha[2]+ "). " +ficha[3]);			       
		       }
		       numFila++;
		    }
		    if (encontrados == 0)
		    {
			    System.out.println("\nArtista no encontrado");
		    }
		    else
		    {
			    System.out.println("\nIntroduzca el numero del evento que desea consultar");
			    System.out.println("Pulse cualquier otra tecla para abandonar la b\u00fasqueda: ");
			    
			    int decision = sc.nextInt();			    
			    try
			    {				    	
			    	Evento auxEvento = new Evento();
			    	auxEvento.MostrarPorBusqueda(numEventos[decision], user.getUser());
			    }
			    catch(Exception e) {}
			    
			    boolean esFavorito;
			    
				try
				{
					esFavorito = ComprobarEventoFavorito(numEventos[decision], user.getUser());
				
			    	if(esFavorito == true)
			    	{
			    		System.out.println("\nPulse (1) para eliminar de eventos favoritos");
			    	}
			    	else
			    	{
			    		System.out.println("\nPulse (1) para a\u00f1adir a eventos favoritos");
			    	}
			    	System.out.println("Pulse cualquier otra tecla para abandonar la b\u00fasqueda: ");
			    	
			    	String opcion = scanner.nextLine();
			    	if (opcion.equals("1"))
			    	{
			    		if(esFavorito == true) 
			    		{
			    			// Eliminar fila de "eventosFavoritos.csv"
			    			// Primero hay que averiguar que numero es la fila
			    		}
			    		else // Falta añadir fila "usuario,IDdeevento" a "eventosFavoritos.csv"
			    		{
			    			EditarCSV editarEvFavs = new EditarCSV("eventosFavoritos.csv");
			    			boolean haCargado = editarEvFavs.cargarCSV();
			    			if(haCargado == true)
				    		{
				    			editarEvFavs.addFila(user.getUser(), 74);
				    		}
			    			else
			    			{
			    				System.out.println("No se ha podido cargar el fichero");
			    			}
			    		}
			    	}
			    	
				} catch (Exception e) { System.out.println("EXPLOSIOOOOOOOON"); }
		    }
	   }
	   catch (FileNotFoundException e)
	   {
		   e.printStackTrace();
	   }
	   catch (IOException e) {
		    e.printStackTrace();
	   }
	   finally
	   {
		   if (reader != null)
		   {
			   try
			   {
				   reader.close();
		       }
			   catch (IOException e)
			   {
		           e.printStackTrace();
		       }
		   }
	   }
	}
	public Usuario Administrador() throws IOException{
		int elec;
		
		BufferedWriter bw;
		PrintWriter pw;
		
				    	
		    	System.out.println("Se ha detectado que su usuario es administrador");
		    	System.out.println("¿Que dato desea Hacer?");
				System.out.println("Pulse (1) para a\u00f1adir Administrador "
						+ "\nPulse (2) para a\u00f1adir evento"
						+ "\nPulse (3) para eliminar evento"
						);
				elec = sc.nextInt();
				switch(elec)
				{
					case 1:
						File tablaUsuarios = new File("usuarios.csv");
						Scanner reader = new Scanner(tablaUsuarios);
						bw = new BufferedWriter(new FileWriter(tablaUsuarios, true));
						pw = new PrintWriter(bw);
						
						//sCadena.toLowerCase()
						String linea = reader.nextLine();
				    	String[] usuarioDividido = linea.split(",");
				    	
						System.out.println("Introduzca el nombre del usuario: ");
						String nombreAux = sc.next();
						if(usuarioDividido[1].equals(nombreAux)) {
							int columna = 9;
							System.out.println("llega aquí 1");
							EditarCSV CSV = new EditarCSV("usuarios.csv");
							boolean edit = CSV.modificarValor(columna, nombreAux);
						}
					break;
					case 2:
						File tablaEventos = new File("eventos.csv");
						Scanner read = new Scanner(tablaEventos);
						bw = new BufferedWriter(new FileWriter(tablaEventos, true));
						pw = new PrintWriter(bw);
						System.out.println("Introduce nombre del Artista: ");
						String ArtistaAux = sc.next();
						setNombre(ArtistaAux);
						System.out.println("Introduce ciudad: ");
						String CiudadAux = sc.next();
						setNombre(CiudadAux);
						System.out.println("Introduce fecha: ");
						String FechaAux = sc.next();
						setNombre(FechaAux);
						System.out.println("Introduce precio m\u00ednimo: ");
						String MinimoAux = sc.next();
						setNombre(MinimoAux);
						System.out.println("Introduce precio m\u00e1ximo: ");
						String MaximoAux = sc.next();
						setNombre(MaximoAux);
						System.out.println("Introduce ubicaci\u00f3n: ");
						String UbicacionAux = sc.next();
						setNombre(UbicacionAux);
						System.out.println("Introduce genero: ");
						String GeneroAux = sc.next();
						setNombre(GeneroAux);
						String lineas = "1," + ArtistaAux + "," + CiudadAux +"," +FechaAux+"," +MinimoAux+","+MaximoAux+","+UbicacionAux+","+GeneroAux;
						pw.println(lineas);
						pw.flush();
					    pw.close(); 
						bw.close();
					break;
					case 3:
						File tablaEvento = new File("eventos.csv");
						Scanner rea = new Scanner(tablaEvento);
						bw = new BufferedWriter(new FileWriter(tablaEvento, true));
						pw = new PrintWriter(bw);
						
					break;
					}
		
		return this;
	}
	public void BuscarCiudad(String city, Usuario user) throws IOException
	{	
		BufferedReader reader = null;
		String line = "";
		String cvsSplit = ",";
		String csvFile = "eventos.csv";
		int numFila = 0;
		int encontrados = 0;
				
		try
		{
			int[] numEventos = new int[150];
		    reader = new BufferedReader(new FileReader(csvFile));
		    while ((line = reader.readLine()) != null)
		    {
			   String[] ficha = line.split(cvsSplit);
		       
		       if(ficha[2].equalsIgnoreCase(city))
		       {
		    	   if (encontrados == 0)
		    	   {
		    		   System.out.println("\nEstos son los eventos de \"" + city + "\":");
		    	   }
		    	   numEventos[encontrados+1] = numFila;
		    	   encontrados++;		    	   
		    	   
		    	   System.out.println("(" +encontrados+ "): " +ficha[1] + ". " +ficha[6]+ " (" +ficha[2]+ "). " +ficha[3]);			       
		       }
		       numFila++;
		    }
		    if (encontrados == 0)
		    {
			    System.out.println("\nCiudad no encontrada");
		    }
		    else
		    {
			    System.out.println("\nIntroduzca el numero del evento que desea consultar");
		 	     System.out.println("Pulse cualquier otra tecla para abandonar la b\u00fasqueda: ");
			    
			     int decision = sc.nextInt();			    
			     try
			     {				    				    	 
			    	 Evento auxEvento = new Evento();
			    	 auxEvento.MostrarPorBusqueda(numEventos[decision], user.getUser());
			     }
			     catch(Exception e) {}
			    
			     boolean esFavorito;
			    
				 try
				 {
					 esFavorito = ComprobarEventoFavorito(numEventos[decision], user.getUser());
				
			    	 if (esFavorito == true)
			    	 {
			    		 System.out.println("\nPulse (1) para eliminar de eventos favoritos");
			    	 }
			    	 else
			    	 {
			    		 System.out.println("\nPulse (1) para a\u00f1adir a eventos favoritos");
			    	 }
			    	 System.out.println("Pulse cualquier otra tecla para abandonar la b\u00fasqueda: ");
			    	
			    	 Scanner scanner = new Scanner(System.in);
					 String opcion = scanner.nextLine();
			    	 if (opcion.equals("1"))
			    	 {
			    		 // añadir / eliminar de favoritos
			    	 }
			    	
				 } catch (Exception e) {}
		     }
	   } catch (FileNotFoundException e){
		   e.printStackTrace();
	   } catch (IOException e) {
		   e.printStackTrace();
	   } finally {
		   if (reader != null)
		   {
			   try
			   {
				   reader.close();
		       }
			   catch (IOException e)
			   {
		           e.printStackTrace();
		       }
		   }
	   }
	}
	
	public void BuscarGenero() throws IOException {
		BufferedReader reader = null;
		
		System.out.println("\nPulse (1) para hiphop/r&b"
				+ "\nPulse (2) para reggaeton"
				+ "\nPulse (3) para pop"
				+ "\nPulse (4) para hard rock/metal"
				+ "\nPulse (5) para hiphop/r&b"
				+ "\nPulse (6) para rock/metal"
				+ "\nPulse (7) para clásica"
				+ "\nPulse (8) para jazz/blues"
				+ "\nPulse (9) para pop/rock"
				+ "\nPulse (10) para world"
				+ "\nPulse (11) para dance/electronica"
				+ "\nPulse (12) para flamenco/rumba"
				+ "\nPulse (13) para indie/alternativo");
		
	// Lo voy terminar (Merche) :)
	}
	
	public void mostrarFicha() throws IOException
	{
		// Poner estas lineas sin cambiar nada antes de cada vez que se quiera tocar algo de la BD
		File tablaAmigos = new File("amistades.csv");
		Scanner reader = new Scanner(tablaAmigos);
		BufferedWriter bw;
		PrintWriter pw;
		bw = new BufferedWriter(new FileWriter(tablaAmigos, true));
		pw = new PrintWriter(bw);
		// Hasta aqui las lineas que hay que copiar
		
		boolean puedeSalir;
		do
		{
			System.out.println("\n- - - - - FICHA DE USUARIO - - - - - - -");
			System.out.println("PERFIL DE: " + this.getNombre() +" "+ this.getApellidos());
			System.out.println("Nombre de usuario: " + this.getUser());
			System.out.println("Busca conciertos desde: " + this.getCiudad());
					
			//faltarï¿½a opcion de enviarle por correo uno de tus eventos favoritos
			
			boolean esAmigo = false;
			
			while(reader.hasNextLine())
		    {
		    	String linea = reader.nextLine();
		    	String[] amistadDividida = linea.split(",");
		    	
		    	if(amistadDividida[0].equals(this.getUser()) || amistadDividida[1].equals(this.getUser()))
		    	{
		    		if(amistadDividida[0].equals(user_) || amistadDividida[1].equals(user_))
		    		{
		    			esAmigo = true;
		    		}
		    	}
		    }
			
			if(esAmigo == true)
			{
				System.out.println("Pulsa (1) para eliminar amigo");
			}
			else
			{
				System.out.println("Pulsa (1) para a\u00f1adir amigo");
			}
			System.out.println("\nPulsa (0) para regresar al men\u00fa");
			
			String eleccion = sc.nextLine();
			
			switch(eleccion)
			{
				case "0":
					puedeSalir = true;
					break;
				case "1":
					if(esAmigo == true)
					{
						// eliminar amigo, pendientes de como hacer eso en POO
					}
					else
					{						
						String nuevaLinea = user_ + "," + this.getUser();
						System.out.println("Aquí tendría que a\u00f1adir una fila con: " + nuevaLinea);
						pw.println(nuevaLinea);
					}
					puedeSalir = false;
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
	
	public Usuario ConfigurarPerfil() throws IOException
	{
		int elec;
		File tablaUsuarios = new File("usuarios.csv");
		BufferedWriter bw;
		PrintWriter pw;
		bw = new BufferedWriter(new FileWriter(tablaUsuarios, true));
		pw = new PrintWriter(bw);
		do
		{
			System.out.println("ï¿½Que dato desea a\u00f1adir?");
			System.out.println("Pulse (1) para cambiar Nombre"
					+ "\nPulse (2) para cambiar primer apellido"
					+ "\nPulse (3) para cambiar sexo"
					+ "\nPulse (4) para cambiar correo electronico"
					+ "\nPulse (5) para cambiar fecha"
					+ "\nPulse (6) para cambiar ciudad"
					+ "\nPulse (7) para cambiar nombre de usuario"
					+ "\nPulse (8) para cambiar contrase\u00f1a"
					+ "\nPulse (9) para salir");
			elec = sc.nextInt();
			switch(elec)
			{
				case 1:
					
					System.out.println("Introduzca su nombre: ");
					String CnombreAux = sc.next();
					setNombre(CnombreAux);
				break;
				case 2:
					System.out.println("Introduzca su primer apellido: ");
					String Capellido1Aux = sc.next();
					setApellidos(Capellido1Aux);
				break;
				case 3:
					System.out.println("Introduzca su sexo ");
					String CsexoAux = sc.next();
					setApellidos(CsexoAux);
				break;
				case 4:
					String CcorreoAux;
					boolean check = false;
					do
					{
						System.out.println("Introduzca su correo electronico: ");
						CcorreoAux = sc.nextLine();
						Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
								+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
						Matcher matcher = pattern.matcher(CcorreoAux);
						if (matcher.find() == false) {
							System.out.println("Correo no aceptado. Intentelo de nuevo");
						}
						else { check = true; }
					} while(check == false);
					setCorreo(CcorreoAux);	
				break;
				case 5:
					String fecha;
					String dia,mes,anyo;
					int posicion;
					int datodia,datomes,datoanyo;
					String fecha1;
					System.out.println("Introduzca su fecha de nacimiento (dd/mm/aaaa): ");
					fecha = sc.next();
					
					posicion= fecha.indexOf("/");
					dia=fecha.substring(0, posicion);
					fecha=fecha.substring(posicion+1);
					posicion= fecha.indexOf("/");
					mes=fecha.substring(0, posicion);
					fecha=fecha.substring(posicion+1);
					anyo=fecha;
					datodia=Integer.parseInt(dia);
					datomes=Integer.parseInt(mes);
					datoanyo=Integer.parseInt(anyo);
					if(1>datodia||datodia>31||1>datomes||datomes>12)
					{
						System.out.println("La fecha introducida es incorrecta");					
					}
					else {
						setNacimiento(fecha);
					}
				break;
				case 6:
					System.out.println("Introduzca su ciudad: ");
					String CciudadAux = sc.next();
					setCiudad(CciudadAux);
				break;
				case 7:
					System.out.println("Introduzca su nombre de usuario: ");
					String CnombreUAux = sc.next();
					setUser(CnombreUAux);
				break;
				case 8:
					System.out.println("Introduzca su nueva contrase\u00f1a: ");
					String Acontrasena = sc.next();
					setPassword(Acontrasena);
				break;
			}
		}
		while(elec != 9);
		
		return this;		
	}
	
	public Usuario Registrarse() throws IOException
	{
		System.out.println("Le recordamos que no podrá asistir a ciertos" +
							"\neventos reservados con Eventell si usted es" +
							"\nmenor de edad\n");
		// Poner estas lineas sin cambiar nada antes de cada vez que se quiera tocar algo de la BD
		File tablaUsuarios = new File("usuarios.csv");
		Scanner reader = new Scanner(tablaUsuarios);
		BufferedWriter bw;
		PrintWriter pw;
		bw = new BufferedWriter(new FileWriter(tablaUsuarios, true));
		pw = new PrintWriter(bw);
		// Hasta aqui las lineas que hay que copiar
		
		String correoAux;
		boolean check = false;
		do
		{
			System.out.println("Introduce un correo electronico: ");
			correoAux = sc.nextLine();
			Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher matcher = pattern.matcher(correoAux);
			if (matcher.find() == false) {
				System.out.println("Correo no aceptado. Intentelo de nuevo");
			}
			else
			{
			    boolean encontrado = false;
			    while(reader.hasNextLine())
			    {
			    	String linea = reader.nextLine();
			    	String[] usuarioDividido = linea.split(",");
			    	if(usuarioDividido[5].equals(correoAux) && usuarioDividido[0].equals("1"))
			    	{
			    		encontrado = true;
			    	}		       
			    }
			    if(encontrado == true)
			    {
			    	System.out.println("Correo ya registrado. Intente de nuevo");
			    }
			    else
			    {
			    	check = true;			    	
			    }
			}
		} while(check == false);
		setCorreo(correoAux);			
		String nombreAux;
		do
		{
			check = false;
			reader = new Scanner(tablaUsuarios);
			System.out.println("Introduce un nombre de usuario: ");
			nombreAux = sc.next();		
			boolean encontrado = false;
		    while(reader.hasNextLine())
		    {
		    	String linea = reader.nextLine();
		    	String[] usuarioDividido = linea.split(",");
		    	if(usuarioDividido[1].equals(nombreAux) && usuarioDividido[0].equals("1"))
		    	{
		    		encontrado = true;
		    	}		       
		    }
		    if(encontrado == true)
		    {
		    	System.out.println("Username ya utilizado. Intente de nuevo");
		    }
		    else
		    {
		    	check = true;			    	
		    }			
		} while(check == false);
		setUser(nombreAux);
		System.out.println("Introduce una contrase\u00f1a: ");
		String contrasenaAux = sc.next();
		setPassword(contrasenaAux);
		
		String linea = "1," + nombreAux + "," + contrasenaAux + ",,,,,,,0";
		pw.println(linea);
		
		// Poner estas lineas sin cambiar nada despues de cada vez que se quiera tocar algo de la BD
		pw.flush();
	    pw.close(); 
		bw.close();
		// Hasta aqui las lineas que hay que copiar

		return this;
	}
	
	public void DarseBaja() throws IOException
	{
		// Poner estas lineas sin cambiar nada antes de cada vez que se quiera tocar algo de la BD
		File tablaUsuarios = new File("usuarios.csv");
		Scanner reader = new Scanner(tablaUsuarios);
		BufferedWriter bw;
		PrintWriter pw;
		bw = new BufferedWriter(new FileWriter(tablaUsuarios, true));
		pw = new PrintWriter(bw);
		// Hasta aqui las lineas que hay que copiar

		System.out.println("Está seguro que desea darse de baja?");
		System.out.println("Pulse (0) para eliminar su cuenta");
		System.out.println("Pulse cualquier otra tecla para mantener su cuenta");
		
		String decision = sc.nextLine();
		if(decision.equals("0"))
		{
			while(reader.hasNextLine())
		    {
		    	String linea = reader.nextLine();
		    	String[] usuarioDividido = linea.split(",");
		    	if(usuarioDividido[1].equals(this.user_) && usuarioDividido[0].equals("1"))
		    	{
		    		int concatenar;
		    		if (esAdmin_.equals("1")) { concatenar = 1; }
		    		else { concatenar = 0; }
		    		linea = "0," + user_ + "," + password_ + "," + nombre_ + "," +
		    				apellidos_ + "," + correo_ + "," + nacimiento_ + "," +
		    				ciudad_ + "," + sexo_ + "," + concatenar;
		    		pw.println(linea);

		    		setDisponible(false);
		    		
		    		EditarCSV editarUsuarios = new EditarCSV("usuarios.csv");
		    		boolean haCargado = editarUsuarios.cargarCSV();
		    		if(haCargado == true)
		    		{
		    			int numFila = editarUsuarios.buscarCoindicencias(0, "1", 1, this.getUser());
		    			editarUsuarios.delFila(numFila-1); // Aun no va bien -> adaptar matriz
		    			System.out.println("Regresando al men\u00fa de inicio de sesi\u00f3n...");
		    		}
		    		else
		    		{
		    			System.out.println("No se encontro el archivo");
		    		}		    		
		    	}		       
		    }					
		}
		
		// Poner estas lineas sin cambiar nada despues de cada vez que se quiera tocar algo de la BD
		pw.flush();
	    pw.close(); 
		bw.close();
		// Hasta aqui las lineas que hay que copiar
	}
	
	public Usuario Login(String username, String contrasena) throws IOException
	{ 
		// Poner estas lineas sin cambiar nada antes de cada vez que se quiera tocar algo de la BD
		File tablaUsuarios = new File("usuarios.csv");				
		BufferedWriter bw;
		PrintWriter pw;
		bw = new BufferedWriter(new FileWriter(tablaUsuarios, true));
		pw = new PrintWriter(bw);
		// Hasta aqui las lineas que hay que copiar
		
		Scanner reader = new Scanner(tablaUsuarios); //Le paso como parï¿½metro el fichero que quiero leer
	
	    //Leemos cada linea
	    int lineNumber = 0;
	    boolean encontrado = false;
	    
	    while(reader.hasNextLine())
	    {	    	
	    	String linea = reader.nextLine();
	    	String[] usuarioDividido = linea.split(",");
	    	if(usuarioDividido[1].equals(username))
	    	{
	    		if(usuarioDividido[2].equals(contrasena))
	    		{
	    			if(String.valueOf(usuarioDividido[0]).equals("1")) // Si esta disponible
	    			{
	    				encontrado = true;
	    				setDisponible(true);
	    				setUser(usuarioDividido[1]);
	    				setPassword(usuarioDividido[2]);
	    				setNombre(usuarioDividido[3]);
	    				setApellidos(usuarioDividido[4]);
	    				setCorreo(usuarioDividido[5]);
	    				setNacimiento(usuarioDividido[6]); //-> No se usar tipo Date
	    				setCiudad(usuarioDividido[7]);
	    				setSexo(usuarioDividido[8]);
	    				setEsAdmin(usuarioDividido[9]);
	    			}
	    		}
	    	}
	        lineNumber++;
	    }
	    if (encontrado == false)
	    { 
	    	System.out.println("Usuario o contrase\u00f1a incorrectos.\nPulse ENTER para regresar al menu de inicio");
	    	String tiempo = sc.nextLine();
	    	
    	}
	    else { System.out.println("Iniciando sesion...\n"); }
	    
	    // Poner estas lineas sin cambiar nada despues de cada vez que se quiera tocar algo de la BD
 		pw.flush();
 	    pw.close(); 
 		bw.close();
 		// Hasta aqui las lineas que hay que copiar
		
		return this;
	}
	
	public boolean ComprobarEventoFavorito(int id, String username) throws Exception
	{
		boolean respuesta = false;
		
		BufferedReader in = null;
		String read;
    	in = new BufferedReader(new FileReader("eventosFavoritos.csv"));
	    	
    	while ((read = in.readLine()) != null)
    	{
    		String[] split = read.split(",");
    		    		
	    	if(split[0].equals(username) && split[1].equals(String.valueOf(id)))
	    	{
	    		respuesta = true;
	    	}
	    }
    	return respuesta;
	}
	
	public Usuario buscarUsuarioPorUser(String username) throws IOException
	{ 
		// Poner estas lineas sin cambiar nada antes de cada vez que se quiera tocar algo de la BD
		File tablaUsuarios = new File("usuarios.csv");				
		BufferedWriter bw;
		PrintWriter pw;
		bw = new BufferedWriter(new FileWriter(tablaUsuarios, true));
		pw = new PrintWriter(bw);
		// Hasta aqui las lineas que hay que copiar
		
		Scanner reader = new Scanner(tablaUsuarios); //Le paso como parï¿½metro el fichero que quiero leer
	
	    //Leemos cada lï¿½nea
	    int lineNumber = 0;
	    boolean encontrado = false;
	    while(reader.hasNextLine())
	    {
	    	String linea = reader.nextLine();
	    	String[] usuarioDividido = linea.split(",");
	    	if(usuarioDividido[1].equals(username))
	    	{    		
	    			if(String.valueOf(usuarioDividido[0]).equals("1")) // Si esta disponible
	    			{	    				
	    				encontrado = true;
	    				setDisponible(true);
	    				setUser(usuarioDividido[1]);
	    				setPassword(usuarioDividido[2]);
	    				setNombre(usuarioDividido[3]);
	    				setApellidos(usuarioDividido[4]);
	    				setCorreo(usuarioDividido[5]);
	    				setNacimiento(usuarioDividido[6]); //-> No se usar tipo Date
	    				setCiudad(usuarioDividido[7]);
	    				setSexo(usuarioDividido[8]);
	    				setEsAdmin(usuarioDividido[9]);
	    			}
	    	}
	        lineNumber++;
	    }
//	    if (encontrado == false)
//	    { 
//	    	System.out.println("Usuario o contraseï¿½a incorrectos.\nPulse ENTER para regresar al menu de inicio");
//	    	String tiempo = sc.nextLine();
//	    	
//    	}
	    //else { System.out.println("Iniciando sesion...\n"); }
	    
	    // Poner estas lineas sin cambiar nada despues de cada vez que se quiera tocar algo de la BD
 		pw.flush();
 	    pw.close(); 
 		bw.close();
 		// Hasta aqui las lineas que hay que copiar
		return this;
	}
	
	public String getNombre()
	{
		return nombre_;
	}
	public String getApellidos()
	{
		return apellidos_;
	}
	public String getNacimiento()
	{
		return nacimiento_;
	}
	public String getCorreo()
	{
		return correo_;
	}
	public String getCiudad()
	{
		return ciudad_;
	}
	public String getSexo()
	{
		return sexo_;
	}
	public String getUser()
	{
		return user_;
	}
	public String getPassword()
	{
		return password_;
	}	
	public boolean getDisponible()
	{
		return disponible_;
	}
	public String getEsAdmin()
	{
		return esAdmin_;
	}
	public void setNombre(String nombre)
	{
		this.nombre_ = nombre;
	}
	public void setApellidos(String apellidos)
	{
		this.apellidos_ = apellidos;
	}
	public void setCorreo(String correo)
	{
		this.correo_ = correo;
	}
	public void setUser(String username)
	{
		this.user_ = username;
	}
	public void setPassword(String contrasena)
	{
		this.password_ = contrasena;
	}
	public void setCiudad(String ciudad)
	{
		this.ciudad_ = ciudad;
	}
	public void setSexo(String sexo)
	{
		this.sexo_ = sexo;
	}
	public void setEsAdmin(String esAdmin)
	{
		this.esAdmin_ = esAdmin;
	}
	public void setDisponible(boolean disponible)
	{
		this.disponible_ = disponible;
	}
	public void setNacimiento(String nacimiento)
	{
		this.nacimiento_ = nacimiento;
	}
}