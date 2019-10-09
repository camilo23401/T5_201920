package controller;

import java.util.Scanner;

import model.data_structures.ArregloDinamico;
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
						for(int j=1;j<5;j++) {
						TravelTime[] primeroYSegundo = modelo.cargarDatos(j);
						System.out.println("Cargó datos");
						for(int i=0; i<primeroYSegundo.length;i++)
						{
							TravelTime actual = primeroYSegundo[i];
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
					}
					catch(Exception e)
					{
						e.getMessage();
					}
					break;
					
				case 2:
					
					break;
					
				case 3: 

					System.out.println("Digite el trimestre a consultar");
					String trimestre = lector.next();
					System.out.println("Digite el número de la zona de origen");
					String sourceId = lector.next();
					System.out.println("Digite el número de la zona de destino");
					String dstid = lector.next();
					String llave=trimestre+"-"+sourceId+"-"+dstid;
					ArregloDinamico<TravelTime>result=modelo.consultaChaining(llave);
					for (int i = 0; i < result.darTamano(); i++) {
						TravelTime actual=result.darElementoPos(i);
						System.out.println("Viaje "+i+" "+"Trimestre: "+actual.darTrimestre()+" Origen:"+actual.darSourceid()+" Destino: "+actual.darDistid()+" Dia: "+actual.darDow()+" Tiempo Promedio: "+actual.darMeanTravelTime());
					}
					break;	
				case 4: 
					System.out.println("Pruebas aleatorias");
					String[]pruebas=modelo.pruebasChaining();
					System.out.println("Tiempo Minimo :"+ pruebas[0]);
					System.out.println("Tiempo Maximo :"+ pruebas[1]);
					System.out.println("Tiempo Promedio :"+ pruebas[2]);
					break;	
				case 5: 
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
