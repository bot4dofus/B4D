package fr.B4D.bot;

import java.awt.Point;
import java.io.Serializable;
import java.util.List;

import fr.B4D.dofus.Dofus;
import fr.B4D.dofus.server.Server;
import fr.B4D.dofus.server.ServerEnum;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.Message;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.transport.TransportInterface;
import fr.B4D.transport.TransportPath;
import fr.B4D.transport.TransportStep;
import fr.B4D.transport.transports.Potion;
import fr.B4D.transport.transports.Zaap;
import fr.B4D.utils.PointF;

/**
 * The {@code Person} class represents a player in the game.
 * <br><br>
 * A player is defined by an acount name, a password, a server and a pseudo.
 * 
 * @author Lucas
 *
 */
public class Person implements Serializable, TransportInterface{

	private static final long serialVersionUID = -3206212064770380443L;
	
	/**
	 * Name of the account.
	 */
	private String account;
	
	/**
	 * Password of the account.
	 */
	private String password;
	
	/**
	 * Server on which the person is registered.
	 */
	private Server server;
	
	/**
	 * Pseudo of the person.
	 */
	private String pseudo;

	/**
	 * Booster potion transport.
	 * When used, the person it teleported to the last saved zaap.
	 */
	private TransportStep boosterPotion = null;
	
	/**
	 * Bonta potion transport.
	 * When used, the person is teleported to Bonta.
	 */
	private TransportStep bontaPotion = null;

	/**
	 * Brakmar potion transport.
	 * When used, the person is teleported to Brakmar.
	 */
	private TransportStep brakmarPotion = null;
	
	/**
	 * Location of the main spell used during fights.
	 */
	private PointF spellPosition = null;
	
	/**
	 * Current location of the person on the map.
	 */
	private Point position = null;
	
	/**
	 * Specifies whether the inventory is full or non.
	 */
	private boolean inventoryFull = false;
	
	/**
	 * Constructor of the {@code Person} class with default fields.<br><br>
	 * This is identical to {@code new Person("Nom de compte", "Mot de passe", Server.AGRIDE, "Pseudo")}.
	 */
	public Person() {
		this("Nom de compte", "Mot de passe", ServerEnum.AGRIDE.getValue(), "Pseudo");
	}
	
	/**
	 * Constructor of the {@code Person} class. 
	 * @param account - Name of the account
	 * @param password - Password of the account.
	 * @param serveur - Server of the person.
	 * @param pseudo - Pseudo of the person.
	 */
	public Person(String account, String password, Server serveur, String pseudo) {
		this.account = account;
		this.password = password;
		this.server = serveur;
		this.pseudo = pseudo;
		
		this.boosterPotion = new TransportStep(new Potion("Booster potion", null, null, BOOSTER_POTION_COST), Zaap.Astrub.getPosition());
		this.bontaPotion = new TransportStep(new Potion("Bonta potion", null, null, BONTA_POTION_COST), BONTA_POTION_DESTINATION);
		this.brakmarPotion = new TransportStep(new Potion("Brakmar potion", null, null, BRAKMAR_POTION_COST), BRAKMAR_POTION_DESTINATION);
	}
	
	/**
	 * Returns the name of the account.
	 * @return Name of the account.
	 */
	public String getAccount() {
		return account;
	}
	
	/**
	 * Defines the name of the account.
	 * @param account - Name of the account.
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	
	/**
	 * Returns the password of the account.
	 * @return Password of the account.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Defines the password of the account.
	 * @param password - Password of the account.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Returns the server of the person.
	 * @return Server of the person.
	 */
	public Server getServer() {
		return server;
	}
	
	/**
	 * Sets the server of the person.
	 * @param server - Server of the person.
	 */
	public void setServer(Server server) {
		this.server = server;
	}
	
	/**
	 * Returns the pseudo of the person.
	 * @return Pseiudo of the person.
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Sets the pseudo of the person.
	 * @param pseudo - Pseudo of the person.
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/**
	 * Returns the booster potion transport.
	 * @return Booster potion transport.
	 */
	public TransportStep getBoosterPotion() {
		return boosterPotion;
	}

	/**
	 * Defines the booster potion transport.
	 * @param boosterPotion - Booster potion transport.
	 */
	public void setBoosterPotion(TransportStep boosterPotion) {
		this.boosterPotion = boosterPotion;
	}

	/**
	 * Returns the Bonta potion transport.
	 * @return Bonta potion transport.
	 */
	public TransportStep getBontaPotion() {
		return bontaPotion;
	}

	/**
	 * Defines the Bonta potion transport.
	 * @param bontaPotion - Bonta potion transport.
	 */
	public void setBontaPotion(TransportStep bontaPotion) {
		this.bontaPotion = bontaPotion;
	}

	/**
	 * Returns the Brakmar potion transport.
	 * @return Brakmar potion transport.
	 */
	public TransportStep getBrakmarPotion() {
		return brakmarPotion;
	}

	/**
	 * Defines the Brakmar potion transport.
	 * @param brakmarPotion - Brakmar potion transport.
	 */
	public void setBrakmarPotion(TransportStep brakmarPotion) {
		this.brakmarPotion = brakmarPotion;
	}

	/**
	 * Returns the location of the main spell.
	 * @return Main spell used during fights.
	 */
	public PointF getSpellPosition() {
		return spellPosition;
	}

	/**
	 * Defines the location of the main spell.
	 * @param spellPosition - Main spell used during fights.
	 */
	public void setSpellPosition(PointF spellPosition) {
		this.spellPosition = spellPosition;
	}

	/**
	 * Returns the current location of the person.
	 * @return Current location of the person.
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * Defines the current location of the person.
	 * @param position - Location of the person on the map.
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * Automatically defines the position of the player by detecting it.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unknown error occurs.
	 */
	public void setPosition() throws StopProgramException, CancelProgramException, B4DException {
		Message message;
		Dofus.getInstance().getChat().addPseudoFilter(pseudo);
		Dofus.getInstance().getChat().clear();
		message = new Message(Channel.GENERAL, "%pos%");
		message.send();
		message = Dofus.getInstance().getChat().waitForMessage(1000);
		if(message == null)
			throw new CancelProgramException("Cannot get the current position. Make sure that the general channel is on and that the message was not the same as the previous one.");

		Dofus.getInstance().getChat().addPseudoFilter(null);
		
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
	
	/**
	 * Checks whether the inventory is full.
	 * @return {@code true} if the inventory is full, {@code false} otherwise.
	 */
	public boolean isInventoryFull() {
		return inventoryFull;
	}

	/**
	 * Defines whether the inventory is full or not.
	 * @param inventoryFull - {@code true} if the inventory is full, {@code false} otherwise.
	 */
	public void setInventoryFull(boolean inventoryFull) {
		this.inventoryFull = inventoryFull;
	}
	
	/* (non-Javadoc)
	 * @see fr.B4D.transport.TransportInterface#goTo(java.awt.Point)
	 */
	public void goTo(Point destination) throws StopProgramException, CancelProgramException, B4DException {
		TransportPath transportPath = getTransportPathTo(destination);
		transportPath.use(this);
	}
	
	/**
	 * Finds the transport path from the current location to the destination.
	 * @param destination - Destination to reach. You can use {@code new Point(X, Y)} to define a new location.
	 * @return Path to follow to reach the destination. {@code null} is not reachable.
	 */
	public TransportPath getTransportPathTo(Point destination) {		
		
		//Add potions
		if(boosterPotion.getTransport().getPositionF() != null) {
			boosterPotion.getTransport().setPosition(position);
			Dofus.getInstance().getWorld().getGraph().addEdge(boosterPotion);
		}
			
		if(bontaPotion.getTransport().getPositionF() != null){
			bontaPotion.getTransport().setPosition(position);
			Dofus.getInstance().getWorld().getGraph().addEdge(bontaPotion);
		}

		if(brakmarPotion.getTransport().getPositionF() != null){
			brakmarPotion.getTransport().setPosition(position);
			Dofus.getInstance().getWorld().getGraph().addEdge(brakmarPotion);
		}
		
		//Get the shortest path
	    List<TransportStep> shortestPath = Dofus.getInstance().getWorld().getGraph().getPath(position, destination).getEdgeList();

	    //Remove potions
		if(boosterPotion.getTransport().getPositionF() != null)
			Dofus.getInstance().getWorld().getGraph().removeEdge(boosterPotion);
		
		if(bontaPotion.getTransport().getPositionF() != null)
			Dofus.getInstance().getWorld().getGraph().removeEdge(bontaPotion);
		
		if(brakmarPotion.getTransport().getPositionF() != null)
			Dofus.getInstance().getWorld().getGraph().removeEdge(brakmarPotion);
		
		if(shortestPath != null)
			return new TransportPath(shortestPath);
		else
			return null;
	}
	
}
