package fr.B4D.gui;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.B4D.bot.B4D;
import fr.B4D.programs.Test;
import fr.B4D.utils.PointF;

/**
 * The class {@code JPanel_Admin} is a GUI used by admins and users for test purposes, program recording and bug report.
 * 
 * @author Lucas
 *
 */
public class JPanel_Admin extends JPanel {

	private static final long serialVersionUID = -7603368625926813641L;

	/**
	 * Width of the panel.
	 */
	public static final int WIDTH = 635;

	/**
	 * Height of the panel.
	 */
	public static final int HEIGHT = 70;
	
	/**
	 * Create the panel.
	 * @param b4d - Instance du bot.
	 */
	public JPanel_Admin(B4D b4d) {
				
		setBackground(new Color(33,43,53));
		setLayout(null);
		
		JLabel lblTestProgram = new JLabel("Programme de test :");
		lblTestProgram.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTestProgram.setOpaque(true);
		lblTestProgram.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestProgram.setForeground(Color.WHITE);
		lblTestProgram.setBackground(new Color(46, 139, 87));
		lblTestProgram.setBounds(10, 10, 150, 25);
		add(lblTestProgram);
		
		JButton btnTestProgram = new JButton("Commencer");
		btnTestProgram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Test().cycle(b4d.getTeam().get(0));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnTestProgram.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnTestProgram.setBounds(10, 35, 150, 25);
		add(btnTestProgram);
		
		JLabel lblRecord = new JLabel("Enregistrement :");
		lblRecord.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRecord.setOpaque(true);
		lblRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecord.setForeground(Color.WHITE);
		lblRecord.setBackground(new Color(46, 139, 87));
		lblRecord.setBounds(165, 10, 150, 25);
		add(lblRecord);
		
		JButton btnRecord = new JButton("Commencer");
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				B4D.mouse.getPoint(
						"Cliquez à la position voulue.",
						new MouseAdapter() {
							public void mousePressed(MouseEvent e) {
								try {
									PointF point = B4D.converter.toPointF(MouseInfo.getPointerInfo().getLocation());
									String out = "new PointF(" + point.x + ", " + point.y + ")";
									B4D.keyboard.setClipboard(out);
								} catch (HeadlessException ex) {
									ex.printStackTrace();
								}
							}
						});
			}
		});
		btnRecord.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRecord.setBounds(165, 35, 150, 25);
		
		if(b4d.getConfiguration().getGameFrame() == null)
			btnRecord.setEnabled(false);
		
		add(btnRecord);
		
		JLabel lblPropose = new JLabel("Nouveau programme / Amélioration / Question :");
		lblPropose.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPropose.setOpaque(true);
		lblPropose.setHorizontalAlignment(SwingConstants.CENTER);
		lblPropose.setForeground(Color.WHITE);
		lblPropose.setBackground(new Color(46, 139, 87));
		lblPropose.setBounds(320, 10, 305, 25);
		add(lblPropose);
		
		JButton btnPropose = new JButton("Proposer");
		btnPropose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop desktop = java.awt.Desktop.getDesktop();
					URI url = new URI("https://github.com/LucBerge/B4D/issues/new/choose");
					desktop.browse(url);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnPropose.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnPropose.setBounds(320, 35, 305, 25);
		add(btnPropose);
		
		setVisible(true);
	}

}
