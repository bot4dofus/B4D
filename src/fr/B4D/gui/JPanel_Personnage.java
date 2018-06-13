package fr.B4D.gui;

import javax.swing.JPanel;
import javax.swing.JTable;

public class JPanel_Personnage extends JPanel {
	final int width = 650;
	final int height = 450;
	
	private JTable table;

	/**
	 * Create the panel.
	 */
	public JPanel_Personnage() {
		setLayout(null);
		
		table = new JTable();
		table.setBounds(10, 90, 630, 350);
		add(table);

	}
}
