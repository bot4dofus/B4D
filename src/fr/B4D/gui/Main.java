package fr.B4D.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.SwingConstants;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Main {

	final static Color selectedTab = new Color(33,43,53);
	final static Color unSelectedTab = new Color(52,63,73);

	final static JPanel_Programme programme = new JPanel_Programme();
	final static JPanel_Personnage personnage = new JPanel_Personnage();
	final static JPanel_Reglage reglage = new JPanel_Reglage();
	final static JPanel_Admin admin = new JPanel_Admin();
	
	final static int offset_x_Form = 26, offset_y_Form = 105;
	
	private JFrame frmBd;										//Fenetre B4D
	private JLabel lblProgrammes = new JLabel("Programmes");	//Bouton programmes
	private JLabel lblPersonnages = new JLabel("Personnages");	//Bouton personnages
	private JLabel lblReglages = new JLabel("R\u00E9glages");	//Bouton reglages
	private JLabel lblAdmin = new JLabel("Admin");				//Bouton admin

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Main window = new Main();
					window.frmBd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBd = new JFrame();
		frmBd.setResizable(false);
		frmBd.setBackground(Color.GRAY);
		frmBd.getContentPane().setBackground(unSelectedTab);
		frmBd.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/fr/B4D/gui/icones/Dofus_Rouge.png")));	//Defini l'icone
		frmBd.setTitle("B4D");									//Defini le nom de la fenetre
		frmBd.setBounds(100, 100, 807, 541);					//Defini les coordonnees
		frmBd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Defini l'action lors de l'appui sur la cloix
		frmBd.getContentPane().setLayout(null);

		programme.setBounds(10, 60, 635, 235);
		frmBd.getContentPane().add(programme);
		personnage.setBounds(10, 60, personnage.width, personnage.height);
		frmBd.getContentPane().add(personnage);
		reglage.setBounds(10, 60, reglage.width, reglage.height);
		frmBd.getContentPane().add(reglage);
		admin.setBounds(10, 60, admin.width, admin.height);
		frmBd.getContentPane().add(admin);
		
		JLabel lblBd = new JLabel("Bot for Dofus");
		lblBd.setOpaque(true);
		lblBd.setBackground(new Color(222,118,56));
		lblBd.setForeground(Color.WHITE);
		lblBd.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblBd.setBounds(0, 0, 655, 30);
		lblBd.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/fr/B4D/gui/icones/Dofus.png"))));
		frmBd.getContentPane().add(lblBd);
		
		/** PROGRAMMES PANEL **/
		lblProgrammes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changerPanel(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!programme.isVisible())
					lblProgrammes.setBackground(selectedTab);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!programme.isVisible())
					lblProgrammes.setBackground(unSelectedTab);
			}
		});
		lblProgrammes.setOpaque(true);
		lblProgrammes.setBackground(selectedTab);
		lblProgrammes.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgrammes.setForeground(Color.WHITE);
		lblProgrammes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProgrammes.setBounds(10, 30, 159, 30);
		frmBd.getContentPane().add(lblProgrammes);
		
		/** PERSONNAGES PANEL **/
		lblPersonnages.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changerPanel(1);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!personnage.isVisible())
					lblPersonnages.setBackground(selectedTab);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!personnage.isVisible())
					lblPersonnages.setBackground(unSelectedTab);
			}
		});		
		lblPersonnages.setOpaque(true);
		lblPersonnages.setBackground(unSelectedTab);
		lblPersonnages.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonnages.setForeground(Color.WHITE);
		lblPersonnages.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPersonnages.setBounds(169, 30, 159, 30);
		frmBd.getContentPane().add(lblPersonnages);
		
		/** REGLAGES PANEL **/
		lblReglages.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changerPanel(2);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!reglage.isVisible())
					lblReglages.setBackground(selectedTab);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!reglage.isVisible())
					lblReglages.setBackground(unSelectedTab);
			}
		});		
		lblReglages.setOpaque(true);
		lblReglages.setBackground(unSelectedTab);
		lblReglages.setHorizontalAlignment(SwingConstants.CENTER);
		lblReglages.setForeground(Color.WHITE);
		lblReglages.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblReglages.setBounds(328, 30, 159, 30);
		frmBd.getContentPane().add(lblReglages);
		
		/** ADMIN PANEL **/
		lblAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changerPanel(3);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!admin.isVisible())
					lblAdmin.setBackground(selectedTab);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!admin.isVisible())
					lblAdmin.setBackground(unSelectedTab);
			}
		});		
		lblAdmin.setOpaque(true);
		lblAdmin.setBackground(unSelectedTab);
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setForeground(Color.WHITE);
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAdmin.setBounds(487, 30, 158, 30);
		frmBd.getContentPane().add(lblAdmin);
		
		changerPanel(0);
	}

	  /************/
	 /* METHODES */
	/************/
	
	private void changerPanel(int index) {
		lblProgrammes.setBackground(unSelectedTab);
		lblPersonnages.setBackground(unSelectedTab);
		lblReglages.setBackground(unSelectedTab);
		lblAdmin.setBackground(unSelectedTab);		
		programme.setVisible(false);
		personnage.setVisible(false);
		reglage.setVisible(false);
		admin.setVisible(false);
		
		switch(index) {
		case 0:
			lblProgrammes.setBackground(selectedTab);
			frmBd.setSize(programme.width + offset_x_Form, programme.height + offset_y_Form);
			programme.setVisible(true);
			break;
		case 1:
			lblPersonnages.setBackground(selectedTab);
			frmBd.setSize(personnage.width + offset_x_Form, personnage.height + offset_y_Form);
			personnage.setVisible(true);
			break;
		case 2:
			lblReglages.setBackground(selectedTab);
			frmBd.setSize(reglage.width + offset_x_Form, reglage.height + offset_y_Form);
			reglage.setVisible(true);
			break;
		case 3:
			lblAdmin.setBackground(selectedTab);
			frmBd.setSize(admin.width + offset_x_Form, admin.height + offset_y_Form);
			admin.setVisible(true);
			break;
		}
		
	}
}
