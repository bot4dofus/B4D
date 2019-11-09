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

public class JFrame_GetPointImage{
	
	public JFrame frame;
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
		frame = new JFrame();
		frame.addMouseListener(mouseListener);
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frame.dispose();
			}
		});
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(Color.GRAY);		
		frame.setOpacity(0.50f);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		if(image != null) {
			JLabel label = new JLabel("");
			label.setBounds((int)(screenSize.getWidth() - image.getIconWidth())/2, (int)(screenSize.getHeight() - image.getIconHeight())/2, image.getIconWidth(), image.getIconHeight());
			label.setIcon(image);
			frame.add(label);
			
			JLabel text = new JLabel(this.text);
			text.setFont(new Font("Tahoma", Font.PLAIN, 30));
			text.setBounds(0, label.getY()-100, frame.getWidth(), 100);
			text.setForeground(Color.WHITE);
			text.setHorizontalAlignment(SwingConstants.CENTER);
			text.setVerticalAlignment(SwingConstants.BOTTOM);
			frame.add(text);
		}    
	}

}
