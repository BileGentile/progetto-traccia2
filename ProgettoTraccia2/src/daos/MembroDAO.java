package daos;

import java.sql.SQLException;
import java.util.List;
import entity.Membro;

public interface MembroDAO {
	public List<Membro> getAllMembri();
	public List<Membro> getMembroByNome(String nome) throws SQLException;
	public List<Membro> getMembroByCognome(String cognome);
	public List<Membro> getMembroByCodFiscale(String codfiscale) throws SQLException;
	public List<Membro> getMembroByNomeCognome(String nome, String cognome);
	public List<Membro> getMembroByRuolo(String ruolo);
	
	public int inserisciMembro(Membro membro) throws SQLException;
	
	public int cancellaMembro(Membro membro);

}
