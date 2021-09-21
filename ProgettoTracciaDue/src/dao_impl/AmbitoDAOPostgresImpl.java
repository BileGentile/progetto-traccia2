package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.AmbitoDAO;
import entity.Ambito;
import entity.Skills;

public class AmbitoDAOPostgresImpl implements AmbitoDAO {
	
	private Connection connection;
	private PreparedStatement getAllAmbitoPS,inserisciAmbitoPS, inserisciAmbitoProgettoPS;

	public AmbitoDAOPostgresImpl(Connection connection) throws SQLException{
		this.connection=connection;
		
		getAllAmbitoPS=connection.prepareStatement("SELECT DISTINCT nome FROM ambito;");
		
		inserisciAmbitoPS=connection.prepareStatement("INSERT INTO ambito VALUES (?,nextval(?))");
		
		inserisciAmbitoProgettoPS=connection.prepareStatement("INSERT INTO associazioneambito("
				+ "	SELECT  P.Codprogetto , A.CodAmbito\n"
				+ "	FROM progetto AS P CROSS JOIN ambito AS A \n"
				+ "	WHERE P.nome=? AND A.nome=?); ");
				}

	@Override
	public List<Ambito> getAllAmbito() throws SQLException {
		ResultSet rs= getAllAmbitoPS.executeQuery();
        List<Ambito> lista = new ArrayList<Ambito>();
        while(rs.next())
        {
        	Ambito s = new Ambito(rs.getString("nome"));
            lista.add(s);
        }
        rs.close();
        return lista;
	}

	public int inserisciAmbito (Ambito ambito) throws SQLException{
		inserisciAmbitoPS.setString(1, ambito.getNomeAmbito());
		inserisciAmbitoPS.setString(2, ambito.getCodAmbito());
	    int row = inserisciAmbitoPS.executeUpdate();
	    System.out.print(row); 
		return row;
	}
	
	public int inserisciAmbitoProgetto (String CodiceProgetto, String CodAmbito) throws SQLException{
		inserisciAmbitoProgettoPS.setString(1, CodiceProgetto);
		inserisciAmbitoProgettoPS.setString(2, CodAmbito);
		int row = inserisciAmbitoProgettoPS.executeUpdate();
	    System.out.print(row); 
		return row;
	}

}
