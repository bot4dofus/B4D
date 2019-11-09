package fr.B4D.dao;

import java.io.Serializable;

/** La classe {@code DAOFactory} permet de récupérer les objets dao permettant de gérer la sauvegarde des instances d'une classe.
 */
public class DAOFactory implements Serializable{
	
	private static final long serialVersionUID = -5379040750762550000L;

	/** Retourne une instance de la classe {@code ConfigurationDAO}.
	 * @return Objet de configuration dao.
	 */
	public static ConfigurationDAO getConfigurationDAO() {
		return new ConfigurationDAO();
	}
	
	/** Retourne une instance de la classe {@code TeamDAO}.
	 * @return Objet de team dao.
	 */
	public static TeamDAO getTeamDAO() {
		return new TeamDAO();
	}
}
