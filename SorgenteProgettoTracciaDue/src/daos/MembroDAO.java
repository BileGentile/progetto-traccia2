package daos;

import java.sql.SQLException;
import java.util.List;
import entity.Membro;

public interface MembroDAO {
	public List<Membro> getAllMembri();
	
	public int inserisciMembro(Membro membro) throws SQLException;
	
	public int cancellaMembro(Membro membro);

}
