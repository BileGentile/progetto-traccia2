package daos;

import java.sql.SQLException;
import java.util.List;

import entity.MeetingTelematico;

public interface MeetingTelematicoDAO {

	public List<MeetingTelematico> getAllMeeting()  throws SQLException;
	public List<MeetingTelematico> getMeetingTelematicoByNome(String nome) throws SQLException;
	public List<MeetingTelematico> getMeetingTelematicoByCodMeet(String CodMeet) throws SQLException ;

	public int inserisciMeetingTelematico(MeetingTelematico meetingTelematico) throws SQLException;
	
	public int cancellaMeetingTelematico(MeetingTelematico meetingTelematico);
	
}



