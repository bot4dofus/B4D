package fr.B4D.transport;

import java.awt.AWTException;
import java.io.Serializable;
import java.util.List;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Person;
import fr.B4D.dofus.B4DCannotFind;

public class TransportPath implements Serializable{
	
	private static final long serialVersionUID = 6035526052301664166L;
	
	private List<TransportStep> transportPath;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public TransportPath(List<TransportStep> shortestPath) throws B4DCannotFind {
		this.transportPath = shortestPath;
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void use(Person person) throws AWTException, B4DCannotFind, B4DWrongPosition {
		for(TransportStep step:transportPath) {
			if (!person.getPosition().equals(step.getTransport().getPosition()))
				throw new B4DWrongPosition();
			step.use();
			B4D.screen.waitForMap();
			person.setPosition(step.getDestination());
		}
	}
	
	public double getWeigth() {
		return transportPath.stream().mapToDouble(t -> t.getTransport().getWeight()).sum();
	}
}
