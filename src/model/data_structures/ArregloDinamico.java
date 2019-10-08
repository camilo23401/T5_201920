package model.data_structures;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico<T extends Comparable<T> >  {
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T elementos[ ];

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
		elementos = newArray(max);
		tamanoMax = max;
		tamanoAct = 0;
	}

	@SuppressWarnings("unchecked")
	private T[] newArray(int size)
	{
		return (T[]) new Comparable[size]; 	
	}

	public void agregar( T dato )
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T [ ] copia = elementos;
			elementos =newArray(tamanoMax);
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
			System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
		}	
		elementos[tamanoAct] = dato;
		tamanoAct++;
	}
	
	public void agregarPos( T dato,int pos )
	{
		elementos[pos]=dato;
		tamanoAct++;
	}
	

	public int darCapacidad() {
		return tamanoMax;
	}

	public int darTamano() {
		return tamanoAct;
	}
	public void setTamanio(int tamanio) {
		tamanoAct=tamanio;
	}
/*	public void shellSort() 
	{ 
		int n = tamanoAct; 

		for (int intervalo = n/2; intervalo > 0; intervalo /= 2) 
		{ 
			for (int i = intervalo; i < n; i += 1) 
			{ 
				T temp = elementos[i]; 
				int j; 
				for (j = i; j >= intervalo &&this.darElemento(j-intervalo).compareTo(temp)<0; j -= intervalo) {
					elementos[j] = elementos[j - intervalo]; 
				}

				elementos[j] = temp; 
			} 
		} 

	}
	*/
	public T darElementoPos(int i) {

		return elementos[i];
	}
	public boolean contains(T elemento) {
		boolean contiene=false;
		for(int i=0;i<elementos.length&&!contiene;i++) {
			if(elementos[i]!=null&&elementos[i].compareTo(elemento)==0) {
				contiene=true;
			}
		}
		return contiene;
	}
	public T darElemento(T elemento) {
		T elemento1=null;
		boolean contiene=false;
		for(int i=0;i<elementos.length&&!contiene;i++) {
			if(elementos[i]!=null&&elementos[i].compareTo(elemento)==0) {
				contiene=true;
				elemento1=elementos[i];
			}
		}
		return elemento1;
	}
	
	public Iterator<T>iterador(){
		ArrayList<T>ite=new ArrayList<T>();
		for(int i=0;i<elementos.length;i++) {
			ite.add(elementos[i]);
		}
		return ite.iterator();
	}
	
	public T eliminar(T elemento) {
		T elemento1=null;
		boolean contiene=false;
		for(int i=0;i<elementos.length&&!contiene;i++) {
			if(elementos[i]!=null&&elementos[i].compareTo(elemento1)==0) {
				contiene=true;
				elemento1=elementos[i];
				elementos[i]=null;
			}
		}
		return elemento1;
	}
	
}
