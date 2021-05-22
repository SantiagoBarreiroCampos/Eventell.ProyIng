package application;

import model.EditarCSV;
import model.ListaAmigos;
import model.ListaArtistasFavoritos;
import model.ListaEventosFavoritos;
import model.ListaUsuarios;
import model.Usuario;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;

public class Main
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		
		Usuario SesionIniciada = new Usuario(); // Este se usara para que, una vez logeado, el sistema sepa con que datos trabajar
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
					SesionIniciada = usuarioAux.Registrarse();
					Menu(SesionIniciada);
					break;
				case "2":
					System.out.println("Introduzca su nombre de usuario:");
					String username = sc.next();
					System.out.println("Introduzca su contrase\u00f1a:");
					String contrasena = sc.next();
					SesionIniciada = usuarioAux.Login(username, contrasena);
					if(SesionIniciada.getUser() != null && SesionIniciada.getDisponible() == true && SesionIniciada.getEsAdmin().equals("1"))
					{
						Usuario AdminAux = new Usuario();
						Usuario Admin = AdminAux.Administrador();
					}
					
					else if(SesionIniciada.getUser() != null && SesionIniciada.getDisponible() == true)
					{
						encontrado = true;
						Menu(SesionIniciada);
						encontrado = false;
					}
					break;
				default:
					System.out.println("El valor introducido no es correcto. Por favor, intentelo de nuevo\n");
			}
		} while(encontrado == false);
		
		sc.close();
	}
	
	public static void Menu(Usuario user) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		String eleccion2;
		String SesionIniciada = user.getUser();
		ListaUsuarios listaUsuarios = new ListaUsuarios();
		listaUsuarios.RellenarVector();
		boolean volverAlMenu = true;
		
		do
		{
			System.out.println(" - - - - MENU - - - - - [Accedido desde: " + user.getUser() + "]");
			System.out.println("Pulse (0) para cerrar sesion"
							+ "\nPulse (1) para cambiar ajustes de perfil"
							+ "\nPulse (2) para ver lista de amigos"
							+ "\nPulse (3) para ver lista de artistas favoritos"
							+ "\nPulse (4) para ver lista de eventos favoritos"
							+ "\nPulse (5) para iniciar una busqueda"
							+ "\nPulse (6) para solicitar sugerencias de eventos"
							+ "\nPulse (7) para darse de baja en el sistema");
			eleccion2 = sc.nextLine();
				
			switch(eleccion2)
			{
				case "0":					
					eleccion2 = "0";
					user = null;
					System.out.println("Acaba de cerrar sesi\u00f3n. Regrasará al men\u00fa de inicio");
					volverAlMenu = false;
					break;
					
				case "1":
					user.ConfigurarPerfil();
					break;
					
				case "2":					
					ListaAmigos listaAux = new ListaAmigos();
					ListaAmigos lista = listaAux.buscarAmigos(user);
					lista.mostrarAmigos();
					break;
					
				case "3":					
					ListaArtistasFavoritos listaAux2 = new ListaArtistasFavoritos();
					ListaArtistasFavoritos lista2 = listaAux2.buscarArtistas(user);
					lista2.mostrarArtistas(user.getUser());
					break;
					
				case "4":					
					ListaEventosFavoritos listaAux3 = new ListaEventosFavoritos();
					ListaEventosFavoritos lista3 = listaAux3.buscarEventos(user);
					lista3.mostrarEventos(user.getUser());
					break;
					
				case "5":
					Scanner sc1 = new Scanner(System.in);
					String eleccion;
					
					System.out.println("\nPulse (1) si desea buscar un evento por artista"
							+ "\nPulse (2) si desea buscar un evento por cuidad"
							+ "\nPulse (3) si desea buscar un evento por genero musical");
					
					eleccion = sc1.nextLine();
				
					switch(eleccion)
					{
						case "1":
							System.out.println("Introduzca el nombre del artista");				
							String nombre = sc1.nextLine();
							Usuario artistaAux = new Usuario();
							artistaAux.BuscarArtista(nombre, user);
							break;
						case "2":
							System.out.println("Introduzca el nombre de la cuidad");				
							String city = sc1.nextLine();
							Usuario cuidadAux = new Usuario();
							cuidadAux.BuscarCiudad(city, user);
							break;
						case "3":
							System.out.println("\nElija uno de los siguientes géneros:"
									+ "\nPulse (1) hiphop/r&b"
									+ "\nPulse (2) reggaeton"
									+ "\nPulse (3) pop"
									+ "\nPulse (4) hard rock/metal"
									+ "\nPulse (5) rock/metal"
									+ "\nPulse (6) clásica"
									+ "\nPulse (7) jazz/blues"
									+ "\nPulse (8) pop/rock"
									+ "\nPulse (9) world"
									+ "\nPulse (10) dance/electronica"
									+ "\nPulse (11) flamenco/rumba"
									+ "\nPulse (12) indie/alternativo");
							
							Usuario generoAux = new Usuario();
							String genero = sc1.nextLine();
							generoAux.BuscarGenero(genero, user);
							break;
							
						default:
							System.out.println("Valor introducido incorrecto.");
					}
					break;
				case "6":
					break;
				case "7":
					user.DarseBaja();
					volverAlMenu = user.getDisponible();
					break;
				default:				
					System.out.println("El valor introducido no es correcto. Por favor, intentelo de nuevo\n");
			}
		} while(volverAlMenu == true);		
	}
}