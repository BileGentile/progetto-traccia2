package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import app.Controller;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class LoginSviluppatore extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	Controller 	IlControllore ;
	private JButton btnNewButton_1;
	public LoginSviluppatore(Controller c) {
		IlControllore= c;
		setTitle("Azienda -Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sara\\Desktop\\ingranaggio-blu.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserisci codice fiscale");
		lblNewLabel.setBounds(21, 48, 171, 21);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(233, 49, 129, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.AvviaBenvenutoS(textField.getText());
			}
		});
		btnNewButton.setBounds(257, 156, 116, 34);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i= 2;
				IlControllore.TornaPresentazione(i);

			}
		});
		btnNewButton_1.setBounds(22, 156, 129, 34);
		contentPane.add(btnNewButton_1);
	}

}
