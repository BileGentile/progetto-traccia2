package gui;

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
import javax.swing.border.EmptyBorder;

import app.Controller;

import dao_impl.MeetingDAOPostgresImpl;
import dao_impl.MeetingFisicoDAOPostgresImpl;
import dao_impl.MeetingTelematicoDAOPostgresImpl;
import daos.MeetingDAO;
import daos.MeetingFisicoDAO;
import daos.MeetingTelematicoDAO;
import daos.ProgettoDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Meeting;
import entity.MeetingFisico;
import entity.MeetingTelematico;
import entity.Progetto;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AggiungiPresenza extends JFrame {

	private JPanel contentPane;
	Controller IlControllore;
	private JTextField CfInserito;
		
	public AggiungiPresenza(Controller c) {
		IlControllore = c;
		setTitle("Azienda - Aggiungi presenza al meeting");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		JLabel lblNewLabel = new JLabel("Inserisci il tuo codice fiscale");
		lblNewLabel.setBounds(41, 34, 160, 21);
		contentPane.add(lblNewLabel);
			
		JLabel lblNewLabel_1 = new JLabel("Seleziona Tipologia Meeting");
		lblNewLabel_1.setBounds(41, 85, 160, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=1;
				IlControllore.RitornaBenvenutoSviluppatore(caso);
			}
		});
		btnNewButton_1.setBounds(41, 188,129, 35);
		contentPane.add(btnNewButton_1);
		
		JComboBox ComboBoxTipologia = new JComboBox();
		ComboBoxTipologia.setModel(new DefaultComboBoxModel(new String[] { "Telematico","Fisico"}));
		ComboBoxTipologia.setToolTipText("");
		ComboBoxTipologia.setBounds(247, 74, 155, 35);
		contentPane.add(ComboBoxTipologia);
		
		JComboBox ComboBoxMeeting = new JComboBox();
		ComboBoxMeeting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DBConnection dbconn = null;
				Connection connection = null;
				DBBuilder builder = null;
				try
				{
					ComboBoxMeeting.removeAllItems();
					dbconn = DBConnection.getInstance();
					connection = dbconn.getConnection();
					builder = new DBBuilder(connection);
					if(ComboBoxTipologia.getSelectedItem().toString().equals("Telematico")) {
						MeetingTelematicoDAO dao = null;
			            
						dao = new MeetingTelematicoDAOPostgresImpl(connection);           
						List<MeetingTelematico> lista = dao.getMeetingTelematicoCodFiscale(CfInserito.getText().toString());
						for(MeetingTelematico p : lista)
						{
							ComboBoxMeeting.addItem (p.getTitolo());
						}
					}else {
						MeetingFisicoDAO dao = null;
			            
						dao = new MeetingFisicoDAOPostgresImpl(connection);           
						List<MeetingFisico> lista = dao.getMeetingFisicoCodFiscale(CfInserito.getText().toString());
						for(MeetingFisico p : lista)
						{
							ComboBoxMeeting.addItem (p.getTitolo());
						}
					}
				}
				catch (SQLException exception)
				{
					System.out.println("Errore SQLException: "+ exception.getMessage());
				}
			}
		});
		ComboBoxMeeting.setMaximumRowCount(10);
		
		ComboBoxMeeting.setBounds(247, 122, 155, 35);
		contentPane.add(ComboBoxMeeting);	
		
		JButton btnNewButton = new JButton("Aggiungi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				IlControllore.AggiungiArchivioPartecipantiMeeting(CfInserito.getText().toString(),ComboBoxTipologia.getSelectedItem().toString(),ComboBoxMeeting.getSelectedItem().toString());;
			}
		});
		btnNewButton.setBounds(306, 188, 96, 35);
		contentPane.add(btnNewButton);
		
		CfInserito = new JTextField();
		CfInserito.setBounds(247, 28, 155, 35);
		contentPane.add(CfInserito);
		CfInserito.setColumns(10);
		CfInserito.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Seleziona Meeting");
		lblNewLabel_1_1.setBounds(41, 125, 128, 29);
		contentPane.add(lblNewLabel_1_1);
	}
}