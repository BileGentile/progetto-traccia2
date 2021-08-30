package entity;

import java.util.ArrayList;

public class Meeting {

    //ATTRIBUTI

	private String CodMeet;
	private String Data;
    private String OraInizio;
    private int Durata;
    private Progetto progettoMeeting;
    private ArrayList<Membro> partecipanti;
    
    //COSTRUTTORI
    
    public Meeting(String codMeet, String data, String oraInizio, int durata, Progetto progettoMeeting) {
		super();
		CodMeet = codMeet;
		Data = data;
		OraInizio = oraInizio;
		Durata = durata;
		this.progettoMeeting = progettoMeeting;
	}

	public String getCodMeet() {
		return CodMeet;
	}

	public void setCodMeet(String codMeet) {
		CodMeet = codMeet;
	}

	public String getData() {
		return Data;
	}

	public void setData(String data) {
		Data = data;
	}

	public String getOraInizio() {
		return OraInizio;
	}

	public void setOraInizio(String oraInizio) {
		OraInizio = oraInizio;
	}

	public int getDurata() {
		return Durata;
	}

	public void setDurata(int durata) {
		Durata = durata;
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

	@Override
	public String toString() {
		return "Meeting [CodMeet=" + CodMeet + ", Data=" + Data + ", OraInizio=" + OraInizio + ", Durata=" + Durata
				+ ", progettoMeeting=" + progettoMeeting + "]";
	}
    
    //METODI
    
}

