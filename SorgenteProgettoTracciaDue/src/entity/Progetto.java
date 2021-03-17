package entity;

import java.util.ArrayList;

public class Progetto {
	
    //ATTRIBUTI
	private String NomeProgetto;
	private String TipoProgetto;
	private ArrayList<String> AmbitoProgetto;
	
	//ASSOCIAZIONI
	private projectManager Manager;
	private ArrayList<Membro> Partecipanti;
	
	//COSTRUTTORI
	public Progetto(String nomeProgetto, String tipoProgetto, ArrayList<String> ambitoProgetto) {
		super();
		NomeProgetto = nomeProgetto;
		TipoProgetto = tipoProgetto;
		AmbitoProgetto = ambitoProgetto;
	}
	
	//GETTERS & SETTERS
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
	
	public projectManager getManager() {
		return Manager;
	}
	public void setManager(projectManager manager) {
		Manager = manager;
	}

	public ArrayList<Membro> getPartecipanti() {
		return Partecipanti;
	}

	public void setPartecipanti(ArrayList<Membro> partecipanti) {
		Partecipanti = partecipanti;
	}
	
	//METODI
	
}
