package daos;

import java.sql.SQLException;
import java.util.List;

import entity.Meeting;
import entity.MeetingTelematico;
import entity.Sviluppatore;

public interface MeetingTelematicoDAO {

	public MeetingTelematico getMeetingTelematicoByTitolo(String nome) throws SQLException;
	
	public List<MeetingTelematico> getMeetingTelematicoCodFiscale(String CF) throws SQLException;
	
	public List<MeetingTelematico> getMeetingTelematicoProjectManager(String CF) throws SQLException;
	
	public int inserisciMeetingTelematico(MeetingTelematico meetingTelematico) throws SQLException;
	
	public List<Sviluppatore> cercaPartecipantiMeeting(String codMeet)throws SQLException;
	
	public int InserisciPartecipazione(String cF, String codMeet)throws SQLException;
	

			
}


