package fr.B4D.transport;

import java.awt.AWTException;
import java.io.Serializable;
import java.util.List;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Person;
import fr.B4D.dofus.CannotFindException;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;

/** La classe {@code TransportPath} représente un chemin entre deux point de la carte.
 * Un chemin est défini par une liste d'étapes.
 */
public class TransportPath implements Serializable{
	
	private static final long serialVersionUID = 6035526052301664166L;
	
	private List<TransportStep> transportPath;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	/** Constructeur de la classe {@code TransportPath}. 
	 * @param shortestPath - Liste d'étapes.
	 */
	public TransportPath(List<TransportStep> shortestPath) {
		this.transportPath = shortestPath;
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	/** Permet d'utiliser le chemin en empruntant tous les transports du chemin.
	 * @param person - Joueur utilisant le chemin.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 * @throws CannotFindException Si la destination d'une des étapes est introuvable.
	 * @throws WrongPositionException Si le joueur est mal placé pour emprunter un moyen de transport.
	 * @throws AWTException Si un problème de souris survient.
	 */
	public void use(Person person) throws AWTException, CannotFindException, WrongPositionException, StopProgramException, CancelProgramException {
		for(TransportStep step:transportPath) {
			if (!person.getPosition().equals(step.getTransport().getPosition()))
				throw new WrongPositionException();
			step.use();
			B4D.screen.waitForMap();
			person.setPosition(step.getDestination());
		}
	}
	
	/** Retourne le poid total du chemin.
	 * @return
	 */
	public double getWeigth() {
		return transportPath.stream().mapToDouble(t -> t.getTransport().getWeight()).sum();
	}
	
	  /**************/
	 /** TOSTRING **/
	/**************/
	
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
