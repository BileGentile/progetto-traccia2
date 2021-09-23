package app;

import java.sql.Connection;
import java.sql.SQLException;
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
import dao_impl.ProgettoDAOPostgresImpl;
import dao_impl.ProjectManagerDAOPostgresImpl;
import dao_impl.SkillsDAOPostgresImpl;
import dao_impl.SviluppatoreDAOPostgresImpl;
import daos.AmbitoDAO;
import daos.MeetingDAO;
import daos.MeetingFisicoDAO;
import daos.MeetingTelematicoDAO;
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
import gui.AggiungiProgetto;
import gui.ValutazioneAvvenutaConSuccesso;
import gui.CreaMeeting;
import gui.EliminaProgetto;
import gui.ErroreCreazioneProgetto;
import gui.ErroreInserimentoPartecipante;
import gui.ErroreCodFiscaleNonRegistrabile;

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
	ErroreCodFiscaleNonRegistrabile erroreCodFiscaleNonRegistrabile;
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
            builder.createTablePartecipazioniSviluppatoreMeetingTelematico();
            builder.createTableAssociazioneSkillsProjectManager();
            builder.createTableAssociazioneSkillsSviluppatore();
            
            //CREAZIONE TRIGGER 
            builder.createTriggerPartecipazioneAlProgetto();
            builder.createTriggerDuplicatidelleSkills();
            builder.createTriggerCodSkills();
            builder.createTriggerCodProgetto();
            builder.createTriggerCodMeeting_Fisico();
            builder.createTriggerCodMeeting_Telematico();
            builder.createTrigger_cod_ambito();
              
            //INSERIRE DELLE SKILLS DI DEFAULT
            SkillsDAO daos = null;
            daos = new SkillsDAOPostgresImpl(connection);	
            Skills s = new Skills("Empatia","sequenzacodiceskills");
    		int re1= daos.inserisciSkills(s);
    		
    		SkillsDAO daos2 = null;
            daos2 = new SkillsDAOPostgresImpl(connection);	
            Skills s2 = new Skills("Puntualità","sequenzacodiceskills");
     		int re2= daos2.inserisciSkills(s2);
    		
     		SkillsDAO daos3 = null;
            daos3 = new SkillsDAOPostgresImpl(connection);	
            Skills s3 = new Skills("Problem Solving","sequenzacodiceskills");
     		int re3= daos3.inserisciSkills(s3);
     		
     		//INSERIRE AMBITI DI DEFAULT
     		AmbitoDAO daoA1 = null;
     		daoA1 = new AmbitoDAOPostgresImpl(connection);
     		Ambito a1 = new Ambito("Economia", "sequenzacodiceambito");
     		int re4 = daoA1.inserisciAmbito(a1);
     		
     		AmbitoDAO daoA2 = null;
     		daoA2 = new AmbitoDAOPostgresImpl(connection);
     		Ambito a2 = new Ambito("Medicina", "sequenzacodiceambito");
     		int re5 = daoA2.inserisciAmbito(a2);
     		
     		AmbitoDAO daoA3 = null;
     		daoA3 = new AmbitoDAOPostgresImpl(connection);
     		Ambito a3 = new Ambito("Informatica", "sequenzacodiceambito");
     		int re6 = daoA3.inserisciAmbito(a3);
     		
             ProgettoDAO daoo = null;
             MeetingDAO daooo=null;
             

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
		ProjectManager pm=new ProjectManager(codiceFiscale.getText());
		List<ProjectManager> lista= pm.accediPm(codiceFiscale.getText());
		if(lista.isEmpty()) {
			erroreCodiceFiscaleSbagliato = new ErroreCodiceFiscaleSbagliato(this);
			erroreCodiceFiscaleSbagliato.setVisible(true);
		}else {
			loginPM.setVisible(false);
			benvenutoPM = new BenvenutoProjectManager(this);
			benvenutoPM.setVisible(true);
		}
	}
	
	//verifica se il codice fiscale inserito dallo sviluppatore risulta corretto, se lo è avvia il benvenuto altrimenti da un messaggio di errore
	public void AvviaBenvenutoS(String codiceFiscale) {
		Sviluppatore s=new Sviluppatore(codiceFiscale);
		List<Sviluppatore> lista= s.accediS(codiceFiscale);
		
		if(lista.isEmpty()) {
			erroreCodiceFiscaleSbagliato = new ErroreCodiceFiscaleSbagliato(this);
            erroreCodiceFiscaleSbagliato.setVisible(true);
		}else {
			loginS.setVisible(false);
			benvenutoS=new BenvenutoSviluppatore (this);
			benvenutoS.setVisible(true);
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
		}else if (caso==2){
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
	public void RegistraSviluppatore(String cognome,String nome, String codfiscale, String salario, List<String> list ){
		boolean errore;
		if(codfiscale.length()!=16) {
        	errore=true;
		}else {
			Sviluppatore s=new Sviluppatore(codfiscale);
			s.RegistraS(cognome, nome, codfiscale, salario, list);
		}
		if(errore=false) {
			registrazioneS.setVisible(false);
			loginS= new LoginSviluppatore(this);
			loginS.setVisible(true);
		}
		else {	
			erroreCodFiscaleNonRegistrabile = new ErroreCodFiscaleNonRegistrabile(this);
			erroreCodFiscaleNonRegistrabile.setVisible(true);
		}
	}
	
	//Creazione di un nuovo project manager 
	public void RegistraProjectManager(String nome, String cognome, String codfiscale, String salario, List<String> list ) {
			boolean errore;
			if(codfiscale.length()!=16) {
            	errore=true;
			}else {
			ProjectManager pm=new ProjectManager(codfiscale);
			pm.RegistraPM(nome, cognome, codfiscale, salario, list);
			}
			if(errore=false) {
			registrazionePM.setVisible(false);
			loginPM= new LoginProjectManager(this);
			loginPM.setVisible(true);
			}else {
				erroreCodFiscaleNonRegistrabile = new ErroreCodFiscaleNonRegistrabile(this);
				erroreCodFiscaleNonRegistrabile.setVisible(true);

			}
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
		ProjectManager pm=new ProjectManager(codiceFiscalePm);
		List<ProjectManager> lista= pm.accediPm(codiceFiscalePm);
		if(lista.isEmpty()) {
			erroreCreazioneProgetto = new ErroreCreazioneProgetto(this);
			erroreCreazioneProgetto.setVisible(true);
	    }else {	
	    	pm.CreaProgetto(nomeProgetto, tipoProgetto, ListaAmbiti);
	    }
		aggiungiProgetto.setVisible(false);
		benvenutoPM=new BenvenutoProjectManager(this);
		benvenutoPM.setVisible(true);      	
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
		Progetto p=new Progetto(codProgetto);
		int ris= p.aggiungiPartecipante(codiceFiscale, codProgetto);
		if(ris ==-1) {
			erroreInserimentoPartecipante = new ErroreInserimentoPartecipante(this);
			erroreInserimentoPartecipante.setVisible(true);
		}else {
			aggiungiMembriAlProgetto.setVisible(false);	
			benvenutoPM = new BenvenutoProjectManager (this);
			benvenutoPM.setVisible(true); 
        }
	}
	
	public void AvviaValutazione() {
		benvenutoPM.setVisible(false);
		valutazioneMembro = new ValutazioneMembro(this);
		valutazioneMembro.setVisible(true);
	}
	
	public void ValutazioneMembro(String CodiceFiscalePm, String valutazione, String CodiceFiscaleS) {
		ProjectManager pm=new ProjectManager(CodiceFiscalePm);
		pm.ValutazioneMembro(valutazione, CodiceFiscaleS);
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
	
	public void EliminaProgetto(String CodiceFiscalePm, String nomeProgetto) {
		ProjectManager pm=new ProjectManager(CodiceFiscalePm);
		pm.EliminaProgetto(nomeProgetto);
        progettoEliminatoConSuccesso = new ProgettoEliminatoConSuccesso(this);
        progettoEliminatoConSuccesso.setVisible(true); 
    }
	
	public void CreaMeeting(String CodiceFiscalePm, String tipologia, String titolo, Date data, String oraInizio, String oraFine, String luogo, String nomeSala, String piattaforma, String NomeProgetto) {
		ProjectManager pm=new ProjectManager(CodiceFiscalePm);
		pm.CreazioneMeeting( tipologia,  titolo,  data,  oraInizio,  oraFine,  luogo,  nomeSala,  piattaforma,CodiceFiscalePm, NomeProgetto);
        creaMeeting.setVisible(false);
        benvenutoPM = new BenvenutoProjectManager(this);
        benvenutoPM.setVisible(true);
	}

	public void AggiungiArchivioPartecipantiMeeting(String CF, String Tipologia, String titoloMeeting) {
	    Meeting m= new Meeting();
	    m.aggiungiPartecipanteMeeting(CF, Tipologia, titoloMeeting);
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

	public void TornaPresentazione3() {
		if (registrazionePM!=null) {
			registrazionePM.setVisible(false);
			erroreCodFiscaleNonRegistrabile.setVisible(false);

		}else if(registrazioneS.isVisible()) {
			registrazioneS.setVisible(false);
			erroreCodFiscaleNonRegistrabile.setVisible(false);

		}
		presenta=new Presentazione(this);
		presenta.setVisible(true);
	}

	public void TornaRegistrazione() {
		if (registrazionePM!=null) {
			registrazionePM.setVisible(false);
			erroreCodFiscaleNonRegistrabile.setVisible(false);
			registrazionePM= new RegistrazioneProjectManager(this);	
			registrazionePM.setVisible(true);
		}
		else if(registrazioneS.isVisible()) {
			registrazioneS.setVisible(false);
			erroreCodFiscaleNonRegistrabile.setVisible(false);
			registrazioneS = new RegistrazioneSviluppatore(this);
			registrazioneS.setVisible(true);

	}
}

}