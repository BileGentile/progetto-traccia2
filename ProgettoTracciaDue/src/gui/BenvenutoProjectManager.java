
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
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BenvenutoProjectManager extends JFrame {

	private JPanel contentPane;

	Controller IlControllore;
	
	public BenvenutoProjectManager(Controller c) {
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

		JButton btnNewButton = new JButton("Aggiungi progetto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=1;
				IlControllore.AvviaCreaProgetto(caso);
			}
		});
			
		JButton btnNewButton_1 = new JButton("Consegna progetto");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.AvviaEliminaProgetto();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Crea Meeting");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			IlControllore.AvviaCreaMeeting();
			}
			});
		
		JButton btnNewButton_3 = new JButton("Aggiungi Membri al progetto");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso =0;
					IlControllore.AvviaInserimentoMembri(caso);
				
			}
		});
		
		JButton btnNewButton_4 = new JButton("Valuta Membro");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.AvviaValutazione();
			}
		});

		JButton btnNewButton_5 = new JButton("Logout");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=1;
				IlControllore.Logout(caso);
			}
		});
		
		JButton btnNewButton_6 = new JButton("Partecipanti Progetto");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.AvviaPartecipantiAlProgetto(0);
			}
		});
		
		JButton btnNewButton_7 = new JButton("Partecipanti Meeting");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnNewButton_7, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
								.addComponent(btnNewButton_5, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(51)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_3))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_4))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_7, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_5))
					.addGap(52))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
