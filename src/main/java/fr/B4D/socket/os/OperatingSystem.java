package fr.B4D.socket.os;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.B4D.bot.B4DException;

/** La classe {@code OperatingSystem} représente un système d'exploitation.<br><br>
 * Un système d'exploitation est définit par un nom et une librairie jpcap.
 */
public abstract class OperatingSystem {
	
	private static final OperatingSystem WINDOWS = new Windows();
	private static final OperatingSystem LINUX = new Linux();
	
	private String name;
	private String library;
	
	/** Constructeur de la classe {@code OperatingSystem}.
	 * @param name - Nom du système d'exploitation.
	 * @param library - Nom de la librairie jpcap utilisée.
	 */
	public OperatingSystem(String name, String library) {
		String paths = System.getProperty("java.library.path");
		if(!Arrays.asList(paths.split(":")).contains("."))
			System.setProperty("java.library.path", paths + ":.");
		
		this.name = name;
		this.library = library;
	}
	
	/** Retourne le nom du système d'exploitation.
	 * @return Nom du système d'exploitation.
	 */
	public String getName() {
		return name;
	}
	
	/** Retourne le nom de la librairie jpcap correspondant au système d'exploitation.
	 * @return Nom de la librairie jpcap.
	 */
	public String getLibrary() {
		return library;
	}
	
	/** Test si la librairie jpcap existe.
	 * @return {@code true} si la librairie existe, {@code false} sinon.
	 */
	public boolean libraryExists() {
		return new File(library).exists();
	}
	
	/** Permet de récupérer le réseau actif.
	 * @return Nom du réseau utilisé.
	 * @throws B4DException Si aucuns des réseaux n'est actif.
	 */
	public abstract String findActiveDevice() throws B4DException;
	
	/** Retourne le système d'exploitation utilisé.
	 * @return Système d'exploitation utilisé.
	 * @throws B4DException Si le système d'exploitation est inconnu.
	 */
	public static OperatingSystem getCurrent() throws B4DException {
		String OS = System.getProperty("os.name");
		switch(OS) {
			case "Linux":
				return LINUX;
			case "Windows 7":
			case "Windows 8":
			case "Windows 8.1":
			case "Windows 10":
				return WINDOWS;
			default:
				throw new B4DException("Unknow operating system : " + OS);
		}
	}
	
	/** Permet d'exécuter une commande dans le terminal.
	 * @param command - Commande à exécuter.
	 * @return Résultat de la commande
	 */
	public static List<String> exec(String command) {
		List<String> lines = new ArrayList<String>();
		try {
			Process proc = Runtime.getRuntime().exec(command);
	        BufferedReader reader =  new BufferedReader(new InputStreamReader(proc.getInputStream()));
	        String line = "";
	        while((line = reader.readLine()) != null)
	        	lines.add(line);
	        proc.waitFor();
		} catch (IOException | InterruptedException e) {
			//Do nothing
		}
        return lines;
	}
}
