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
import entity.MeetingFisico;
import entity.MeetingTelematico;
import entity.Progetto;

public class MeetingTelematicoDAOPostgresImpl implements MeetingTelematicoDAO  {

	private Connection connection;

	private PreparedStatement getMeetingTelematicoByTitoloPS, getAllMeetingTelematicoPS, inserisciMeetingPS, getMeetingTelematicoByCodMeetPS, cancellaMeetingTelematicoByTitoloPS;

	public MeetingTelematicoDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;

		getMeetingTelematicoByTitoloPS = connection.prepareStatement("SELECT * FROM MeetingTelematico WHERE titolo LIKE ?");
		inserisciMeetingPS = connection.prepareStatement("INSERT INTO MeetingTelematico VALUES (nextval(?), ?, TO_DATE(?, 'YYYY MM DD'), TO_TIMESTAMP(?, 'HH24:MI'), TO_TIMESTAMP(?, 'HH24:MI'), ?, ?)");
		getMeetingTelematicoByCodMeetPS = connection.prepareStatement("SELECT * FROM MeetingTelematico WHERE codMeet LIKE ? ");
		getAllMeetingTelematicoPS = connection.prepareStatement("SELECT * FROM MeetingTelematico");
		cancellaMeetingTelematicoByTitoloPS = connection.prepareStatement("DELETE FROM MeetingTelematico WHERE titolo LIKE ?");

		}


	@Override
	public List<MeetingTelematico> getMeetingTelematicoByTitolo(String titolo) throws SQLException {
		getMeetingTelematicoByTitoloPS.setString(1, titolo);
        ResultSet rs= getMeetingTelematicoByTitoloPS.executeQuery();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet fks = metaData.getExportedKeys(connection.getCatalog(), null, "meetingtelematico");
        List<MeetingTelematico> lista = new ArrayList<MeetingTelematico>();
        while(rs.next())
        {
        	MeetingTelematico s = new MeetingTelematico(rs.getString("codMeet")); 
            s.setTitolo(rs.getString("titolo"));
            s.setData(rs.getDate("data"));
            s.setOraInizio(rs.getString("oraInizio"));
            s.setOraFine(rs.getString("oraFine"));
            s.setPiattaforma(rs.getString("piattaforma"));
            s.setProgettoMeeting(fks.getObject("codProgetto", Progetto.class));
            //s.setOrganizzatore(rs.getString("Organizzatore")); DA FARE???
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	
	@Override
	public List<MeetingTelematico> getAllMeetingTelematico() throws SQLException {
        ResultSet rs= getAllMeetingTelematicoPS.executeQuery();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet fks = metaData.getExportedKeys(connection.getCatalog(), null, "meetingtelematico");
        List<MeetingTelematico> lista = new ArrayList<MeetingTelematico>();
        while(rs.next())
        {
        	MeetingTelematico s = new MeetingTelematico(rs.getString("codMeet"));
            s.setTitolo(rs.getString("titolo"));
            s.setData(rs.getDate("data"));
            s.setOraInizio(rs.getString("oraInizio"));
            s.setOraFine(rs.getString("oraFine"));
            s.setPiattaforma(rs.getString("piattaforma"));
            s.setProgettoMeeting(fks.getObject("codProgetto", Progetto.class));
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	
	@Override
	public int inserisciMeetingTelematico(MeetingTelematico meetingTelematico) throws SQLException {
		Progetto p= meetingTelematico.getProgettoMeeting();
	    //DA RIVEDERE
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
	public List<MeetingTelematico> getMeetingTelematicoByCodMeet(String codMeet) throws SQLException {
		getMeetingTelematicoByCodMeetPS.setString(2, codMeet);
        ResultSet rs= getMeetingTelematicoByCodMeetPS.executeQuery();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet fks = metaData.getExportedKeys(connection.getCatalog(), null, "meetingtelematico");
        List<MeetingTelematico> lista = new ArrayList<MeetingTelematico>();
        while(rs.next())
        {
        	MeetingTelematico s = new MeetingTelematico(rs.getString("codMeet")); //rs.getString(1)
            s.setTitolo(rs.getString("titolo"));
            s.setData(rs.getDate("data"));
            s.setOraInizio(rs.getString("oraInizio"));
            s.setOraFine(rs.getString("oraFine"));
            s.setPiattaforma(rs.getString("piattaforma"));
            s.setProgettoMeeting(fks.getObject("codProgetto", Progetto.class));
            //s.setOrganizzatore(rs.getString("Organizzatore")); DA FARE?
            lista.add(s);
        }
        rs.close();
        return lista;
	}
}
