package gui;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Controller;

import dao_impl.MeetingDAOPostgresImpl;
import daos.MeetingDAO;
import daos.ProgettoDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Meeting;
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
			
		JLabel lblNewLabel_1 = new JLabel("Seleziona Meeting");
		lblNewLabel_1.setBounds(41, 85, 128, 29);
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
			
		JComboBox ComboBoxMeeting = new JComboBox();
		ComboBoxMeeting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DBConnection dbconn = null;
				Connection connection = null;
				DBBuilder builder = null;
				try
				{
					dbconn = DBConnection.getInstance();
					connection = dbconn.getConnection();
					builder = new DBBuilder(connection);
					MeetingDAO dao = null;
						            
					dao = new MeetingDAOPostgresImpl(connection);
						            
					List<Meeting> lista = dao.getMeetingCodFiscale(CfInserito.getText().toString());
					for(Meeting p : lista)
					{
						ComboBoxMeeting.addItem(p.getTitolo());
					}

				}
				catch (SQLException exception)
				{
					System.out.println("Errore SQLException: "+ exception.getMessage());
				}
			}
		});
		ComboBoxMeeting.setMaximumRowCount(10);
		
		ComboBoxMeeting.setBounds(247, 82, 155, 35);
		contentPane.add(ComboBoxMeeting);	
		
		JButton btnNewButton = new JButton("Aggiungi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(306, 188, 96, 35);
		contentPane.add(btnNewButton);
		
		CfInserito = new JTextField();
		CfInserito.setBounds(247, 34, 155, 29);
		contentPane.add(CfInserito);
		CfInserito.setColumns(10);
		CfInserito.setColumns(10);
	}
}