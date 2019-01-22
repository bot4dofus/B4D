package fr.B4D.bot;

import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.B4D.dao.DAOFactory;
import fr.B4D.dofus.Dofus;
import fr.B4D.modules.B4DOther;
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

	private static Configuration configuration;
	private static Team team;
	
	private static SocketListener socketListener;
	private static KeyboardListener keyboardListener;

	public static Logger logger;    

	private Dofus dofus;
	
	/***********************/
	/** GETTERS & SETTERS **/
	/***********************/

	public B4D() throws ClassNotFoundException, IOException, CaptureDeviceLookupException, NoSocketDetectedException, CaptureDeviceOpenException, InvalidFilterException {
		logger = new Logger();

		configuration = DAOFactory.getConfigurationDAO().find();
		team = DAOFactory.getTeamDAO().find();
		
		socketListener = new SocketListener();
		keyboardListener = new KeyboardListener();
		
		dofus = new Dofus();
	}

	/***********************/
	/** GETTERS & SETTERS **/
	/***********************/

	public static Team getTeam() {
		return team;
	}
	
	public static Configuration getConfiguration() {
		return configuration;
	}

	public static SocketListener getSocketListener() {
		return socketListener;
	}

	public static KeyboardListener getKeyboardListener() {
		return keyboardListener;
	}

	/**********/
	/** SAVE **/
	/**********/
	
	public static void saveConfiguration() throws ClassNotFoundException, IOException {
		DAOFactory.getConfigurationDAO().update(configuration);
	}
	
	public static void saveTeam() throws ClassNotFoundException, IOException {
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
			if (configurationFilter.accept(file)) {
				configuration = DAOFactory.getConfigurationDAO().deserialize(file);
				saveConfiguration();
			}
			else {
				team = DAOFactory.getTeamDAO().deserialize(file);
				saveTeam();
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

	public void runProgram(Program program, Person person) throws InvalidFilterException {
		socketListener.setFilter(person.getServer());
		keyboardListener.startWith(program);			//Demarre le thread du clavier
		program.startWith(person);
		try {
			program.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		socketListener.interrupt();
		keyboardListener.interrupt();
	}
}
