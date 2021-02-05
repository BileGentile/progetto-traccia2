package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Controller;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BenvenutoProjectManager extends JFrame {

	private JPanel contentPane;

	Controller IlControllore;
	public BenvenutoProjectManager(Controller c) {
		IlControllore =c;
		setTitle("Azienda- Benvenuto Project Manager");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Sara\\Desktop\\ingranaggio-blu.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Benvenuto project manager, cosa desideri fare?");
		lblNewLabel.setBounds(10, 25, 405, 58);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(0, 0, 0, 0);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton = new JButton("Aggiungi progetto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.CreaProgetto();
			}
		});
		btnNewButton.setBounds(10, 120, 168, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Elimina progetto");
		btnNewButton_1.setBounds(10, 167, 168, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Modifica progetto");
		btnNewButton_2.setBounds(10, 215, 168, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("Valuta Sviluppatore");
		btnNewButton_4.setBounds(242, 120, 139, 21);
		contentPane.add(btnNewButton_4);
	}
}
