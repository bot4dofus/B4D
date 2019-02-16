package fr.B4D.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import fr.B4D.bot.Configuration;

public class Serialization implements Serializable{

	private static final long serialVersionUID = 293051504653438854L;
	
	private File file;
	
	  public Serialization(File defaultFile) {
		this.file = defaultFile;
	}
	  
	  /*************************************/
	 /** SERIALISATION & DESERIALISATION **/
	/*************************************/
	
	public void serialize(Configuration object) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath());
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		Gson gson = new Gson();
		out.writeObject(gson.toJson(object));
		out.close();
		fileOut.close();
	}
	
	public Configuration deserialize() throws IOException, JsonSyntaxException, ClassNotFoundException{
		FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Gson gson = new Gson();
		Configuration object = gson.fromJson((String) in.readObject(), Configuration.class);
		in.close();
		fileIn.close();
		return object;
	}
	
}
