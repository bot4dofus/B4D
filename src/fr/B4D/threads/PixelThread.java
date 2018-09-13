package fr.B4D.threads;

import java.awt.Color;
import java.awt.Point;

import fr.B4D.modules.B4DScreen;

public class PixelThread extends Thread{
	private Point pixel;
		
	public PixelThread(Point pixel) {
		this.pixel = pixel;
	}
		
	public void run(){
		try{
			Color couleur = B4DScreen.getPixelColor(pixel);
			while(couleur.equals(B4DScreen.getPixelColor(pixel)));
		}catch (Exception e){
			Thread.currentThread().interrupt();			
		}
	}
}
