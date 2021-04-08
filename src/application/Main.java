package application;

import java.util.Scanner;

public class Main
{
	public static void Registrarse()
	{
		System.out.println("Estas en la funcion Registrarse(). Pulsa cualquier tecla para ir al menu");
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		Menu();
	}
	public static void LogIn()
	{
		System.out.println("Estas en la funcion LogIn(). Pulsa cualquier tecla para ir al menu");
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		Menu();
	}
	public static void LogOut()
		{
			System.out.println("Estas en la funcion LogOut(). Pulsa cualquier tecla para volver al menu");
			Scanner sc = new Scanner(System.in);
			String a = sc.nextLine();
			Menu();
		}
	public static void Menu()
	{
		System.out.println(" - - - - - MENU - - - - -");
	}
	
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		String eleccion;
		do
		{
			System.out.println("BIENVENIDO A EVENTELL");
			System.out.println("Pulse (1) para registrarse\nPulse (2) para iniciar sesion\nPulse (0) para salir del programa");
			eleccion = sc.nextLine();
			switch(eleccion)
			{
				case "0":
					break;
				case "1":
					Registrarse();
					break;
				case "2":
					LogIn();
					break;
				default:
					System.out.println("El valor introducido no es correcto. Por favor, intentelo de nuevo\n");
			}
		} while(eleccion != "0");
	}
}