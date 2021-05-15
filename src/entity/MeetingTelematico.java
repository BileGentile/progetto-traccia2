package entity;

public class MeetingTelematico {

    //ATTRIBUTI
	private String Nome;
	private String CodMeet;
	private String Data;
    private String OraInizio;
    private String Piattaforma;
 
    
    //COSTRUTTORI
    public MeetingTelematico(String nome, String codMeet, String data, String oraInizio, String piattaforma) {
		super();
		Nome = nome;
		CodMeet = codMeet;
		Data = data;
		OraInizio = oraInizio;
		Piattaforma = piattaforma;
	}
	
	public MeetingTelematico(String codMeet) {
		this.CodMeet = codMeet;
	}
	
	
	//GETTERS & SETTERS
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String nome) {
		Nome = nome;
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
	
	
	@Override
	public String toString() {
		return "MeetingTelematico [Nome=" + Nome + ", CodMeet=" + CodMeet + ", Data=" + Data + ", OraInizio=" + OraInizio + ", Piattaforma=" + Piattaforma +"]";
	}
	

}
