package daos;

import java.sql.SQLException;
import java.util.List;
import entity.Progetto;


public interface ProgettoDAO {
	
	
		public List<Progetto> getAllProgetti();
		public List<Progetto> getProgettoByNome(String nome) throws SQLException;
		public List<Progetto> getProgettoByCodProgetto(String codice);
		public List<Progetto> getProgettoByAmbito(String ambito);
		public List<Progetto> getMembroByTipo(String tipo);
		
		public int inserisciProgetto(Progetto progetto) throws SQLException;
		
		public int cancellaMembro(Progetto progetto);

	}

