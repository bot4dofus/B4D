package fr.B4D.transport;

import java.awt.AWTException;
import java.io.Serializable;
import java.util.List;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Person;
import fr.B4D.dofus.CannotFindException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

public class TransportPath implements Serializable{
	
	private static final long serialVersionUID = 6035526052301664166L;
	
	private List<TransportStep> transportPath;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public TransportPath(List<TransportStep> shortestPath) {
		this.transportPath = shortestPath;
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void use(Person person) throws AWTException, CannotFindException, WrongPositionException, StopProgramException, CancelProgramException {
		for(TransportStep step:transportPath) {
			if (!person.getPosition().equals(step.getTransport().getPosition()))
				throw new WrongPositionException();
			step.use();
			B4D.screen.waitForMap();
			person.setPosition(step.getDestination());
		}
	}
	
	public double getWeigth() {
		return transportPath.stream().mapToDouble(t -> t.getTransport().getWeight()).sum();
	}
	
	  /**************/
	 /** TOSTRING **/
	/**************/
	
	public String toString() {
		String out = "\nFrom " +  transportPath.get(0).getTransport().getPosition() + " to " + transportPath.get(transportPath.size()-1).getDestination() + "\n";
		out += "Total weight = " + getWeigth() + " (" + transportPath.size() + " steps)\n";
		for(TransportStep step : transportPath)
			out += step + "\n";
		return out;
	}
}
