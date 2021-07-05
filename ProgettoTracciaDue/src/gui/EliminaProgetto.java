package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.Controller;
import dao_impl.ProgettoDAOPostgresImpl;
import daos.ProgettoDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Progetto;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminaProgetto extends JFrame {

	private JPanel contentPane;
	Controller IlControllore;
	
	public EliminaProgetto(Controller c) {
		IlControllore = c;

		setTitle("Azienda - Elimina progetto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleziona nome progetto:");
		lblNewLabel.setBounds(38, 45, 152, 29);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
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
	            	comboBox.addItem(m.getNomeProgetto());
	            }

	        }
    		catch (SQLException exception)
        	{
                System.out.println("Errore SQLException: "+ exception.getMessage());
        	}
        comboBox.setBounds(213, 38, 150, 43);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Elimina Progetto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
				IlControllore.EliminaProgetto(comboBox.getSelectedItem().toString());
	            

		}
		});
		btnNewButton.setBounds(246, 187, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=2;
				IlControllore.RitornaBenvenutoProjectManager(caso);
			}
		});
		btnNewButton_1.setBounds(50, 187, 117, 29);
		contentPane.add(btnNewButton_1);
	}

}
