package entity;

import java.util.Date;

public class MeetingTelematico extends Meeting {
	
	//ATTRIBUTI
	private String Piattaforma;
	private String link;
	
	//COSTRUTTORI
	
	public MeetingTelematico(String codMeet, String titolo, Date data, String oraInizio, String oraFine,
			 Progetto progettoMeeting, String piattaforma) {
		super(codMeet, titolo, data, oraInizio, oraFine, progettoMeeting);
		Piattaforma = piattaforma;
	}
	
	public MeetingTelematico(String codMeet) {
		super(codMeet);
	}
	
	//GETTERS AND SETTERS

	public String getPiattaforma() {
		return Piattaforma;
	}

	public void setPiattaforma(String piattaforma) {
		Piattaforma = piattaforma;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "MeetingTelematico [Piattaforma=" + Piattaforma + ", link=" + link + ", toString()=" + super.toString()
				+ "]";
	}

	//METODI

}
