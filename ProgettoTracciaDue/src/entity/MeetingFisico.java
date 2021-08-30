package entity;

public class MeetingFisico extends Meeting {
	
	//ATTRIBUTI
	private String luogo;
	private String nomeSala;
	
	//COSTRUTTORI
	public MeetingFisico(String codMeet, String data, String oraInizio, int durata, Progetto progettoMeeting,
			String luogo, String nomeSala) {
		super(codMeet, data, oraInizio, durata, progettoMeeting);
		this.luogo = luogo;
		this.nomeSala = nomeSala;
	}

	public MeetingFisico(String codMeet, String data, String oraInizio, int durata, Progetto progettoMeeting,
		 String nomeSala) {
		super(codMeet, data, oraInizio, durata, progettoMeeting);
		this.nomeSala = nomeSala;
	}
	public String getLuogo() {
		return luogo;
	}

	public void setLuogo(String luogo) {
		this.luogo = luogo;
	}

	public String getNomeSala() {
		return nomeSala;
	}

	public void setNomeSala(String nomeSala) {
		this.nomeSala = nomeSala;
	}

	@Override
	public String toString() {
		return "MeetingFisico [luogo=" + luogo + ", nomeSala=" + nomeSala + ", toString()=" + super.toString() + "]";
	}
	
	//METODI
}
