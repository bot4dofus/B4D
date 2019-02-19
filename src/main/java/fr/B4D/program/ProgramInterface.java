package fr.B4D.program;

import java.awt.AWTException;
import java.io.IOException;
import java.security.GeneralSecurityException;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import fr.B4D.dofus.CannotFindException;
import net.sourceforge.tess4j.TesseractException;

public interface ProgramInterface{
	
	/** Fonction d'introduction du programme. Celle-ci ne sera éxecutée qu'une seule fois en début de programme.
	 * @param person - Personnage avec lequel lancer l'introduction.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 * @throws B4DException Si une exception de type B4D est levée.
	 * @throws IOException Si un problème de fichier survient.
     * @throws GeneralSecurityException Si problèmes de sécurité survient.
	 */
	public void intro(Person person) throws StopProgramException, CancelProgramException, B4DException, IOException, GeneralSecurityException;
	
	/** Fonction principale du programme. Celle-ci sera éxecutée plusieurs fois.
	 * @param person - Personnage avec lequel lancer le programme.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 * @throws B4DException Si une exception de type B4D est levée.
	 * @throws FullInventoryException Si l'inventaire est plein.
	 * @throws TesseractException Si une exception Tesseract est levée.
	 * @throws AWTException Si un problème de souris ou clavier survient. 
	 * @throws IOException Si un problème de fichier survient.
	 * @throws NumberFormatException Si un problème d'encodage survient.
	 */
	public void cycle(Person person) throws StopProgramException, CancelProgramException, FullInventoryException, AWTException, CannotFindException, IOException, TesseractException, NumberFormatException, B4DException;
	
	/** Fonction de fin du programme. Celle-ci ne sera éxecutée qu'une seule fois en fin de programme.
	 * @param person - Personnage avec lequel lancer la fonction de fin.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void outro(Person person) throws CancelProgramException;
}
