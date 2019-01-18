package fr.B4D.transport;

import java.awt.AWTException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.B4D.exceptions.B4DCannotFind;
import fr.B4D.exceptions.B4DWrongPosition;

public class TransportPath implements Serializable{
	
	private static final long serialVersionUID = 6035526052301664166L;
	
	private ArrayList<TransportStep> transportPath;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public TransportPath(List<B4DEdge> shortestPath) throws B4DCannotFind {
		transportPath = new ArrayList<TransportStep>();
		for(B4DEdge edge:shortestPath)
			transportPath.add(new TransportStep(edge));
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void use() throws AWTException, B4DCannotFind, B4DWrongPosition {
		for(TransportStep step:transportPath)
			step.use();
	}
	
	public double getWeigth() {
		return transportPath.stream().mapToDouble(t -> t.getWeigth()).sum();
	}
}
