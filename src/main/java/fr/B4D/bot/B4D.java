package fr.B4D.bot;

import java.awt.AWTException;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.B4D.bot.statics.Converter;
import fr.B4D.bot.statics.Keyboard;
import fr.B4D.bot.statics.KeyboardListener;
import fr.B4D.bot.statics.Logger;
import fr.B4D.bot.statics.Mouse;
import fr.B4D.bot.statics.Screen;
import fr.B4D.bot.statics.Wait;
import fr.B4D.filemanager.ConfigurationFileManager;
import fr.B4D.filemanager.FileManagerFactory;
import fr.B4D.filemanager.TeamFileManager;
import fr.B4D.interaction.Status;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramOptions;
import fr.B4D.socket.SocketListener;
import fr.B4D.utils.os.Os;

/**
 * The {@code B4D} class is the top level class of the bot.
 * It provides the methods used by the GUI.
 * 
 * @author Lucas
 *
 */
public final class B4D{
	
	/**
	 * Logger to display messages in the console.
	 */
	public static Logger logger;
	
	/**
	 * Converter to convert between mouse coordinates.
	 */
	public static Converter converter;
	
	/**
	 * Screen of the user to perform analysis on it.
	 */
	public static Screen screen;
	
	/**
	 * Mouse of the user to perform actions on it.
	 */
	public static Mouse mouse;
	
	/**
	 * Keyboard of the user to perform actions on it.
	 */
	public static Keyboard keyboard;
	
	/**
	 * Object to wait on.
	 */
	public static Wait wait;
	
	/**
	 * Configuration of the bot.
	 */
	private Configuration configuration;
	
	/**
	 * User team.
	 */
	private Team team;
	
	/**
	 * Socket listener. Listening for the incoming sockets.
	 */
	private SocketListener socketListener;
	
	/**
	 * Keyboard listener. Listening for the keys pressed.
	 */
	private KeyboardListener keyboardListener;
	

	/**
	 * Builder of the {@code B4D} class. <br>
	 * The configuration and teams info are automatically saved in files.
	 * @throws B4DException if an unknown operation occurs.
	 * @throws ClassNotFoundException if a serialization problem occurs.
	 * @throws IOException if cannot create the configuration files.
	 * @throws AWTException if the platform configuration does not allow low-level input control.
	 */
	public B4D() throws B4DException, ClassNotFoundException, IOException, AWTException {
		
		/** LOGGER **/
		logger = new Logger();
		
		/** DYNAMICS **/
		configuration = FileManagerFactory.getConfigurationFileManager().read();
		team = FileManagerFactory.getTeamFileManager().read();
		
		/** STATICS **/
		converter = new Converter(configuration);
		screen = new Screen(configuration);
		mouse = new Mouse(configuration);
		keyboard = new Keyboard();
		wait = new Wait();
		
		Channel.setChatMenuPosition(configuration.getChatMenu());
		Status.setStatusMenuPosition(configuration.getStatus());
	}
	
	/**
	 * Returns the current configuration of the game.
	 * @return Configuration of the game.
	 */
	public Configuration getConfiguration() {
		return configuration;
	}	
	
	/**
	 * Defines the current configuration of the game and save it in a file.
	 * @param configuration - New configuration of the game..
	 * @throws IOException if cannot save the files.
	 */
	private void setConfiguration(Configuration configuration) throws IOException {
		this.configuration = configuration;
		saveConfiguration();
	}
	
	/**
	 * Returns the team of the game.
	 * @return Team of the game.
	 */
	public Team getTeam() {
		return team;
	}
	
	/**
	 * Defines the team configuration and save it in a file.
	 * @param team - New team configuration.
	 * @throws IOException if cannot save the file.
	 */
	private void setTeam(Team team) throws IOException {
		this.team = team;
		saveTeam();
	}
	
	/**
	 * Saves the current configuration of the game in a file.
	 * @throws IOException if cannot save the files.
	 */
	public void saveConfiguration() throws IOException {
		FileManagerFactory.getConfigurationFileManager().write(configuration);
	}
	
	/**
	 * Saves the current team configuration in a file.
	 * @throws IOException if cannot save the files.
	 */
	public void saveTeam() throws IOException {
		FileManagerFactory.getTeamFileManager().write(team);
	}

	/**
	 * Imports a configuration from an existing file. <br>
	 * This method opens the file navigation system.
	 * @throws ClassNotFoundException if cannot deserialize.
	 * @throws IOException if cannot serialize.
	 * @throws B4DException 
	 */
	public void importFile() throws ClassNotFoundException, IOException, B4DException {
		FileNameExtensionFilter configurationFilter = ConfigurationFileManager.FILTER;
		FileNameExtensionFilter teamFilter = TeamFileManager.FILTER;

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));	  
		fileChooser.setFileFilter(configurationFilter);	  
		fileChooser.addChoosableFileFilter(teamFilter);

		if (fileChooser.showOpenDialog(new Frame()) == JFileChooser.APPROVE_OPTION) {			
			File file = fileChooser.getSelectedFile();
			if (configurationFilter.accept(file))
				setConfiguration(FileManagerFactory.getConfigurationFileManager().read());
			else if(teamFilter.accept(file))
				setTeam(FileManagerFactory.getTeamFileManager().read());
		}
	}

	/**
	 * Exports a configuration to a new file.
	 * @throws IOException if cannot serialize.
	 */
	public void exportFile() throws IOException {
		FileNameExtensionFilter configurationFilter = ConfigurationFileManager.FILTER;
		FileNameExtensionFilter teamFilter = TeamFileManager.FILTER;
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));	
		fileChooser.setFileFilter(configurationFilter);	  
		fileChooser.addChoosableFileFilter(teamFilter);
		
		if (fileChooser.showSaveDialog(new Frame()) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			if (configurationFilter.accept(file))
				FileManagerFactory.getConfigurationFileManager().write(configuration);		
			else if(teamFilter.accept(file))
				FileManagerFactory.getTeamFileManager().write(team);
		}
	}

	/**
	 * Returns all the programs.
	 * @return List containing all the programs.
	 */
	public ArrayList<Program> getPrograms(){
		return Program.getAll();
	}

	/**
	 * Runs a program with specific options.
	 * @param program - Program to run.
	 * @param person - Person which execute the program.
	 * @param programOptions - Program options.
	 */
	public void runProgram(Program program, Person person, ProgramOptions programOptions) {
		try {
			socketListener = new SocketListener();
			socketListener.start();
			
			keyboardListener = new KeyboardListener();
			keyboardListener.start();
		
			Os os = Os.findOs();
			String serverIp = os.findServerIp();
			socketListener.setFilter(serverIp);
			program.start(person, programOptions);
		}catch (CancelProgramException e) {
			if(e.getMessage() != null)
				B4D.logger.popUp(e.getMessage());
		} catch (B4DException e) {
			B4D.logger.error(e);
		}
		finally {
			if(socketListener != null && socketListener.isAlive())
				socketListener.interrupt();
			if(keyboardListener != null && keyboardListener.isAlive())
				keyboardListener.interrupt();
		}
	}
	
	/**
	 * Interrupts the socket listener and the keyboard listener. 
	 */
	public void interruptListeners() {
		if(socketListener != null && socketListener.isAlive())
			socketListener.interrupt();
		if(keyboardListener != null && keyboardListener.isAlive())
			keyboardListener.interrupt();
	}
}
