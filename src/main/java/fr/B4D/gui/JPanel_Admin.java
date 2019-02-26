package fr.B4D.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.B4D.bot.B4D;
import fr.B4D.programs.Test;
import fr.B4D.utils.PointF;

public class JPanel_Admin extends JPanel {

	private static final long serialVersionUID = -7603368625926813641L;
	
	public final int width = 635;
	public final int height = 70;

	private JButton btnStart;
	private JButton btnRecord;
	
	/**
	 * Create the panel.
	 * @param b4d - Instance du bot.
	 */
	public JPanel_Admin(B4D b4d) {
				
		addComponentListener(new ComponentAdapter() {
			public void componentShown(ComponentEvent e) {
				if(b4d.getConfiguration().getGameFrame() != null && b4d.getConfiguration().getChatMenu() != null && b4d.getConfiguration().getChatBar() != null && b4d.getConfiguration().getMinimap() != null) {
					btnStart.setEnabled(true);
					btnRecord.setEnabled(true);
				}else {
					btnStart.setEnabled(false);		
					btnRecord.setEnabled(false);					
				}
			}
		});
		setBackground(new Color(33,43,53));
		setLayout(null);
		
		JLabel lblStart = new JLabel("Programme de test :");
		lblStart.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStart.setOpaque(true);
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setForeground(Color.WHITE);
		lblStart.setBackground(new Color(46, 139, 87));
		lblStart.setBounds(10, 10, 150, 25);
		add(lblStart);
		
		btnStart = new JButton("Commencer");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Test.test.cycle(b4d.getTeam().get(0));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnStart.setBounds(10, 35, 150, 25);
		add(btnStart);
		
		JLabel lblRecord = new JLabel("Enregistrement :");
		lblRecord.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRecord.setOpaque(true);
		lblRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecord.setForeground(Color.WHITE);
		lblRecord.setBackground(new Color(46, 139, 87));
		lblRecord.setBounds(165, 10, 150, 25);
		add(lblRecord);
		
		btnRecord = new JButton("Commencer");
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
		add(btnRecord);
		setVisible(true);
	}

}
