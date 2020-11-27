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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import fr.B4D.bot.B4D;
import fr.B4D.program.Category;
import fr.B4D.program.Place;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramOptions;

/**
 * The class {@code JPanel_Programme} is a GUI allowing program selection.
 * @author Lucas
 *
 */
public class JPanel_Programme extends JPanel {

	private static final long serialVersionUID = -1975429297614634621L;

	/**
	 * Width of the panel.
	 */
	public static final int WIDTH = 635;

	/**
	 * Height of the panel.
	 */
	public static final int HEIGHT = 235;
	
	private JComboBox<Place> comboBox_Place;
	private JComboBox<Category> comboBox_Category;
	private JComboBox<String> comboBox_SubCategory;
	private JComboBox<String> comboBox_Name;
	
	private JFormattedTextField textField_Turns, textField_Delay;

	private JCheckBox checkBox_HDV;
	private JCheckBox checkBox_Bank;
	private JCheckBox checkBox_Stop;
	
	private JButton button_Start;
	
	/**
	 * Create the panel.
	 * @param b4d - Instance du bot.
	 */
	public JPanel_Programme(B4D b4d) {
		
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				b4d.getPrograms().stream().filter(p ->
					((DefaultComboBoxModel<Place>)comboBox_Place.getModel()).getIndexOf(p.getPlace()) < 0)
				.forEach(p -> comboBox_Place.addItem(p.getPlace()));
				
				if(b4d.getConfiguration().isComplet() && !b4d.getTeam().isEmpty()) {
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
				b4d.getPrograms().stream().filter(p ->
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
				comboBox_SubCategory.removeAllItems();
				b4d.getPrograms().stream().filter(p -> 
					((DefaultComboBoxModel<String>)comboBox_SubCategory.getModel()).getIndexOf(p.getSubCategory()) < 0
					&& p.getPlace().equals(comboBox_Place.getSelectedItem())
					&& p.getCategory().equals(comboBox_Category.getSelectedItem()))
				.forEach(p -> comboBox_SubCategory.addItem(p.getSubCategory()));
			}
		});
		comboBox_Category.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_Category.setBounds(10, 90, 150, 25);
		add(comboBox_Category);
		
		JLabel lblType = new JLabel("Sous-catégorie :");
		lblType.setBackground(new Color(46, 139, 87));
		lblType.setForeground(Color.WHITE);
		lblType.setOpaque(true);
		lblType.setBounds(10, 120, 150, 25);
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblType);
		
		comboBox_SubCategory = new JComboBox<String>();
		comboBox_SubCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_Name.removeAllItems();
				b4d.getPrograms().stream().filter(p ->
						((DefaultComboBoxModel<String>)comboBox_Name.getModel()).getIndexOf(p.getProgramName()) < 0
						&& p.getPlace().equals(comboBox_Place.getSelectedItem())
						&& p.getCategory().equals(comboBox_Category.getSelectedItem()) 
						&& p.getSubCategory().equals(comboBox_SubCategory.getSelectedItem()))
				.forEach(p -> comboBox_Name.addItem(p.getProgramName()));
			}
		});
		comboBox_SubCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_SubCategory.setBounds(10, 145, 150, 25);
		add(comboBox_SubCategory);
		
		JLabel lblRessource = new JLabel("Nom :");
		lblRessource.setBackground(new Color(46, 139, 87));
		lblRessource.setForeground(Color.WHITE);
		lblRessource.setOpaque(true);
		lblRessource.setBounds(10, 175, 150, 25);
		lblRessource.setHorizontalAlignment(SwingConstants.CENTER);
		lblRessource.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblRessource);
		
		comboBox_Name = new JComboBox<String>();
		comboBox_Name.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox_Name.setBounds(10, 200, 150, 25);
		add(comboBox_Name);
		
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
		textField_Turns.setText("1");
		textField_Turns.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_Turns.setBounds(165, 35, 150, 25);
		textField_Turns.setColumns(10);
		add(textField_Turns);
		
		JLabel label_Tours0 = new JLabel("(-1 = infini)");
		label_Tours0.setBackground(Color.LIGHT_GRAY);
		label_Tours0.setForeground(Color.DARK_GRAY);
		label_Tours0.setOpaque(true);
		label_Tours0.setBounds(165, 60, 150, 25);
		label_Tours0.setHorizontalAlignment(SwingConstants.CENTER);
		label_Tours0.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(label_Tours0);
		
		JLabel labelDelay = new JLabel("Délais inter-cycle (ms) :");
		labelDelay.setBackground(new Color(46, 139, 87));
		labelDelay.setForeground(Color.WHITE);
		labelDelay.setOpaque(true);
		labelDelay.setBounds(165, 90, 150, 25);
		labelDelay.setHorizontalAlignment(SwingConstants.CENTER);
		labelDelay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(labelDelay);
		
		textField_Delay = new JFormattedTextField(NumberFormat.getNumberInstance());
		textField_Delay.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Delay.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		textField_Delay.setText("0");
		textField_Delay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_Delay.setBounds(165, 115, 150, 25);
		textField_Delay.setColumns(10);
		add(textField_Delay);
		
		JLabel label_Depots0 = new JLabel("(-1 = infini)");
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
		
		JLabel lblProgram = new JLabel("Programme :");
		lblProgram.setBackground(new Color(46, 139, 87));
		lblProgram.setForeground(Color.WHITE);
		lblProgram.setOpaque(true);
		lblProgram.setBounds(475, 10, 150, 25);
		lblProgram.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgram.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblProgram);
		
		button_Start = new JButton("Commencer");
		button_Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Program program = b4d.getPrograms().stream().filter( p ->
					p.getPlace().equals(comboBox_Place.getSelectedItem())
					&& p.getCategory().equals(comboBox_Category.getSelectedItem()) 
					&& p.getSubCategory().equals(comboBox_SubCategory.getSelectedItem())
					&& p.getProgramName().equals(comboBox_Name.getSelectedItem()))
				.findFirst().orElse(null);

				ProgramOptions programOptions = new ProgramOptions(Integer.valueOf(textField_Turns.getText()), Long.valueOf(textField_Delay.getText()), checkBox_HDV.isSelected(), checkBox_Bank.isSelected(), checkBox_Stop.isSelected());
				b4d.runProgram(program, b4d.getTeam().get(0), programOptions);
				getParent().requestFocus();
			}
		});
		button_Start.setBounds(475, 35, 150, 40);
		button_Start.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		add(button_Start);
	}
}