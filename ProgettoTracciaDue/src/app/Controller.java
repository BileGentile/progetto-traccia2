package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import javax.swing.JTextField;

import dbConfig.DBBuilder;
import dbConfig.DBConnection;

import dao_impl.AmbitoDAOPostgresImpl;
import dao_impl.MeetingDAOPostgresImpl;
import dao_impl.MeetingFisicoDAOPostgresImpl;
import dao_impl.MeetingTelematicoDAOPostgresImpl;
import dao_impl.MembroDAOPostgresImpl;
import dao_impl.ProgettoDAOPostgresImpl;
import dao_impl.ProjectManagerDAOPostgresImpl;
import dao_impl.SkillsDAOPostgresImpl;
import dao_impl.SviluppatoreDAOPostgresImpl;
import daos.AmbitoDAO;
import daos.MeetingDAO;
import daos.MeetingFisicoDAO;
import daos.MeetingTelematicoDAO;
import daos.MembroDAO;
import daos.ProgettoDAO;
import daos.ProjectManagerDAO;
import daos.SkillsDAO;
import daos.SviluppatoreDAO;
import entity.Ambito;
import entity.Meeting;
import entity.MeetingFisico;
import entity.MeetingTelematico;
import entity.Membro;
import entity.Progetto;
import entity.ProjectManager;
import entity.Skills;
import entity.Sviluppatore;
import exceptions.ConnectionException;

import gui.Presentazione;
import gui.ProgettiSviluppatore;
import gui.ProgettoEliminatoConSuccesso;
import gui.LoginSviluppatore;
import gui.MeetingSviluppatore;
import gui.PartecipantiAlMeeting;
import gui.LoginProjectManager;
import gui.RegistrazioneProjectManager;
import gui.ErroreCodiceFiscaleSbagliato;
import gui.BenvenutoProjectManager;
import gui.BenvenutoSviluppatore;
import gui.RegistrazioneSviluppatore;
import gui.ValutazioneMembro;
import gui.PartecipantiAlProgetto;
import gui.AggiungiMembriAlProgetto;
import gui.AggiungiPresenza;
//import gui.AggiungiPresenzaSviluppatore;
import gui.AggiungiProgetto;
import gui.ValutazioneAvvenutaConSuccesso;
import gui.CreaMeeting;
import gui.EliminaProgetto;
import gui.ErroreCreazioneProgetto;
import gui.ErroreInserimentoPartecipante;

public class Controller {

	int caso;
	Presentazione  presenta;
	LoginProjectManager loginPM;
	LoginSviluppatore loginS;
	RegistrazioneProjectManager registrazionePM;
	ErroreCodiceFiscaleSbagliato erroreCodiceFiscaleSbagliato;
	BenvenutoProjectManager benvenutoPM;
	BenvenutoSviluppatore benvenutoS;
	RegistrazioneSviluppatore registrazioneS;
	AggiungiProgetto aggiungiProgetto;
	ValutazioneMembro valutazioneMembro;
	AggiungiMembriAlProgetto aggiungiMembriAlProgetto;
	EliminaProgetto eliminaProgetto;
	ProgettoEliminatoConSuccesso progettoEliminatoConSuccesso;
	CreaMeeting  creaMeeting;
	AggiungiPresenza aggiungiPresenza;
	PartecipantiAlProgetto partecipantiAlProgetto;
	ErroreCreazioneProgetto erroreCreazioneProgetto;
	ValutazioneAvvenutaConSuccesso valutazioneAvvenutaConSuccesso;
	ErroreInserimentoPartecipante erroreInserimentoPartecipante;
	PartecipantiAlMeeting partecipantiAlMeeting;
	ProgettiSviluppatore progettiSviluppatore;
	MeetingSviluppatore meetingSviluppatore;
	
	public static void main(String[] args) {
		
		//CREAZIONE DEL DATABASE E DELLA CONNECTION
		DBConnection dbconn = null;
        Connection connection = null;
        DBBuilder builder = null;
 
        try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            
            //CREAZIONE ENUM
            //builder.createEnumRuolo();
            //builder.createEnumTipologia();
            
            //CREAZIONE SEQUENZE
            builder.createSequenceProgetto();
            builder.createSequenceMeetingFisico();
            builder.createSequenceMeetingTelematico();
            builder.createSequenceAmbito();
            builder.createSequenceSkills();
            
            //CREAZIONE CLASSI 
            builder.createTableProjectManager();
            builder.createTableSviluppatore();
            builder.createTableProgetto();
            builder.createTableAmbito();
            builder.createTableMeetingFisico();
            builder.createTableMeetingTelematico();
            builder.createTableSkills();
            
            //CREAZIONE CLASSI D'ASSOCIAZIONE
            builder.createTablePartecipazioniProgetto();
            builder.createTableAssociazioneAmbito();
            builder.createTablePartecipazioniSviluppatoreMeetingFisico();
            builder.createTablePartecipazioniProjectManagerMeetingFisico();
            builder.createTablePartecipazioniSviluppatoreMeetingTelematico();
            builder.createTablePartecipazioniProjectManagerMeetingTelematico();
            builder.createTableAssociazioneSkillsProjectManager();
            builder.createTableAssociazioneSkillsSviluppatore();
            
            //CREAZIONE TRIGGER 
            builder.createTriggerPartecipazioneAlProgetto();
            builder.createTriggerDuplicatidelleSkills();
            builder.createTriggerCodSkills();
          
//            INSERIRE DELLE SKILLS DI DEFAULT
//            SkillsDAO daos = null;
//            daos = new SkillsDAOPostgresImpl(connection);	
//            Skills s = new Skills("Empatia","sequenzacodiceskills");
//    		int re1= daos.inserisciSkills(s);
//    		
//    		SkillsDAO daos2 = null;
//            daos2 = new SkillsDAOPostgresImpl(connection);	
//            Skills s2 = new Skills("Puntualità","sequenzacodiceskills");
//     		int re2= daos2.inserisciSkills(s2);
//    		
//     		SkillsDAO daos3 = null;
//            daos3 = new SkillsDAOPostgresImpl(connection);	
//            Skills s3 = new Skills("Problem Solving","sequenzacodiceskills");
//     		int re3= daos3.inserisciSkills(s3);
//   		
     		
     		 MembroDAO dao = null;
             ProgettoDAO daoo = null;
             MeetingDAO daooo=null;
             
             dao = new MembroDAOPostgresImpl(connection);

            
            //TEST PER IL DATABASE, SIMULO L'INSERIMENTO DI TRE MEMBRI, TOGLI COMMENTO PER TESTARE
            
//            Membro m1  =  new Membro("Mario", "Biondi", "MROVRD77L99K776J", "Schifo", "ProjectManager");
//            Membro m2  =  new Membro("Pino", "Verdi", "PNOVRD77L99K776J", "Bravo", "Sviluppatore");
//            Membro m3  =  new Membro("Rino", "Ceronte", "RNOVRD77L99K775P", "Eccellente", "Sviluppatore");
//            int res =  dao.inserisciMembro(m1);
//            System.out.println(res);
//            int res2 = dao.inserisciMembro(m2);
//            System.out.println(res2);
//            int res3 = dao.inserisciMembro(m3);
//            System.out.println(res3);
//        
        }
        catch (SQLException exception)
        {
            System.out.println("Errore SQLException: "+ exception.getMessage());
        }
        catch (ConnectionException ex)
        {
            System.out.println("CE: "+ex);
        }
        
        //START CONTROLLER
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
            ProjectManagerDAO dao = null;
            
            dao = new ProjectManagerDAOPostgresImpl(connection);
            
	            List<ProjectManager> lista = dao.getProjectManagerByCodFiscale(codiceFiscale.getText());
	            
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
            SviluppatoreDAO dao = null;
            
            dao = new SviluppatoreDAOPostgresImpl(connection);
            
            List<Sviluppatore> lista = dao.getSviluppatoreByCodFiscale(codiceFiscale);
           
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
	
	public void TornaPresentazione2(int caso) {
		if(caso==1) {
			loginPM.setVisible(false);	
		}else{
			loginS.setVisible(false);	
		}
		presenta=new Presentazione(this);
		presenta.setVisible(true);
	}
	
	public void Logout(int caso) {
		if(caso==1)
		{
			benvenutoPM.setVisible(false);
		}else if(caso==2){
			benvenutoS.setVisible(false);
		}
		presenta = new Presentazione(this);
		presenta.setVisible(true);
	}

	//Creazione di un nuovo sviluppatore 
	public void RegistraSviluppatore(String nome, String cognome, String codfiscale, String salario, List<String> list ){
		DBConnection dbconn = null;
        Connection connection = null;
        DBBuilder builder = null;

        try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            builder.createTableSviluppatore();
            builder.createTableSkills();
            builder.createTableAssociazioneSkillsSviluppatore();
            SviluppatoreDAO daoSviluppatore = null;
            SkillsDAO daoSkill =null;

            daoSviluppatore = new  SviluppatoreDAOPostgresImpl(connection);
         
            
            Sviluppatore m1  =  new Sviluppatore(nome, cognome, codfiscale, "Sviluppatore", Integer.valueOf(salario), "NULL");
      
            int res =  daoSviluppatore.inserisciSviluppatore(m1);
            	int i= 0;
            	while (i<list.size()) {
            		String s1=list.get(i);         
            		int res2= daoSviluppatore.inserisciSkillSviluppatore(m1,s1);
            		i++;
            	}
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
	
	//Creazione di un nuovo project manager 
	public void RegistraProjectManager(String nome, String cognome, String codfiscale, String salario, List<String> list ) {
	        DBConnection dbconn = null;
	        Connection connection = null;
	        DBBuilder builder = null;
	       
	        try
	        {
	        	dbconn = DBConnection.getInstance();
	            connection = dbconn.getConnection();
	            builder = new DBBuilder(connection);
	            builder.createTableProjectManager();
	            builder.createTableSkills();
	            builder.createTableAssociazioneSkillsProjectManager(); 
	            ProjectManagerDAO daoProjectManager = null;
	            SkillsDAO daoSkill =null;

	            daoProjectManager = new ProjectManagerDAOPostgresImpl(connection);
	            
	            ProjectManager m1  =  new ProjectManager(nome, cognome, codfiscale, "ProjectManager", Integer.valueOf(salario));
	           
	            int res =  daoProjectManager.inserisciProjectManager(m1);
	            int i= 0;
            	while (i<list.size()) {
            		String s1=list.get(i);
            		int res2= daoProjectManager.inserisciSkillProjectManager(m1,s1);
            		i++;
            	}
        }
	        catch (SQLException exception)
	        {
	            System.out.println("Errore SQLException: "+ exception.getMessage());
	        } catch (ConnectionException e) {
	        	  System.out.println("CE: "+e);
			}
	        
			registrazionePM.setVisible(false);
			loginPM= new LoginProjectManager(this);
			loginPM.setVisible(true);
	}
	
	public void AvviaCreaProgetto(int caso) {
		if( caso ==1) {
			benvenutoPM.setVisible(false);
			aggiungiProgetto= new AggiungiProgetto(this);
			aggiungiProgetto.setVisible(true);
		}else {
			erroreCreazioneProgetto.setVisible(false);
		}	
	}
	
	public void RitornaBenvenutoProjectManager(int caso) {
		if(caso== 1){
			valutazioneMembro.setVisible(false);
		}else if (caso ==2){
			eliminaProgetto.setVisible(false);
		}else if(caso==3){
			eliminaProgetto.setVisible(false);
			progettoEliminatoConSuccesso.setVisible(false);			
		}else if(caso==4) {
			aggiungiProgetto.setVisible(false);
		}else if (caso==5) {
			creaMeeting.setVisible(false);
		}else if(caso==6) {
			aggiungiMembriAlProgetto.setVisible(false);
		}else if(caso==7) {
			partecipantiAlProgetto.setVisible(false);
		}else if(caso==8) {
			erroreCreazioneProgetto.setVisible(false);
			aggiungiProgetto.setVisible(false);
		}else if(caso ==9) {
			valutazioneMembro.setVisible(false);
			valutazioneAvvenutaConSuccesso.setVisible(false);	
		}else if (caso ==10) {
			erroreInserimentoPartecipante.setVisible(false);
			aggiungiMembriAlProgetto.setVisible(false);
		}else if (caso==11) {
			partecipantiAlMeeting.setVisible(false);
		}
		benvenutoPM = new BenvenutoProjectManager (this);
	    benvenutoPM.setVisible(true);
	}
	
	//Creazione di un nuovo progetto
	public void CreaProgetto(String nomeProgetto, String tipoProgetto , List<String> ListaAmbiti, String codiceFiscalePm) {
			DBConnection dbconn = null;
	        Connection connection = null;
	        DBBuilder builder = null;
	        try
	        {
	            dbconn = DBConnection.getInstance();
	            connection = dbconn.getConnection();
	            builder = new DBBuilder(connection);
	            ProjectManagerDAO dao = null;
	            
	            dao = new ProjectManagerDAOPostgresImpl(connection);
	            
	            List<ProjectManager> lista = dao.getProjectManagerByCodFiscale(codiceFiscalePm);
		           
	            if(lista.isEmpty()) {

	            	erroreCreazioneProgetto = new ErroreCreazioneProgetto(this);
	            	erroreCreazioneProgetto.setVisible(true);
	            	
	            }else {	
	            	builder.createTableProgetto();
	            	ProgettoDAO dao1 = null;
	            	dao1 = new ProgettoDAOPostgresImpl(connection);
	            	Progetto p2  =  new Progetto (nomeProgetto, tipoProgetto, "sequenzacodiceprogetti", "Incompleto", lista.get(0));
	            	int res =  dao1.inserisciProgetto(p2);
	          	
	            	builder.createTableAmbito();
	            	AmbitoDAO dao3 = null;
	            	dao3 = new AmbitoDAOPostgresImpl(connection);
	            	int i= 0;
	            	while (i<ListaAmbiti.size()) {
	            		String s1=ListaAmbiti.get(i);
	            		int res3= dao3.inserisciAmbitoProgetto(nomeProgetto,s1);
	            		i++;
	            	}
	            	aggiungiProgetto.setVisible(false);
	    	        benvenutoPM=new BenvenutoProjectManager(this);
	        		benvenutoPM.setVisible(true);      	
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
    	
	}
	
	public void AvviaInserimentoMembri(int caso) {
		if (caso==0) {
			benvenutoPM.setVisible(false);
			aggiungiMembriAlProgetto = new AggiungiMembriAlProgetto(this);
			aggiungiMembriAlProgetto.setVisible(true);
		}else if(caso==1) {
			erroreInserimentoPartecipante.setVisible(false);
		}
	}
	
	public void CreaArchivioPartecipanti(String codiceFiscale, String codProgetto) {
		DBConnection dbconn = null;
	    Connection connection = null;
	    DBBuilder builder = null;
	    
	    try
	    {
	    	dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            builder.createTablePartecipazioniProgetto();
            ProgettoDAO dao =  null;
            
            dao= new ProgettoDAOPostgresImpl(connection);
            int res= dao.inserisciArchivioPartecipantiProgettoPS(codiceFiscale, codProgetto);
            int ris= dao.inserimentoAvvenutoConSuccesso(codiceFiscale, codProgetto);
            if(ris ==-1) {
            	erroreInserimentoPartecipante = new ErroreInserimentoPartecipante(this);
            	erroreInserimentoPartecipante.setVisible(true);
            }else {
            	aggiungiMembriAlProgetto.setVisible(false);	
        	    benvenutoPM = new BenvenutoProjectManager (this);
        	    benvenutoPM.setVisible(true); 
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
	}
	
	public void AvviaValutazione() {
		benvenutoPM.setVisible(false);
		valutazioneMembro = new ValutazioneMembro(this);
		valutazioneMembro.setVisible(true);
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
			SviluppatoreDAO dao = null;
        
			dao = new SviluppatoreDAOPostgresImpl(connection);
        
			dao.inserisciValutazione(valutazione , codicefiscale );
			
		}
		catch (SQLException exception)
		{
			System.out.println("Errore SQLException: "+ exception.getMessage());
		}
		valutazioneAvvenutaConSuccesso = new ValutazioneAvvenutaConSuccesso(this);
		valutazioneAvvenutaConSuccesso.setVisible(true);
	}
	
	public void AvviaPartecipantiAlProgetto(int caso) {
		if(caso ==1) {
			erroreInserimentoPartecipante.setVisible(false);
		}else {
			benvenutoPM.setVisible(false);
		}
		partecipantiAlProgetto = new PartecipantiAlProgetto(this);
		partecipantiAlProgetto.setVisible(true);
	}
	
	public void AvviaEliminaProgetto() {
		benvenutoPM.setVisible(false);
		eliminaProgetto = new EliminaProgetto(this);
		eliminaProgetto.setVisible(true);
	}
	
	public void AvviaCreaMeeting() {
		benvenutoPM.setVisible(false);
		creaMeeting = new CreaMeeting(this);
		creaMeeting.setVisible(true);
	}

	public void AvviaAggiungiPresenza() {
	    benvenutoS.setVisible(false);
	    aggiungiPresenza = new AggiungiPresenza(this);
	    aggiungiPresenza.setVisible(true);
	}
	
	public void RitornaBenvenutoSviluppatore(int caso) {
		if(caso== 1){
			aggiungiPresenza.setVisible(false);
		}
		else if(caso ==2)
		{
			progettiSviluppatore.setVisible(false);
		}
		else if(caso==3) {
			meetingSviluppatore.setVisible(false);
		}
		benvenutoS = new BenvenutoSviluppatore(this);
	    benvenutoS.setVisible(true);
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
        progettoEliminatoConSuccesso = new ProgettoEliminatoConSuccesso(this);
        progettoEliminatoConSuccesso.setVisible(true); 
    }
	
	public void CreaMeeting(String tipologia, String titolo, Date data, String oraInizio, String oraFine, String luogo, String nomeSala, String piattaforma, String organizzatore, String NomeProgetto) {
		DBConnection dbconn = null;
	    Connection connection = null;
	    DBBuilder builder = null;
	    String codiceProgetto;
	    Progetto p;
        try
        {
            	
            	dbconn = DBConnection.getInstance();
            	connection = dbconn.getConnection();
            	builder = new DBBuilder(connection);
            	ProgettoDAO dao = null;			
            	
            	dao = new ProgettoDAOPostgresImpl(connection);
            	codiceProgetto= dao.getProgettoByNome(NomeProgetto);
            	p= new Progetto(codiceProgetto);
            	
            	dbconn = DBConnection.getInstance();
                connection = dbconn.getConnection();
                builder = new DBBuilder(connection);
                
            	if(tipologia.equals("Telematico")) {
            		builder.createTableMeetingTelematico();
            		MeetingTelematicoDAO dao1 = null;
            		dao1 = new MeetingTelematicoDAOPostgresImpl(connection);
            		MeetingTelematico p1  =  new MeetingTelematico("sequenzacodicemeetingtelematico", titolo, data, oraInizio , oraFine, p, piattaforma );
            		//AGGIUNGERE LINK??
            		int res =  dao1.inserisciMeetingTelematico(p1);
            		MeetingTelematico m= dao1.getMeetingTelematicoByTitolo(titolo);
            		int res2= dao1.getInserisciPartecipazionePM(organizzatore, m.getCodMeet());
            	}else if (tipologia.equals("Fisico")) {
            		builder.createTableMeetingFisico(); 
            		MeetingFisicoDAO dao1 = null;
            		dao1 = new MeetingFisicoDAOPostgresImpl(connection);
            		MeetingFisico p1  =  new MeetingFisico("sequenzacodicemeetingfisico", titolo, data, oraInizio , oraFine , p , luogo, nomeSala);
            		int res =  dao1.inserisciMeetingFisico(p1);
            		MeetingFisico m= dao1.getMeetingFisicoByTitolo(titolo);
            		int res2= dao1.getInserisciPartecipazionePM(organizzatore, m.getCodMeet());
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
        creaMeeting.setVisible(false);
        benvenutoPM = new BenvenutoProjectManager(this);
        benvenutoPM.setVisible(true);

	}

	public void AggiungiArchivioPartecipantiMeeting(String CF, String Tipologia, String titoloMeeting) {
		DBConnection dbconn = null;
	    Connection connection = null;
	    DBBuilder builder = null;
	    try
        {
	    	dbconn = DBConnection.getInstance();
	    	connection = dbconn.getConnection();
	    	builder = new DBBuilder(connection);
	    	
	    	if(Tipologia.equals("Telematico")) {
	    		MeetingTelematicoDAO dao = null;
	    		dao = new MeetingTelematicoDAOPostgresImpl(connection); 
	    		MeetingTelematico m =dao.getMeetingTelematicoByTitolo(titoloMeeting);
	    		int ris=dao.getInserisciPartecipazione(CF,m.getCodMeet());
	    	}else {
	    		MeetingFisicoDAO dao = null;
	    		dao = new MeetingFisicoDAOPostgresImpl(connection); 
	    		MeetingFisico m=dao.getMeetingFisicoByTitolo(titoloMeeting);
	    		int ris=dao.getInserisciPartecipazione(CF,m.getCodMeet());
	    	}
	    }
        catch (SQLException exception)
        {
            System.out.println("Errore SQLException: "+ exception.getMessage());
        } 	
		aggiungiPresenza.setVisible(false);
		benvenutoS = new BenvenutoSviluppatore(this);
		benvenutoS.setVisible(true);
	}
	
	public void AvviaPartecipantiMeeting() {
		benvenutoPM.setVisible(false);
		partecipantiAlMeeting= new PartecipantiAlMeeting(this);
		partecipantiAlMeeting.setVisible(true);
	}
	
	public void AvviaProgettiSviluppatore() {
		benvenutoS.setVisible(false);
		progettiSviluppatore= new ProgettiSviluppatore(this);
		progettiSviluppatore.setVisible(true);
	}
	
	public void  AvviaMeetingSviluppatore() {
		benvenutoS.setVisible(false);
		meetingSviluppatore= new MeetingSviluppatore(this);
		meetingSviluppatore.setVisible(true);
	}

}