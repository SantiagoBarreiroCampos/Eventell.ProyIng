package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import model.ListaAmigos;
import model.ListaArtistasFavoritos;
import model.ListaEventosFavoritos;
import model.Usuario;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;

public class Main
{		
	public static void main(String args[]) throws IOException
	{
		Scanner sc = new Scanner(System.in); //xxxxxxxxxxxx (NO BORRAR)
		
		Usuario SesionIniciada = new Usuario(); // Este se usara para que, una vez logeado, el sistema sepa con que datos trabajar
		boolean encontrado = false;
		
		do
		{
			Usuario usuarioAux = new Usuario();
			String eleccion1;		
			System.out.println("BIENVENIDO A EVENTELL");
			System.out.println("Pulse (1) para registrarse"
							+ "\nPulse (2) para iniciar sesion");
			System.out.println("Pulse (0) para salir del programa");
			eleccion1 = sc.next();
				
			switch(eleccion1)
			{
				case "0":					
					encontrado = true;
					System.out.println("Hasta la próxima");
					break;
				case "1":					
					SesionIniciada = usuarioAux.Registrarse();
					Menu(SesionIniciada);
					break;
				case "2":
					System.out.println("Introduzca su nombre de usuario:");
					String username = sc.next();
					System.out.println("Introduzca su contraseña:");
					String contrasena = sc.next();
					SesionIniciada = usuarioAux.Login(username, contrasena);
					if(SesionIniciada.getUser() != null)
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
	
	public static void Menu(Usuario user) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		String eleccion2;
		String SesionIniciada = user.getUser();
		boolean volverAlMenu = true;
		
		do
		{
			System.out.println(" - - - - - MENU - - - - - - [Accedido desde: " + user.getUser() + "]");
			System.out.println("Pulse (0) para cerrar sesion"
							+ "\nPulse (1) para cambiar ajustes de perfil"
							+ "\nPulse (2) para ver eventos comprados"
							+ "\nPulse (3) para ver lista de amigos"
							+ "\nPulse (4) para ver lista de artistas favoritos"
							+ "\nPulse (5) para ver lista de eventos favoritos"
							+ "\nPulse (6) para iniciar una busqueda"
							+ "\nPulse (7) para ver calendario personalizado"
							+ "\nPulse (8) para solicitar sugerencias de eventos"
							+ "\nPulse (9) para darse de baja en el sistema de EVENTELL");
			eleccion2 = sc.nextLine();
				
			switch(eleccion2)
			{
				case "0":
					
					eleccion2 = "0";
					user = null;
					System.out.println("Acaba de cerrar sesión. Regrasará al menú de inicio");
					volverAlMenu = false;
					break;
					
				case "1":
					Usuario perfilAux = new Usuario();
					Usuario perfil = perfilAux.ConfigurarPerfil();
					break;
					
				case "2":
					
					break;
					
				case "3":
					
					ListaAmigos listaAux = new ListaAmigos();
					ListaAmigos lista = listaAux.buscarAmigos(user);
					lista.mostrarAmigos();
					System.out.println("Pulsa ENTER para volver al menú principal\n");
					sc.nextLine();
					//volverAlMenu =false;
					break;
					
				case "4":
					
					ListaArtistasFavoritos listaAux2 = new ListaArtistasFavoritos();
					ListaArtistasFavoritos lista2 = listaAux2.buscarArtistas(user);
					lista2.mostrarArtistas();
					System.out.println("Pulsa ENTER para volver al menú principal\n");
					sc.nextLine();
					//volverAlMenu =false;
					break;
					
				case "5":
					
					ListaEventosFavoritos listaAux3 = new ListaEventosFavoritos();
					ListaEventosFavoritos lista3 = listaAux3.buscarEventos(user);
					lista3.mostrarEventos();
					System.out.println("Pulsa ENTER para volver al menú principal\n");
					sc.nextLine();
					//volverAlMenu =false;
					break;
					
				case "6":
					break;
				case "7":
					break;
				case "8":
					break;
				case "9":
					System.out.println("Está seguro que desea darse de baja en el sistema?\n Si: (1), No: (2)");
					eleccion2 = sc.next();
					if(eleccion2 == "1")
					{
						user.setDisponible(false);
						eleccion2 = "0";						
					}
					else
					{
						System.out.println("Usted conservará sus datos en el sistema");
					}
					break;
				default:
					System.out.println("El valor introducido no es correcto. Por favor, intentelo de nuevo\n");
			}
		} while(volverAlMenu == true); // Esto es provisional para que no se me salga del programa
		
	}
	
	public static void LogOut()
	{
		System.out.println("Estas en la funcion LogOut(). Pulsa cualquier tecla para volver al menu");
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		//Menu();
	}
	 
}