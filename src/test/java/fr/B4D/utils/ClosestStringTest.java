package fr.B4D.utils;

import org.junit.Assert;
import org.junit.Test;

public class ClosestStringTest {
	
	@Test
	public void testOui1() {
		ClosestString closestString = new ClosestString("Oui", "Non");
		Assert.assertEquals(closestString.getClosest("oui"), "Oui");
	}
	
	@Test
	public void testOui2() {
		ClosestString closestString = new ClosestString("Oui", "Non");
		Assert.assertEquals(closestString.getClosest("OUI"), "Oui");
	}
	
	@Test
	public void testOui3() {
		ClosestString closestString = new ClosestString("Oui", "Non");
		Assert.assertEquals(closestString.getClosest("o u i !"), "Oui");
	}
	
	@Test
	public void tesNon1() {
		ClosestString closestString = new ClosestString("Oui", "Non");
		Assert.assertEquals(closestString.getClosest("non"), "Non");
	}
	
	@Test
	public void tesNon2() {
		ClosestString closestString = new ClosestString("Oui", "Non");
		Assert.assertEquals(closestString.getClosest("NON"), "Non");
	}
	
	@Test
	public void testNon3() {
		ClosestString closestString = new ClosestString("Oui", "Non");
		Assert.assertEquals(closestString.getClosest("onn."), "Non");
	}
}
