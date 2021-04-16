package fr.B4D.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * The class {@code JFrame_GetPoint} is a GUI used to get a location of an element on the screen.<br>
 * This interface displays a gray circle around the arrow with instructions in it.
 * 
 * @author Lucas
 *
 */
public class JFrame_GetPoint extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7361371118059302380L;
	
	private MouseListener mouseListener;
	private String text;

	/**
	 * Create the application.
	 * @param text - Texte affiché à l'écran.
	 * @param mouseListener - Sub-routine exécutée lors du clique.
	 */
	public JFrame_GetPoint(String text, MouseListener mouseListener) {
		this.text = text;
		this.mouseListener = mouseListener;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				setShape(new Ellipse2D.Double(0,0,getWidth(),getHeight()));
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				Point point = MouseInfo.getPointerInfo().getLocation();
				setLocation(point.x - getWidth()/2, point.y - getHeight()/2);
			}
		});
		addMouseListener(mouseListener);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		setUndecorated(true);
		getContentPane().setBackground(Color.GRAY);
		setOpacity(0.50f);
		setBounds(200, 200, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Point point = MouseInfo.getPointerInfo().getLocation();
		setLocation(point.x - getWidth()/2, point.y - getHeight()/2);
		
		JLabel text = new JLabel(this.text);
		text.setFont(new Font("Tahoma", Font.BOLD, 15));
		text.setBounds(0, 0, getWidth(), getHeight());
		text.setForeground(Color.WHITE);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setVerticalAlignment(SwingConstants.CENTER);
		add(text);
	}
}
