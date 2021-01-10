import java.util.ArrayList;

public class Progetto {
	
//	attributi
	private String NomeProgetto;
	private String TipoProgetto;
	private ArrayList<String> AmbitoProgetto;
	
	//	costruttori
	
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
	public ArrayList<String> getAmbitoProgetto() {
		return AmbitoProgetto;
	}
	public void setAmbitoProgetto(ArrayList<String> ambitoProgetto) {
		AmbitoProgetto = ambitoProgetto;
	}
	
	public Progetto(String nomeProgetto, String tipoProgetto, ArrayList<String> ambitoProgetto) {
		super();
		NomeProgetto = nomeProgetto;
		TipoProgetto = tipoProgetto;
		AmbitoProgetto = ambitoProgetto;
	}
	
//metodi
	
}
