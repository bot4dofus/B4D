package fr.B4D.bot;

import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.B4D.dao.DAOFactory;
import fr.B4D.dao.Serialization;
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
	
	private static final String configurationFormat = "B4D";	
    private static final String[] adminMacAdresses = {"24-0A-64-51-AB-D5", "AC-22-0B-14-51-EB"};		//Adresse mac de l'admin

    private boolean admin;
    private FileNameExtensionFilter filter;
    
    private static Configuration configuration;
    private static SocketListener socketListener;
    private static KeyboardListener keyboardListener;
    

	  /***********************/
	 /** GETTERS & SETTERS **/
	/***********************/
    
	public B4D() throws ClassNotFoundException, IOException, CaptureDeviceLookupException, NoSocketDetectedException, CaptureDeviceOpenException, InvalidFilterException {
		String currentMacAddress = B4DOther.getMacAddress();
		admin = (adminMacAdresses[0].equals(currentMacAddress) || adminMacAdresses[0].equals(currentMacAddress));
		
		configuration = DAOFactory.getConfigurationDAO().find();
		filter = new FileNameExtensionFilter("Fichiers " + configurationFormat, configurationFormat);
		
		socketListener = new SocketListener();
	}

	  /***********************/
	 /** GETTERS & SETTERS **/
	/***********************/
	
	public boolean isAdmin() {
		return admin;
	}

	public static Configuration getConfiguration() {
		return configuration;
	}
	
	public static Configuration setConfiguration(Configuration configuration) throws ClassNotFoundException, IOException {
		return B4D.configuration = DAOFactory.getConfigurationDAO().update(configuration);
	}
	
	public void saveConfiguration() throws ClassNotFoundException, IOException {
		setConfiguration(configuration);
	}

	public static SocketListener getSocketListener() {
		return socketListener;
	}

	public static KeyboardListener getKeyboardListener() {
		return keyboardListener;
	}

	  /*************/
	 /** METHODS **/
	/*************/

	public ArrayList<Program> getPrograms(){
		return Program.getAll();
	}
	
	public void runProgram(Program program) {
		KeyboardListener stopThread = new KeyboardListener(program);	//Thread permettant de gérer le programme (Pause, Lecture, Stop);
		stopThread.start();												//Demarre le thread
		program.start();
		try {
			program.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Dofus.chat.interrupt();
		stopThread.interrupt();
	}
	
	  public Configuration importConfiguration() throws ClassNotFoundException, IOException {		  
		  JFileChooser fileChooser = new JFileChooser();
		  fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));	  
		  fileChooser.setFileFilter(filter);
		  if (fileChooser.showOpenDialog(new Frame()) == JFileChooser.APPROVE_OPTION) {
			  File file = fileChooser.getSelectedFile();
			  Serialization serialization = new Serialization(file);
			  Configuration configuration = serialization.deserialize();
			  setConfiguration(configuration);
			  return configuration;
		  }
		  return null;
	  }
	  
	  public void exportConfiguration() throws ClassNotFoundException, IOException {
		  JFileChooser fileChooser = new JFileChooser();
		  fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));	  
		  fileChooser.setFileFilter(filter);
		  if (fileChooser.showSaveDialog(new Frame()) == JFileChooser.APPROVE_OPTION) {
			  File file = fileChooser.getSelectedFile();
			  Serialization serialization = new Serialization(file);
			  serialization.serialize(configuration);
		  }
	  }	
}
