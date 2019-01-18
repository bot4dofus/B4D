package fr.B4D.dao;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import fr.B4D.bot.Configuration;

public class ConfigurationDAO extends DAO<Configuration> implements Serializable{

	private static final long serialVersionUID = -222219942703682998L;
	
	private String confPath = "Configuration.B4D";
	private File confFile = new File(confPath);
	
	@Override
	public Configuration find() throws IOException, ClassNotFoundException {
		if(confFile.exists()) {
			Serialization confSerialization = new Serialization(confFile);
			return Configuration.setInstance(confSerialization.deserialize());
		}
		else
			return create();
	}

	@Override
	public Configuration create() throws IOException, ClassNotFoundException {
		Configuration configuration = Configuration.getInstance();
		Serialization confSerialization = new Serialization(confFile);
		confSerialization.serialize(configuration);
		return Configuration.setInstance(confSerialization.deserialize());
	}

	@Override
	public Configuration update(Configuration conf) throws IOException, ClassNotFoundException {
		Serialization confSerialization = new Serialization(confFile);
		confSerialization.serialize(conf);
		return confSerialization.deserialize();
	}
}
