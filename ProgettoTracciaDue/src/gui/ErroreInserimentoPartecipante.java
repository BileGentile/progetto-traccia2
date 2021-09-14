package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.Controller;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class ErroreInserimentoPartecipante extends JDialog {

	private final JPanel contentPanel = new JPanel();

	Controller IlControllore;
	
	public ErroreInserimentoPartecipante(Controller c) {
		
		IlControllore = c;
		
		setTitle("Azienda - Errore Inserimento Partecipante");

		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setBounds(80, 80, 350, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Errore! Il codice fiscale scelto");
			contentPanel.add(lblNewLabel);
			JLabel lblNewLabe2 = new JLabel("non può essere inserito perchè");
			contentPanel.add(lblNewLabe2);
			JLabel lblNewLabe3 = new JLabel("è lo stesso del Project manager ");
			contentPanel.add(lblNewLabe3);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.activeCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int caso=10;
						IlControllore.RitornaBenvenutoProjectManager(caso);
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("riprova");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int caso=1;
						IlControllore.AvviaInserimentoMembri(caso);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
