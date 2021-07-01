package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.Controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RegistrazioneSviluppatore extends JFrame {
	
	private JPanel contentPane;

	private JTextField CodiceFiscaleS;

	private JButton btnNewButton_1;
	Controller IlControllore;
	private JLabel lblInserisciNome;
	private JTextField NomeS;
	private JTextField CognomeS;
	private JTextField textField;
	
	
	public RegistrazioneSviluppatore(Controller c) {

		IlControllore =c;
		
		setTitle("Azienda - Registrazione Sviluppatore");  
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneSviluppatore.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Inserisci codice Fiscale");
		lblNewLabel.setBounds(15, 42, 168, 19);
		contentPane.add(lblNewLabel);
		
		CodiceFiscaleS = new JTextField();
		CodiceFiscaleS.setBounds(185, 42, 168, 19);
		contentPane.add(CodiceFiscaleS);
		CodiceFiscaleS.setColumns(10);
		
		lblInserisciNome = new JLabel("Inserisci nome");
		lblInserisciNome.setBounds(15, 74, 163, 29);
		contentPane.add(lblInserisciNome);
		
		NomeS = new JTextField();
		NomeS.setColumns(10);
		NomeS.setBounds(185, 79, 168, 19);
		contentPane.add(NomeS);
		
		JLabel lblInserisciCognome = new JLabel("Inserisci cognome");
		lblInserisciCognome.setBounds(15, 113, 183, 19);
		contentPane.add(lblInserisciCognome);
		
		CognomeS = new JTextField();
		CognomeS.setColumns(10);
		CognomeS.setBounds(185, 113, 168, 19);
		contentPane.add(CognomeS);
		
		JButton btnNewButton = new JButton("Registrati");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IlControllore.RegistraSviluppatore(CognomeS, NomeS, CodiceFiscaleS,textField);
				
			}
		});
		
				
		btnNewButton.setBounds(256, 203, 103, 39);
		contentPane.add(btnNewButton);
		
		JLabel lblInserisciSalario = new JLabel("Inserisci salario");
		lblInserisciSalario.setBounds(15, 142, 163, 29);
		contentPane.add(lblInserisciSalario);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(185, 147, 168, 19);
		contentPane.add(textField);
	}
}