package fr.B4D.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import fr.B4D.enu.Categories;
import fr.B4D.enu.Lieux;
import fr.B4D.enu.Ressources;
import fr.B4D.enu.Types;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class JPanel_Programme extends JPanel {
	final int width = 510;
	final int height = 320;
	
	private JTextField textField_Tours;
	private JTextField textField_Depots;

	/**
	 * Create the panel.
	 */
	public JPanel_Programme() {
		setLayout(null);
		
		JLabel label_Lieu = new JLabel("-------- Lieu --------");
		label_Lieu.setBounds(10, 10, 130, 30);
		label_Lieu.setHorizontalAlignment(SwingConstants.CENTER);
		label_Lieu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(label_Lieu);
		
		JComboBox<Lieux> comboBox_Lieu = new JComboBox<Lieux>();
		comboBox_Lieu.setBounds(10, 40, 130, 30);
		add(comboBox_Lieu);
		
		JLabel label_Categorie = new JLabel("----- Cat\u00E9gorie -----");
		label_Categorie.setBounds(10, 80, 130, 30);
		label_Categorie.setHorizontalAlignment(SwingConstants.CENTER);
		label_Categorie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(label_Categorie);
		
		JComboBox<Categories> comboBox_Categorie = new JComboBox<Categories>();
		comboBox_Categorie.setBounds(10, 120, 130, 30);
		add(comboBox_Categorie);
		
		JLabel label_Type = new JLabel("------- Type -------");
		label_Type.setBounds(10, 160, 130, 30);
		label_Type.setHorizontalAlignment(SwingConstants.CENTER);
		label_Type.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(label_Type);
		
		JComboBox<Types> comboBox_Type = new JComboBox<Types>();
		comboBox_Type.setBounds(10, 200, 130, 30);
		add(comboBox_Type);
		
		JLabel label_Ressource = new JLabel("---- Ressource ----");
		label_Ressource.setBounds(10, 240, 130, 30);
		label_Ressource.setHorizontalAlignment(SwingConstants.CENTER);
		label_Ressource.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(label_Ressource);
		
		JComboBox<Ressources> comboBox_Ressource = new JComboBox<Ressources>();
		comboBox_Ressource.setBounds(10, 280, 130, 30);
		add(comboBox_Ressource);
		
		JLabel label_Tours = new JLabel("----- Nombre de tours -----");
		label_Tours.setBounds(150, 10, 180, 30);
		label_Tours.setHorizontalAlignment(SwingConstants.CENTER);
		label_Tours.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(label_Tours);
		
		textField_Tours = new JTextField();
		textField_Tours.setBounds(150, 40, 180, 30);
		textField_Tours.setColumns(10);
		add(textField_Tours);
		
		JLabel label_Tours0 = new JLabel("(0 = infini)");
		label_Tours0.setBounds(150, 80, 180, 30);
		label_Tours0.setHorizontalAlignment(SwingConstants.CENTER);
		label_Tours0.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(label_Tours0);
		
		JLabel label_Depots = new JLabel("---- Nombre de d\u00E9p\u00F4ts ----");
		label_Depots.setBounds(150, 160, 180, 30);
		label_Depots.setHorizontalAlignment(SwingConstants.CENTER);
		label_Depots.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(label_Depots);
		
		textField_Depots = new JTextField();
		textField_Depots.setBounds(150, 200, 180, 30);
		textField_Depots.setColumns(10);
		add(textField_Depots);
		
		JLabel label_Depots0 = new JLabel("(0 = infini)");
		label_Depots0.setBounds(150, 240, 180, 30);
		label_Depots0.setHorizontalAlignment(SwingConstants.CENTER);
		label_Depots0.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(label_Depots0);
		
		JLabel label_Plein = new JLabel("---- Inventaire plein ----");
		label_Plein.setBounds(340, 10, 160, 30);
		label_Plein.setHorizontalAlignment(SwingConstants.CENTER);
		label_Plein.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(label_Plein);
		
		JCheckBox checkBox_HDV = new JCheckBox("Mettre en HDV");
		checkBox_HDV.setBounds(340, 40, 160, 20);
		checkBox_HDV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(checkBox_HDV);
		
		JCheckBox checkBox_Banque = new JCheckBox("Mettre en Banque");
		checkBox_Banque.setBounds(340, 60, 160, 20);
		checkBox_Banque.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(checkBox_Banque);
		
		JCheckBox checkBox_Arreter = new JCheckBox("Arreter");
		checkBox_Arreter.setBounds(340, 80, 160, 20);
		checkBox_Arreter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(checkBox_Arreter);
		
		JLabel label_Depart = new JLabel("---- D\u00E9part en ----");
		label_Depart.setBounds(340, 120, 160, 30);
		label_Depart.setHorizontalAlignment(SwingConstants.CENTER);
		label_Depart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(label_Depart);
		
		JLabel label_Pos = new JLabel("X:Y");
		label_Pos.setBounds(340, 160, 160, 70);
		label_Pos.setHorizontalAlignment(SwingConstants.CENTER);
		label_Pos.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(label_Pos);
		
		JButton button_Commencer = new JButton("Commencer");
		button_Commencer.setEnabled(false);
		button_Commencer.setBounds(340, 240, 160, 70);
		button_Commencer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(button_Commencer);
	}
}
