package fr.B4D.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.filechooser.FileNameExtensionFilter;

import fr.B4D.bot.Team;

public class TeamDAO extends DAO<Team> implements Serializable{

	private static final long serialVersionUID = -222219942703682998L;

	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private final static String format = "team.b4d";
	private final static String defaultName = "default";
    private final static String defaultFullName = defaultName + "." + format;
	private static File defaultFile = new File(defaultFullName);
	
    private static FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers de configuration " + format, format);
	
	  /*************/
	 /** METHODS **/
	/*************/
	
	@Override
	public Team find() throws IOException, ClassNotFoundException {
		if(defaultFile.exists())
			return deserialize(defaultFile);
		else
			return create();
	}

	@Override
	protected Team create() throws IOException, ClassNotFoundException {
		Team team = new Team();
		serialize(team, defaultFile);
		return team;
	}

	@Override
	public void update(Team team) throws IOException, ClassNotFoundException {
		serialize(team, defaultFile);
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
	public void serialize(Team team, File file) throws ClassNotFoundException, IOException {		  
			FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath());
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(team);
			out.close();
			fileOut.close();
	  }

	@Override
	public Team deserialize(File file) throws ClassNotFoundException, IOException {
			FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Team team = (Team) in.readObject();
			in.close();
			fileIn.close();
			return team;
	  }	
}
