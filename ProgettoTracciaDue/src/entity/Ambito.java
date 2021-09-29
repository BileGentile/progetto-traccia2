package entity;

import java.util.ArrayList;

public class Ambito {

	//ATTRIBUTI

	private String NomeAmbito;
	private String CodAmbito;
	private ArrayList<Progetto> ambitiProgetto;
	
	//COSTRUTTORI
	
	public Ambito(String nomeAmbito, String codAmbito) {
		super();
		NomeAmbito = nomeAmbito;
		CodAmbito = codAmbito;
	}

	public Ambito(String nomeAmbito) {
		super();
		NomeAmbito = nomeAmbito;
	}

	public String getNomeAmbito() {
		return NomeAmbito;
	}

	public void setNomeAmbito(String nomeAmbito) {
		NomeAmbito = nomeAmbito;
	}

	public String getCodAmbito() {
		return CodAmbito;
	}

	public void setCodAmbito(String codAmbito) {
		CodAmbito = codAmbito;
	}
	
	public ArrayList<Progetto> getAmbitiProgetto() {
		return ambitiProgetto;
	}

	public void setAmbitiProgetto(ArrayList<Progetto> ambitiProgetto) {
		this.ambitiProgetto = ambitiProgetto;
	}

	@Override
	public String toString() {
		return "Ambito [NomeAmbito=" + NomeAmbito + ", CodAmbito=" + CodAmbito + ", ambitiProgetto=" + ambitiProgetto
				+ "]";
	}
	
}
