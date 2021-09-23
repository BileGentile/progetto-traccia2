package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
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

public class ErroreCodFiscaleNonRegistrabile extends JDialog {

	private final JPanel contentPanel = new JPanel();

	Controller IlControllore;
	
	public ErroreCodFiscaleNonRegistrabile(Controller c) {
		
		IlControllore = c;
		
		setTitle("Azienda - Errore Login");

		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		setBounds(80, 80, 350, 200);
		setMinimumSize(new Dimension(100,150));
		getContentPane().setLayout(new BorderLayout());
		
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Errore! Il codice fiscale inserito non \u00E8 corretto");
			lblNewLabel.setBounds(16, 31, 310, 13);
			contentPanel.add(lblNewLabel);

		}
		{
			JLabel lblOSeiGi = new JLabel("o sei gi\u00E0 registrato.");
			lblOSeiGi.setBounds(16, 47, 310, 13);
			contentPanel.add(lblOSeiGi);
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
						IlControllore.TornaPresentazione3();
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
					
						IlControllore.TornaRegistrazione();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
