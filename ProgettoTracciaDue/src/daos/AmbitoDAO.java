package daos;

import java.sql.SQLException;
import java.util.List;
import entity.Ambito;
import entity.Skills;

public interface AmbitoDAO {
	public List<Ambito> getAllAmbito()  throws SQLException;
	public int inserisciAmbito (Ambito ambito) throws SQLException;
	public int inserisciAmbitoProgetto(String CodiceProgetto, String CodAmbito)throws SQLException;
}
