package fr.B4D.program;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.dofus.Dofus;
import fr.B4D.interaction.Status;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.interaction.chat.Message;
import fr.B4D.programs.Breaking;
import fr.B4D.programs.Loto;
import fr.B4D.programs.PricesEvolution;
import fr.B4D.programs.Spam;
import fr.B4D.programs.tutorials.BankAPITutorial1;
import fr.B4D.programs.tutorials.BankAPITutorial2;
import fr.B4D.programs.tutorials.ConverterAPITutorial;
import fr.B4D.programs.tutorials.ExchangeAPITutorial;
import fr.B4D.programs.tutorials.HdvAPITutorial;
import fr.B4D.programs.tutorials.KeyboardAPITutorial;
import fr.B4D.programs.tutorials.LoggerAPITutorial;
import fr.B4D.programs.tutorials.MessageAPITutorial1;
import fr.B4D.programs.tutorials.MessageAPITutorial2;
import fr.B4D.programs.tutorials.MessageAPITutorial3;
import fr.B4D.programs.tutorials.MouseAPITutorial;
import fr.B4D.programs.tutorials.ScreenAPITutorial1;
import fr.B4D.programs.tutorials.ScreenAPITutorial2;
import fr.B4D.programs.tutorials.TransportAPITutorial1;
import fr.B4D.programs.tutorials.TransportAPITutorial2;

/** La classe {@code Program} représente un programme B4D.<br><br>
 * Un programme est défini par un lieux, une catégorie, une sous catégorie, un nom et une sub-routine implémentant l'interface {@code ProgramInterface}.
 * Il est possible de spécifier les canaux affichées dans le chat et le status du joueur.
 */
public abstract class Program {
	
	/**
	 * Path of the programs files such as google credentials and bundles.
	 */
	public static final String PROGRAM_FILE_PATH = "programs/";
	
	private Place place;
	private Category category;
	private String subCategory;
	private String programName;
	
	private List<Channel> displayedChannels;
	private Status status;

	/** Constructeur de la classe {@code Program}. 
	 * @param place - Lieu d'éxecution du programme.
	 * @param category - Catégorie du programme.
	 * @param subCategory - Sous catégorie du programme.
	 * @param programName - Nom du programme.
	 * @param displayedChannels - Canaux affichés pendant le programme. Si {@code null}, les canaux seront laissés par défaut.
	 * @param status - Status du joueur pendant le programme. Si {@code null}, le status sera laissé par défaut.
	 */
	public Program(Place place, Category category, String subCategory, String programName, Channel[] displayedChannels, Status status) {
		this.place = place;
		this.category = category;
		this.subCategory = subCategory;
		this.programName = programName;
		
		this.displayedChannels = (displayedChannels == null) ? null : Arrays.asList(displayedChannels);
		this.status = status;
	}
	
  /** Retourne la liste de tous les programmes disponibles.
 * @return Liste de tous les programmes.
 */
public final static ArrayList<Program> getAll(){
  	ArrayList<Program> programs = new ArrayList<Program>();
  	
  	programs.add(new Loto());
  	programs.add(new Breaking());
  	programs.add(new PricesEvolution());
  	programs.add(new Spam());

	/** TUTORIALS **/
  	
  	programs.add(new MessageAPITutorial1());
  	programs.add(new MessageAPITutorial2());
  	programs.add(new MessageAPITutorial3());
  	
  	programs.add(new ExchangeAPITutorial());
  	
  	programs.add(new TransportAPITutorial1());
  	programs.add(new TransportAPITutorial2());
  	
  	programs.add(new LoggerAPITutorial());
  	
  	programs.add(new ConverterAPITutorial());
  	
  	programs.add(new MouseAPITutorial());
  	
  	programs.add(new KeyboardAPITutorial());
  	
  	programs.add(new ScreenAPITutorial1());
  	programs.add(new ScreenAPITutorial2());
  	
  	programs.add(new BankAPITutorial1());
  	programs.add(new BankAPITutorial2());
  	
  	programs.add(new HdvAPITutorial());
  	
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
	
	/**
	 * Returns the google credentials associated with this program.
	 * @return Google credential file.
	 * @throws CancelProgramException if the credential file is missing
	 */
	public String getGoogleCredentials() throws CancelProgramException {
		String name = PROGRAM_FILE_PATH + programName.toLowerCase().replace(" ", "_").replace("-", "_").toLowerCase() + ".credentials";
		if(!new File(name).exists())
			throw new CancelProgramException("The file \"" + name + "\" is missing.");
		return name;
	}
	
	/**
	 * Returns the bundle file associated with this program.
	 * @return The bundle file of this program.
	 * @throws CancelProgramException if the bundle file is missing.
	 */
	public ResourceBundle getBundleFile() throws CancelProgramException {
		String name = PROGRAM_FILE_PATH + programName.toLowerCase().replace(" ", "_").replace("-", "_").toLowerCase();
		ResourceBundle bundle;
		try {
			File file = new File(".");
			URL[] urls = {file.toURI().toURL()};
			ClassLoader loader = new URLClassLoader(urls);
			bundle = ResourceBundle.getBundle(name, Locale.getDefault(), loader);
		}
		catch(MissingResourceException | MalformedURLException  e) {
			throw new CancelProgramException("The file \"" + name + ".properties\" is missing.");
		}
		return bundle;
	}
	
	/** Permet de lancer le programme.
	 * @param person - Personnage avec lequel lancer le programme.
	 * @param programOptions - Options de lancement du programme.
	 * @throws B4DException if a B4DException occurs.
	 * @throws CancelProgramException if the program has been canceled.
	 */
	public void start(Person person, ProgramOptions programOptions) throws B4DException, CancelProgramException {
		try {
			pre_intro(person, programOptions);
			loop(person, programOptions);
		} catch (StopProgramException e) {
			B4D.logger.debug("Program stoped");
		}
		pre_outro(person, programOptions);
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
	private void pre_intro(Person person, ProgramOptions programOptions) throws StopProgramException, CancelProgramException, B4DException {
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
		intro(person);
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
	private void loop(Person person, ProgramOptions programOptions) throws B4DException, StopProgramException, CancelProgramException {
		
		int cycles = programOptions.getCycles();
		long delay = programOptions.getDelay();
		
		while(cycles != 0) {
			try {
				cycle(person);
			} catch (FullInventoryException e) {

				if(programOptions.isHdvWhenFull()) {
					//Mettre en HDV
				}
				if(programOptions.isBankWhenFull()) {
					//Mettre en banque
				}
				if(programOptions.isStopWhenFull())
					throw new StopProgramException();
			}
			cycles--;
			B4D.wait.sleep(delay);
		}
	}
	
	/**Fonction de fin du programme. Celle-ci ne sera éxecutée qu'une seule fois en fin de programme.
	 * @param person - Personnage avec lequel lancer le programme.
	 * @param programOptions - Options de lancement du programme.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 * @throws B4DException Si une exception de type B4D est levée.
	 */
	private void pre_outro(Person person, ProgramOptions programOptions) throws CancelProgramException, B4DException {
		outro(person);
		B4D.logger.popUp("Le programme s'est correctement terminé.");
	}
	
	/** Fonction d'introduction du programme. Celle-ci ne sera éxecutée qu'une seule fois en début de programme.
	 * @param person - Personnage avec lequel lancer l'introduction.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 * @throws B4DException Si une exception de type B4D est levée.
	 */
	public abstract void intro(Person person) throws StopProgramException, CancelProgramException, B4DException;
	
	/** Fonction principale du programme. Celle-ci sera éxecutée plusieurs fois.
	 * @param person - Personnage avec lequel lancer le programme.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 * @throws B4DException Si une exception de type B4D est levée.
	 * @throws FullInventoryException Si l'inventaire est plein.
	 */
	public abstract void cycle(Person person) throws StopProgramException, CancelProgramException, FullInventoryException, B4DException;
	
	/** Fonction de fin du programme. Celle-ci ne sera éxecutée qu'une seule fois en fin de programme.
	 * @param person - Personnage avec lequel lancer la fonction de fin.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public abstract void outro(Person person) throws CancelProgramException;
}
