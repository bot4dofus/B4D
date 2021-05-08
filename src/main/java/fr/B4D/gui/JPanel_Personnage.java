package fr.B4D.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import fr.B4D.bot.B4D;
import fr.B4D.bot.Person;
import fr.B4D.dofus.server.Server;
import fr.B4D.dofus.server.ServerEnum;
import fr.B4D.transport.transports.Zaap;

/**
 * The class {@code JPanel_Personnage} is a GUI allowing persons configurations.
 * 
 * @author Lucas
 *
 */
public class JPanel_Personnage extends JPanel {

	private static final long serialVersionUID = 8907893091716626123L;
	
	/**
	 * Width of the panel.
	 */
	public static final int WIDTH = 635;
	
	/**
	 * Height of the panel.
	 */
	public static final int HEIGHT = 310;
	
	private Vector<Vector<String>> dataTable;
	private JTable table;
	private JLabel lblXY_Rappel;
	private JLabel lblXY_Bonta;
	private JLabel lblXY_Brakmar;
	private JLabel lblXY_Sort;

	private JButton btnModifierRappel;
	private JComboBox<String> comboBox_Zaaps;
	private JLabel lblPosition_Bonta;
	private JButton btnModifierBonta;
	private JLabel lblPosition_Brakmar;
	private JButton btnModifierBrakmar;
	private JButton btnModifierSort;

	private B4D b4d;
	
	/**
	 * Create the panel.
	 * @param b4d - Instance du bot.
	 */
	public JPanel_Personnage(B4D b4d) {
		this.b4d = b4d;
		
		setBackground(new Color(33,43,53));
		setLayout(null);
		setVisible(false);
		
		JComboBox<Server> comboBox_server = new JComboBox<Server>();
		
		for(ServerEnum server:ServerEnum.values())
			comboBox_server.addItem(server.getValue());
		
		dataTable = new Vector<Vector<String>>();
		Vector<String> colonnes = new Vector<String>(Arrays.asList(new String[] {"Nom de compte","Mot de passe","Serveur","Pseudo"}));
		table = new JTable(dataTable, colonnes);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableColumn serverColumn = table.getColumnModel().getColumn(2);
		serverColumn.setCellEditor(new DefaultCellEditor(comboBox_server));
		table.getModel().addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent evt){
				switch(evt.getColumn()){
					case 0:
						b4d.getTeam().get(evt.getFirstRow()).setAccount(table.getModel().getValueAt(evt.getFirstRow(), evt.getColumn()).toString());
						break;
					case 1:
						b4d.getTeam().get(evt.getFirstRow()).setPassword(table.getModel().getValueAt(evt.getFirstRow(), evt.getColumn()).toString());
						break;
					case 2:
						b4d.getTeam().get(evt.getFirstRow()).setServer((Server) table.getModel().getValueAt(evt.getFirstRow(), evt.getColumn()));
						break;
					case 3:
						b4d.getTeam().get(evt.getFirstRow()).setPseudo(table.getModel().getValueAt(evt.getFirstRow(), evt.getColumn()).toString());
						break;
				}
				ActualiserInfos();
			}
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					if(b4d.getConfiguration().getGameFrame() != null) {
						btnModifierRappel.setEnabled(true);
						comboBox_Zaaps.setEnabled(true);
						btnModifierBonta.setEnabled(true);
						btnModifierBrakmar.setEnabled(true);
						btnModifierSort.setEnabled(true);
					}
					ActualiserInfos(b4d.getTeam().get(table.getSelectedRow()));
				}
			}
		});
		
		JButton btnNouveau = new JButton("Nouveau");
		btnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b4d.getTeam().add(new Person());
				ActualiserInfos();
			}
		});
		btnNouveau.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNouveau.setBackground(SystemColor.menu);
		btnNouveau.setBounds(10, 10, 150, 25);
		add(btnNouveau);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					b4d.getTeam().remove(table.getSelectedRow());
					ActualiserInfos();
				}
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSupprimer.setBackground(SystemColor.menu);
		btnSupprimer.setBounds(165, 10, 150, 25);
		add(btnSupprimer);
		
		JButton btnJouerCePersonnage = new JButton("Jouer ce personnage");
		btnJouerCePersonnage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					Person personnage = b4d.getTeam().get(table.getSelectedRow());
					b4d.getTeam().remove(personnage);
					b4d.getTeam().add(0,personnage);
					table.setRowSelectionInterval(0, 0);
					ActualiserInfos();
				}
			}
		});
		btnJouerCePersonnage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnJouerCePersonnage.setBackground(SystemColor.menu);
		btnJouerCePersonnage.setBounds(320, 10, 305, 25);
		add(btnJouerCePersonnage);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 40, 615, 125);
		add(scrollPane);
		
		JPanel panel_Potion_Rappel = new JPanel();
		panel_Potion_Rappel.setForeground(Color.WHITE);
		panel_Potion_Rappel.setBackground(Color.LIGHT_GRAY);
		panel_Potion_Rappel.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Potion_Rappel.setBounds(10, 170, 150, 130);
		panel_Potion_Rappel.setLayout(null);
		add(panel_Potion_Rappel);
		
		JLabel lblPotionDeRappel = new JLabel("Potion de Rappel");
		lblPotionDeRappel.setOpaque(true);
		lblPotionDeRappel.setBackground(new Color(46, 139, 87));
		lblPotionDeRappel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPotionDeRappel.setForeground(Color.WHITE);
		lblPotionDeRappel.setHorizontalAlignment(SwingConstants.CENTER);
		lblPotionDeRappel.setBounds(0, 0, 150, 25);
		panel_Potion_Rappel.add(lblPotionDeRappel);
		
		lblXY_Rappel = new JLabel("X:Y");
		lblXY_Rappel.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_Rappel.setForeground(Color.DARK_GRAY);
		lblXY_Rappel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXY_Rappel.setBackground(Color.GRAY);
		lblXY_Rappel.setBounds(0, 25, 150, 25);
		panel_Potion_Rappel.add(lblXY_Rappel);
		
		btnModifierRappel = new JButton("Modifier");
		btnModifierRappel.setEnabled(false);
		btnModifierRappel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B4D.mouse.getPoint(
					"Cliquez sur votre potion de rappel",
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(JFrame_B4D.class.getResource("/fr/B4D/images/PotionRappel.png"))),
					new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							Person person = b4d.getTeam().get(table.getSelectedRow());
							person.getBoosterPotion().getTransport().setPositionF(B4D.converter.toPointF(MouseInfo.getPointerInfo().getLocation()));
							ActualiserInfos(person);
						}
					});
			}
		});
		btnModifierRappel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnModifierRappel.setBackground(UIManager.getColor("Button.background"));
		btnModifierRappel.setBounds(5, 50, 140, 25);
		panel_Potion_Rappel.add(btnModifierRappel);
		
		JLabel lblDestination_Rappel = new JLabel("Destination :");
		lblDestination_Rappel.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestination_Rappel.setForeground(Color.DARK_GRAY);
		lblDestination_Rappel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDestination_Rappel.setBackground(Color.GRAY);
		lblDestination_Rappel.setBounds(0, 75, 150, 25);
		panel_Potion_Rappel.add(lblDestination_Rappel);
		
		comboBox_Zaaps = new JComboBox<String>();
		Zaap.getAll().stream().forEach(z -> this.comboBox_Zaaps.addItem(z.getName()));
		comboBox_Zaaps.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if(table.getSelectedRow() != -1) {
					try {
						b4d.getTeam().get(table.getSelectedRow()).getBoosterPotion().setDestination(Zaap.getZaap(comboBox_Zaaps.getSelectedItem().toString()).getPosition());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
		    	}
		    }
		});
		comboBox_Zaaps.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_Zaaps.setBackground(UIManager.getColor("Button.background"));
		comboBox_Zaaps.setBounds(5, 100, 140, 25);
		panel_Potion_Rappel.add(comboBox_Zaaps);
		
		JPanel panel_Potion_Bonta = new JPanel();
		panel_Potion_Bonta.setForeground(Color.WHITE);
		panel_Potion_Bonta.setLayout(null);
		panel_Potion_Bonta.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Potion_Bonta.setBackground(Color.LIGHT_GRAY);
		panel_Potion_Bonta.setBounds(165, 170, 150, 130);
		add(panel_Potion_Bonta);
		
		JLabel lblPotionDeBonta = new JLabel("Potion de Bonta");
		lblPotionDeBonta.setOpaque(true);
		lblPotionDeBonta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPotionDeBonta.setForeground(Color.WHITE);
		lblPotionDeBonta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPotionDeBonta.setBackground(new Color(46, 139, 87));
		lblPotionDeBonta.setBounds(0, 0, 150, 25);
		panel_Potion_Bonta.add(lblPotionDeBonta);
		
		lblXY_Bonta = new JLabel("X:Y");
		lblXY_Bonta.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_Bonta.setForeground(Color.DARK_GRAY);
		lblXY_Bonta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXY_Bonta.setBackground(Color.GRAY);
		lblXY_Bonta.setBounds(0, 25, 150, 25);
		panel_Potion_Bonta.add(lblXY_Bonta);
		
		btnModifierBonta = new JButton("Modifier");
		btnModifierBonta.setEnabled(false);		
		btnModifierBonta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B4D.mouse.getPoint(
					"Cliquez sur votre potion de bonta",
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(JFrame_B4D.class.getResource("/fr/B4D/images/PotionBonta.png"))),
					new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							Person person = b4d.getTeam().get(table.getSelectedRow());
							person.getBontaPotion().getTransport().setPositionF(B4D.converter.toPointF(MouseInfo.getPointerInfo().getLocation()));
							ActualiserInfos(person);
						}
					});
			}
		});
		btnModifierBonta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnModifierBonta.setBackground(UIManager.getColor("Button.background"));
		btnModifierBonta.setBounds(5, 50, 140, 25);
		panel_Potion_Bonta.add(btnModifierBonta);
		
		JLabel lblDestination_Bonta = new JLabel("Destination :");
		lblDestination_Bonta.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestination_Bonta.setForeground(Color.DARK_GRAY);
		lblDestination_Bonta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDestination_Bonta.setBackground(Color.GRAY);
		lblDestination_Bonta.setBounds(0, 75, 150, 25);
		panel_Potion_Bonta.add(lblDestination_Bonta);
		
		lblPosition_Bonta = new JLabel("-33:-56");
		lblPosition_Bonta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosition_Bonta.setForeground(Color.DARK_GRAY);
		lblPosition_Bonta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPosition_Bonta.setBounds(0, 100, 150, 25);
		panel_Potion_Bonta.add(lblPosition_Bonta);
		
		JPanel panel_Potion_Brakmar = new JPanel();
		panel_Potion_Brakmar.setForeground(Color.WHITE);
		panel_Potion_Brakmar.setLayout(null);
		panel_Potion_Brakmar.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Potion_Brakmar.setBackground(Color.LIGHT_GRAY);
		panel_Potion_Brakmar.setBounds(320, 170, 150, 130);
		add(panel_Potion_Brakmar);
		
		JLabel lblPotionDeBrakmar = new JLabel("Potion de Brakmar");
		lblPotionDeBrakmar.setOpaque(true);
		lblPotionDeBrakmar.setHorizontalAlignment(SwingConstants.CENTER);
		lblPotionDeBrakmar.setForeground(Color.WHITE);
		lblPotionDeBrakmar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPotionDeBrakmar.setBackground(new Color(46, 139, 87));
		lblPotionDeBrakmar.setBounds(0, 0, 150, 25);
		panel_Potion_Brakmar.add(lblPotionDeBrakmar);
		
		lblXY_Brakmar = new JLabel("X:Y");
		lblXY_Brakmar.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_Brakmar.setForeground(Color.DARK_GRAY);
		lblXY_Brakmar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXY_Brakmar.setBackground(Color.GRAY);
		lblXY_Brakmar.setBounds(0, 25, 150, 25);
		panel_Potion_Brakmar.add(lblXY_Brakmar);

		btnModifierBrakmar = new JButton("Modifier");
		btnModifierBrakmar.setEnabled(false);		
		btnModifierBrakmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B4D.mouse.getPoint(
					"Cliquez sur votre potion de brakmar",
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(JFrame_B4D.class.getResource("/fr/B4D/images/PotionBrakmar.png"))),
					new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							Person person = b4d.getTeam().get(table.getSelectedRow());
							person.getBrakmarPotion().getTransport().setPositionF(B4D.converter.toPointF(MouseInfo.getPointerInfo().getLocation()));
							ActualiserInfos(person);
						}
					});
			}
		});
		btnModifierBrakmar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnModifierBrakmar.setBackground(UIManager.getColor("Button.background"));
		btnModifierBrakmar.setBounds(5, 50, 140, 25);
		panel_Potion_Brakmar.add(btnModifierBrakmar);
		
		JLabel lblDestination_Brakmar = new JLabel("Destination :");
		lblDestination_Brakmar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestination_Brakmar.setForeground(Color.DARK_GRAY);
		lblDestination_Brakmar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDestination_Brakmar.setBackground(Color.GRAY);
		lblDestination_Brakmar.setBounds(0, 75, 150, 25);
		panel_Potion_Brakmar.add(lblDestination_Brakmar);
		
		lblPosition_Brakmar = new JLabel("-26:36");
		lblPosition_Brakmar.setBounds(0, 100, 150, 25);
		lblPosition_Brakmar.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosition_Brakmar.setForeground(Color.DARK_GRAY);
		lblPosition_Brakmar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPosition_Brakmar.setBackground(Color.GRAY);
		panel_Potion_Brakmar.add(lblPosition_Brakmar);
		
		JPanel panel_Sort = new JPanel();
		panel_Sort.setForeground(Color.WHITE);
		panel_Sort.setLayout(null);
		panel_Sort.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Sort.setBackground(Color.LIGHT_GRAY);
		panel_Sort.setBounds(475, 170, 150, 80);
		add(panel_Sort);
		
		JLabel lblSortDePorte = new JLabel("Sort de port\u00E9e");
		lblSortDePorte.setOpaque(true);
		lblSortDePorte.setHorizontalAlignment(SwingConstants.CENTER);
		lblSortDePorte.setForeground(Color.WHITE);
		lblSortDePorte.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSortDePorte.setBackground(new Color(46, 139, 87));
		lblSortDePorte.setBounds(0, 0, 150, 25);
		panel_Sort.add(lblSortDePorte);
		
		lblXY_Sort = new JLabel("X:Y");
		lblXY_Sort.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_Sort.setForeground(Color.DARK_GRAY);
		lblXY_Sort.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblXY_Sort.setBackground(Color.GRAY);
		lblXY_Sort.setBounds(0, 25, 150, 25);
		panel_Sort.add(lblXY_Sort);
		
		btnModifierSort = new JButton("Modifier");
		btnModifierSort.setEnabled(false);
		btnModifierSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B4D.mouse.getPoint(
					"Cliquez sur votre sort principal",
					new ImageIcon(Toolkit.getDefaultToolkit().getImage(JFrame_B4D.class.getResource("/fr/B4D/images/Sort.png"))),
					new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							Person person = b4d.getTeam().get(table.getSelectedRow());
							person.setSpellPosition(B4D.converter.toPointF(MouseInfo.getPointerInfo().getLocation()));
							ActualiserInfos(person);
						}
					});
			}
		});
		btnModifierSort.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnModifierSort.setBackground(UIManager.getColor("Button.background"));
		btnModifierSort.setBounds(5, 50, 140, 25);
		panel_Sort.add(btnModifierSort);
		
		ActualiserInfos();
	}
	
	/**
	 * Refresh the informations displayed on the panel.
	 */
	public void ActualiserInfos() {
		
		this.dataTable.removeAllElements();
		
		for(Person person:b4d.getTeam()) {
			Vector<String> row = new Vector<String>();
			row.add(person.getAccount());
			row.add(person.getPassword().replaceAll("(?s).", "*"));
			row.add(person.getServer().getName());
			row.add(person.getPseudo());
			dataTable.add(row);
		}

		table.revalidate();
		table.repaint();
	}
	
	
	/**
	 * Refresh the informations displayed for a given person.
	 * @param person - Person's informations to refresh.
	 */
	private void ActualiserInfos(Person person) {
		
		if(person.getBoosterPotion().getTransport().getPositionF() != null)
			this.lblXY_Rappel.setText(person.getBoosterPotion().getTransport().getPositionF().getX() + ":" + person.getBoosterPotion().getTransport().getPositionF().getY());
		else
			this.lblXY_Rappel.setText("X:Y");
		
		if(person.getBontaPotion().getTransport().getPositionF() != null)
			this.lblXY_Bonta.setText(person.getBontaPotion().getTransport().getPositionF().getX() + ":" + person.getBontaPotion().getTransport().getPositionF().getY());
		else
			this.lblXY_Bonta.setText("X:Y");

		if(person.getBrakmarPotion().getTransport().getPositionF() != null)
			this.lblXY_Brakmar.setText(person.getBrakmarPotion().getTransport().getPositionF().getX() + ":" + person.getBrakmarPotion().getTransport().getPositionF().getY());
		else
			this.lblXY_Brakmar.setText("X:Y");

		if(person.getSpellPosition() != null)
			this.lblXY_Sort.setText(person.getSpellPosition().getX() + ":" + person.getSpellPosition().getY());
		else
			this.lblXY_Sort.setText("X:Y");

		if(person.getBoosterPotion().getDestination() != null)
			this.comboBox_Zaaps.setSelectedItem(Zaap.getZaap(person.getBoosterPotion().getDestination()).getName());
		
		if(person.getBontaPotion().getDestination() != null)
			this.lblPosition_Bonta.setText(person.getBontaPotion().getDestination().getX() + ":" + person.getBontaPotion().getDestination().getY());
		else
			this.lblPosition_Bonta.setText("X:Y");
		
		if(person.getBrakmarPotion().getDestination() != null)
			this.lblPosition_Brakmar.setText(person.getBrakmarPotion().getDestination().getX() + ":" + person.getBrakmarPotion().getDestination().getY());
		else
			this.lblPosition_Brakmar.setText("X:Y");
	}
}
