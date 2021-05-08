package fr.B4D.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.B4D.bot.B4D;
import fr.B4D.dofus.server.Server;
import fr.B4D.dofus.server.ServerEnum;
import fr.B4D.program.Program;
import fr.B4D.program.ProgramOptions;

/**
 * The class {@code JPanel_Programme} is a GUI allowing program selection.
 * 
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
	public static final int HEIGHT = 325;
	
	private JFormattedTextField textField_Turns, textField_Delay;

	private JCheckBox checkBox_HDV;
	private JCheckBox checkBox_Bank;
	private JCheckBox checkBox_Stop;
	
	private JButton button_Start;
	private JTable table;
	
	/**
	 * Create the panel.
	 * @param b4d - Instance du bot.
	 */
	public JPanel_Programme(B4D b4d) {
		
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {				
				checkStartButton(b4d);
			}
		});
		setBackground(new Color(33,43,53));
		setLayout(null);
		setVisible(true);

		JComboBox<Server> comboBox_server = new JComboBox<Server>();
		
		for(ServerEnum server:ServerEnum.values())
			comboBox_server.addItem(server.getValue());
		
		Vector<Vector<String>> dataTable = new Vector<Vector<String>>();
		
		for(Program program:b4d.getPrograms()) {
			Vector<String> row = new Vector<String>();
			row.add(program.getCategory().toString());
			row.add(program.getSubCategory());
			row.add(program.getPlace().toString());
			row.add(program.getProgramName());
			dataTable.add(row);
		}
		
		Vector<String> colonnes = new Vector<String>(Arrays.asList(new String[] {"Catégorie","Sous catégorie","Lieu","Nom"}));
		table = new JTable(dataTable, colonnes);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		    	if(!e.getValueIsAdjusting()) {
		    		checkStartButton(b4d);
		    	}
		    }
		});
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, 615, 215);
		add(scrollPane);
		
		JLabel lblNombreDeTours = new JLabel("Nombre de tours :");
		lblNombreDeTours.setBackground(new Color(46, 139, 87));
		lblNombreDeTours.setForeground(Color.WHITE);
		lblNombreDeTours.setOpaque(true);
		lblNombreDeTours.setBounds(10, 230, 150, 25);
		lblNombreDeTours.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeTours.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblNombreDeTours);
		
		textField_Turns = new JFormattedTextField(NumberFormat.getNumberInstance());
		textField_Turns.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Turns.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		textField_Turns.setText("1");
		textField_Turns.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_Turns.setBounds(10, 255, 150, 25);
		textField_Turns.setColumns(10);
		add(textField_Turns);
		
		JLabel label_Tours0 = new JLabel("(-1 = infini)");
		label_Tours0.setBackground(Color.LIGHT_GRAY);
		label_Tours0.setForeground(Color.DARK_GRAY);
		label_Tours0.setOpaque(true);
		label_Tours0.setBounds(10, 280, 150, 25);
		label_Tours0.setHorizontalAlignment(SwingConstants.CENTER);
		label_Tours0.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(label_Tours0);
		
		JLabel labelDelay = new JLabel("Délais inter-cycle (ms) :");
		labelDelay.setBackground(new Color(46, 139, 87));
		labelDelay.setForeground(Color.WHITE);
		labelDelay.setOpaque(true);
		labelDelay.setBounds(165, 230, 150, 25);
		labelDelay.setHorizontalAlignment(SwingConstants.CENTER);
		labelDelay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(labelDelay);
		
		textField_Delay = new JFormattedTextField(NumberFormat.getNumberInstance());
		textField_Delay.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Delay.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192), new Color(192, 192, 192)));
		textField_Delay.setText("0");
		textField_Delay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textField_Delay.setBounds(165, 255, 150, 25);
		textField_Delay.setColumns(10);
		add(textField_Delay);
		
		JLabel label_Depots0 = new JLabel("(-1 = infini)");
		label_Depots0.setBackground(Color.LIGHT_GRAY);
		label_Depots0.setForeground(Color.DARK_GRAY);
		label_Depots0.setOpaque(true);
		label_Depots0.setBounds(165, 280, 150, 25);
		label_Depots0.setHorizontalAlignment(SwingConstants.CENTER);
		label_Depots0.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(label_Depots0);
		
		JLabel lblInventairePlein = new JLabel("Inventaire plein :");
		lblInventairePlein.setOpaque(true);
		lblInventairePlein.setBackground(new Color(46, 139, 87));
		lblInventairePlein.setForeground(Color.WHITE);
		lblInventairePlein.setBounds(320, 230, 150, 25);
		lblInventairePlein.setHorizontalAlignment(SwingConstants.CENTER);
		lblInventairePlein.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblInventairePlein);
		
		checkBox_HDV = new JCheckBox("Mettre en HDV");
		checkBox_HDV.setBackground(Color.LIGHT_GRAY);
		checkBox_HDV.setForeground(Color.DARK_GRAY);
		checkBox_HDV.setBounds(320, 255, 150, 20);
		checkBox_HDV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(checkBox_HDV);
		
		checkBox_Bank = new JCheckBox("Mettre en Banque");
		checkBox_Bank.setBackground(Color.LIGHT_GRAY);
		checkBox_Bank.setForeground(Color.DARK_GRAY);
		checkBox_Bank.setBounds(320, 275, 150, 20);
		checkBox_Bank.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(checkBox_Bank);
		
		checkBox_Stop = new JCheckBox("Arreter");
		checkBox_Stop.setBackground(Color.LIGHT_GRAY);
		checkBox_Stop.setForeground(Color.DARK_GRAY);
		checkBox_Stop.setBounds(320, 295, 150, 20);
		checkBox_Stop.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(checkBox_Stop);
		
		JLabel lblProgram = new JLabel("Programme :");
		lblProgram.setBackground(new Color(46, 139, 87));
		lblProgram.setForeground(Color.WHITE);
		lblProgram.setOpaque(true);
		lblProgram.setBounds(475, 230, 150, 25);
		lblProgram.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgram.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(lblProgram);
		
		button_Start = new JButton("Commencer");
		button_Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() != -1) {
					Program program = b4d.getPrograms().get(table.getSelectedRow());
					ProgramOptions programOptions = new ProgramOptions(Integer.valueOf(textField_Turns.getText()), Long.valueOf(textField_Delay.getText()), checkBox_HDV.isSelected(), checkBox_Bank.isSelected(), checkBox_Stop.isSelected());
					b4d.runProgram(program, b4d.getTeam().get(0), programOptions);
					getParent().requestFocus();
				}
			}
		});
		button_Start.setBounds(475, 255, 150, 40);
		button_Start.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		add(button_Start);
	}
	
	/**
	 * Check if the start button can be enable or not.<br>
	 * To start a program, 3 conditions need to be satisfied:
	 * <ul>
	 *   <li>The configuration must be full</li>
	 *   <li>At least one person must be created</li>
	 *   <li>A program must be selected</li>
	 * </ul>
	 * @param b4d - B4D instance.
	 */
	private void checkStartButton(B4D b4d) {
		if(b4d.getConfiguration().isComplet() && !b4d.getTeam().isEmpty() && table.getSelectedRow() != -1) {
			button_Start.setEnabled(true);
		}else {
			button_Start.setEnabled(false);
		}
	}
}