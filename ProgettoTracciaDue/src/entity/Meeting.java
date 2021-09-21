package entity;

import java.util.ArrayList;
import java.util.Date;

public class Meeting {

    //ATTRIBUTI

	private String CodMeet;
	private String Titolo;
	private Date Data;
    private String OraInizio;
    private String OraFine;
    private Progetto progettoMeeting;
    private ProjectManager Organizzatore;
    private ArrayList<Membro> partecipanti;
    
    //COSTRUTTORI
    
   public Meeting(String codMeet, String titolo, Date data, String oraInizio, String oraFine,
			Progetto progettoMeeting) {
		super();
		CodMeet = codMeet;
		Titolo = titolo;
		Data = data;
		OraInizio = oraInizio;
		OraFine = oraFine;
		this.progettoMeeting = progettoMeeting;
	}
  
    public Meeting(String codMeet) {
    	super();
    	CodMeet = codMeet;
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
	
	public ArrayList<Membro> getPartecipanti() {
		return partecipanti;
	}

	public void setPartecipanti(ArrayList<Membro> partecipanti) {
		this.partecipanti = partecipanti;
	}
	
	public String getTitolo() {
		return Titolo;
	}

	public void setTitolo(String titolo) {
		Titolo = titolo;
	}

	public ProjectManager getOrganizzatore() {
		return Organizzatore;
	}

	public void setOrganizzatore(ProjectManager organizzatore) {
		Organizzatore = organizzatore;
	}

	@Override
	public String toString() {
		return "Meeting [CodMeet=" + CodMeet + ", Data=" + Data + ", OraInizio=" + OraInizio + ", Durata=" + OraFine
				+ ", progettoMeeting=" + progettoMeeting + "]";
	}

    
    //METODI
	
    
}

