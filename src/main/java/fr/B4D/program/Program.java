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
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.dofus.Dofus;
import fr.B4D.interaction.Status;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.Message;
import fr.B4D.programs.Loto;
import fr.B4D.programs.Test;
import fr.B4D.programs.tutorials.ExchangeAPI;
import fr.B4D.programs.tutorials.MessageAPI;
import fr.B4D.programs.tutorials.TransportAPI;
import net.sourceforge.tess4j.TesseractException;

/** La classe {@code Program} représente un programme B4D.<br><br>
 * Un programme est défini par un lieux, une catégorie, une sous catégorie, un nom et une sub-routine implémentant l'interface {@code ProgramInterface}.
 * Il est possible de spécifier les canaux affichées dans le chat et le status du joueur.
 */
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
	
	private int cycles;
	private int deposits;

	private boolean hdvWhenFull;
	private boolean bankWhenFull;
	private boolean stopWhenFull;

	private Person person;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/

	/** Constructeur de la classe {@code Program}. 
	 * @param place - Lieu d'éxecution du programme.
	 * @param category - Catégorie du programme.
	 * @param subCategory - Sous catégorie du programme.
	 * @param programName - Nom du programme.
	 * @param displayedChannels - Canaux affichés pendant le programme. Si {@code null}, les canaux seront laissés par défaut.
	 * @param status - Status du joueur pendant le programme. Si {@code null}, le status sera laissé par défaut.
	 * @param program - Sub-routine du programme.
	 */
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
		
		this.cycles = -1;
		this.deposits = -1;
	}
	
	  /************************/
	 /** METHODES STATIQUES **/
	/************************/
	
  /** Retourne la liste de tous les programmes disponibles.
 * @return Liste de tous les programmes.
 */
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
	
	/** Retourne le lieu d'éxecution du programme.
	 * @return Lieux d'éxecution du programme.
	 */
	public Place getPlace() {
		return place;
	}
	
	/** Retourne la catégorie du programme.
	 * @return Catégorie du programme.
	 */
	public Category getCategory() {
		return category;
	}
	
	/** Retourne la sous catégorie du programme.
	 * @return Sous ctégorie du programme.
	 */
	public String getSubCategory() {
		return subCategory;
	}
	
	/** Retourne le nom du programme.
	 * @return Nom du programme.
	 */
	public String getProgramName() {
		return programName;
	}
	
	/** Retourne le nombre de cycles avant la fin du programme.
	 * @return Nombre de cycles restants.
	 */
	public int getCycles() {
		return cycles;
	}
	
	/** Modifi le nombre de cycles avant la fin du programme.
	 * @param cycles - Nombre de cycles avant fin du programme.
	 */
	public void setCycles(int cycles) {
		this.cycles = cycles;
	}
	
	/** Retourne le nombre de dépôts avant la fin du programme.
	 * @return Nombre de dépôts restants.
	 */
	public int getDeposits() {
		return deposits;
	}
	
	/** Modifi le nombre de dépôts avant la fin du programme.
	 * @param deposits - Nombre de dépôts avant fin du programme.
	 */
	public void setDeposits(int deposits) {
		this.deposits = deposits;
	}
	
	/** Retourne un booléen représentant si le joueur veut vider son inventaire en HDV lorsque celui-ci est plein.
	 * @return {@code true} si le joueur veut vider son inventaire en HDV lorsque celui-ci est plein, {@code false} sinon.
	 */
	public boolean isHdvWhenFull() {
		return hdvWhenFull;
	}
	
	/** Présice si le joueur veut vider son inventaire en HDV lorsque celui-ci est plein.
	 * Cela permet par exemple, de vendre automatiquement les ressources que l'on vient de récupérer. 
	 * @param hdvWhenFull - {@code true} si le joueur veut vider son inventaire en HDV lorsque celui-ci est plein, {@code false} sinon.
	 */
	public void setHdvWhenFull(boolean hdvWhenFull) {
		this.hdvWhenFull = hdvWhenFull;
	}
	
	/** Retourne un booléen représentant si le joueur veut vider son inventaire en banque lorsque celui-ci est plein.
	 * @return {@code true} si le joueur veut vider son inventaire en banque lorsque celui-ci est plein, {@code false} sinon.
	 */
	public boolean isBankWhenFull() {
		return bankWhenFull;
	}
	
	/** Présice si le joueur veut vider son inventaire en banque lorsque celui-ci est plein.
	 * Cela permet par exemple, de stocker automatiquement les ressources que l'on vient de récupérer. 
	 * @param bankWhenFull - {@code true} si le joueur veut vider son inventaire en banque lorsque celui-ci est plein, {@code false} sinon.
	 */
	public void setBankWhenFull(boolean bankWhenFull) {
		this.bankWhenFull = bankWhenFull;
	}
	
	/** Retourne un booléen représentant si le joueur veut stopper le programme lorsque l'inventaire est plein.
	 * @return {@code true} si le joueur veut stopper le programme lorsque l'inventaire est plein, {@code false} sinon.
	 */
	public boolean isStopWhenFull() {
		return stopWhenFull;
	}
	
	/** Précise si le joueur veut stopper le programme lorsque son inventaire est plein.
	 * @param stopWhenFull - {@code true} si le joueur veut stopper le programme lorsque l'inventaire est plein, {@code false} sinon.
	 */
	public void setStopWhenFull(boolean stopWhenFull) {
		this.stopWhenFull = stopWhenFull;
	}

	  /**************/
	 /** METHODES **/
	/**************/
	
	/** Permet de lancer le programme.
	 * @param person - Personnage avec lequel lancer le programme.
	 */
	public void start(Person person) {
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
		}catch(B4DException | TesseractException | GeneralSecurityException  | AWTException | UnsupportedFlavorException | IOException e){
			B4D.logger.error(e);
		}
	}

	/** Fonction d'introduction du programme. Celle-ci ne sera éxecutée qu'une seule fois et permet de :<br/>
	 * <ul>
	 * <li>Effacer le chat.</li>
	 * <li>Modifier les canaux affichés dans le chat.</li>
	 * <li>Modifier le status du joueur.</li>
	 * <li>Récupérer la position actuelle du joueur.</li>
	 * <li>Exécuter la sub-routine d'intro du programme.</li>
	 * </ul>
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 * @throws B4DException Si une exception de type B4D est levée.
	 * @throws AWTException Si un problème de souris ou clavier survient.
	 * @throws IOException Si un problème de fichier survient.
     * @throws GeneralSecurityException Si problèmes de sécurité survient.
	 */
	private void intro() throws StopProgramException, CancelProgramException, B4DException, AWTException, IOException, GeneralSecurityException {
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
	
	/** Fonction principale du programme. Celle-ci ne sera éxecutée qu'une seule fois et permet d'exécuter la sub-routine cycle du programme.
	 * Celle-ci sera exécuté tant que le nombre de cycles et le nombre de dépôts seront atteint.
	 * En fonction des paramètres de lancement du programme, si l'inventaire est plein, les items sont automatiquement mit en HDV ou en banque.
	 * Le programme peut aussi être stoppé.
	 * 
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 * @throws B4DException Si une exception de type B4D est levée.
	 * @throws AWTException Si un problème de souris ou clavier survient.
	 * @throws IOException Si un problème de fichier survient.
	 * @throws TesseractException Si une exception Tesseract est levée.
	 * @throws UnsupportedFlavorException
	 */
	private void cycle() throws B4DException, StopProgramException, CancelProgramException, AWTException, UnsupportedFlavorException, IOException, TesseractException{
		
		while(cycles != 0 && deposits != 0) {
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
				
				deposits--;
			}
			cycles--;
		}
	}
	private void outro() throws CancelProgramException {
		program.outro(person);
		B4D.logger.popUp("Le bot s'est correctement terminé.");
	}
}
