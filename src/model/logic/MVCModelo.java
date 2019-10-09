package model.logic;

import java.io.FileReader;
import java.util.Iterator;

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
	private String llave;
	private double value;
	private TablaHashLineal<String, TravelTime> tablaLineal;
	private HashSeparateChaining<String, TravelTime> tablaChaining;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		tablaChaining=new HashSeparateChaining<String, TravelTime>(20000000);
		tablaLineal = new TablaHashLineal<String, TravelTime>(15000000);
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


	public TravelTime[] cargarDatos(int i) throws Exception
	{

		TravelTime[] rta = new TravelTime[2];
		int contador = 0;
		TravelTime primero = null;
		TravelTime ultimo = null;		
		Short trimestre = (short) i;
		String ruta = "data/bogota-cadastral-2018-"+i+"-WeeklyAggregate.csv";
		int valorAntesChaining = tablaChaining.size();
		int valorAntesLineal = tablaLineal.size();
		CSVReader lector = new CSVReader(new FileReader(ruta)); 
		String [] siguiente;

		while ((siguiente = lector.readNext()) != null) 
		{
			if(contador!=0)
			{
				if(contador==1)
				{
					TravelTime viaje = new TravelTime(trimestre,Short.parseShort(siguiente[0]), Short.parseShort(siguiente[1]), Short.parseShort(siguiente[2]), Double.parseDouble(siguiente[3]), Double.parseDouble(siguiente[4]));	
					llave = trimestre + "-" + siguiente[0] + "-" + siguiente[1];
					value = Double.parseDouble(siguiente[3]);
                    tablaLineal.put(llave, viaje);
					//tablaChaining.putInSet(llave, viaje);

					primero = viaje;	
				}

				TravelTime viaje = new TravelTime(trimestre,Short.parseShort(siguiente[0]), Short.parseShort(siguiente[1]), Short.parseShort(siguiente[2]), Double.parseDouble(siguiente[3]), Double.parseDouble(siguiente[4]));	
				llave = trimestre + "-" + siguiente[0] + "-" + siguiente[1];
				value = Double.parseDouble(siguiente[3]);
				tablaLineal.put(llave, viaje);
				//tablaChaining.putInSet(llave, viaje);
				ultimo = viaje;


			}
			contador++;
		}

		lector.close();
		System.out.println("Total de viajes leidos para el trimestre "+i+" utilizando linear probing : "+(tablaLineal.size()-valorAntesLineal));
		System.out.println("Total de viajes leidos para el trimestre "+i+" utilizando separate chaining : "+(tablaChaining.size()-valorAntesChaining));
		
		rta[0] = primero;
		rta[1] = ultimo;
		return rta;
	}
	
	public ArregloDinamico<TravelTime> consultaChaining(String llave) {
		Iterator<TravelTime>it=tablaChaining.getSet(llave);
		ArregloDinamico<TravelTime>retorno=new ArregloDinamico<TravelTime>(200000);
		while(it.hasNext()) {
			retorno.agregar(it.next());
		}
		retorno.shellSort();
		return retorno;
	}
}
