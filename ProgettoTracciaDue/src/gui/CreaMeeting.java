
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
import dao_impl.ProgettoDAOPostgresImpl;
import dao_impl.ProjectManagerDAOPostgresImpl;
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
import java.util.GregorianCalendar;
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
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

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
		setBounds(100, 100, 569, 539);
		setMinimumSize(new Dimension(600,550));
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona tipologia");
		
		JLabel lblNewLabel_2 = new JLabel("Inserisci ora inizio");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=5;
				IlControllore.RitornaBenvenutoProjectManager(caso);
			}
		});
		
		
		JComboBox comboBox_Piattaforma = new JComboBox();
		comboBox_Piattaforma.setModel(new DefaultComboBoxModel(new String[] {"Teams", "Zoom", "GoogleMeet", "Webex"}));
		comboBox_Piattaforma.setToolTipText("\n");
			
		JLabel lblNewLabel_2_1 = new JLabel("Seleziona piattaforma");
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Inserisci nome della sala");
		
		txtNull_NomeSala = new JTextField();
		txtNull_NomeSala.setText("Null");
		txtNull_NomeSala.setColumns(10);
		
		JComboBox comboBox_Tipologia = new JComboBox();
		comboBox_Tipologia.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(comboBox_Tipologia.getSelectedItem()=="Fisico") {
					
					comboBox_Piattaforma.removeAllItems();
					comboBox_Piattaforma.addItem("null");
					textField_Luogo.setText("");
					txtNull_NomeSala.setText("");
					}else {
					comboBox_Piattaforma.removeAllItems();
					comboBox_Piattaforma.addItem("Teams");
					comboBox_Piattaforma.addItem("Zoom");
					txtNull_NomeSala.setText("NULL");
					textField_Luogo.setText("NULL");
					}
			}
		});
					
		comboBox_Tipologia.setModel(new DefaultComboBoxModel(new String[] { "Telematico","Fisico"}));
		comboBox_Tipologia.setToolTipText("");
		
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

		JLabel lblNewLabel_2_2 = new JLabel("Inserisci ora fine");

		JLabel lblNewLabel_1_1 = new JLabel("Inserisci CF dell'organizzatore");

		textField_CF = new JTextField();
		textField_CF.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Seleziona il Progetto");
		
		Date date = new Date();
		
		JSpinner spinner2_OraFine = new JSpinner();
		spinner2_OraFine.setModel(new SpinnerDateModel(date, null, null, Calendar.MINUTE));
		
		JSpinner spinner_OraInizio = new JSpinner();
		spinner_OraInizio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
		        Date mod = (Date)spinner_OraInizio.getValue();
		        mod.setHours(23);
            	mod.setMinutes(59);
            	mod.setSeconds(0);
            	
				spinner2_OraFine.setModel(new SpinnerDateModel((Date)spinner_OraInizio.getValue(), (Date)spinner_OraInizio.getValue(), mod, Calendar.MINUTE));
				JSpinner.DateEditor dateEditor2 = new JSpinner.DateEditor(spinner2_OraFine, "HH:mm");
				spinner2_OraFine.setEditor(dateEditor2);
			}
		});
		SpinnerDateModel mdl = new SpinnerDateModel(date, null, null, Calendar.MINUTE);
		spinner_OraInizio.setModel(mdl);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getDateEditor().addPropertyChangeListener(
			    new PropertyChangeListener() {
			        @Override
			        public void propertyChange(PropertyChangeEvent evt) {
			            if ("date".equals(evt.getPropertyName())) {
			            	spinner_OraInizio.setModel(new SpinnerDateModel((Date) evt.getNewValue(), null , null, Calendar.MINUTE));
			            	JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner_OraInizio, "HH:mm");
			        	    spinner_OraInizio.setEditor(dateEditor);
			            	System.out.println(evt.getPropertyName()
			                    + ": " + (Date) evt.getNewValue());
			            }
			        }
			    });
		dateChooser.setMinSelectableDate(new Date());
		dateChooser.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		dateChooser.setDateFormatString("yyyy-MM-dd");

		JLabel lblNewLabel = new JLabel("Seleziona una data");
		
		JLabel lblNewLabel_3 = new JLabel("Inserisci titolo");
		
		textField_Titolo = new JTextField();
		textField_Titolo.setColumns(10);
		
		System.out.println(spinner_OraInizio.getValue().toString());
		JButton btnNewButton = new JButton("Crea");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oraInizioS = new SimpleDateFormat("HH:mm").format(spinner_OraInizio.getValue());
				String oraFineS = new SimpleDateFormat("HH:mm").format(spinner2_OraFine.getValue());
				IlControllore.CreaMeeting(textField_CF.getText().toString(), comboBox_Tipologia.getSelectedItem().toString(), textField_Titolo.getText(), dateChooser.getDate(), oraInizioS, oraFineS, textField_Luogo.getText(), txtNull_NomeSala.getText().toString(), comboBox_Piattaforma.getSelectedItem().toString(),  comboBox_Progetto.getSelectedItem().toString());
			}
		});
		
		textField_Luogo = new JTextField();
		textField_Luogo.setColumns(10);
		textField_Luogo.setText("NULL");
		
		JLabel lblNewLabel_4 = new JLabel("Inserisci Luogo");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(33)
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
							.addGap(84)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
							.addGap(35))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(15)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2_2, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(40)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox_Piattaforma, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtNull_NomeSala, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_Luogo, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinner2_OraFine, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinner_OraInizio, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_Titolo, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_Tipologia, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_Progetto, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_CF, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))))
					.addGap(122))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(textField_CF, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_Progetto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_Tipologia, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Titolo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addGap(16))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(spinner_OraInizio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(spinner2_OraFine, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_Luogo, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNull_NomeSala, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2_1)
						.addComponent(comboBox_Piattaforma, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		
	}
}
