package fr.B4D.classes;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

import fr.B4D.enu.Category;
import fr.B4D.enu.Place;
import fr.B4D.enu.Ressource;
import fr.B4D.enu.RessourceType;
import fr.B4D.exceptions.B4DFullInventory;
import fr.B4D.exceptions.B4DStopProgram;
import fr.B4D.exceptions.B4DWrongPosition;
import fr.B4D.modules.B4DKeyboard;
import fr.B4D.modules.B4DMouse;
import fr.B4D.modules.B4DOther;
import fr.B4D.modules.B4DScreen;
import fr.B4D.programs.Test;
import fr.B4D.threads.StopThread;

public class Program extends Thread{

	  /****************/
	 /** COLLECTION **/
	/****************/
	
	public final static Program test = new Program(Place.Aucune, Category.Aucune, RessourceType.Aucun, Ressource.Aucune, Test.test, 0, 0);
	
	  /***************/
	 /** ATTRIBUTS **/
	/***************/
	
	private Place place;
	private Category category;
	private RessourceType type;
	private Ressource ressource;

	private ProgramInterface program;
	
	private int maxCycles;
	private int maxDeposits;

	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/
	
	public Program(Place place, Category category, RessourceType type, Ressource ressource, ProgramInterface program, int maxCycles, int maxDeposits) {
		this.place = place;
		this.category = category;
		this.type = type;
		this.ressource = ressource;
		this.program = program;
		this.maxCycles = maxCycles;
		this.maxDeposits = maxDeposits;
	}
	
	  /************************/
	 /** METHODES STATIQUES **/
	/************************/
	
  public final static ArrayList<Program> getAll(){
  	ArrayList<Program> programs = new ArrayList<Program>();
  	programs.add(test);
    return programs;
  }
      
  public final static Program getProgram(int index){
	  return getAll().get(index);
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
	public RessourceType getType() {
		return type;
	}
	public Ressource getRessource() {
		return ressource;
	}
	
	  /**************/
	 /** METHODES **/
	/**************/
	
	public void run() {
		StopThread stopThread = new StopThread(this);	//Thread permettant de gérer le thread principal
		stopThread.start();								//Demarre le thread
		try {
			Intro();
			Tours();
		}catch(B4DWrongPosition | AWTException | UnsupportedFlavorException | IOException e){
			Outro();
			e.getStackTrace().toString();
			//Message mal fini
		}catch(B4DStopProgram e){
			Outro();
			//Message correctement fini
		}
		stopThread.interrupt();							//Arrete le thread
	}

	private void Intro() throws B4DWrongPosition, AWTException, UnsupportedFlavorException, IOException{
		
		B4DOther.focusDofus();
		B4DKeyboard.writeChat("/clear");
		
		if(B4DScreen.getPixelColor(new PointF(0.28, 0.99)).getGreen() > 200){	//Le mode solo n'est pas activé
            B4DMouse.leftClick(new PointF(0.28, 0.99), false);                	//Clic sur status
            B4DMouse.leftClick(new PointF(0.3, 0.976), false);                	//Clic sur solo
		}

		Bot.configuration.persons.get(0).position = Bot.world.getPosition();	//Ma position théorique devient la position réelle
	}
	private void Tours() throws B4DStopProgram{
		while(maxCycles != 0 && maxDeposits != 0) {
			try {
				program.run();
			} catch (B4DFullInventory e) {			
				
				if(Bot.configuration.hdvWhenFull) {
					//Mettre en HDV
				}
				if(Bot.configuration.bankWhenFull) {
					//Mettre en banque
				}
				if(Bot.configuration.stopWhenFull)
					throw new B4DStopProgram();
				
				maxDeposits = (maxDeposits>0 ? maxDeposits-1 : maxDeposits);	//Décrémente le nombre de depots si non infini
				
			}finally {
				maxCycles = (maxCycles>0 ? maxCycles-1 : maxCycles);			//Décrémente le nombre de cycles si non infini	
			}
		}
		throw new B4DStopProgram();
	}
	private void Outro() {
		B4DOther.focusBot();
	}
}
