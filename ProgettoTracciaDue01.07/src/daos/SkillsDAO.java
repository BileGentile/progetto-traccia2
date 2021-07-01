package daos;

import java.sql.SQLException;
import java.util.List;
import entity.Skills;

public interface SkillsDAO {
	public List<Skills> getAllSkills()  throws SQLException;
	public List<Skills> getSkillByCodFiscale(String codFiscale) throws SQLException;
	
	public int inserisciSkills (Skills skills) throws SQLException;
	public int inserisciCodFiscale(String codFiscale) throws SQLException;
	
}
