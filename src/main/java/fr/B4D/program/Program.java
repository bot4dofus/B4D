package fr.B4D.program;

import java.io.Serializable;
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
import fr.B4D.programs.Breaking;
import fr.B4D.programs.Loto;
import fr.B4D.programs.tutorials.BankAPI;
import fr.B4D.programs.tutorials.ConverterAPI;
import fr.B4D.programs.tutorials.ExchangeAPI;
import fr.B4D.programs.tutorials.HdvAPI;
import fr.B4D.programs.tutorials.KeyboardAPI;
import fr.B4D.programs.tutorials.LoggerAPI;
import fr.B4D.programs.tutorials.MessageAPI;
import fr.B4D.programs.tutorials.MouseAPI;
import fr.B4D.programs.tutorials.ScreenAPI;
import fr.B4D.programs.tutorials.TransportAPI;

/** La classe {@code Program} représente un programme B4D.<br><br>
 * Un programme est défini par un lieux, une catégorie, une sous catégorie, un nom et une sub-routine implémentant l'interface {@code ProgramInterface}.
 * Il est possible de spécifier les canaux affichées dans le chat et le status du joueur.
 */
public class Program {
	
	private Place place;
	private Category category;
	private String subCategory;
	private String programName;
	
	private List<Channel> displayedChannels;
	private Status status;

	private ProgramInterface program;

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
	}
	
  /** Retourne la liste de tous les programmes disponibles.
 * @return Liste de tous les programmes.
 */
public final static ArrayList<Program> getAll(){
  	ArrayList<Program> programs = new ArrayList<Program>();
  	
  	programs.add(Loto.LOTO);

	/** TUTORIALS **/
  	
  	programs.add(MessageAPI.TUTORIAL1);
  	programs.add(MessageAPI.TUTORIAL2);
  	programs.add(MessageAPI.TUTORIAL3);
  	
  	programs.add(ExchangeAPI.TUTORIAL1);
  	
  	programs.add(TransportAPI.TUTORIAL1);
  	programs.add(TransportAPI.TUTORIAL2);
  	
  	programs.add(LoggerAPI.TUTORIAL1);
  	
  	programs.add(ConverterAPI.TUTORIAL1);
  	
  	programs.add(MouseAPI.TUTORIAL1);
  	
  	programs.add(KeyboardAPI.TUTORIAL1);
  	
  	programs.add(ScreenAPI.TUTORIAL1);
  	programs.add(ScreenAPI.TUTORIAL2);
  	
  	programs.add(BankAPI.TUTORIAL1);
  	programs.add(BankAPI.TUTORIAL2);
  	
  	programs.add(HdvAPI.TUTORIAL1);
  	
  	programs.add(Breaking.BREAKING);
  	
    return programs;
  }
	
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
	
	/** Permet de lancer le programme.
	 * @param person - Personnage avec lequel lancer le programme.
	 * @param programOptions - Options de lancement du programme.
	 */
	public void start(Person person, ProgramOptions programOptions) {
		
		try {
			try {
				intro(person, programOptions);
				cycle(person, programOptions);
			} catch (StopProgramException e) {
				B4D.logger.debug("Program stoped");
			}
			outro(person, programOptions);
		} catch (CancelProgramException e) {
			if(e.getMessage() != null)
				B4D.logger.popUp(e.getMessage());
		}catch(B4DException e){
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
	 * @param person - Personnage avec lequel lancer le programme.
	 * @param programOptions - Options de lancement du programme.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 * @throws B4DException Si une exception de type B4D est levée.
	 */
	private void intro(Person person, ProgramOptions programOptions) throws StopProgramException, CancelProgramException, B4DException {
		Dofus.getInstance().getChat().clear();
		if(this.category != Category.Test) {
			B4D.screen.focusDofus();
			Message clear = new Message("/clear");
			clear.send();
			
			if(displayedChannels != null)
				Channel.displayChannels(person.getServer(), displayedChannels);
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
	 * @param person - Personnage avec lequel lancer le programme.
	 * @param programOptions - Options de lancement du programme.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 * @throws B4DException Si une exception de type B4D est levée.
	 */
	private void cycle(Person person, ProgramOptions programOptions) throws B4DException, StopProgramException, CancelProgramException {
		
		int cycles = programOptions.getCycles();
		int deposits = programOptions.getDeposits();
		
		while(cycles != 0 && deposits != 0) {
			try {
				program.cycle(person);
			} catch (FullInventoryException e) {

				if(programOptions.isHdvWhenFull()) {
					//Mettre en HDV
				}
				if(programOptions.isBankWhenFull()) {
					//Mettre en banque
				}
				if(programOptions.isStopWhenFull())
					throw new StopProgramException();
				
				deposits--;
			}
			cycles--;
		}
	}
	
	/**Fonction de fin du programme. Celle-ci ne sera éxecutée qu'une seule fois en fin de programme.
	 * @param person - Personnage avec lequel lancer le programme.
	 * @param programOptions - Options de lancement du programme.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 * @throws B4DException Si une exception de type B4D est levée.
	 */
	private void outro(Person person, ProgramOptions programOptions) throws CancelProgramException, B4DException {
		program.outro(person);
		B4D.logger.popUp("Le programme s'est correctement terminé.");
	}
}
