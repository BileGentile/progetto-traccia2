package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.Controller;
import dao_impl.MembroDAOPostgresImpl;
import dao_impl.SkillsDAOPostgresImpl;
import daos.MembroDAO;
import daos.SkillsDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Membro;
import entity.Skills;
import exceptions.ConnectionException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JFormattedTextField;

public class RegistrazioneSviluppatore extends JFrame {
	
	private JPanel contentPane;

	private JTextField CodiceFiscaleS;

	private JButton btnNewButton_1;
	Controller IlControllore;
	private JLabel lblInserisciNome;
	private JTextField NomeS;
	private JTextField CognomeS;
	private JTextField salario;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxOrganizzazione;
	private JCheckBox chckbxProblemsolving;
	private JCheckBox chckbxEmpatia;
	
	
	public RegistrazioneSviluppatore(Controller c) {

		IlControllore =c;
		
		setTitle("Azienda - Registrazione Sviluppatore");  
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneSviluppatore.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 150, 450, 341);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Inserisci codice Fiscale");
		lblNewLabel.setBounds(15, 42, 168, 19);
		contentPane.add(lblNewLabel);
		
		CodiceFiscaleS = new JTextField();
		CodiceFiscaleS.setBounds(185, 42, 168, 19);
		contentPane.add(CodiceFiscaleS);
		CodiceFiscaleS.setColumns(10);
		
		lblInserisciNome = new JLabel("Inserisci nome");
		lblInserisciNome.setBounds(15, 74, 163, 29);
		contentPane.add(lblInserisciNome);
		
		NomeS = new JTextField();
		NomeS.setColumns(10);
		NomeS.setBounds(185, 79, 168, 19);
		contentPane.add(NomeS);
		
		
		JLabel lblInserisciCognome = new JLabel("Inserisci cognome");
		lblInserisciCognome.setBounds(15, 113, 183, 19);
		contentPane.add(lblInserisciCognome);
		
		CognomeS = new JTextField();
		CognomeS.setColumns(10);
		CognomeS.setBounds(185, 113, 168, 19);
		contentPane.add(CognomeS);
		
		JLabel lblInserisciSalario = new JLabel("Inserisci salario");
		lblInserisciSalario.setBounds(15, 142, 163, 29);
		contentPane.add(lblInserisciSalario);
		
		salario = new JTextField();
		salario.setColumns(10);
		salario.setBounds(185, 147, 168, 19);
		contentPane.add(salario);
		
		JLabel inserisciSkills = new JLabel("Inserisci le skill");
		inserisciSkills.setBounds(15, 176, 183, 19);
		contentPane.add(inserisciSkills);
		
		
		JButton btnNewButton = new JButton("Registrati");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IlControllore.RegistraSviluppatore(CognomeS, NomeS, CodiceFiscaleS,salario,chckbxNewCheckBox.isSelected(), chckbxOrganizzazione.isSelected(),chckbxProblemsolving.isSelected(), chckbxEmpatia.isSelected());
				
				
			}
		});
		
				
		btnNewButton.setBounds(269, 232, 103, 39);
		contentPane.add(btnNewButton);
		
		chckbxNewCheckBox = new JCheckBox("puntualit\u00E0");
		chckbxNewCheckBox.setBounds(164, 177, 103, 21);
		contentPane.add(chckbxNewCheckBox);
		
		chckbxOrganizzazione = new JCheckBox("organizzazione");
		chckbxOrganizzazione.setBounds(164, 202, 103, 21);
		contentPane.add(chckbxOrganizzazione);
		
		chckbxProblemsolving = new JCheckBox("ProblemSolving");
		chckbxProblemsolving.setBounds(269, 177, 127, 21);
		contentPane.add(chckbxProblemsolving);
		
		chckbxEmpatia = new JCheckBox("Empatia");
		chckbxEmpatia.setBounds(269, 200, 127, 21);
		contentPane.add(chckbxEmpatia);
		

	}
}