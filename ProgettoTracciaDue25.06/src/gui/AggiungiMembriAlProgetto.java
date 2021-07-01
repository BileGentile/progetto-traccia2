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

public class AggiungiMembriAlProgetto extends JFrame {

	private JPanel contentPane;
	Controller IlControllore;
	private JComboBox ComboBoxMembri;
	private JTextField Salario;
	
	public AggiungiMembriAlProgetto(Controller c) {
		IlControllore = c;
		setTitle("Azienda - Aggiungi membri al progetto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 342);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserisci valore del \r\n");
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
		
		
		Salario = new JTextField();
		Salario.setBounds(211, 88, 156, 29);
		contentPane.add(Salario);
		Salario.setColumns(10);

		
		JButton btnNewButton = new JButton("Crea");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.CreaArchivioPartecipanti(ComboBoxMembri.getSelectedItem().toString(),ComboBoxProgetti.getSelectedItem().toString());
				
			}
		});
		btnNewButton.setBounds(268, 249, 119, 46);
		contentPane.add(btnNewButton);
		
		
		JLabel lblSalarioMedioDel = new JLabel("Salario medio del membro");
		lblSalarioMedioDel.setBounds(38, 106, 146, 21);
		contentPane.add(lblSalarioMedioDel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Inseriscis skill ricercata");
		lblNewLabel_1_1.setBounds(38, 137, 128, 29);
		contentPane.add(lblNewLabel_1_1);
		
		ComboBoxMembri = new JComboBox();
		ComboBoxMembri.setMaximumRowCount(10);
		ComboBoxMembri.setBounds(211, 204, 155, 29);
		contentPane.add(ComboBoxMembri);
		
		JComboBox ComboBoxMembri_1 = new JComboBox();
		ComboBoxMembri_1.setModel(new DefaultComboBoxModel(new String[] {"Puntualit\u00E0", "Organizzazione", "Problem Solving", "Empatia"}));
		ComboBoxMembri_1.setMaximumRowCount(10);
		ComboBoxMembri_1.setBounds(211, 127, 155, 29);
		contentPane.add(ComboBoxMembri_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Seleziona membro");
		lblNewLabel_1_1_1.setBounds(38, 207, 128, 29);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnR = new JButton("Ricerca membri");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboBoxMembri.removeAllItems();
				DBConnection dbconn = null;
		        Connection connection = null;
		        DBBuilder builder = null;
		        try
			        {
			            dbconn = DBConnection.getInstance();
			            connection = dbconn.getConnection();
			            builder = new DBBuilder(connection);
			            MembroDAO dao = null;
			            
			            dao = new MembroDAOPostgresImpl(connection);
			            List<Membro> listaMembri = dao.getSviluppatoreBySalarioESkills(Integer.parseInt(Salario.getText().toString()),ComboBoxMembri_1.getSelectedItem().toString()); 
			      
			            for(Membro n : listaMembri)
			            {
			            	
			            	ComboBoxMembri.addItem(n.getCF());
			            }

			        }
				catch (SQLException exception)
		  	{
		          System.out.println("Errore SQLException: "+ exception.getMessage());
		  	}
			}
		});
		btnR.setBounds(142, 166, 128, 28);
		contentPane.add(btnR);
		
		
		
		
		
	
		
	}
}
