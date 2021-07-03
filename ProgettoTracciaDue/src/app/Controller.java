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

import dao_impl.ArchivioPartecipantiProgettoDAOPostgresImpl;
import dao_impl.MeetingDAOPostgresImpl;
import dao_impl.MembroDAOPostgresImpl;
import dao_impl.ProgettoDAOPostgresImpl;
import dao_impl.SkillsDAOPostgresImpl;
import daos.ArchivioPartecipantiProgettoDAO;
import daos.MeetingDAO;
import daos.MembroDAO;
import daos.ProgettoDAO;
import daos.SkillsDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.ArchivioPartecipantiProgetto;
import entity.Meeting;
import entity.Membro;
import entity.Progetto;
import entity.Skills;
import exceptions.ConnectionException;
import gui.AggiungiMembriAlProgetto;
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
	AggiungiMembriAlProgetto aggiungiMembriAlProgetto;
	EliminaProgetto eliminaProgetto;
	AzioneAvvenutaConSuccesso azioneAvvenutaConSuccesso;
	ErroreCodiceFiscaleSbagliato erroreCodiceFiscaleSbagliato;
	CreaMeeting  creaMeeting;
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

	// nel caso in cui il codice fiscale inserito sia sbagliato, e si clicchi su "ok", si ritornerà alla schermata di presentazione
	public void TornaPresentazione() {
		if(loginPM.isVisible()) {
			loginPM.setVisible(false);
			presenta=new Presentazione(this);
			presenta.setVisible(true);
		
		}else if(loginS.isVisible()) {
			loginS.setVisible(false);
			presenta=new Presentazione(this);
			presenta.setVisible(true);
		}
			if(erroreCodiceFiscaleSbagliato.isVisible()) {
				erroreCodiceFiscaleSbagliato.setVisible(false);
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
		}else{
			loginS.setVisible(false);
			loginS=new LoginSviluppatore(this);
			loginS.setVisible(true);
		}
	}else if(caso==2) {
		registrazionePM.setVisible(false);
		loginPM=new LoginProjectManager(this);
		loginPM.setVisible(true);
	}else if(caso==3) {
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
	public void RegistraProjectManager(String cognome, String nome, String codfiscale, String salario, boolean selected, boolean selected2, boolean selected3, boolean selected4) {
        DBConnection dbconn = null;
        Connection connection = null;
        DBBuilder builder = null;
        
        try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            builder.createTableMembro();
            builder.createTableSkills();

            MembroDAO daoMembro = null;
            SkillsDAO daoSkill =null;

            daoMembro = new MembroDAOPostgresImpl(connection);
            daoSkill = new SkillsDAOPostgresImpl(connection);	
            
         	
            Membro m1  =  new Membro( nome, cognome, codfiscale , "ProjectManager", Integer.valueOf(salario), "NULL");
            int res =  daoMembro.inserisciMembro(m1);
		
            if(selected==true) {
                Skills s1 = new Skills(codfiscale,"Puntualità");

        		int res1= daoSkill.inserisciSkills(s1);

                }
                if(selected2==true) {
                Skills s2 = new Skills(codfiscale,"Organizzazione");

        		int res2= daoSkill.inserisciSkills(s2);

                }
                if(selected3==true) {
                Skills s3 = new Skills(codfiscale,"Problem Solving");
        		int res3= daoSkill.inserisciSkills(s3);

            	}
            	if(selected4==true) {
                Skills s4 = new Skills(codfiscale,"Empatia");

        		int res4= daoSkill.inserisciSkills(s4);

            	}
       

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
	public void RegistraSviluppatore(JTextField cognomeS, JTextField nomeS, JTextField codiceFiscaleS,JTextField salario, String skillDaInserire) {
		DBConnection dbconn = null;
        Connection connection = null;
        DBBuilder builder = null;

        try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            builder.createTableMembro();
            builder.createTableSkills();
            MembroDAO daoMembro = null;
            SkillsDAO daoSkill =null;
            
            daoMembro = new MembroDAOPostgresImpl(connection);
            daoSkill = new SkillsDAOPostgresImpl(connection);		
            
            Membro m1  =  new Membro( nomeS.getText(), cognomeS.getText(), codiceFiscaleS.getText(), "Sviluppatore", Integer.valueOf(salario.getText()), "NULL");
            int res =  daoMembro.inserisciMembro(m1);
            Skills s1=new Skills(codiceFiscaleS.getText(),skillDaInserire);
            int res1= daoSkill.inserisciSkills(s1);
            	
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
	public void CreaProgetto(String nomeProgetto, String tipoProgetto , String ambitoProgetto, String codiceFiscalePm) {
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
	            
	            List<Membro> lista = dao.getProjectManagerByCodFiscale(codiceFiscalePm);
	           
	            if(lista.isEmpty()) {

	            	erroreCodiceFiscaleSbagliato = new ErroreCodiceFiscaleSbagliato(this);
	            	erroreCodiceFiscaleSbagliato.setVisible(true);
	            }else {
	           
	            builder.createTableArchivioPartecipantiProgetto();
	            ArchivioPartecipantiProgettoDAO dao1 =  null;
                
                dao1= new ArchivioPartecipantiProgettoDAOPostgresImpl(connection);
                ArchivioPartecipantiProgetto a1 = new ArchivioPartecipantiProgetto(codiceFiscalePm,nomeProgetto,"ProjectManager");
                int res= dao1.InserisciArchivioPartecipanti(a1);
                
                builder.createTableProgetto();
	            ProgettoDAO dao2 = null;
	            
	            dao2 = new ProgettoDAOPostgresImpl(connection);
	            Progetto p2  =  new Progetto(nomeProgetto, tipoProgetto, ambitoProgetto, "sequenzacodiceprogetti", "Incompleto" );
	            int res2 =  dao2.inserisciProgetto(p2);
	            
	            }
	            
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

	public void AvviaInserimentoMembri() {
		benvenutoPM.setVisible(false);
		aggiungiMembriAlProgetto = new AggiungiMembriAlProgetto(this);
		aggiungiMembriAlProgetto.setVisible(true);
	}

	public void RitornaBenvenuto(int caso) {
		if(caso== 1){
			valutazioneMembro.setVisible(false);
		}else if (caso ==2){
			eliminaProgetto.setVisible(false);
		}else if(caso==3){
			azioneAvvenutaConSuccesso.setVisible(false);
			eliminaProgetto.setVisible(false);
		}else if(caso==4) {
			aggiungiProgetto.setVisible(false);
		}
		
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
	
	public void Logout() {
		if(benvenutoPM.isVisible())
		{
			benvenutoPM.setVisible(false);
		}else if(benvenutoS.isVisible()){
			benvenutoS.setVisible(false);
		}
		presenta = new Presentazione(this);
		presenta.setVisible(true);
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

	public void CreaMeeting(String tipologia, String data, String oraInizio, String piattaforma, String nomeSala,  int durata) {
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

        	
            Meeting p1  =  new Meeting("sequenzacodiceMeeting" ,data, oraInizio , piattaforma ,tipologia , nomeSala, durata );
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
        creaMeeting.setVisible(false);
        benvenutoPM = new BenvenutoProjectManager(this);
        benvenutoPM.setVisible(true);
		
	}

	public void CreaArchivioPartecipanti(String codiceFiscale, String NomeProgetto) {
	DBConnection dbconn = null;
    Connection connection = null;
    DBBuilder builder = null;
    try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            builder.createTableArchivioPartecipantiProgetto();
            ArchivioPartecipantiProgettoDAO dao =  null;
            
            dao= new ArchivioPartecipantiProgettoDAOPostgresImpl(connection);
            ArchivioPartecipantiProgetto a1 = new ArchivioPartecipantiProgetto(codiceFiscale,NomeProgetto, "Sviluppatore");
            int res= dao.InserisciArchivioPartecipanti(a1);
            
        }
    catch (SQLException exception)
    	{
        System.out.println("Errore SQLException: "+ exception.getMessage());
    	}
    catch (ConnectionException ex)
    	{
        System.out.println("CE: "+ex);
    	}
    aggiungiMembriAlProgetto.setVisible(false);
	
    benvenutoPM = new BenvenutoProjectManager (this);
    benvenutoPM.setVisible(true); 
}

	public void TornaPresentazione2(int caso) {
	if(caso==1) {
		loginPM.setVisible(false);
		
	}else{
		loginS.setVisible(false);
		
	}
	presenta=new Presentazione(this);
	presenta.setVisible(true);
	}

	public void AvviaAggiungiPresenza() {
	    benvenutoS.setVisible(false);
	    aggiungiPresenza = new AggiungiPresenza(this);
	    aggiungiPresenza.setVisible(true);
	}
	
}