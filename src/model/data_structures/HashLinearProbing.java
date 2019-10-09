package model.data_structures;

import java.util.Iterator;

public class HashLinearProbing <K, V>
{
	private int N;
	private int M;
	private K[] llaves;
	private V[] valores;

	public HashLinearProbing(int pCapacidad)
	{
		M = pCapacidad;
		llaves = (K[]) new Object[M];
		valores = (V[]) new Object[M];
	}
	public void put(K pLlave, V pValor )
	{
		if((double)N/(double)M>=0.75)
		{
			resize(M*2);
		}
		int i;
		if(pLlave==null||pLlave=="")
		{
			return;	
		}
		for(i=hash(pLlave);llaves[i]!=null;i=(i+1)%M)
		{
			if(llaves[i].equals(pLlave))
			{
				valores[i] = pValor;
				return;
			}
		}
		llaves[i] = pLlave;
		valores[i] =  pValor;
		N++;
	}
	public void putInSet(K pLlave,V pValor)
	{
		if((double)N/(double)M>=0.75)
		{
			resize(M*2);
		}
		int i;
		if(pLlave==null||pLlave=="")
		{
			return;	
		}
		for(i=hash(pLlave);llaves[i]!=null;i=(i+1)%M)
		{
			if(llaves[i].equals(pLlave))
			{
				valores[i] = pValor;
				return;
			}
		}
		llaves[i] = pLlave;
		valores[i] =  pValor;
		N++;
	}
	public V get(K pLlave)
	{
		for(int i= hash(pLlave); llaves[i]!=null; i = (i+1)%M)
		{
			if(llaves[i].equals(pLlave))
			{
				return valores[i];
			}
		}
		return null;
	}
	public V delete(K pLlave)
	{
		if(pLlave==null)
		{
			System.out.println("Llave null");
			return null;
		}
		if(!contieneLlave(pLlave))
		{
			System.out.println("No tiene llave");
			return null;
		}
		int i = hash(pLlave);
		while(!pLlave.equals(llaves[i]))
		{
			i = (i+1)%M;
		}
		System.out.println(i);
		V porEliminar = valores[i];
		llaves[i] = null;
		valores[i] = null;
		i = (i+1)%M;
		while(llaves[i]!=null)
		{
			K porRehash = llaves[i];
			V porRehashValor = valores[i];
			llaves[i] = null;
			valores[i] = null;
			N--;
			put(porRehash, porRehashValor);
			i = (i+1)%M;
		}
		N--;
		return porEliminar;
	}
	public Iterator<K> keys ()
	{
		Stack<K> llavesIterador = new Stack<K>();
		for(int i = 0; i< M; i++)
		{
			if(llaves[i]!=null)
			{
				llavesIterador.push(llaves[i]);
			}
		}
		return llavesIterador.iterator();
	}
	public void resize(int pCapacidad)
	{
		HashLinearProbing<K, V> temporal = new HashLinearProbing<K, V>(pCapacidad);
		for(int i=0; i< M; i++)
		{
			if(llaves[i]!=null)
			{
				temporal.put(llaves[i], valores[i]);
			}
		}
		llaves = temporal.llaves;
		valores = temporal.valores;
	}
	private int hash(K pLlave)
	{
		return (pLlave.hashCode() & 0x7fffffff) % M;
	}
	public boolean contieneLlave(K pLlave)
	{
		for(int i = 0; i<llaves.length;i++)
		{
			if(llaves[i]!=null)
			{
				if(llaves[i].equals(pLlave))
				{
					return true;
				}	
			}
		}
		return false;
	}
	public int size()
	{
		return N;
	}
	public int darCapacidad()
	{
		return M;
	}
}
