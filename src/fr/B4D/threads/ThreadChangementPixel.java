package fr.B4D.threads;

import java.awt.Color;
import java.awt.Point;

import fr.B4D.modules.autre.B4DEcran;

public class ThreadChangementPixel extends Thread{
	private Point pixel;
		
	public ThreadChangementPixel(Point pixel) {
		this.pixel = pixel;
	}
		
	public void run(){
		try{
			Color couleur = B4DEcran.Couleur_Pixel(pixel);
			while(couleur.equals(B4DEcran.Couleur_Pixel(pixel)));
		}catch (Exception e){
			Thread.currentThread().interrupt();			
		}
	}
}
