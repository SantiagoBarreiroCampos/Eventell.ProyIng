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
	public boolean modificarValor(int columna, String nombreAux)
	{
		boolean resultado = false;
		
		 for(int i=0;i<datos.length;i++)
		 {
            for(int j=0;j<datos[i].length;j++)
            {
            	if (datos[i][j].equals(nombreAux))
            	{
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
	
	public int buscarCoindicencias(int col, Usuario user)
	{
		int numFila = -70;
		for(int i = 0; i < datos.length; i++)
		{
			if(datos[i][col].equals(user.getUser()))
			{
				numFila = i + 1;
			}
		}
		return numFila;
	}
	
	public int buscarCoindicencias(int col1, Usuario dato1, int col2, int dato2)
	{
		int numFila = -70;
		for(int i = 0; i < datos.length; i++)
		{			
			if(datos[i][col1].equals(dato1.getUser()) && datos[i][col2].equals(String.valueOf(dato2)))
			{
				numFila = i + 1;
			}
		}
		return numFila;
	}
	public int buscarCoindicencias(int col1, Usuario dato1, int col2, String dato2)
	{
		int numFila = -70;
		for(int i = 0; i < datos.length; i++)
		{			
			if(datos[i][col1].equals(dato1.getUser()) && datos[i][col2].equals(dato2))
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

	/*boolean disponible_;
	String user_;
	String password_;
	String nombre_;
	String apellidos_;
	String correo_;
	String nacimiento_;
	String ciudad_;
	String sexo_;
	String esAdmin_;*/

	public void addFila(String username1, String username2) // Introduce una fila en la posición orden
	{
		String[][] nuevosDatos = new String[this.datos.length+1][this.datos[0].length]; // Se reserva espacio para una matriz con una fila más		
		int correccionFila = 0; // Se usa para corregir la fila de lectura
				
		for (int i = 0; i < nuevosDatos.length; i++) // Se recorre esa matriz asignando los valores de la anterior
		{			
			if (i == 0) // Se localiza si estamos en la fila deseada...
			{
				nuevosDatos[i][0] = username1;
				nuevosDatos[i][1] = username2;
				
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
	
	public void addFila(Usuario user) // Introduce una fila en la posición orden
	{
		String[][] nuevosDatos = new String[this.datos.length+1][this.datos[0].length]; // Se reserva espacio para una matriz con una fila más		
		int correccionFila = 0; // Se usa para corregir la fila de lectura
				
		for (int i = 0; i < nuevosDatos.length; i++) // Se recorre esa matriz asignando los valores de la anterior
		{			
			if (i == 0) // Se localiza si estamos en la fila deseada...
			{
				nuevosDatos[i][0] = "1";
				nuevosDatos[i][1] = user.getUser();
				nuevosDatos[i][2] = user.getPassword();
				nuevosDatos[i][3] = user.getNombre();
				nuevosDatos[i][4] = user.getApellidos();
				nuevosDatos[i][5] = user.getCorreo();
				nuevosDatos[i][6] = user.getNacimiento();
				nuevosDatos[i][7] = user.getCiudad();
				nuevosDatos[i][8] = user.getSexo();
				nuevosDatos[i][9] = user.getEsAdmin();
				
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
		//System.out.println("Hay " + lineas + " lineas en " + ruta);
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
		//System.out.println("Hay " + columnas + " columnas en " + ruta);
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