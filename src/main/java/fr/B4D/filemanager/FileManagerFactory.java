package fr.B4D.filemanager;

/**
 * This class is used to get the FileManagers.
 * 
 * @author Lucas
 */
public class FileManagerFactory {

	/**
	 * Returns a ConfigurationFileManager object.
	 * @return ConfigurationFileManager object.
	 */
	public static ConfigurationFileManager getConfigurationFileManager(){
		return new ConfigurationFileManager();
	}

	/**
	 * Returns a TeamFileManager object.
	 * @return TeamFileManager object.
	 */
	public static TeamFileManager getTeamFileManager(){
		return new TeamFileManager();
	} 

}
