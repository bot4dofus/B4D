package fr.B4D.program;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Person;
import fr.B4D.bot.statics.Mouse;
import fr.B4D.dofus.B4DCannotFind;
import fr.B4D.dofus.Dofus;
import fr.B4D.farming.Ressource;
import fr.B4D.farming.RessourceType;
import fr.B4D.interaction.chat.Message;
import fr.B4D.programs.Deplacement;
import fr.B4D.programs.Loto;
import fr.B4D.programs.tutorials.MessageAPI;
import fr.B4D.transport.B4DWrongPosition;
import fr.B4D.utils.PointF;
import net.sourceforge.tess4j.TesseractException;

public class Program extends Thread implements Serializable{

	private static final long serialVersionUID = -4725926340583319625L;

	  /****************/
	 /** COLLECTION **/
	/****************/
	
	public final static Program deplacement = new Program(Place.Aucune, Category.Aucune, RessourceType.Aucun, Ressource.Aucune, Deplacement.deplacement);
	public final static Program loto = new Program(Place.Tous, Category.Jeux, RessourceType.Aucun, Ressource.Aucune, Loto.loto);
	
	/** TUTORIALS **/

	public final static Program MessageAPItutorial1 = new Program(Place.Aucune, Category.Test, RessourceType.Aucun, Ressource.Aucune, MessageAPI.tutorial1);
	public final static Program MessageAPItutorial2 = new Program(Place.Aucune, Category.Test, RessourceType.Aucun, Ressource.Aucune, MessageAPI.tutorial2);
	public final static Program MessageAPItutorial3 = new Program(Place.Aucune, Category.Test, RessourceType.Aucun, Ressource.Aucune, MessageAPI.tutorial3);
	
	  /***************/
	 /** ATTRIBUTS **/
	/***************/
	
	private Person person;
	
	private Place place;
	private Category category;
	private RessourceType ressourceType;
	private Ressource ressource;

	private ProgramInterface program;
	
	private int maxCycles;
	private int maxDeposits;

	private boolean hdvWhenFull;
	private boolean bankWhenFull;
	private boolean stopWhenFull;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public Program(Place place, Category category, RessourceType type, Ressource ressource, ProgramInterface program) {
		this.place = place;
		this.category = category;
		this.ressourceType = type;
		this.ressource = ressource;
		this.program = program;
		this.maxCycles = -1;
		this.maxDeposits = -1;
	}
	
	  /************************/
	 /** METHODES STATIQUES **/
	/************************/
	
  public final static ArrayList<Program> getAll(){
  	ArrayList<Program> programs = new ArrayList<Program>();
  	programs.add(deplacement);
  	programs.add(loto);
  	programs.add(MessageAPItutorial1);
  	programs.add(MessageAPItutorial2);
  	programs.add(MessageAPItutorial3);
    return programs;
  }
	
	  /***********************/
	 /** GETTERS & SETTERS **/
	/***********************/
	
	public Place getPlace() {
		return place;
	}
	public Category getCategory() {
		return category;
	}
	public RessourceType getRessourceType() {
		return ressourceType;
	}
	public Ressource getRessource() {
		return ressource;
	}
	public int getMaxCycles() {
		return maxCycles;
	}
	public void setMaxCycles(int maxCycles) {
		this.maxCycles = maxCycles;
	}
	public int getMaxDeposits() {
		return maxDeposits;
	}
	public void setMaxDeposits(int maxDeposits) {
		this.maxDeposits = maxDeposits;
	}
	public boolean isHdvWhenFull() {
		return hdvWhenFull;
	}
	public void setHdvWhenFull(boolean hdvWhenFull) {
		this.hdvWhenFull = hdvWhenFull;
	}
	public boolean isBankWhenFull() {
		return bankWhenFull;
	}
	public void setBankWhenFull(boolean bankWhenFull) {
		this.bankWhenFull = bankWhenFull;
	}
	public boolean isStopWhenFull() {
		return stopWhenFull;
	}
	public void setStopWhenFull(boolean stopWhenFull) {
		this.stopWhenFull = stopWhenFull;
	}

	  /**************/
	 /** METHODES **/
	/**************/
	
	public void startWith(Person person) {
		this.person = person;
		start();
	}
	
	public void run() {
		try {
			if(this.category != Category.Test)
				Intro(person);
			Tours();
			if(this.category != Category.Test)
				Outro();
			
			B4D.logger.popUp("Le bot s'est correctement terminé.");
		}catch(B4DWrongPosition | AWTException | UnsupportedFlavorException | IOException | B4DCannotFind | TesseractException | InterruptedException e){
			B4D.logger.error(e);
		}
	}

	private void Intro(Person person) throws B4DWrongPosition, AWTException, UnsupportedFlavorException, IOException{
		
		B4D.screen.focusDofus();
		Message.sendChat("/clear");
		
		if(B4D.screen.getPixelColor(new PointF(0.28, 0.99)).getGreen() > 200){	//Le mode solo n'est pas activé
            Mouse.leftClick(new PointF(0.28, 0.99), false);                	//Clic sur status
            Mouse.leftClick(new PointF(0.3, 0.976), false);                	//Clic sur solo
		}

		person.setPosition(Dofus.world.getPosition());	//Récupère la position actuelle
	}
	private void Tours() throws AWTException, B4DCannotFind, B4DWrongPosition, UnsupportedFlavorException, IOException, TesseractException, InterruptedException{
		int nbCycles = 0, nbDeposits = 0;

		while(nbCycles != maxCycles && nbDeposits != maxDeposits) {
			try {
				program.run(person);
			} catch (B4DFullInventory e) {			
				if(hdvWhenFull) {
					//Mettre en HDV
				}
				if(bankWhenFull) {
					//Mettre en banque
				}
				if(stopWhenFull)
					break;
				
				maxDeposits++;
				
			}finally {
				maxCycles++;
			}
		}
	}
	private void Outro() {
		//Nothing special for the moment
	}
}
