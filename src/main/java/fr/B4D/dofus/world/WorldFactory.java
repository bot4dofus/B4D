package fr.B4D.dofus.world;

/**
 * The class {@code WorldFactory} is used to instantiate worlds.
 * 
 * @author Lucas
 *
 */
public class WorldFactory {

	/**
	 * Returns the main world.
	 * @return Main world.
	 */
	public static MainWorld getMainWorld() {
		return new MainWorld();
	}
	
}
