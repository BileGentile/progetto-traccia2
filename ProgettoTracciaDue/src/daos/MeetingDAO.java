package daos;

import java.sql.SQLException;
import java.util.List;
import entity.Meeting;

public interface MeetingDAO {
	
	public List<Meeting> getMeetingSviluppatore(String CF)  throws SQLException;

}