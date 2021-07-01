package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.MeetingDAO;
import entity.Meeting;

public class MeetingDAOPostgresImpl implements MeetingDAO {

		private Connection connection;
		private PreparedStatement inserisciMeeting, getMeetingByCodMeet, getAllMeeting;
		

		public MeetingDAOPostgresImpl (Connection connection) throws SQLException{
			this.connection=connection;
			inserisciMeeting = connection.prepareStatement("INSERT INTO Meeting VALUES ( nextval(?), ?, ?, ?, ?,?)");
			getMeetingByCodMeet = connection.prepareStatement("SELECT * FROM Meeting WHERE codMeet LIKE ?  ");
			getAllMeeting = connection.prepareStatement("SELECT * FROM Meeting");
			}


		@Override
		public List<Meeting> getAllMeeting() throws SQLException {
			return null;
		}



		@Override
		public List<Meeting> getMeetingByCodMeet(String codMeet) throws SQLException {
			getMeetingByCodMeet.setString(1, codMeet);
	        ResultSet rs= getMeetingByCodMeet.executeQuery();
	        List<Meeting> lista = new ArrayList<Meeting>();
	        while(rs.next())
	        {
	        	Meeting s = new Meeting(rs.getString("codMeet")); //rs.getString(1)
	            s.setData(rs.getString("data"));
	            s.setOraInizio(rs.getString("oraInizio"));
	            s.setPiattaforma(rs.getString("Piattaforma"));
	            s.setTipologia(rs.getString("Tipologia"));
	            s.setPiattaforma(rs.getString("NomeSala"));
	            lista.add(s);
	        }
	        rs.close();
	        return lista;
		}


		
		@Override
		public int inserisciMeeting(Meeting meeting) throws SQLException {
			inserisciMeeting.setString(1, meeting.getCodMeet());
			inserisciMeeting.setString(2, meeting.getData());
			inserisciMeeting.setString(3, meeting.getOraInizio());
			inserisciMeeting.setString(4, meeting.getPiattaforma());
			inserisciMeeting.setString(5, meeting.getTipologia());
			inserisciMeeting.setString(6, meeting.getNomeSala());
	        int row = inserisciMeeting.executeUpdate();
	        return row;
		}
		
		



		@Override
		public int cancellaMeeting(Meeting meeting) {
			// TODO Auto-generated method stub
			return 0;
		}


		
}