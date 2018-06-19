package fr.B4D.modules.autre;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D.Double;

public final class B4DClavier {

	  /***************/
	 /* ECRIRE CHAT */
	/***************/
	
	public static void Ecrire_Chat(String texte, double timeOut) {
		B4DSouris.Clic_Gauche(new Double(0.05347166799, 0.98902195608), false, 0.5);
		Ecrire_Clavier(texte,timeOut);
		//Enter
	}
	public static void Ecrire_Chat(String texte) {
		B4DClavier.Ecrire_Chat(texte, 0.5);
	}

	  /******************/
	 /* ECRIRE CLAVIER */
	/******************/
	
	public static void Ecrire_Clavier(String texte, double timeOut) {
		try {  
			Clipboard clipboard = getSystemClipboard();
			Robot robot = new Robot();
			
			clipboard.setContents(new StringSelection(texte), null);
			robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_V);
			
		} catch(Exception e){
			e.printStackTrace();			
		}
		B4DAttente.Attendre(timeOut);
	}
	public static void Ecrire_Clavier(String texte) {
		Ecrire_Clavier(texte, 0.5);
	}
	
	  /*****************/
	 /* PRESSE PAPIER */
	/*****************/
	
	public static void CopierPressePapier(String texte) {
		try {  
			Clipboard clipboard = getSystemClipboard();
			Robot robot = new Robot();
			
			clipboard.setContents(new StringSelection(texte), null);
			robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyRelease(KeyEvent.VK_V);
			
		} catch(Exception e){
			e.printStackTrace();			
		}
	}
	
	public static String RecupererPressePapier() {
        Clipboard systemClipboard = getSystemClipboard();
        DataFlavor dataFlavor = DataFlavor.stringFlavor;

        if (systemClipboard.isDataFlavorAvailable(dataFlavor)) {
			try {
				return (String) systemClipboard.getData(dataFlavor);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
        }
		else
        	return null;
	}
	
    private static Clipboard getSystemClipboard()
    {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard systemClipboard = defaultToolkit.getSystemClipboard();
        return systemClipboard;
    }
}
