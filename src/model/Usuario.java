package model;

import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.io.File;
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
	private boolean esAdmin_;
	private boolean disponible_;
	
	public Usuario()
	{
		
	}
	
	public Usuario(int disponible, String user, String password, String nombre, String apellidos, String correo, String nacimiento, String ciudad, String sexo, int esAdmin)
	{
		this.nombre_ = nombre;
		this.apellidos_ = apellidos;
		this.nacimiento_ = nacimiento;
		this.correo_ = correo;
		this.ciudad_ = ciudad;
		this.sexo_ = sexo;
		this.user_ = user;
		this.password_ = password;
		this.esAdmin_ = (esAdmin == 1);
		this.disponible_ = (disponible == 1);
	}
	
	public void MostrarFicha(Usuario perfil) throws IOException
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
			System.out.println("- - - - - - - - - - - -");
			System.out.println("PERFIL DE: " + perfil.getNombre());
			System.out.println("Nombre de usuario: " + perfil.getUser());
			System.out.println("Busca conciertos desde: " + perfil.getCiudad());
			System.out.println("\nPulsa (0) para regresar al menú");
			
			boolean esAmigo = false;
			
			while(reader.hasNextLine())
		    {
		    	String linea = reader.nextLine();
		    	String[] amistadDividida = linea.split(",");
		    	
		    	if(amistadDividida[0].equals(perfil.getUser()) || amistadDividida[1].equals(perfil.getUser()))
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
				System.out.println("Pulsa (1) para añadir amigo");
			}
			
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
						String nuevaLinea = user_ + "," + perfil.getUser();
						System.out.println("Aquí tendría que añadir una fila con: " + nuevaLinea);
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
			System.out.println("¿Que dato desea añadir?");
			System.out.println("Pulse (1) para cambiar Nombre"
					+ "\nPulse (2) para cambiar primer apellido"
					+ "\nPulse (3) para cambiar sexo"
					+ "\nPulse (4) para cambiar correo electronico"
					+ "\nPulse (5) para cambiar fecha"
					+ "\nPulse (6) para cambiar ciudad"
					+ "\nPulse (7) para cambiar nombre de usuario"
					+ "\nPulse (8) para cambiar contraseña"
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
					if(1>datodia||datodia>31||1>datomes||datomes>12) {
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
					System.out.println("Introduzca su nueva contraseña: ");
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
		System.out.println("Introduce una contraseña: ");
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
		System.out.println(decision);
		if(decision.equals("0"))
		{
			while(reader.hasNextLine())
		    {
		    	String linea = reader.nextLine();
		    	String[] usuarioDividido = linea.split(",");
		    	if(usuarioDividido[1].equals(this.user_) && usuarioDividido[0].equals("1"))
		    	{
		    		int concatenar;
		    		if (esAdmin_ == true) { concatenar = 1; }
		    		else { concatenar = 0; }
		    		linea = "0," + user_ + "," + password_ + "," + nombre_ + "," +
		    				apellidos_ + "," + correo_ + "," + nacimiento_ + "," +
		    				ciudad_ + "," + sexo_ + "," + concatenar;
		    		pw.println(linea);
		    		// aqui falta que elimine la linea original, porque solo añade una
		    		// linea nueva de no disponible, no sobreescribe
		    		setDisponible(false);
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
		
		Scanner reader = new Scanner(tablaUsuarios); //Le paso como parámetro el fichero que quiero leer
	
	    //Leemos cada línea
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
	    				setEsAdmin(Boolean.parseBoolean(usuarioDividido[9]));
	    			}
	    		}
	    	}
	        lineNumber++;
	    }
	    if (encontrado == false)
	    { 
	    	System.out.println("Usuario o contraseña incorrectos.\nPulse ENTER para regresar al menu de inicio");
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
	public void setEsAdmin(boolean esAdmin)
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