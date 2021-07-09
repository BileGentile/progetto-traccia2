package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class CreaMeeting extends JFrame {

	private JPanel contentPane;


	Controller IlControllore; 
	private JTextField txtNull;
	private JTextField textField;
	private JTextField textField_CF;
	
	public CreaMeeting(Controller c) {
		IlControllore = c;
		
		setTitle("Azienda - Creazione Meeting");

		setIconImage(Toolkit.getDefaultToolkit().getImage(ValutazioneMembro.class.getResource("/image/ingranaggio blu.png"))); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 347);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona tipologia");
		lblNewLabel_1.setBounds(20, 84, 140, 21);
		contentPane.add(lblNewLabel_1);
		
		
		JLabel lblNewLabel_2 = new JLabel("Inserisci data e ora inizio");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblNewLabel_2.setBounds(20, 116, 140, 13);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=5;
				IlControllore.RitornaBenvenutoProjectManager(caso);
			}
		});
		btnNewButton_1.setBounds(20, 256, 107, 41);
		contentPane.add(btnNewButton_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1616713200000L), new Date(1616713200000L), null, Calendar.HOUR_OF_DAY));
		spinner.setBounds(232, 109, 140, 26);
		contentPane.add(spinner);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Teams", "Zoom", "GoogleMeet", "Webex"}));
		comboBox_1.setToolTipText("\n");
		comboBox_1.setBounds(232, 178, 140, 26);
		contentPane.add(comboBox_1);
		
		
		JLabel lblNewLabel_2_1 = new JLabel("Seleziona piattaforma");
		lblNewLabel_2_1.setBounds(17, 185, 143, 13);
		contentPane.add(lblNewLabel_2_1);
		
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Inserisci nome della sala");
		lblNewLabel_2_1_1.setBounds(20, 215, 172, 19);
		contentPane.add(lblNewLabel_2_1_1);
		
		txtNull = new JTextField();
		txtNull.setText("Null");
		txtNull.setColumns(10);
		txtNull.setBounds(232, 210, 140, 24);
		contentPane.add(txtNull);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(comboBox.getSelectedItem()=="Fisico") {
					
					comboBox_1.removeAllItems();
					comboBox_1.addItem("null");
					txtNull.setText("");
					}else {
					comboBox_1.removeAllItems();
					comboBox_1.addItem("Teams");
					comboBox_1.addItem("Zoom");
					txtNull.setText("NULL");
				
		
					}
			}
		});
					
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Telematico","Fisico"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(232, 77, 140, 21);
		contentPane.add(comboBox);
		
		JComboBox comboBox_Progetto = new JComboBox();
		comboBox_Progetto.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseReleased(MouseEvent e) {

								   		DBConnection dbconn = null;
								        Connection connection = null;
								        DBBuilder builder = null;
												   try
											        {
													   comboBox_Progetto.removeAllItems();
											            dbconn = DBConnection.getInstance();
											            connection = dbconn.getConnection();
											            builder = new DBBuilder(connection);
											            MembroDAO dao = null;
											            
											            dao = new MembroDAOPostgresImpl(connection);
											            
											            List<Membro> lista = dao.getProjectManagerByCodFiscale(textField_CF.getText().toString());
											            
											            if(lista.isEmpty()) {
											            	comboBox_Progetto.addItem("Non esistono progetti per il codicefiscale inserito");

											            }else {
											            	 try
														        {
														            dbconn = DBConnection.getInstance();
														            connection = dbconn.getConnection();
														            builder = new DBBuilder(connection);
														            ProgettoDAO dao1 = null;
														            
														            dao1 = new ProgettoDAOPostgresImpl(connection);
														            
														            List<Progetto> listaProgetti = dao1.getProgettoProjectManager(textField_CF.getText().toString());
														            for(Progetto m : listaProgetti)
														            {
														            
														            	comboBox_Progetto.addItem(m.getNomeProgetto());
														            }

														        }
											            	 	catch (SQLException exception)
													        	{
													                System.out.println("Errore SQLException: "+ exception.getMessage());
													        	}
											            	}
											        }catch (SQLException exception)
												   	{
											        	System.out.println("Errore SQLException: "+ exception.getMessage());
												   	}
					}

			
		});
		
					       
		comboBox_Progetto.setMaximumRowCount(10);
		comboBox_Progetto.setToolTipText("");
		comboBox_Progetto.setBounds(232, 43, 140, 21);
		contentPane.add(comboBox_Progetto);
		
		
		System.out.println(spinner.getValue().toString());
		JButton btnNewButton = new JButton("Crea");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.CreaMeeting(comboBox.getSelectedItem().toString(), spinner.getValue().toString(),spinner.getValue().toString(), comboBox_1.getSelectedItem().toString(), txtNull.getText().toString(),textField_CF.getText(),comboBox_Progetto.getSelectedItem().toString() , Integer.parseInt(textField.getText().toString()));
				
			}
		});
		btnNewButton.setBounds(304, 256, 107, 41);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2_2 = new JLabel("Inserisci durata in ore");
		lblNewLabel_2_2.setBounds(20, 145, 159, 22);
		contentPane.add(lblNewLabel_2_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(232, 146, 140, 21);
		contentPane.add(textField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Inserisci CF dell'organizzatore");
		lblNewLabel_1_1.setBounds(20, 23, 172, 21);
		contentPane.add(lblNewLabel_1_1);
		
		textField_CF = new JTextField();
		textField_CF.setColumns(10);
		textField_CF.setBounds(232, 11, 140, 21);
		contentPane.add(textField_CF);
		
		JLabel lblNewLabel_1_2 = new JLabel("Seleziona il Progetto");
		lblNewLabel_1_2.setBounds(20, 55, 140, 21);
		contentPane.add(lblNewLabel_1_2);
		
		
	}
}
