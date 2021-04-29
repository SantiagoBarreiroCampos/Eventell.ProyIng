	// String[] usuarioDividido = new String[8]; -> Cada vez que se cree un usuario sera el un array de 9 espacios
	// Cada espacio correspondera a un campo, ver en la BD que numero es cada uno
	//System.out.println("Nombre: ");
	//usuarioDividido[0] = sc.next();			
	//System.out.println("Apellidos: ");
	//usuarioDividido[1] = sc.next();	
	//System.out.println("Correo: ");
	//usuarioDividido[2] = sc.next();	
	//System.out.println("Fecha de nacimiento: ");
	//usuarioDividido[3] = sc.next();
	//System.out.println("Ciudad: ");
	//usuarioDividido[4] = sc.next();
	//System.out.println("Nombre de usuario: ");
	//usuarioDividido[5] = sc.next();
	//System.out.println("Contraseña: ");
	//usuarioDividido[6] = sc.next();
	//System.out.println("Sexo: ");
	//usuarioDividido[7] = sc.next();
	//usuarioDividido[8] = "0"; -> Si es admin o no. Por defecto decir que no
	// String linea = concatenar(usuarioDividido,","); // Concatenamos para guardar con coma como delimitador
												// Este String con todo sera lo que hay que grabar en el CSV
package model;

import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
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
	
	public Usuario(String nombre, String apellidos, String nacimiento, String correo, String ciudad, String sexo, String user, String password)
	{ // HAY QUE REORDENAR ESTO DE ACUERDO AL ORDEN DEL CSV
		this.nombre_ = nombre;
		this.apellidos_ = apellidos;
		this.nacimiento_ = nacimiento;
		this.correo_ = correo;
		this.ciudad_ = ciudad;
		this.sexo_ = sexo;
		this.user_ = user;
		this.password_ = password;
	}
	
	public void MostrarFicha()
	{
		// Aquí tu parte, Merche. Son basicamente muchos println
	}
	
	public Usuario ConfigurarPerfil() throws IOException
	{
		int elec;
		File tablaUsuarios = new File("usuarios.csv");
		BufferedWriter bw;
		PrintWriter pw;
		bw = new BufferedWriter(new FileWriter(tablaUsuarios, true));
		pw = new PrintWriter(bw);
		do {
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
			else { check = true; }
		} while(check == false);
		setCorreo(correoAux);			
		System.out.println("Introduce un nombre de usuario: ");
		String nombreAux = sc.next();
		setUser(nombreAux);
		System.out.println("Introduce una contraseña: ");
		String contrasenaAux = sc.next();
		setPassword(contrasenaAux);
		
		String linea = "1,"+nombreAux+"," + contrasenaAux + ",,,,,,,0";
		pw.println(linea);
		
		// Poner estas lineas sin cambiar nada despues de cada vez que se quiera tocar algo de la BD
		pw.flush();
	    pw.close(); 
		bw.close();
		// Hasta aqui las lineas que hay que copiar

		return this;
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