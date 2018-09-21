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

	File file;
	FileNameExtensionFilter filter;
	
	  public Serialization(String format, String path) {
		filter = new FileNameExtensionFilter("Fichiers de configuration B4D", format);
		this.file = new File(path);
		try {
			file.createNewFile();
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
		  if (fileChooser.showOpenDialog(new Frame()) == JFileChooser.APPROVE_OPTION) {
		      file = fileChooser.getSelectedFile();
		      return Deserialize();
		  }		  
		  return null;
	  }
	  
	  /************************************/
	 /** ENREGISTRER & ENREGISTRER_SOUS **/
	/************************************/
	  
	  public void Save(Configuration object) throws IOException, ClassNotFoundException {
		  if(file.exists())
			  Serialize(object);
		  else
			  SaveAs(object);
	  }
	  
	  public void SaveAs(Configuration object) throws ClassNotFoundException, IOException {
		  JFileChooser fileChooser = new JFileChooser();
		  fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));	  
		  fileChooser.setFileFilter(filter);
		  if (fileChooser.showSaveDialog(new Frame()) == JFileChooser.APPROVE_OPTION) {
		     file = fileChooser.getSelectedFile();
		     Serialize(object);
		  }
	  }
	  
	  /*************************************/
	 /** SERIALISATION & DESERIALISATION **/
	/*************************************/
	
	private void Serialize(Configuration object) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(file.getAbsolutePath());
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		Gson gson = new Gson();
		out.writeObject(gson.toJson(object));
		out.close();
		fileOut.close();
	}
	
	private Configuration Deserialize() throws IOException, ClassNotFoundException{
		FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Gson gson = new Gson();
		Configuration object = gson.fromJson((String) in.readObject(), Configuration.class);
		in.close();
		fileIn.close();
		return object;
	}
	
}
