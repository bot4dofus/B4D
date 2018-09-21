package fr.B4D.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import fr.B4D.classes.transports.B4DGraph;
import fr.B4D.classes.Bot;
import fr.B4D.classes.Configuration;
import fr.B4D.classes.transports.B4DEdge;
import fr.B4D.enu.TransportType;
import fr.B4D.modules.B4DOther;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import javax.swing.SwingConstants;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class Main {

	final static Color selectedTab = new Color(33,43,53);
	final static Color unSelectedTab = new Color(52,63,73);

	final static JPanel_Programme programPanel = new JPanel_Programme();
	final static JPanel_Personnage personPanel = new JPanel_Personnage();
	final static JPanel_Reglage settingPanel = new JPanel_Reglage();
	final static JPanel_Admin adminPanel = new JPanel_Admin();
	
	final static int offset_x_Form = 26, offset_y_Form = 105;
	protected static final String SerialisationConfiguration = null;
	
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
					
					for(String arg:args)
						System.out.println(arg);
					
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
		test();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBd = new JFrame();
		frmBd.setResizable(false);
		frmBd.setBackground(Color.GRAY);
		frmBd.getContentPane().setBackground(unSelectedTab);
		frmBd.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/fr/B4D/icones/Dofus_Rouge.png")));	//Defini l'icone
		frmBd.setTitle("B4D");									//Defini le nom de la fenetre
		frmBd.setBounds(100, 100, 807, 541);					//Defini les coordonnees
		frmBd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Defini l'action lors de l'appui sur la cloix
		frmBd.getContentPane().setLayout(null);

		programPanel.setBounds(10, 60, 635, 235);
		frmBd.getContentPane().add(programPanel);
		personPanel.setBounds(10, 60, personPanel.width, personPanel.height);
		frmBd.getContentPane().add(personPanel);
		settingPanel.setBounds(10, 60, settingPanel.width, settingPanel.height);
		frmBd.getContentPane().add(settingPanel);
		adminPanel.setBounds(10, 60, adminPanel.width, adminPanel.height);
		frmBd.getContentPane().add(adminPanel);
		
		JLabel lblBd = new JLabel("Bot for Dofus");
		lblBd.setOpaque(true);
		lblBd.setBackground(new Color(222,118,56));
		lblBd.setForeground(Color.WHITE);
		lblBd.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblBd.setBounds(0, 0, 655, 30);
		lblBd.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/fr/B4D/icones/Dofus.png"))));
		frmBd.getContentPane().add(lblBd);
		
		/** PROGRAMMES PANEL **/
		lblProgrammes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changerPanel(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(!programPanel.isVisible())
					lblProgrammes.setBackground(selectedTab);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!programPanel.isVisible())
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
				if(!personPanel.isVisible())
					lblPersonnages.setBackground(selectedTab);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!personPanel.isVisible())
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
				if(!settingPanel.isVisible())
					lblReglages.setBackground(selectedTab);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!settingPanel.isVisible())
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
				if(!adminPanel.isVisible())
					lblAdmin.setBackground(selectedTab);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(!adminPanel.isVisible())
					lblAdmin.setBackground(unSelectedTab);
			}
		});
		try {
			if(!B4DOther.getMacAdress().equals(Bot.AdminMacAdresse))
				lblAdmin.setVisible(false);
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
	
		lblAdmin.setOpaque(true);
		lblAdmin.setBackground(unSelectedTab);
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setForeground(Color.WHITE);
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAdmin.setBounds(487, 30, 158, 30);
		frmBd.getContentPane().add(lblAdmin);
		
		JPopupMenu popupMenu = new JPopupMenu();
		
		JMenuItem itemOpen = new JMenuItem("Ouvrir");
		itemOpen.setMnemonic(KeyEvent.VK_O);
		itemOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Configuration configuration = Bot.ConfigurationSerialization.Open();
					if (configuration != null)
						Bot.MyConfiguration = configuration;						
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();						
				} catch (IOException e1) {
					System.out.println("Impossible de lire le fichier, fichier corrompu.");
					e1.printStackTrace();
				}
			}
		});
		popupMenu.add(itemOpen);
		
		JMenuItem itemSave = new JMenuItem("Enregistrer");
		itemSave.setMnemonic(KeyEvent.VK_S);
		itemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Bot.ConfigurationSerialization.Save(Bot.MyConfiguration);
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		popupMenu.add(itemSave);
		
		JMenuItem itemSaveAs = new JMenuItem("Enregistrer Sous");
		itemSaveAs.setMnemonic(KeyEvent.VK_S);
		itemSaveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Bot.ConfigurationSerialization.SaveAs(Bot.MyConfiguration);
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		popupMenu.add(itemSaveAs);
		
		addPopup(frmBd, popupMenu);
		
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
		programPanel.setVisible(false);
		personPanel.setVisible(false);
		settingPanel.setVisible(false);
		adminPanel.setVisible(false);
		
		switch(index) {
		case 0:
			lblProgrammes.setBackground(selectedTab);
			frmBd.setSize(programPanel.width + offset_x_Form, programPanel.height + offset_y_Form);
			programPanel.setVisible(true);
			break;
		case 1:
			lblPersonnages.setBackground(selectedTab);
			frmBd.setSize(personPanel.width + offset_x_Form, personPanel.height + offset_y_Form);
			personPanel.setVisible(true);
			break;
		case 2:
			lblReglages.setBackground(selectedTab);
			frmBd.setSize(settingPanel.width + offset_x_Form, settingPanel.height + offset_y_Form);
			settingPanel.setVisible(true);
			break;
		case 3:
			lblAdmin.setBackground(selectedTab);
			frmBd.setSize(adminPanel.width + offset_x_Form, adminPanel.height + offset_y_Form);
			adminPanel.setVisible(true);
			break;
		}	
	}
	
	private void test() {
		
//		B4DGraph graph = new B4DGraph();
//		graph.addVertex(new Point(1,1));
//		graph.addVertex(new Point(2,2));
//		graph.addVertex(new Point(3,3));
//
//	    graph.addEdge(new Point(1,1), new Point(3,3), 6, TypeDeTransport.Marche);
//	    graph.addEdge(new Point(1,1), new Point(2,2), 3, TypeDeTransport.Zaap);
//	    graph.addEdge(new Point(2,2), new Point(3,3), 2, TypeDeTransport.Zaap);
//		
//	    List<B4DEdge> shortestPath = graph.getPath(new Point(1,1), new Point(3,3));
//	    System.out.println(shortestPath);

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

