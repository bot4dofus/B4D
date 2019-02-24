package fr.B4D.program;

import java.io.IOException;

import fr.B4D.bot.B4DException;
import fr.B4D.bot.Person;
import net.sourceforge.tess4j.TesseractException;

/** Interface représentant un programme et ses sub-routines.
 *
 */
public interface ProgramInterface{
	
	/** Fonction d'introduction du programme. Celle-ci ne sera éxecutée qu'une seule fois en début de programme.
	 * @param person - Personnage avec lequel lancer l'introduction.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 * @throws B4DException Si une exception de type B4D est levée.
	 * @throws IOException Si un problème de fichier survient.
	 */
	public void intro(Person person) throws StopProgramException, CancelProgramException, B4DException, IOException;
	
	/** Fonction principale du programme. Celle-ci sera éxecutée plusieurs fois.
	 * @param person - Personnage avec lequel lancer le programme.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 * @throws B4DException Si une exception de type B4D est levée.
	 * @throws FullInventoryException Si l'inventaire est plein.
	 * @throws TesseractException Si une exception Tesseract est levée. 
	 * @throws IOException Si un problème de fichier survient.
	 */
	public void cycle(Person person) throws StopProgramException, CancelProgramException, FullInventoryException, IOException, TesseractException, B4DException;
	
	/** Fonction de fin du programme. Celle-ci ne sera éxecutée qu'une seule fois en fin de programme.
	 * @param person - Personnage avec lequel lancer la fonction de fin.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void outro(Person person) throws CancelProgramException;
}
