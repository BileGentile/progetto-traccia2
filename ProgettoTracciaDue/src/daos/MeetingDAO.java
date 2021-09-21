package daos;

import java.sql.SQLException;
import java.util.List;
import entity.Meeting;
import entity.Progetto;

public interface MeetingDAO {
	
	public List<Meeting> getMeetingSviluppatore(String CF)  throws SQLException;

}