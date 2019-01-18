package fr.B4D.threads;

import java.awt.Rectangle;

import fr.B4D.modules.B4DScreen;

public class OCRThread extends Thread{
	private Rectangle rectangle;
	private String text, result;

	public OCRThread(Rectangle rectangle, String text) {
		this.rectangle = rectangle;
		this.text = text;
	}
		
	public String getResult() {
		return result;
	}
	
	public void run(){
		try{
			//while(!B4DScreen.OCR(rectangle).contains(texte));
			String ocr;
			do {
				ocr = B4DScreen.OCR(rectangle);
			}while(!ocr.contains(text));
			result = ocr;
		}catch (Exception e){
			Thread.currentThread().interrupt();
		}
	}
}
