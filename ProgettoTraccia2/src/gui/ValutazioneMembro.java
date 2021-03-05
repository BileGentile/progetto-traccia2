package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.Controller;

public class ValutazioneMembro extends JFrame {

	private JPanel contentPane;
	Controller IlControllore;
	private JTextField textField;
	
	
	public ValutazioneMembro(Controller c) {
		IlControllore =c;
		setTitle("Azienda - Valutazione Membro");

		setIconImage(Toolkit.getDefaultToolkit().getImage(ValutazioneMembro.class.getResource("/image/ingranaggio blu.png"))); {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserisci codice fiscale ");
		lblNewLabel.setBounds(38, 68, 158, 29);
		contentPane.add(lblNewLabel);
		
	
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona valutazione");
		lblNewLabel_1.setBounds(38, 107, 127, 29);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(235, 73, 142, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblBenvenutoNellaValutazione = new JLabel("Benvenuto nella valutazione dei membri ");
		lblBenvenutoNellaValutazione.setBounds(38, 29, 339, 29);
		contentPane.add(lblBenvenutoNellaValutazione);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBox.setBounds(235, 100, 73, 43);
		contentPane.add(comboBox);
	}

	}
}
