package fr.B4D.dao;

import java.io.IOException;
import java.io.Serializable;

public abstract class DAO<Type> implements Serializable{	

	private static final long serialVersionUID = -1678900024637018459L;
	
	public abstract Type find() throws IOException, ClassNotFoundException;
	protected abstract Type create() throws IOException, ClassNotFoundException;
	public abstract Type update(Type obj) throws IOException, ClassNotFoundException;
}
