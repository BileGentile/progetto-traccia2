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
		private PreparedStatement getMeetingSviluppatore;
		

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

}