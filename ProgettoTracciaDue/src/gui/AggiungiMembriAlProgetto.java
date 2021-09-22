package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import dao_impl.ProgettoDAOPostgresImpl;
import dao_impl.ProjectManagerDAOPostgresImpl;
import dao_impl.SkillsDAOPostgresImpl;
import dao_impl.SviluppatoreDAOPostgresImpl;
import daos.ProgettoDAO;
import daos.ProjectManagerDAO;
import daos.SkillsDAO;
import daos.SviluppatoreDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Membro;
import entity.Progetto;
import entity.ProjectManager;
import entity.Skills;
import entity.Sviluppatore;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AggiungiMembriAlProgetto extends JFrame {

	private JPanel contentPane;
	Controller IlControllore;
	private JComboBox ComboBoxMembri;
	private JTextField Salario;
	private JTextField textField;
	private String codiceProgetto;
	
	public AggiungiMembriAlProgetto(Controller c) {
		IlControllore = c;
		setTitle("Azienda - Aggiungi membri al progetto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 520);

		setMinimumSize(new Dimension(556, 420));
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inserisci valore del \r\n");
		lblNewLabel.setBounds(36, 113, 190, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona progetto");
		lblNewLabel_1.setBounds(36, 77, 172, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Seleziona tipo di progetto");
		lblNewLabel_2.setBounds(36, 247, 163, 19);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(" a cui deve avere partecipato");
		lblNewLabel_3.setBounds(36, 257, 211, 29);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBoxTipologia = new JComboBox();
		comboBoxTipologia .setModel(new DefaultComboBoxModel(new String[] {"Ricerca di base","Ricerca industriale","Ricerca sperimentale", "Sviluppo sperimentale"}));
		comboBoxTipologia .setBounds(312, 242, 155, 35);
		contentPane.add(comboBoxTipologia );
		
		JButton btnNewButton_1 = new JButton("Torna indietro");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caso=6;
				IlControllore.RitornaBenvenutoProjectManager(caso);
			}
		});
		btnNewButton_1.setBounds(36, 397, 125, 46);
		contentPane.add(btnNewButton_1);
		
		JComboBox comboBoxValutazione = new JComboBox();
		comboBoxValutazione.setBounds(312, 193, 155, 35);
		comboBoxValutazione.setModel(new DefaultComboBoxModel(new String[] {"Buona", "Mediocre", "Male", "NULL"}));
		comboBoxValutazione.setMaximumRowCount(10);
		contentPane.add(comboBoxValutazione);
		
		JComboBox ComboBoxProgetti = new JComboBox();
		ComboBoxProgetti.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseReleased(MouseEvent e) {

				DBConnection dbconn = null;
				Connection connection = null;
				DBBuilder builder = null;
				try
				{
					ComboBoxProgetti.removeAllItems();
					dbconn = DBConnection.getInstance();
					connection = dbconn.getConnection();
					builder = new DBBuilder(connection);
					ProgettoDAO dao = null;
											            
					dao = new ProgettoDAOPostgresImpl(connection);
											            
					List<Progetto> lista = dao.getProgettoProjectManager(textField.getText().toString());
					
					if(lista.isEmpty()) {
						ComboBoxProgetti.addItem("Non esistono progetti per il codicefiscale inserito");
						
					}else {
						for(Progetto m : lista)
						{		
							ComboBoxProgetti.addItem(m.getNomeProgetto());
						}
					}
				}
				catch (SQLException exception)
				{
					System.out.println("Errore SQLException: "+ exception.getMessage());
				}
			}
		});
						       
		ComboBoxProgetti.setMaximumRowCount(10);
		
        ComboBoxProgetti.setBounds(312, 68, 155, 29);
        contentPane.add(ComboBoxProgetti);

        Salario = new JTextField();
        Salario.setBounds(312, 113, 156, 29);
        contentPane.add(Salario);
        Salario.setColumns(10);

		JLabel lblSalarioMedioDel = new JLabel("Salario medio del membro");
		lblSalarioMedioDel.setBounds(36, 127, 190, 21);
		contentPane.add(lblSalarioMedioDel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Inseriscis skill ricercata");
		lblNewLabel_1_1.setBounds(35, 153, 155, 29);
		contentPane.add(lblNewLabel_1_1);
		
		ComboBoxMembri = new JComboBox();
		ComboBoxMembri.setMaximumRowCount(10);
		ComboBoxMembri.setBounds(257, 347, 155, 29);
		contentPane.add(ComboBoxMembri);
		
		JComboBox ComboBoxSkills = new JComboBox();
		ComboBoxSkills.removeAllItems();
		DBConnection dbconn = null;
		Connection connection = null;
		DBBuilder builder = null;
		try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            SkillsDAO dao1 = null;
            
            dao1 = new SkillsDAOPostgresImpl(connection);
            List<Skills> listaSkill = dao1.getAllSkills(); 
      
            for(Skills n : listaSkill)
            {
            	ComboBoxSkills.addItem(n.getNomeSkill());
            }

        }
	catch (SQLException exception)
	{
      System.out.println("Errore SQLException: "+ exception.getMessage());
	}
		ComboBoxSkills.setMaximumRowCount(10);
        ComboBoxSkills.setBounds(312, 153, 155, 29);
		contentPane.add(ComboBoxSkills);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Seleziona membro");
		lblNewLabel_1_1_1.setBounds(111, 347, 136, 29);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnNewButton = new JButton("Aggiungi");
		btnNewButton.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {
				IlControllore.CreaArchivioPartecipanti(ComboBoxMembri.getSelectedItem().toString(), codiceProgetto);			
			}
		});
		btnNewButton.setBounds(449, 397, 119, 46);
		contentPane.add(btnNewButton);
		
		JButton btnR = new JButton("Ricerca membri");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
					codiceProgetto= dao.getProgettoByNome(ComboBoxProgetti.getSelectedItem().toString());
				}
				catch (SQLException exception)
				{
					System.out.println("Errore SQLException: "+ exception.getMessage());
				}
				ComboBoxMembri.removeAllItems();
				
		        try
			    {
			        dbconn = DBConnection.getInstance();
		            connection = dbconn.getConnection();
		            builder = new DBBuilder(connection);
		            SviluppatoreDAO dao = null;
		            dao = new SviluppatoreDAOPostgresImpl(connection);
		            int valore=ComboBoxProgetti.getSelectedIndex();
		            List<Sviluppatore> listaMembri = dao.getSviluppatoreBySalarioESkillsEValutazioneETipologiaPS(Integer.parseInt(Salario.getText().toString()),ComboBoxSkills.getSelectedItem().toString(), comboBoxValutazione.getSelectedItem().toString(), codiceProgetto, comboBoxTipologia.getSelectedItem().toString() ); 
		            for(Sviluppatore n : listaMembri)
		            {
		            	ComboBoxMembri.addItem(n.getCF());
		            }
			    }

				catch (SQLException exception)
		        {
					System.out.println("Errore SQLException: "+ exception.getMessage());
		        }
			}
		});
		btnR.setBounds(422, 300, 146, 32);
		contentPane.add(btnR);
		
		JLabel lblNewLabel_4 = new JLabel("Inserisci la valutazione");
		lblNewLabel_4.setBounds(36, 196, 154, 21);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("aziendale minima richiesta");
		lblNewLabel_5.setBounds(36, 214, 154, 14);
		contentPane.add(lblNewLabel_5);


		JLabel lblNewLabel_6 = new JLabel("Inserisci il tuo codice fiscale");
		lblNewLabel_6.setBounds(36, 28, 190, 29);
		contentPane.add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(312, 28, 156, 29);
		contentPane.add(textField);		
		
	}
}
