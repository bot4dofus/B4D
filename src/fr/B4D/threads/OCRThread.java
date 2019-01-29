package fr.B4D.threads;

import java.awt.Rectangle;

import fr.B4D.bot.B4D;

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
			String ocr;
			do {
				ocr = B4D.screen.OCR(rectangle);
			}while(!ocr.contains(text));
			result = ocr;
		}catch (Exception e){
			Thread.currentThread().interrupt();
		}
	}
}
