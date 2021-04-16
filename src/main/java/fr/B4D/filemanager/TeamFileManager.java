package fr.B4D.filemanager;

import javax.swing.filechooser.FileNameExtensionFilter;

import fr.B4D.bot.Team;

/**
 * The {@code TeamFileManager} class is used to manage the wpa_supplicant file. This file contains networks ssid and passwords.
 * 
 * @author Lucas
 *
 */
public class TeamFileManager extends FileManager<Team>{

	/**
	 * Format of the file.
	 */
	public final static String FILE_FORMAT = "team.b4d";
	
	/**
	 * Relative path of the configuration file.
	 */
	public final static String FILE_NAME = "default." + FILE_FORMAT;
	
    /**
     * File filter for the explorer.
     */
    public final static FileNameExtensionFilter FILTER = new FileNameExtensionFilter("Fichiers de configuration " + FILE_FORMAT, FILE_FORMAT);
	
	/**
	 * Default content of the file if it doesn't exist.
	 * Used when the file doesn't exist or if is corrupted.
	 */
	public final static Team DEFAULT_CONTENT = new Team();
	
	/**
	 * Constructor of the {@code TeamFileManager} class.
	 */
	public TeamFileManager() {
		super(FILE_NAME, DEFAULT_CONTENT);
	}
}
