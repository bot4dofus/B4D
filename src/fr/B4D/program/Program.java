package fr.B4D.program;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Configuration;
import fr.B4D.farming.Ressource;
import fr.B4D.farming.RessourceType;
import fr.B4D.modules.B4DChat;
import fr.B4D.modules.B4DMouse;
import fr.B4D.modules.B4DOther;
import fr.B4D.modules.B4DScreen;
import fr.B4D.programs.Deplacement;
import fr.B4D.threads.StopThread;
import fr.B4D.transport.B4DCannotFind;
import fr.B4D.transport.B4DWrongPosition;
import fr.B4D.utils.PointF;
import net.sourceforge.tess4j.TesseractException;

public class Program extends Thread implements Serializable{

	private static final long serialVersionUID = -4725926340583319625L;

	  /****************/
	 /** COLLECTION **/
	/****************/
	
	public final static Program deplacement = new Program(Place.Aucune, Category.Aucune, RessourceType.Aucun, Ressource.Aucune, Deplacement.deplacement);
	
	  /***************/
	 /** ATTRIBUTS **/
	/***************/
	
	private Place place;
	private Category category;
	private RessourceType ressourceType;
	private Ressource ressource;

	private ProgramInterface program;
	
	private int maxCycles;
	private int maxDeposits;

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

	  /**************/
	 /** METHODES **/
	/**************/
	
	public void run() {
		StopThread stopThread = new StopThread(this);	//Thread permettant de gérer le thread principal
		stopThread.start();								//Demarre le thread
		try {
			Intro();
			Tours();
			Outro();
			System.out.println("fin");
			//JOptionPane.showConfirmDialog(null, "Vous avez stoppé le bot.", "Fin", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
		}catch(B4DWrongPosition | AWTException | UnsupportedFlavorException | IOException | B4DCannotFind | TesseractException e){
			Outro();
			e.getStackTrace().toString();
			JOptionPane.showConfirmDialog(null, "Erreur : \n" + e.getMessage(), "Erreur", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
		}finally {
			stopThread.interrupt();	//Arrete le thread
		}
	}

	private void Intro() throws B4DWrongPosition, AWTException, UnsupportedFlavorException, IOException{
		
		B4DOther.focusDofus();
		B4DChat.sendChat("/clear");
		
		if(B4DScreen.getPixelColor(new PointF(0.28, 0.99)).getGreen() > 200){	//Le mode solo n'est pas activé
            B4DMouse.leftClick(new PointF(0.28, 0.99), false);                	//Clic sur status
            B4DMouse.leftClick(new PointF(0.3, 0.976), false);                	//Clic sur solo
		}

		Configuration.getInstance().persons.get(0).position = B4D.world.getPosition();	//Récupère la position actuelle
	}
	private void Tours() throws AWTException, B4DCannotFind, B4DWrongPosition, UnsupportedFlavorException, IOException, TesseractException{

		System.out.println("maxCycles ="+maxCycles);
		System.out.println("maxDeposits ="+maxDeposits);
		while(maxCycles != 0 || maxDeposits != 0) {
			try {
				program.run();
			} catch (B4DFullInventory e) {			
				if(Configuration.getInstance().hdvWhenFull) {
					//Mettre en HDV
				}
				if(Configuration.getInstance().bankWhenFull) {
					//Mettre en banque
				}
				if(Configuration.getInstance().stopWhenFull)
					break;
				
				maxDeposits = (maxDeposits>0 ? maxDeposits-1 : maxDeposits);	//Décrémente le nombre de depots si non infini
				
			}finally {
				maxCycles = (maxCycles>0 ? maxCycles-1 : maxCycles);			//Décrémente le nombre de cycles si non infini	
			}
		}
	}
	private void Outro() {
		//Nothing special for the moment
	}
}
