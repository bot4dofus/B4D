package fr.B4D.threads;

import java.awt.Color;
import java.awt.Point;

import fr.B4D.bot.B4D;

public class PixelThread extends Thread{
	private Point pixel;
	private Color color;	
	
	public PixelThread(Point pixel) {
		this.pixel = pixel;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void run(){
		try{
			Color color = B4D.screen.getPixelColor(pixel);
			Color newColor;
			do {
				newColor = B4D.screen.getPixelColor(pixel);
				Thread.sleep(100);
			}while(color.equals(newColor));
			this.color = newColor;
		}catch (Exception e){
			Thread.currentThread().interrupt();			
		}
	}
}
