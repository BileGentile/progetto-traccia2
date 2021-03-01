package entity;

public class Membro {
	
    //ATTRIBUTI
	private String Nome;
	private String Cognome;
	private String CF;
	private String ValutazioneAziendale;
    
	//SKILLS
	//TODO
	
    //COSTRUTTORI
	public Membro(String nome, String cognome, String cF, String valutazioneAziendale) {
		super();
		Nome = nome;
		Cognome = cognome;
		CF = cF;
		ValutazioneAziendale = valutazioneAziendale;
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
