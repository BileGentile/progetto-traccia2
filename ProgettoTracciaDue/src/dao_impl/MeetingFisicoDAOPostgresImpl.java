package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.MeetingFisicoDAO;
import entity.MeetingFisico;
import entity.Progetto;

public class MeetingFisicoDAOPostgresImpl implements MeetingFisicoDAO  {
	
	private Connection connection;
	private PreparedStatement getMeetingFisicoByNomePS, inserisciMeetingFisicoPS, getMeetingFisicoByCodMeet;
	

	public MeetingFisicoDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;
//fare
//		getMeetingFisicoByNomePS = connection.prepareStatement("SELECT * FROM MeetingTelematicoo WHERE nome LIKE ?");
		inserisciMeetingFisicoPS = connection.prepareStatement("INSERT INTO MeetingFisico VALUES (nextval(?), ?,?, 'NULL', ?, ?,?)");
//		getMeetingFisicoByCodMeet = connection.prepareStatement("SELECT * FROM MeetingTelematicoo WHERE codMeet LIKE ?  ");
//fare
		}

//fare
//	@Override
//	public List<MeetingFisico> getMeetingFisicoByNome(String nome) throws SQLException {
//		getMeetingFisicoByNomePS.setString(1, nome);
//        ResultSet rs= getMeetingFisicoByNomePS.executeQuery();
//        List<MeetingFisico> lista = new ArrayList<MeetingFisico>();
//        while(rs.next())
//        {
//        	MeetingFisico s = new MeetingFisico(rs.getString("codMeet")); //rs.getString(1)
//            s.setNome(rs.getString("nome"));
//            s.setData(rs.getString("data"));
//            s.setOraInizio(rs.getString("oraInizio"));
//            s.setPiattaforma(rs.getString("nomeSala"));
//            s.setOrganizzatore(rs.getString("Organizzatore"));
//            lista.add(s);
//        }
//        rs.close();
//        return lista;
//	}
//	
//	@Override
//	public List<MeetingFisico> getMeetingFisicoByCodMeet(String codMeet) throws SQLException {
//		getMeetingFisicoByCodMeet.setString(2, codMeet);
//        ResultSet rs= getMeetingFisicoByCodMeet.executeQuery();
//        List<MeetingFisico> lista = new ArrayList<MeetingFisico>();
//        while(rs.next())
//        {
//        	MeetingFisico s = new MeetingFisico(rs.getString("codMeet")); //rs.getString(1)
//            s.setNome(rs.getString("nome"));
//            s.setData(rs.getString("data"));
//            s.setOraInizio(rs.getString("oraInizio"));
//            s.setPiattaforma(rs.getString("nomeSala"));
//            s.setOrganizzatore(rs.getString("Organizzatore"));
//            lista.add(s);
//        }
//        rs.close();
//        return lista;
//	}
//	
	@Override
	public int inserisciMeetingFisicoPS(MeetingFisico meetingFisico) throws SQLException {
		Progetto p= meetingFisico.getProgettoMeeting();
	
		inserisciMeetingFisicoPS.setString(1, meetingFisico.getCodMeet());
		inserisciMeetingFisicoPS.setString(2, meetingFisico.getData());
		inserisciMeetingFisicoPS.setString(3, meetingFisico.getOraInizio());
		inserisciMeetingFisicoPS.setString(4, meetingFisico.getNomeSala());
		inserisciMeetingFisicoPS.setInt(5, meetingFisico.getDurata());
		inserisciMeetingFisicoPS.setString(6, p.getCodiceProgetto());
        int row = inserisciMeetingFisicoPS.executeUpdate();
        return row;
	}
//	
//	@Override
//	public int cancellaMeetingFisico(MeetingFisico meetingFisico) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<MeetingFisico> getAllMeetingFisico() throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//fare

}
