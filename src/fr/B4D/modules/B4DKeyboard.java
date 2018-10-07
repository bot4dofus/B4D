package fr.B4D.modules;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import fr.B4D.classes.PointF;

public final class B4DKeyboard {

	  /***************/
	 /* ECRIRE CHAT */
	/***************/
	
	public static void writeChat(String texte, double time) throws AWTException {
		
		B4DMouse.leftClick(new PointF(0.05347166799, 0.98902195608), false, 0.5);
		writeKeyboard(texte,time);
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public static void writeChat(String texte) throws AWTException{
		B4DKeyboard.writeChat(texte, 0.5);
	}

	  /******************/
	 /* ECRIRE CLAVIER */
	/******************/
	
	public static void writeKeyboard(String texte, double time) throws AWTException {
		Clipboard clipboard = getSystemClipboard();
		Robot robot = new Robot();
		
		clipboard.setContents(new StringSelection(texte), null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);		
		B4DWait.wait(time);
	}
	public static void writeKeyboard(String texte) throws AWTException {
		writeKeyboard(texte, 0.5);
	}
	
	  /*****************/
	 /* PRESSE PAPIER */
	/*****************/
	
	public static void CopierPressePapier(String time) throws AWTException {
		Clipboard clipboard = getSystemClipboard();
		Robot robot = new Robot();
		
		clipboard.setContents(new StringSelection(time), null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
	}
	
	public static String RecupererPressePapier() throws AWTException, UnsupportedFlavorException, IOException{
        Clipboard systemClipboard = getSystemClipboard();
        DataFlavor dataFlavor = DataFlavor.stringFlavor;

        if (systemClipboard.isDataFlavorAvailable(dataFlavor)) 
			return (String) systemClipboard.getData(dataFlavor);
        
		return "";
	}
	
    private static Clipboard getSystemClipboard(){
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard systemClipboard = defaultToolkit.getSystemClipboard();
        return systemClipboard;
    }
}
