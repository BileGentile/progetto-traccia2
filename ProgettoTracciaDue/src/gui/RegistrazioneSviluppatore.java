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
import dao_impl.ProgettoDAOPostgresImpl;
import dao_impl.SkillsDAOPostgresImpl;
import daos.MembroDAO;
import daos.ProgettoDAO;
import daos.SkillsDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Membro;
import entity.Progetto;
import entity.Skills;
import exceptions.ConnectionException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
		lblInserisciSalario.setBounds(15, 143, 163, 29);
		contentPane.add(lblInserisciSalario);
		
		salario = new JTextField();
		salario.setColumns(10);
		salario.setBounds(185, 147, 168, 19);
		contentPane.add(salario);
		
		JLabel inserisciSkills = new JLabel("Inserisci le skill");
		inserisciSkills.setBounds(15, 189, 183, 19);
		contentPane.add(inserisciSkills);
		
		JComboBox SkillsJComboBox = new JComboBox();
		SkillsJComboBox.setEditable(true);
		SkillsJComboBox.setMaximumRowCount(10);
			DBConnection dbconn = null;
			Connection connection = null;
			DBBuilder builder = null;
			try
	        {
	            dbconn = DBConnection.getInstance();
	            connection = dbconn.getConnection();
	            builder = new DBBuilder(connection);
	            SkillsDAO dao = null;
	            
	            dao = new SkillsDAOPostgresImpl(connection);
	            
	            List<Skills> listaSkills = dao.getAllSkills();
	            for(Skills m : listaSkills)
	            {
	            	SkillsJComboBox.addItem(m.getSkill());
	            }

	        }
    		catch (SQLException exception)
        	{
                System.out.println("Errore SQLException: "+ exception.getMessage());
        	}

		SkillsJComboBox.setBounds(185, 187, 168, 22);
		contentPane.add(SkillsJComboBox);
		
		
		JButton btnNewButton = new JButton("Registrati");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IlControllore.RegistraSviluppatore(CognomeS, NomeS, CodiceFiscaleS, salario, SkillsJComboBox.getSelectedItem().toString());	
				
			}
		});
		
				
		btnNewButton.setBounds(269, 232, 103, 39);
		contentPane.add(btnNewButton);
		
		
		
		
	}
}
