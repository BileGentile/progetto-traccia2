package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import daos.MeetingFisicoDAO;
import entity.Meeting;
import entity.MeetingFisico;
import entity.Progetto;
import entity.Sviluppatore;

public class MeetingFisicoDAOPostgresImpl implements MeetingFisicoDAO  {
	
	private Connection connection;
	private PreparedStatement getMeetingFisicoByTitoloPS,cercaPartecipantiMeeting,getMeetingFisicoProjectManager,InserisciPartecipazione,getMeetingFisicoCodFiscale, inserisciMeetingFisicoPS;
	

	public MeetingFisicoDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;

		getMeetingFisicoByTitoloPS = connection.prepareStatement("SELECT * FROM MeetingFisico WHERE titolo LIKE ?");
		
		inserisciMeetingFisicoPS = connection.prepareStatement("INSERT INTO MeetingFisico VALUES (nextval(?), ?, TO_DATE(?, 'YYYY MM DD'), TO_TIMESTAMP(?, 'HH24:MI'), TO_TIMESTAMP(?, 'HH24:MI'), ?, ?,?,?)");
		
		getMeetingFisicoCodFiscale=connection.prepareStatement("select codicemeeting, titolo, data, codprogetto\r\n"
				+ "from meetingfisico\r\n"
				+ "where codprogetto in (SELECT codprogetto\r\n"
				+ "					  FROM partecipazioniprogetto\r\n"
				+ "					  where codfiscale LIKE ?)\r\n"
				+ "ANd codicemeeting not in\r\n"
				+ "					  (select codmeeting\r\n"
				+ "					   from partecipazionisviluppatoremeetingfisico\r\n"
				+ "					   where codfiscale LIKE ?);");
		
		InserisciPartecipazione=connection.prepareStatement("insert into partecipazionisviluppatoremeetingfisico\r\n"
				+ "values(?,?);");
		
		
		
		getMeetingFisicoProjectManager=connection.prepareStatement("(SELECT CODICEMEETING, TITOLO,CODPROGETTO\r\n"
				+ "FROM MEETINGFISICO\r\n"
				+ "WHERE CODICEMEETING IN \r\n"
				+ "(select CODMEETING\r\n"
				+ "from partecipazioniprojectmanagermeetingfisico\r\n"
				+ "where codfiscale LIKE ?));");
		
		cercaPartecipantiMeeting=connection.prepareStatement("select *\r\n"
				+ "from sviluppatore\r\n"
				+ "where codfiscale in \r\n"
				+ "(select codfiscale\r\n"
				+ "from partecipazionisviluppatoremeetingfisico\r\n"
				+ "where codmeeting like ?);");
	
	}
	


	@Override
	public MeetingFisico getMeetingFisicoByTitolo(String titolo) throws SQLException {
		getMeetingFisicoByTitoloPS.setString(1, titolo);
        ResultSet rs= getMeetingFisicoByTitoloPS.executeQuery();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet fks = metaData.getExportedKeys(connection.getCatalog(), null, "meetingfisico");
        rs.next();
        	MeetingFisico s = new MeetingFisico(rs.getString("codicemeeting"));//rs.getString(1)
        	s.setData(rs.getDate("data"));
            s.setOraInizio(rs.getString("oraInizio"));
            s.setOraFine(rs.getString("oraFine"));
            s.setLuogo(rs.getString("luogo"));
            s.setNomeSala(rs.getString("nomeSala"));
         
        return s;
	}
	
	@Override
	public int inserisciMeetingFisico(MeetingFisico meetingFisico) throws SQLException {
		Progetto p= meetingFisico.getProgettoMeeting();
		inserisciMeetingFisicoPS.setString(1, meetingFisico.getCodMeet());
		inserisciMeetingFisicoPS.setString(2, meetingFisico.getTitolo());
		inserisciMeetingFisicoPS.setDate(3, new java.sql.Date(meetingFisico.getData().getTime()));
		inserisciMeetingFisicoPS.setString(4, meetingFisico.getOraInizio());
		inserisciMeetingFisicoPS.setString(5, meetingFisico.getOraFine());
		inserisciMeetingFisicoPS.setString(6, meetingFisico.getLuogo());
		inserisciMeetingFisicoPS.setString(7, meetingFisico.getNomeSala());
		inserisciMeetingFisicoPS.setString(8, p.getCodiceProgetto());
		inserisciMeetingFisicoPS.setString(9, meetingFisico.getOrganizzatore());

        int row = inserisciMeetingFisicoPS.executeUpdate();
        return row;
	}

	@Override
	public List<MeetingFisico> getMeetingFisicoCodFiscale(String CF) throws SQLException {
		getMeetingFisicoCodFiscale.setString(1, CF);
		getMeetingFisicoCodFiscale.setString(2, CF);
		ResultSet rs= getMeetingFisicoCodFiscale.executeQuery();
		List<MeetingFisico> lista = new ArrayList<MeetingFisico>();
		 while(rs.next())
	        {
				Progetto p= new Progetto (rs.getString("codprogetto"));
				MeetingFisico s = new MeetingFisico(rs.getString("codicemeeting")); 
			 	s.setTitolo(rs.getString("titolo"));
			 	s.setData(rs.getDate("data"));
	        	s.setProgettoMeeting(p);
	        	lista.add(s);
	        }
		return lista;
	}
	
	@Override
	public List<MeetingFisico> getMeetingFisicoProjectManager(String CF) throws SQLException{
		getMeetingFisicoProjectManager.setString(1, CF);
		ResultSet rs= getMeetingFisicoProjectManager.executeQuery();
		List<MeetingFisico> lista = new ArrayList<MeetingFisico>();
		 while(rs.next())
	        {
				Progetto p= new Progetto (rs.getString("codprogetto"));
				MeetingFisico s = new MeetingFisico(rs.getString("codicemeeting")); 
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
