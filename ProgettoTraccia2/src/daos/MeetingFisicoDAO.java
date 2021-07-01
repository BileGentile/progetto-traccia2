package daos;

import java.sql.SQLException;
import java.util.List;

import entity.MeetingFisico;

public interface MeetingFisicoDAO {
	public List<MeetingFisico> getAllMeetingFisico()  throws SQLException;
	public List<MeetingFisico> getMeetingFisicoByNome(String nome) throws SQLException;
	public List<MeetingFisico> getMeetingFisicoByCodMeet(String CodMeet) throws SQLException ;
	
	public int inserisciMeetingFisico(MeetingFisico meetingFisico) throws SQLException;
	
	public int cancellaMeetingFisico(MeetingFisico meetingFisico);
}
