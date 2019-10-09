package model.data_structures;

import java.util.ArrayList;
import java.util.Iterator;

public class TablaHashLineal <K extends Comparable<K>,V> 
{

	private int tamanio = 0;
	private int capacidad = 0;
	private int contadorRehash = 0;
	private ArregloDinamico<NodoHash<K, V>> listaNodos;


	public TablaHashLineal(int pCapacidad)
	{
		this.capacidad = pCapacidad;
		listaNodos = new ArregloDinamico<NodoHash<K,V>>(pCapacidad);
	}
	public void put(K llave, V valor) 
	{
		if(llave!=null&&!llave.toString().equals(""))
		{
			NodoHash<K, V> nodoActual = new NodoHash<K,V>(null, null);
			if((double)tamanio/(double)capacidad >= 0.75)
			{
				agrandar();
			}
			int i;
			for(i = hash(llave);listaNodos.darElementoPos(i)!=null;i=(i+1)%capacidad)
			{
				nodoActual = listaNodos.darElementoPos(i);
				if(nodoActual.getLlave().equals(llave))
				{
					nodoActual.setValor(valor);
					listaNodos.agregarPos(nodoActual, i+1);
					tamanio++;
					return;
				}
			}
			nodoActual.setLlave(llave);
			nodoActual.setValor(valor);
			listaNodos.agregarPos(nodoActual, i);
			tamanio++;

		}
	}
	public V get(K llave) 
	{
		V rta = null;
		if(llave!=null)
		{
			for(int i = hash(llave); listaNodos.darElementoPos(i)!=null; i = (i+1)%capacidad)
			{
				NodoHash<K, V> elemento = listaNodos.darElementoPos(i);
				if(elemento.getLlave().equals(llave))
				{
					rta = elemento.getValor();
				}
			}	
		}
		return rta;

	}
	public V delete(K llave) 
	{
		if(llave==null)
		{
			return null;
		}
		if(!perteneceATabla(llave))
		{
			return null;
		}
		boolean encontrado = false;
		NodoHash<K, V> porEliminar = null;
		int numParaRehash = 0;
		for(int i = hash(llave); listaNodos.darElementoPos(i)!=null&&!encontrado;i=(i+1)%capacidad)
		{
			NodoHash<K, V> elemento = listaNodos.darElementoPos(i);
			if(elemento.getLlave().equals(llave))
			{
				encontrado = true;
				porEliminar = elemento;
				listaNodos.eliminar(elemento);
				numParaRehash = i;
			}
		}
		rehash(numParaRehash);
		return porEliminar.getValor();
	}
	public Iterator<K> keys() 
	{
		Stack<K> llaves = new Stack<K>();
		for(int i=0;i<listaNodos.darCapacidad();i++)
		{
			NodoHash<K, V> elemento = listaNodos.darElementoPos(i);
			if(elemento!=null)
			{
				llaves.push(elemento.getLlave());
			}
		}
		return llaves.iterator();
	}

	public int size()
	{
		return tamanio;
	}
	private int hash(K llave) 
	{
		return (llave.hashCode() & 0x7fffffff) % capacidad;
	}  
	public void agrandar()
	{
		ArregloDinamico<NodoHash<K, V>> temp = listaNodos; 
		capacidad = 2 * capacidad; 
		listaNodos = new ArregloDinamico<>(capacidad);  
		tamanio=0;
		for(int i=0;i<temp.darCapacidad();i++) {
			NodoHash<K,V>actual=temp.darElementoPos(i);				
			while(actual!=null) {
				put(actual.getLlave(),actual.getValor());
				actual=actual.getSiguiente();
			}

		} 

	}
	public boolean perteneceATabla(K llave)
	{
		boolean rta = false;
		if(llave==null)
		{
			return rta;
		}
		else
		{
			if(get(llave)!=null)
			{
				rta = true;
			}
		}
		return rta;
	}
	public void rehash(int pNum)
	{
		int i = pNum;
		i = (i + 1) % capacidad;
		while (listaNodos.darElementoPos(i) != null) {
			NodoHash<K, V> paraReHash = listaNodos.darElementoPos(i);
			if(paraReHash!=null)
			{
				put(paraReHash.getLlave(), paraReHash.getValor());
				paraReHash = null;
				tamanio--;
				i = (i + 1) % capacidad;	
			}
		}
		tamanio--;
		contadorRehash++;
	}
	public int darCapacidad()
	{
		return capacidad;
	}

	public double factorCargaFinal()
	{
		return (double)tamanio/(double)capacidad;
	}
	public int numeroDuplas()
	{
		return tamanio;
	}
	public int numFinal()
	{
		return capacidad;
	}
	public int darContadorRehash()
	{
		return contadorRehash;
	}
	public Stack<V> valoresDeLlaves (K pLlave)
	{
		Stack<V> values = new Stack<V>();
		for(int i=0;i<listaNodos.darCapacidad();i++)
		{
			NodoHash<K, V> elemento = listaNodos.darElementoPos(i);
			if(elemento!=null)
			{
				if(elemento.getLlave().equals(pLlave))
				{
					values.push(elemento.getValor());	
				}
			}
		}
		return values;
	}
	public V getPos(int pos) {
		V retorno=null;
		if(listaNodos.darElementoPos(pos)!=null) {
			retorno= listaNodos.darElementoPos(pos).getValor();
		}
		return retorno;
	}
}
