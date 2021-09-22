package daos;

import java.sql.SQLException;
import java.util.List;
import entity.MeetingFisico;
import entity.Sviluppatore;

public interface MeetingFisicoDAO {

	public MeetingFisico getMeetingFisicoByTitolo(String titolo) throws SQLException;
	
	public List<MeetingFisico> getMeetingFisicoCodFiscale(String CF) throws SQLException;
	
	public List<MeetingFisico> getMeetingFisicoProjectManager(String CF) throws SQLException;
	
	public int inserisciMeetingFisico(MeetingFisico meetingFisico) throws SQLException;
	
	public List<Sviluppatore> cercaPartecipantiMeeting(String codMeet)throws SQLException;
	
	public int InserisciPartecipazione(String cF, String codMeet)throws SQLException;
	
	
}
