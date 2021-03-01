package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Controller;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BenvenutoSviluppatore extends JFrame {

	private JPanel contentPane;
	
	Controller IlControllore;

	public BenvenutoSviluppatore(Controller c) {
		IlControllore =c;
		setTitle("Azienda- Benvenuto Sviluppatore");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sara\\Desktop\\ingranaggio-blu.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Benvenuto sviluppatore, cosa desideri fare?");
		lblNewLabel.setBounds(10, 25, 405, 58);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Aggiungi presenza");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 152, 159, 28);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Mostra dati personali");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(217, 152, 159, 28);
		contentPane.add(btnNewButton_1);
		
	}

}
