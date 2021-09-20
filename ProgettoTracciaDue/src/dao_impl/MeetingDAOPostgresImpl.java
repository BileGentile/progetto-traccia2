package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.MeetingDAO;
import entity.Meeting;
import entity.Progetto;

public class MeetingDAOPostgresImpl implements MeetingDAO {

		private Connection connection;
		private PreparedStatement inserisciMeeting, getMeetingByCodMeet, getAllMeeting, getMeetingSviluppatore;
		

		public MeetingDAOPostgresImpl (Connection connection) throws SQLException{
			this.connection=connection;
			getMeetingSviluppatore=connection.prepareStatement("(select p.codmeeting, m.titolo, m.data, m.orainizio, m.orafine\n"
					+ "from partecipazionisviluppatoremeetingtelematico as p join meetingtelematico as m \n"
					+ "on p.codmeeting=m.codicemeeting\n"
					+ "where p.codfiscale LIKE ?)\n"
					+ "union \n"
					+ "(select p.codmeeting, m.titolo, m.data, m.orainizio, m.orafine\n"
					+ "from partecipazionisviluppatoremeetingfisico as p join meetingfisico as m \n"
					+ "on p.codmeeting=m.codicemeeting\n"
					+ "where p.codfiscale LIKE ?)");

//			inserisciMeeting = connection.prepareStatement("INSERT INTO Meeting VALUES ( nextval(?),SUBSTR(?,1,10), SUBSTR(?,12,8),?, ?,?,?,?,?)");
//			getMeetingByCodMeet = connection.prepareStatement("SELECT * FROM Meeting WHERE codMeet LIKE ?  ");
//			getAllMeeting = connection.prepareStatement("SELECT * FROM Meeting");
			
		}
		
		@Override
		public List<Meeting> getMeetingSviluppatore(String CF) throws SQLException {
			getMeetingSviluppatore.setString(1, CF);
			getMeetingSviluppatore.setString(2, CF);
			ResultSet rs= getMeetingSviluppatore.executeQuery();
			List<Meeting> lista = new ArrayList<Meeting>();
			 while(rs.next())
		        {
				 	Meeting s = new Meeting(rs.getString("codmeeting")); 
				 	s.setTitolo(rs.getString("titolo"));
				 	s.setData(rs.getDate("data"));
		            s.setOraInizio(rs.getString("oraInizio"));
		            s.setOraFine(rs.getString("oraFine"));
		        	lista.add(s);
		        }
			return lista;
		}

//		@Override
//		public List<Meeting> getAllMeeting() throws SQLException {
//			return null;
//		}
//		
//		@Override
//		public List<Meeting> getMeetingByCodMeet(String codMeet) throws SQLException {
//			getMeetingByCodMeet.setString(1, codMeet);
//	        ResultSet rs= getMeetingByCodMeet.executeQuery();
//	        List<Meeting> lista = new ArrayList<Meeting>();
//	        while(rs.next())
//	        {
//	        	Meeting s = new Meeting(rs.getString("codMeet")); 
//	            s.setData(rs.getString("data"));
//	            s.setOraInizio(rs.getString("oraInizio"));
//	            s.setPiattaforma(rs.getString("Piattaforma"));
//	            s.setTipologia(rs.getString("Tipologia"));
//	            s.setPiattaforma(rs.getString("NomeSala"));
//	            s.setOrganizzatore(rs.getString("Organizzatore"));
//	            s.setNomeProgetto(rs.getString("NomeProgetto"));
//	            s.setDurata(rs.getInt("Durata"));
//	            lista.add(s);
//	        }
//	        rs.close();
//	        return lista;
//		}
//
//		@Override
//		public int inserisciMeeting(Meeting meeting) throws SQLException {
//			inserisciMeeting.setString(1, meeting.getCodMeet());
//			inserisciMeeting.setString(2, meeting.getData());
//			inserisciMeeting.setString(3, meeting.getOraInizio());
//			inserisciMeeting.setString(4, meeting.getPiattaforma());
//			inserisciMeeting.setString(5, meeting.getTipologia());
//			inserisciMeeting.setString(6, meeting.getNomeSala());
//			inserisciMeeting.setString(7, meeting.getOrganizzatore());
//			inserisciMeeting.setString(8, meeting.getNomeProgetto());
//			inserisciMeeting.setInt(9, meeting.getDurata());
//	        int row = inserisciMeeting.executeUpdate();
//	        return row;
//		}
//		
//		@Override
//		public int cancellaMeeting(Meeting meeting) {
//			// TODO Auto-generated method stub
//			return 0;
//		}

}