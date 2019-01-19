package fr.B4D.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.NumberFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import fr.B4D.bot.B4D;
import fr.B4D.farming.Ressource;
import fr.B4D.farming.RessourceType;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;

public class JPanel_Programme extends JPanel {

	private static final long serialVersionUID = -1975429297614634621L;

	@SuppressWarnings("unused")
	private JFrame parent;
	
	public final int width = 635;
	public final int height = 235;
	
	private JComboBox<Place> comboBox_Place;
	private JComboBox<Category> comboBox_Category;
	private JComboBox<RessourceType> comboBox_RessourceType;
	private JComboBox<Ressource> comboBox_Ressource;
	
	private JFormattedTextField textField_Turns, textField_Deposits;

	private JCheckBox checkBox_HDV;
	private JCheckBox checkBox_Bank;
	private JCheckBox checkBox_Stop;
	
	private JButton button_Start;

	/**
	 * Create the panel.
	 */
	public JPanel_Programme(JFrame parent) {
		this.parent = parent;
		
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				Program.getAll().stream().filter(p ->
					((DefaultComboBoxModel<Place>)comboBox_Place.getModel()).getIndexOf(p.getPlace()) < 0)
				.forEach(p -> comboBox_Place.addItem(p.getPlace()));
				
				if(B4D.getConfiguration().getGameFrame() != null && B4D.getConfiguration().getChatFrame() != null && B4D.getConfiguration().getChatBar() != null && B4D.getConfiguration().getMinimap() != null) {
					button_Start.setEnabled(true);
				}else {
					button_Start.setEnabled(false);
				}
			}
		});
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
	
		
		comboBox_Place = new JComboBox<Place>();
		comboBox_Place.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_Category.removeAllItems();
				Program.getAll().stream().filter(p ->
					((DefaultComboBoxModel<Category>)comboBox_Category.getModel()).getIndexOf(p.getCategory()) < 0
					&& p.getPlace().equals(comboBox_Place.getSelectedItem()))
				.forEach(p -> comboBox_Category.addItem(p.getCategory()));
			}
		});
		comboBox_Place.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_Place.setBounds(10, 35, 150, 25);
		add(comboBox_Place);
		
		JLabel lblCatgorie = new JLabel("Cat\u00E9gorie :");
		lblCatgorie.setBackground(new Color(46, 139, 87));
		lblCatgorie.setForeground(Color.WHITE);
		lblCatgorie.setOpaque(true);
		lblCatgorie.setBounds(10, 65, 150, 25);
		lblCatgorie.setHorizontalAlignment(SwingConstants.CENTER);
		lblCatgorie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblCatgorie);
		
		comboBox_Category = new JComboBox<Category>();
		comboBox_Category.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_RessourceType.removeAllItems();
				Program.getAll().stream().filter(p -> 
					((DefaultComboBoxModel<RessourceType>)comboBox_RessourceType.getModel()).getIndexOf(p.getRessourceType()) < 0
					&& p.getPlace().equals(comboBox_Place.getSelectedItem())
					&& p.getCategory().equals(comboBox_Category.getSelectedItem()))
				.forEach(p -> comboBox_RessourceType.addItem(p.getRessourceType()));
			}
		});
		comboBox_Category.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_Category.setBounds(10, 90, 150, 25);
		add(comboBox_Category);
		
		JLabel lblType = new JLabel("Type :");
		lblType.setBackground(new Color(46, 139, 87));
		lblType.setForeground(Color.WHITE);
		lblType.setOpaque(true);
		lblType.setBounds(10, 120, 150, 25);
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblType);
		
		comboBox_RessourceType = new JComboBox<RessourceType>();
		comboBox_RessourceType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_Ressource.removeAllItems();
				Program.getAll().stream().filter(p ->
						((DefaultComboBoxModel<Ressource>)comboBox_Ressource.getModel()).getIndexOf(p.getRessource()) < 0
						&& p.getPlace().equals(comboBox_Place.getSelectedItem())
						&& p.getCategory().equals(comboBox_Category.getSelectedItem()) 
						&& p.getRessourceType().equals(comboBox_RessourceType.getSelectedItem()))
				.forEach(p -> comboBox_Ressource.addItem(p.getRessource()));
			}
		});
		comboBox_RessourceType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_RessourceType.setBounds(10, 145, 150, 25);
		add(comboBox_RessourceType);
		
		JLabel lblRessource = new JLabel("Ressource :");
		lblRessource.setBackground(new Color(46, 139, 87));
		lblRessource.setForeground(Color.WHITE);
		lblRessource.setOpaque(true);
		lblRessource.setBounds(10, 175, 150, 25);
		lblRessource.setHorizontalAlignment(SwingConstants.CENTER);
		lblRessource.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblRessource);
		
		comboBox_Ressource = new JComboBox<Ressource>();
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
		
		textField_Turns = new JFormattedTextField(NumberFormat.getNumberInstance());
		textField_Turns.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Turns.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		textField_Turns.setText("0");
		textField_Turns.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_Turns.setBounds(165, 35, 150, 25);
		textField_Turns.setColumns(10);
		add(textField_Turns);
		
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
		
		textField_Deposits = new JFormattedTextField(NumberFormat.getNumberInstance());
		textField_Deposits.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Deposits.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		textField_Deposits.setText("0");
		textField_Deposits.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_Deposits.setBounds(165, 115, 150, 25);
		textField_Deposits.setColumns(10);
		add(textField_Deposits);
		
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
		
		checkBox_HDV = new JCheckBox("Mettre en HDV");
		checkBox_HDV.setBackground(Color.LIGHT_GRAY);
		checkBox_HDV.setForeground(Color.DARK_GRAY);
		checkBox_HDV.setBounds(320, 35, 150, 20);
		checkBox_HDV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(checkBox_HDV);
		
		checkBox_Bank = new JCheckBox("Mettre en Banque");
		checkBox_Bank.setBackground(Color.LIGHT_GRAY);
		checkBox_Bank.setForeground(Color.DARK_GRAY);
		checkBox_Bank.setBounds(320, 55, 150, 20);
		checkBox_Bank.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(checkBox_Bank);
		
		checkBox_Stop = new JCheckBox("Arreter");
		checkBox_Stop.setBackground(Color.LIGHT_GRAY);
		checkBox_Stop.setForeground(Color.DARK_GRAY);
		checkBox_Stop.setBounds(320, 75, 150, 20);
		checkBox_Stop.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(checkBox_Stop);
		
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
		
		button_Start = new JButton("Commencer");
		button_Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Program program = Program.getAll().stream().filter( p ->
					p.getPlace().equals(comboBox_Place.getSelectedItem())
					&& p.getCategory().equals(comboBox_Category.getSelectedItem()) 
					&& p.getRessourceType().equals(comboBox_RessourceType.getSelectedItem())
					&& p.getRessource().equals(comboBox_Ressource.getSelectedItem()))
				.findFirst().orElse(null);
				
				program.setMaxCycles(Integer.valueOf(textField_Turns.getText()));
				program.setMaxDeposits(Integer.valueOf(textField_Deposits.getText()));
				
				program.setBankWhenFull(checkBox_HDV.isSelected());
				program.setHdvWhenFull(checkBox_Bank.isSelected());
				program.setStopWhenFull(checkBox_Stop.isSelected());
				
				program.start();
				
				try {
					program.join();
					parent.requestFocus();
				} catch (InterruptedException e) {
					JOptionPane.showConfirmDialog(null, "Vous avez stoppé le bot.", "Fin", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		button_Start.setBounds(475, 75, 150, 45);
		button_Start.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		add(button_Start);
	}
}