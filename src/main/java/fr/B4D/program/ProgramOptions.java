package fr.B4D.program;

import java.io.Serializable;

/** La classe {@code ProgramOptions} permet de spécifier les options avec lesquelles lancer un Program.
 * @see fr.B4D.program.Program Program
 */
public class ProgramOptions implements Serializable{

	private static final long serialVersionUID = -4725926340583319625L;	
	
	  /***************/
	 /** ATTRIBUTS **/
	/***************/
	
	private int cycles;
	private long delay;

	private boolean hdvWhenFull;
	private boolean bankWhenFull;
	private boolean stopWhenFull;
	
	  /******************/
	 /** CONSTRUCTEUR **/
	/******************/

	/** Constructeur de la classe {@code ProgramOptions}. 
	 * @param cycles - Nombre de cycles avant fin du programme.
	 * @param delay - Delay between each cycle in ms.
	 * @param hdvWhenFull - {@code true} si le joueur veut vider son inventaire en HDV lorsque celui-ci est plein, {@code false} sinon.
	 * @param bankWhenFull - {@code true} si le joueur veut vider son inventaire en banque lorsque celui-ci est plein, {@code false} sinon.
	 * @param stopWhenFull - {@code true} si le joueur veut stopper le programme lorsque l'inventaire est plein, {@code false} sinon.
	 */
	public ProgramOptions(int cycles, long delay, boolean hdvWhenFull, boolean bankWhenFull, boolean stopWhenFull) {
		this.cycles = cycles;
		this.delay = delay;
		this.hdvWhenFull = hdvWhenFull;
		this.bankWhenFull = bankWhenFull;
		this.stopWhenFull = stopWhenFull;
	}
	
	  /*************/
	 /** GETTERS **/
	/*************/
	
	/** Retourne le nombre de cycles avant la fin du programme.
	 * @return Nombre de cycles restants.
	 */
	public int getCycles() {
		return cycles;
	}
	
	/** Returns the delay between each cycle.
	 * @return Delay between each cycle in ms.
	 */
	public long getDelay() {
		return delay;
	}
	
	/** Retourne un booléen représentant si le joueur veut vider son inventaire en HDV lorsque celui-ci est plein.
	 * @return {@code true} si le joueur veut vider son inventaire en HDV lorsque celui-ci est plein, {@code false} sinon.
	 */
	public boolean isHdvWhenFull() {
		return hdvWhenFull;
	}
	
	/** Retourne un booléen représentant si le joueur veut vider son inventaire en banque lorsque celui-ci est plein.
	 * @return {@code true} si le joueur veut vider son inventaire en banque lorsque celui-ci est plein, {@code false} sinon.
	 */
	public boolean isBankWhenFull() {
		return bankWhenFull;
	}
	
	/** Retourne un booléen représentant si le joueur veut stopper le programme lorsque l'inventaire est plein.
	 * @return {@code true} si le joueur veut stopper le programme lorsque l'inventaire est plein, {@code false} sinon.
	 */
	public boolean isStopWhenFull() {
		return stopWhenFull;
	}
}
