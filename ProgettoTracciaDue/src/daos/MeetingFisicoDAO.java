package daos;

import java.sql.SQLException;
import java.util.List;
import entity.MeetingFisico;

public interface MeetingFisicoDAO {

	public List<MeetingFisico> getAllMeetingFisico()  throws SQLException;
	public MeetingFisico getMeetingFisicoByTitolo(String titolo) throws SQLException;
	public MeetingFisico getMeetingFisicoByCodMeet(String CodMeet) throws SQLException ;
	public List<MeetingFisico> getMeetingFisicoCodFiscale(String CF) throws SQLException;
		
	public int inserisciMeetingFisico(MeetingFisico meetingFisico) throws SQLException;
	
	public int cancellaMeetingFisicoByTitolo(MeetingFisico meetingFisico) throws SQLException;
	public int getInserisciPartecipazione(String cF, String codMeet)throws SQLException;
	public int getInserisciPartecipazionePM(String cF, String codMeet)throws SQLException;
		
}
