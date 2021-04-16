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
import fr.B4D.programs.tutorials.ExchangeAPITutorial1;
import fr.B4D.programs.tutorials.HdvAPITutorial1;
import fr.B4D.programs.tutorials.KeyboardAPITutorial;
import fr.B4D.programs.tutorials.LoggerAPITutorial;
import fr.B4D.programs.tutorials.MessageAPITutorial1;
import fr.B4D.programs.tutorials.MessageAPITutorial2;
import fr.B4D.programs.tutorials.MessageAPITutorial3;
import fr.B4D.programs.tutorials.MouseAPITutorial;
import fr.B4D.programs.tutorials.ScreenAPITutorial1;
import fr.B4D.programs.tutorials.ScreenAPITutorial2;
import fr.B4D.programs.tutorials.TransportAPITutorial1;
import fr.B4D.utils.os.Os;

/**
 * The {@code Program} class represents a B4D program.
 * <br><br>
 * A program is defines by a place, a category, a sub-categroy, a name, a list of channels and a status.
 * 
 * @author Lucas
 *
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

	/**
	 * Constructor of the {@code Program} class. 
	 * @param place - Place of the program.
	 * @param category - Category of the program.
	 * @param subCategory - Sub-category of the program.
	 * @param programName - Name of the program.
	 * @param displayedChannels - Channels displayed during the program, {@code null} to leave it as default.
	 * @param status - Status of the player during the program, {@code null}, to leave it as default.
	 */
	public Program(Place place, Category category, String subCategory, String programName, Channel[] displayedChannels, Status status) {
		this.place = place;
		this.category = category;
		this.subCategory = subCategory;
		this.programName = programName;
		
		this.displayedChannels = (displayedChannels == null) ? null : Arrays.asList(displayedChannels);
		this.status = status;
	}
	
  /**
   * Returns a list of all the existing B4D programs.
   * @return List of programs.
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
	  	
	  	programs.add(new ExchangeAPITutorial1());
	  	
	  	programs.add(new TransportAPITutorial1());
	  	
	  	programs.add(new LoggerAPITutorial());
	  	
	  	programs.add(new ConverterAPITutorial());
	  	
	  	programs.add(new MouseAPITutorial());
	  	
	  	programs.add(new KeyboardAPITutorial());
	  	
	  	programs.add(new ScreenAPITutorial1());
	  	programs.add(new ScreenAPITutorial2());
	  	
	  	programs.add(new BankAPITutorial1());
	  	programs.add(new BankAPITutorial2());
	  	
	  	programs.add(new HdvAPITutorial1());
	  	
	    return programs;
	  }
	
	/**
	 * Returns the place of the program.
	 * @return Place of the program.
	 */
	public Place getPlace() {
		return place;
	}
	
	/**
	 * Returns the category of the program.
	 * @return Category of the program.
	 */
	public Category getCategory() {
		return category;
	}
	
	/**
	 * Returns the sub-category of the program.
	 * @return Sub-category of the program.
	 */
	public String getSubCategory() {
		return subCategory;
	}
	
	/**
	 * Returns the name of the program.
	 * @return Name of the program.
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
	
	/**
	 * Starts the program.
	 * @param person - Person to start the program with.
	 * @param programOptions - Program start options.
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

	/**
	 * Pre-intro method.
	 * It will executed only once.
	 * <br>
	 * <ul>
	 *     <li>Clear the chat.</li>
	 *     <li>Modifies the displayed channels.</li>
	 *     <li>Modifies the player status.</li>
	 *     <li>Retrieve the current position of the player.</li>
	 *     <li>Call the intro method</li>
	 * </ul>
	 * @param person - Person to start the program with.
	 * @param programOptions - Program start options.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unknown exception occur.
	 */
	private void pre_intro(Person person, ProgramOptions programOptions) throws StopProgramException, CancelProgramException, B4DException {
		Dofus.getInstance().getChat().clear();
		if(this.category != Category.Test) {
			Os.findOs().setFocus(person.getPseudo());
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
	
	/** 
	 * Main method of a program.
	 * It will be executed once and is used to call the {@code cycle} method N times.
	 * @param person - Person to start the program with.
	 * @param programOptions - Program start options.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unknown exception occur.
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
	
	/**
	 * Pre-outro method.
	 * It will be called once at the end of the program.
	 * @param person - Person to start the program with.
	 * @param programOptions - Program start options.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unknown exception occur.
	 */
	private void pre_outro(Person person, ProgramOptions programOptions) throws CancelProgramException, B4DException {
		outro(person);
		B4D.logger.popUp("Le programme s'est correctement terminé.");
	}
	
	/**
	 * Intro method.
	 * It will be called once at the beginning of the program.
	 * Each program has its own implementation.
	 * @param person - Person to start the program with.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unknown exception occur.
	 */
	public abstract void intro(Person person) throws StopProgramException, CancelProgramException, B4DException;
	
	/** 
	 * Cycle method.
	 * It will be called N times.
	 * Each program has its own implementation.
	 * @param person - Person to start the program with.
	 * @throws StopProgramException if the program is stopped.
	 * @throws CancelProgramException if the program is canceled.
	 * @throws B4DException if an unknown exception occur.
	 * @throws FullInventoryException if the inventory is full.
	 */
	public abstract void cycle(Person person) throws StopProgramException, CancelProgramException, FullInventoryException, B4DException;
	
	/**
	 * Intro method.
	 * It will be called once at the end of the program.
	 * Each program has its own implementation.
	 * @param person - Person to start the program with.
	 * @throws CancelProgramException if the program is canceled.
	 */
	public abstract void outro(Person person) throws CancelProgramException;
}
