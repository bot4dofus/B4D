package fr.B4D.transport;

import java.io.Serializable;
import java.util.List;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.socket.event.ChangeMapEvent;
import fr.B4D.socket.store.EventStore;

/**
 * The {@code TransportPath} class represents a path between two locations on the map.
 * <br><br>
 * A path is defines by a list of steps.
 * 
 * @author Lucas
 *
 */
public class TransportPath implements Serializable{
	
	private static final long serialVersionUID = 6035526052301664166L;
	
	/**
	 * List of steps in the path.
	 */
	private List<TransportStep> transportPath;
	
	/**
	 * Constructor of the {@code TransportPath} class. 
	 * @param shortestPath - Path between the two locations.
	 */
	public TransportPath(List<TransportStep> shortestPath) {
		this.transportPath = shortestPath;
	}
	
	/**
	 * Uses the path by using all the transports.
	 * @param person - Player using the transports.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unknown exception occur.
	 */
	public void use(Person person) throws StopProgramException, CancelProgramException, B4DException {
		for(TransportStep step:transportPath) {
			if (!person.getPosition().equals(step.getTransport().getPosition()))
				throw new B4DException("Wrong position.");
			step.use();
			if(EventStore.getInstance().waitForEvent(ChangeMapEvent.class, 20000) == null)
				throw new B4DException("Timeout while waiting for the map to change.");
			B4D.wait.sleep(1000); 	//Wait one more second for low connections
			person.setPosition(step.getDestination());
		}
	}
	
	/**
	 * Returns the total weight of the path. It is the sum of the transport weights.
	 * @return Total weight of the path.
	 */
	public double getWeigth() {
		return transportPath.stream().mapToDouble(t -> t.getTransport().getWeight()).sum();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String out = "\nFrom " +  transportPath.get(0).getTransport().getPosition() + " to " + transportPath.get(transportPath.size()-1).getDestination() + "\n";
		out += "Total weight = " + getWeigth() + " (" + transportPath.size() + " steps)\n";
		for(TransportStep step : transportPath)
			out += step + "\n";
		return out;
	}
}
