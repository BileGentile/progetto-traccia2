package gui;

import app.Controller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;

public class Presentazione extends JFrame {

	private JPanel contentPane;
	Controller IlControllore;
	
	public Presentazione(Controller c) {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		

		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		IlControllore =c;

		setTitle("Azienda- Benvenuto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 300);
		setMinimumSize(new Dimension(450,340));
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Benvenuto nell'APP ufficiale dell'azienda,");
		lblNewLabel.setBounds(23, 35, 330, 82);
				
				JButton btnNewButton = new JButton("Sviluppatore");
				btnNewButton.setBounds(250, 217, 135, 51);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IlControllore.AvviaLoginSviluppatore();
					}
				});
				
				JButton btnNewButton_1 = new JButton("Project Manager");
				btnNewButton_1.setBounds(71, 217, 139, 51);
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						IlControllore.LoginProjectManager();
					}
				});
				contentPane.setLayout(null);
				
				JLabel lblNewLabel_1 = new JLabel("Clicca sul tuo ruolo:");
				lblNewLabel_1.setBounds(23, 75, 137, 53);
				contentPane.add(lblNewLabel_1);
				contentPane.add(lblNewLabel);
				contentPane.add(btnNewButton_1);
				contentPane.add(btnNewButton);
				
	}
}

