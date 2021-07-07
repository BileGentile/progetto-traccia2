package daos;

import java.sql.SQLException;
import java.util.List;
import entity.Membro;

public interface MembroDAO {
	public List<Membro> getAllMembri()  throws SQLException;
	public List<Membro> getMembroByNome(String nome) throws SQLException;
	public List<Membro> getMembroByCognome(String cognome);
	public List<Membro> getSviluppatoreByCodFiscale(String codfiscale) throws SQLException ;
	public List<Membro> getProjectManagerByCodFiscale(String codfiscale) throws SQLException;
	public List<Membro> getMembroByRuolo(String ruolo) throws SQLException;
	public List<Membro> getSviluppatoreBySalarioESkillsEValutazionePS(int salario, String valutazione, String skills, String progetto) throws SQLException;
	public List<Membro> getSviluppatoreByValutazione(String valutazione)  throws SQLException;
	public List<Membro> getAllSviluppatori() throws SQLException;
	public List<Membro> getPartecipantiProgetto(String nomeprogetto) throws SQLException ;
	public List<Membro> getAllSviluppatoriProgetto(String codfiscale)throws SQLException ;
	
	
	public int inserisciMembro(Membro membro) throws SQLException;
	public int inserisciValutazione(String valutazione, String codFiscale) throws SQLException;
	
	public int cancellaMembro(Membro membro);

}
