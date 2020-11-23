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
import fr.B4D.dao.DAOFactory;
import fr.B4D.interaction.Status;
import fr.B4D.interaction.chat.Channel;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramOptions;
import fr.B4D.socket.SocketListener;
import fr.B4D.utils.os.Os;

/** La classe {@code B4D} est la classe principale du bot.<br><br>
 * Elle fournit les méthodes dont à besoin l'interface graphique pour fonctionner.
 */
public final class B4D{

	/**************/
	/** ATRIBUTS **/
	/**************/
	
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
	

	/** Constructeur de la classe {@code B4D} et sauvegarde la nouvelle instance dans un fichier.
	 * @throws B4DException Si une exception B4D est levée.
	 * @throws ClassNotFoundException Si un problème de déserialisation survient.
	 * @throws IOException Si impossible de créer les fichiers de configuration.
	 * @throws AWTException Si la configuration de l'ordinateur ne permet pas l'automatisation des périphériques
	 */
	public B4D() throws B4DException, ClassNotFoundException, IOException, AWTException {
		
		/** LOGGER **/
		logger = new Logger();
		
		/** DYNAMICS **/
		configuration = DAOFactory.getConfigurationDAO().find();
		team = DAOFactory.getTeamDAO().find();
		
		/** STATICS **/
		converter = new Converter(configuration);
		screen = new Screen(configuration);
		mouse = new Mouse(configuration);
		keyboard = new Keyboard();
		wait = new Wait();
		
		Channel.setChatMenuPosition(configuration.getChatMenu());
		Status.setStatusMenuPosition(configuration.getStatus());
	}
	
	/** Retourne la configuration actuelle de la fenêtre de jeu.
	 * @return Configuration de la fenêtre de jeu.
	 */
	public Configuration getConfiguration() {
		return configuration;
	}	
	
	/** Modifi la configuration de la fenêtre de jeu.
	 * @param configuration - Nouvelle configuration de la fenêtre de jeu.
	 * @throws IOException Si impossible de sérialiser la configuration.
	 */
	private void setConfiguration(Configuration configuration) throws IOException {
		this.configuration = configuration;
		saveConfiguration();
	}
	
	/** Retourne la team du joueur.
	 * @return Team du joueur.
	 */
	public Team getTeam() {
		return team;
	}
	
	/** Modifi la team du joueur et sauvegarde la nouvelle instance dans un fichier.
	 * @param team - Nouvelle team du joueur.
	 * @throws IOException Si impossible de sérialiser la configuration.
	 */
	private void setTeam(Team team) throws IOException {
		this.team = team;
		saveTeam();
	}
	
	/** Sauvegarde l'instance actuelle de la configuration dans un fichier.
	 * @throws IOException Si impossible de sérialiser la configuration.
	 */
	public void saveConfiguration() throws IOException {
		DAOFactory.getConfigurationDAO().update(configuration);
	}
	
	/** Sauvegarde l'instance actuelle de la team dans un fichier.
	 * @throws IOException Si impossible de sérialiser la configuration.
	 */
	public void saveTeam() throws IOException {
		DAOFactory.getTeamDAO().update(team);
	}

	/** Permet d'importer une configuration ou une team.
	 * @throws ClassNotFoundException Si impossible de déserialiser.
	 * @throws IOException Si impossible de sérialiser.
	 */
	public void importFile() throws ClassNotFoundException, IOException {
		FileNameExtensionFilter configurationFilter = DAOFactory.getConfigurationDAO().getFilter();
		FileNameExtensionFilter teamFilter = DAOFactory.getTeamDAO().getFilter();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));	  
		fileChooser.setFileFilter(configurationFilter);	  
		fileChooser.addChoosableFileFilter(teamFilter);

		if (fileChooser.showOpenDialog(new Frame()) == JFileChooser.APPROVE_OPTION) {			
			File file = fileChooser.getSelectedFile();
			if (configurationFilter.accept(file))
				setConfiguration(DAOFactory.getConfigurationDAO().deserialize(file));
			else if(teamFilter.accept(file))
				setTeam(DAOFactory.getTeamDAO().deserialize(file));
		}
	}

	/** Permet d'exporter une configuration ou une team.
	 * @throws IOException Si impossible de sérialiser.
	 */
	public void exportFile() throws IOException {
		FileNameExtensionFilter configurationFilter = DAOFactory.getConfigurationDAO().getFilter();
		FileNameExtensionFilter teamFilter = DAOFactory.getTeamDAO().getFilter();
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));	
		fileChooser.setFileFilter(configurationFilter);	  
		fileChooser.addChoosableFileFilter(teamFilter);
		
		if (fileChooser.showSaveDialog(new Frame()) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			if (configurationFilter.accept(file))
				DAOFactory.getConfigurationDAO().serialize(configuration, file);		
			else if(teamFilter.accept(file))
				DAOFactory.getTeamDAO().serialize(team, file);
		}
	}

	/** Retourne la liste de tous les programmes.
	 * @return Liste de tous les programmes.
	 */
	public ArrayList<Program> getPrograms(){
		return Program.getAll();
	}

	/** Permet de lancer un programme avec une configuration et un personnage particulier.
	 * @param program - Programme à éxecuter.
	 * @param person - Personnage qui éxecute le programme.
	 * @param programOptions - Options de lancement du programme.
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
			
			socketListener.interrupt();
			keyboardListener.interrupt();
		}catch (CancelProgramException e) {
			if(e.getMessage() != null)
				B4D.logger.popUp(e.getMessage());
		} catch (B4DException e) {
			B4D.logger.error(e);
		}
		finally {
			socketListener.interrupt();
			keyboardListener.interrupt();
		}
	}
	
	/**
	 * Interrupt the socket listener and the keyboard listener. 
	 */
	public void interruptListeners() {
		if(socketListener != null && socketListener.isAlive())
			socketListener.interrupt();
		if(keyboardListener != null && keyboardListener.isAlive())
			keyboardListener.interrupt();
	}
}
