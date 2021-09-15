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
import javax.swing.text.DateFormatter;

import app.Controller;
import dao_impl.MembroDAOPostgresImpl;
import dao_impl.ProgettoDAOPostgresImpl;
import dao_impl.ProjectManagerDAOPostgresImpl;
import daos.MembroDAO;
import daos.ProgettoDAO;
import daos.ProjectManagerDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Membro;
import entity.Progetto;
import entity.ProjectManager;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Calendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JCalendar;
import javax.swing.JFormattedTextField;
import com.toedter.calendar.JDateChooser;
import java.awt.ComponentOrientation;

public class CreaMeeting extends JFrame {

	private JPanel contentPane;


	Controller IlControllore; 
	private JTextField txtNull_NomeSala;
	private JSpinner textField;
	private JTextField textField_CF;
	private JTextField textField_Titolo;
	private JTextField textField_Luogo;
	
	public CreaMeeting(Controller c){
		IlControllore = c;
		
		setTitle("Azienda - Creazione Meeting");

		setIconImage(Toolkit.getDefaultToolkit().getImage(ValutazioneMembro.class.getResource("/image/ingranaggio blu.png"))); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 539);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona tipologia");
		lblNewLabel_1.setBounds(20, 77, 140, 21);
		contentPane.add(lblNewLabel_1);
		
		
		JLabel lblNewLabel_2 = new JLabel("Inserisci ora inizio");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblNewLabel_2.setBounds(20, 182, 140, 13);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=5;
				IlControllore.RitornaBenvenutoProjectManager(caso);
			}
		});
		btnNewButton_1.setBounds(38, 426, 107, 41);
		contentPane.add(btnNewButton_1);
		
		
		JComboBox comboBox_Piattaforma = new JComboBox();
		comboBox_Piattaforma.setModel(new DefaultComboBoxModel(new String[] {"Teams", "Zoom", "GoogleMeet", "Webex"}));
		comboBox_Piattaforma.setToolTipText("\n");
		comboBox_Piattaforma.setBounds(225, 337, 140, 26);
		contentPane.add(comboBox_Piattaforma);
			
		JLabel lblNewLabel_2_1 = new JLabel("Seleziona piattaforma");
		lblNewLabel_2_1.setBounds(20, 344, 143, 13);
		contentPane.add(lblNewLabel_2_1);		
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Inserisci nome della sala");
		lblNewLabel_2_1_1.setBounds(20, 284, 172, 19);
		contentPane.add(lblNewLabel_2_1_1);
		
		txtNull_NomeSala = new JTextField();
		txtNull_NomeSala.setText("Null");
		txtNull_NomeSala.setColumns(10);
		txtNull_NomeSala.setBounds(225, 281, 140, 24);
		contentPane.add(txtNull_NomeSala);
		
		JComboBox comboBox_Tipologia = new JComboBox();
		comboBox_Tipologia.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(comboBox_Tipologia.getSelectedItem()=="Fisico") {
					
					comboBox_Piattaforma.removeAllItems();
					comboBox_Piattaforma.addItem("null");
					txtNull_NomeSala.setText("");
					}else {
					comboBox_Piattaforma.removeAllItems();
					comboBox_Piattaforma.addItem("Teams");
					comboBox_Piattaforma.addItem("Zoom");
					txtNull_NomeSala.setText("NULL");
		
					}
			}
		});
					
		comboBox_Tipologia.setModel(new DefaultComboBoxModel(new String[] { "Telematico","Fisico"}));
		comboBox_Tipologia.setToolTipText("");
		comboBox_Tipologia.setBounds(225, 77, 140, 21);
		contentPane.add(comboBox_Tipologia);
		
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
					            ProjectManagerDAO dao = null;
					            
					            dao = new ProjectManagerDAOPostgresImpl(connection);
					            
					            List<ProjectManager> lista = dao.getProjectManagerByCodFiscale(textField_CF.getText().toString());
					            
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
		comboBox_Progetto.setBounds(225, 43, 140, 21);
		contentPane.add(comboBox_Progetto);



		JLabel lblNewLabel_2_2 = new JLabel("Inserisci ora fine");
		lblNewLabel_2_2.setBounds(20, 211, 159, 22);
		contentPane.add(lblNewLabel_2_2);

		
		JLabel lblNewLabel_1_1 = new JLabel("Inserisci CF dell'organizzatore");
		lblNewLabel_1_1.setBounds(20, 11, 172, 21);
		contentPane.add(lblNewLabel_1_1);

		textField_CF = new JTextField();
		textField_CF.setColumns(10);
		textField_CF.setBounds(225, 11, 140, 21);
		contentPane.add(textField_CF);

		JLabel lblNewLabel_1_2 = new JLabel("Seleziona il Progetto");
		lblNewLabel_1_2.setBounds(20, 43, 140, 21);
		contentPane.add(lblNewLabel_1_2);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(225, 144, 139, 20);
		contentPane.add(dateChooser);
		
		Date date = new Date();
		
		JSpinner spinner_OraInizio = new JSpinner();
		spinner_OraInizio.setModel(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner_OraInizio, "HH:mm");
		spinner_OraInizio.setEditor(dateEditor);
		spinner_OraInizio.setBounds(225, 178, 140, 21);
		contentPane.add(spinner_OraInizio);
		
		JSpinner spinner2_OraFine = new JSpinner();
		spinner2_OraFine.setModel(new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor dateEditor2 = new JSpinner.DateEditor(spinner2_OraFine, "HH:mm");
		spinner2_OraFine.setEditor(dateEditor2);
		spinner2_OraFine.setBounds(225, 212, 140, 21);
		contentPane.add(spinner2_OraFine);
		
		
		JLabel lblNewLabel = new JLabel("Seleziona una data");
		lblNewLabel.setBounds(20, 150, 125, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Inserisci titolo");
		lblNewLabel_3.setBounds(20, 112, 80, 14);
		contentPane.add(lblNewLabel_3);
		
		textField_Titolo = new JTextField();
		textField_Titolo.setBounds(225, 109, 140, 20);
		contentPane.add(textField_Titolo);
		textField_Titolo.setColumns(10);
		
		System.out.println(spinner_OraInizio.getValue().toString());
		JButton btnNewButton = new JButton("Crea");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oraInizioS = new SimpleDateFormat("HH:mm").format(spinner_OraInizio.getValue());
				String oraFineS = new SimpleDateFormat("HH:mm").format(spinner2_OraFine.getValue());
				IlControllore.CreaMeeting(comboBox_Tipologia.getSelectedItem().toString(), textField_Titolo.getText(), dateChooser.getDate(), oraInizioS, oraFineS, textField_Luogo.getText(), txtNull_NomeSala.getText().toString(), comboBox_Piattaforma.getSelectedItem().toString(), textField_CF.getText(), comboBox_Progetto.getSelectedItem().toString());
			}
		});
		btnNewButton.setBounds(255, 426, 107, 41);
		contentPane.add(btnNewButton);
		
		
		textField_Luogo = new JTextField();
		textField_Luogo.setBounds(225, 244, 140, 20);
		contentPane.add(textField_Luogo);
		textField_Luogo.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Inserisci Luogo");
		lblNewLabel_4.setBounds(20, 247, 91, 14);
		contentPane.add(lblNewLabel_4);
		
	}
}
