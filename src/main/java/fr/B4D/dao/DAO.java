package fr.B4D.dao;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.filechooser.FileNameExtensionFilter;

/** La classe {@code DAO<T>} est une classe abstraite.<br><br>
 * Elle permet pas de gérer la sauvegarde des instances de la classe générique.
 */
public abstract class DAO<T> implements Serializable{	

	private static final long serialVersionUID = -1678900024637018459L;
	
	/** Retourne le filtre de l'extension du fichier.
	 * @return Filtre du fichier correspondant à l'extension.
	 */
	public abstract FileNameExtensionFilter getFilter();
	
	/** Permet de récupérer la dernière instance de la classe.
	 * @return Instance de la classe 
	 * @throws IOException Si impossible de sérialiser.
	 * @throws ClassNotFoundException Si impossible de déserialiser.
	 */
	public abstract T find() throws IOException, ClassNotFoundException;
	
	/** Permet de créer une nouvelle sauvegarde de l'instance.
	 * @return Instance de l'objet.
	 * @throws IOException Si impossible de sérialiser.
	 */
	protected abstract T create() throws IOException;
	
	/** Permet de mettre à jour la sauvegarde de l'instance.
	 * @param obj - Nouvelle instance.
	 * @throws IOException Si impossible de sérialiser.
	 */
	public abstract void update(T obj) throws IOException;
	
	/** Permet de sérialiser l'instance d'un objet dans un fichier.
	 * @param object - Instance de l'objet à sérialiser.
	 * @param file - Fichier dans lequel sérialiser l'instance de l'objet.
	 * @throws IOException Si impossible de sérialiser.
	 */
	public abstract void serialize(T object, File file)  throws IOException;
	
	/** Permet de désérialiser l'instance d'un objet depuis un fichier.
	 * @param file - Fichier contenant l'instance de l'objet.
	 * @return Instance de l'objet déserialisée.
	 * @throws ClassNotFoundException Si impossible de déserialiser.
	 * @throws IOException Si un problème de fichier survient.
	 */
	public abstract T deserialize(File file)  throws IOException, ClassNotFoundException;
}
