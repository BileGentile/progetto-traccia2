package entity;

import java.util.ArrayList;

public class Progetto {
	
    //	attributi
	private String NomeProgetto;
	private String CodiceProgetto;
	private String AmbitoProgetto;
	private String TipoProgetto;
	private String Stato;
	//	costruttori
	

	public Progetto(String nomeProgetto, String tipoProgetto, String ambitoProgetto, String stato, String codiceProgetto) {
		super();
		NomeProgetto = nomeProgetto;
		TipoProgetto = tipoProgetto;
		AmbitoProgetto = ambitoProgetto;
		Stato = stato;
		CodiceProgetto = codiceProgetto;
		}
	public Progetto(String CodiceProgetto) {
		this.CodiceProgetto = CodiceProgetto;
	}
	
	
	public String getNomeProgetto() {
		return NomeProgetto;
	}
	public void setNomeProgetto(String nomeProgetto) {
		NomeProgetto = nomeProgetto;
	}
	public String getTipoProgetto() {
		return TipoProgetto;
	}
	public void setTipoProgetto(String tipoProgetto) {
		TipoProgetto = tipoProgetto;
	}
	public String getAmbitoProgetto() {
		return AmbitoProgetto;
	}
	public void setAmbitoProgetto(String ambitoProgetto) {
		AmbitoProgetto = ambitoProgetto;
	}
	public String getStato() {
		return Stato;
	}
	public void setStato(String stato) {
		Stato = stato;
	}
	public String getCodiceProgetto() {
		return CodiceProgetto;
	}
	public void setCodiceProgetto(String codiceProgetto) {
		CodiceProgetto = codiceProgetto;
	}
	
//metodi
	
}
