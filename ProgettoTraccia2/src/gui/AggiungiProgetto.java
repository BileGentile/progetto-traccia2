package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Controller;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JTree;
import javax.swing.AbstractListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class AggiungiProgetto extends JFrame {

	private JPanel contentPane;
	private JTextField nomeProgetto;
	Controller IlControllore;
	
	
	public AggiungiProgetto(Controller c) {
		IlControllore = c;
		
		setTitle("Azienda - Aggiungi progetto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
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
		
		nomeProgetto = new JTextField();
		nomeProgetto.setBounds(211, 45, 134, 29);
		contentPane.add(nomeProgetto);
		nomeProgetto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona tipo");
		lblNewLabel_1.setBounds(38, 84, 127, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Seleziona ambito");
		lblNewLabel_1_1.setBounds(38, 131, 127, 29);
		contentPane.add(lblNewLabel_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Economico", "Medico", "Informatico"}));
		comboBox.setBounds(211, 137, 134, 43);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Ricerca di base", "Ricerca industruale", "Ricerca sperimentale", "Sviluppo sperimentale"}));
		comboBox_1.setBounds(211, 84, 134, 43);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Crea");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.CreaProgetto(nomeProgetto.getText(),  comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString());
			}
		});
		btnNewButton.setBounds(270, 207, 96, 35);
		contentPane.add(btnNewButton);
		
	}
}
