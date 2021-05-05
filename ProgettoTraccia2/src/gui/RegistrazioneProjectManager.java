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
	private JTextField SalarioMedio;
	private JLabel lblInserisciSalario;
	
	
		public RegistrazioneProjectManager(Controller c) {
		IlControllore = c;
		
		setTitle("Azienda - Registrazione Project Manager");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Inserisci codice fiscale");
		lblNewLabel.setBounds(20, 30, 163, 55);
		contentPane.add(lblNewLabel);
		
		CodiceFiscalePM = new JTextField();
		CodiceFiscalePM.setBounds(185, 48, 176, 19);
		contentPane.add(CodiceFiscalePM);
		CodiceFiscalePM.setColumns(10);
		
		lblInserisciNome = new JLabel("Inserisci nome");
		lblInserisciNome.setBounds(20, 72, 163, 29);
		contentPane.add(lblInserisciNome);
		
		NomePM = new JTextField();
		NomePM.setColumns(10);
		NomePM.setBounds(185, 77, 176, 19);
		contentPane.add(NomePM);
		
		JLabel lblInserisciCognome = new JLabel("Inserisci cognome");
		lblInserisciCognome.setBounds(20, 101, 163, 29);
		contentPane.add(lblInserisciCognome);
		
		CognomePM = new JTextField();
		CognomePM.setColumns(10);
		CognomePM.setBounds(185, 106, 176, 19);
		contentPane.add(CognomePM);
		
		JButton btnNewButton = new JButton("Registrati");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.RegistraProjectManager(CognomePM.getText(), NomePM.getText(), CodiceFiscalePM.getText(), SalarioMedio.getText());
			}
		});
		btnNewButton.setBounds(258, 190, 132, 39);
		contentPane.add(btnNewButton);
		
		SalarioMedio = new JTextField();
		SalarioMedio.setColumns(10);
		SalarioMedio.setBounds(185, 135, 176, 19);
		contentPane.add(SalarioMedio);
		
		lblInserisciSalario = new JLabel("Inserisci salario\r\n");
		lblInserisciSalario.setBounds(20, 130, 163, 29);
		contentPane.add(lblInserisciSalario);
		
		JButton btnNewButton_1_1 = new JButton("Torna indietro");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso;
				caso=2;
				IlControllore.TornaLogin(caso);
			}
		});
		btnNewButton_1_1.setBounds(105, 190, 120, 39);
		contentPane.add(btnNewButton_1_1);
	}
}
