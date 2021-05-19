/*1,pabloadmin,pablo123,Pablo,Gonzalez Sanchez,pgonzalezs1999@gmail.com,08/01/1999,Malaga,1,1
0,pablogs,pablo123,Pablo,Gonzalez Sanchez,pgonzalezs1999@gmail.com,08/01/1999,Malaga,1,0
1,maria2500,maria123,Maria,Rodriguez Gomez,mtrgomez00@gmail.com,25/07/2000,Madrid,2,1
1,yago,yago123,Santiago,Barreiro Campos,s.barreirocampos@gmail.com,01/05/1999,Vigo,1,0
1,pr1,pr1,,,,,,,0
1,m.benz02,merche123,Mercedes,Noide1 Noidea2,norellenolastablas2@gmail.com,23/04/2022,Shanghai,2,1
1,Meche,Miau123,,,,,,,0*/

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

	public void addFila() // Se añade fila al final: Nº fila = length
	{	
		addFila(this.datos.length);
	}

	public void addFila(int orden) {
		// Introduce una fila en la posición orden
		// 0: Primera fila
		// FilasMatriz : Última fila
		if (orden >= 0 &&
			orden < (this.datos.length+1) ) {
			
			// Se reserva espacio para una matriz con una fila más
			String[][] nuevosDatos = new String[this.datos.length+1][this.datos[0].length];
			
			int correccionFila = 0; // Se usa para corregir la fila de lectura
			
			// Se recorre esa matriz asignando los valores de la anterior
			for (int i=0; i< nuevosDatos.length; i++) {
				// Se localiza si estamos en la fila deseada...
				if (i == orden) {
					// Se meten todo Strings vacíos
					for (int j=0; j<nuevosDatos[0].length; j++) {
						nuevosDatos[i][j] = "";
					}
					correccionFila = -1;
				}
				else {
					// Se copia toda la fila, aplicando corrección
					nuevosDatos[i] = this.datos[i+correccionFila];
				}
			}
			this.datos = nuevosDatos;
		}
		else {
			System.out.println("ERROR(addFila) - Número para la nueva fila incorrecto.");
		}
	}

	public void delFila(int orden) // Elimina la fila en la posición orden
	{		
		int numFilas = this.contarLineasFichero();
		int numColumnas = this.contarColumnasFichero();	
		System.out.println("Orden = " + orden);
		System.out.println("Numero de filas = " + numFilas);
		System.out.println("Numero de columnas = " + numColumnas);
		
		if (orden >= 0 && orden < (this.datos.length))
		{
			String[][] nuevosDatos = new String[this.datos.length - 1][this.datos[0].length]; // Se reserva espacio para una matriz con una fila MENOS
			int correccionFila = 0; // Se usa para corregir la fila de lectura
			
			for (int i = 0; i < this.datos.length; i++) // Se recorre la matriz asignando los valores de la anterior
			{
				if (i == orden) // Se localiza si estamos en la fila deseada...
				{
					System.out.println("Dentro del if. La i = " + i);
					correccionFila = -1; // No se copian los datos y se corrije a la fila anterior
				}
				else // Se copia toda la fila, aplicando corrección
				{
					System.out.println("Dentro del else. La i = " + i);
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

	public void addColumna() //Se añade columna al final: Nº columna = length de una fila
	{		
		addColumna(this.datos[0].length);
	}

	public void addColumna(int orden) {
		// Introduce una columna en la posición orden
		// 0: Primera column
		// ColumnasMatriz : Última columna
		if (orden >= 0 &&
			orden < (this.datos[0].length+1) ) {
			
			// Se reserva espacio para una matriz con una columna más
			String[][] nuevosDatos = new String[this.datos.length][this.datos[0].length+1];
			
						
			// Se recorre esa matriz asignando los valores de la anterior
			for (int i=0; i< nuevosDatos.length; i++) {
				int correccionColumna = 0; // Se usa para corregir la columna de lectura
				for (int j=0; j< nuevosDatos[0].length; j++) {
					if (j == orden) {
						// Se mete String vacío
						nuevosDatos[i][j] = "";
						correccionColumna = -1;
					}
					else {
						nuevosDatos[i][j] = this.datos[i][j+correccionColumna];
					}
				}
			}
			this.datos = nuevosDatos;
		}
		else {
			System.out.println("ERROR(addColumna) - Nº de nueva columna incorrecto.");
		}
	}
	
	public void delColumna(int orden) // Elimina la columna de la posición orden
	{
		if (orden >= 0 && orden < (this.datos[0].length + 1))
		{						
			String[][] nuevosDatos = new String[this.datos.length][this.datos[0].length-1]; // Se reserva espacio para una matriz con una columna MENOS
												
			for (int i = 0; i < this.datos.length; i++) // Se recorre la matriz asignando los valores a la nueva
			{
				int correccionColumna = 0; // Se usa para corregir la columna de lectura
				for (int j = 0; j < this.datos[0].length; j++)
				{
					if (j == orden) // Se elimina la columna
					{					
						correccionColumna = -1; // corrección de la siguiente columna
					}
					else
					{
						nuevosDatos[i][j+correccionColumna] = this.datos[i][j];
					}
				}
			}
			this.datos = nuevosDatos;			
		}
		else // Puede darse el caso de quedar una matriz con 0 columnas y algunas filas...
		{
			System.out.println("ERROR(delColumna) - Nº de columna a eliminar incorrecto.");
		}
	}

	public int contarLineasFichero()
	{
		int lineas = 0;
		// Usamos Scanner para contar las lineas
		try
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

	public int contarColumnasFichero() {
		int columnas = 0;
		String primeraLinea = "";

		// Usamos Scanner para contar las lineas
		try {
			Scanner fichero = new Scanner(new File(this.ruta));
			if (fichero.hasNextLine()) {
				primeraLinea = fichero.nextLine();
			}
			fichero.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ha habido un error contando las columnas de " + ruta);
		}
		
		// primeraLinea algo de este estilo: "3.5;-4.0;88.0"  | ";;"
		columnas = primeraLinea.split(",").length;
		
		// Para resolver el problema del split si datos, solo ;
		// es el caso de ",,,...;"
		if (columnas == 0) {
			columnas = primeraLinea.lastIndexOf(',')+2; // posición del último ; 
		}
		return columnas;
	}

	// Pinta matrices en base a sus dimensiones
	public  void listar()
	{
		System.out.println("----------------------");
		for (int i = 0; i < this.datos.length; i++)
		{
			// dentro de cada fila, recorremos las columnas
			for (int j = 0; j < this.datos[0].length; j++)
			{
				System.out.print(this.datos[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("----------------------");
	}
}