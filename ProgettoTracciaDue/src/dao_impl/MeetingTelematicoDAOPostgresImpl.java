package dao_impl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.MeetingTelematicoDAO;
import entity.Meeting;
import entity.MeetingFisico;
import entity.MeetingTelematico;
import entity.Progetto;
import entity.ProjectManager;
import entity.Sviluppatore;

public class MeetingTelematicoDAOPostgresImpl implements MeetingTelematicoDAO  {

	private Connection connection;

	private PreparedStatement getMeetingTelematicoByTitoloPS,cercaPartecipantiMeeting,getMeetingTelematicoProjectManager,InserisciPartecipazione,getMeetingTelematicoCodFiscale, inserisciMeetingPS;

	public MeetingTelematicoDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;

		getMeetingTelematicoByTitoloPS = connection.prepareStatement("SELECT * FROM MeetingTelematico WHERE titolo LIKE ?");
		
		inserisciMeetingPS = connection.prepareStatement("INSERT INTO MeetingTelematico VALUES (nextval(?), ?, TO_DATE(?, 'YYYY MM DD'), TO_TIMESTAMP(?, 'HH24:MI'), TO_TIMESTAMP(?, 'HH24:MI'), ?, ?,?)");
		
		getMeetingTelematicoCodFiscale=connection.prepareStatement("select codicemeeting, titolo, data, codprogetto\r\n"
				+ "from meetingtelematico\r\n"
				+ "where codprogetto in (SELECT codprogetto\r\n"
				+ "					  FROM partecipazioniprogetto\r\n"
				+ "					  where codfiscale LIKE ?)\r\n"
				+ "ANd codicemeeting not in\r\n"
				+ "					  (select codmeeting\r\n"
				+ "					   from partecipazionisviluppatoremeetingtelematico\r\n"
				+ "					   where codfiscale LIKE ?);");
		
		InserisciPartecipazione=connection.prepareStatement("insert into partecipazionisviluppatoremeetingtelematico\r\n"
				+ "values(?,?);");
		
		
		getMeetingTelematicoProjectManager=connection.prepareStatement("(SELECT CODICEMEETING, TITOLO,CODPROGETTO\r\n"
				+ "FROM MEETING"
				+ "WHERE CODICEMEETING IN \r\n"
				+ "(select CODMEETING\r\n"
				+ "FROM partecipazioniprojectmanagermeetingtelematico\r\n"
				+ "where codfiscale LIKE ?));\r\n"
				+ "");
		
		cercaPartecipantiMeeting=connection.prepareStatement("select *\r\n"
				+ "from sviluppatore\r\n"
				+ "where codfiscale in \r\n"
				+ "(select codfiscale\r\n"
				+ "from partecipazionisviluppatoremeetingtelematico\r\n"
				+ "where codmeeting like ?);");
	}


	@Override
	public MeetingTelematico getMeetingTelematicoByTitolo(String titolo) throws SQLException {
		getMeetingTelematicoByTitoloPS.setString(1, titolo);
        ResultSet rs= getMeetingTelematicoByTitoloPS.executeQuery();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet fks = metaData.getExportedKeys(connection.getCatalog(), null, "meetingtelematico");
        rs.next();
        	MeetingTelematico s = new MeetingTelematico(rs.getString("codicemeeting")); 
            s.setTitolo(rs.getString("titolo"));
            s.setData(rs.getDate("data"));
            s.setOraInizio(rs.getString("oraInizio"));
            s.setOraFine(rs.getString("oraFine"));
            s.setPiattaforma(rs.getString("piattaforma"));
          
        return s;
	}
	
	@Override
	public int inserisciMeetingTelematico(MeetingTelematico meetingTelematico) throws SQLException {
		Progetto p= meetingTelematico.getProgettoMeeting();
		inserisciMeetingPS.setString(1, meetingTelematico.getCodMeet());
		inserisciMeetingPS.setString(2, meetingTelematico.getTitolo());
		inserisciMeetingPS.setDate(3, new java.sql.Date(meetingTelematico.getData().getTime()));
		inserisciMeetingPS.setString(4, meetingTelematico.getOraInizio());
		inserisciMeetingPS.setString(5, meetingTelematico.getOraFine());
		inserisciMeetingPS.setString(6, meetingTelematico.getPiattaforma());
		inserisciMeetingPS.setString(7, p.getCodiceProgetto());
		inserisciMeetingPS.setString(8, meetingTelematico.getOrganizzatore());

        int row = inserisciMeetingPS.executeUpdate();
        return row;
	}
	
	@Override
	public List<MeetingTelematico> getMeetingTelematicoCodFiscale(String CF) throws SQLException {
		getMeetingTelematicoCodFiscale.setString(1, CF);
		getMeetingTelematicoCodFiscale.setString(2, CF);
		ResultSet rs= getMeetingTelematicoCodFiscale.executeQuery();
		List<MeetingTelematico> lista = new ArrayList<MeetingTelematico>();
		 while(rs.next())
	        {
				Progetto p= new Progetto (rs.getString("codprogetto"));
			 	MeetingTelematico s = new MeetingTelematico(rs.getString("codicemeeting")); 
			 	s.setTitolo(rs.getString("titolo"));
			 	s.setData(rs.getDate("data"));
	        	s.setProgettoMeeting(p);
	        	lista.add(s);
	        }
		return lista;
	}
	
	@Override
	public List<MeetingTelematico> getMeetingTelematicoProjectManager(String CF) throws SQLException{
	getMeetingTelematicoProjectManager.setString(1, CF);
	ResultSet rs= getMeetingTelematicoProjectManager.executeQuery();
	List<MeetingTelematico> lista = new ArrayList<MeetingTelematico>();
	 while(rs.next())
        {
			Progetto p= new Progetto (rs.getString("codprogetto"));
		 	MeetingTelematico s = new MeetingTelematico(rs.getString("codicemeeting")); 
		 	s.setTitolo(rs.getString("titolo"));
        	s.setProgettoMeeting(p);
        	lista.add(s);
        }
	return lista;
	}
	
	@Override
	public int InserisciPartecipazione(String cF, String codMeet)throws SQLException{
		InserisciPartecipazione.setString(1,cF);
		InserisciPartecipazione.setString(2, codMeet);
		int row = InserisciPartecipazione.executeUpdate();
    	return row;
	}
	
	
	
	public List<Sviluppatore> cercaPartecipantiMeeting(String codMeet)throws SQLException{
		cercaPartecipantiMeeting.setString(1, codMeet);
		ResultSet rs= cercaPartecipantiMeeting.executeQuery();
		List<Sviluppatore> lista = new ArrayList<Sviluppatore>();
		 while(rs.next())
	        {
			 	Sviluppatore p= new Sviluppatore (rs.getString("codfiscale"));
			 	p.setCognome(rs.getString("cognome"));
	        	p.setNome(rs.getString("nome"));
	        	lista.add(p);
	        }
		return lista;
	}

}
