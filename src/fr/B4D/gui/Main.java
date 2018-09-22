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
import fr.B4D.modules.B4DSouris;

import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.AWTException;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.Component;
import java.awt.event.MouseMotionAdapter;

public class Main {

	final static Color selectedTab = new Color(33,43,53);
	final static Color unSelectedTab = new Color(52,63,73);

	final static JPanel_Programme programPanel = new JPanel_Programme();
	final static JPanel_Personnage personPanel = new JPanel_Personnage();
	final static JPanel_Reglage settingPanel = new JPanel_Reglage();
	final static JPanel_Admin adminPanel = new JPanel_Admin();

	private Point offset;
	final static int offset_x_Form = 20, offset_y_Form = 70;	
	
	private JFrame frmBd;										//Fenetre B4D
	private JLabel lblProgrammes = new JLabel("Programmes");	//Bouton programmes
	private JLabel lblPersonnages = new JLabel("Personnages");	//Bouton personnages
	private JLabel lblReglages = new JLabel("R\u00E9glages");	//Bouton reglages
	private JLabel lblAdmin = new JLabel("Admin");				//Bouton admin

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		//EventQueue.invokeLater(new Runnable() {
			//public void run() {
				
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Main window = new Main();
					window.frmBd.setVisible(true);
					for(String arg:args)
						System.out.println(arg);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			//}
		//});
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
		frmBd.setUndecorated(true);
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
		
		JLabel lblReduce = new JLabel("-");
		lblReduce.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmBd.setState(Frame.ICONIFIED);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblReduce.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblReduce.setForeground(Color.WHITE);
			}
		});
		lblReduce.setHorizontalAlignment(SwingConstants.CENTER);
		lblReduce.setForeground(Color.WHITE);
		lblReduce.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblReduce.setBounds(595, 0, 30, 30);
		frmBd.getContentPane().add(lblReduce);		
		
		JLabel lblClose = new JLabel("x");
		lblClose.setVerticalAlignment(SwingConstants.BOTTOM);
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmBd.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblClose.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblClose.setForeground(Color.WHITE);
			}
		});
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setForeground(Color.WHITE);
		lblClose.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblClose.setBounds(625, 0, 30, 30);
		frmBd.getContentPane().add(lblClose);
		
		JLabel lblBd = new JLabel("Bot for Dofus");
		lblBd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				offset = frmBd.getMousePosition();
			}
		});
		lblBd.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point point = MouseInfo.getPointerInfo().getLocation();
				frmBd.setLocation(point.x - (int)offset.getX(), point.y - (int)offset.getY());
			}
		});
		
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
		
		JMenuItem itemImport = new JMenuItem("Importer", new ImageIcon(Main.class.getResource("/fr/B4D/icones/Import_20x20.png")));		
		itemImport.setMnemonic(KeyEvent.VK_O);
		itemImport.addActionListener(new ActionListener() {
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
		popupMenu.add(itemImport);
		
		JMenuItem itemExport = new JMenuItem("Exporter", new ImageIcon(Main.class.getResource("/fr/B4D/icones/Export_20x20.png")));
		itemExport.setMnemonic(KeyEvent.VK_S);
		itemExport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Bot.ConfigurationSerialization.SaveAs(Bot.MyConfiguration);
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		popupMenu.add(itemExport);		
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

