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

/** La classe {@code ConfigurationDAO} permet de gérer la sauvegarde des instances de la classe {@code Configuration}.<br><br>
 * Cette classe étend la classe {@code DAO<Configuration>}.
 */
public class ConfigurationDAO extends DAO<Configuration> implements Serializable{

	private static final long serialVersionUID = -222219942703682998L;

	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private final static String format = "configuration.b4d";
	private final static String defaultName = "default";
    private final static String defaultFullName = defaultName + "." + format;
	private static File defaultFile = new File(defaultFullName);
	
    private static FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers de configuration " + format, format);
	
	  /*************/
	 /** GETTERS **/
	/*************/

	/* (non-Javadoc)
	 * @see fr.B4D.dao.DAO#getFilter()
	 */
	@Override
	public FileNameExtensionFilter getFilter() {
		return filter;
	}
    
	  /*************/
	 /** METHODS **/
	/*************/
	
	/* (non-Javadoc)
	 * @see fr.B4D.dao.DAO#find()
	 */
	@Override
	public Configuration find() throws IOException, ClassNotFoundException {
		if(defaultFile.exists())
			return deserialize(defaultFile);
		else
			return create();
	}

	/* (non-Javadoc)
	 * @see fr.B4D.dao.DAO#create()
	 */
	@Override
	protected Configuration create() throws IOException {
		Configuration configuration = new Configuration();
		serialize(configuration, defaultFile);
		return configuration;
	}

	/* (non-Javadoc)
	 * @see fr.B4D.dao.DAO#update(java.lang.Object)
	 */
	@Override
	public void update(Configuration confuguration) throws IOException {
		serialize(confuguration, defaultFile);
	}
	
	  /*********************/
	 /** IMPORT & EXPORT **/
	/*********************/

	/* (non-Javadoc)
	 * @see fr.B4D.dao.DAO#serialize(java.lang.Object, java.io.File)
	 */
	@Override
	public void serialize(Configuration configuration, File file) throws IOException {		  
			FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath());
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(configuration);
			out.close();
			fileOut.close();
	  }

	/* (non-Javadoc)
	 * @see fr.B4D.dao.DAO#deserialize(java.io.File)
	 */
	@Override
	public Configuration deserialize(File file) throws IOException, ClassNotFoundException {
			FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Configuration configuration = (Configuration) in.readObject();
			in.close();
			fileIn.close();
			return configuration;
	  }	
}
