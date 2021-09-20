package daos;

import java.sql.SQLException;
import java.util.List;
import entity.Meeting;
import entity.Progetto;

public interface MeetingDAO {
	public List<Meeting> getMeetingSviluppatore(String CF)  throws SQLException;
	
//	public List<Meeting> getAllMeeting()  throws SQLException;
//	public List<Meeting> getMeetingByCodMeet(String CodMeet) throws SQLException ;
//	public int inserisciMeeting(Meeting meeting) throws SQLException;
//	public int cancellaMeeting(Meeting meeting);
//fare
}