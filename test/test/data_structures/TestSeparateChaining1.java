package test.data_structures;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.ArregloDinamico;
import model.data_structures.HashSeparateChaining;
public class TestSeparateChaining1 {
	private HashSeparateChaining<String,Integer> tabla;

	private static int TAMANO=20;

	@Before
	public void setUp1() {
		tabla= new HashSeparateChaining<String,Integer>(TAMANO);
	}
	@Test
	public void testHashSeparateChaining() {
		assertTrue(tabla!=null);
		assertEquals(0, tabla.size());
		assertEquals(TAMANO,tabla.darCapacidad());
	}
	@Test
	public void testPut() {
		tabla.put("Camilo", 2);
		tabla.put("Nestor", 4);
		tabla.put("NicolEt", 4);
		tabla.put("Daniel", 4);
		tabla.put("Daniel", 8);
		assertEquals(4, tabla.size());


	}
	@Test
	public void testPutIn() {
		tabla.putInSet("Camilo", 2);
		tabla.putInSet("Nestor", 4);
		tabla.putInSet("NicolEt", 4);
		tabla.putInSet("Daniel", 4);
		tabla.putInSet("Daniel", 8);
		assertEquals(5, tabla.size());


	}
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
	@Test
	public void keys() {
		tabla.putInSet("Camilo", 2);
		tabla.putInSet("Nestor", 4);
		tabla.putInSet("NicolEt", 4);
		tabla.putInSet("Daniel", 4);
		tabla.putInSet("Daniel", 8);
		tabla.putInSet("Daniel", 16);
		tabla.putInSet("Daniel", 20);
		Iterator<String>ite=tabla.keys();
		String valor=ite.next();
		assertEquals("Daniel",valor);
		valor=ite.next();
		assertEquals("Camilo",valor);
		valor=ite.next();
		assertEquals("NicolEt",valor);
		valor=ite.next();
		assertEquals("Nestor",valor);
		
	}
	@Test
	public void rehash() {
		assertEquals(20,tabla.darCapacidad());
		tabla.putInSet("Camilo", 2);
		tabla.putInSet("Nestor", 4);
		tabla.putInSet("NicolEt", 4);
		tabla.putInSet("Daqqniel", 4);
		tabla.putInSet("Daniel", 8);
		tabla.putInSet("Dqqaniesl", 16);
		tabla.putInSet("Daniel", 20);
		tabla.putInSet("Daniel", 20);
		tabla.putInSet("Dqasaniel", 20);
		tabla.putInSet("Danqqiel", 20);
		tabla.putInSet("qqDaniel", 20);
		tabla.putInSet("Daaniel", 20);
		tabla.putInSet("Daniel", 20);
		tabla.putInSet("Dassniel", 20);
		tabla.putInSet("Daniwsdel", 20);
		tabla.putInSet("Daqqniel", 20);
		tabla.putInSet("Danssiel", 20);
		assertEquals(40,tabla.darCapacidad());
		
	}

}
