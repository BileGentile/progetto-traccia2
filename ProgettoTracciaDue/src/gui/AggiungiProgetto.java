package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Controller;
import dao_impl.AmbitoDAOPostgresImpl;
import daos.AmbitoDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Ambito;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.AbstractListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class AggiungiProgetto extends JFrame {

	private JPanel contentPane;
	private JTextField nomeProgetto;
	Controller IlControllore;
	private JTextField CodiceFiscalePm;
	private JTextField textFieldNuovoAmbito;
	
	
	public AggiungiProgetto(Controller c) {
		IlControllore = c;
		
		setTitle("Azienda - Aggiungi progetto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 434);
		setMinimumSize(new Dimension(560,450));

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserisci nome progetto:");
		lblNewLabel.setBounds(23, 79, 189, 29);
		contentPane.add(lblNewLabel);
		
		nomeProgetto = new JTextField();
		nomeProgetto.setBounds(235, 79, 134, 29);
		contentPane.add(nomeProgetto);
		nomeProgetto.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona tipo di progetto");
		lblNewLabel_1.setBounds(23, 137, 189, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Seleziona ambito del progetto");
		lblNewLabel_1_1.setBounds(23, 188, 189, 29);
		contentPane.add(lblNewLabel_1_1);
		
		DefaultListModel<String> demoList = new DefaultListModel<>();
		
		DBConnection dbconn = null;
		Connection connection = null;
		DBBuilder builder = null;
		try
        {
			dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            AmbitoDAO dao = null;
            
            dao = new AmbitoDAOPostgresImpl(connection);
            
            List<Ambito> listaAmbito = dao.getAllAmbito();
            for(Ambito m : listaAmbito)
            {
            	demoList.addElement(m.getNomeAmbito());
            }
        }
		catch (SQLException exception)
    	{
            System.out.println("Errore SQLException: "+ exception.getMessage());
    	}	

		JList<String> ListAmbiti = new JList<String>(demoList);		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(ListAmbiti);
		scrollPane.setBounds(227, 185, 176, 88);
		ListAmbiti.setLayoutOrientation(JList.VERTICAL);

		contentPane.add(scrollPane);
		
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Ricerca di base","Ricerca industriale","Ricerca sperimentale", "Sviluppo sperimentale"}));
		comboBox_1.setBounds(235, 130, 134, 43);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Crea");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				IlControllore.CreaProgetto(nomeProgetto.getText(), comboBox_1.getSelectedItem().toString(),ListAmbiti.getSelectedValuesList(), CodiceFiscalePm.getText());
			}
		});
		btnNewButton.setBounds(333, 320, 117, 43);
		contentPane.add(btnNewButton);
		
		CodiceFiscalePm = new JTextField();
		CodiceFiscalePm.setColumns(10);
		CodiceFiscalePm.setBounds(235, 24, 134, 29);
		contentPane.add(CodiceFiscalePm);
		
		JLabel lblInserisciCodiceFiscale = new JLabel("Inserire codice fiscale personale");
		lblInserisciCodiceFiscale.setBounds(23, 24, 202, 29);
		contentPane.add(lblInserisciCodiceFiscale);
		
		JButton btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=4;
				IlControllore.RitornaBenvenutoProjectManager(caso);
			}
		});
		btnNewButton_1.setBounds(83, 320, 117, 43);
		contentPane.add(btnNewButton_1);
		
		//AGGIUNGE UN NUOVO AMBITO ALLA TABELLA DI AMBITI GIA' PRESENTI NEL DATABASE SOLO SE E' UNA SKILL DI NOME DIVERSO DA QUELLI GIA' PRESENTI 
		JTextField textFieldNuovoAmbito = new JTextField();		
		textFieldNuovoAmbito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			

				DBConnection dbconn = null;
				Connection connection = null;
				DBBuilder builder = null;
				try
				{
					dbconn = DBConnection.getInstance();
					connection = dbconn.getConnection();
					builder = new DBBuilder(connection);
					AmbitoDAO dao2 = null;
			            
					dao2 = new AmbitoDAOPostgresImpl(connection);	
					Ambito a = new Ambito(textFieldNuovoAmbito.getText(),"sequenzacodiceambito");
					dao2.inserisciAmbito(a);
					demoList.addElement(a.getNomeAmbito());
					JList<String> listsambiti1 = new JList<String>(demoList);
					contentPane.add(listsambiti1);
				}
				catch (SQLException exception)
				{
					System.out.println("Errore SQLException: "+ exception.getMessage());
			    }
				
			}
		});
		textFieldNuovoAmbito.setBounds(23, 240, 134, 19);
		contentPane.add(textFieldNuovoAmbito);
		textFieldNuovoAmbito.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("o creane uno nuovo:");
		lblNewLabel_1_1_1.setBounds(23, 207, 189, 29);
		contentPane.add(lblNewLabel_1_1_1);
		
	}
}