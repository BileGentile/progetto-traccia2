package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.SystemColor;
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
import javax.swing.SwingConstants;

public class ValutazioneAvvenutaConSuccesso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Controller IlControllore;
	
	public  ValutazioneAvvenutaConSuccesso (Controller c) {
		IlControllore = c;
		setTitle("Azienda - Valutazione Avvenuta Con Successo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistrazioneProjectManager.class.getResource("/image/ingranaggio blu.png")));
		contentPanel.setBackground(SystemColor.activeCaption);
		setBounds(80, 80, 350, 200);
		setMinimumSize(new Dimension(200,250));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Complimenti, azione avvenuta con successo");
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.activeCaption);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Ok");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int caso=9;
						IlControllore.RitornaBenvenutoProjectManager(caso);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
