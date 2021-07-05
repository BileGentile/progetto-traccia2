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
import exceptions.ConnectionException;

public class ValutazioneMembro extends JFrame {

	private JPanel contentPane;
	Controller IlControllore;
	
	
	public ValutazioneMembro(Controller c) {
		IlControllore =c;
		setTitle("Azienda - Valutazione Membro");

		setIconImage(Toolkit.getDefaultToolkit().getImage(ValutazioneMembro.class.getResource("/image/ingranaggio blu.png"))); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleziona Membro ");
		lblNewLabel.setBounds(38, 68, 158, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona valutazione");
		lblNewLabel_1.setBounds(38, 107, 127, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblBenvenutoNellaValutazione = new JLabel("Benvenuto nella valutazione dei membri ");
		lblBenvenutoNellaValutazione.setBounds(38, 29, 339, 29);
		contentPane.add(lblBenvenutoNellaValutazione);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Buona", "Mediocre", "Male"}));
		comboBox.setBounds(200, 114, 140, 29);
		contentPane.add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(200, 68, 140, 29);
		contentPane.add(comboBox_1);
		try
		{
			DBConnection dbconn = null;
	        Connection connection = null;
	        DBBuilder builder = null;
	        
	        dbconn = DBConnection.getInstance();     
	        connection = dbconn.getConnection();	           
	        builder = new DBBuilder(connection);
	        MembroDAO dao = null;
	            
	            dao = new MembroDAOPostgresImpl(connection);
			    List<Membro> lista = dao.getAllSviluppatori();
			    for(Membro mm : lista)
			    {
			    	 comboBox_1.addItem(mm.getCF());
			    }

		}
	    catch (SQLException exception)
		{
	    	System.out.println("Errore SQLException: "+ exception.getMessage());
		}
		

	
	JButton btnNewButton = new JButton("Valuta ");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			IlControllore.ValutazioneMembro(comboBox.getSelectedItem().toString(), comboBox_1.getSelectedItem().toString());

		}		
		});

	btnNewButton.setBounds(288, 197, 91, 29);
	contentPane.add(btnNewButton);
	
	JButton btnNewButton_1 = new JButton("Torna indietro");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int caso=1;
			IlControllore.RitornaBenvenutoProjectManager(caso);
		}
	});
	btnNewButton_1.setBounds(50, 197, 129, 34);
	contentPane.add(btnNewButton_1);

	}
}
	
