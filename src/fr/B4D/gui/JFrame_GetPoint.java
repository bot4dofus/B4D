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

public class JFrame_GetPoint {
	
	public JFrame frame;
	private MouseListener mouseListener;
	private String text;

	/**
	 * Create the application.
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
		frame = new JFrame();
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				frame.setShape(new Ellipse2D.Double(0,0,frame.getWidth(),frame.getHeight()));
			}
		});
		frame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				Point point = MouseInfo.getPointerInfo().getLocation();
				frame.setLocation(point.x - frame.getWidth()/2, point.y - frame.getHeight()/2);
			}
		});
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
		frame.setBounds(200, 200, 400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Point point = MouseInfo.getPointerInfo().getLocation();
		frame.setLocation(point.x - frame.getWidth()/2, point.y - frame.getHeight()/2);
		
		JLabel text = new JLabel(this.text);
		text.setFont(new Font("Tahoma", Font.BOLD, 15));
		text.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		text.setForeground(Color.WHITE);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setVerticalAlignment(SwingConstants.CENTER);
		frame.add(text);
	}
}
