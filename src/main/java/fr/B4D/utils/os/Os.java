package fr.B4D.utils.os;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;

/**
 * The {@code Os} class gives access to useful methods relative to the Operating System.
 * 
 * @author Lucas
 * 
 */
public abstract class Os {
	
	/**
	 * Returns the current operating system.
	 * @return The current operating system.
	 * @throws B4DException if the operating system is unknown.
	 */
	public static Os findOs() throws B4DException {
		if(Os.isWindows())
			return new Windows();
		/*else if (Os.isLinux())
			return new Linux();*/
		else
			throw new B4DException("Unknown operating system: " + System.getProperty("os.name"));
	}
	
	/**
	 * Checks whether the operating system is windows or not.
	 * @return {@code true} if the operating system is Windows.
	 */
	public static Boolean isWindows() {
		return System.getProperty("os.name").contains("Windows");
	}
	
	/**
	 * Checks whether the operating system is linux or not.
	 * @return {@code true} if the operating system is linux.
	 */
	public static Boolean isLinux() {
		return System.getProperty("os.name").contains("Linux");
	}

	/**
	 * Execute a runtime process.
	 * @param command - Command to execute.
	 * @return The result of the command.
	 * @throws IOException If impossible to execute the command.
	 */
	public static List<String> exec(String...command) throws IOException{
		List<String> lines = new ArrayList<String>();
		Process process = new ProcessBuilder(command).start();
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String line;
        while ((line = input.readLine()) != null) {
        	lines.add(line);
        }
        return lines;
	}
	

    /**
     * Gives the focus to an external application.
     * @param title - Title of the application to give the focus.
     * @return {@code true} if the focus as been set, {@code false} if the window couldn't be found.
     */
    public abstract boolean setFocus(String title);
	
	/**
	 * Finds the Dofus server ip.
	 * @return Server ip.
	 * @throws B4DException if a B4DException occurs.
	 * @throws CancelProgramException if couldn't find the server ip. Probably no Dofus session has been launched.
	 */
	public abstract String findServerIp() throws B4DException, CancelProgramException;
}
