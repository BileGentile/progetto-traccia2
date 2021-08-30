package entity;

public class MeetingTelematico extends Meeting {
	
	//ATTRIBUTI
	private String Piattaforma;
	private String link;
	
	//COSTRUTTORI
	public MeetingTelematico(String codMeet, String data, String oraInizio, int durata, Progetto progettoMeeting,
			String piattaforma, String link) {
		super(codMeet, data, oraInizio, durata, progettoMeeting);
		Piattaforma = piattaforma;
		this.link = link;
	}
	
	//COSTRUTTORI
		public MeetingTelematico(String codMeet, String data, String oraInizio, int durata, Progetto progettoMeeting,
				String piattaforma) {
			super(codMeet, data, oraInizio, durata, progettoMeeting);
			Piattaforma = piattaforma;
		}

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
