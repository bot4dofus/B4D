package fr.B4D.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * The class {@code JFrame_GetPointImage} is a GUI used to get a location of an element on the screen.<br>
 * This interface displays a gray filter on the entire screen with instructions and a picture.
 * 
 * @author Lucas
 *
 */
public class JFrame_GetPointImage extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6872332734366298081L;
	
	private MouseListener mouseListener;
	private ImageIcon image;
	private String text;

	/**
	 * Create the application.
	 * @param text - Texte affiché à l'écran.
	 * @param image - Image affichée à l'écran.
	 * @param mouseListener Sub-routine exécutée lors qu clique.
	 */
	public JFrame_GetPointImage(String text, ImageIcon image, MouseListener mouseListener) {
		this.text = text;
		this.image = image;
		this.mouseListener = mouseListener;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		if(image != null) {
			JLabel label = new JLabel("");
			label.setBounds((int)(screenSize.getWidth() - image.getIconWidth())/2, (int)(screenSize.getHeight() - image.getIconHeight())/2, image.getIconWidth(), image.getIconHeight());
			label.setIcon(image);
			add(label);
			
			JLabel text = new JLabel(this.text);
			text.setFont(new Font("Tahoma", Font.PLAIN, 30));
			text.setBounds(0, label.getY()-100, getWidth(), 100);
			text.setForeground(Color.WHITE);
			text.setHorizontalAlignment(SwingConstants.CENTER);
			text.setVerticalAlignment(SwingConstants.BOTTOM);
			add(text);
		}    
	}

}
