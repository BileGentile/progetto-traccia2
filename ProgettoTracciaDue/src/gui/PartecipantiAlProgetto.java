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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.Controller;
import dao_impl.MembroDAOPostgresImpl;
import dao_impl.ProgettoDAOPostgresImpl;
import daos.MembroDAO;
import daos.ProgettoDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Membro;
import entity.Progetto;

import java.awt.FlowLayout;
import javax.swing.JDesktopPane;
import javax.swing.AbstractButton;
import javax.swing.Box;

public class PartecipantiAlProgetto extends JFrame {

	private JPanel contentPane;
	
	Controller IlControllore;
	
	public PartecipantiAlProgetto(Controller c) {
		IlControllore= c;
		
		setTitle("Azienda - Partecipanti Al Progetto");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(ValutazioneMembro.class.getResource("/image/ingranaggio blu.png"))); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona progetto");
		lblNewLabel_1.setBounds(10, 138, 128, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setBounds(23, 271, 99, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblCfPm = new JLabel("Codice fiscale del Project Manager");
		lblCfPm.setBounds(10, 45, 175, 21);
		contentPane.add(lblCfPm);
		
		JTextField cf = new JTextField();
		cf.setBounds(232, 41, 156, 29);
		contentPane.add(cf);
		cf.setColumns(10);
		
		JComboBox ComboBoxProgetti = new JComboBox();
		ComboBoxProgetti.setMaximumRowCount(10);
				
		ComboBoxProgetti.setBounds(110, 138, 128, 29);
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
		btnR.setBounds(232, 88, 156, 28);
		contentPane.add(btnR);
		
		JTextField Partecipanti = new JTextField();
		Partecipanti.setToolTipText("questo testo non è editabile");
		Partecipanti.setEditable(false);
		Partecipanti.setBounds(20, 178, 368, 82);
		contentPane.add(Partecipanti);
		Partecipanti.setColumns(10);
		
		JButton btnR1 = new JButton("Ricerca Partecipanti");
		btnR1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConnection dbconn = null;
		        Connection connection = null;
		        DBBuilder builder = null;
				try
		        {
		            dbconn = DBConnection.getInstance();
		            connection = dbconn.getConnection();
		            builder = new DBBuilder(connection);
		            MembroDAOPostgresImpl dao = null;
		            
		            dao = new MembroDAOPostgresImpl(connection);
		            
		            List<Membro> listaPartecipanti = dao.getPartecipantiProgetto(ComboBoxProgetti.getSelectedItem().toString());
		            for(Membro m : listaPartecipanti)
		            {
		            	Partecipanti.setText(Partecipanti.getText().concat("\n\n"+m.toString()));
		            	System.out.println(m.toString());
		            }

		        }
        		catch (SQLException exception)
	        	{
	                System.out.println("Errore SQLException: "+ exception.getMessage());
	        	}
			}
		});
		btnR1.setBounds(248, 139, 141, 28);
		contentPane.add(btnR1);
		
		
	}
}
