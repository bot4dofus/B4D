package fr.B4D.bot.statics;

import java.awt.AWTException;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Configuration;
import fr.B4D.gui.JFrame_GetPoint;
import fr.B4D.gui.JFrame_GetPointImage;
import fr.B4D.program.CancelProgramException;
import fr.B4D.program.StopProgramException;
import fr.B4D.utils.PointD;
import fr.B4D.utils.PointF;

/** La classe {@code Mouse} permet d'accéder à toutes les méthodes liés à la souris.
 */
public final class Mouse {

	private Configuration configuration;
	private Robot robot;
    
	/*************/
	/** BUILDER **/
	/*************/

	/** Constructeur de la classe {@code Mouse}. 
     * @param configuration - Configuration de l'écran de jeu.
	 * @throws AWTException Si la configuration de l'ordinateur ne permet pas l'automatisation de la souris
     */
    public Mouse(Configuration configuration) throws AWTException {
    	this.configuration = configuration;
		this.robot = new Robot();
    }
    
	/***************/
	/** GET POINT **/
	/***************/

	/** Permet de récupérer la position d'un élément en coordonnées simples.
	 * @param text - Texte affiché.
	 * @param mouseAdapter - Action à réaliser l'or de l'appui.
	 */
	public void getPoint(String text, MouseAdapter mouseAdapter) {
		JFrame_GetPoint window = new JFrame_GetPoint(text, mouseAdapter);
		window.frame.setVisible(true);
	}
	
	/** Permet de récupérer la position d'un élément en coordonnées simples.
	 * @param text - Texte affichée.
	 * @param image - Image affichée.
	 * @param mouseAdapter - Action à réaliser l'or de l'appui.
	 */
	public void getPoint(String text, ImageIcon image, MouseAdapter mouseAdapter) {
		JFrame_GetPointImage window = new JFrame_GetPointImage(text, image, mouseAdapter);
		window.frame.setVisible(true);
	}
	
	/** Permet de récupérer la position de deux éléments en coordonnées simples.
	 * @param text1 - Texte affiché pour le premier appui.
	 * @param mouseAdapter1 - Action à réaliser l'or du premier appui.
	 * @param text2 - Texte affiché pour le second appui.
	 * @param mouseAdapter2 - Action à réaliser l'or du second appui.
	 */
	public void getPoints(String text1, MouseAdapter mouseAdapter1, String text2, MouseAdapter mouseAdapter2) {
		JFrame_GetPoint window1 = new JFrame_GetPoint(text1, mouseAdapter1);
		JFrame_GetPoint window2 = new JFrame_GetPoint(text2, mouseAdapter2);
		window1.frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				window2.frame.setVisible(true);
			}
		});
		window1.frame.setVisible(true);
	}
	
	/** Permet de récupérer la position de deux éléments en coordonnées simples.
	 * @param text1 - Texte affiché pour le premier appui
	 * @param image1 - Image affichée pour le premier appui.
	 * @param mouseAdapter1 - Action à réaliser l'or du premier appui.
	 * @param text2 - Texte affiché pour le second appui.
	 * @param image2 - Image affichée pour le second appui.
	 * @param mouseAdapter2 - Action à réaliser l'or du second appui.
	 */
	public void getPoints(String text1, ImageIcon image1, MouseAdapter mouseAdapter1, String text2, ImageIcon image2, MouseAdapter mouseAdapter2) {
		JFrame_GetPointImage window1 = new JFrame_GetPointImage(text1, image1, mouseAdapter1);
		JFrame_GetPointImage window2 = new JFrame_GetPointImage(text2, image2, mouseAdapter2);
		window1.frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				window2.frame.setVisible(true);
			}
		});
		window1.frame.setVisible(true);
	}
	
	  /************/
	 /** PLACER **/
	/************/
	
	/** Permet de placer la souris à une certaine position.
	 * @param position - Position de la souris en coordonnées simples.
	 * @param millis - Temps d'attente après positionnement.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void place(Point position, int millis) throws StopProgramException, CancelProgramException {
		robot.mouseMove((int)position.getX(),(int)position.getY());
		B4D.wait.wait(millis);
	}
	
	/** Permet de placer la souris à une certaine position avec un temps d'attente par défaut de 0ms.
	 * Cela est identique à {@code place(position, 0)}.
	 * @param position - Position de la souris en coordonnées simples.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void place(Point position) throws StopProgramException, CancelProgramException {
		place(position, 0);
	}
	
	/** Permet de placer la souris à une certaine position.
	 * @param position - Position de la souris en coordonnées relatives.
	 * @param millis - Temps d'attente après positionnement.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void place(PointF position, int millis) throws StopProgramException, CancelProgramException {
		place(B4D.converter.toPoint(position), millis);
	}
	
	/** Permet de placer la souris à une certaine position avec un temps d'attente par défaut de 0ms.
	 * Cela est identique à {@code place(position, 0)}.
	 * @param position - Position de la souris en coordonnées relatives.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void place(PointF position) throws StopProgramException, CancelProgramException {
		place(B4D.converter.toPoint(position), 0);
	}

	/** Permet de placer la souris à une certaine position.
	 * @param position - Position de la souris en coordonnées du damier de dofus.
	 * @param millis - Temps d'attente après positionnement.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void place(PointD position, int millis) throws StopProgramException, CancelProgramException {
		place(B4D.converter.toPoint(position), millis);
	}
	
	/** Permet de placer la souris à une certaine position avec un temps d'attente par défaut de 0ms.
	 * Cela est identique à {@code place(position, 0)}.
	 * @param position - Position de la souris en coordonnées du damier de dofus.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void place(PointD position) throws StopProgramException, CancelProgramException {
		place(B4D.converter.toPoint(position), 0);
	}
	
	  /******************/
	 /** CLIQUE DROIT **/
	/******************/

	/** Permet de simuler un clique droit.
	 * @param position - Position de la souris en coordonnées simples.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @param millis - Temps d'attente après clique.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void rightClick(Point position, boolean maj, int millis) throws StopProgramException, CancelProgramException{
		robot.mouseMove((int)position.getX(),(int)position.getY());
			
		if(maj)
			robot.keyPress(KeyEvent.VK_SHIFT);
		robot.mousePress(KeyEvent.BUTTON3_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON3_DOWN_MASK);
		if(maj)	
			robot.keyRelease(KeyEvent.VK_SHIFT);

		B4D.wait.wait(millis);		
	}
	
	/** Permet de simuler un clique droit avec un temps d'attente par défaut de 1 secondes.
	 * Cela est identique à {@code rightClick(position, 1000)}.
	 * @param position - Position de la souris en coordonnées simples.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void rightClick(Point position, boolean maj) throws StopProgramException, CancelProgramException{
		rightClick(position, maj, 1000);
	}
	
	/** Permet de simuler un clique droit.
	 * @param position - Position de la souris en coordonnées relatives.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @param millis - Temps d'attente après clique.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void rightClick(PointF position, boolean maj, int millis) throws StopProgramException, CancelProgramException{
		rightClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/** Permet de simuler un clique droit avec un temps d'attente par défaut de 1 secondes.
	 * Cela est identique à {@code rightClick(position, 1000)}.
	 * @param position - Position de la souris en coordonnées relatives.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void rightClick(PointF position, boolean maj) throws StopProgramException, CancelProgramException{
		rightClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	/** Permet de simuler un clique droit.
	 * @param position - Position de la souris en coordonnées du damier de dofus.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @param millis - Temps d'attente après clique.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void rightClick(PointD position, boolean maj, int millis) throws StopProgramException, CancelProgramException{
		rightClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/** Permet de simuler un clique droit avec un temps d'attente par défaut de 1 secondes.
	 * Cela est identique à {@code rightClick(position, 1000)}.
	 * @param position - Position de la souris en coordonnées du damier de dofus.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void rightClick(PointD position, boolean maj) throws StopProgramException, CancelProgramException{
		rightClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	  /*******************/
	 /** CLIQUE GAUCHE **/
	/*******************/
	
	/** Permet de simuler un clique gauche.
	 * @param position - Position de la souris en coordonnées simples.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @param millis - Temps d'attente après clique.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void leftClick(Point position, boolean maj, int millis) throws StopProgramException, CancelProgramException {
		robot.mouseMove((int)position.getX(),(int)position.getY());
			
		if(maj)
			robot.keyPress(KeyEvent.VK_SHIFT);
		robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		if(maj)	
			robot.keyRelease(KeyEvent.VK_SHIFT);

		B4D.wait.wait(millis);
	}
	
	/** Permet de simuler un clique gauche avec un temps d'attente par défaut de 1 secondes.
	 * Cela est identique à {@code leftClick(position, 1000)}.
	 * @param position - Position de la souris en coordonnées simples.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void leftClick(Point position, boolean maj) throws StopProgramException, CancelProgramException{
		leftClick(position, maj, 1000);
	}
	
	/** Permet de simuler un clique gauche.
	 * @param position - Position de la souris en coordonnées relatives.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @param millis - Temps d'attente après clique.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void leftClick(PointF position, boolean maj, int millis) throws StopProgramException, CancelProgramException{
		leftClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/** Permet de simuler un clique gauche avec un temps d'attente par défaut de 1 secondes.
	 * Cela est identique à {@code leftClick(position, 1000)}.
	 * @param position - Position de la souris en coordonnées relatives.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void leftClick(PointF position, boolean maj) throws StopProgramException, CancelProgramException{
		leftClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	/** Permet de simuler un clique gauche.
	 * @param position - Position de la souris en coordonnées du damier de dofus.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @param millis - Temps d'attente après clique.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void leftClick(PointD position, boolean maj, int millis) throws StopProgramException, CancelProgramException{
		leftClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/** Permet de simuler un clique gauche avec un temps d'attente par défaut de 1 secondes.
	 * Cela est identique à {@code leftClick(position, 1000)}.
	 * @param position - Position de la souris en coordonnées du damier de dofus.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void leftClick(PointD position, boolean maj) throws StopProgramException, CancelProgramException{
		leftClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	  /**************************/
	 /** DOUBLE CLIQUE GAUCHE **/
	/**************************/
	
	/** Permet de simuler un double clique gauche.
	 * @param position - Position de la souris en coordonnées simples.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @param millis - Temps d'attente après clique.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void doubleLeftClick(Point position, boolean maj, int millis) throws StopProgramException, CancelProgramException {
		leftClick(position, maj, 0);
		leftClick(position, maj, millis);
	}
	
	/** Permet de simuler un clique gauche avec un temps d'attente par défaut de 1 secondes.
	 * Cela est identique à {@code doubleLeftClick(position, 1000)}.
	 * @param position - Position de la souris en coordonnées simples.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void doubleLeftClick(Point position, boolean maj) throws StopProgramException, CancelProgramException{
		doubleLeftClick(position, maj, 1000);
	}
	
	/** Permet de simuler un double clique gauche.
	 * @param position - Position de la souris en coordonnées relatives.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @param millis - Temps d'attente après clique.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void doubleLeftClick(PointF position, boolean maj, int millis) throws StopProgramException, CancelProgramException{
		doubleLeftClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/** Permet de simuler un clique gauche avec un temps d'attente par défaut de 1 secondes.
	 * Cela est identique à {@code doubleLeftClick(position, 1000)}.
	 * @param position - Position de la souris en coordonnées relatives.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void doubleLeftClick(PointF position, boolean maj) throws StopProgramException, CancelProgramException{
		doubleLeftClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	/** Permet de simuler un double clique gauche.
	 * @param position - Position de la souris en coordonnées du damier de dofus.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @param millis - Temps d'attente après clique.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void doubleLeftClick(PointD position, boolean maj, int millis) throws StopProgramException, CancelProgramException{
		doubleLeftClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/** Permet de simuler un clique gauche avec un temps d'attente par défaut de 1 secondes.
	 * Cela est identique à {@code doubleLeftClick(position, 1000)}.
	 * @param position - Position de la souris en coordonnées du damier de dofus.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void doubleLeftClick(PointD position, boolean maj) throws StopProgramException, CancelProgramException{
		doubleLeftClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	  /**************************/
	 /** TRIPLE CLIQUE GAUCHE **/
	/**************************/
	
	/** Permet de simuler un triple clique gauche.
	 * @param position - Position de la souris en coordonnées simples.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @param millis - Temps d'attente après clique.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void tripleLeftClick(Point position, boolean maj, int millis) throws StopProgramException, CancelProgramException {
		leftClick(position, maj, 0);
		leftClick(position, maj, 0);
		leftClick(position, maj, millis);
	}
	
	/** Permet de simuler un clique gauche avec un temps d'attente par défaut de 1 secondes.
	 * Cela est identique à {@code tripleLeftClick(position, 1000)}.
	 * @param position - Position de la souris en coordonnées simples.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void tripleLeftClick(Point position, boolean maj) throws StopProgramException, CancelProgramException{
		tripleLeftClick(position, maj, 1000);
	}
	
	/** Permet de simuler un triple clique gauche.
	 * @param position - Position de la souris en coordonnées relatives.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @param millis - Temps d'attente après clique.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void tripleLeftClick(PointF position, boolean maj, int millis) throws StopProgramException, CancelProgramException{
		tripleLeftClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/** Permet de simuler un clique gauche avec un temps d'attente par défaut de 1 secondes.
	 * Cela est identique à {@code tripleLeftClick(position, 1000)}.
	 * @param position - Position de la souris en coordonnées relatives.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void tripleLeftClick(PointF position, boolean maj) throws StopProgramException, CancelProgramException{
		tripleLeftClick(B4D.converter.toPoint(position), maj, 1000);
	}
	
	/** Permet de simuler un triple clique gauche.
	 * @param position - Position de la souris en coordonnées du damier de dofus.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @param millis - Temps d'attente après clique.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void tripleLeftClick(PointD position, boolean maj, int millis) throws StopProgramException, CancelProgramException{
		tripleLeftClick(B4D.converter.toPoint(position), maj, millis);
	}
	
	/** Permet de simuler un clique gauche avec un temps d'attente par défaut de 1 secondes.
	 * Cela est identique à {@code tripleLeftClick(position, 1000)}.
	 * @param position - Position de la souris en coordonnées du damier de dofus.
	 * @param maj - {@code true} si Shift (Maj) doit être enfoncé en même temps. Cela peut être utiliser pour empiler des actions.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void tripleLeftClick(PointD position, boolean maj) throws StopProgramException, CancelProgramException{
		tripleLeftClick(B4D.converter.toPoint(position), maj, 1000);
	}

	  /**********/
	 /** CHAT **/
	/**********/
	
	/** Permet de cliquer dans la barre de chat.
	 * @param millis - Temps d'attente après clique.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le bot programme est annulé.
	 */
	public void chatClick(int millis) throws StopProgramException, CancelProgramException{
		leftClick(configuration.getChatBar(), false, millis);
	}
	
	/** Permet de cliquer dans la barre de chat avec un temps d'attente par défaut de 500 millisecondes.
	 * Cela est identique à {@code chatClick(500)}.
	 * @throws StopProgramException Si le programme est stoppé.
	 * @throws CancelProgramException Si le programme est annulé.
	 */
	public void chatClick() throws StopProgramException, CancelProgramException{
		chatClick(500);
	}
}
