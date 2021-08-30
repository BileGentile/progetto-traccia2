package daos;

import java.sql.SQLException;
import java.util.List;
import entity.Progetto;

public interface ProgettoDAO {

	public List<Progetto> getAllProgetti() throws SQLException;
	public String getProgettoByNome(String nome) throws SQLException;
//	public List<Progetto> getProgettoByCodProgetto(String codice);
//	public List<Progetto> getProgettoByAmbito(String ambito);
//	public List<Progetto> getMembroByTipo(String tipo);
	public List<Progetto> getProgettoProjectManager(String codfiscale) throws SQLException;
	public int inserisciProgetto(Progetto progetto) throws SQLException;
	public int cambiaStatoProgetto(String nome) throws SQLException;
//	public int cancellaProgetto(Progetto progetto);
	public int inserisciArchivioPartecipantiProgettoPS(String codFiscale, String codProgetto)throws SQLException ;

}