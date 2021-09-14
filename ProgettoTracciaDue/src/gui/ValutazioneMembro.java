package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import dao_impl.SviluppatoreDAOPostgresImpl;
import daos.MembroDAO;
import daos.ProgettoDAO;
import daos.SviluppatoreDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Membro;
import entity.Progetto;
import entity.Sviluppatore;
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
		lblNewLabel.setBounds(38, 100, 158, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona valutazione");
		lblNewLabel_1.setBounds(38, 150, 127, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblBenvenutoNellaValutazione = new JLabel("Benvenuto nella valutazione dei membri ");
		lblBenvenutoNellaValutazione.setBounds(38, 20, 339, 29);
		contentPane.add(lblBenvenutoNellaValutazione);

		JLabel lblNewLabel_1_2 = new JLabel("Inserisci il tuo codice fiscale");
		lblNewLabel_1_2.setBounds(38, 50, 146, 29);
		contentPane.add(lblNewLabel_1_2);
		
		JTextField textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(200, 50, 156, 29);
		contentPane.add(textField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Buona", "Mediocre", "Male"}));
		comboBox.setBounds(200, 150, 140, 29);
		contentPane.add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
		
				DBConnection dbconn = null;
				Connection connection = null;
				DBBuilder builder = null;
	        
				try
				{
					comboBox_1.removeAllItems();
					dbconn = DBConnection.getInstance();     
					connection = dbconn.getConnection();	           
					builder = new DBBuilder(connection);
					SviluppatoreDAO dao = null;
	            
					dao = new SviluppatoreDAOPostgresImpl(connection);
	            
					List<Sviluppatore> lista = dao.getAllSviluppatoriProgetto(textField.getText().toString());
					for(Sviluppatore mm : lista)
					{
						comboBox_1.addItem(mm.getCF());
					}

				}
				catch (SQLException exception)
				{
					System.out.println("Errore SQLException: "+ exception.getMessage());
				}
		
			}
		
		});

		comboBox_1.setBounds(200, 100, 140, 29);
		contentPane.add(comboBox_1);
	
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

