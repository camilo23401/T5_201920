package view;

import model.logic.MVCModelo;

public class MVCView 
{
	    /**
	     * Metodo constructor
	     */
	    public MVCView()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar datos a las tablas hash");
			System.out.println("2.Dado un trimestre, zona de origen y zona destino retornar los tiempos de sus viajes y los dias respectivos");
			System.out.println("3.Dado un trimestre, zona de origen y zona destino retornar los tiempos de sus viajes y los dias respectivos");
			System.out.println("4. Realizar Pruebas aleatorias chaining");
			System.out.println("5. Realizar pruebas aleatorias linear probing");
			System.out.println("6. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(MVCModelo modelo)
		{
			// TODO implementar
		}
}
