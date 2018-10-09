package fr.B4D.gui;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fr.B4D.classes.Bot;
import fr.B4D.exceptions.B4DFullInventory;
import fr.B4D.programs.Test;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanel_Admin extends JPanel {

	private static final long serialVersionUID = -7603368625926813641L;
	
	public final int width = 635;
	public final int height = 70;

	private JButton btnStart;
	private JButton btnRecord;
	
	/**
	 * Create the panel.
	 */
	public JPanel_Admin() {
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				if(Bot.configuration.gameFrame != null && Bot.configuration.chatFrame != null && Bot.configuration.chatBar != null && Bot.configuration.minimap != null) {
					btnStart.setEnabled(true);
					btnRecord.setEnabled(true);
				}else {
					btnStart.setEnabled(false);		
					btnRecord.setEnabled(false);					
				}
			}
		});
		setBackground(new Color(33,43,53));
		setLayout(null);
		
		JLabel lblStart = new JLabel("Programme de test :");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStart.setOpaque(true);
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setForeground(Color.WHITE);
		lblStart.setBackground(new Color(46, 139, 87));
		lblStart.setBounds(10, 10, 150, 25);
		add(lblStart);
		
		btnStart = new JButton("Commencer");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {					
					Test.test.run();
				} catch (B4DFullInventory e1) {
					e1.printStackTrace();
				}
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnStart.setBounds(10, 35, 150, 25);
		add(btnStart);
		
		JLabel lblRecord = new JLabel("Enregistrement :");
		lblRecord.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRecord.setOpaque(true);
		lblRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecord.setForeground(Color.WHITE);
		lblRecord.setBackground(new Color(46, 139, 87));
		lblRecord.setBounds(165, 10, 150, 25);
		add(lblRecord);
		
		btnRecord = new JButton("Commencer");
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRecord.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRecord.setBounds(165, 35, 150, 25);
		add(btnRecord);
		setVisible(true);
	}

}
