package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.Controller;
import dao_impl.MeetingTelematicoDAOPostgresImpl;
import dao_impl.SkillsDAOPostgresImpl;
import daos.MeetingTelematicoDAO;
import daos.SkillsDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Ambito;
import entity.MeetingTelematico;
import entity.Skills;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.UIManager;
import javax.swing.JComboBox;

public class RegistrazioneProjectManager extends JFrame {

	private JPanel contentPane;
	private JTextField CodiceFiscalePM;

	private JButton btnNewButton_1;
	Controller IlControllore;
	private JLabel lblInserisciNome;
	private JTextField NomePM;
	private JTextField CognomePM;
	private JTextField SalarioMedio;
	private JLabel InserisciSalario;
	private JLabel lblNewLabel_1;
	
	
		public RegistrazioneProjectManager(Controller c) {
		IlControllore = c;
		
		setTitle("Azienda - Registrazione Project Manager");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 90, 388, 300);
		setMinimumSize(new Dimension(550,450));
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Inserisci codice fiscale");
		lblNewLabel.setBounds(20, 30, 163, 55);
		contentPane.add(lblNewLabel);
		
		CodiceFiscalePM = new JTextField();
		CodiceFiscalePM.setBounds(227, 47, 176, 19);
		contentPane.add(CodiceFiscalePM);
		CodiceFiscalePM.setColumns(10);
		
		lblInserisciNome = new JLabel("Inserisci nome");
		lblInserisciNome.setBounds(20, 72, 163, 29);
		contentPane.add(lblInserisciNome);
		
		NomePM = new JTextField();
		NomePM.setColumns(10);
		NomePM.setBounds(227, 76, 176, 19);
		contentPane.add(NomePM);
		
		JLabel lblInserisciCognome = new JLabel("Inserisci cognome");
		lblInserisciCognome.setBounds(20, 101, 163, 29);
		contentPane.add(lblInserisciCognome);
		
		CognomePM = new JTextField();
		CognomePM.setColumns(10);
		CognomePM.setBounds(227, 105, 176, 19);
		contentPane.add(CognomePM);

		DefaultListModel<String> demoList = new DefaultListModel<>();
		
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
            	demoList.addElement(m.getNomeSkill());
            }

        }
		catch (SQLException exception)
    	{
            System.out.println("Errore SQLException: "+ exception.getMessage());
    	}

		
		JList<String> listskills = new JList<String>(demoList);		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(listskills);
		scrollPane.setBounds(227, 165, 176, 88);
		listskills.setLayoutOrientation(JList.VERTICAL);
		
		contentPane.add(scrollPane);
		
		//AGGIUNGE UNA NUOVA SKILL ALLA TABELLA DI SKILL GIA' PRESENTI NEL DATABASE SOLO SE E' UNA SKILL DI NOME DIVERSO DA QUELLI GIA' PRESENTI 
		JTextField textFieldNuovaSkill = new JTextField();
		textFieldNuovaSkill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

			DBConnection dbconn = null;
			Connection connection = null;
			DBBuilder builder = null;
			try
	        {
				dbconn = DBConnection.getInstance();
	            connection = dbconn.getConnection();
	            builder = new DBBuilder(connection);
	            SkillsDAO dao2 = null;
	            
	            
	            dao2 = new SkillsDAOPostgresImpl(connection);	
	            Skills s = new Skills(textFieldNuovaSkill.getText(),"sequenzacodiceskills");
        		int res1= dao2.inserisciSkills(s);
        		demoList.addElement(s.getNomeSkill());
        		JList<String> listskills1 = new JList<String>(demoList);
        		contentPane.add(listskills1);
	        }
			catch (SQLException exception)
	    	{
	            System.out.println("Errore SQLException: "+ exception.getMessage());
	    	}
			
		}
		});
		textFieldNuovaSkill.setColumns(15);
		textFieldNuovaSkill.setBounds(20, 207, 136, 29);
		contentPane.add(textFieldNuovaSkill);         

		
		
		JButton btnNewButton = new JButton("Registrati");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.RegistraProjectManager( NomePM.getText(),CognomePM.getText(), CodiceFiscalePM.getText(), SalarioMedio.getText(), listskills.getSelectedValuesList());
			}
		});
		btnNewButton.setBounds(338, 297, 103, 39);
		contentPane.add(btnNewButton);
		
		SalarioMedio = new JTextField();
		SalarioMedio.setColumns(10);
		SalarioMedio.setBounds(227, 135, 176, 19);
		contentPane.add(SalarioMedio);
		
		InserisciSalario = new JLabel("Inserisci salario\r\n");
		InserisciSalario.setBounds(20, 130, 163, 29);
		contentPane.add(InserisciSalario);
		
		JButton btnNewButton_1_1 = new JButton("Torna indietro");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso;
				caso=2;
				IlControllore.TornaLogin (caso);
			}
		});
		btnNewButton_1_1.setBounds(20, 297, 120, 39);
		contentPane.add(btnNewButton_1_1);
		
		lblNewLabel_1 = new JLabel("Scegli tra le skill presenti ");
		lblNewLabel_1.setBounds(20, 169, 144, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lbl2 = new JLabel("oppure inseriscine una nuova qui");
		lbl2.setBounds(20, 171, 168, 36);
		contentPane.add(lbl2);

		}
}
