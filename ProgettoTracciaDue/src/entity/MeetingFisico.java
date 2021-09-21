package entity;

import java.util.Date;

public class MeetingFisico extends Meeting {
	
	//ATTRIBUTI
	private String luogo;
	private String nomeSala;
	
	//COSTRUTTORI
	
	public MeetingFisico(String codMeet, String titolo, Date data, String oraInizio, String oraFine,
			Progetto progettoMeeting, String luogo, String nomeSala) {
		super(codMeet, titolo, data, oraInizio, oraFine,progettoMeeting);
		this.luogo = luogo;
		this.nomeSala = nomeSala;
	}
	
	public MeetingFisico(String codMeet) {
		super(codMeet);
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
