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
import dao_impl.MeetingDAOPostgresImpl;
import dao_impl.MeetingFisicoDAOPostgresImpl;
import dao_impl.MeetingTelematicoDAOPostgresImpl;
import dao_impl.MembroDAOPostgresImpl;
import dao_impl.ProgettoDAOPostgresImpl;
import dao_impl.SviluppatoreDAOPostgresImpl;
import daos.MeetingDAO;
import daos.MeetingFisicoDAO;
import daos.MeetingTelematicoDAO;
import daos.MembroDAO;
import daos.ProgettoDAO;
import daos.SviluppatoreDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Meeting;
import entity.MeetingFisico;
import entity.MeetingTelematico;
import entity.Membro;
import entity.Progetto;
import entity.Sviluppatore;

import java.awt.FlowLayout;
import java.awt.ScrollPane;

import javax.swing.JDesktopPane;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.Scrollbar;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PartecipantiAlMeeting extends JFrame {

	private JPanel contentPane;
	
	Controller IlControllore;
	private JTable table;
	
	public PartecipantiAlMeeting(Controller c) {
		IlControllore= c;
		
		setTitle("Azienda - Partecipanti Al Meeting");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ValutazioneMembro.class.getResource("/image/ingranaggio blu.png"))); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 650);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona Meeting");
		lblNewLabel_1.setBounds(30, 135, 128, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Seleziona Tipologia Meeting");
		lblNewLabel_3.setBounds(30, 77, 160, 29);
		contentPane.add(lblNewLabel_3);
		
		JComboBox ComboBoxTipologia = new JComboBox();
		ComboBoxTipologia.setModel(new DefaultComboBoxModel(new String[] { "Telematico","Fisico"}));
		ComboBoxTipologia.setToolTipText("");
		ComboBoxTipologia.setBounds(267, 74, 175, 35);
		contentPane.add(ComboBoxTipologia);
		
		JButton btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=11;
				IlControllore.RitornaBenvenutoProjectManager(caso);
			}
		});
		btnNewButton_1.setBounds(35, 537, 173, 50);
		contentPane.add(btnNewButton_1);
		
		JLabel lblCfPm = new JLabel("Codice fiscale del Project Manager");
		lblCfPm.setBounds(30, 29, 175, 28);
		contentPane.add(lblCfPm);
		
		JTextField cf = new JTextField();
		cf.setBounds(267, 26, 175, 34);
		contentPane.add(cf);
		cf.setColumns(10);
		
		JComboBox ComboBoxMeeting = new JComboBox();
		ComboBoxMeeting.setMaximumRowCount(10);
				
		ComboBoxMeeting.setBounds(267, 130, 175, 39);
		contentPane.add(ComboBoxMeeting);
		
		JButton btnR = new JButton("Ricerca Meeting");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboBoxMeeting.removeAllItems();
				DBConnection dbconn = null;
		        Connection connection = null;
		        DBBuilder builder = null;
		        try
		        {
		        	dbconn = DBConnection.getInstance();
		            connection = dbconn.getConnection();
		            builder = new DBBuilder(connection);
		            
		            if(ComboBoxTipologia.getSelectedItem().toString().equals("Telematico")) {
						MeetingTelematicoDAO dao = null;
			            
						dao = new MeetingTelematicoDAOPostgresImpl(connection);           
						List<MeetingTelematico> lista = dao.getMeetingTelematicoProjectManager(cf.getText().toString());
						for(MeetingTelematico p : lista)
						{
							ComboBoxMeeting.addItem (p.getTitolo());
						}
					}else {
						MeetingFisicoDAO dao = null;
			            
						dao = new MeetingFisicoDAOPostgresImpl(connection);           
						List<MeetingFisico> lista = dao.getMeetingTelematicoProjectManager(cf.getText().toString());
						for(MeetingFisico p : lista)
						{
							ComboBoxMeeting.addItem (p.getTitolo());
						}
					}
		      
			    }
		        catch (SQLException exception)
	        	{
	                System.out.println("Errore SQLException: "+ exception.getMessage());
	        	}
			}
		});
		btnR.setBounds(514, 72, 175, 39);
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
			table.setBounds(46, 239, 553, 183);
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
		            
		            if(ComboBoxTipologia.getSelectedItem().toString().equals("Telematico")) {
		            	MeetingTelematicoDAO dao = null;
			            dao = new MeetingTelematicoDAOPostgresImpl(connection);
			            Meeting meet1=dao.getMeetingTelematicoByTitolo(ComboBoxMeeting.getSelectedItem().toString());
			            List<Sviluppatore> listaPartecipanti = dao.cercaPartecipantiMeeting(meet1.getCodMeet());
			            for(Sviluppatore m : listaPartecipanti)
				        {
							DefaultTableModel model1 = (DefaultTableModel) table.getModel();
							model1.addRow(new Object[]{ m.getNome(), m.getCognome(), m.getCF()});
				        }
					}else {
						MeetingFisicoDAO dao = null;
						dao = new MeetingFisicoDAOPostgresImpl(connection); 
						Meeting meet1=dao.getMeetingFisicoByTitolo(ComboBoxMeeting.getSelectedItem().toString());
			            List<Sviluppatore> listaPartecipanti = dao.cercaPartecipantiMeeting(meet1.getCodMeet());
			            for(Sviluppatore m : listaPartecipanti)
				        {
							DefaultTableModel model1 = (DefaultTableModel) table.getModel();
							model1.addRow(new Object[]{ m.getNome(), m.getCognome(), m.getCF()});
				        }
					}
		      
		        }
				catch (SQLException exception)
				{
					System.out.println("Errore SQLException: "+ exception.getMessage());
				}
	            }
			});
		
		btnR1.setBounds(514, 135, 175, 39);
		contentPane.add(btnR1);	
		
	}
}
