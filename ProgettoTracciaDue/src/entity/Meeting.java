package entity;

public class Meeting {

    //ATTRIBUTI

	private String CodMeet;
	private String Data;
    private String OraInizio;
	private String Piattaforma;
    private String Tipologia;
    private String NomeSala;
    private String Organizzatore;
    private int Durata;
    
    //COSTRUTTORI
    public Meeting(String codMeet, String data, String oraInizio, String piattaforma, String tipologia, String nomeSala, String organizzatore, int durata) {
		super();
		CodMeet = codMeet;
		Data = data;
		OraInizio = oraInizio;
		Piattaforma = piattaforma;
		Tipologia = tipologia;
		NomeSala = nomeSala;
		Organizzatore= organizzatore;
		Durata = durata;
	}
	
	public Meeting(String CodMeet) {
		this.CodMeet = CodMeet;
	}
	

	public String getCodMeet() {
		return CodMeet;
	}
	public void setCodMeet(String codMeet) {
		CodMeet = codMeet;
	}

	public void setData(String data) {
		Data = data;
	}
	
	public String getData() {
		return Data;
	}
	public String getOraInizio() {
		return OraInizio;
	}
	public void setOraInizio(String oraInizio) {
		OraInizio = oraInizio;
	}
	public String getPiattaforma() {
		return Piattaforma;
	}
	public void setPiattaforma(String piattaforma) {
		Piattaforma = piattaforma;
	}
	public String getTipologia() {
		return Tipologia;
	}
	public void setTipologia(String tipologia) {
		Tipologia = tipologia ;

	}

	public String getNomeSala() {
		return NomeSala;
	}
	public void setNomeSala(String nomeSala) {
		NomeSala = nomeSala;
	}
	
	public int getDurata() {
		return Durata;
	}
	
	public void setDurata(int durata){
		Durata = durata;
	}
	
	public String getOrganizzatore() {
		return Organizzatore;
	}

	public void setOrganizzatore(String organizzatore) {
		Organizzatore = organizzatore;
	}

	@Override
	public String toString() {
		return "Meeting [CodMeet=" + CodMeet + ", Data=" + Data + ", OraInizio=" + OraInizio + ", Piattaforma="
				+ Piattaforma + ", Tipologia=" + Tipologia + ", NomeSala=" + NomeSala + ", Organizzatore="
				+ Organizzatore + ", Durata=" + Durata + "]";
	}

	


}

