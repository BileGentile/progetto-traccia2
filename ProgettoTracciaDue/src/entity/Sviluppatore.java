package entity;

public class Sviluppatore extends Membro {
	
	private String Valutazione;

	public Sviluppatore(String CF) {
		super(CF);
		// TODO Auto-generated constructor stub
	}

	public Sviluppatore(String nome, String cognome, String cF, String ruolo, int salarioMedio, String valutazione) {
		super(nome, cognome, cF, ruolo, salarioMedio);
		Valutazione = valutazione;
	}

	public String getValutazione() {
		return Valutazione;
	}

	public void setValutazione(String valutazione) {
		Valutazione = valutazione;
	}

	

}
