package model.data_structures;

import java.util.ArrayList;
import java.util.Iterator;

public class HashSeparateChaining<K extends Comparable<K>,V> implements HashTable<K,V>{

	private int tamanio;

	private int capacidad;

	private ArregloDinamico<NodoHash<K,V>>listaNodos;

	public int size() {
		return tamanio;
	} 
	public HashSeparateChaining(int n) {
		this.capacidad = n;
		listaNodos=new ArregloDinamico<NodoHash<K,V>>(n);
	} 

	@Override
	public void put(K llave, V valor) {
		NodoHash<K,V>agregado=new NodoHash<K,V>(llave,valor);
		if(listaNodos.contains(agregado)) {
			NodoHash<K,V>cambiar=listaNodos.darElemento(agregado);
			cambiar.setValor(valor);
		}
		else {
			int pos = hashCode();
			listaNodos.agregarPos(agregado, pos);
			tamanio++;
			if ((1.0*tamanio)/capacidad >= 0.75) 
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
		}

	}

	@Override
	public void putInSet(K llave, V valor) {
		NodoHash<K,V>agregado=new NodoHash<K,V>(llave,valor);
		if(listaNodos.contains(agregado)) {
			NodoHash<K,V>cambiar=listaNodos.darElemento(agregado);
			cambiar.setSiguiente(agregado);
			tamanio++;
		}
		else {
			int pos = hashCode();
			listaNodos.agregarPos(agregado, pos);
			tamanio++;
			if ((1.0*tamanio)/capacidad >= 0.75) 
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
		}

	}

	@Override
	public V get(K llave) {
		boolean encontrado=false;
		V buscado=null;
		for (int i = 0; i < listaNodos.darTamano()&&!encontrado; i++) {
			NodoHash<K,V>actual=listaNodos.darElementoPos(i);
			if(actual.getLlave().compareTo(llave)==0) {
				encontrado=true;
				buscado=actual.getValor();
			}
		}
		return buscado;
	}

	@Override
	public Iterator<V> getSet(K llave) {
		boolean encontrado=false;
		V buscado=null;
		ArrayList<V>dinami=new ArrayList<V>();
		for (int i = 0; i < listaNodos.darTamano()&&!encontrado; i++) {
			NodoHash<K,V>actual=listaNodos.darElementoPos(i);
			if(actual.getLlave().compareTo(llave)==0) {
				encontrado=true;
				while(actual!=null) {
					buscado=actual.getValor();
					dinami.add(buscado);
					actual=actual.getSiguiente();
				}
			}
		}
		return dinami.iterator();
	}

	@Override
	public V delete(K llave) {
		boolean encontrado=false;
		V eliminado=null;
		for (int i = 0; i < listaNodos.darTamano()&&!encontrado; i++) {
			NodoHash<K,V>actual=listaNodos.darElementoPos(i);
			if(actual.getLlave().compareTo(llave)==0) {
				encontrado=true;
				eliminado=actual.getValor();
				listaNodos.eliminar(actual);
			}
		}
		return eliminado;
	}

	@Override
	public Iterator<V> deleteSet(K llave) {
		boolean encontrado=false;
		V eliminado=null;
		ArrayList<V>dinami=new ArrayList<V>();
		for (int i = 0; i < listaNodos.darTamano()&&!encontrado; i++) {
			NodoHash<K,V>actual=listaNodos.darElementoPos(i);
			if(actual.getLlave().compareTo(llave)==0) {
				encontrado=true;
				while(actual!=null) {
					eliminado=actual.getValor();
					dinami.add(eliminado);
					actual=actual.getSiguiente();
				}
				listaNodos.eliminar(actual);
			}
		}
		return dinami.iterator();
	}

	@Override
	public Iterator<K> keys() {;
		ArrayList<K>dinami=new ArrayList<K>();
		for (int i = 0; i < listaNodos.darTamano(); i++) {
			NodoHash<K,V>actual=listaNodos.darElementoPos(i);
			dinami.add(actual.getLlave());
		}
		return dinami.iterator();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacidad;
		return result;
	}




}
