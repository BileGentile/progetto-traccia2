package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.Controller;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrazioneProjectManager extends JFrame {


	private JPanel contentPane;
	private JTextField CodiceFiscalePM;

	private JButton btnNewButton_1;
	Controller IlControllore;
	private JLabel lblInserisciNome;
	private JTextField NomePM;
	private JTextField CognomePM;
	
	
	public RegistrazioneProjectManager(Controller c) {
		IlControllore =c;
		
		setTitle("Azienda- Registrazione");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Inserisci codice Fiscale");
		lblNewLabel.setBounds(20, 30, 163, 55);
		contentPane.add(lblNewLabel);
		
		CodiceFiscalePM = new JTextField();
		CodiceFiscalePM.setBounds(185, 48, 176, 19);
		contentPane.add(CodiceFiscalePM);
		CodiceFiscalePM.setColumns(10);
		
		lblInserisciNome = new JLabel("Inserisci nome");
		lblInserisciNome.setBounds(20, 76, 163, 55);
		contentPane.add(lblInserisciNome);
		
		NomePM = new JTextField();
		NomePM.setColumns(10);
		NomePM.setBounds(185, 95, 176, 19);
		contentPane.add(NomePM);
		
		JLabel lblInserisciCognome = new JLabel("Inserisci cognome");
		lblInserisciCognome.setBounds(20, 121, 163, 55);
		contentPane.add(lblInserisciCognome);
		
		CognomePM = new JTextField();
		CognomePM.setColumns(10);
		CognomePM.setBounds(185, 141, 176, 19);
		contentPane.add(CognomePM);
		
		JButton btnNewButton = new JButton("Registrati");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.RegistraProjectManager(CognomePM, NomePM, CodiceFiscalePM);
			}
		});
		btnNewButton.setBounds(229, 190, 103, 39);
		contentPane.add(btnNewButton);
	}
}
