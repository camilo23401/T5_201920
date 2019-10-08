package model.logic;

import java.io.FileReader;

import com.opencsv.CSVReader;

import model.data_structures.ArregloDinamico;
import model.data_structures.HashSeparateChaining;
import model.data_structures.IArregloDinamico;
import model.data_structures.TablaHashLineal;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private IArregloDinamico datos;
	private TablaHashLineal<Comparable<K>, V> tablaLineal;
	private HashSeparateChaining<Comparable<K>, V> tablaChaining;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		
	}
	
	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public MVCModelo(int capacidad)
	{
	
	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.darTamano();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(String dato)
	{	
		datos.agregar(dato);
	}
	
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public String buscar(String dato)
	{
		return datos.buscar(dato);
	}
	
	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public String eliminar(String dato)
	{
		return datos.eliminar(dato);
	}
	public void cargarDatos() throws Exception
	{
		int contador = 0;
		for(int i = 1;i<5;i++)
		{
			String ruta = "data/bogota-cadastral-2018-"+i+"-All-MonthlyAggregate.csv";
			CSVReader lector = new CSVReader(new FileReader(ruta)); 
			String [] siguiente;
			while ((siguiente = lector.readNext()) != null) 
			{
				if(contador!=0)
				{
					TravelTime viaje = new TravelTime(Integer.parseInt(siguiente[0]), Integer.parseInt(siguiente[1]), Integer.parseInt(siguiente[2]), Double.parseDouble(siguiente[3]), Double.parseDouble(siguiente[4]));
					tablaLineal
				}
				contador++;
			}
			lector.close();
			contador = 0;
		}
	}


}
