package fr.B4D.gui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import fr.B4D.classes.Bot;
import fr.B4D.modules.B4DSouris;

import javax.swing.SwingConstants;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class JPanel_Reglage extends JPanel {
	final int width = 635;
	final int height = 125;

	JLabel lblXY_GameFrame = new JLabel("X:Y");
	JLabel lblLH_GameFrame = new JLabel("LxH");
	JLabel lblXY_ChatFrame = new JLabel("X:Y");
	JLabel lblLH_ChatFrame = new JLabel("LxH");
	JLabel lblXY_ChatBar = new JLabel("X:Y");
	JLabel lblXY_Minimap = new JLabel("X:Y");
	
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
		
		lblXY_GameFrame = new JLabel("X:Y");
		lblXY_GameFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_GameFrame.setForeground(Color.DARK_GRAY);
		lblXY_GameFrame.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXY_GameFrame.setBackground(Color.GRAY);
		lblXY_GameFrame.setBounds(0, 25, 150, 25);
		panel_Zone_Jeu.add(lblXY_GameFrame);
		
		lblLH_GameFrame = new JLabel("LxH");
		lblLH_GameFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblLH_GameFrame.setForeground(Color.DARK_GRAY);
		lblLH_GameFrame.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLH_GameFrame.setBackground(Color.GRAY);
		lblLH_GameFrame.setBounds(0, 50, 150, 25);
		panel_Zone_Jeu.add(lblLH_GameFrame);
		
		JButton button_4 = new JButton("Modifier");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Point topLeft = B4DSouris.getPoint("Cliquez dans le coin suppérieur gauche", new ImageIcon(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/fr/B4D/images/ZoneHautGauche.png"))));
					Point bottomRight = B4DSouris.getPoint("Cliquez dans le coin inférieur droit", new ImageIcon(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/fr/B4D/images/ZoneBasDroite.png"))));
					Bot.MyConfiguration.gameFrame = new Rectangle(topLeft.x, topLeft.y, bottomRight.x - topLeft.x, bottomRight.y - topLeft.y);
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
			}
		});
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
		
		lblXY_ChatFrame = new JLabel("X:Y");
		lblXY_ChatFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_ChatFrame.setForeground(Color.DARK_GRAY);
		lblXY_ChatFrame.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXY_ChatFrame.setBackground(Color.GRAY);
		lblXY_ChatFrame.setBounds(0, 25, 150, 25);
		panel_Zone_Chat.add(lblXY_ChatFrame);

		lblLH_ChatFrame = new JLabel("LxH");
		lblLH_ChatFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblLH_ChatFrame.setForeground(Color.DARK_GRAY);
		lblLH_ChatFrame.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLH_ChatFrame.setBackground(Color.GRAY);
		lblLH_ChatFrame.setBounds(0, 50, 150, 25);
		panel_Zone_Chat.add(lblLH_ChatFrame);
		
		JButton button = new JButton("Modifier");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Point topLeft = B4DSouris.getPoint("Cliquez dans le coin suppérieur gauche de la zone de chat", new ImageIcon(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/fr/B4D/images/ChatHautGauche.png"))));
					Point bottomRight = B4DSouris.getPoint("Cliquez dans le coin inférieur droit de la zone de chat", new ImageIcon(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/fr/B4D/images/ChatBasDroite.png"))));
					Bot.MyConfiguration.chatFrame = new Rectangle(topLeft.x, topLeft.y, bottomRight.x - topLeft.x, bottomRight.y - topLeft.y);
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
			}
		});
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
		
		lblXY_ChatBar = new JLabel("X:Y");
		lblXY_ChatBar.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_ChatBar.setForeground(Color.DARK_GRAY);
		lblXY_ChatBar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXY_ChatBar.setBackground(Color.GRAY);
		lblXY_ChatBar.setBounds(0, 25, 150, 25);
		panel_Barre_Chat.add(lblXY_ChatBar);
		
		JButton button_1 = new JButton("Modifier");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Bot.MyConfiguration.chatBar = B4DSouris.getPoint("Cliquez dans la barre de chat", new ImageIcon(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/fr/B4D/images/ChatBarre.png"))));
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
			}
		});
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

		lblXY_Minimap = new JLabel("X:Y");
		lblXY_Minimap.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_Minimap.setForeground(Color.DARK_GRAY);
		lblXY_Minimap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXY_Minimap.setBackground(Color.GRAY);
		lblXY_Minimap.setBounds(0, 25, 150, 25);
		panel_Minimap.add(lblXY_Minimap);
		
		JButton button_2 = new JButton("Modifier");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Bot.MyConfiguration.minimap = B4DSouris.getPoint("Cliquez sur la minimap", new ImageIcon(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/fr/B4D/images/Minimap.png"))));
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_2.setBackground(UIManager.getColor("Button.background"));
		button_2.setBounds(5, 50, 140, 25);
		panel_Minimap.add(button_2);
		
		ActualiserInfos();
	}
	
	public void ActualiserInfos() {
		if(Bot.MyConfiguration.gameFrame != null) {
			this.lblXY_GameFrame.setText(Bot.MyConfiguration.gameFrame.x + ":" + Bot.MyConfiguration.gameFrame.y);
			this.lblLH_GameFrame.setText(Bot.MyConfiguration.gameFrame.width + "x" + Bot.MyConfiguration.gameFrame.height);
		}else {
			this.lblXY_GameFrame.setText("X:Y");
			this.lblLH_GameFrame.setText("LxH");
		}
		
		if(Bot.MyConfiguration.chatFrame != null) {
			this.lblXY_ChatFrame.setText(Bot.MyConfiguration.chatFrame.x + ":" + Bot.MyConfiguration.chatFrame.y);
			this.lblLH_ChatFrame.setText(Bot.MyConfiguration.chatFrame.width + "x" + Bot.MyConfiguration.chatFrame.height);
		}else {
			this.lblXY_ChatFrame.setText("X:Y");
			this.lblLH_ChatFrame.setText("LxH");
		}
		
		if(Bot.MyConfiguration.chatBar != null)
			this.lblXY_ChatBar.setText(Bot.MyConfiguration.chatBar.x + ":" + Bot.MyConfiguration.chatBar.y);
		else
			this.lblXY_ChatBar.setText("X:Y");
		
		if(Bot.MyConfiguration.minimap != null)
			this.lblXY_Minimap.setText(Bot.MyConfiguration.minimap.x + ":" + Bot.MyConfiguration.minimap.y);
		else
			this.lblXY_Minimap.setText("X:Y");
	}
}
