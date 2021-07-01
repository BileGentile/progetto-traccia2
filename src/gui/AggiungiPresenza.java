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

	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;

	import app.Controller;
	import dao_impl.MeetingTelematicoDAOPostgresImpl;
	import dao_impl.ProgettoDAOPostgresImpl;
	import daos.MeetingTelematicoDAO;
	import daos.ProgettoDAO;
	import dbConfig.DBBuilder;
	import dbConfig.DBConnection;
	import entity.MeetingTelematico;
	import entity.Progetto;
	import javax.swing.JTextField;

public class AggiungiPresenza extends JFrame {

	private JPanel contentPane;
	Controller IlControllore;
	private JTextField MatricolaInserita;
		
	public AggiungiPresenza(Controller c) {
		IlControllore = c;
		setTitle("Azienda - Aggiungi membri al progetto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		JLabel lblNewLabel = new JLabel("Inserisci la tua matricola");
		lblNewLabel.setBounds(38, 106, 146, 21);
		contentPane.add(lblNewLabel);
			
		JLabel lblNewLabel_1 = new JLabel("Seleziona Meeting");
		lblNewLabel_1.setBounds(38, 45, 128, 29);
		contentPane.add(lblNewLabel_1);
			
			
		JComboBox ComboBoxProgetti = new JComboBox();
		ComboBoxProgetti.setMaximumRowCount(10);
		DBConnection dbconn = null;
		Connection connection = null;
		DBBuilder builder = null;
		try{
			dbconn = DBConnection.getInstance();
			connection = dbconn.getConnection();
			builder = new DBBuilder(connection);
			MeetingTelematicoDAO dao = null;
				            
			dao = new MeetingTelematicoDAOPostgresImpl(connection);
				            
			List<MeetingTelematico> lista = dao.getAllMeeting();
			for(MeetingTelematico mt : lista)
				{
				ComboBoxProgetti.addItem(mt.getNome());
				}

			}
			catch (SQLException exception)
			{
			System.out.println("Errore SQLException: "+ exception.getMessage());
			}
			ComboBoxProgetti.setBounds(211, 38, 155, 43);
			contentPane.add(ComboBoxProgetti);
			
			
			
			JButton btnNewButton = new JButton("Crea");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			btnNewButton.setBounds(270, 207, 96, 35);
			contentPane.add(btnNewButton);
			
			
			
			MatricolaInserita = new JTextField();
			MatricolaInserita.setBounds(211, 107, 155, 20);
			contentPane.add(MatricolaInserita);
			MatricolaInserita.setColumns(10);
			MatricolaInserita.setColumns(10);
		}
		}
