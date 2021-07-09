package entity;

public class MeetingFisico {

    //ATTRIBUTI
	private String Nome;
	private String CodMeet;
	private String Data;
    private String OraInizio;
    private String NomeSala;
    private String Organizzatore;
 
    //COSTRUTTORI
    public MeetingFisico(String nome, String codMeet, String data, String oraInizio,String nomeSala, String organizzatore) {
		super();
		Nome = nome;
		CodMeet = codMeet;
		Data = data;
		OraInizio = oraInizio;
		NomeSala = nomeSala;
		Organizzatore = organizzatore;
	}
	
	public String getNomeSala() {
		return NomeSala;
	}

	public void setNomeSala(String nomeSala) {
		NomeSala = nomeSala;
	}

	public String getOrganizzatore() {
		return Organizzatore;
	}

	public void setOrganizzatore(String organizzatore) {
		Organizzatore = organizzatore;
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
		return "MeetingFisico [Nome=" + Nome + ", CodMeet=" + CodMeet + ", Data=" + Data + ", OraInizio=" + OraInizio
				+ ", NomeSala=" + NomeSala + ", Organizzatore=" + Organizzatore + "]";
	}
	

}

