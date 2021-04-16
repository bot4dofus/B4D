package fr.B4D.filemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import fr.B4D.bot.B4D;
import fr.B4D.bot.B4DException;

/**
 * The {@code FileManager} class manages file in an easy way.
 * @author Lucas
 *
 * @param <T> Type of the data.
 */
public class FileManager<T> {

	private String file;
	private T defaultContent;
	
	/**
	 * Constructor of the {@code FileManager} class.
	 * @param file - Path of the file.
	 * @param defaultContent - Default content of the file.
	 */
	public FileManager(String file, T defaultContent) {
		this.file = file;
		this.defaultContent = defaultContent;
	}
	
	/**
	 * Returns the file name.
	 * @return Name of the file.
	 */
	public String getFile() {
		return file;
	}

	/**
	 * Returns the default content of the file.
	 * @return Default content of the file.
	 */
	public T getDefaultContent() {
		return defaultContent;
	}

	/**
	 * Reads the content of the file. Creates a new file with the default value if it doesn't exist or if the file is empty.
	 * @return Content of the file, line by line.
	 * @throws B4DException if cannot create the file.
	 */
	@SuppressWarnings("unchecked")
	public T read() throws B4DException{
		T content;
		try {
			File f = new File(file);
			FileInputStream fileIn = new FileInputStream(f.getAbsolutePath());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			content = (T) in.readObject();
			in.close();
			fileIn.close();
			
			if(content == null) {
				B4D.logger.warning("The file " + file + " is empty. Creating it with the default content.");
				write(defaultContent);
				content = defaultContent;
			}
		} catch (IOException | ClassNotFoundException e1) {
			try {
				B4D.logger.warning("The file " + file + " does not exists. Creating it with the default content.");
				write(defaultContent);
				content = defaultContent;
			} catch (IOException e2) {
				throw new B4DException(e2);
			}
		}
		return content;
	}
	
	/**
	 * Writes the new content of the file. Erase the previous content.
	 * @param object - Object to write in the file.
	 * @throws IOException if couldn't find or create the file.
	 */
	public void write(T object) throws IOException{
		File f = new File(file);
		FileOutputStream fileOut = new FileOutputStream(f.getAbsolutePath());
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(object);
		out.close();
		fileOut.close();
	}
}
