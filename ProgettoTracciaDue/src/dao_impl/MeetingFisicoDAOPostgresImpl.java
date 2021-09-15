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
import entity.MeetingFisico;
import entity.Progetto;

public class MeetingFisicoDAOPostgresImpl implements MeetingFisicoDAO  {
	
	private Connection connection;
	private PreparedStatement getMeetingFisicoByTitoloPS, inserisciMeetingFisicoPS, getMeetingFisicoByCodMeetPS, cancellaMeetingFisicoByTitoloPS, getAllMeetingFisicoPS;
	

	public MeetingFisicoDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;

		getMeetingFisicoByTitoloPS = connection.prepareStatement("SELECT * FROM MeetingFisico WHERE titolo LIKE ?");
		inserisciMeetingFisicoPS = connection.prepareStatement("INSERT INTO MeetingFisico VALUES (nextval(?), ?, TO_DATE(?, 'YYYY MM DD'), TO_TIMESTAMP(?, 'HH24:MI'), TO_TIMESTAMP(?, 'HH24:MI'), ?, ?, ?)");
		getMeetingFisicoByCodMeetPS = connection.prepareStatement("SELECT * FROM MeetingFisico WHERE codMeet LIKE ?  ");
		cancellaMeetingFisicoByTitoloPS = connection.prepareStatement("DELETE FROM MeetingFisico WHERE titolo LIKE ?");
		getAllMeetingFisicoPS = connection.prepareStatement("SELECT * FROM MeetingFisico");

		}


	@Override
	public List<MeetingFisico> getMeetingFisicoByTitolo(String titolo) throws SQLException {
		getMeetingFisicoByTitoloPS.setString(2, titolo);
        ResultSet rs= getMeetingFisicoByTitoloPS.executeQuery();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet fks = metaData.getExportedKeys(connection.getCatalog(), null, "meetingfisico");
        List<MeetingFisico> lista = new ArrayList<MeetingFisico>();
        while(rs.next())
        {
        	MeetingFisico s = new MeetingFisico(rs.getString("codMeet"));//rs.getString(1)
        	s.setData(rs.getDate("data"));
            s.setOraInizio(rs.getString("oraInizio"));
            s.setOraFine(rs.getString("oraFine"));
            s.setLuogo(rs.getString("luogo"));
            s.setNomeSala(rs.getString("nomeSala"));
            s.setProgettoMeeting(fks.getObject("codProgetto", Progetto.class));
            //s.setOrganizzatore(); DA FARE?
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	
	@Override
	public List<MeetingFisico> getMeetingFisicoByCodMeet(String codMeet) throws SQLException {
		getMeetingFisicoByCodMeetPS.setString(2, codMeet);
        ResultSet rs= getMeetingFisicoByCodMeetPS.executeQuery();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet fks = metaData.getExportedKeys(connection.getCatalog(), null, "meetingfisico");
        List<MeetingFisico> lista = new ArrayList<MeetingFisico>();
        while(rs.next())
        {
        	MeetingFisico s = new MeetingFisico(rs.getString("codMeet")); //rs.getString(1)
            s.setTitolo(rs.getString("titolo"));
            s.setData(rs.getDate("data"));
            s.setOraInizio(rs.getString("oraInizio"));
            s.setOraFine(rs.getString("oraFine"));
            s.setLuogo(rs.getString("luogo"));
            s.setNomeSala(rs.getString("nomeSala"));
            s.setProgettoMeeting(fks.getObject("codProgetto", Progetto.class));
            //s.setOrganizzatore(rs.getString("Organizzatore")); DA FARE?
            lista.add(s);
        }
        rs.close();
        return lista;
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
        int row = inserisciMeetingFisicoPS.executeUpdate();
        return row;
	}
	
	@Override
	public int cancellaMeetingFisicoByTitolo(MeetingFisico meetingFisico) throws SQLException {
		cancellaMeetingFisicoByTitoloPS.setString(1, meetingFisico.getTitolo());
		int row = cancellaMeetingFisicoByTitoloPS.executeUpdate();
		return row;
	}

	@Override
	public List<MeetingFisico> getAllMeetingFisico() throws SQLException {
		ResultSet rs = getAllMeetingFisicoPS.executeQuery();
		DatabaseMetaData metaData = connection.getMetaData();
        ResultSet fks = metaData.getExportedKeys(connection.getCatalog(), null, "meetingfisico");
		List<MeetingFisico> lista = new ArrayList<MeetingFisico>();
		while(rs.next()) {
			MeetingFisico s = new MeetingFisico(rs.getString("codMeet"));
			s.setTitolo(rs.getString("titolo"));
			s.setData(rs.getDate("data"));
			s.setOraInizio(rs.getString("oraInizio"));
			s.setOraFine(rs.getString("oraFine"));
			s.setLuogo(rs.getString("luogo"));
			s.setNomeSala(rs.getString("nomeSala"));
			s.setProgettoMeeting(fks.getObject("codProgetto", Progetto.class));
			lista.add(s);
		}
		rs.close();
		return lista;
	}

}
