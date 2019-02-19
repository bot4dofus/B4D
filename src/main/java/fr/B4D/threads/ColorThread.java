package fr.B4D.threads;

import java.awt.Color;
import java.awt.Point;

import fr.B4D.bot.B4D;

/** La classe {@code ColorThread} permet d'attendre qu'un pixel soit compris dans un intervale de couleur.
 * Cette classe étend la classe {@code Thread}.
 */
public class ColorThread extends Thread{
	private Point pixel;
	private Color color, min, max;

	/** Constructeur de la classe {@code ColorThread}.
	 * @param pixel - Position du pixel en coodonnées simples.
	 * @param min - Couleur minimale.
	 * @param max - Couleur maximale.
	 */
	public ColorThread(Point pixel, Color min, Color max) {
		this.pixel = pixel;
		this.min = min;
		this.max = max;
	}
		
	/** Retourne la couleur du pixel.
	 * @return Couleur du pixel. {@code null} si le pixel n'est pas dans l'interval attendu.
	 */
	public Color getColor() {
		return this.color;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
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
