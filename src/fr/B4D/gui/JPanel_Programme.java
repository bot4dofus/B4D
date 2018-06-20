package fr.B4D.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;

import javax.swing.SwingConstants;

import fr.B4D.enu.Categories;
import fr.B4D.enu.Lieux;
import fr.B4D.enu.Ressources;
import fr.B4D.enu.Types;
import fr.B4D.modules.autre.B4DEcran;
import net.sourceforge.tess4j.TesseractException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.AWTException;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanel_Programme extends JPanel {
	final int width = 635;
	final int height = 235;
	
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
		lblLieu.setBounds(10, 10, 150, 25);
		lblLieu.setHorizontalAlignment(SwingConstants.CENTER);
		lblLieu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblLieu);
		
		JComboBox<Lieux> comboBox_Lieu = new JComboBox<Lieux>();
		comboBox_Lieu.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 10));
		comboBox_Lieu.setBounds(10, 35, 150, 25);
		add(comboBox_Lieu);
		
		JLabel lblCatgorie = new JLabel("Cat\u00E9gorie :");
		lblCatgorie.setBackground(new Color(46, 139, 87));
		lblCatgorie.setForeground(Color.WHITE);
		lblCatgorie.setOpaque(true);
		lblCatgorie.setBounds(10, 65, 150, 25);
		lblCatgorie.setHorizontalAlignment(SwingConstants.CENTER);
		lblCatgorie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblCatgorie);
		
		JComboBox<Categories> comboBox_Categorie = new JComboBox<Categories>();
		comboBox_Categorie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_Categorie.setBounds(10, 90, 150, 25);
		add(comboBox_Categorie);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setBackground(new Color(46, 139, 87));
		lblType.setForeground(Color.WHITE);
		lblType.setOpaque(true);
		lblType.setBounds(10, 120, 150, 25);
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblType);
		
		JComboBox<Types> comboBox_Type = new JComboBox<Types>();
		comboBox_Type.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_Type.setBounds(10, 145, 150, 25);
		add(comboBox_Type);
		
		JLabel lblRessource = new JLabel("Ressource :");
		lblRessource.setBackground(new Color(46, 139, 87));
		lblRessource.setForeground(Color.WHITE);
		lblRessource.setOpaque(true);
		lblRessource.setBounds(10, 175, 150, 25);
		lblRessource.setHorizontalAlignment(SwingConstants.CENTER);
		lblRessource.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblRessource);
		
		JComboBox<Ressources> comboBox_Ressource = new JComboBox<Ressources>();
		comboBox_Ressource.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_Ressource.setBounds(10, 200, 150, 25);
		add(comboBox_Ressource);
		
		JLabel lblNombreDeTours = new JLabel("Nombre de tours :");
		lblNombreDeTours.setBackground(new Color(46, 139, 87));
		lblNombreDeTours.setForeground(Color.WHITE);
		lblNombreDeTours.setOpaque(true);
		lblNombreDeTours.setBounds(165, 10, 150, 25);
		lblNombreDeTours.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeTours.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNombreDeTours);
		
		textField_Tours = new JTextField();
		textField_Tours.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		textField_Tours.setText("0");
		textField_Tours.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_Tours.setBounds(165, 35, 150, 25);
		textField_Tours.setColumns(10);
		add(textField_Tours);
		
		JLabel label_Tours0 = new JLabel("(0 = infini)");
		label_Tours0.setBackground(Color.LIGHT_GRAY);
		label_Tours0.setForeground(Color.DARK_GRAY);
		label_Tours0.setOpaque(true);
		label_Tours0.setBounds(165, 60, 150, 25);
		label_Tours0.setHorizontalAlignment(SwingConstants.CENTER);
		label_Tours0.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(label_Tours0);
		
		JLabel lblNombreDeDpts = new JLabel("Nombre de d\u00E9p\u00F4ts :");
		lblNombreDeDpts.setBackground(new Color(46, 139, 87));
		lblNombreDeDpts.setForeground(Color.WHITE);
		lblNombreDeDpts.setOpaque(true);
		lblNombreDeDpts.setBounds(165, 90, 150, 25);
		lblNombreDeDpts.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeDpts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNombreDeDpts);
		
		textField_Depots = new JTextField();
		textField_Depots.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		textField_Depots.setText("0");
		textField_Depots.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_Depots.setBounds(165, 115, 150, 25);
		textField_Depots.setColumns(10);
		add(textField_Depots);
		
		JLabel label_Depots0 = new JLabel("(0 = infini)");
		label_Depots0.setBackground(Color.LIGHT_GRAY);
		label_Depots0.setForeground(Color.DARK_GRAY);
		label_Depots0.setOpaque(true);
		label_Depots0.setBounds(165, 140, 150, 25);
		label_Depots0.setHorizontalAlignment(SwingConstants.CENTER);
		label_Depots0.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(label_Depots0);
		
		JLabel lblInventairePlein = new JLabel("Inventaire plein :");
		lblInventairePlein.setOpaque(true);
		lblInventairePlein.setBackground(new Color(46, 139, 87));
		lblInventairePlein.setForeground(Color.WHITE);
		lblInventairePlein.setBounds(320, 10, 150, 25);
		lblInventairePlein.setHorizontalAlignment(SwingConstants.CENTER);
		lblInventairePlein.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblInventairePlein);
		
		JCheckBox checkBox_HDV = new JCheckBox("Mettre en HDV");
		checkBox_HDV.setBackground(Color.LIGHT_GRAY);
		checkBox_HDV.setForeground(Color.DARK_GRAY);
		checkBox_HDV.setBounds(320, 35, 150, 20);
		checkBox_HDV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(checkBox_HDV);
		
		JCheckBox checkBox_Banque = new JCheckBox("Mettre en Banque");
		checkBox_Banque.setBackground(Color.LIGHT_GRAY);
		checkBox_Banque.setForeground(Color.DARK_GRAY);
		checkBox_Banque.setBounds(320, 55, 150, 20);
		checkBox_Banque.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(checkBox_Banque);
		
		JCheckBox checkBox_Arreter = new JCheckBox("Arreter");
		checkBox_Arreter.setBackground(Color.LIGHT_GRAY);
		checkBox_Arreter.setForeground(Color.DARK_GRAY);
		checkBox_Arreter.setBounds(320, 75, 150, 20);
		checkBox_Arreter.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(checkBox_Arreter);
		
		JLabel lblDpartEn = new JLabel("D\u00E9part en :");
		lblDpartEn.setBackground(new Color(46, 139, 87));
		lblDpartEn.setForeground(Color.WHITE);
		lblDpartEn.setOpaque(true);
		lblDpartEn.setBounds(475, 10, 150, 25);
		lblDpartEn.setHorizontalAlignment(SwingConstants.CENTER);
		lblDpartEn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblDpartEn);
		
		JLabel label_Pos = new JLabel("X:Y");
		label_Pos.setBackground(Color.LIGHT_GRAY);
		label_Pos.setOpaque(true);
		label_Pos.setForeground(Color.DARK_GRAY);
		label_Pos.setBounds(475, 35, 150, 40);
		label_Pos.setHorizontalAlignment(SwingConstants.CENTER);
		label_Pos.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(label_Pos);
		
		JButton button_Commencer = new JButton("Commencer");
		button_Commencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println(B4DEcran.OCR(new Point(17,920),new Point(696,1007)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		button_Commencer.setBounds(475, 75, 150, 45);
		button_Commencer.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		add(button_Commencer);
	}
}
