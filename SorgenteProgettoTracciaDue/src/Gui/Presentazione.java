package Gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import app.Controller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Presentazione extends JFrame {

	private JPanel contentPane;
	Controller IlControllore;	


	public Presentazione(Controller c){
		IlControllore = c;

		setTitle("Azienda- Benvenuto");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sara\\Desktop\\ingranaggio-blu.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Benvenuto nell'ufficiale app dell'azienda,");
		lblNewLabel.setBounds(23, 35, 326, 29);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Project Manager");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.LoginProjectManager();
			}
		});
		btnNewButton.setBounds(35, 163, 141, 43);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sviluppatore");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.LoginProjectManager();
			}
		});
		btnNewButton_1.setBounds(215, 163, 134, 43);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Clicca sul tuo ruolo:");
		lblNewLabel_1.setBounds(23, 65, 279, 13);
		contentPane.add(lblNewLabel_1);
		
		
	}
}

