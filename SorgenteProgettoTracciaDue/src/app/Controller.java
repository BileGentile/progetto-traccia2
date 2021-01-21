package app;
import Gui.CosaFarePM;
import Gui.LoginProjectManager;
import Gui.Presentazione;

public class Controller {
Presentazione  presenta;
LoginProjectManager LoginPM;
CosaFarePM Cosafarepm;
	public static void main(String[] args) {

		Controller c =new Controller();
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

	public void AvviaLoginPM(String text) {
		System.out.print("L'utente ha inserito codice fiscale = "+ text);
	
	}
}
