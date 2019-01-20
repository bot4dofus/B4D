package fr.B4D.dao;

import java.io.Serializable;

public class DAOFactory implements Serializable{
	
	private static final long serialVersionUID = -704835752059114530L;

	public static ConfigurationDAO getConfigurationDAO() {
		return new ConfigurationDAO();
	}
	public static TeamDAO getTeamDAO() {
		return new TeamDAO();
	}
}
