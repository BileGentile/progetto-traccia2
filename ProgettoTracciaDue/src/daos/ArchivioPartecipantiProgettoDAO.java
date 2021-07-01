package daos;

import java.sql.SQLException;
import java.util.List;

import entity.ArchivioPartecipantiProgetto;
import entity.Skills;


public interface ArchivioPartecipantiProgettoDAO {

	public List<ArchivioPartecipantiProgetto> getPartecipantiByProgetto(String CodProgetto) throws SQLException ;
	
	public int InserisciArchivioPartecipanti(ArchivioPartecipantiProgetto archivioPartecipantiProgetto) throws SQLException;
	public int EliminaPartecipanteProgettoByCodFiscale(ArchivioPartecipantiProgetto archivioPartecipantiProgetto) throws SQLException;
}

