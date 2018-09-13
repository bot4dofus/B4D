package fr.B4D.threads;

import java.awt.Rectangle;

import fr.B4D.modules.autre.B4DEcran;

public class ThreadOCR extends Thread{
	private Rectangle rectangle;
	private String texte;
		
	public ThreadOCR(Rectangle rectangle, String texte) {
		this.rectangle = rectangle;
		this.texte = texte;
	}
		
	public void run(){
		try{
			while(!B4DEcran.OCR(rectangle).contains(texte));
		}catch (Exception e){
			Thread.currentThread().interrupt();
		}
	}
}
