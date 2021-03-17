package entity;

import java.util.ArrayList;

public class Membro {
	
    //ATTRIBUTI
	private String Nome;
	private String Cognome;
	private String CF;
	private String ValutazioneAziendale;
	
	//ASSOCIAZIONI
	private ArrayList<Progetto> Partecipazioni;
    
	//SKILLS
	//TODO
	
    //COSTRUTTORI
	public Membro(String Nome, String Cognome, String CF, String ValutazioneAziendale) {
		super();
		this.Nome = Nome;
		this.Cognome = Cognome;
		this.CF = CF;
		this.ValutazioneAziendale = ValutazioneAziendale;
	}
	
	public Membro(String CF) {
		this.CF = CF;
	}
	
	//GETTERS & SETTERS
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String nome) {
		Nome = nome;
	}
	
	public String getCognome() {
		return Cognome;
	}
	
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	
	public String getCF() {
		return CF;
	}
	
	public void setCF(String cF) {
		CF = cF;
	}
	
	public String getValutazioneAziendale() {
		return ValutazioneAziendale;
	}
	
	public void setValutazioneAziendale(String valutazioneAziendale) {
		ValutazioneAziendale = valutazioneAziendale;
	}

	@Override
	public String toString() {
		return "Membro [Nome=" + Nome + ", Cognome=" + Cognome + ", CF=" + CF + ", ValutazioneAziendale="
				+ ValutazioneAziendale + "]";
	}
	
    //METODI
	
}
