package entity;

public class ArchivioPartecipantiProgetto {

    //ATTRIBUTI

	private String CodFiscale;
	private String NomeProgetto;
	private String Ruolo;
 
    //COSTRUTTORI
    public ArchivioPartecipantiProgetto(String codFiscale, String nomeProgetto, String ruolo) {
		super();
		CodFiscale = codFiscale;
		NomeProgetto = nomeProgetto;
		Ruolo = ruolo;
    }
	
	
	public String getNomeProgetto() {
		return NomeProgetto;
	}

	public void setNomeProgetto(String nomeProgetto) {
		NomeProgetto = nomeProgetto;
	}
	
	public String getCodFiscale() {
		return CodFiscale;
	}
	public void setCodFiscalea(String codFiscale) {
		CodFiscale = codFiscale;
	}
	

	public String getRuolo() {
		return Ruolo;
	}
	public void setRuolo(String ruolo) {
		Ruolo = ruolo;
	}
	@Override
	public String toString() {
		return "Skills [CodFiscale=" + CodFiscale + ", NomeProgetto=" + NomeProgetto + ",Ruolo ="+ Ruolo +", ]";
	}
	

}

