package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EditarCSV
{
	private String[][] datos;
	String ruta;
	
	EditarCSV(String nuevaRuta)
	{
		this.ruta = nuevaRuta;
	}
	public boolean modificarValor(int columna, String nombreAux) {
		boolean resultado = true;
		 for(int i=0;i<datos.length;i++){
	            for(int j=0;j<datos[i].length;j++){
	            	if (datos[i][j].equals(nombreAux)) {
	            		System.out.println("llega aquí 2");
			this.datos[i][columna]="1";
			resultado = true;
	            	}
	            }
		}
		return true;
		
	}
	
	public int buscarCoindicencias(int col1, String dato1, int col2, String dato2)
	{
		int numFila = -70;
		for(int i = 0; i < datos.length; i++)
		{			
			if(datos[i][col1].equals(dato1) && datos[i][col2].equals(dato2))
			{
				numFila = i + 1;
			}
		}
		return numFila;
	}
	public boolean cargarCSV()
	{
		boolean resultado = false;

		String[][] matriz;
		String linea;
		int fila = 0;
		int filas = contarLineasFichero();
		int columnas = contarColumnasFichero();
		
		matriz = new String[filas][columnas]; // Reservamos espacio a la matriz
		
		for (int i=0; i< matriz.length; i++) // Se ponen todos los elementos de matriz a ""
		{
			for (int j=0; j< matriz[0].length; j++)
			{
				matriz[i][j] = "";
			}
		}
		
		try // Abrir el fichero para leerlo
		{
			Scanner fichero = new Scanner(new File(ruta));
			while (fichero.hasNextLine())
			{
				linea = fichero.nextLine();				
				matriz[fila] = linea.split(","); // fila tiene los elementos en tipo String
				fila++;
			}
			fichero.close();
			
			this.datos = matriz; // Se actualiza la matriz de datos CSV
			resultado = true; // Todo ha ido bien
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Ha habido un error leyendo la matriz de " + ruta);
		}
		return resultado;
	}

	public void guardarCSV()
	{
		boolean resultado = false;
		FileWriter fichero;
		try
		{
			fichero = new FileWriter(ruta);
			for (int i = 0; i < this.datos.length; i++)
			{
				for (int j = 0; j < this.datos[0].length; j++)
				{
					fichero.write("" + this.datos[i][j]); 
					
					if (j != (this.datos[0].length - 1)) // Hay que poner "," entre cada valor excepto en el último
					{
						fichero.write(",");
					}
				}
				fichero.write("\n"); // Fin de la fila
			}
			fichero.close();
			resultado = true;
		}
		catch (IOException e)
		{
			System.out.println("ERROR ESCRITURA FICHERO: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/*private String nombre_;
	private String apellidos_;
	private String nacimiento_;
	private String correo_;
	private String ciudad_;
	private String sexo_;
	private String user_;
	private String password_;
	private String esAdmin_;
	private boolean disponible_;*/

	public void addFila(String username, int IDevento) // Introduce una fila en la posición orden
	{
		String[][] nuevosDatos = new String[this.datos.length+1][this.datos[0].length]; // Se reserva espacio para una matriz con una fila más		
		int correccionFila = 0; // Se usa para corregir la fila de lectura
				
		for (int i = 0; i < nuevosDatos.length; i++) // Se recorre esa matriz asignando los valores de la anterior
		{			
			if (i == 0) // Se localiza si estamos en la fila deseada...
			{					
				for (int j = 0; j < nuevosDatos[0].length; j++)
				{
					nuevosDatos[i][0] = username; // Se meten todo Strings vacíos
					nuevosDatos[i][1] = String.valueOf(IDevento);
				}
				correccionFila = -1;
			}
			else
			{					
				nuevosDatos[i] = this.datos[i+correccionFila]; // Se copia toda la fila, aplicando corrección
			}
		}
		this.datos = nuevosDatos;
		this.guardarCSV();
	}

	public void delFila(int orden) // Elimina la fila en la posición orden
	{			
		if (orden >= 0 && orden < (this.datos.length))
		{
			String[][] nuevosDatos = new String[this.datos.length - 1][this.datos[0].length]; // Se reserva espacio para una matriz con una fila MENOS
			int correccionFila = 0; // Se usa para corregir la fila de lectura
			
			for (int i = 0; i < this.datos.length; i++) // Se recorre la matriz asignando los valores de la anterior
			{
				if (i == orden) // Se localiza si estamos en la fila deseada...
				{
					correccionFila = -1; // No se copian los datos y se corrije a la fila anterior
				}
				else // Se copia toda la fila, aplicando corrección
				{
					nuevosDatos[i + correccionFila] = this.datos[i];
				}
			}
			this.datos = nuevosDatos;
			this.guardarCSV();
		}
		else // Puede darse el caso de quedar una matriz con 0 filas y algunas columnas...
		{
			System.out.println("ERROR(delFila) - Número para la fila a eliminar incorrecto.");
		}
	}

	public int contarLineasFichero()
	{
		int lineas = 0;		
		try // Usamos Scanner para contar las lineas
		{
			Scanner fichero = new Scanner(new File(this.ruta));
			while (fichero.hasNextLine())
			{
				lineas++;
				fichero.nextLine();
			}
			fichero.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Ha habido un error contando las lineas de " + ruta);
		}
		return lineas;
	}

	public int contarColumnasFichero()
	{
		int columnas = 0;
		String primeraLinea = "";
		
		try // Usamos Scanner para contar las lineas
		{
			Scanner fichero = new Scanner(new File(this.ruta));
			if (fichero.hasNextLine())
			{
				primeraLinea = fichero.nextLine();
			}
			fichero.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Ha habido un error contando las columnas de " + ruta);
		}
		columnas = primeraLinea.split(",").length;

		if(columnas == 0)
		{
			columnas = primeraLinea.lastIndexOf(',')+2; // Posición del último "," 
		}
		return columnas;
	}

	public void ImprimirMatriz()
	{
		System.out.println("----------------------");
		for (int i = 0; i < this.datos.length; i++)
		{			
			for (int j = 0; j < this.datos[0].length; j++) // Dentro de cada fila, recorremos las columnas
			{
				System.out.print(this.datos[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("----------------------");
	}
}