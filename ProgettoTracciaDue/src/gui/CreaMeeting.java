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
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class CreaMeeting extends JFrame {

	private JPanel contentPane;


	Controller IlControllore; 
	private JTextField txtNull;
	private JTextField textField;
	
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
		lblNewLabel_1.setBounds(20, 56, 140, 21);
		contentPane.add(lblNewLabel_1);
		
	
		
		JLabel lblNewLabel_2 = new JLabel("Inserisci data e ora inizio");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblNewLabel_2.setBounds(20, 93, 140, 13);
		contentPane.add(lblNewLabel_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1616713200000L), new Date(1616713200000L), null, Calendar.HOUR_OF_DAY));
		spinner.setBounds(232, 87, 140, 26);
		contentPane.add(spinner);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Teams", "Zoom", "GoogleMeet", "Webex"}));
		comboBox_1.setToolTipText("\n");
		comboBox_1.setBounds(232, 148, 140, 26);
		contentPane.add(comboBox_1);
		
		
		
		JLabel lblNewLabel_2_1 = new JLabel("Seleziona piattaforma");
		lblNewLabel_2_1.setBounds(20, 155, 143, 13);
		contentPane.add(lblNewLabel_2_1);
		
		
	
		JLabel lblNewLabel_2_1_1 = new JLabel("Inserisci nome della sala");
		lblNewLabel_2_1_1.setBounds(20, 186, 172, 19);
		contentPane.add(lblNewLabel_2_1_1);
		
		txtNull = new JTextField();
		txtNull.setText("Null");
		txtNull.setColumns(10);
		txtNull.setBounds(232, 184, 140, 24);
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
		comboBox.setBounds(232, 56, 140, 21);
		contentPane.add(comboBox);
		
		
		System.out.println(spinner.getValue().toString());
		JButton btnNewButton = new JButton("Crea");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.CreaMeeting(comboBox.getSelectedItem().toString(), spinner.getValue().toString(),spinner.getValue().toString(), comboBox_1.getSelectedItem().toString(),txtNull.getText().toString(), Integer.parseInt(textField.getText().toString()));
			}
		});
		btnNewButton.setBounds(273, 231, 107, 41);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2_2 = new JLabel("Inserisci durata in ore");
		lblNewLabel_2_2.setBounds(20, 118, 159, 22);
		contentPane.add(lblNewLabel_2_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(232, 119, 140, 21);
		contentPane.add(textField);
		
		
	}
}
