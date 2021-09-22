package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.Controller;
import dao_impl.ProgettoDAOPostgresImpl;
import dao_impl.SkillsDAOPostgresImpl;
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
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


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
		setBounds(100, 90, 388, 300);
		setMinimumSize(new Dimension(650,550));
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JLabel lblNewLabel = new JLabel("Inserisci codice Fiscale");
		
		CodiceFiscaleS = new JTextField();
		CodiceFiscaleS.setColumns(10);
		
		lblInserisciNome = new JLabel("Inserisci nome");
		
		NomeS = new JTextField();
		NomeS.setColumns(10);
		
		JLabel lblInserisciCognome = new JLabel("Inserisci cognome");
		
		CognomeS = new JTextField();
		CognomeS.setColumns(10);
		
		JLabel lblInserisciSalario = new JLabel("Inserisci salario");
		
		salario = new JTextField();
		salario.setColumns(10);
		
		JLabel inserisciSkills = new JLabel("Scegli tra le skill presenti ");
		
		JLabel lbl2 = new JLabel("oppure inseriscine una nuova ");
		
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
		
		JList<String> list = new JList<String>(demoList);
		list.setVisibleRowCount(6);	
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		list.setLayoutOrientation(JList.VERTICAL);		
	
		JTextField textFieldNuovaSkill = new JTextField();
		textFieldNuovaSkill.setBounds(42, 286, 168, 29);
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
		textFieldNuovaSkill.setColumns(10);
		textFieldNuovaSkill.setBounds(42, 286, 168, 29);
		contentPane.add(textFieldNuovaSkill);         

		
		
		JButton btnNewButton = new JButton("Registrati");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IlControllore.RegistraSviluppatore(CognomeS.getText(), NomeS.getText(), CodiceFiscaleS.getText(), salario.getText(), list.getSelectedValuesList());	
			}
		});
		
		JButton btnNewButton_2 = new JButton("Torna indietro");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso;
				caso=3;
				IlControllore.TornaLogin(caso);
			}
		});
	
		JLabel lblQuSotto = new JLabel("qu\u00EC sotto");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addComponent(lblInserisciCognome, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
							.addGap(45)
							.addComponent(CognomeS, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addComponent(lblInserisciSalario, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
							.addGap(65)
							.addComponent(salario, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(inserisciSkills, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuSotto, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl2, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
							.addGap(45)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(183)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblInserisciNome, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
									.addGap(65)
									.addComponent(NomeS, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
									.addGap(60)
									.addComponent(CodiceFiscaleS, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(115, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(15)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(CodiceFiscaleS, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(16)
							.addComponent(NomeS, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblInserisciNome, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblInserisciCognome, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(CognomeS, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInserisciSalario, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(salario, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(inserisciSkills, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblQuSotto, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addComponent(lbl2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);	
	}
}
