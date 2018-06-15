package fr.B4D.gui;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;

public class JPanel_Admin extends JPanel {
	final int width = 520;
	final int height = 60;

	/**
	 * Create the panel.
	 */
	public JPanel_Admin() {
		setBackground(new Color(33,43,53));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Programme de test :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(new Color(46, 139, 87));
		lblNewLabel.setBounds(10, 10, 120, 20);
		add(lblNewLabel);
		
		JButton btnCommencer = new JButton("Commencer");
		btnCommencer.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnCommencer.setBounds(10, 30, 120, 20);
		add(btnCommencer);
		
		JLabel lblEnregistrement = new JLabel("Enregistrement :");
		lblEnregistrement.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblEnregistrement.setOpaque(true);
		lblEnregistrement.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnregistrement.setForeground(Color.WHITE);
		lblEnregistrement.setBackground(new Color(46, 139, 87));
		lblEnregistrement.setBounds(135, 10, 120, 20);
		add(lblEnregistrement);
		
		JButton button = new JButton("Commencer");
		button.setFont(new Font("Tahoma", Font.PLAIN, 10));
		button.setBounds(135, 30, 120, 20);
		add(button);
		setVisible(true);
	}

}
