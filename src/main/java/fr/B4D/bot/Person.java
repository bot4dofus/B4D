package fr.B4D.bot;

import java.awt.AWTException;
import java.awt.Point;
import java.io.Serializable;
import java.util.List;

import fr.B4D.dofus.CannotFindException;
import fr.B4D.dofus.Dofus;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.Message;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.transport.WrongPositionException;
import fr.B4D.transport.TransportInterface;
import fr.B4D.transport.TransportPath;
import fr.B4D.transport.TransportStep;
import fr.B4D.transport.transports.Potion;
import fr.B4D.transport.transports.Zaap;
import fr.B4D.utils.PointF;

/** La classe {@code Person} représente un joueur. Un joueur est défini par un nom de compte, un mot de passe, un serveur et un pseudo
 *
 */
public class Person implements Serializable, TransportInterface{

	private static final long serialVersionUID = -3206212064770380443L;

	  /***************/
	 /** ATTRIBUTS **/
	/***************/
	
	private String account;
	private String password;
	private Server server;
	private String pseudo;

	private TransportStep boosterPotion = null;
	private TransportStep bontaPotion = null;
	private TransportStep brakmarPotion = null;
	
	private PointF spellPosition = null;
	
	private Point position = null;
	private boolean inventoryFull = false;
	
	  /*************/
	 /** BUILDER **/
	/*************/
	
	/** Constructeur de la classe {@code Person} utilisant les champs par défaut.
	 * Cela est identique à {@code new Person("Nom de compte", "Mot de passe", Server.AGRIDE, "Pseudo")}.
	 */
	public Person() {
		this("Nom de compte", "Mot de passe", Server.AGRIDE, "Pseudo");
	}
	
	/** Constructeur de la classe {@code Person}.
	 * @param account - Nom de compte du joueur.
	 * @param password - Mot de passe du joueur.
	 * @param serveur - Serveur du joueur.
	 * @param pseudo - Pseudo du joueur.
	 */
	public Person(String account, String password, Server serveur, String pseudo) {
		this.account = account;
		this.password = password;
		this.server = serveur;
		this.pseudo = pseudo;
		
		this.boosterPotion = new TransportStep(new Potion("Booster potion", null, null, boosterPotionCost), Zaap.Astrub.getPosition());
		this.bontaPotion = new TransportStep(new Potion("Bonta potion", null, null, bontaPotionCost), bontaPotionDestination);
		this.brakmarPotion = new TransportStep(new Potion("Brakmar potion", null, null, brakmarPotionCost), brakmarPotionDestination);
	}
	
	  /***********************/
	 /** GETTERS & SETTERS **/
	/***********************/
	
	/** Retourne le nom de compte du joueur.
	 * @return Nom de compte du joueur.
	 */
	public String getAccount() {
		return account;
	}
	
	/** Modifie le nom de compte du joueur.
	 * @param account - Nouveau nom de compte.
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	/** Retourne le mot de passe du joueur.
	 * @return Mot de passe du joueur.
	 */
	public String getPassword() {
		return password;
	}
	
	/** Modifie le mot de passe du joueur.
	 * @param password - Nouveau mot de passe.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/** Retourne le serveur du joueur.
	 * @return Serveur du joueur.
	 */
	public Server getServer() {
		return server;
	}
	
	/** Modifie le serveur du joueur.
	 * @param server - Nouveau serveur.
	 */
	public void setServer(Server server) {
		this.server = server;
	}
	
	/** Retourne le pseudo du joueur.
	 * @return Pseudo du joueur.
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/** Modifie le pseudo du joueur.
	 * @param pseudo - Pseudo nouveau pseudo.
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/** Retourne la potion de rappel du joueur.
	 * @return Potion de rappel du joueur.
	 */
	public TransportStep getBoosterPotion() {
		return boosterPotion;
	}

	/** Modifie la potion de rappel du joueur.
	 * @param boosterPotion - Nouvelle potion de rappel.
	 */
	public void setBoosterPotion(TransportStep boosterPotion) {
		this.boosterPotion = boosterPotion;
	}

	/** Retourne la potion de bonta du joueur.
	 * @return Potion de bonta du joueur.
	 */
	public TransportStep getBontaPotion() {
		return bontaPotion;
	}

	/** Modifie la potion de bonta du joueur.
	 * @param bontaPotion - Nouvelle potion de bonta.
	 */
	public void setBontaPotion(TransportStep bontaPotion) {
		this.bontaPotion = bontaPotion;
	}

	/** Retourne la potion de brakmar du joueur.
	 * @return Potion de brakmar du joueur.
	 */
	public TransportStep getBrakmarPotion() {
		return brakmarPotion;
	}

	/** Modifie la potion de brakmar du joueur.
	 * @param brakmarPotion - Nouvelle potion de brakmar.
	 */
	public void setBrakmarPotion(TransportStep brakmarPotion) {
		this.brakmarPotion = brakmarPotion;
	}

	/** Retourne la position du sort principal du joueur.
	 * @return position du sort principal.
	 */
	public PointF getSpellPosition() {
		return spellPosition;
	}

	/** Modifie la position du sort principal du joueur.
	 * @param spellPosition - Position du sort principal.
	 */
	public void setSpellPosition(PointF spellPosition) {
		this.spellPosition = spellPosition;
	}

	/** Retourne la position actuelle du joueur.
	 * @return position actuelle du joueur.
	 */
	public Point getPosition() {
		return position;
	}

	/** Modifie la position actuelle du joueur.
	 * @param position - Nouvelle position du joueur.
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/** Met à jour la position actuelle du joueur à tapant %pos% dans le chat.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 * @throws CannotGetPositionException Si aucun message n'est lu dans le chat.
	 */
	public void setPosition() throws StopProgramException, CancelProgramException, CannotGetPositionException {
		Message message;
		Dofus.chat.addPseudoFilter(pseudo);
		
		message = new Message(Channel.GENERAL, "%pos%");
		message.send();
		message = Dofus.chat.waitForMessage(1000);
		if(message == null)
			throw new CannotGetPositionException();

		Dofus.chat.addPseudoFilter(null);
		
		String text = message.getText();
		int firstComma = text.indexOf(",");
		int secondComma = text.indexOf(",", firstComma+1);
		int thirdComma = text.indexOf(",", secondComma+1);
		int end = text.indexOf("}");
		
		int x = Integer.parseInt(text.substring(firstComma+1, secondComma));
		int y = Integer.parseInt(text.substring(secondComma+1, thirdComma));
		int z = Integer.parseInt(text.substring(thirdComma+1, end));
		
		setPosition(new Point(x, y));
	}
	
	/** Retourne un booléen représentant l'inventaire du joueur.
	 * @return {@code true} si l'inventaire est plein et {@code false} sinon.
	 */
	public boolean isInventoryFull() {
		return inventoryFull;
	}

	/** Modifie l'état du l'inventaire du joueur.
	 * @param inventoryFull - {@code true} si l'inventaire est plein et {@code false} sinon.
	 */
	public void setInventoryFull(boolean inventoryFull) {
		this.inventoryFull = inventoryFull;
	}
	
	  /***********/
	 /** GO TO **/
	/***********/
	
	/* (non-Javadoc)
	 * @see fr.B4D.transport.TransportInterface#goTo(java.awt.Point)
	 */
	public void goTo(Point destination) throws AWTException, CannotFindException, WrongPositionException, StopProgramException, CancelProgramException {
		TransportPath transportPath = getTransportPathTo(destination);
		transportPath.use(this);
	}
	
	/** Retourne le chemin le plus court entre la position actuelle du joueur et la destination.
	 * @param destination - Destination à atteindre. Vous pouver utiliser {@code new Point(X, Y)} pour définir un nouveau point.
	 * @return Chemin à suivre pour atteindre la destination. {@code null} si la destination n'est pas atteignable.
	 */
	public TransportPath getTransportPathTo(Point destination) {		
		
		//Add potions
		if(boosterPotion.getTransport().getPositionF() != null) {
			boosterPotion.getTransport().setPosition(position);
			Dofus.world.getGraph().addEdge(boosterPotion);
		}
			
		if(bontaPotion.getTransport().getPositionF() != null){
			bontaPotion.getTransport().setPosition(position);
			Dofus.world.getGraph().addEdge(bontaPotion);
		}

		if(brakmarPotion.getTransport().getPositionF() != null){
			brakmarPotion.getTransport().setPosition(position);
			Dofus.world.getGraph().addEdge(brakmarPotion);
		}
		
		//Get the shortest path
	    List<TransportStep> shortestPath = Dofus.world.getGraph().getPath(position, destination).getEdgeList();

	    //Remove potions
		if(boosterPotion.getTransport().getPositionF() != null)
			Dofus.world.getGraph().removeEdge(boosterPotion);
		
		if(bontaPotion.getTransport().getPositionF() != null)
			Dofus.world.getGraph().removeEdge(bontaPotion);
		
		if(brakmarPotion.getTransport().getPositionF() != null)
			Dofus.world.getGraph().removeEdge(brakmarPotion);
		
		if(shortestPath != null)
			return new TransportPath(shortestPath);
		else
			return null;
	}
	
}
