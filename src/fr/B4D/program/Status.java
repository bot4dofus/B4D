package fr.B4D.program;

import java.awt.AWTException;

import fr.B4D.bot.B4D;
import fr.B4D.utils.PointF;

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
	
	private final PointF statusMenuPosition = new PointF(0.28,0.989);
	
	private String name;
	private PointF relativPosition;
	
	  /*************/
	 /** BUILDER **/
	/*************/
	
	public Status(String name, PointF relativPosition) {
		this.name = name;
		this.relativPosition = relativPosition;
	}
	
	  /*************/
	 /** GETTERS **/
	/*************/ 
	
	public String getName() {
		return this.name;
	}
	
	  /************/
	 /** STATIC **/
	/************/
	
	public void setStatus() throws AWTException, StopProgramException, CancelProgramException {
		B4D.mouse.leftClick(statusMenuPosition, false, 200);		//Ouvre le menu des status
		PointF checkPosition = new PointF(statusMenuPosition.x + relativPosition.x, statusMenuPosition.y + relativPosition.y);		
		B4D.mouse.leftClick(checkPosition, false, 100);
	}
}
