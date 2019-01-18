package fr.B4D.transport;

import java.awt.AWTException;
import java.awt.Point;
import java.io.Serializable;

import fr.B4D.bot.Configuration;
import fr.B4D.exceptions.B4DCannotFind;
import fr.B4D.exceptions.B4DWrongPosition;
import fr.B4D.transport.transports.BontaPotion;
import fr.B4D.transport.transports.BoosterPotion;
import fr.B4D.transport.transports.BrakmarPotion;
import fr.B4D.transport.transports.Walk;
import fr.B4D.transport.transports.Zaap;
import fr.B4D.transport.transports.ZaapiBonta;
import fr.B4D.transport.transports.ZaapiBrakmar;

public class TransportStep implements Serializable{

	private static final long serialVersionUID = 5240292689676673762L;
	
	private Transport transport;
	private Point destination;

	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public TransportStep(B4DEdge edge) throws B4DCannotFind {
		switch(edge.getTypeDeTransport()) {
			case BoosterPotion:
				transport = new BoosterPotion(edge.getSource(), Configuration.getInstance().persons.get(0).boosterPotionPosition);
				break;
			case BontaPotion:
				transport = new BontaPotion(edge.getSource(), Configuration.getInstance().persons.get(0).bontaPotionPosition);
				break;
			case BrakmarPotion:
				transport = new BrakmarPotion(edge.getSource(), Configuration.getInstance().persons.get(0).brakmarPotionPosition);
				break;
			case Walk:
				transport = new Walk(edge.getSource());
				break;
			case Zaap:
				transport = Zaap.getZaap(edge.getSource());
				break;
			case ZaapiBonta:
				transport = ZaapiBonta.getZaap(edge.getSource());
				break;
			case ZaapiBrakmar:
				transport = ZaapiBrakmar.getZaap(edge.getSource());
				break;
			default:
				break;
		}
		destination = edge.getTarget();
	}

	  /**************/
	 /** METHODES **/
	/**************/
	
	public void use() throws AWTException, B4DCannotFind, B4DWrongPosition {
		transport.goTo(destination);
	}

	public double getWeigth() {
		return transport.getWeight();
	}
}
