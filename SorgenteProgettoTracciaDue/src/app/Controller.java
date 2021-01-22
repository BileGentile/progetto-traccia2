package app;

import gui.Presentazione;
import gui.LoginSviluppatore;
import gui.LoginProjectManager;

public class Controller {

	int caso;
	Presentazione  presenta;
	LoginProjectManager LoginPM;
	LoginSviluppatore LoginS;
	
	public static void main(String[] args) {

		Controller c = new Controller();
	}

	public Controller() {
		presenta=new Presentazione(this);
		presenta.setVisible(true);
	}
	

	public void LoginProjectManager() {
		presenta.setVisible(false);
		LoginPM= new LoginProjectManager(this);
		LoginPM.setVisible(true);
		
	}
	public void AvviaLoginSviluppatore() {
		presenta.setVisible(false);
		LoginS= new LoginSviluppatore(this);
		LoginS.setVisible(true);
		
	}

	public void TornaPresentazione(int caso) {
		if(caso==1) {
			LoginPM.setVisible(false);
		}else {
			LoginS.setVisible(false);
		}
			
			presenta=new Presentazione(this);
			presenta.setVisible(true);
		}
}
