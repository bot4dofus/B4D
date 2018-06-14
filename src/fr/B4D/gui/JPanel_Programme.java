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
import java.awt.Component;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

public class JPanel_Programme extends JPanel {
	final int width = 520;
	final int height = 195;
	
	private JTextField textField_Tours;
	private JTextField textField_Depots;

	/**
	 * Create the panel.
	 */
	public JPanel_Programme() {
		setBackground(new Color(33,43,53));
		setLayout(null);
		setVisible(true);
		
		JLabel lblLieu = new JLabel("Lieu :");
		lblLieu.setBackground(new Color(46, 139, 87));
		lblLieu.setForeground(Color.WHITE);
		lblLieu.setOpaque(true);
		lblLieu.setBounds(10, 10, 120, 20);
		lblLieu.setHorizontalAlignment(SwingConstants.CENTER);
		lblLieu.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		add(lblLieu);
		
		JComboBox<Lieux> comboBox_Lieu = new JComboBox<Lieux>();
		comboBox_Lieu.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		comboBox_Lieu.setBounds(10, 30, 120, 20);
		add(comboBox_Lieu);
		
		JLabel lblCatgorie = new JLabel("Cat\u00E9gorie :");
		lblCatgorie.setBackground(new Color(46, 139, 87));
		lblCatgorie.setForeground(Color.WHITE);
		lblCatgorie.setOpaque(true);
		lblCatgorie.setBounds(10, 55, 120, 20);
		lblCatgorie.setHorizontalAlignment(SwingConstants.CENTER);
		lblCatgorie.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		add(lblCatgorie);
		
		JComboBox<Categories> comboBox_Categorie = new JComboBox<Categories>();
		comboBox_Categorie.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		comboBox_Categorie.setBounds(10, 75, 120, 20);
		add(comboBox_Categorie);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setBackground(new Color(46, 139, 87));
		lblType.setForeground(Color.WHITE);
		lblType.setOpaque(true);
		lblType.setBounds(10, 100, 120, 20);
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		add(lblType);
		
		JComboBox<Types> comboBox_Type = new JComboBox<Types>();
		comboBox_Type.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		comboBox_Type.setBounds(10, 120, 120, 20);
		add(comboBox_Type);
		
		JLabel lblRessource = new JLabel("Ressource :");
		lblRessource.setBackground(new Color(46, 139, 87));
		lblRessource.setForeground(Color.WHITE);
		lblRessource.setOpaque(true);
		lblRessource.setBounds(10, 145, 120, 20);
		lblRessource.setHorizontalAlignment(SwingConstants.CENTER);
		lblRessource.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		add(lblRessource);
		
		JComboBox<Ressources> comboBox_Ressource = new JComboBox<Ressources>();
		comboBox_Ressource.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		comboBox_Ressource.setBounds(10, 165, 120, 20);
		add(comboBox_Ressource);
		
		JLabel lblNombreDeTours = new JLabel("Nombre de tours :");
		lblNombreDeTours.setBackground(new Color(46, 139, 87));
		lblNombreDeTours.setForeground(Color.WHITE);
		lblNombreDeTours.setOpaque(true);
		lblNombreDeTours.setBounds(135, 10, 125, 20);
		lblNombreDeTours.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeTours.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		add(lblNombreDeTours);
		
		textField_Tours = new JTextField();
		textField_Tours.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		textField_Tours.setText("0");
		textField_Tours.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		textField_Tours.setBounds(135, 30, 125, 20);
		textField_Tours.setColumns(10);
		add(textField_Tours);
		
		JLabel label_Tours0 = new JLabel("(0 = infini)");
		label_Tours0.setBackground(Color.LIGHT_GRAY);
		label_Tours0.setForeground(Color.DARK_GRAY);
		label_Tours0.setOpaque(true);
		label_Tours0.setBounds(135, 50, 125, 20);
		label_Tours0.setHorizontalAlignment(SwingConstants.CENTER);
		label_Tours0.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		add(label_Tours0);
		
		JLabel lblNombreDeDpts = new JLabel("Nombre de d\u00E9p\u00F4ts :");
		lblNombreDeDpts.setBackground(new Color(46, 139, 87));
		lblNombreDeDpts.setForeground(Color.WHITE);
		lblNombreDeDpts.setOpaque(true);
		lblNombreDeDpts.setBounds(135, 75, 125, 20);
		lblNombreDeDpts.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeDpts.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		add(lblNombreDeDpts);
		
		textField_Depots = new JTextField();
		textField_Depots.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		textField_Depots.setText("0");
		textField_Depots.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		textField_Depots.setBounds(135, 95, 125, 20);
		textField_Depots.setColumns(10);
		add(textField_Depots);
		
		JLabel label_Depots0 = new JLabel("(0 = infini)");
		label_Depots0.setBackground(Color.LIGHT_GRAY);
		label_Depots0.setForeground(Color.DARK_GRAY);
		label_Depots0.setOpaque(true);
		label_Depots0.setBounds(135, 115, 125, 20);
		label_Depots0.setHorizontalAlignment(SwingConstants.CENTER);
		label_Depots0.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		add(label_Depots0);
		
		JLabel lblInventairePlein = new JLabel("Inventaire plein :");
		lblInventairePlein.setOpaque(true);
		lblInventairePlein.setBackground(new Color(46, 139, 87));
		lblInventairePlein.setForeground(Color.WHITE);
		lblInventairePlein.setBounds(265, 10, 120, 20);
		lblInventairePlein.setHorizontalAlignment(SwingConstants.CENTER);
		lblInventairePlein.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		add(lblInventairePlein);
		
		JCheckBox checkBox_HDV = new JCheckBox("Mettre en HDV");
		checkBox_HDV.setBackground(Color.LIGHT_GRAY);
		checkBox_HDV.setForeground(Color.DARK_GRAY);
		checkBox_HDV.setBounds(265, 30, 120, 20);
		checkBox_HDV.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		add(checkBox_HDV);
		
		JCheckBox checkBox_Banque = new JCheckBox("Mettre en Banque");
		checkBox_Banque.setBackground(Color.LIGHT_GRAY);
		checkBox_Banque.setForeground(Color.DARK_GRAY);
		checkBox_Banque.setBounds(265, 50, 120, 20);
		checkBox_Banque.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		add(checkBox_Banque);
		
		JCheckBox checkBox_Arreter = new JCheckBox("Arreter");
		checkBox_Arreter.setBackground(Color.LIGHT_GRAY);
		checkBox_Arreter.setForeground(Color.DARK_GRAY);
		checkBox_Arreter.setBounds(265, 70, 120, 20);
		checkBox_Arreter.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		add(checkBox_Arreter);
		
		JLabel lblDpartEn = new JLabel("D\u00E9part en :");
		lblDpartEn.setBackground(new Color(46, 139, 87));
		lblDpartEn.setForeground(Color.WHITE);
		lblDpartEn.setOpaque(true);
		lblDpartEn.setBounds(390, 10, 120, 20);
		lblDpartEn.setHorizontalAlignment(SwingConstants.CENTER);
		lblDpartEn.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		add(lblDpartEn);
		
		JLabel label_Pos = new JLabel("X:Y");
		label_Pos.setBackground(Color.LIGHT_GRAY);
		label_Pos.setOpaque(true);
		label_Pos.setForeground(Color.DARK_GRAY);
		label_Pos.setBounds(390, 30, 120, 40);
		label_Pos.setHorizontalAlignment(SwingConstants.CENTER);
		label_Pos.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(label_Pos);
		
		JButton button_Commencer = new JButton("Commencer");
		button_Commencer.setBounds(390, 70, 120, 40);
		button_Commencer.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		add(button_Commencer);
	}
}
