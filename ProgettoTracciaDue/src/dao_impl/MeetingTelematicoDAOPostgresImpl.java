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
import entity.Sviluppatore;

public class MeetingTelematicoDAOPostgresImpl implements MeetingTelematicoDAO  {

	private Connection connection;

	private PreparedStatement getMeetingTelematicoByTitoloPS,cercaPartecipantiMeeting,getMeetingTelematicoProjectManager,getInserisciPartecipazionePM,getInserisciPartecipazione,getMeetingTelematicoCodFiscale, getAllMeetingTelematicoPS, inserisciMeetingPS, getMeetingTelematicoByCodMeetPS, cancellaMeetingTelematicoByTitoloPS;

	public MeetingTelematicoDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;

		getMeetingTelematicoByTitoloPS = connection.prepareStatement("SELECT * FROM MeetingTelematico WHERE titolo LIKE ?");
		inserisciMeetingPS = connection.prepareStatement("INSERT INTO MeetingTelematico VALUES (nextval(?), ?, TO_DATE(?, 'YYYY MM DD'), TO_TIMESTAMP(?, 'HH24:MI'), TO_TIMESTAMP(?, 'HH24:MI'), ?, ?)");
		getMeetingTelematicoByCodMeetPS = connection.prepareStatement("SELECT * FROM MeetingTelematico WHERE codMeet LIKE ? ");
		getAllMeetingTelematicoPS = connection.prepareStatement("SELECT * FROM MeetingTelematico");
		cancellaMeetingTelematicoByTitoloPS = connection.prepareStatement("DELETE FROM MeetingTelematico WHERE titolo LIKE ?");
		getMeetingTelematicoCodFiscale=connection.prepareStatement("select codicemeeting, titolo,codprogetto\r\n"
				+ "from meetingtelematico\r\n"
				+ "where codprogetto in (SELECT codprogetto\r\n"
				+ "					  FROM partecipazioniprogetto\r\n"
				+ "					  where codfiscale LIKE ?)\r\n"
				+ "ANd codicemeeting not in\r\n"
				+ "					  (select codmeeting\r\n"
				+ "					   from partecipazionisviluppatoremeetingtelematico\r\n"
				+ "					   where codfiscale LIKE ?);");
		getInserisciPartecipazione=connection.prepareStatement("insert into partecipazionisviluppatoremeetingtelematico\r\n"
				+ "values(?,?);");
		getInserisciPartecipazionePM=connection.prepareStatement("insert into partecipazioniprojectmanagermeetingtelematico\r\n"
				+ "values(?,?);");
		getMeetingTelematicoProjectManager=connection.prepareStatement("(SELECT CODICEMEETING, TITOLO,CODPROGETTO\r\n"
				+ "FROM MEETINGTELEMATICO\r\n"
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
	public List<MeetingTelematico> getAllMeetingTelematico() throws SQLException {
        ResultSet rs= getAllMeetingTelematicoPS.executeQuery();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet fks = metaData.getExportedKeys(connection.getCatalog(), null, "meetingtelematico");
        List<MeetingTelematico> lista = new ArrayList<MeetingTelematico>();
        while(rs.next())
        {
        	MeetingTelematico s = new MeetingTelematico(rs.getString("codicemeeting"));
            s.setTitolo(rs.getString("titolo"));
            s.setData(rs.getDate("data"));
            s.setOraInizio(rs.getString("oraInizio"));
            s.setOraFine(rs.getString("oraFine"));
            s.setPiattaforma(rs.getString("piattaforma"));
            s.setProgettoMeeting(fks.getObject("codprogetto", Progetto.class));
            lista.add(s);
        }
        rs.close();
        return lista;
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
        int row = inserisciMeetingPS.executeUpdate();
        return row;
	}
	
	@Override
	public int cancellaMeetingTelematicoByTitolo(MeetingTelematico meetingTelematico) throws SQLException {
		cancellaMeetingTelematicoByTitoloPS.setString(1, meetingTelematico.getTitolo());
		int row = cancellaMeetingTelematicoByTitoloPS.executeUpdate();
		return row;
	}

	@Override
	public MeetingTelematico getMeetingTelematicoByCodMeet(String codMeet) throws SQLException {
		getMeetingTelematicoByCodMeetPS.setString(2, codMeet);
        ResultSet rs= getMeetingTelematicoByCodMeetPS.executeQuery();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet fks = metaData.getExportedKeys(connection.getCatalog(), null, "meetingtelematico");
        rs.next();
        	MeetingTelematico s = new MeetingTelematico(rs.getString("codicemeeting")); //rs.getString(1)
            s.setTitolo(rs.getString("titolo"));
            s.setData(rs.getDate("data"));
            s.setOraInizio(rs.getString("oraInizio"));
            s.setOraFine(rs.getString("oraFine"));
            s.setPiattaforma(rs.getString("piattaforma"));
            s.setProgettoMeeting(fks.getObject("codprogetto", Progetto.class));
            //s.setOrganizzatore(rs.getString("Organizzatore")); DA FARE?
       
        return s;
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
	public int getInserisciPartecipazione(String cF, String codMeet)throws SQLException{
		getInserisciPartecipazione.setString(1,cF);
		getInserisciPartecipazione.setString(2, codMeet);
		int row = getInserisciPartecipazione.executeUpdate();
    	return row;
	}
	
	@Override
	public int getInserisciPartecipazionePM(String cF, String codMeet)throws SQLException{
		getInserisciPartecipazionePM.setString(1,cF);
		getInserisciPartecipazionePM.setString(2, codMeet);
		int row = getInserisciPartecipazionePM.executeUpdate();
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
