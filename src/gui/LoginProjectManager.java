package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Controller;

import java.awt.Toolkit;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;

public class LoginProjectManager extends JFrame {

	private JPanel contentPane;
	private JTextField codiceFiscale;

	private JButton btnNewButton_1;
	Controller IlControllore;
	
	public LoginProjectManager(Controller c) {
		IlControllore =c;
		
		setTitle("Azienda- Login");

		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserisci codice fiscale");
		lblNewLabel.setBounds(30, 48, 163, 55);
		contentPane.add(lblNewLabel);
		
		codiceFiscale = new JTextField();
		codiceFiscale.setBounds(185, 66, 176, 19);
		contentPane.add(codiceFiscale);
		codiceFiscale.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.AvviaBenvenutoPM(codiceFiscale);
			}
		});
		btnNewButton.setBounds(242, 183, 119, 41);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				IlControllore.PresentazioneIniziale();
			}
		});
		btnNewButton_1.setBounds(34, 183, 127, 41);
		contentPane.add(btnNewButton_1);
		
		JLabel BottoneRegistrarePW = new JLabel("Non sei registrato? clicca quì");
		BottoneRegistrarePW.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				IlControllore.AvviaRegistrazioneProjectManager();
			}
		});
		BottoneRegistrarePW.setBounds(185, 95, 195, 19);
		contentPane.add(BottoneRegistrarePW);
	}
}
