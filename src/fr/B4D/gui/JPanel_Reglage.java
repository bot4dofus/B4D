package fr.B4D.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class JPanel_Reglage extends JPanel {
	final int width = 635;
	final int height = 125;

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
		panel_Zone_Jeu.setBounds(10, 10, 150, 105);
		add(panel_Zone_Jeu);
		
		JLabel lblSortDePorte = new JLabel("Zone de jeu");
		lblSortDePorte.setOpaque(true);
		lblSortDePorte.setHorizontalAlignment(SwingConstants.CENTER);
		lblSortDePorte.setForeground(Color.WHITE);
		lblSortDePorte.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSortDePorte.setBackground(new Color(46, 139, 87));
		lblSortDePorte.setBounds(0, 0, 150, 25);
		panel_Zone_Jeu.add(lblSortDePorte);
		
		JLabel lblXY_3 = new JLabel("X:Y");
		lblXY_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_3.setForeground(Color.DARK_GRAY);
		lblXY_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXY_3.setBackground(Color.GRAY);
		lblXY_3.setBounds(0, 25, 150, 25);
		panel_Zone_Jeu.add(lblXY_3);
		
		JLabel lblLxh = new JLabel("LxH");
		lblLxh.setHorizontalAlignment(SwingConstants.CENTER);
		lblLxh.setForeground(Color.DARK_GRAY);
		lblLxh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLxh.setBackground(Color.GRAY);
		lblLxh.setBounds(0, 50, 150, 25);
		panel_Zone_Jeu.add(lblLxh);
		
		JButton button_4 = new JButton("Modifier");
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_4.setBackground(UIManager.getColor("Button.background"));
		button_4.setBounds(5, 75, 140, 25);
		panel_Zone_Jeu.add(button_4);
		
		JPanel panel_Zone_Chat = new JPanel();
		panel_Zone_Chat.setLayout(null);
		panel_Zone_Chat.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Zone_Chat.setBackground(Color.LIGHT_GRAY);
		panel_Zone_Chat.setBounds(165, 10, 150, 105);
		add(panel_Zone_Chat);
		
		JLabel lblZoneDeChat = new JLabel("Zone de chat");
		lblZoneDeChat.setOpaque(true);
		lblZoneDeChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblZoneDeChat.setForeground(Color.WHITE);
		lblZoneDeChat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblZoneDeChat.setBackground(new Color(46, 139, 87));
		lblZoneDeChat.setBounds(0, 0, 150, 25);
		panel_Zone_Chat.add(lblZoneDeChat);
		
		JLabel label_1 = new JLabel("X:Y");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBackground(Color.GRAY);
		label_1.setBounds(0, 25, 150, 25);
		panel_Zone_Chat.add(label_1);
		
		JLabel label_2 = new JLabel("LxH");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_2.setBackground(Color.GRAY);
		label_2.setBounds(0, 50, 150, 25);
		panel_Zone_Chat.add(label_2);
		
		JButton button = new JButton("Modifier");
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.setBackground(UIManager.getColor("Button.background"));
		button.setBounds(5, 75, 140, 25);
		panel_Zone_Chat.add(button);
		
		JPanel panel_Barre_Chat = new JPanel();
		panel_Barre_Chat.setLayout(null);
		panel_Barre_Chat.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Barre_Chat.setBackground(Color.LIGHT_GRAY);
		panel_Barre_Chat.setBounds(320, 10, 150, 80);
		add(panel_Barre_Chat);
		
		JLabel lblBarreDeChat = new JLabel("Barre de chat");
		lblBarreDeChat.setOpaque(true);
		lblBarreDeChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblBarreDeChat.setForeground(Color.WHITE);
		lblBarreDeChat.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBarreDeChat.setBackground(new Color(46, 139, 87));
		lblBarreDeChat.setBounds(0, 0, 150, 25);
		panel_Barre_Chat.add(lblBarreDeChat);
		
		JLabel label_4 = new JLabel("X:Y");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.DARK_GRAY);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_4.setBackground(Color.GRAY);
		label_4.setBounds(0, 25, 150, 25);
		panel_Barre_Chat.add(label_4);
		
		JButton button_1 = new JButton("Modifier");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_1.setBackground(UIManager.getColor("Button.background"));
		button_1.setBounds(5, 50, 140, 25);
		panel_Barre_Chat.add(button_1);
		
		JPanel panel_Minimap = new JPanel();
		panel_Minimap.setBounds(475, 10, 150, 80);
		add(panel_Minimap);
		panel_Minimap.setLayout(null);
		panel_Minimap.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Minimap.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblMinimap = new JLabel("Minimap");
		lblMinimap.setOpaque(true);
		lblMinimap.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimap.setForeground(Color.WHITE);
		lblMinimap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMinimap.setBackground(new Color(46, 139, 87));
		lblMinimap.setBounds(0, 0, 150, 25);
		panel_Minimap.add(lblMinimap);
		
		JLabel label_6 = new JLabel("X:Y");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.DARK_GRAY);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_6.setBackground(Color.GRAY);
		label_6.setBounds(0, 25, 150, 25);
		panel_Minimap.add(label_6);
		
		JButton button_2 = new JButton("Modifier");
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_2.setBackground(UIManager.getColor("Button.background"));
		button_2.setBounds(5, 50, 140, 25);
		panel_Minimap.add(button_2);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAnnuler.setBounds(320, 90, 150, 25);
		add(btnAnnuler);
		
		JButton btnTerminer = new JButton("Terminer");
		btnTerminer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnTerminer.setBounds(475, 90, 150, 25);
		add(btnTerminer);
	}
}
