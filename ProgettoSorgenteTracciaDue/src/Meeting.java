import java.sql.Date;

public class Meeting {

	//	attributi
	private String NomeProgetto;
	private Date DataInzio;
	
	//	costruttori
	public String getNomeProgetto() {
		return NomeProgetto;
	}
	public void setNomeProgetto(String nomeProgetto) {
		NomeProgetto = nomeProgetto;
	}
	public Date getDataInzio() {
		return DataInzio;
	}
	public void setDataInzio(Date dataInzio) {
		DataInzio = dataInzio;
	}
	public Meeting(String nomeProgetto, Date dataInzio) {
		super();
		NomeProgetto = nomeProgetto;
		DataInzio = dataInzio;
	}
	
	// metodi
	
}
