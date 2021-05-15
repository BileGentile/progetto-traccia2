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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AggiungiMembroAlProgetto extends JFrame {

	private JPanel contentPane;
	Controller IlControllore;
	private JComboBox ComboBoxMembri;
	
	public AggiungiMembroAlProgetto(Controller c) {
		IlControllore = c;
		setTitle("Azienda - Aggiungi membri al progetto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Saleziona valore del \r\n");
		lblNewLabel.setBounds(38, 87, 146, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona progetto");
		lblNewLabel_1.setBounds(38, 45, 128, 29);
		contentPane.add(lblNewLabel_1);
		
		
		JComboBox ComboBoxProgetti = new JComboBox();
		ComboBoxProgetti.setMaximumRowCount(10);
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
			            
			            List<Progetto> listaProgetti = dao.getAllProgetti();
			            for(Progetto m : listaProgetti)
			            {
			            	 ComboBoxProgetti.addItem(m.getNomeProgetto());
			            }

			        }
	        		catch (SQLException exception)
		        	{
		                System.out.println("Errore SQLException: "+ exception.getMessage());
		        	}
		ComboBoxProgetti.setBounds(211, 42, 155, 35);
		contentPane.add(ComboBoxProgetti);
		
		
		
		JButton btnNewButton = new JButton("Crea");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(270, 207, 96, 35);
		contentPane.add(btnNewButton);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(500), new Integer(500), null, new Integer(10)));
		spinner.setBounds(211, 88, 50, 39);
		contentPane.add(spinner);
		
		JLabel lblSalarioMedioDel = new JLabel("Salario medio del membro");
		lblSalarioMedioDel.setBounds(38, 106, 146, 21);
		contentPane.add(lblSalarioMedioDel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Seleziona membro");
		lblNewLabel_1_1.setBounds(38, 137, 128, 29);
		contentPane.add(lblNewLabel_1_1);
		
		ComboBoxMembri = new JComboBox();
		ComboBoxMembri.setMaximumRowCount(10);
		 try
	        {
	            dbconn = DBConnection.getInstance();
	            connection = dbconn.getConnection();
	            builder = new DBBuilder(connection);
	            MembroDAO dao = null;
	            
	            dao = new MembroDAOPostgresImpl(connection);
	            
	            List<Membro> listaMembri = dao.getSviluppatoreBySalario(Integer.parseInt(spinner.getValue().toString())); 
	            //con getValue esce l'ogetto selezionato, con to string diventa una stringa e con Integer.parseInt diventa intero 
	            System.out.println("ecco:"+spinner.getValue().toString());
	            for(Membro n : listaMembri)
	            {
	            	
	            	ComboBoxMembri.addItem(n.getCF());
	            }

	        }
		catch (SQLException exception)
  	{
          System.out.println("Errore SQLException: "+ exception.getMessage());
  	}
		ComboBoxMembri.setBounds(211, 132, 155, 35);
		contentPane.add(ComboBoxMembri);
		
	
		
	}
}