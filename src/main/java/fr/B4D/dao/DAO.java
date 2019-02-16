package fr.B4D.dao;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.filechooser.FileNameExtensionFilter;

public abstract class DAO<Type> implements Serializable{	

	private static final long serialVersionUID = -1678900024637018459L;
	
	public abstract Type find() throws IOException, ClassNotFoundException;
	protected abstract Type create() throws IOException, ClassNotFoundException;
	public abstract void update(Type obj) throws IOException, ClassNotFoundException;

	public abstract FileNameExtensionFilter getFilter();
	
	public abstract void serialize(Type object, File file)  throws ClassNotFoundException, IOException;
	public abstract Type deserialize(File file)  throws ClassNotFoundException, IOException;
}
