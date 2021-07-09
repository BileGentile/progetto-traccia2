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
	private JTextField CodiceFiscalePm;
	
	
	public AggiungiProgetto(Controller c) {
		IlControllore = c;
		
		setTitle("Azienda - Aggiungi progetto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 380);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserisci nome progetto:");
		lblNewLabel.setBounds(23, 45, 189, 29);
		contentPane.add(lblNewLabel);
		
		nomeProgetto = new JTextField();
		nomeProgetto.setBounds(235, 45, 134, 29);
		contentPane.add(nomeProgetto);
		nomeProgetto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona tipo di progetto");
		lblNewLabel_1.setBounds(23, 84, 189, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Seleziona ambito del progetto");
		lblNewLabel_1_1.setBounds(23, 144, 189, 29);
		contentPane.add(lblNewLabel_1_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Economico", "Medico", "Informatico"}));
		comboBox.setBounds(235, 137, 134, 43);
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Ricerca di base", "Ricerca industruale", "Ricerca sperimentale", "Sviluppo sperimentale"}));
		comboBox_1.setBounds(235, 84, 134, 43);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Crea");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				IlControllore.CreaProgetto(nomeProgetto.getText(),  comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString(), CodiceFiscalePm.getText() );
			}
		});
		btnNewButton.setBounds(273, 264, 116, 43);
		contentPane.add(btnNewButton);
		
		CodiceFiscalePm = new JTextField();
		CodiceFiscalePm.setColumns(10);
		CodiceFiscalePm.setBounds(235, 190, 134, 29);
		contentPane.add(CodiceFiscalePm);
		
		JLabel lblInserisciCodiceFiscale = new JLabel("Inserisci codice fiscale personale");
		lblInserisciCodiceFiscale.setBounds(23, 190, 202, 29);
		contentPane.add(lblInserisciCodiceFiscale);
		
		JButton btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=4;
				IlControllore.RitornaBenvenutoProjectManager(caso);
			}
		});
		btnNewButton_1.setBounds(50, 264, 117, 29);
		contentPane.add(btnNewButton_1);
		
	}
}

