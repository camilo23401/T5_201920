package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.ArregloDinamico;
import model.data_structures.HashSeparateChaining;

public class TestSeparateChaining {

	private HashSeparateChaining<String,Integer> tabla;
	
	private static int TAMANO=1000;
	
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
}
