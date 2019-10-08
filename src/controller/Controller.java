package controller;

import java.util.Scanner;

import model.logic.MVCModelo;
import model.logic.TravelTime;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;
	
	/* Instancia de la Vista*/
	private MVCView view;
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					try
					{
						TravelTime[] primeroYSegundo = modelo.cargarDatos();
						System.out.println("Cargó datos");
						for(int i=0; i<primeroYSegundo.length;i++)
						{
							TravelTime actual = primeroYSegundo[i];
							System.out.println();
							if(i == 0)
							{
								System.out.println("PRIMER VIAJE");	
							}
							else
							{
								System.out.println("ULTIMO VIAJE");
							}
							System.out.println("------------------------");
							System.out.println("Origen: "+actual.darSourceid());
							System.out.println("Destino: "+actual.darDistid());
							System.out.println("Día de la semana: "+ actual.darDow());
							System.out.println("Tiempo promedio en viaje: "+actual.darMeanTravelTime());
							System.out.println("------------------------");
						}
					}
					catch(Exception e)
					{
						e.getMessage();
					}
					
					break;
				case 2: 
					System.out.println("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				default: 
					System.out.println("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
}
