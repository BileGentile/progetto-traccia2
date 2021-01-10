
public class Membro {
	
//	attributi
	private String Nome;
	private String Cognome;
	private String CF;
	private String ValutazioneAziendale;
//	skill da inserire
	
//	costruttori
	
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
	
	public Membro(String nome, String cognome, String cF) {
		super();
		Nome = nome;
		Cognome = cognome;
		CF = cF;
	}
	
//	metodi
	
}
