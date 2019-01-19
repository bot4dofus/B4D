package fr.B4D.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.gson.Gson;

import fr.B4D.bot.Configuration;

public class ConfigurationDAO extends DAO<Configuration> implements Serializable{

	private static final long serialVersionUID = -222219942703682998L;

	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private final static String configurationFormat = "B4D";
	private final static String defaultName = "Configuration";
    private final static String defaultFullName = defaultName + "." + configurationFormat;
	private static File defaultFile = new File(defaultFullName);
	
    private static FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers de configuration " + configurationFormat, configurationFormat);
	
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
			Gson gson = new Gson();
			out.writeObject(gson.toJson(configuration));
			out.close();
			fileOut.close();
	  }

	@Override
	public Configuration deserialize(File file) throws ClassNotFoundException, IOException {
			FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Gson gson = new Gson();
			Configuration configuration = gson.fromJson((String) in.readObject(), Configuration.class);
			in.close();
			fileIn.close();
			return configuration;
	  }	
}
