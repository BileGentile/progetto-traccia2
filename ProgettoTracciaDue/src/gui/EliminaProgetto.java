package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class EliminaProgetto extends JFrame {

	private JPanel contentPane;
	Controller IlControllore;
	
	public EliminaProgetto (Controller c) {
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
		
		JLabel lblNewLabel_1_2 = new JLabel("Inserisci il tuo codice fiscale");
		lblNewLabel_1_2.setBounds(38, 50, 146, 29);
		contentPane.add(lblNewLabel_1_2);
		
		JTextField textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(236, 50, 156, 29);
		setMinimumSize(new Dimension(600,550));
		contentPane.add(textField);
		
		JLabel lblNewLabel = new JLabel("Seleziona nome progetto:");
		lblNewLabel.setBounds(36, 116, 152, 29);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				DBConnection dbconn = null;
				Connection connection = null;
				DBBuilder builder = null;
				try
				{
					comboBox.removeAllItems();
					dbconn = DBConnection.getInstance();
					connection = dbconn.getConnection();
					builder = new DBBuilder(connection);
					ProgettoDAO dao = null;
		            
					dao = new ProgettoDAOPostgresImpl(connection);
											            
					List<Progetto> lista = dao.getProgettoProjectManager(textField.getText().toString());
					
					if(lista.isEmpty()) {
						comboBox.addItem("Non esistono progetti per il codicefiscale inserito");
						
					}else {
						for(Progetto m : lista)
						{
							
							comboBox.addItem(m.getNomeProgetto());
						}
					}
				}
				catch (SQLException exception)
				{
					System.out.println("Errore SQLException: "+ exception.getMessage());
				}
			}
		});
        comboBox.setBounds(236, 109, 156, 43);
		
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Elimina Progetto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
				IlControllore.EliminaProgetto(textField.getText().toString(),comboBox.getSelectedItem().toString());
		}
		});
		btnNewButton.setBounds(275, 199, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=2;
				IlControllore.RitornaBenvenutoProjectManager(caso);
			}
		});
		btnNewButton_1.setBounds(38, 199, 117, 29);
		contentPane.add(btnNewButton_1);
	}

}
