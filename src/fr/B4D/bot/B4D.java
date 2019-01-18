package fr.B4D.bot;

import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import fr.B4D.dao.DAOFactory;
import fr.B4D.dao.Serialization;
import fr.B4D.modules.B4DOther;
import fr.B4D.program.Program;
import fr.B4D.transport.World;

public final class B4D{
	private static final String configurationFormat = "B4D";	
    private static final String[] adminMacAdresses = {"24-0A-64-51-AB-D5", "AC-22-0B-14-51-EB"};		//Adresse mac de l'admin
	@Deprecated
    public static final World world = new World();                          //Créer la variable de type Monde
    
    private boolean admin;
    private static Configuration configuration;
    private FileNameExtensionFilter filter;
    
	public B4D() throws ClassNotFoundException, IOException {
		configuration = DAOFactory.getConfigurationDAO().find();
		filter = new FileNameExtensionFilter("Fichiers " + configurationFormat, configurationFormat);

		String currentMacAddress = B4DOther.getMacAddress();
		admin = (adminMacAdresses[0].equals(currentMacAddress) || adminMacAdresses[0].equals(currentMacAddress));
	}

	//================================
	
	public boolean isAdmin() {
		return admin;
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) throws ClassNotFoundException, IOException {
		B4D.configuration = DAOFactory.getConfigurationDAO().update(configuration);
	}
	
	public void saveConfiguration() throws ClassNotFoundException, IOException {
		setConfiguration(configuration);
	}

	//================================
	
	public ArrayList<Program> getPrograms(){
		return Program.getAll();
	}
	
	public void runProgram(Program progam) {
		
	}

	//================================
	
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
