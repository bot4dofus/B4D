package fr.B4D.interaction;

import java.awt.Point;

import fr.B4D.bot.B4D;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointF;

/** La classe {@code Status} représente un status de jeu.<br><br>
 * Un status est défini par un nom et une position relative au menu status.
 */
public class Status {
	
	  /****************/
	 /** COLLECTION **/
	/****************/
	
	public final static Status AVAILABLE = new Status("Disponible", new PointF(0.0131, -0.1008 + 0*0.0216));
	public final static Status ABSENT = new Status("Absent", new PointF(0.0131, -0.1008 + 1*0.0216));
	public final static Status PRIVATE = new Status("Privé", new PointF(0.0131, -0.1008 + 3*0.0216));
	public final static Status SOLO = new Status("Solo", new PointF(0.0131, -0.1008 + 4*0.0216));
	
	  /**************/
	 /** ATRIBUTS **/
	/**************/
	
	private static Point statusMenuPosition;
	
	private String name;
	private PointF relativPosition;
	
	  /*************/
	 /** BUILDER **/
	/*************/
	
	/** Constructeur de la classe {@code Message}. 
	 * @param name - Nom du status.
	 * @param relativPosition - Position relative du status par rapport au menu status.
	 */
	public Status(String name, PointF relativPosition) {
		this.name = name;
		this.relativPosition = relativPosition;
	}
	
	  /***********************/
	 /** GETTERS & SETTERS **/
	/***********************/ 
	
	/** Retourne le nom du status.
	 * @return Nom du status.
	 */
	public String getName() {
		return this.name;
	}
	
	/** Modifi la position du menu des status.
	 * @param statusMenuPosition - Nouvelle position du menu des status.
	 */
	public static void setStatusMenuPosition(Point statusMenuPosition) {
		Status.statusMenuPosition = statusMenuPosition;
	}
	
	  /************/
	 /** STATIC **/
	/************/
	
	/** Active le status pour le joueur en cours.
	 * @return {@code true} si le status a été activé, {@code false} sinon.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 */
	public boolean setStatus() throws StopProgramException, CancelProgramException {
		if(statusMenuPosition != null) {
			PointF menu = B4D.converter.toPointF(statusMenuPosition);
			B4D.mouse.leftClick(menu, false, 200);		//Ouvre le menu des status
			PointF checkPosition = new PointF(menu.x + relativPosition.x, menu.y + relativPosition.y);		
			B4D.mouse.leftClick(checkPosition, false, 100);
			return true;
		}
		else
			return false;
	}
	
	  /***************/
	 /** TO STRING **/
	/***************/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}
}
