package fr.B4D.gui;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Font;

public class JPanel_Reglage extends JPanel {
	final int width = 520;
	final int height = 105;

	/**
	 * Create the panel.
	 */
	public JPanel_Reglage() {
		setBackground(new Color(33,43,53));
		setLayout(null);
		setVisible(false);

		JPanel panel_Zone_Jeu = new JPanel();
		panel_Zone_Jeu.setLayout(null);
		panel_Zone_Jeu.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Zone_Jeu.setBackground(Color.LIGHT_GRAY);
		panel_Zone_Jeu.setBounds(10, 10, 120, 85);
		add(panel_Zone_Jeu);
		
		JLabel lblSortDePorte = new JLabel("Zone de jeu");
		lblSortDePorte.setOpaque(true);
		lblSortDePorte.setHorizontalAlignment(SwingConstants.CENTER);
		lblSortDePorte.setForeground(Color.WHITE);
		lblSortDePorte.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSortDePorte.setBackground(new Color(46, 139, 87));
		lblSortDePorte.setBounds(0, 0, 120, 20);
		panel_Zone_Jeu.add(lblSortDePorte);
		
		JLabel lblXY_3 = new JLabel("X:Y");
		lblXY_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_3.setForeground(Color.WHITE);
		lblXY_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblXY_3.setBackground(Color.GRAY);
		lblXY_3.setBounds(0, 20, 120, 20);
		panel_Zone_Jeu.add(lblXY_3);
		
		JLabel lblLxh = new JLabel("LxH");
		lblLxh.setHorizontalAlignment(SwingConstants.CENTER);
		lblLxh.setForeground(Color.WHITE);
		lblLxh.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblLxh.setBackground(Color.GRAY);
		lblLxh.setBounds(0, 40, 120, 20);
		panel_Zone_Jeu.add(lblLxh);
		
		JButton button_4 = new JButton("Modifier");
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_4.setBackground(Color.LIGHT_GRAY);
		button_4.setBounds(5, 60, 110, 20);
		panel_Zone_Jeu.add(button_4);
		
		JPanel panel_Zone_Chat = new JPanel();
		panel_Zone_Chat.setLayout(null);
		panel_Zone_Chat.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Zone_Chat.setBackground(Color.LIGHT_GRAY);
		panel_Zone_Chat.setBounds(135, 10, 125, 85);
		add(panel_Zone_Chat);
		
		JLabel lblZoneDeChat = new JLabel("Zone de chat");
		lblZoneDeChat.setOpaque(true);
		lblZoneDeChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblZoneDeChat.setForeground(Color.WHITE);
		lblZoneDeChat.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblZoneDeChat.setBackground(new Color(46, 139, 87));
		lblZoneDeChat.setBounds(0, 0, 125, 20);
		panel_Zone_Chat.add(lblZoneDeChat);
		
		JLabel label_1 = new JLabel("X:Y");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_1.setBackground(Color.GRAY);
		label_1.setBounds(0, 20, 125, 20);
		panel_Zone_Chat.add(label_1);
		
		JLabel label_2 = new JLabel("LxH");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_2.setBackground(Color.GRAY);
		label_2.setBounds(0, 40, 125, 20);
		panel_Zone_Chat.add(label_2);
		
		JButton button = new JButton("Modifier");
		button.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(5, 60, 115, 20);
		panel_Zone_Chat.add(button);
		
		JPanel panel_Barre_Chat = new JPanel();
		panel_Barre_Chat.setLayout(null);
		panel_Barre_Chat.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Barre_Chat.setBackground(Color.LIGHT_GRAY);
		panel_Barre_Chat.setBounds(265, 10, 120, 65);
		add(panel_Barre_Chat);
		
		JLabel lblBarreDeChat = new JLabel("Barre de chat");
		lblBarreDeChat.setOpaque(true);
		lblBarreDeChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblBarreDeChat.setForeground(Color.WHITE);
		lblBarreDeChat.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblBarreDeChat.setBackground(new Color(46, 139, 87));
		lblBarreDeChat.setBounds(0, 0, 120, 20);
		panel_Barre_Chat.add(lblBarreDeChat);
		
		JLabel label_4 = new JLabel("X:Y");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_4.setBackground(Color.GRAY);
		label_4.setBounds(0, 20, 120, 20);
		panel_Barre_Chat.add(label_4);
		
		JButton button_1 = new JButton("Modifier");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(5, 40, 110, 20);
		panel_Barre_Chat.add(button_1);
		
		JPanel panel_Minimap = new JPanel();
		panel_Minimap.setBounds(390, 10, 120, 65);
		add(panel_Minimap);
		panel_Minimap.setLayout(null);
		panel_Minimap.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Minimap.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblMinimap = new JLabel("Minimap");
		lblMinimap.setOpaque(true);
		lblMinimap.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimap.setForeground(Color.WHITE);
		lblMinimap.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMinimap.setBackground(new Color(46, 139, 87));
		lblMinimap.setBounds(0, 0, 120, 20);
		panel_Minimap.add(lblMinimap);
		
		JLabel label_6 = new JLabel("X:Y");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_6.setBackground(Color.GRAY);
		label_6.setBounds(0, 20, 120, 20);
		panel_Minimap.add(label_6);
		
		JButton button_2 = new JButton("Modifier");
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setBounds(5, 40, 110, 20);
		panel_Minimap.add(button_2);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(265, 75, 120, 20);
		add(btnAnnuler);
		
		JButton btnTerminer = new JButton("Terminer");
		btnTerminer.setBounds(390, 75, 120, 20);
		add(btnTerminer);
	}
}
