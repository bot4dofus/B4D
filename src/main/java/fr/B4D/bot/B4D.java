package fr.B4D.bot;

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
import fr.B4D.dofus.Dofus;
import fr.B4D.program.Program;
import fr.B4D.socket.NoSocketDetectedException;
import fr.B4D.socket.SocketListener;
import net.sourceforge.jpcap.capture.CaptureDeviceLookupException;
import net.sourceforge.jpcap.capture.CaptureDeviceOpenException;
import net.sourceforge.jpcap.capture.InvalidFilterException;

public final class B4D{

	/**************/
	/** ATRIBUTS **/
	/**************/

	private Dofus dofus;
	
	public static Logger logger;
	public static SocketListener socketListener;
	public static KeyboardListener keyboardListener;
	public static Converter converter;
	public static Screen screen;
	public static Mouse mouse;
	public static Keyboard keyboard;
	public static Wait wait;
	
	private Configuration configuration;
	private Team team;
	
	  /*************/
	 /** BUILDER **/
	/*************/

	public B4D() throws ClassNotFoundException, IOException, CaptureDeviceLookupException, NoSocketDetectedException, CaptureDeviceOpenException, InvalidFilterException {
		
		System.out.println(System.getProperty("java.library.path"));
		/** LOGGER **/
		logger = new Logger();
		
		dofus = new Dofus();
		
		/** DYNAMICS **/
		configuration = DAOFactory.getConfigurationDAO().find();
		team = DAOFactory.getTeamDAO().find();
		
		/** STATICS **/
		socketListener = new SocketListener();
		keyboardListener = new KeyboardListener();
		converter = new Converter(configuration);
		screen = new Screen(configuration);
		mouse = new Mouse(configuration);
		keyboard = new Keyboard();
		wait = new Wait();
	}
	
	/***********************/
	/** GETTERS & SETTERS **/
	/***********************/
	
	public Configuration getConfiguration() {
		return configuration;
	}	
	private void setConfiguration(Configuration configuration) throws ClassNotFoundException, IOException {
		this.configuration = configuration;
		saveConfiguration();
	}
	public Team getTeam() {
		return team;
	}
	private void setTeam(Team team) throws ClassNotFoundException, IOException {
		this.team = team;
		saveTeam();
	}
	
	/**********/
	/** SAVE **/
	/**********/
	
	public void saveConfiguration() throws ClassNotFoundException, IOException {
		DAOFactory.getConfigurationDAO().update(configuration);
	}
	
	public void saveTeam() throws ClassNotFoundException, IOException {
		DAOFactory.getTeamDAO().update(team);
	}
	
	  /*************/
	 /** METHODS **/
	/*************/

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
			else {
				setTeam(DAOFactory.getTeamDAO().deserialize(file));
			}
		}
	}

	public void exportFile() throws ClassNotFoundException, IOException {
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
			else
				DAOFactory.getTeamDAO().serialize(team, file);
		}
	}

	public ArrayList<Program> getPrograms(){
		return Program.getAll();
	}

	/*********/
	/** RUN **/
	/*********/

	public void runProgram(Program program, Person person) {
		if(!socketListener.isAlive())
			socketListener.start();
		if(!keyboardListener.isAlive())
			keyboardListener.start();
		
		try {
			socketListener.setFilter(person.getServer());
			program.startWith(person);
		} catch (InvalidFilterException e) {
			B4D.logger.error(e);
		}
	}
}
