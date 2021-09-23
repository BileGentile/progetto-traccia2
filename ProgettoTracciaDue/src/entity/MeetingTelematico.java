package entity;

import java.util.Date;

public class MeetingTelematico extends Meeting {
	
	//ATTRIBUTI
	private String Piattaforma;
	
	//COSTRUTTORI
	
	public MeetingTelematico(String codMeet, String titolo, Date data, String oraInizio, String oraFine,
			Progetto progettoMeeting, String organizzatore, String piattaforma) {
		super(codMeet, titolo, data, oraInizio, oraFine, progettoMeeting, organizzatore);
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

	@Override
	public String toString() {
		return "MeetingTelematico [Piattaforma=" + Piattaforma +  ", toString()=" + super.toString()
				+ "]";
	}

}
