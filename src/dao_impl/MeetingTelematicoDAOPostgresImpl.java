package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.MeetingTelematicoDAO;
import entity.MeetingTelematico;

public class MeetingTelematicoDAOPostgresImpl implements MeetingTelematicoDAO  {

	private Connection connection;
	private PreparedStatement getMeetingTelematicoByNomePS, getAllMeetingPS, inserisciMeetingPS, getMeetingTelematicoByCodMeetPS;
	

	public MeetingTelematicoDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;
		getMeetingTelematicoByNomePS = connection.prepareStatement("SELECT * FROM MeetingTelematicoo WHERE nome LIKE ?");
		inserisciMeetingPS = connection.prepareStatement("INSERT INTO MeetingTelematicoo VALUES (?,  nextval(?), ?, ?, ?)");
		getMeetingTelematicoByCodMeetPS = connection.prepareStatement("SELECT * FROM MeetingTelematico WHERE codMeet LIKE ?  ");
		getAllMeetingPS = connection.prepareStatement("SELECT codMeet, nome FROM MeetingTelematico JOIN MeetingFisico");
		}


	
	@Override
	public List<MeetingTelematico> getMeetingTelematicoByNome(String nome) throws SQLException {
		getMeetingTelematicoByNomePS.setString(1, nome);
        ResultSet rs= getMeetingTelematicoByNomePS.executeQuery();
        List<MeetingTelematico> lista = new ArrayList<MeetingTelematico>();
        while(rs.next())
        {
        	MeetingTelematico s = new MeetingTelematico(rs.getString("codMeet")); 
            s.setNome(rs.getString("nome"));
            s.setData(rs.getString("data"));
            s.setOraInizio(rs.getString("oraInizio"));
            s.setPiattaforma(rs.getString("piattaforma"));
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	
	
	@Override
	public List<MeetingTelematico> getAllMeeting() throws SQLException {
        ResultSet rs= getAllMeetingPS.executeQuery();
        List<MeetingTelematico> lista = new ArrayList<MeetingTelematico>();
        while(rs.next())
        {
        	MeetingTelematico s = new MeetingTelematico(rs.getString("codMeet"));
            s.setNome(rs.getString("nome"));
            lista.add(s);
        }
        rs.close();
        return lista;
	}


		
	
	@Override
	public int inserisciMeetingTelematico(MeetingTelematico meetingTelematico) throws SQLException {
		inserisciMeetingPS.setString(1, meetingTelematico.getNome());
		inserisciMeetingPS.setString(2, meetingTelematico.getCodMeet());
		inserisciMeetingPS.setString(3, meetingTelematico.getData());
		inserisciMeetingPS.setString(4, meetingTelematico.getOraInizio());
		inserisciMeetingPS.setString(5, meetingTelematico.getPiattaforma());
        int row = inserisciMeetingPS.executeUpdate();
        return row;
	}
	
	
	
	@Override
	public int cancellaMeetingTelematico(MeetingTelematico meetingTelematico) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public List<MeetingTelematico> getMeetingTelematicoByCodMeet(String CodMeet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}




}
