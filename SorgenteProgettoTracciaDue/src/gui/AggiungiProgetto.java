package gui;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.Controller;

public class AggiungiProgetto extends JFrame {

	private JPanel contentPane;


	Controller IlControllore;
	private JTextField textField;
	public AggiungiProgetto(Controller c) {
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
		
		JLabel lblNewLabel = new JLabel("Inserisci nome:");
		lblNewLabel.setBounds(38, 45, 152, 29);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(211, 45, 134, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona tipo");
		lblNewLabel_1.setBounds(38, 84, 127, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Seleziona ambito");
		lblNewLabel_1_1.setBounds(38, 131, 127, 29);
		contentPane.add(lblNewLabel_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Economia", "Medicina", "Informatica"}));
		comboBox.setBounds(211, 137, 134, 43);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Ricerca di base", "Ricerca industruale", "Ricerca sperimentale", "Sviluppo sperimentale"}));
		comboBox_1.setBounds(211, 84, 134, 43);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Crea");
		btnNewButton.setBounds(270, 207, 96, 35);
		contentPane.add(btnNewButton);
		
	}
}
