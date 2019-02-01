package fr.B4D.program;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Person;
import fr.B4D.dofus.B4DCannotFind;
import fr.B4D.dofus.Dofus;
import fr.B4D.interaction.chat.Message;
import fr.B4D.programs.Loto;
import fr.B4D.programs.tutorials.ExchangeAPI;
import fr.B4D.programs.tutorials.MessageAPI;
import fr.B4D.programs.tutorials.TransportAPI;
import fr.B4D.transport.B4DWrongPosition;
import fr.B4D.utils.PointF;
import net.sourceforge.tess4j.TesseractException;

public class Program extends Thread implements Serializable{

	private static final long serialVersionUID = -4725926340583319625L;

	  /****************/
	 /** COLLECTION **/
	/****************/
	
	public final static Program loto = new Program(Place.Astrub, Category.Jeux, "Argent", "Loto", Loto.loto);
	
	/** TUTORIALS **/

	public final static Program messageAPItutorial1 = new Program(Place.Aucun, Category.Tutorial, "Message API", "Tutorial 1", MessageAPI.tutorial1);
	public final static Program messageAPItutorial2 = new Program(Place.Aucun, Category.Tutorial, "Message API", "Tutorial 2", MessageAPI.tutorial2);
	public final static Program messageAPItutorial3 = new Program(Place.Aucun, Category.Tutorial, "Message API", "Tutorial 3", MessageAPI.tutorial3);

	public final static Program exchangeAPItutorial1 = new Program(Place.Aucun, Category.Tutorial, "Exchange API", "Tutorial 1", ExchangeAPI.tutorial1);
	public final static Program exchangeAPItutorial2 = new Program(Place.Aucun, Category.Tutorial, "Exchange API", "Tutorial 2", ExchangeAPI.tutorial2);
	public final static Program exchangeAPItutorial3 = new Program(Place.Aucun, Category.Tutorial, "Exchange API", "Tutorial 3", ExchangeAPI.tutorial3);
	
	public final static Program transportAPItutorial1 = new Program(Place.Aucun, Category.Tutorial, "Transport API", "Tutorial 1", TransportAPI.tutorial1);
	public final static Program transportAPItutorial2 = new Program(Place.Aucun, Category.Tutorial, "Transport API", "Tutorial 2", TransportAPI.tutorial2);
	public final static Program transportAPItutorial3 = new Program(Place.Aucun, Category.Tutorial, "Transport API", "Tutorial 3", TransportAPI.tutorial3);
	
	  /***************/
	 /** ATTRIBUTS **/
	/***************/
	
	private Person person;
	
	private Place place;
	private Category category;
	private String subCategory;
	private String programName;

	private ProgramInterface program;
	
	private int maxCycles;
	private int maxDeposits;

	private boolean hdvWhenFull;
	private boolean bankWhenFull;
	private boolean stopWhenFull;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public Program(Place place, Category category, String subCategory, String programName, ProgramInterface program) {
		this.place = place;
		this.category = category;
		this.subCategory = subCategory;
		this.programName = programName;
		this.program = program;
		this.maxCycles = -1;
		this.maxDeposits = -1;
	}
	
	  /************************/
	 /** METHODES STATIQUES **/
	/************************/
	
  public final static ArrayList<Program> getAll(){
  	ArrayList<Program> programs = new ArrayList<Program>();
  	
  	programs.add(loto);

	/** TUTORIALS **/
  	
  	programs.add(messageAPItutorial1);
  	programs.add(messageAPItutorial2);
  	programs.add(messageAPItutorial3);
  	
  	programs.add(exchangeAPItutorial1);
  	programs.add(exchangeAPItutorial2);
  	programs.add(exchangeAPItutorial3);
  	
  	programs.add(transportAPItutorial1);
  	programs.add(transportAPItutorial2);
  	programs.add(transportAPItutorial3);
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
	public String getSubCategory() {
		return subCategory;
	}
	public String getProgramName() {
		return programName;
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
			try {
				intro();
				cycle();
			} catch (StopProgramException e) {
				B4D.logger.debug(this, "Program stoped");
			}
			outro();
		} catch (CancelProgramException e) {
			B4D.logger.popUp(e.getMessage());
		}catch(B4DWrongPosition | AWTException | UnsupportedFlavorException | IOException | B4DCannotFind | TesseractException | InterruptedException | GeneralSecurityException e){
			B4D.logger.error(e);
		}
	}

	private void intro() throws B4DWrongPosition, AWTException, UnsupportedFlavorException, IOException, GeneralSecurityException, StopProgramException, CancelProgramException{
		if(this.category != Category.Test) {
			B4D.screen.focusDofus();
			Message.sendChat("/clear");
			
			if(B4D.screen.getPixelColor(new PointF(0.28, 0.99)).getGreen() > 200){	//Le mode solo n'est pas activé
	            B4D.mouse.leftClick(new PointF(0.28, 0.99), false);                	//Clic sur status
	            B4D.mouse.leftClick(new PointF(0.3, 0.976), false);                	//Clic sur solo
			}
			
			person.setPosition(Dofus.world.getPosition());	//Récupère la position actuelle
		}
		program.intro(person);
	}
	private void cycle() throws AWTException, B4DCannotFind, B4DWrongPosition, UnsupportedFlavorException, IOException, TesseractException, InterruptedException, StopProgramException, CancelProgramException{
		int nbCycles = 0, nbDeposits = 0;
		
		while(nbCycles != maxCycles && nbDeposits != maxDeposits) {
			try {
				program.cycle(person);
			} catch (FullInventoryException e) {

				if(hdvWhenFull) {
					//Mettre en HDV
				}
				if(bankWhenFull) {
					//Mettre en banque
				}
				if(stopWhenFull)
					throw new StopProgramException();
				
				nbDeposits++;
				
			}finally {
				nbCycles++;
			}
		}
	}
	private void outro() throws CancelProgramException {
		program.outro(person);
		//B4D.logger.popUp("Le bot s'est correctement terminé.");
	}
}
