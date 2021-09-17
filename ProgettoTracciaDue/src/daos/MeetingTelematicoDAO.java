package daos;

import java.sql.SQLException;
import java.util.List;

import entity.Meeting;
import entity.MeetingTelematico;

public interface MeetingTelematicoDAO {

	public List<MeetingTelematico> getAllMeetingTelematico()  throws SQLException;
	public MeetingTelematico getMeetingTelematicoByTitolo(String nome) throws SQLException;
	public MeetingTelematico getMeetingTelematicoByCodMeet(String CodMeet) throws SQLException ;
	public List<MeetingTelematico> getMeetingTelematicoCodFiscale(String CF) throws SQLException;
		
	public int inserisciMeetingTelematico(MeetingTelematico meetingTelematico) throws SQLException;
	
	public int cancellaMeetingTelematicoByTitolo(MeetingTelematico meetingTelematico) throws SQLException;
	public int getInserisciPartecipazione(String cF, String codMeet)throws SQLException;
	public int getInserisciPartecipazionePM(String cF, String codMeet)throws SQLException;
		

}


