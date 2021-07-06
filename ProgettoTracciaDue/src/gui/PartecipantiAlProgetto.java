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
import javax.swing.JTextArea;

public class PartecipantiAlProgetto extends JFrame {

	private JPanel contentPane;
	
	Controller IlControllore;
	
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
		btnNewButton_1.setBounds(35, 537, 103, 39);
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
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(33, 212, 438, 294);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		
		JButton btnR1 = new JButton("Ricerca Partecipanti");
		btnR1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(""); 
				int cont=1;
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
		            	textArea.setText(textArea.getText().concat("\n"+cont+" "+m.toString()));
		            	cont++;
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
