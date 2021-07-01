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
import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class CreaMeeting extends JFrame {

	private JPanel contentPane;


	Controller IlControllore; 
	private JTextField textField_1;
	
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
		lblNewLabel_1.setBounds(20, 70, 103, 13);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Fisico", "Telematico"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(196, 68, 136, 17);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Inserisci data e ora");
		lblNewLabel_2.setBounds(20, 100, 103, 13);
		contentPane.add(lblNewLabel_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel(new Date(1616713200000L), new Date(1616713200000L), null, Calendar.HOUR_OF_DAY));
		spinner.setBounds(196, 96, 136, 17);
		contentPane.add(spinner);
		
	
		
		JLabel lblNewLabel_2_1 = new JLabel("Seleziona piattaforma");
		lblNewLabel_2_1.setBounds(20, 130, 103, 13);
		contentPane.add(lblNewLabel_2_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Teams", "Zoom", "GoogleMeet", "Webex", "Nessuna"}));
		comboBox_1.setToolTipText("\n");
		comboBox_1.setBounds(196, 123, 136, 17);
		contentPane.add(comboBox_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Inserisci nome della sala");
		lblNewLabel_2_1_1.setBounds(20, 160, 156, 13);
		contentPane.add(lblNewLabel_2_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(196, 157, 136, 17);
		contentPane.add(textField_1);
		
		
	
		JButton btnNewButton = new JButton("Crea");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IlControllore.CreaMeeting(comboBox.getSelectedItem().toString(), spinner.getValue().toString(),spinner.getValue().toString(), comboBox_1.getSelectedItem().toString(),textField_1.toString());
			}
		});
		btnNewButton.setBounds(298, 238, 85, 21);
		contentPane.add(btnNewButton);
		
		
	}
}
