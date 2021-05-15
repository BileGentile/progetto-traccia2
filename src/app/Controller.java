package app;

import gui.Presentazione;
import gui.RegistrazioneProjectManager;
import gui.RegistrazioneSviluppatore;
import gui.ValutazioneMembro;
import gui.LoginSviluppatore;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JTextField;

import dao_impl.MeetingDAOPostgresImpl;
import dao_impl.MembroDAOPostgresImpl;
import dao_impl.ProgettoDAOPostgresImpl;
import daos.MeetingDAO;
import daos.MembroDAO;
import daos.ProgettoDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Meeting;
import entity.Membro;
import entity.Progetto;
import exceptions.ConnectionException;
import gui.AggiungiMembroAlProgetto;
import gui.AggiungiPresenza;
import gui.AggiungiProgetto;
import gui.AzioneAvvenutaConSuccesso;
import gui.BenvenutoProjectManager;
import gui.BenvenutoSviluppatore;
import gui.CreaMeeting;
import gui.EliminaProgetto;
import gui.ErroreCodiceFiscaleSbagliato;
import gui.LoginProjectManager;

public class Controller {

	int caso;
	Presentazione  presenta;
	LoginProjectManager loginPM;
	LoginSviluppatore loginS;
	RegistrazioneProjectManager registrazionePM;
	BenvenutoProjectManager benvenutoPM;
	BenvenutoSviluppatore benvenutoS;
	AggiungiProgetto aggiungiProgetto;
	RegistrazioneSviluppatore registrazioneS;
	ValutazioneMembro valutazioneMembro;
	AggiungiMembroAlProgetto aggiungiMembroAlProgetto;
	EliminaProgetto eliminaProgetto;
	AzioneAvvenutaConSuccesso azioneAvvenutaConSuccesso;
	ErroreCodiceFiscaleSbagliato erroreCodiceFiscaleSbagliato;
	CreaMeeting  creaMeeting;
	
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
	
	public void PresentazioneIniziale() {
		if(loginPM.isVisible()) {
			loginPM.setVisible(false);
			presenta.setVisible(true);
		}
		else {
			loginS.setVisible(false);
			presenta.setVisible(true);
		}
	}


// nel caso in cui il codice fiscale inserito sia sbagliato, e si clicchi su "ok", si ritornerà alla schermata di presentazione
	public void TornaPresentazione() {
		if(loginPM.isVisible()) {
			loginPM.setVisible(false);
			if(erroreCodiceFiscaleSbagliato.isVisible()) {
				erroreCodiceFiscaleSbagliato.setVisible(false);
			}
			presenta=new Presentazione(this);
			presenta.setVisible(true);
		}else {
			if(erroreCodiceFiscaleSbagliato.isVisible()) {
				erroreCodiceFiscaleSbagliato.setVisible(false);
			}
			loginS.setVisible(false);
			presenta=new Presentazione(this);
			presenta.setVisible(true);
		}
			
	}
// nel caso in cui il codice fiscale inserito sia sbagliato, e si clicchi su "riprova", si ritornerà alla schermata di login
public void TornaLogin(int caso) {

if(caso==1) {
	erroreCodiceFiscaleSbagliato.setVisible(false);
		if(loginPM.isVisible()) {
			loginPM.setVisible(false);
			loginPM=new LoginProjectManager(this);
			loginPM.setVisible(true);
		}else {
			//if (caso==2) {
			loginS.setVisible(false);
			loginS=new LoginSviluppatore(this);
			loginS.setVisible(true);
		}
}
else if(registrazionePM.isVisible()) {
	registrazionePM.setVisible(false);
	loginPM=new LoginProjectManager(this);
	loginPM.setVisible(true);
}else if (registrazioneS.isVisible()) {
	registrazioneS.setVisible(false);
	loginS=new LoginSviluppatore(this);
	loginS.setVisible(true);
}
	
}

	public void AvviaCreaProgetto() {
		benvenutoPM.setVisible(false);
		aggiungiProgetto= new AggiungiProgetto(this);
		aggiungiProgetto.setVisible(true);
	}
	
//verifica se il codice fiscale inserito dal project manager risulta corretto, se lo è avvia il benvenuto altrimenti da un messaggio di errore
	public void AvviaBenvenutoPM(JTextField codiceFiscale) {
		DBConnection dbconn = null;
        Connection connection = null;
        DBBuilder builder = null;
        try
	        {
	            dbconn = DBConnection.getInstance();
	            connection = dbconn.getConnection();
	            builder = new DBBuilder(connection);
	            MembroDAO dao = null;
	            
	            dao = new MembroDAOPostgresImpl(connection);
	            
	            List<Membro> lista = dao.getProjectManagerByCodFiscale(codiceFiscale.getText());
	            
	            if(lista.isEmpty()) {
	            	erroreCodiceFiscaleSbagliato = new ErroreCodiceFiscaleSbagliato(this);
	            	erroreCodiceFiscaleSbagliato.setVisible(true);

	            }else {
	            	loginPM.setVisible(false);
	            	benvenutoPM = new BenvenutoProjectManager(this);
	            	benvenutoPM.setVisible(true);
	            }
	        	}
	            catch (SQLException exception)
	            {
	                System.out.println("Errore SQLException: "+ exception.getMessage());
	            }
        }
	
//verifica se il codice fiscale inserito dallo sviluppatore risulta corretto, se lo è avvia il benvenuto altrimenti da un messaggio di errore

	public void AvviaBenvenutoS(String codiceFiscale) {
		DBConnection dbconn = null;
        Connection connection = null;
        DBBuilder builder = null;

        try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            MembroDAO dao = null;
            
            dao = new MembroDAOPostgresImpl(connection);
            
            List<Membro> lista = dao.getSviluppatoreByCodFiscale(codiceFiscale);
           
            if(lista.isEmpty()) {

            	erroreCodiceFiscaleSbagliato = new ErroreCodiceFiscaleSbagliato(this);
            	erroreCodiceFiscaleSbagliato.setVisible(true);

            }else {
            	loginS.setVisible(false);
    			benvenutoS=new BenvenutoSviluppatore (this);
    			benvenutoS.setVisible(true);
            
            }
        	}
            catch (SQLException exception)
            {
                System.out.println("Errore SQLException: "+ exception.getMessage());
            }
			}
	
//L'utente ha scelto di creare un nuovo account (del tipo project manager), si mostra la scheda di registrazione
	public void AvviaRegistrazioneProjectManager() {
		loginPM.setVisible(false);
		registrazionePM= new RegistrazioneProjectManager(this);
		registrazionePM.setVisible(true);
	}
	
//L'utente ha scelto di creare un nuovo account (del tipo sviluppatore), si mostra la scheda di registrazione
	public void AvviaRegistrazioneSviluppatore() {
		loginS.setVisible(false);
	    registrazioneS = new RegistrazioneSviluppatore(this);
	    registrazioneS.setVisible(true);
	    
	}

//Creazione di un nuovo project manager 
	public void RegistraProjectManager(String cognome, String nome, String codfiscale, String salario) {
        DBConnection dbconn = null;
        Connection connection = null;
        DBBuilder builder = null;
        
        try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            builder.createTableMembro();
            
            MembroDAO dao = null;
            
            dao = new MembroDAOPostgresImpl(connection);
            
		Membro m1  =  new Membro( nome, cognome, codfiscale , "ProjectManager", Integer.valueOf(salario), "NULL" );
		int res =  dao.inserisciMembro(m1);

        }
        catch (SQLException exception)
        {
            System.out.println("Errore SQLException: "+ exception.getMessage());
        } catch (ConnectionException e) {
			// TODO Auto-generated catch block
        	  System.out.println("CE: "+e);
		}
        
		registrazionePM.setVisible(false);
		loginPM= new LoginProjectManager(this);
		loginPM.setVisible(true);
	}
	

//Creazione di un nuovo sviluppatore 
	public void RegistraSviluppatore(JTextField cognomeS, JTextField nomeS, JTextField codiceFiscaleS,JTextField salario) {
        DBConnection dbconn = null;
        Connection connection = null;
        DBBuilder builder = null;

        try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            builder.createTableMembro();
            MembroDAO dao = null;
            
            dao = new MembroDAOPostgresImpl(connection);
            
		Membro m1  =  new Membro( nomeS.getText(), cognomeS.getText(), codiceFiscaleS.getText(), "Sviluppatore", Integer.valueOf(salario.getText()), "NULL");
		int res =  dao.inserisciMembro(m1);

        }
        catch (SQLException exception)
        {
            System.out.println("Errore SQLException: "+ exception.getMessage());
        } catch (ConnectionException e) {
        	  System.out.println("CE: "+e);
		}
        
		registrazioneS.setVisible(false);
		loginS= new LoginSviluppatore(this);
		loginS.setVisible(true);
	}

//Creazione di un nuovo progetto
	public void CreaProgetto(String nomeProgetto, String tipoProgetto , String ambitoProgetto) {
			DBConnection dbconn = null;
	        Connection connection = null;
	        DBBuilder builder = null;

	        try
	        {
	            dbconn = DBConnection.getInstance();
	            connection = dbconn.getConnection();
	            builder = new DBBuilder(connection);
	            builder.createTableProgetto();
	            ProgettoDAO dao = null;
	            
	            dao = new ProgettoDAOPostgresImpl(connection);
	            
	            Progetto p1  =  new Progetto(nomeProgetto, tipoProgetto, ambitoProgetto, "sequenzacodiceprogetti", "Incompleto" );
	            int res =  dao.inserisciProgetto(p1);
	        }
	        catch (SQLException exception)
	        {
	            System.out.println("Errore SQLException: "+ exception.getMessage());
	        }
	        catch (ConnectionException ex)
	        {
	            System.out.println("CE: "+ex);
	        }
			
        aggiungiProgetto.setVisible(false);
        benvenutoPM=new BenvenutoProjectManager(this);
    	benvenutoPM.setVisible(true);
}

	

	public void AvviaValutazione() {
		benvenutoPM.setVisible(false);
		valutazioneMembro = new ValutazioneMembro(this);
		valutazioneMembro.setVisible(true);
	}

	public void AvviaInserimentoMembro() {
		benvenutoPM.setVisible(false);
		aggiungiMembroAlProgetto = new AggiungiMembroAlProgetto(this);
		aggiungiMembroAlProgetto.setVisible(true);
	}

	

	public void RitornaBenvenuto() {
		if(valutazioneMembro.isVisible()) {
			valutazioneMembro.setVisible(false);
		}else {
		
		eliminaProgetto.setVisible(false);
		}
		azioneAvvenutaConSuccesso.setVisible(false);
		benvenutoPM = new BenvenutoProjectManager (this);
	    benvenutoPM.setVisible(true);
	    
	}

	public void AvviaEliminaProgetto() {
		benvenutoPM.setVisible(false);
		eliminaProgetto = new EliminaProgetto(this);
		eliminaProgetto.setVisible(true);
	}

	public void EliminaProgetto(String nomeProgetto) {
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
            
            dao.cambiaStatoProgetto(nomeProgetto);
           
        }
        catch (SQLException exception)
        {
            System.out.println("Errore SQLException: "+ exception.getMessage());
        }
        azioneAvvenutaConSuccesso = new AzioneAvvenutaConSuccesso(this);
        azioneAvvenutaConSuccesso.setVisible(true);
        
	}

	public void ValutazioneMembro(String valutazione, String codicefiscale) {
	DBConnection dbconn = null;
    Connection connection = null;
    DBBuilder builder = null;
    try
    {
        dbconn = DBConnection.getInstance();
        connection = dbconn.getConnection();
        builder = new DBBuilder(connection);
        MembroDAO dao = null;
        
        dao = new MembroDAOPostgresImpl(connection);
        
        dao.inserisciValutazione(valutazione , codicefiscale );
       
    }
    catch (SQLException exception)
    {
        System.out.println("Errore SQLException: "+ exception.getMessage());
    }
 azioneAvvenutaConSuccesso = new AzioneAvvenutaConSuccesso(this);
 azioneAvvenutaConSuccesso.setVisible(true);
	}

	public void AvviaCreaMeeting() {
		benvenutoPM.setVisible(false);
		creaMeeting = new CreaMeeting(this);
		creaMeeting.setVisible(true);
		
	}

	
	public void CreaMeeting(String tipologia, String data, String oraInizio, String piattaforma, String nomeSala) {
		DBConnection dbconn = null;
        Connection connection = null;
        DBBuilder builder = null;

        try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            builder.createTableMeeting();
            MeetingDAO dao = null;
            
            dao = new MeetingDAOPostgresImpl(connection);

        	
            Meeting p1  =  new Meeting("sequenzacodicemeeting" ,data , oraInizio , piattaforma ,tipologia , nomeSala  );
            int res =  dao.inserisciMeeting(p1);
        }
        catch (SQLException exception)
        {
            System.out.println("Errore SQLException: "+ exception.getMessage());
        }
        catch (ConnectionException ex)
        {
            System.out.println("CE: "+ex);
        }
	}

	

	

}