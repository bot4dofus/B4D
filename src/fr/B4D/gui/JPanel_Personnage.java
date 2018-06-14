package fr.B4D.gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class JPanel_Personnage extends JPanel {
	final int width = 520;
	final int height = 310;
	
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public JPanel_Personnage() {
		setBackground(new Color(33,43,53));
		setLayout(null);
		setVisible(false);
		
		JLabel lblNomDeCompte = new JLabel("Nom de compte");
		lblNomDeCompte.setBounds(10, 10, 120, 20);
		lblNomDeCompte.setOpaque(true);
		lblNomDeCompte.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomDeCompte.setForeground(Color.WHITE);
		lblNomDeCompte.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNomDeCompte.setBackground(new Color(46, 139, 87));
		add(lblNomDeCompte);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 30, 120, 20);
		add(textField);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setOpaque(true);
		lblMotDePasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblMotDePasse.setForeground(Color.WHITE);
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMotDePasse.setBackground(new Color(46, 139, 87));
		lblMotDePasse.setBounds(135, 10, 125, 20);
		add(lblMotDePasse);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(135, 30, 125, 20);
		add(textField_1);
		
		JLabel lblServeur = new JLabel("Serveur");
		lblServeur.setOpaque(true);
		lblServeur.setHorizontalAlignment(SwingConstants.CENTER);
		lblServeur.setForeground(Color.WHITE);
		lblServeur.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblServeur.setBackground(new Color(46, 139, 87));
		lblServeur.setBounds(265, 10, 120, 20);
		add(lblServeur);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(265, 30, 120, 20);
		add(textField_2);
		
		JLabel lblPseudo = new JLabel("Pseudo");
		lblPseudo.setOpaque(true);
		lblPseudo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPseudo.setForeground(Color.WHITE);
		lblPseudo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPseudo.setBackground(new Color(46, 139, 87));
		lblPseudo.setBounds(390, 10, 120, 20);
		add(lblPseudo);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(390, 30, 120, 20);
		add(textField_3);
		
		table = new JTable();
		table.setBounds(10, 60, 500, 125);
		add(table);
		
		JPanel panel_Potion_Rappel = new JPanel();
		panel_Potion_Rappel.setForeground(Color.WHITE);
		panel_Potion_Rappel.setBackground(Color.LIGHT_GRAY);
		panel_Potion_Rappel.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Potion_Rappel.setBounds(10, 195, 120, 105);
		panel_Potion_Rappel.setLayout(null);
		add(panel_Potion_Rappel);
		
		JLabel lblPotionDeRappel = new JLabel("Potion de Rappel");
		lblPotionDeRappel.setOpaque(true);
		lblPotionDeRappel.setBackground(new Color(46, 139, 87));
		lblPotionDeRappel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPotionDeRappel.setForeground(Color.WHITE);
		lblPotionDeRappel.setHorizontalAlignment(SwingConstants.CENTER);
		lblPotionDeRappel.setBounds(0, 0, 120, 20);
		panel_Potion_Rappel.add(lblPotionDeRappel);
		
		JLabel lblXY = new JLabel("X:Y");
		lblXY.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY.setForeground(Color.DARK_GRAY);
		lblXY.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblXY.setBackground(Color.GRAY);
		lblXY.setBounds(0, 20, 120, 20);
		panel_Potion_Rappel.add(lblXY);
		
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.setBounds(5, 40, 110, 20);
		panel_Potion_Rappel.add(btnNewButton);
		
		JLabel lblModifier = new JLabel("Destination :");
		lblModifier.setHorizontalAlignment(SwingConstants.CENTER);
		lblModifier.setForeground(Color.DARK_GRAY);
		lblModifier.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblModifier.setBackground(Color.GRAY);
		lblModifier.setBounds(0, 60, 120, 20);
		panel_Potion_Rappel.add(lblModifier);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 10));
		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.setBounds(5, 80, 110, 20);
		panel_Potion_Rappel.add(comboBox);
		
		JPanel panel_Potion_Bonta = new JPanel();
		panel_Potion_Bonta.setForeground(Color.WHITE);
		panel_Potion_Bonta.setLayout(null);
		panel_Potion_Bonta.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Potion_Bonta.setBackground(Color.LIGHT_GRAY);
		panel_Potion_Bonta.setBounds(135, 195, 125, 105);
		add(panel_Potion_Bonta);
		
		JLabel lblPotionDeBonta = new JLabel("Potion de Bonta");
		lblPotionDeBonta.setOpaque(true);
		lblPotionDeBonta.setHorizontalAlignment(SwingConstants.CENTER);
		lblPotionDeBonta.setForeground(Color.WHITE);
		lblPotionDeBonta.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPotionDeBonta.setBackground(new Color(46, 139, 87));
		lblPotionDeBonta.setBounds(0, 0, 125, 20);
		panel_Potion_Bonta.add(lblPotionDeBonta);
		
		JLabel lblXY_1 = new JLabel("X:Y");
		lblXY_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_1.setForeground(Color.DARK_GRAY);
		lblXY_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblXY_1.setBackground(Color.GRAY);
		lblXY_1.setBounds(0, 20, 125, 20);
		panel_Potion_Bonta.add(lblXY_1);
		
		JButton button = new JButton("Modifier");
		button.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button.setBackground(UIManager.getColor("Button.background"));
		button.setBounds(5, 40, 115, 20);
		panel_Potion_Bonta.add(button);
		
		JLabel label_2 = new JLabel("Destination :");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.DARK_GRAY);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_2.setBackground(Color.GRAY);
		label_2.setBounds(0, 60, 125, 20);
		panel_Potion_Bonta.add(label_2);
		
		JLabel lbly = new JLabel("-33:-56");
		lbly.setHorizontalAlignment(SwingConstants.CENTER);
		lbly.setForeground(Color.DARK_GRAY);
		lbly.setFont(new Font("Tahoma", Font.BOLD, 10));
		lbly.setBounds(0, 80, 125, 20);
		panel_Potion_Bonta.add(lbly);
		
		JPanel panel_Potion_Brakmar = new JPanel();
		panel_Potion_Brakmar.setForeground(Color.WHITE);
		panel_Potion_Brakmar.setLayout(null);
		panel_Potion_Brakmar.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Potion_Brakmar.setBackground(Color.LIGHT_GRAY);
		panel_Potion_Brakmar.setBounds(265, 195, 120, 105);
		add(panel_Potion_Brakmar);
		
		JLabel lblPotionDeBrakmar = new JLabel("Potion de Brakmar");
		lblPotionDeBrakmar.setOpaque(true);
		lblPotionDeBrakmar.setHorizontalAlignment(SwingConstants.CENTER);
		lblPotionDeBrakmar.setForeground(Color.WHITE);
		lblPotionDeBrakmar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPotionDeBrakmar.setBackground(new Color(46, 139, 87));
		lblPotionDeBrakmar.setBounds(0, 0, 120, 20);
		panel_Potion_Brakmar.add(lblPotionDeBrakmar);
		
		JLabel lblXY_2 = new JLabel("X:Y");
		lblXY_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_2.setForeground(Color.DARK_GRAY);
		lblXY_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblXY_2.setBackground(Color.GRAY);
		lblXY_2.setBounds(0, 20, 120, 20);
		panel_Potion_Brakmar.add(lblXY_2);
		
		JButton button_2 = new JButton("Modifier");
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_2.setBackground(UIManager.getColor("Button.background"));
		button_2.setBounds(5, 40, 110, 20);
		panel_Potion_Brakmar.add(button_2);
		
		JLabel label_5 = new JLabel("Destination :");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.DARK_GRAY);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_5.setBackground(Color.GRAY);
		label_5.setBounds(0, 60, 120, 20);
		panel_Potion_Brakmar.add(label_5);
		
		JLabel label = new JLabel("-26:36");
		label.setBounds(0, 80, 120, 20);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Tahoma", Font.BOLD, 10));
		label.setBackground(Color.GRAY);
		panel_Potion_Brakmar.add(label);
		
		JPanel panel_Sort = new JPanel();
		panel_Sort.setForeground(Color.WHITE);
		panel_Sort.setLayout(null);
		panel_Sort.setBorder(new LineBorder(new Color(46, 139, 87), 3));
		panel_Sort.setBackground(Color.LIGHT_GRAY);
		panel_Sort.setBounds(390, 195, 120, 65);
		add(panel_Sort);
		
		JLabel lblSortDePorte = new JLabel("Sort de port\u00E9e");
		lblSortDePorte.setOpaque(true);
		lblSortDePorte.setHorizontalAlignment(SwingConstants.CENTER);
		lblSortDePorte.setForeground(Color.WHITE);
		lblSortDePorte.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSortDePorte.setBackground(new Color(46, 139, 87));
		lblSortDePorte.setBounds(0, 0, 120, 20);
		panel_Sort.add(lblSortDePorte);
		
		JLabel lblXY_3 = new JLabel("X:Y");
		lblXY_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblXY_3.setForeground(Color.DARK_GRAY);
		lblXY_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblXY_3.setBackground(Color.GRAY);
		lblXY_3.setBounds(0, 20, 120, 20);
		panel_Sort.add(lblXY_3);
		
		JButton button_4 = new JButton("Modifier");
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button_4.setBackground(UIManager.getColor("Button.background"));
		button_4.setBounds(5, 40, 110, 20);
		panel_Sort.add(button_4);
		
		JButton btnNewButton_1 = new JButton("Terminer");
		btnNewButton_1.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_1.setBounds(390, 260, 120, 20);
		add(btnNewButton_1);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBackground(UIManager.getColor("Button.background"));
		btnAnnuler.setBounds(390, 280, 120, 20);
		add(btnAnnuler);

	}
}
