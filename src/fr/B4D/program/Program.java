package fr.B4D.program;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.B4D.bot.B4D;
import fr.B4D.bot.CannotGetPositionException;
import fr.B4D.bot.Person;
import fr.B4D.dofus.CannotFindException;
import fr.B4D.dofus.Dofus;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.Message;
import fr.B4D.programs.Loto;
import fr.B4D.programs.Test;
import fr.B4D.programs.tutorials.ExchangeAPI;
import fr.B4D.programs.tutorials.MessageAPI;
import fr.B4D.programs.tutorials.TransportAPI;
import fr.B4D.transport.WrongPositionException;
import net.sourceforge.tess4j.TesseractException;

public class Program implements Serializable{

	private static final long serialVersionUID = -4725926340583319625L;	
	
	  /***************/
	 /** ATTRIBUTS **/
	/***************/
	
	private Place place;
	private Category category;
	private String subCategory;
	private String programName;
	
	private List<Channel> displayedChannels;
	private Status status;

	private ProgramInterface program;
	
	private int maxCycles;
	private int maxDeposits;

	private boolean hdvWhenFull;
	private boolean bankWhenFull;
	private boolean stopWhenFull;

	private Person person;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public Program(Place place, Category category, String subCategory, String programName, Channel[] displayedChannels, Status status, ProgramInterface program) {
		this.place = place;
		this.category = category;
		this.subCategory = subCategory;
		this.programName = programName;
		
		if(displayedChannels == null)
			this.displayedChannels = null;
		else
			this.displayedChannels = Arrays.asList(displayedChannels);
		this.status = status;
		
		this.program = program;
		
		this.maxCycles = -1;
		this.maxDeposits = -1;
	}
	
	  /************************/
	 /** METHODES STATIQUES **/
	/************************/
	
  public final static ArrayList<Program> getAll(){
  	ArrayList<Program> programs = new ArrayList<Program>();
  	
  	programs.add(Test.TEST);
  	programs.add(Loto.LOTO);

	/** TUTORIALS **/
  	
  	programs.add(MessageAPI.TUTORIAL1);
  	programs.add(MessageAPI.TUTORIAL2);
  	programs.add(MessageAPI.TUTORIAL3);
  	
  	programs.add(ExchangeAPI.TUTORIAL1);
  	
  	programs.add(TransportAPI.TUTORIAL1);
  	programs.add(TransportAPI.TUTORIAL2);
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
		
		try {
			try {
				intro();
				cycle();
			} catch (StopProgramException e) {
				B4D.logger.debug(this, "Program stoped");
			}
			outro();
		} catch (CancelProgramException e) {
			if(e.getMessage() != null)
				B4D.logger.popUp(e.getMessage());
		}catch(WrongPositionException | CannotFindException | CannotGetPositionException | TesseractException | GeneralSecurityException  | AWTException | UnsupportedFlavorException | IOException e){
			B4D.logger.error(e);
		}
	}

	private void intro() throws WrongPositionException, AWTException, UnsupportedFlavorException, IOException, GeneralSecurityException, StopProgramException, CancelProgramException, CannotGetPositionException{
		Dofus.chat.clear();
		if(this.category != Category.Test) {
			B4D.screen.focusDofus();
			Message clear = new Message("/clear");
			clear.send();
			
			if(displayedChannels != null)
				Channel.displayChannels(displayedChannels);
			if(status != null)
				status.setStatus();
			
			person.setPosition();	//Récupère la position actuelle
		}
		program.intro(person);
	}
	private void cycle() throws AWTException, CannotFindException, WrongPositionException, UnsupportedFlavorException, IOException, TesseractException, StopProgramException, CancelProgramException{
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
		B4D.logger.popUp("Le bot s'est correctement terminé.");
	}
}
