package entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dao_impl.MeetingFisicoDAOPostgresImpl;
import dao_impl.MeetingTelematicoDAOPostgresImpl;
import daos.MeetingFisicoDAO;
import daos.MeetingTelematicoDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;

public class Meeting {

    //ATTRIBUTI

	private String CodMeet;
	private String Titolo;
	private Date Data;
    private String OraInizio;
    private String OraFine;
    private Progetto progettoMeeting;
    private String Organizzatore;
    private ArrayList<Sviluppatore> partecipanti;
    
    //COSTRUTTORI
   public Meeting(String codMeet, String titolo, Date data, String oraInizio, String oraFine,
			Progetto progettoMeeting, String organizzatore) {
		super();
		CodMeet = codMeet;
		Titolo = titolo;
		Data = data;
		OraInizio = oraInizio;
		OraFine = oraFine;
		Organizzatore = organizzatore;
		this.progettoMeeting = progettoMeeting;
	}
  
    public Meeting(String codMeet) {
    	super();
    	CodMeet = codMeet;
    }

	public Meeting() {
		super();
	}

	public String getCodMeet() {
		return CodMeet;
	}

	public void setCodMeet(String codMeet) {
		CodMeet = codMeet;
	}

	public Date getData() {
		return Data;
	}

	public void setData(Date data) {
		Data = data;
	}

	public String getOraInizio() {
		return OraInizio;
	}

	public void setOraInizio(String oraInizio) {
		OraInizio = oraInizio;
	}

	public String getOraFine() {
		return OraFine;
	}

	public void setOraFine(String oraFine) {
		OraFine = oraFine;
	}

	public Progetto getProgettoMeeting() {
		return progettoMeeting;
	}

	public void setProgettoMeeting(Progetto progettoMeeting) {
		this.progettoMeeting = progettoMeeting;
	}
	
	public ArrayList<Sviluppatore> getPartecipanti() {
		return partecipanti;
	}

	public void setPartecipanti(ArrayList<Sviluppatore> partecipanti) {
		this.partecipanti = partecipanti;
	}
	
	public String getTitolo() {
		return Titolo;
	}

	public void setTitolo(String titolo) {
		Titolo = titolo;
	}

	public String getOrganizzatore() {
		return Organizzatore;
	}

	public void setOrganizzatore(String organizzatore) {
		Organizzatore = organizzatore;
	}



	@Override
	public String toString() {
		return "Meeting [CodMeet=" + CodMeet + ", Data=" + Data + ", OraInizio=" + OraInizio + ", Durata=" + OraFine
				+ ", progettoMeeting=" + progettoMeeting + " Organizzatore=" + Organizzatore +"]";
	}

    //METODI
	
	public void aggiungiPartecipanteMeeting(String CF, String Tipologia, String titoloMeeting) {
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
	    		dao.InserisciPartecipazione(CF,m.getCodMeet());
	    	}else {
	    		MeetingFisicoDAO dao = null;
	    		dao = new MeetingFisicoDAOPostgresImpl(connection); 
	    		MeetingFisico m=dao.getMeetingFisicoByTitolo(titoloMeeting);
	    		dao.InserisciPartecipazione(CF,m.getCodMeet());
	    	}
        }
		
        catch (SQLException exception)
        {
            System.out.println("Errore SQLException: "+ exception.getMessage());
        } 	
	}
    
}

