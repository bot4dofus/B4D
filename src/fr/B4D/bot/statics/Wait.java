package fr.B4D.bot.statics;

import javax.swing.JOptionPane;

import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;


public final class Wait {
	
	private boolean suspended = false;
	
	  /*************/
	 /** SETTERS **/
	/*************/
	
	public void suspend() {
		suspended = true;
	}
	
	public void setPause() throws StopProgramException, CancelProgramException {
		Object[] options = {"Lecture", "Stopper", "Interrompre"};
		int response = JOptionPane.showOptionDialog(null, "Le bot est sur pause. Appuyez sur \"Lecture\" pour reprendre, sur \"Stop\" pour arreter le programme et sur \"Interrompre\" pour forcer l'arret.", "Pause", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);						
		suspended = false;
		if(response == JOptionPane.NO_OPTION)
			throw new StopProgramException();
		else if(response == JOptionPane.CANCEL_OPTION)
			throw new CancelProgramException();
	}

	  /********************/
	 /** ATTENTE SIMPLE **/
	/********************/

	public void wait(int timeOut) throws StopProgramException, CancelProgramException {
		if(suspended)
			setPause();
		else {
			try {
				Thread.sleep(timeOut);
			} catch (InterruptedException e) {
				//Do nothing
			}
		}
	}
}
