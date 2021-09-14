package daos;

import java.sql.SQLException;
import java.util.List;
import entity.Membro;

public interface MembroDAO {

	public List<Membro> getPartecipantiProgetto(String nomeprogetto) throws SQLException ;
	
	public int InserisciArchivioPartecipanti(String codiceFiscale, String codiceprogetti) throws SQLException;
	

}
