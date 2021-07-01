package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.MeetingFisicoDAO;
import entity.MeetingFisico;

public class MeetingFisicoDAOPostgresImpl implements MeetingFisicoDAO  {
	
	private Connection connection;
	private PreparedStatement getMeetingFisicoByNomePS, inserisciMeetingFisico, getMeetingFisicoByCodMeet;
	

	public MeetingFisicoDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;
		getMeetingFisicoByNomePS = connection.prepareStatement("SELECT * FROM MeetingTelematicoo WHERE nome LIKE ?");
		inserisciMeetingFisico = connection.prepareStatement("INSERT INTO MeetingTelematicoo VALUES (?,  nextval(?), ?, ?, ?)");
		getMeetingFisicoByCodMeet = connection.prepareStatement("SELECT * FROM MeetingTelematicoo WHERE codMeet LIKE ?  ");
		}


	
	@Override
	public List<MeetingFisico> getMeetingFisicoByNome(String nome) throws SQLException {
		getMeetingFisicoByNomePS.setString(1, nome);
        ResultSet rs= getMeetingFisicoByNomePS.executeQuery();
        List<MeetingFisico> lista = new ArrayList<MeetingFisico>();
        while(rs.next())
        {
        	MeetingFisico s = new MeetingFisico(rs.getString("codMeet")); //rs.getString(1)
            s.setNome(rs.getString("nome"));
            s.setData(rs.getString("data"));
            s.setOraInizio(rs.getString("oraInizio"));
            s.setPiattaforma(rs.getString("nomeSala"));
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	@Override
	public List<MeetingFisico> getMeetingFisicoByCodMeet(String codMeet) throws SQLException {
		getMeetingFisicoByCodMeet.setString(2, codMeet);
        ResultSet rs= getMeetingFisicoByCodMeet.executeQuery();
        List<MeetingFisico> lista = new ArrayList<MeetingFisico>();
        while(rs.next())
        {
        	MeetingFisico s = new MeetingFisico(rs.getString("codMeet")); //rs.getString(1)
            s.setNome(rs.getString("nome"));
            s.setData(rs.getString("data"));
            s.setOraInizio(rs.getString("oraInizio"));
            s.setPiattaforma(rs.getString("nomeSala"));
            lista.add(s);
        }
        rs.close();
        return lista;
	}


	
	@Override
	public int inserisciMeetingFisico(MeetingFisico meetingFisico) throws SQLException {
		inserisciMeetingFisico.setString(1, meetingFisico.getNome());
		inserisciMeetingFisico.setString(2, meetingFisico.getCodMeet());
		inserisciMeetingFisico.setString(3, meetingFisico.getData());
		inserisciMeetingFisico.setString(4, meetingFisico.getOraInizio());
		inserisciMeetingFisico.setString(5, meetingFisico.getPiattaforma());
        int row = inserisciMeetingFisico.executeUpdate();
        return row;
	}
	
	
	
	@Override
	public int cancellaMeetingFisico(MeetingFisico meetingFisico) {
		// TODO Auto-generated method stub
		return 0;
	}





	@Override
	public List<MeetingFisico> getAllMeetingFisico() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
