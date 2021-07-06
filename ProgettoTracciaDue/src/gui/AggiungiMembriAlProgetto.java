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
		setBounds(100, 100, 588, 444);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserisci valore del \r\n");
		lblNewLabel.setBounds(80, 69, 146, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona progetto");
		lblNewLabel_1.setBounds(80, 29, 128, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=6;
				IlControllore.RitornaBenvenutoProjectManager(caso);
			}
		});
		btnNewButton_1.setBounds(27, 348, 119, 46);
		contentPane.add(btnNewButton_1);
		
		JComboBox comboBoxValutazione = new JComboBox();
		comboBoxValutazione.setBounds(312, 176, 155, 29);
		comboBoxValutazione.setModel(new DefaultComboBoxModel(new String[] {"Buona", "Mediocre", "Male", "NULL"}));
		comboBoxValutazione.setMaximumRowCount(10);
		contentPane.add(comboBoxValutazione);
		
		
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
		ComboBoxProgetti.setBounds(312, 23, 155, 35);
		contentPane.add(ComboBoxProgetti);
		
		
		Salario = new JTextField();
		Salario.setBounds(312, 76, 156, 29);
		contentPane.add(Salario);
		Salario.setColumns(10);

		
		JButton btnNewButton = new JButton("Aggiungi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.CreaArchivioPartecipanti(ComboBoxMembri.getSelectedItem().toString(),ComboBoxProgetti.getSelectedItem().toString());
				
			}
		});
		btnNewButton.setBounds(371, 348, 119, 46);
		contentPane.add(btnNewButton);
		
		
		JLabel lblSalarioMedioDel = new JLabel("Salario medio del membro");
		lblSalarioMedioDel.setBounds(80, 84, 146, 21);
		contentPane.add(lblSalarioMedioDel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Inseriscis skill ricercata");
		lblNewLabel_1_1.setBounds(80, 130, 128, 29);
		contentPane.add(lblNewLabel_1_1);
		
		ComboBoxMembri = new JComboBox();
		ComboBoxMembri.setMaximumRowCount(10);
		ComboBoxMembri.setBounds(311, 277, 155, 29);
		contentPane.add(ComboBoxMembri);
		
		JComboBox ComboBoxMembri_1 = new JComboBox();
		ComboBoxMembri_1.setModel(new DefaultComboBoxModel(new String[] {"Puntualit\u00E0", "Organizzazione", "Problem Solving", "Empatia"}));
		ComboBoxMembri_1.setMaximumRowCount(10);
		ComboBoxMembri_1.setBounds(312, 130, 155, 29);
		contentPane.add(ComboBoxMembri_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Seleziona membro");
		lblNewLabel_1_1_1.setBounds(173, 277, 128, 29);
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
			            List<Membro> listaMembri = dao.getSviluppatoreBySalarioESkillsEValutazionePS(Integer.parseInt(Salario.getText().toString()),comboBoxValutazione.getSelectedItem().toString(), ComboBoxMembri_1.getSelectedItem().toString(),ComboBoxProgetti.getSelectedItem().toString()); 
			      
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
		btnR.setBounds(371, 238, 119, 28);
		contentPane.add(btnR);
		
		JLabel lblNewLabel_2 = new JLabel("Inserisci la valutazione");
		lblNewLabel_2.setBounds(80, 180, 128, 21);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("aziendale minima richiesta");
		lblNewLabel_3.setBounds(80, 199, 128, 14);
		contentPane.add(lblNewLabel_3);
		
		
	}
}
