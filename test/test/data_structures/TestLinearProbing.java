package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;


import model.data_structures.TablaHashLineal;

public class TestLinearProbing 
{
	private TablaHashLineal<String, Integer> tabla;
	private static int TAMANO=21;

	@Before
	public void setUp1() {
		tabla= new TablaHashLineal<String,Integer>(TAMANO);
	}
	@Test
	public void testTablaHashLineal() {
		assertTrue(tabla!=null);
		assertEquals(0, tabla.size());
		assertEquals(TAMANO,tabla.darCapacidad());
	}
	@Test
	public void testPut() {
		tabla.put("Camilo", 2);
		tabla.put("Nestor", 4);
		tabla.put("Daniel", 3);
		tabla.put("Mia", 5);
		tabla.put("NicolEt", 4);
		tabla.put("Mia", 1);
		tabla.put("Majo", 3);
		tabla.put("", 7);
		tabla.put(null, 4);
		assertEquals(7, tabla.size());


	}
	/**@Test
public void testPutIn() {
	tabla.putInSet("Camilo", 2);
	tabla.putInSet("Nestor", 4);
	tabla.putInSet("NicolEt", 4);
	tabla.putInSet("Daniel", 4);
	tabla.putInSet("Daniel", 8);
	assertEquals(5, tabla.size());


}
	*/
	@Test
	public void get() {
		
		tabla.put("Camilo", 2);
		tabla.put("Nestor", 4);
		tabla.put("NicolEt", 4);
		tabla.put("Daniel", 4);
		tabla.put("Daniel", 8);

		int valor=tabla.get("Camilo");
		assertEquals(2,valor);
		valor=tabla.get("Nestor");
		assertEquals(4,valor);
		valor=tabla.get("NicolEt");
		assertEquals(4,valor);
		valor=tabla.get("Daniel");
		assertEquals(8,valor);

	}
	/**
	@Test
	public void getSet() {
		tabla.putInSet("Camilo", 2);
		tabla.putInSet("Nestor", 4);
		tabla.putInSet("NicolEt", 4);
		tabla.putInSet("Daniel", 4);
		tabla.putInSet("Daniel", 8);
		tabla.putInSet("Daniel", 16);
		tabla.putInSet("Daniel", 20);
		Iterator<Integer>ite=tabla.getSet("Daniel");
		int valor=ite.next();
		assertEquals(20,valor);
		valor=ite.next();
		assertEquals(16,valor);
		valor=ite.next();
		assertEquals(8,valor);
		valor=ite.next();
		assertEquals(4,valor);
	}
	 */
	@Test
	public void delete() {
		tabla.put("Camilo", 2);
		tabla.put("Nestor", 4);
		tabla.put("NicolEt", 4);
		tabla.put("Daniel", 4);
		assertEquals(4, tabla.size());
		int val=tabla.delete("Camilo");
		assertEquals(2,val);
		assertEquals(3, tabla.size());
		assertNull(tabla.get("Camilo"));
		val=tabla.delete("Nestor");
		assertEquals(4,val);
		assertEquals(2, tabla.size());
		assertNull(tabla.get("Nestor"));

	}
	/**
	@Test
	public void deleteSet() {
		tabla.putInSet("Camilo", 2);
		tabla.putInSet("Nestor", 4);
		tabla.putInSet("NicolEt", 4);
		tabla.putInSet("Daniel", 4);
		tabla.putInSet("Daniel", 8);
		tabla.putInSet("Daniel", 16);
		tabla.putInSet("Daniel", 20);
		Iterator<Integer>ite=tabla.deleteSet("Daniel");
		assertEquals(3, tabla.size());
		assertNull(tabla.get("Daniel"));
		int valor=ite.next();
		assertEquals(20,valor);
		valor=ite.next();
		assertEquals(16,valor);
		valor=ite.next();
		assertEquals(8,valor);
		valor=ite.next();
		assertEquals(4,valor);
	}
	**/ 
	@Test
	public void keys() {
		tabla.put("Camilo", 2);
		tabla.put("Nestor", 4);
		tabla.put("NicolEt", 4);
		tabla.put("Daniel", 4);
		tabla.put("Daniel", 8);
		
		
		Iterator<String>ite=tabla.keys();
		String valor=ite.next();
		assertEquals("NicolEt",valor);
		valor=ite.next();
		assertEquals("Nestor",valor);              //NO DEBERIA IR PRIMERO NESTOR? JMMM
		valor=ite.next();
		assertEquals("Daniel",valor);
		valor=ite.next();
		assertEquals("Daniel",valor);
		valor = ite.next();
		assertEquals("Camilo",valor);
		

	}
}
