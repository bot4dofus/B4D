package fr.B4D.almanax;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.B4D.bot.B4DException;
import fr.B4D.dofus.DofusDatabase;
import fr.B4D.dofus.items.Item;
import fr.B4D.dofus.items.Resource;
import fr.B4D.dofus.items.Stack;

@SuppressWarnings("javadoc")
public class AlmanaxTest {

	private static DofusDatabase database;
	private static Almanax almanax;
	
	@BeforeClass
	public static void init() throws B4DException {
		database = new DofusDatabase("/data/items.fr.json");
		almanax = new Almanax(Almanax.FRENCH_LANGUAGE, database);
	}

	@Test
	public void testGetStack() throws B4DException {
		Stack stack = almanax.getStack();
		Assert.assertNotNull(stack);
	}

	@Test
	public void testGetStack_17_11_2019() throws B4DException {
		Resource resource = database.findResourceByName("Viande Macérée");
		Stack expectedStack = new Stack((Item) resource, 1);
		
		Stack stack = almanax.getStack(2019, 11, 17);
		
		Assert.assertNotNull(stack);
		Assert.assertEquals(expectedStack, stack);
	}

	@Test
	public void testGetStack_21_06_2015() throws B4DException {
		Resource resource = database.findResourceByName("Conque Marine");
		Stack expectedStack = new Stack((Item) resource, 2);
		
		Stack stack = almanax.getStack(2015, 06, 21);

		Assert.assertNotNull(stack);
		Assert.assertEquals(expectedStack, stack);
	}
}