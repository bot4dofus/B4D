package fr.B4D.threads;

import java.awt.Rectangle;

import fr.B4D.modules.B4DScreen;

public class OCRThread extends Thread{
	private Rectangle rectangle;
	private String texte;
		
	public OCRThread(Rectangle rectangle, String texte) {
		this.rectangle = rectangle;
		this.texte = texte;
	}
		
	public void run(){
		try{
			while(!B4DScreen.OCR(rectangle).contains(texte));
		}catch (Exception e){
			Thread.currentThread().interrupt();
		}
	}
}
