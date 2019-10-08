package model.data_structures;

import java.util.ArrayList;
import java.util.Iterator;

public class TablaHashLineal <K extends Comparable<K>,V> implements HashTable<K,V>
{

	private int tamanio = 0;
	private int capacidad = 0;
	private ArregloDinamico<NodoHash<K, V>> listaNodos;


	public TablaHashLineal(int pCapacidad)
	{
		this.capacidad = pCapacidad;
		listaNodos = new ArregloDinamico<NodoHash<K,V>>(pCapacidad);
	}
	public void put(K llave, V valor) 
	{
		if(llave==null)
		{
			return; //Error
		}
		if(valor == null)
		{
			delete(llave);
			return;
		}
		if(tamanio>=capacidad/2)
		{
			agrandar();
		}
		for(int i = hash(llave);listaNodos.darElementoPos(i)!=null; i = (i+1)%capacidad)
		{
			NodoHash<K, V> elemento = listaNodos.darElementoPos(i);
			if(elemento.getLlave().equals(llave))
			{
				elemento.setValor(valor);
				return;
			}
			elemento.setLlave(llave);
			elemento.setValor(valor);
			tamanio++;
		}
	}

	public void putInSet(K llave, V valor) 
	{

	}

	public V get(K llave) 
	{
		V objeto = null;
		if(llave == null)
		{
			return objeto;
		}
		for(int i = hash(llave); listaNodos.darElementoPos(i)!=null; i = (i+1)%capacidad)
		{
			NodoHash<K, V> elemento = listaNodos.darElementoPos(i);
			if(elemento.getLlave().equals(llave))
			{
				objeto = elemento.getValor();
			}
		}
		return objeto;
	}

	public Iterator<V> getSet(K llave) 
	{
		return null;
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
				elemento.setLlave(null);
				elemento.setValor(null);
				numParaRehash = i;
			}
			rehash(numParaRehash);
		}
		return porEliminar.getValor();
	}

	public Iterator<V> deleteSet(K llave) 
	{
		return null;
	}

	public Iterator<K> keys() 
	{
		ArrayList<K> llaves = new ArrayList<K>();
		for(int i=0;i<capacidad;i++)
		{
			NodoHash<K, V> elemento = listaNodos.darElementoPos(i);
			llaves.add(elemento.getLlave());
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
		tamanio = 0; 
		for (int i = 0; i < capacidad; i++) 
			listaNodos.agregarPos(null,i); 

		for(int i=0;i<temp.darTamano();i++) {
			listaNodos.agregarPos(temp.darElementoPos(i),i);
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
        while (listaNodos != null) {
         
            NodoHash<K, V> paraReHash = listaNodos.darElementoPos(i);
            put(paraReHash.getLlave(), paraReHash.getValor());
            paraReHash = null;
            tamanio--;
            i = (i + 1) % capacidad;
        }
        tamanio--;
	}

}