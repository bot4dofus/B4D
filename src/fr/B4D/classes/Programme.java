package fr.B4D.classes;

import java.awt.Point;
import java.util.concurrent.Callable;

import fr.B4D.enu.Category;
import fr.B4D.enu.Place;
import fr.B4D.enu.Ressource;
import fr.B4D.enu.RessourceType;

public class Programme extends Thread{

	  /*************/
	 /* ATTRIBUTS */
	/*************/
	
	private Place place;
	private Category category;
	private RessourceType type;
	private Ressource ressource;

	private Point start;
	private Callable<Integer> fonction;
	
	private int maxCycles;
	private int maxSubmissions;

	  /****************/
	 /* CONSTRUCTEUR */
	/****************/
	
	public Programme(Place lieux, Category categorie, RessourceType type, Ressource ressource, Callable<Integer> fonction, Point depart, int nombre_tour, int nombre_depot) {
		this.place = lieux;
		this.category = categorie;
		this.type = type;
		this.ressource = ressource;
		this.start = depart;
		this.fonction = fonction;
		this.maxCycles = nombre_tour;
		this.maxSubmissions = nombre_depot;
	}
	
	  /*********************/
	 /* GETTERS & SETTERS */
	/*********************/
	
	//A faire si besoin
	
	  /************/
	 /* METHODES */
	/************/
	
	public void run() {
		try {
			Intro();
			while (Tour() == true);
		}catch(B4DException e){
			e.getReason();
		}finally {
			Outro();
		}
	}
	private void Intro() throws B4DException{
		
	}
	private boolean Tour() {
		
		//fonction.call()
		return true;
	}
	private void Outro() {
		
	}
}
