package fr.B4D.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Main {

	private JFrame frmBd;

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
		frmBd.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/fr/B4D/icones/Dofus_Rouge.png")));	//Defini l'icone
		frmBd.setTitle("B4D");									//Defini le nom de la fenetre
		frmBd.setBounds(100, 100, 548, 408);					//Defini les coordonnees
		frmBd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Defini l'action lors de l'appui sur la cloix
		frmBd.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int offset_x_tabbedPane = 5, offset_y_tabbedPane = 30, offset_x_Form = 40, offset_y_Form = 90;
				
				JTabbedPane tabbedPane = (JTabbedPane)e.getSource();		//Recupere le tableau de panneau
				JPanel panel = (JPanel)tabbedPane.getSelectedComponent();	//Recupere le panneau
								
				if(panel instanceof JPanel_Programme) {
					tabbedPane.setSize(((JPanel_Programme)panel).width + offset_x_tabbedPane, ((JPanel_Programme)panel).height + offset_y_tabbedPane);
					frmBd.setSize(((JPanel_Programme)panel).width + offset_x_Form, ((JPanel_Programme)panel).height + offset_y_Form);
				}
				else if(panel instanceof JPanel_Personnage) {
					tabbedPane.setSize(((JPanel_Personnage)panel).width + offset_x_tabbedPane, ((JPanel_Personnage)panel).height + offset_y_tabbedPane);
					frmBd.setSize(((JPanel_Personnage)panel).width + offset_x_Form, ((JPanel_Personnage)panel).height + offset_y_Form);
				}
				else if(panel instanceof JPanel_Reglage) {
					tabbedPane.setSize(((JPanel_Reglage)panel).width + offset_x_tabbedPane, ((JPanel_Reglage)panel).height + offset_y_tabbedPane);
					frmBd.setSize(((JPanel_Reglage)panel).width + offset_x_Form, ((JPanel_Reglage)panel).height + offset_y_Form);
				}
				else if(panel instanceof JPanel_Admin) {
					tabbedPane.setSize(((JPanel_Admin)panel).width + offset_x_tabbedPane, ((JPanel_Admin)panel).height + offset_y_tabbedPane);
					frmBd.setSize(((JPanel_Admin)panel).width + offset_x_Form, ((JPanel_Admin)panel).height + offset_y_Form);
				}
			}
		});
		tabbedPane.setBounds(10, 7, 515, 350);
		frmBd.getContentPane().add(tabbedPane);

		JPanel_Programme programme = new JPanel_Programme();
		tabbedPane.addTab("Programmes", new ImageIcon(Main.class.getResource("/fr/B4D/icones/Dofus_20x20.png")), programme, null);

		JPanel_Personnage personnage = new JPanel_Personnage();
		tabbedPane.addTab("Personnages", new ImageIcon(Main.class.getResource("/fr/B4D/icones/Personnage_20x20.png")), personnage, null);

		JPanel_Reglage reglage = new JPanel_Reglage();
		tabbedPane.addTab("Réglages", new ImageIcon(Main.class.getResource("/fr/B4D/icones/Reglages_20x20.png")), reglage, null);

		JPanel_Admin admin = new JPanel_Admin();
		tabbedPane.addTab("Admin", new ImageIcon(Main.class.getResource("/fr/B4D/icones/Drapeau_20x20.png")), admin, null);
	}
}
