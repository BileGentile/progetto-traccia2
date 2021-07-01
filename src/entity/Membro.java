package entity;

public class Membro {
	
    //ATTRIBUTI
	private String Nome;
	private String Cognome;
	private String CF;
    private String Ruolo;
    private int SalarioMedio;
    private String Valutazione;
		
    
    //COSTRUTTORI
	public Membro(String nome, String cognome, String cF, String ruolo, int salarioMedio, String valutazione) {
		super();
		Nome = nome;
		Cognome = cognome;
		CF = cF;
		Ruolo = ruolo;
		SalarioMedio = salarioMedio;
		Valutazione = valutazione;
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
	
	public String getRuolo() {
		return Ruolo;
	}

	public void setRuolo(String ruolo) {
		Ruolo = ruolo;
	}
	
	public int getSalarioMedio() {
		return SalarioMedio;
	}
	
	public void setSalarioMedio(int salario) {
		SalarioMedio = salario;
	}
	
	public String getValutazione() {
		return Valutazione;
	}

	public void setValutazione(String valutazione) {
		Valutazione = valutazione;
	}

	
	@Override
	public String toString() {
		return "Membro [Nome=" + Nome + ", Cognome=" + Cognome + ", CF=" + CF + ", Ruolo=" + Ruolo + ", SalarioMedio=" + SalarioMedio +", Valutazione=" + Valutazione +"]";
	}
	
}
