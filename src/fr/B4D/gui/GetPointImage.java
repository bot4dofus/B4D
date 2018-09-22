package fr.B4D.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class GetPointImage extends Thread{
	
	private JFrame frame;
	private Point point;
	private ImageIcon image;
	private String text;

	/**
	 * Create the application.
	 */
	public GetPointImage(String text, ImageIcon image) {
		this.text = text;
		this.image = image;
		initialize();
	}
	
	@Override
	public void run() {
		frame.setVisible(true);
		synchronized(frame) {
            try {
            	frame.wait();
    		} catch (InterruptedException e) {
    			e.printStackTrace();
            }
		}
	}
	
	public Point getPoint() {
		this.start();
		while(this.isAlive());
		return point;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				point = MouseInfo.getPointerInfo().getLocation();
				synchronized(frame) {
					frame.notify();
					frame.dispose();
				}
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
