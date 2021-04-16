package fr.B4D.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import fr.B4D.bot.B4D;
import java.awt.SystemColor;

/**
 * The class {@code JPanel_Reglage} is a GUI allowing screen configuration and other settings.
 * 
 * @author Lucas
 *
 */
public class JPanel_Reglage extends JPanel {

	private static final long serialVersionUID = -4135111440537713705L;

	/**
	 * Width of the panel.
	 */
	public static final int WIDTH = 635;

	/**
	 * Height of the panel.
	 */
	public static final int HEIGHT = 215;

	private Point topLeft, bottomRight;
	
	private JLabel lblXY_GameFrame = new JLabel("X:Y");
	private JLabel lblLH_GameFrame = new JLabel("LxH");
	private JLabel lblXY_ChatMenu = new JLabel("X:Y");
	private JLabel lblXY_ChatBar = new JLabel("X:Y");
	private JLabel lblXY_Status = new JLabel("X:Y");
	private JLabel lblXY_Minimap = new JLabel("X:Y");

	private B4D b4d;
	
	/**
	 * Create the panel.
	 * @param b4d - Instance du bot.
	 */
	public JPanel_Reglage(B4D b4d) {
		this.b4d = b4d;
				
		setBackground(new Color(33,43,53));
		setLayout(null);
		setVisible(false);

		/****************/
		/** GAME FRAME **/
		/****************/
		
		JPanel panel_GameFrame = new JPanel();
		panel_GameFrame.setLayout(null);
		panel_GameFrame.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_GameFrame.setBackground(Color.LIGHT_GRAY);
		panel_GameFrame.setBounds(10, 10, 615, 35);
		add(panel_GameFrame);
		
		JLabel lbl_GameFrame = new JLabel("Zone de jeu");
		lbl_GameFrame.setOpaque(true);
		lbl_GameFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_GameFrame.setForeground(Color.WHITE);
		lbl_GameFrame.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_GameFrame.setBackground(new Color(46, 139, 87));
		lbl_GameFrame.setBounds(0, 0, 150, 35);
		panel_GameFrame.add(lbl_GameFrame);
		
		lblXY_GameFrame = new JLabel("X:Y");
		lblXY_GameFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_GameFrame.setForeground(Color.DARK_GRAY);
		lblXY_GameFrame.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXY_GameFrame.setBackground(Color.GRAY);
		lblXY_GameFrame.setBounds(150, 0, 150, 35);
		panel_GameFrame.add(lblXY_GameFrame);
		
		lblLH_GameFrame = new JLabel("LxH");
		lblLH_GameFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblLH_GameFrame.setForeground(Color.DARK_GRAY);
		lblLH_GameFrame.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLH_GameFrame.setBackground(Color.GRAY);
		lblLH_GameFrame.setBounds(300, 0, 150, 35);
		panel_GameFrame.add(lblLH_GameFrame);
		
		JButton button_GameFrame = new JButton("Modifier");
		button_GameFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B4D.mouse.getPoints(
					"Cliquez dans le coin suppérieur gauche",
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(JFrame_B4D.class.getResource("/fr/B4D/images/ZoneHautGauche.png"))),
					new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							topLeft = MouseInfo.getPointerInfo().getLocation();
						}
					},
					"Cliquez dans le coin inférieur droit",
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(JFrame_B4D.class.getResource("/fr/B4D/images/ZoneBasDroite.png"))),
					new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							bottomRight = MouseInfo.getPointerInfo().getLocation();
							
							int width = bottomRight.x - topLeft.x;
							int height = bottomRight.y - topLeft.y;
							int realWidth = height * 1157 / 927;
							b4d.getConfiguration().setGameFrame(new Rectangle((width - realWidth)/2, topLeft.y, realWidth, height));
							
							ActualiserInfos();
						}
					});
			}
		});
		button_GameFrame.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_GameFrame.setBackground(UIManager.getColor("Button.background"));
		button_GameFrame.setBounds(470, 5, 140, 25);
		panel_GameFrame.add(button_GameFrame);

		/***************/
		/** CHAT MENU **/
		/***************/
		
		JPanel panel_ChatMenu = new JPanel();
		panel_ChatMenu.setLayout(null);
		panel_ChatMenu.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_ChatMenu.setBackground(Color.LIGHT_GRAY);
		panel_ChatMenu.setBounds(10, 50, 615, 35);
		add(panel_ChatMenu);
		
		JLabel lbl_ChatMenu = new JLabel("Menu du chat");
		lbl_ChatMenu.setOpaque(true);
		lbl_ChatMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ChatMenu.setForeground(Color.WHITE);
		lbl_ChatMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_ChatMenu.setBackground(new Color(46, 139, 87));
		lbl_ChatMenu.setBounds(0, 0, 150, 35);
		panel_ChatMenu.add(lbl_ChatMenu);
		
		lblXY_ChatMenu = new JLabel("X:Y");
		lblXY_ChatMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_ChatMenu.setForeground(Color.DARK_GRAY);
		lblXY_ChatMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXY_ChatMenu.setBackground(Color.GRAY);
		lblXY_ChatMenu.setBounds(150, 0, 150, 35);
		panel_ChatMenu.add(lblXY_ChatMenu);
		
		JButton button_ChatMenu = new JButton("Modifier");
		button_ChatMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B4D.mouse.getPoint(
					"Cliquez sur le menu du chat",
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(JFrame_B4D.class.getResource("/fr/B4D/images/ChatMenu.png"))),
					new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							Point chatMenu = MouseInfo.getPointerInfo().getLocation();
							b4d.getConfiguration().setChatMenu(chatMenu);
							Point status = b4d.getConfiguration().getStatus();
							
							if(status != null) {
								Point chatBar = new Point((chatMenu.x + status.x)/2,(chatMenu.y + status.y)/2);
								b4d.getConfiguration().setChatBar(chatBar);
							}
							ActualiserInfos();
						}
					});	
			}
		});
		button_ChatMenu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_ChatMenu.setBackground(UIManager.getColor("Button.background"));
		button_ChatMenu.setBounds(470, 5, 140, 25);
		panel_ChatMenu.add(button_ChatMenu);
		
		/**************/
		/** CHAT BAR **/
		/**************/
		
		JPanel panel_ChatBar = new JPanel();
		panel_ChatBar.setLayout(null);
		panel_ChatBar.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_ChatBar.setBackground(Color.LIGHT_GRAY);
		panel_ChatBar.setBounds(10, 90, 615, 35);
		add(panel_ChatBar);
		
		JLabel lbl_ChatBar = new JLabel("Barre de chat");
		lbl_ChatBar.setOpaque(true);
		lbl_ChatBar.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ChatBar.setForeground(Color.WHITE);
		lbl_ChatBar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_ChatBar.setBackground(new Color(46, 139, 87));
		lbl_ChatBar.setBounds(0, 0, 150, 35);
		panel_ChatBar.add(lbl_ChatBar);
		
		lblXY_ChatBar = new JLabel("X:Y");
		lblXY_ChatBar.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_ChatBar.setForeground(Color.DARK_GRAY);
		lblXY_ChatBar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXY_ChatBar.setBackground(Color.GRAY);
		lblXY_ChatBar.setBounds(150, 0, 150, 35);
		panel_ChatBar.add(lblXY_ChatBar);
		
		JButton button = new JButton("Modifier");
		button.setEnabled(false);
		button.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button.setBackground(SystemColor.menu);
		button.setBounds(470, 5, 140, 25);
		panel_ChatBar.add(button);
		
		/************/
		/** STATUS **/
		/************/
		
		JPanel panel_Status = new JPanel();
		panel_Status.setLayout(null);
		panel_Status.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Status.setBackground(Color.LIGHT_GRAY);
		panel_Status.setBounds(10, 130, 615, 35);
		add(panel_Status);
		
		JLabel lbl_Status = new JLabel("Status");
		lbl_Status.setOpaque(true);
		lbl_Status.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Status.setForeground(Color.WHITE);
		lbl_Status.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_Status.setBackground(new Color(46, 139, 87));
		lbl_Status.setBounds(0, 0, 150, 35);
		panel_Status.add(lbl_Status);
		
		lblXY_Status = new JLabel("X:Y");
		lblXY_Status.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_Status.setForeground(Color.DARK_GRAY);
		lblXY_Status.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXY_Status.setBackground(Color.GRAY);
		lblXY_Status.setBounds(150, 0, 150, 35);
		panel_Status.add(lblXY_Status);
		
		JButton button_Status = new JButton("Modifier");
		button_Status.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 B4D.mouse.getPoint(
					"Cliquez sur le status du joueur",
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(JFrame_B4D.class.getResource("/fr/B4D/images/Status.png"))),
					new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							Point status = MouseInfo.getPointerInfo().getLocation();
							b4d.getConfiguration().setStatus(status);
							Point chatMenu = b4d.getConfiguration().getChatMenu();
							
							if(chatMenu != null) {
								Point chatBar = new Point((chatMenu.x + status.x)/2,(chatMenu.y + status.y)/2);
								b4d.getConfiguration().setChatBar(chatBar);
							}
							ActualiserInfos();
						}
					});
			}
		});
		button_Status.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_Status.setBackground(UIManager.getColor("Button.background"));
		button_Status.setBounds(470, 5, 140, 25);
		panel_Status.add(button_Status);

		/*************/
		/** MINIMAP **/
		/*************/
		
		JPanel panel_Minimap = new JPanel();
		panel_Minimap.setBounds(10, 170, 615, 35);
		add(panel_Minimap);
		panel_Minimap.setLayout(null);
		panel_Minimap.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Minimap.setBackground(Color.LIGHT_GRAY);
		
		JLabel lbl_Minimap = new JLabel("Minimap");
		lbl_Minimap.setOpaque(true);
		lbl_Minimap.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Minimap.setForeground(Color.WHITE);
		lbl_Minimap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_Minimap.setBackground(new Color(46, 139, 87));
		lbl_Minimap.setBounds(0, 0, 150, 35);
		panel_Minimap.add(lbl_Minimap);

		lblXY_Minimap = new JLabel("X:Y");
		lblXY_Minimap.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_Minimap.setForeground(Color.DARK_GRAY);
		lblXY_Minimap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXY_Minimap.setBackground(Color.GRAY);
		lblXY_Minimap.setBounds(150, 0, 150, 35);
		panel_Minimap.add(lblXY_Minimap);
		
		JButton button_Minimap = new JButton("Modifier");
		button_Minimap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B4D.mouse.getPoint(
					"Cliquez sur la minimap",
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(JFrame_B4D.class.getResource("/fr/B4D/images/Minimap.png"))),
					new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							b4d.getConfiguration().setMinimap(MouseInfo.getPointerInfo().getLocation());
							ActualiserInfos();
						}
					});
			}
		});
		button_Minimap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		button_Minimap.setBackground(UIManager.getColor("Button.background"));
		button_Minimap.setBounds(470, 5, 140, 25);
		panel_Minimap.add(button_Minimap);
			
		ActualiserInfos();
	}

	/**
	 * Refresh the informatons displayed on the panel.
	 */
	public void ActualiserInfos() {
		if(b4d.getConfiguration().getGameFrame() != null) {
			this.lblXY_GameFrame.setText(b4d.getConfiguration().getGameFrame().x + ":" + b4d.getConfiguration().getGameFrame().y);
			this.lblLH_GameFrame.setText(b4d.getConfiguration().getGameFrame().width + "x" + b4d.getConfiguration().getGameFrame().height);
		}else {
			this.lblXY_GameFrame.setText("X:Y");
			this.lblLH_GameFrame.setText("LxH");
		}
		
		if(b4d.getConfiguration().getChatMenu() != null) 
			this.lblXY_ChatMenu.setText(b4d.getConfiguration().getChatMenu().x + ":" + b4d.getConfiguration().getChatMenu().y);
		else 
			this.lblXY_ChatMenu.setText("X:Y");
		
		if(b4d.getConfiguration().getChatBar() != null)
			this.lblXY_ChatBar.setText(b4d.getConfiguration().getChatBar().x + ":" + b4d.getConfiguration().getChatBar().y);
		else
			this.lblXY_ChatBar.setText("X:Y");
		
		if(b4d.getConfiguration().getStatus() != null)
			this.lblXY_Status.setText(b4d.getConfiguration().getStatus().x + ":" + b4d.getConfiguration().getStatus().y);
		else
			this.lblXY_Status.setText("X:Y");
		
		if(b4d.getConfiguration().getMinimap() != null)
			this.lblXY_Minimap.setText(b4d.getConfiguration().getMinimap().x + ":" + b4d.getConfiguration().getMinimap().y);
		else
			this.lblXY_Minimap.setText("X:Y");
	}
}
