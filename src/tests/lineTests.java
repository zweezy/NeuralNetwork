package tests;

import static org.junit.Assert.*;

import network.*;

import org.junit.Test;

public class lineTests {
	Line1 l = new Line1();

	@Test
	public void testL() {
		double x = 1;
		double y = 1;
		assertFalse(l.isAbove(x, y));
	}
	
	@Test
	public void testL1() {
		double x = 100;
		double y = 1;
		assertTrue(l.isAbove(x, y));
	}
	
	

}
