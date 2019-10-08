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
	private String llave;
	private double value;
	private TablaHashLineal<String, Double> tablaLineal;
	private HashSeparateChaining<String, Double> tablaChaining;

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
		llave = "";
		value = 0.0;
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
	public TravelTime[] cargarDatos() throws Exception
	{
		TravelTime[] rta = new TravelTime[2];
		int contador = 0;
		TravelTime primero = null;
		TravelTime ultimo = null;
		for(int i = 1;i<5;i++)
		{
			Short trimestre = (short) i;
			String ruta = "data/bogota-cadastral-2018-"+i+"-WeeklyAggregate.csv";
			CSVReader lector = new CSVReader(new FileReader(ruta)); 
			String [] siguiente;
			while ((siguiente = lector.readNext()) != null) 
			{
				if(contador!=0)
				{
					if(contador==1)
					{
						TravelTime viaje = new TravelTime(trimestre,Integer.parseInt(siguiente[0]), Integer.parseInt(siguiente[1]), Integer.parseInt(siguiente[2]), Double.parseDouble(siguiente[3]), Double.parseDouble(siguiente[4]));	
						llave = trimestre + "-" + siguiente[0] + "-" + siguiente[1];
						value = Double.parseDouble(siguiente[3]);
				     	//tablaLineal.put(llave, value);
						//tablaChaining.put(llave, value);
						primero = viaje;	
					}
					TravelTime viaje = new TravelTime(trimestre,Integer.parseInt(siguiente[0]), Integer.parseInt(siguiente[1]), Integer.parseInt(siguiente[2]), Double.parseDouble(siguiente[3]), Double.parseDouble(siguiente[4]));
					llave = trimestre + "-" + siguiente[0] + "-" + siguiente[1];
					value = Double.parseDouble(siguiente[3]);
					//tablaLineal.put(llave, value);
					//tablaChaining.put(llave, value);
					ultimo = viaje;
				}
				contador++;
			}
			lector.close();
			contador = 0;
		}
		rta[0] = primero;
		rta[1] = ultimo;
		return rta;
	}
}
