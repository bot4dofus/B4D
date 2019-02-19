package fr.B4D.threads;

import java.awt.Color;
import java.awt.Point;

import fr.B4D.bot.B4D;

/** La classe {@code PixelThread} permet d'attendre qu'un pixel change de couleur.
 * Cette classe étend la classe {@code Thread}.
 */
public class PixelThread extends Thread{
	private Point pixel;
	private Color color;

	/** Constructeur de la classe {@code PixelThread}.
	 * @param pixel - Position du pixel en coordonnées simples.
	 */
	public PixelThread(Point pixel) {
		this.pixel = pixel;
	}
	
	/** Retourne la couleur du pixel.
	 * @return Couleur du pixel. {@code null} si le pixel n'a pas changé.
	 */
	public Color getColor() {
		return this.color;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
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
