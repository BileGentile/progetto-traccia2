package entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import dao_impl.ProgettoDAOPostgresImpl;
import daos.ProgettoDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import exceptions.ConnectionException;

public class Progetto {
	
    //	attributi
	
	private String NomeProgetto;
	private String CodiceProgetto;
	private String TipoProgetto;
	private String Stato;
	private ArrayList<Sviluppatore> membriProgetto;
	private ProjectManager projectManagerProgetto;
	private ArrayList<Ambito> ambitiProgetto;
	private ArrayList<Meeting> meetingProgetto;
	
	//	costruttori
	
	public Progetto(String nomeProgetto, String tipoProgetto, String codiceProgetto,  String stato,
		 ProjectManager projectManagerProgetto) {
		super();
		NomeProgetto = nomeProgetto;
		CodiceProgetto = codiceProgetto;
		TipoProgetto = tipoProgetto;
		Stato = stato;
		this.projectManagerProgetto = projectManagerProgetto;
	}

	public Progetto(String CodiceProgetto) {
		this.CodiceProgetto = CodiceProgetto;
	}
	
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
	
	public String getStato() {
		return Stato;
	}
	
	public void setStato(String stato) {
		Stato = stato;
	}
	
	public String getCodiceProgetto() {
		return CodiceProgetto;
	}
	
	public void setCodiceProgetto(String codiceProgetto) {
		CodiceProgetto = codiceProgetto;
	}

	public ArrayList<Sviluppatore> getMembriProgetto() {
		return membriProgetto;
	}

	public void setMembriProgetto(ArrayList<Sviluppatore> membriProgetto) {
		this.membriProgetto = membriProgetto;
	}

	public ProjectManager getProjectManagerProgetto() {
		return projectManagerProgetto;
	}

	public void setProjectManagerProgetto(ProjectManager projectManagerProgetto) {
		this.projectManagerProgetto = projectManagerProgetto;
	}

	public ArrayList<Ambito> getAmbitiProgetto() {
		return ambitiProgetto;
	}

	public void setAmbitiProgetto(ArrayList<Ambito> ambitiProgetto) {
		this.ambitiProgetto = ambitiProgetto;
	}

	public ArrayList<Meeting> getMeetingProgetto() {
		return meetingProgetto;
	}

	public void setMeetingProgetto(ArrayList<Meeting> meetingProgetto) {
		this.meetingProgetto = meetingProgetto;
	}

	@Override
	public String toString() {
		return "Progetto [NomeProgetto=" + NomeProgetto + ", CodiceProgetto=" + CodiceProgetto + ", TipoProgetto="
				+ TipoProgetto + ", Stato=" + Stato + ", membriProgetto=" + membriProgetto + ", projectManagerProgetto="
				+ projectManagerProgetto + ", ambitiProgetto=" + ambitiProgetto + ", meetingProgetto=" + meetingProgetto
				+ "]";
	}
	
	//metodi
	
	public int aggiungiPartecipante(String codiceFiscale, String codProgetto) {
		DBConnection dbconn = null;
	    Connection connection = null;
	    DBBuilder builder = null;
	    int ris = -1;
	    try
	    {
	    	dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            builder.createTablePartecipazioniProgetto();
            ProgettoDAO dao =  null;

            dao= new ProgettoDAOPostgresImpl(connection);
            int res= dao.inserisciArchivioPartecipantiProgettoPS(codiceFiscale, codProgetto);
            ris= dao.inserimentoAvvenutoConSuccesso(codiceFiscale, codProgetto); 
        }
	    catch (SQLException exception)
    	{
	    	System.out.println("Errore SQLException: "+ exception.getMessage());
    	}
	    catch (ConnectionException ex)
    	{
	    	System.out.println("CE: "+ex);
    	}
	    return ris;
	}
}
