package fr.B4D.threads;

import java.awt.Color;
import java.awt.Point;

import fr.B4D.bot.B4D;

public class PixelThread extends Thread{
	private Point pixel;
		
	public PixelThread(Point pixel) {
		this.pixel = pixel;
	}
		
	public void run(){
		try{
			Color couleur = B4D.screen.getPixelColor(pixel);
			while(couleur.equals(B4D.screen.getPixelColor(pixel)));
		}catch (Exception e){
			Thread.currentThread().interrupt();			
		}
	}
}
