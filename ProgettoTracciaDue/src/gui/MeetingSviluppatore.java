package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import app.Controller;
import daos.MeetingDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Meeting;
import dao_impl.MeetingDAOPostgresImpl;

public class MeetingSviluppatore extends JFrame {

private JPanel contentPane;
	
	Controller IlControllore;
	private JTable table;
	
	public MeetingSviluppatore(Controller c) {
		IlControllore= c;
		
		setTitle("Azienda -I Miei Meeting");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ValutazioneMembro.class.getResource("/image/ingranaggio blu.png"))); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 650);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=3;
				IlControllore.RitornaBenvenutoSviluppatore(caso);
			}
		});
		btnNewButton_1.setBounds(35, 537, 173, 50);
		contentPane.add(btnNewButton_1);
		
		JLabel lblCf = new JLabel("Inserisci il tuo codice fiscale");
		lblCf.setBounds(30, 29, 175, 28);
		contentPane.add(lblCf);
		
		JTextField cf = new JTextField();
		cf.setBounds(267, 26, 175, 34);
		contentPane.add(cf);
		cf.setColumns(10);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Titolo", "Data", "Ora Inizio", "Ora Fine"},
			},
			new String[] {
					"Titolo", "Data", "Ora Inizio", "Ora Fine"
			}
		));
		table.setBounds(35, 189, 465, 287);
		contentPane.add(table);
		
		JButton btnR = new JButton("Ricerca Progetti");
		btnR.addActionListener(new ActionListener() {
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
		            
					MeetingDAO dao = null;
			            
						dao = new MeetingDAOPostgresImpl(connection);           
						List<Meeting> listaMeeting = dao.getMeetingSviluppatore(cf.getText().toString());
			            for(Meeting m : listaMeeting)
				        {
							DefaultTableModel model1 = (DefaultTableModel) table.getModel();
							model1.addRow(new Object[]{ m.getTitolo(), m.getData(), m.getOraInizio(),m.getOraFine()});
				        }
					}
		     
		        catch (SQLException exception)
	        	{
	                System.out.println("Errore SQLException: "+ exception.getMessage());
	        	}
			}
		});
		btnR.setBounds(267, 96, 175, 39);
		contentPane.add(btnR);
		
	}

}
