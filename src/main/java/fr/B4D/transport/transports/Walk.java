package fr.B4D.transport.transports;

import java.awt.Point;
import java.io.Serializable;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.transport.Transport;
import fr.B4D.utils.PointF;

/**
 * The {@code Walk} represents a walk.
 * <br><br>
 * This class extends {@code Transport}.
 * 
 * @author Lucas
 *
 */
public class Walk extends Transport implements Serializable{
	
	private static final long serialVersionUID = 3052740487765574838L;
	
	/**
	 * Vector to the vertex under to the walk origin.
	 */
	private final Point down = new Point(0, 1);

	/**
	 * Vector to the vertex above to the walk origin.
	 */
	private final Point up = new Point(0, -1);

	/**
	 * Vector to the vertex right side to the walk origin.
	 */
	private final Point right = new Point(1, 0);

	/**
	 * Vector to the vertex left side to the walk origin.
	 */
	private final Point left = new Point(-1, 0);
	
	/**
	 * Location of the click to perform to go up.
	 */
	private final PointF goUp = new PointF(0.5, 0.01);
	
	/**
	 * Location of the click to perform to go down.
	 */
	private final PointF goDown = new PointF(0.5, 0.879);
	
	/**
	 * Location of the click to perform to go right.
	 */
	private final PointF goRight = new PointF(0.99, 0.5);
	
	/**
	 * Location of the click to perform to go left.
	 */
	private final PointF goLeft = new PointF(0.01, 0.5);
	
	/**
	 * Constructor of the {@code Walk} class.
	 * @param position - Location of the walk origin.
	 */
	public Walk(Point position) {
		super("Walking", position, null, WALK_COST);
	}

	/* (non-Javadoc)
	 * @see fr.B4D.transport.TransportInterface#goTo(java.awt.Point)
	 */
	public void goTo(Point destination) throws StopProgramException, CancelProgramException, B4DException {		
		Point move = new Point(destination.x - super.getPosition().x, destination.y - super.getPosition().y);
		if(move.equals(up))
			B4D.mouse.leftClick(goUp, false);
		else if(move.equals(down))
			B4D.mouse.leftClick(goDown, false);
		else if(move.equals(left))
			B4D.mouse.leftClick(goLeft, false);
		else if(move.equals(right))
			B4D.mouse.leftClick(goRight, false);
	}
}
