package fr.B4D.building;

import java.awt.Point;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

/**
 * The class {@code Building} represents a building on the map.<br><br>
 * A building has a position on the map.
 * 
 * @author Lucas
 *
 */
public abstract class Building{

	private Point position;
	
	/**
	 * Builder of the {@code Building} class.
	 * @param position - Position on the map. Cannot be {@code null}.
	 */
	public Building(Point position) {
		if(position == null)
			throw new IllegalArgumentException("The building position cannot be null.");
		
		this.position = position;
	}
	
	/**
	 * Returns the position of the building on the map.
	 * @return Position on the map.
	 */
	public Point getPosition() {
		return position;
	}
	
	/**
	 * Go to the building with a specific person. 
	 * @param person Person which go to the building.
	 * @throws StopProgramException If the program has been stopped.
	 * @throws CancelProgramException If the program has been canceled.
	 * @throws B4DException If a B4D exception has been raised.
	 */
	public void goTo(Person person) throws StopProgramException, CancelProgramException, B4DException {
		if(!person.getPosition().equals(position))
			person.goTo(position);
	}
}
