package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controller;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginProjectManager extends JFrame {

	private JPanel contentPane;
	private JTextField CodiceFiscaleInserito;
	Controller IlControllore;

	/**
	 * Launch the application.
	 */
	
	public LoginProjectManager(Controller controller ) {
		IlControllore =controller;
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sara\\Desktop\\ingranaggio-blu.png"));
		setTitle("Azienda- Login del project manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserisci codice fiscale");
		lblNewLabel.setBounds(27, 49, 128, 24);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CodiceFiscaleInserito.getText().length()>0) {
					IlControllore.AvviaLoginPM(CodiceFiscaleInserito.getText());
				}
			}
		});
		btnNewButton.setBounds(263, 203, 102, 35);
		contentPane.add(btnNewButton);
		
		CodiceFiscaleInserito= new JTextField();
		CodiceFiscaleInserito.setBounds(238, 44, 142, 35);
		contentPane.add(CodiceFiscaleInserito);
		CodiceFiscaleInserito.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Crea nuovo project manager");
		lblNewLabel_1.setBounds(238, 86, 155, 13);
		contentPane.add(lblNewLabel_1);
	}

}
