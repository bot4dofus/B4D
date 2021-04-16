package fr.B4D.program;

import java.io.Serializable;

/**
 * The {@code ProgramOptions} class represents options to start a program with.
 * 
 * @author Lucas
 *
 */
public class ProgramOptions implements Serializable{

	private static final long serialVersionUID = -4725926340583319625L;	
	
	/**
	 * Number of cycles.
	 */
	private int cycles;
	
	/**
	 * Delay between each cycle.
	 */
	private long delay;

	/**
	 * Specifies if the player must go to an HDV when the inventory is full.
	 */
	private boolean hdvWhenFull;

	/**
	 * Specifies if the player must go to a bank when the inventory is full.
	 */
	private boolean bankWhenFull;

	/**
	 * Specifies if the program must stop when the inventory is full.
	 */
	private boolean stopWhenFull;

	/**
	 * Constructor of the {@code ProgramOptions} class.
	 * @param cycles - Number of cycles before the end of the program.
	 * @param delay - Delay between each cycle in ms.
	 * @param hdvWhenFull - {@code true} if the player must go to an HDV when the inventory is full, {@code false} otherwise.
	 * @param bankWhenFull - {@code true} if the player must go to a bank when the inventory is full, {@code false} otherwise.
	 * @param stopWhenFull - {@code true} if the program must stop when the inventory is full, {@code false} otherwise.
	 */
	public ProgramOptions(int cycles, long delay, boolean hdvWhenFull, boolean bankWhenFull, boolean stopWhenFull) {
		this.cycles = cycles;
		this.delay = delay;
		this.hdvWhenFull = hdvWhenFull;
		this.bankWhenFull = bankWhenFull;
		this.stopWhenFull = stopWhenFull;
	}
	
	/**
	 * Returns the number of cycles.
	 * @return Nnumber of cycles.
	 */
	public int getCycles() {
		return cycles;
	}
	
	/**
	 * Returns the delay between each cycle.
	 * @return Delay between each cycle.
	 */
	public long getDelay() {
		return delay;
	}
	
	/**
	 * Specifies whether the player must go to an HDV when the inventory is full.
	 * @return {@code true} if the player must go to an HDV when the inventory is full, {@code false} otherwise.
	 */
	public boolean isHdvWhenFull() {
		return hdvWhenFull;
	}

	/**
	 * Specifies whether the player must go to a bank when the inventory is full.
	 * @return {@code true} if the player must go to a bank when the inventory is full, {@code false} otherwise.
	 */
	public boolean isBankWhenFull() {
		return bankWhenFull;
	}

	/**
	 * Specifies whether the program must stop when the inventory is full.
	 * @return {@code true} if the program must stop when the inventory is full, {@code false} otherwise.
	 */
	public boolean isStopWhenFull() {
		return stopWhenFull;
	}
}
