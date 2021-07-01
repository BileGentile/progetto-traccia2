package entity;

public class Meeting {

    //ATTRIBUTI

	private String CodMeet;
	private String Data;
    private String OraInizio;
	private String Piattaforma;
    private String Tipologia;
    private String NomeSala;
    
    
    //COSTRUTTORI
    public Meeting(String codMeet, String data, String oraInizio, String piattaforma, String tipologia,String nomeSala) {
		super();
		CodMeet = codMeet;
		Data = data;
		OraInizio = oraInizio;
		Piattaforma = piattaforma;
		Tipologia = tipologia;
		NomeSala = nomeSala;
	}
	
	public Meeting (String codMeet) {
		this.CodMeet = codMeet;
	}
	public String getCodMeet() {
		return CodMeet;
	}
	public void setCodMeet(String codMeet) {
		CodMeet = codMeet;
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
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
	@Override
	public String toString() {
		return "MeetingFisico [CodMeet=" + CodMeet + ", Data=" + Data + ", OraInizio=" + OraInizio + ",Piattaforma=" + Piattaforma + ",  Tipologia="+ Tipologia+", NomeSala=" + NomeSala +",]";
	}
	

}

