package fr.B4D.threads;

import java.awt.Color;
import java.awt.Point;

import fr.B4D.bot.B4D;

public class ColorThread extends Thread{
	private Point pixel;
	private Color color, min, max;
		
	public ColorThread(Point pixel, Color min, Color max) {
		this.pixel = pixel;
		this.min = min;
		this.max = max;
	}
		
	public Color getColor() {
		return this.color;
	}
	
	public void run(){
		try{
			Color color;
			do {
				color = B4D.screen.getPixelColor(pixel);
				Thread.sleep(100);
			}while(!B4D.screen.isBetween(color, min, max));
			this.color = color;
		}catch (Exception e){
			Thread.currentThread().interrupt();			
		}
	}
}
