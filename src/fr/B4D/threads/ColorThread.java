package fr.B4D.threads;

import java.awt.Color;
import java.awt.Point;

import fr.B4D.bot.B4D;

public class ColorThread extends Thread{
	private Point pixel;
	private Color min;
	private Color max;
		
	public ColorThread(Point pixel, Color min, Color max) {
		this.pixel = pixel;
		this.min = min;
		this.max = max;
	}
		
	public void run(){
		try{
			while(B4D.screen.isBetween(B4D.screen.getPixelColor(pixel), min, max));
		}catch (Exception e){
			Thread.currentThread().interrupt();			
		}
	}
}
