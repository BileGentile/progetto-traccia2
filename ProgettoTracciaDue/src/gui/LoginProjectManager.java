package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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
		setBounds(100, 100, 419, 300);
		setMinimumSize(new Dimension(450,340));

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Inserisci codice fiscale");
		lblNewLabel.setBounds(30, 48, 163, 55);
		
		codiceFiscale = new JTextField();
		codiceFiscale.setBounds(185, 66, 179, 19);
		codiceFiscale.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(250, 217, 135, 51);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.AvviaBenvenutoPM(codiceFiscale);
			}
		});
		
		btnNewButton_1 = new JButton("Torna indietro");		
		btnNewButton_1.setBounds(71, 217, 139, 51);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=1;
				IlControllore.TornaPresentazione2(caso);
			}
		});
		
		JLabel BottoneRegistrarePW = new JLabel("Non sei registrato? clicca quì");
		BottoneRegistrarePW.setBounds(185, 95, 195, 19);
		BottoneRegistrarePW.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				IlControllore.AvviaRegistrazioneProjectManager();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(codiceFiscale);
		contentPane.add(lblNewLabel);
		contentPane.add(btnNewButton_1);
		contentPane.add(btnNewButton);
		contentPane.add(BottoneRegistrarePW);
	}
}