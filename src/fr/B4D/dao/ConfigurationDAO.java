package fr.B4D.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.filechooser.FileNameExtensionFilter;

import fr.B4D.bot.Configuration;

public class ConfigurationDAO extends DAO<Configuration> implements Serializable{

	private static final long serialVersionUID = -222219942703682998L;

	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private final static String format = "B4Dconfiguration";
	private final static String defaultName = "default";
    private final static String defaultFullName = defaultName + "." + format;
	private static File defaultFile = new File(defaultFullName);
	
    private static FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers de configuration " + format, format);
	
	  /*************/
	 /** METHODS **/
	/*************/
	
	@Override
	public Configuration find() throws IOException, ClassNotFoundException {
		if(defaultFile.exists())
			return deserialize(defaultFile);
		else
			return create();
	}

	@Override
	protected Configuration create() throws IOException, ClassNotFoundException {
		Configuration configuration = new Configuration();
		serialize(configuration, defaultFile);
		return configuration;
	}

	@Override
	public void update(Configuration confuguration) throws IOException, ClassNotFoundException {
		serialize(confuguration, defaultFile);
	}

	  /*************/
	 /** GETTERS **/
	/*************/

	@Override
	public FileNameExtensionFilter getFilter() {
		return filter;
	}
	
	  /*********************/
	 /** IMPORT & EXPORT **/
	/*********************/

	@Override
	public void serialize(Configuration configuration, File file) throws ClassNotFoundException, IOException {		  
			FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath());
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(configuration);
			out.close();
			fileOut.close();
	  }

	@Override
	public Configuration deserialize(File file) throws ClassNotFoundException, IOException {
			FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Configuration configuration = (Configuration) in.readObject();
			in.close();
			fileIn.close();
			return configuration;
	  }	
}
