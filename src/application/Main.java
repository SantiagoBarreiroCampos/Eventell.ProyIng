package application;

import model.Busquedas;
import model.EditarCSV;
import model.Evento;
import model.GeneroFavorito;
import model.ListaAmigos;
import model.ListaArtistasFavoritos;
import model.ListaEventosFavoritos;
import model.ListaUsuarios;
import model.Recomendaciones;
import model.Usuario;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;

public class Main
{
	static Usuario sesionIniciada = new Usuario(); // Este se usara para que, una vez logeado, el sistema sepa con que datos trabajar
	
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);		
		boolean encontrado = false;
		
		do
		{
			Usuario usuarioAux = new Usuario();
			String eleccion1;		
			System.out.println("- - - BIENVENIDO A EVENTELL - - -");
			System.out.println("Pulse (1) para registrarse"
							+ "\nPulse (2) para iniciar sesion");
			System.out.println("Pulse (0) para salir del programa");
			eleccion1 = sc.next();
				
			switch(eleccion1)
			{
				case "0":					
					encontrado = true;
					System.out.println("Acaba de abandonar el sistema EVENTELL. Un saludo");
					break;
				case "1":					
					sesionIniciada = usuarioAux.Registrarse();
					Menu(getSesionIniciada());
					break;
				case "2":
					System.out.println("Introduzca su nombre de usuario:");
					String username = sc.next();
					System.out.println("Introduzca su contrase\u00f1a:");
					String contrasena = sc.next();
					sesionIniciada = usuarioAux.Login(username, contrasena);
					if(sesionIniciada.getUser() != null && sesionIniciada.getDisponible() == true && sesionIniciada.getEsAdmin().equals("1"))
					{
						usuarioAux.Administrador();
					}
					
					else if(sesionIniciada.getUser() != null && sesionIniciada.getDisponible() == true)
					{
						encontrado = true;
						Menu(sesionIniciada);
						encontrado = false;
					}
					break;
				default:
					System.out.println("El valor introducido no es correcto. Por favor, intentelo de nuevo\n");
			}
		} while(encontrado == false);
		
		sc.close();
	}
	
	public static void Menu(Usuario sesionIniciada) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		String eleccion2 = "A";
		String SesionIniciada = sesionIniciada.getUser();
		ListaUsuarios listaUsuarios = new ListaUsuarios();
		listaUsuarios.RellenarVector();
		boolean volverAlMenu = true;
		
		do
		{
			System.out.println(" - - - - MENU - - - - - [Accedido desde: " + sesionIniciada.getUser() + "]");
			System.out.println("Pulse (0) para cerrar sesion"
							+ "\nPulse (1) para cambiar ajustes de perfil"
							+ "\nPulse (2) para ver lista de amigos"
							+ "\nPulse (3) para ver lista de artistas favoritos"
							+ "\nPulse (4) para ver lista de eventos favoritos"
							+ "\nPulse (5) para iniciar una busqueda"
							+ "\nPulse (6) para solicitar sugerencias de eventos"
							+ "\nPulse (7) para darse de baja en el sistema"
							+ "\nPulse (8) para contactar a soporte al usuario");
			eleccion2 = sc.next();
				
			switch(eleccion2)
			{
				case "0":					
					eleccion2 = "0";
					sesionIniciada = null;
					System.out.println("Acaba de cerrar sesi\u00f3n. Regrasará al men\u00fa de inicio");
					volverAlMenu = false;
					break;
					
				case "1":
					sesionIniciada.ConfigurarPerfil();
					break;
					
				case "2":					
					ListaAmigos listaAux = new ListaAmigos();
					ListaAmigos lista = listaAux.buscarAmigos(sesionIniciada);
					lista.mostrarAmigos();
					break;
					
				case "3":					
					ListaArtistasFavoritos listaAux2 = new ListaArtistasFavoritos();
					ListaArtistasFavoritos lista2 = listaAux2.buscarArtistas(sesionIniciada);
					lista2.mostrarArtistas(sesionIniciada.getUser());
					break;
					
				case "4":					
					ListaEventosFavoritos listaAux3 = new ListaEventosFavoritos();
					ListaEventosFavoritos lista3 = listaAux3.buscarEventos(sesionIniciada);
					lista3.mostrarEventos(sesionIniciada.getUser());
					break;
					
				case "5":
					Busquedas busqueda = new Busquedas();
					
					Scanner sc1 = new Scanner(System.in);
					String eleccion;
					
					System.out.println("\nPulse (1) si desea buscar un artista"
							+ "\nPulse (2) si desea buscar eventos por cuidad"
							+ "\nPulse (3) si desea buscar eventos por genero musical"
							+ "\nPulse (4) si desea buscar eventos por precio maximo"
							+ "\nPulse (5) si desea buscar eventos por fecha"
							+ "\nPulse (6) si desea buscar otros usuarios");
					
					eleccion = sc1.nextLine();
				
					switch(eleccion)
					{
						case "1":
							busqueda.buscarArtista();
							break;
						case "2":
							busqueda.buscarCiudad();
							break;
						case "3":
							busqueda.buscarGenero();
							break;
						case "4":
							busqueda.buscarPrecio();
							break;
						case "5":
							busqueda.buscarFecha();
							break;	
						case "6":
							busqueda.buscarUsuario(sesionIniciada);
							break;						
						default:
							System.out.println("Valor introducido incorrecto.");
					}
					break;
				case "6":
					 Usuario user = sesionIniciada.buscarUsuarioPorUser(sesionIniciada.getUser());
					Recomendaciones recomendaciones = new Recomendaciones(user.getCiudad(),user);
					recomendaciones.ListarEventos();
				
					
					break;
				case "7":
					sesionIniciada.DarseBaja();
					volverAlMenu = sesionIniciada.getDisponible();
					break;
				case "8":
					System.out.println("Si tiene algun problema con el fubcionamiento de la aplicacion"
							+ "\nNo dude en ponerse en contacto con nosotros"
							+ "\nEmail: uemeventell@gmail.com");
					break;
				default:				
					System.out.println("El valor introducido no es correcto. Por favor, intentelo de nuevo\n");
			}
		} while(volverAlMenu == true);		
	}
	public static Usuario getSesionIniciada()
	{
		return sesionIniciada;
	}
}