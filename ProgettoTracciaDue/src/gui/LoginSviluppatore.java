package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class LoginSviluppatore extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	Controller 	IlControllore ;
	private JButton btnNewButton_1;
	
	public LoginSviluppatore(Controller c) {
		IlControllore= c;
		setTitle("Azienda -Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 0);
		setMinimumSize(new Dimension(500,350));

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Inserisci codice fiscale");
		lblNewLabel.setBounds(21, 48, 171, 21);
		
		textField = new JTextField();
		textField.setBounds(210, 49, 159, 19);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(250, 217, 135, 51);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.AvviaBenvenutoS(textField.getText());
			}
		});
		
		btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.setBounds(71, 217, 139, 51);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=2;
				IlControllore.TornaPresentazione2(caso);
			}
		});
	
		JLabel BottoneRegistrareS = new JLabel("Non sei registrato? clicca quì");
		BottoneRegistrareS.setBounds(210, 79, 195, 19);
		BottoneRegistrareS.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseClicked(MouseEvent e) {

			IlControllore.AvviaRegistrazioneSviluppatore();
		}
	});
	contentPane.setLayout(null);
	contentPane.add(btnNewButton_1);
	contentPane.add(lblNewLabel);
	contentPane.add(btnNewButton);
	contentPane.add(BottoneRegistrareS);
	contentPane.add(textField);
	
	}
}