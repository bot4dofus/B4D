package fr.B4D.utils.os;

import java.io.IOException;
import java.util.List;

import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.platform.win32.WinDef.HWND;

import fr.B4D.bot.B4DException;
import fr.B4D.program.CancelProgramException;

/**
 * The {@code Windows} class gives access to useful methods relative to the Windows Operating System.
 * 
 * @author Lucas
 *
 */
public class Windows extends Os{

    /**
     * The {@code User32} interface represents the User32 library under Windows. This class provides useful native Windows methods.
     * @author Lucas
     *
     */
    public interface User32 extends W32APIOptions {
        /**
         * Instance of the User32 library.
         */
        User32 instance = (User32) Native.loadLibrary("user32", User32.class, DEFAULT_OPTIONS);
        
        /**
         * Shows a window.
         * @param hWnd - Window to show.
         * @param nCmdShow - Option.
         * @return {@code true} if it succeed, {@code false} otherwise.
         */
        boolean ShowWindow(HWND hWnd, int nCmdShow);
        
        /**
         * Set a window to foreground.
         * @param hWnd - Window to set foreground to.
         * @return {@code true} if it succeed, {@code false} otherwise.
         */
        boolean SetForegroundWindow(HWND hWnd);
        
        /**
         * Find a window from it's title.
         * @param winClass - Type of the window, {@code null} for any type.
         * @param title - Title of the window to find.
         * @return Native window, {@code null} if not found.
         */
        HWND FindWindow(String winClass, String title);
        
        /**
         * Option to show the window.
         */
        int SW_SHOW = 1;
    }
	
    public boolean setFocus(String title) {
    	User32 user32 = User32.instance;
        HWND hWnd = user32.FindWindow(null, title);
        if(hWnd == null) return false;
	    return user32.ShowWindow(hWnd, User32.SW_SHOW) && user32.SetForegroundWindow(hWnd);
    }
    
	public String findServerIp() throws B4DException, CancelProgramException {
		try {
			List<String> lines = Os.exec("cmd.exe", "/c", "netstat", "-a", "-p", "TCP", "-n", "|", "findstr", ":5555");				
			if(lines.size() == 0)
				throw new CancelProgramException("Coudn't find the server ip: The netstat command returned an empty result. Did you launch a Dofus session ?");
			if(lines.size() > 1)
				throw new CancelProgramException("Coudn't find the server ip: The netstat command returned more than one result. Did you launch a Dofus session ?");
			
			String[] worlds = lines.get(0).split(" ");
			String ipAndPort = null;
			
			for(String word:worlds) {
				if(!word.isEmpty() && word.contains(":5555") && !word.contains("127.0.0.1"))
					ipAndPort = word;
			}
			
			if(ipAndPort == null)
				throw new CancelProgramException("Couldn't find the server ip: ip is null. Did you launch a Dofus session ?");
			
			String[] address = ipAndPort.split(":");
			
			if(address.length != 2)
				throw new CancelProgramException("Couldn't find the server ip: server address is " + address.length + " long. Did you launch a Dofus session ?");
			
			return address[0];
		} catch (IOException e) {
			throw new B4DException("Coudn't find the server ip:", e);
		}
	}
}
