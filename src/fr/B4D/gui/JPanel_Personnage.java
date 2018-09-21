package fr.B4D.gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import fr.B4D.classes.Bot;
import fr.B4D.classes.Person;
import fr.B4D.classes.transports.Zaap;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.util.Arrays;
import java.util.Vector;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class JPanel_Personnage extends JPanel {
	final int width = 635;
	final int height = 365;
	
	private Vector<Vector<String>> dataTable;
	private JTable table;
	private JLabel lblXY_Rappel;
	private JLabel lblXY_Bonta;
	private JLabel lblXY_Brakmar;
	private JLabel lblXY_Sort;

	JComboBox<String> comboBox_Zaaps;
	private JLabel lblPosition_Bonta;
	private JLabel lblPosition_Brakmar;

	/**
	 * Create the panel.
	 * @param lblXY_Rappel 
	 */
	public JPanel_Personnage() {
		setBackground(new Color(33,43,53));
		setLayout(null);
		setVisible(false);
		
		dataTable = new Vector<Vector<String>>();
		Vector<String> colonnes = new Vector<String>(Arrays.asList(new String[] {"Nom de compte","Mot de passe","Serveur","Pseudo"}));
		table = new JTable(dataTable, colonnes);
		table.getModel().addTableModelListener(new TableModelListener(){
			public void tableChanged(TableModelEvent evt){
				String text = (String) table.getModel().getValueAt(evt.getFirstRow(), evt.getColumn());
				switch(evt.getColumn()){
					case 0:
						Bot.MyConfiguration.persons.get(evt.getFirstRow()).account = text;
						break;
					case 1:
						Bot.MyConfiguration.persons.get(evt.getFirstRow()).password = text;
						break;
					case 2:
						Bot.MyConfiguration.persons.get(evt.getFirstRow()).serveur = text;
						break;
					case 3:
						Bot.MyConfiguration.persons.get(evt.getFirstRow()).pseudo = text;
						break;
				}
				ActualiserListe();
			}
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting())
					ActualiserInfos(Bot.MyConfiguration.persons.get(e.getFirstIndex()));
			}
		});
		
		JButton btnNouveau = new JButton("Nouveau");
		btnNouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bot.MyConfiguration.persons.add(new Person());
				ActualiserListe();
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
					Bot.MyConfiguration.persons.remove(table.getSelectedRow());
					ActualiserListe();
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
					Person personnage = Bot.MyConfiguration.persons.get(table.getSelectedRow());
					Bot.MyConfiguration.persons.remove(personnage);
					Bot.MyConfiguration.persons.add(0,personnage);
					table.setRowSelectionInterval(0, 0);
					ActualiserListe();
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
		
		JButton btnModifierRappel = new JButton("Modifier");
		btnModifierRappel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		comboBox_Zaaps.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if(table.getSelectedRow() != -1) {
					try {
						Bot.MyConfiguration.persons.get(table.getSelectedRow()).rappelPotionDestination = Zaap.getZaap(comboBox_Zaaps.getSelectedItem().toString());
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
		
		JButton btnModifierBonta = new JButton("Modifier");
		btnModifierBonta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		JButton btnModifierBrakmar = new JButton("Modifier");
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
		
		JButton btnModifierSort = new JButton("Modifier");
		btnModifierSort.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnModifierSort.setBackground(UIManager.getColor("Button.background"));
		btnModifierSort.setBounds(5, 50, 140, 25);
		panel_Sort.add(btnModifierSort);
		
		ActualiserListe();
	}

	  /**************/
	 /** METHODES **/
	/**************/
	
	private void ActualiserListe() {
		
		this.dataTable.removeAllElements();
		
		for(Person personnage:Bot.MyConfiguration.persons) {
			Vector<String> row = new Vector<String>();
			row.add(personnage.account);
			row.add(personnage.password.replaceAll("(?s).", "*"));
			row.add(personnage.serveur);
			row.add(personnage.pseudo);
			dataTable.add(row);
		}
		
		if(comboBox_Zaaps.getItemCount() == 0) {
			for(Zaap zaap:Zaap.getAll())
				this.comboBox_Zaaps.addItem(zaap.getNom());
		}

		table.revalidate();
		table.repaint();
	}
	private void ActualiserInfos(Person personnage) {
		if(personnage.rappelPotionPosition != null)
			this.lblXY_Rappel.setText(personnage.rappelPotionPosition.getX() + ":" + personnage.rappelPotionPosition.getY());
		else
			this.lblXY_Rappel.setText("X:Y");
		
		if(personnage.bontaPotionPosition != null)
			this.lblXY_Bonta.setText(personnage.bontaPotionPosition.getX() + ":" + personnage.bontaPotionPosition.getY());
		else
			this.lblXY_Bonta.setText("X:Y");

		if(personnage.brakmarPotionPosition != null)
			this.lblXY_Brakmar.setText(personnage.brakmarPotionPosition.getX() + ":" + personnage.brakmarPotionPosition.getY());
		else
			this.lblXY_Brakmar.setText("X:Y");

		if(personnage.spellPosition != null)
			this.lblXY_Sort.setText(personnage.spellPosition.getX() + ":" + personnage.spellPosition.getY());
		else
			this.lblXY_Sort.setText("X:Y");

		if(personnage.rappelPotionDestination != null)
			this.comboBox_Zaaps.setSelectedItem(personnage.rappelPotionDestination.getNom());
		
		if(personnage.bontaPotionDestination != null)
			this.lblPosition_Bonta.setText(personnage.bontaPotionDestination.getX() + ":" + personnage.bontaPotionDestination.getY());
		else
			this.lblPosition_Bonta.setText("X:Y");
		
		if(personnage.brakmarPotionDestination != null)
			this.lblPosition_Brakmar.setText(personnage.brakmarPotionDestination.getX() + ":" + personnage.brakmarPotionDestination.getY());
		else
			this.lblPosition_Brakmar.setText("X:Y");
		
	}
}
