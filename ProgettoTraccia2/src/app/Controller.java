package app;

import gui.Presentazione;
import gui.LoginSviluppatore;
import gui.AggiungiProgetto;
import gui.BenvenutoProjectManager;
import gui.BenvenutoSviluppatore;
import gui.LoginProjectManager;

public class Controller {

	int caso;
	Presentazione  presenta;
	LoginProjectManager loginPM;
	LoginSviluppatore loginS;
	BenvenutoProjectManager benvenutoPM;
	BenvenutoSviluppatore benvenutoS;
	AggiungiProgetto aggiungiprogetto;
	public static void main(String[] args) {

		Controller c = new Controller();
	}

	public Controller() {
		presenta=new Presentazione(this);
		presenta.setVisible(true);
	}
	

	public void LoginProjectManager() {
		presenta.setVisible(false);
		loginPM= new LoginProjectManager(this);
		loginPM.setVisible(true);
		
	}
	public void AvviaLoginSviluppatore() {
		presenta.setVisible(false);
		loginS= new LoginSviluppatore(this);
		loginS.setVisible(true);
		
	}

	public void TornaPresentazione(int caso) {
		if(caso==1) {
			loginPM.setVisible(false);
		}else {
			loginS.setVisible(false);
		}
			
			presenta=new Presentazione(this);
			presenta.setVisible(true);
		}

	public void CreaProgetto() {
		benvenutoPM.setVisible(false);
		aggiungiprogetto=new AggiungiProgetto(this);
		aggiungiprogetto.setVisible(true);
	}

	public void AvviaBenvenutoPM(String text) {
		if(text.equals("")) {
			loginPM.setVisible(false);
			loginPM.setVisible(true);

		}else {
			loginPM.setVisible(false);
			benvenutoPM=new BenvenutoProjectManager (this);
			benvenutoPM.setVisible(true);
		}
	}
	

	public void AvviaBenvenutoS(String text) {
		if(text.equals("")) {
			loginS.setVisible(false);
			loginS.setVisible(true);

		}else {
			loginS.setVisible(false);
			benvenutoS=new BenvenutoSviluppatore (this);
			benvenutoS.setVisible(true);
		}
	}
}
