package entity;

public class MeetingFisico {

    //ATTRIBUTI
	private String Nome;
	private String CodMeet;
	private String Data;
    private String OraInizio;
    private String NomeSala;
 
    //COSTRUTTORI
    public MeetingFisico(String nome, String codMeet, String data, String oraInizio, String nomeSala) {
		super();
		Nome = nome;
		CodMeet = codMeet;
		Data = data;
		OraInizio = oraInizio;
		NomeSala = nomeSala;
	}
	
	public MeetingFisico(String codMeet) {
		this.CodMeet = codMeet;
	}
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
		return NomeSala;
	}
	public void setPiattaforma(String nomeSala) {
		NomeSala = nomeSala;
	}
	
	@Override
	public String toString() {
		return "MeetingFisico [Nome=" + Nome + ", CodMeet=" + CodMeet + ", Data=" + Data + ", OraInizio=" + OraInizio + ", NomeSala=" + NomeSala +"]";
	}
	

}

