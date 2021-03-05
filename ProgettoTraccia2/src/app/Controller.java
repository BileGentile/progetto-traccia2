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

import dao_impl.MembroDAOPostgresImpl;
import dao_impl.ProgettoDAOPostgresImpl;
import daos.MembroDAO;
import daos.ProgettoDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Membro;
import entity.Progetto;
import exceptions.ConnectionException;
import gui.AggiungiMembroAlProgetto;
import gui.AggiungiPresenza;
import gui.AggiungiProgetto;
import gui.BenvenutoProjectManager;
import gui.BenvenutoSviluppatore;
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
	AggiungiPresenza aggiungiPresenza;
	
	
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
			presenta=new Presentazione(this);
			presenta.setVisible(true);
		}else {
			loginS.setVisible(false);
			presenta=new Presentazione(this);
			presenta.setVisible(true);
		}
			
	}

	public void AvviaCreaProgetto() {
		benvenutoPM.setVisible(false);
		aggiungiProgetto= new AggiungiProgetto(this);
		aggiungiProgetto.setVisible(true);
	}

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
	            	loginPM.setVisible(false);
	            	loginPM = new LoginProjectManager(this);
	            	loginPM.setVisible(true);

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
            	loginS.setVisible(false);
            	loginS= new LoginSviluppatore(this);
    			loginS.setVisible(true);
         

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

	public void AvviaRegistrazioneProjectManager() {
		loginPM.setVisible(false);
		registrazionePM= new RegistrazioneProjectManager(this);
		registrazionePM.setVisible(true);
	}

	public void RegistraProjectManager(String cognome, String nome, String codfiscale, String salario) {

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
            
		Membro m1  =  new Membro( nome, cognome, codfiscale , "ProjectManager", Integer.valueOf(salario) );
		int res =  dao.inserisciMembro(m1);

        }
        catch (SQLException exception)
        {
            System.out.println("Errore SQLException: "+ exception.getMessage());
        }
        
		registrazionePM.setVisible(false);
		loginPM= new LoginProjectManager(this);
		loginPM.setVisible(true);
		
		
	}
	public void AvviaBenvenutoS(JTextField codiceFiscale) {
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
	            
	            List<Membro> lista = dao.getSviluppatoreByCodFiscale(codiceFiscale.getText());
	            
	            if(lista.isEmpty()) {
	            	loginS.setVisible(false);
	            	loginS= new LoginSviluppatore(this);
	            	loginS.setVisible(true);

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
	
	public void RegistraSviluppatore(JTextField cognomeS, JTextField nomeS, JTextField codiceFiscaleS,JTextField salario) {

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
            
		Membro m1  =  new Membro( nomeS.getText(), cognomeS.getText(), codiceFiscaleS.getText(), "Sviluppatore", Integer.valueOf(salario.getText()));
		int res =  dao.inserisciMembro(m1);

        }
        catch (SQLException exception)
        {
            System.out.println("Errore SQLException: "+ exception.getMessage());
        }
        
		registrazioneS.setVisible(false);
		loginS= new LoginSviluppatore(this);
		loginS.setVisible(true);
		
		
	}

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
	            
	            Progetto p1  =  new Progetto(nomeProgetto, tipoProgetto, ambitoProgetto, "sequenzacodiceprogetti", "Completo" );
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

	public void AvviaRegistrazioneSviluppatore() {
		
		loginS.setVisible(false);
	    registrazioneS = new RegistrazioneSviluppatore(this);
	    registrazioneS.setVisible(true);
	    
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

	public void AvviaAggiungipresenza() {
		
    	benvenutoS.setVisible(false);
    	aggiungiPresenza = new AggiungiPresenza(this);
    	aggiungiPresenza.setVisible(true);
		
	}
	

	
	
}