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

public class Usuario
{
	Scanner sc = new Scanner(System.in);

	Usuario userMain_;
	
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

	public void MostrarInfo()
	{
		System.out.println("USUARIO: "+this.getDisponible()+","+this.getPassword()+","+this.getNombre()
			+","+this.getApellidos()+","+this.getCorreo()+","+this.getNacimiento()+","+this.getCiudad()
			+","+this.getSexo()+","+this.getEsAdmin());
	}
	
	public Usuario Administrador() throws IOException
	{
		File tablaAmigos = new File("amistades.csv");
		BufferedWriter bw;
		PrintWriter pw;
		int elec;

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

	public void mostrarFicha() throws IOException
	{
		File tablaAmigos = new File("amistades.csv");
		Scanner reader = new Scanner(tablaAmigos);

		boolean puedeSalir;
		do
		{
			System.out.println("\n- - - - - FICHA DE USUARIO - - - - - - -");
			System.out.println("PERFIL DE: " + this.getNombre() +" "+ this.getApellidos());
			System.out.println("Nombre de usuario: " + this.getUser());
			System.out.println("Busca conciertos desde: " + this.getCiudad());

			boolean esAmigo = false;
			while(reader.hasNextLine())
			{
				String linea = reader.nextLine();
				String[] amistadDividida = linea.split(",");
				
				if(amistadDividida[0].equals(this.getUser()) && amistadDividida[1].equals(this.getUserMain().getUser()))
				{
					esAmigo = true;
				}
				else
				{
					if(amistadDividida[0].equals(this.getUserMain().getUser()) && amistadDividida[1].equals(this.getUser()))
					{
						esAmigo = true;
					}					
				}
			}

			if(esAmigo == true)
			{
				System.out.println("\nPulsa (1) para eliminar amigo");
			}
			else
			{
				System.out.println("\nPulsa (1) para a\u00f1adir amigo");
			}
			System.out.println("Pulsa (0) para regresar al men\u00fa");

			String eleccion = sc.nextLine();

			switch(eleccion)
			{
			case "0":
				puedeSalir = true;
				break;
			case "1":
				if(esAmigo == true)
				{
					EditarCSV editarAmistades = new EditarCSV("amistades.csv");
					boolean haCargado = editarAmistades.cargarCSV();
					if(haCargado == true)
					{
						int numFila = editarAmistades.buscarCoindicencias(0, this.getUser(), 1, this.getUserMain().getUser());
						if(numFila == -70)
						{
							numFila = editarAmistades.buscarCoindicencias(0, this.getUserMain().getUser(), 1, this.getUser());
						}
						editarAmistades.delFila(numFila-1);
					}
					System.out.println("\nUsuario eliminado de amigos correctamente\nRegresando al menú...\n");
				}
				else
				{						
					EditarCSV editarAmistades = new EditarCSV("amistades.csv");
					boolean haCargado = editarAmistades.cargarCSV();
					if(haCargado == true)
					{
						editarAmistades.addFila(this.getUserMain().getUser(), this.getUser());
					}
					System.out.println("\nUsuario añadido a amigos correctamente"
							+ "\nRegresando al menú...\n");
				}
				
				puedeSalir = true;
				break;
			default:
				System.out.println("Dato introducido incorrecto. Intente de nuevo");
				puedeSalir = false;
				break;
			}
		} while(puedeSalir == false);

		reader.close();
	}

	public Usuario ConfigurarPerfil() throws IOException
	{
		int numFila = 0;		
		String eleccion;
		do
		{
			System.out.println("ï¿½Que dato desea a\u00f1adir?");
			System.out.println("\nPulse (1) para terminar la configuración"
							+ "\nPulse (2) para cambiar su contrase\u00f1a"
							+ "\nPulse (3) para cambiar su nombre"
							+ "\nPulse (4) para cambiar sus apellidos"
							+ "\nPulse (5) para cambiar su correo electronico"
							+ "\nPulse (6) para cambiar su fecha de nacimiento"
							+ "\nPulse (7) para cambiar su ciudad de preferencia"
							+ "\nPulse (8) para cambiar su sexo");		
			eleccion = sc.nextLine();
			
			switch(eleccion)
			{
				case "1":
					System.out.println("Regresando al menú principal...");
					break;
				case "2":
					System.out.println("Introduzca una nueva contraseña: ");
					String pass1 = sc.nextLine();
					System.out.println("Confirme la nueva contraseña: ");
					String pass2 = sc.nextLine();
					if(pass1.equals(pass2))
					{
						this.setPassword(pass2);
						System.out.println("OK. Sus datos se actualizarán cuando regrese al menú princial\n");
					}
					else
					{
						System.out.println("Las contraseñas deben coincidir\n");
					}
					break;
				case "3":
					System.out.println("Introduzca un nuevo nombre: ");
					this.setNombre(sc.nextLine());	
					System.out.println("OK. Sus datos se actualizarán cuando regrese al menú princial\n");
					break;
				case "4":
					System.out.println("Introduzca nuevos apellidos: ");
					this.setApellidos(sc.nextLine());
					System.out.println("OK. Sus datos se actualizarán cuando regrese al menú princial\n");
					break;
				case "5":
					System.out.println("Introduzca un nuevo correo: ");
					this.setCorreo(sc.nextLine());
					System.out.println("OK. Sus datos se actualizarán cuando regrese al menú princial\n");
					break;
				case "6":
					System.out.println("Introduzca un año de nacimiento: ");
					String year = sc.nextLine();
					String mes = sc.nextLine();
					String dia = sc.nextLine();
					String fecha = dia +"/"+ mes +"/"+ year;
					this.setNacimiento(fecha);
					System.out.println("OK. Sus datos se actualizarán cuando regrese al menú princial\n");
					break;
				case "7":
					System.out.println("Introduzca una nueva ciudad: ");
					this.setCiudad(sc.nextLine());	
					System.out.println("OK. Sus datos se actualizarán cuando regrese al menú princial\n");
					break;
				case "8":
					System.out.println("Introduzca un nuevo sexo: ");
					this.setSexo(sc.nextLine());	
					System.out.println("OK. Sus datos se actualizarán cuando regrese al menú princial\n");
					break;
				default:
					System.out.println("Dato introducido incorrecto. Intente de nuevo");
			}
			
		} while(!eleccion.equals("1"));
			
		EditarCSV editarEvFavs = new EditarCSV("usuarios.csv");
		boolean haCargado = editarEvFavs.cargarCSV();
		if(haCargado == true)
		{
			editarEvFavs.addFila(this);
			numFila = editarEvFavs.buscarCoindicencias(1, this);
			editarEvFavs.delFila(numFila-1);
			System.out.println("Perfil actualizado correctamente\nRegresando al menú...");		    		
		}
		else
		{
			System.out.println("Lo sentimos, algo ha ocurrido con el fichero");
		}

		return this;		
	}

	public Usuario Registrarse() throws IOException
	{
		System.out.println("Le recordamos que no podrá asistir a ciertos eventos" +
				"\nreservados con Eventell si es menor de edad\n");
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
			System.out.println("Recuerde que el username no podrá modificarse");
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

	public int ComprobarEventoFavorito(int id, String username) throws Exception
	{
		int respuesta = -1;

		BufferedReader in = null;
		String read;
		in = new BufferedReader(new FileReader("eventosFavoritos.csv"));

		int i = 0;
		while ((read = in.readLine()) != null)
		{
			String[] split = read.split(",");

			if(split[0].equals(username) && split[1].equals(String.valueOf(id)))
			{
				respuesta = i;
			}
			i++;
		}
		in.close();

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
		
		int lineNumber = 0; //Leemos cada lï¿½nea
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
	public Usuario getUserMain()
	{
		return userMain_;
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
	public void setUserMain(Usuario userMain)
	{
		this.userMain_ = userMain;
	}
}