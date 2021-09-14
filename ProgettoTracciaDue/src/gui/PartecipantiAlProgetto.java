package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.Controller;
import dao_impl.MembroDAOPostgresImpl;
import dao_impl.ProgettoDAOPostgresImpl;
import dao_impl.SviluppatoreDAOPostgresImpl;
import daos.MembroDAO;
import daos.ProgettoDAO;
import daos.SviluppatoreDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Membro;
import entity.Progetto;
import entity.Sviluppatore;

import java.awt.FlowLayout;
import java.awt.ScrollPane;

import javax.swing.JDesktopPane;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JTextArea;
import java.awt.Scrollbar;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PartecipantiAlProgetto extends JFrame {

	private JPanel contentPane;
	
	Controller IlControllore;
	private JTable table;
	
	public PartecipantiAlProgetto(Controller c) {
		IlControllore= c;
		
		setTitle("Azienda - Partecipanti Al Progetto");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ValutazioneMembro.class.getResource("/image/ingranaggio blu.png"))); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 650);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona progetto");
		lblNewLabel_1.setBounds(33, 114, 128, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=7;
				IlControllore.RitornaBenvenutoProjectManager(caso);
			}
		});
		btnNewButton_1.setBounds(35, 537, 173, 50);
		contentPane.add(btnNewButton_1);
		
		JLabel lblCfPm = new JLabel("Codice fiscale del Project Manager");
		lblCfPm.setBounds(33, 49, 175, 21);
		contentPane.add(lblCfPm);
		
		JTextField cf = new JTextField();
		cf.setBounds(267, 40, 175, 34);
		contentPane.add(cf);
		cf.setColumns(10);
		
		JComboBox ComboBoxProgetti = new JComboBox();
		ComboBoxProgetti.setMaximumRowCount(10);
				
		ComboBoxProgetti.setBounds(267, 109, 175, 39);
		contentPane.add(ComboBoxProgetti);
		
		JButton btnR = new JButton("Ricerca Progetti");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboBoxProgetti.removeAllItems();
				DBConnection dbconn = null;
		        Connection connection = null;
		        DBBuilder builder = null;
		        try
		        {
		        	dbconn = DBConnection.getInstance();
		            connection = dbconn.getConnection();
		            builder = new DBBuilder(connection);
		            ProgettoDAO dao = null;
		            
		            dao = new ProgettoDAOPostgresImpl(connection);
		            
		            List<Progetto> listaProgetti = dao.getProgettoProjectManager(cf.getText().toString());
		            for(Progetto m : listaProgetti)
		            {
		            	ComboBoxProgetti.addItem(m.getNomeProgetto());
		            }
			    }
		        catch (SQLException exception)
	        	{
	                System.out.println("Errore SQLException: "+ exception.getMessage());
	        	}
			}
		});
		btnR.setBounds(514, 40, 175, 39);
		contentPane.add(btnR);
		
	      
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{"Nome", "Cognome", "Codice Fiscale"},
				},
				new String[] {
					"Nome", "Cognome", "Codice Fiscale"
				}
			));
			table.setBounds(46, 239, 450, 183);
			contentPane.add(table);		
			
		JButton btnR1 = new JButton("Ricerca Partecipanti");
		btnR1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            
				DBConnection dbconn = null;
		        Connection connection = null;
		        DBBuilder builder = null;
		 
		        while(table.getRowCount()!=1){
				DefaultTableModel model1 = (DefaultTableModel) table.getModel(); 
				model1.removeRow(1);
		        }
				try
		        {
					dbconn = DBConnection.getInstance();
		            connection = dbconn.getConnection();
		            builder = new DBBuilder(connection);
		            SviluppatoreDAO dao = null;
		            
		            dao = new SviluppatoreDAOPostgresImpl(connection);
		            
		            List<Sviluppatore> listaPartecipanti = dao.getPartecipantiProgettoPS(ComboBoxProgetti.getSelectedItem().toString());
		           
					for(Sviluppatore m : listaPartecipanti)
			        {
						DefaultTableModel model1 = (DefaultTableModel) table.getModel();
						model1.addRow(new Object[]{ m.getNome(), m.getCognome(), m.getCF()});
			        }
		        }
				catch (SQLException exception)
				{
					System.out.println("Errore SQLException: "+ exception.getMessage());
				}
	            }
			});
		
		btnR1.setBounds(514, 109, 175, 39);
		contentPane.add(btnR1);	
		
	}
}
