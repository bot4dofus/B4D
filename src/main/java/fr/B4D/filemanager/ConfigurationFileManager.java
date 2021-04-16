package fr.B4D.filemanager;

import javax.swing.filechooser.FileNameExtensionFilter;

import fr.B4D.bot.Configuration;

/**
 * The {@code ConfigurationFileManager} class is used to manage the configuration file. This file contains all the product configuration.
 * 
 * @author Lucas
 *
 */
public class ConfigurationFileManager extends FileManager<Configuration>{

	/**
	 * Format of the file.
	 */
	public final static String FILE_FORMAT = "configuration.b4d";
	
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
	public final static Configuration DEFAULT_CONTENT = new Configuration();
	
	/**
	 * Constructor of the {@code ConfigurationFileManager} class.
	 */
	public ConfigurationFileManager() {
		super(FILE_NAME, DEFAULT_CONTENT);
	}
}
