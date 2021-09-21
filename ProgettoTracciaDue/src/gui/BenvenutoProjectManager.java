
package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BenvenutoProjectManager extends JFrame {

	private JPanel contentPane;

	Controller IlControllore;
	
	public BenvenutoProjectManager(Controller c){
		IlControllore =c;
		setTitle("Azienda- Benvenuto Project Manager");

		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 380);
		setMinimumSize(new Dimension(500,450));
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Benvenuto project manager, cosa desideri fare?");
		lblNewLabel.setBounds(34, 56, 405, 58);

		JButton btnNewButton = new JButton("Aggiungi progetto");
		btnNewButton.setBounds(34, 172, 186, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=1;
				IlControllore.AvviaCreaProgetto(caso);
			}
		});
			
		JButton btnNewButton_1 = new JButton("Consegna progetto");
		btnNewButton_1.setBounds(34, 226, 186, 21);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.AvviaEliminaProgetto();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Crea Meeting");
		btnNewButton_2.setBounds(34, 285, 186, 21);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.AvviaCreaMeeting();
			}
			});
		
		JButton btnNewButton_3 = new JButton("Membri al Progetto");
		btnNewButton_3.setBounds(271, 172, 186, 21);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso =0;
					IlControllore.AvviaInserimentoMembri(caso);
			}
		});
		
		JButton btnNewButton_4 = new JButton("Valuta Membro");
		btnNewButton_4.setBounds(271, 226, 186, 21);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.AvviaValutazione();
			}
		});

		JButton btnNewButton_5 = new JButton("Logout");
		btnNewButton_5.setBounds(271, 334, 186, 21);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=1;
				IlControllore.Logout(caso);
			}
		});
		
		JButton btnNewButton_6 = new JButton("Partecipanti Progetto");
		btnNewButton_6.setBounds(271, 285, 186, 21);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.AvviaPartecipantiAlProgetto(0);
			}
		});
		
		JButton btnNewButton_7 = new JButton("Partecipanti Meeting");
		btnNewButton_7.setBounds(34, 333, 186, 23);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.AvviaPartecipantiMeeting();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton_2);
		contentPane.add(btnNewButton_6);
		contentPane.add(btnNewButton_1);
		contentPane.add(btnNewButton);
		contentPane.add(lblNewLabel);
		contentPane.add(btnNewButton_7);
		contentPane.add(btnNewButton_5);
		contentPane.add(btnNewButton_3);
		contentPane.add(btnNewButton_4);
	}
}
