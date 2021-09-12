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
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.AvviaBenvenutoS(textField.getText());
			}
		});
		
		btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=2;
				IlControllore.TornaPresentazione2(caso);
			}
		});
	
		JLabel BottoneRegistrareS = new JLabel("Non sei registrato? clicca quì");
		BottoneRegistrareS.addMouseListener(new MouseAdapter() {
		
		@Override
		public void mouseClicked(MouseEvent e) {

			IlControllore.AvviaRegistrazioneSviluppatore();
		}
	});
	GroupLayout gl_contentPane = new GroupLayout(contentPane);
	gl_contentPane.setHorizontalGroup(
		gl_contentPane.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPane.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(10, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(16)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(66)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE))
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(BottoneRegistrareS, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))))
				.addGap(76))
	);
	gl_contentPane.setVerticalGroup(
		gl_contentPane.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_contentPane.createSequentialGroup()
				.addGap(43)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(BottoneRegistrareS, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
				.addGap(93)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
	);
	contentPane.setLayout(gl_contentPane);
	
	}
}