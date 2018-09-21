package fr.B4D.classes;

import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.google.gson.Gson;

public class Serialization {

	File defaultFile;
	FileNameExtensionFilter filter;
	
	  public Serialization(String format, String defaultPath) {
		filter = new FileNameExtensionFilter("Fichiers de configuration B4D", format);
		defaultFile = new File(defaultPath);
		try {
			defaultFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	  /************/
	 /** OUVRIR **/
	/************/
	  
	  public Configuration Open() throws ClassNotFoundException, IOException {		  
		  JFileChooser fileChooser = new JFileChooser();
		  fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));	  
		  fileChooser.setFileFilter(filter);
		  if (fileChooser.showOpenDialog(new Frame()) == JFileChooser.APPROVE_OPTION) 
		      return Deserialize(fileChooser.getSelectedFile()); 
		  return null;
	  }
	  
	  /************************************/
	 /** ENREGISTRER & ENREGISTRER_SOUS **/
	/************************************/
	  
	  public void Save(Configuration object) throws IOException, ClassNotFoundException {
		  if(defaultFile.exists())
			  Serialize(object, defaultFile);
		  else
			  SaveAs(object);
	  }
	  
	  public void SaveAs(Configuration object) throws ClassNotFoundException, IOException {
		  JFileChooser fileChooser = new JFileChooser();
		  fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));	  
		  fileChooser.setFileFilter(filter);
		  if (fileChooser.showSaveDialog(new Frame()) == JFileChooser.APPROVE_OPTION) {
		     Serialize(object, fileChooser.getSelectedFile());
		  }
	  }
	  
	  /*************************************/
	 /** SERIALISATION & DESERIALISATION **/
	/*************************************/
	
	private void Serialize(Configuration object, File file) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath());
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		Gson gson = new Gson();
		out.writeObject(gson.toJson(object));
		out.close();
		fileOut.close();
	}
	
	private Configuration Deserialize(File file) throws IOException, ClassNotFoundException{
		FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Gson gson = new Gson();
		Configuration object = gson.fromJson((String) in.readObject(), Configuration.class);
		in.close();
		fileIn.close();
		return object;
	}
	
}
