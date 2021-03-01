package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.ProgettoDAO;
import entity.Progetto;

public class ProgettoDAOPostgresImpl implements ProgettoDAO {
	
	private Connection connection;
	private PreparedStatement getProgettoByNomePS, inserisciProgettoPS;
	
	public ProgettoDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;
		getProgettoByNomePS = connection.prepareStatement("SELECT * FROM progetto WHERE nome LIKE ?");
		inserisciProgettoPS = connection.prepareStatement("INSERT INTO progetto VALUES (?, ?, ?, ?, ?, )");
	}

	@Override
	public List<Progetto> getAllProgetti() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Progetto> getProgettoByNome(String nome) throws SQLException {
		getProgettoByNomePS.setString(1, nome);
        ResultSet rs= getProgettoByNomePS.executeQuery();
        List<Progetto> lista = new ArrayList<Progetto>();
        while(rs.next())
        {
            Progetto s = new Progetto(rs.getString("codProgetto")); //rs.getString(1)
            s.setNomeProgetto(rs.getString("nome"));
            s.setAmbitoProgetto(rs.getString("ambito"));
            s.setTipoProgetto(rs.getString("tipo"));
            s.setStato(rs.getString("stato"));
            
            
            lista.add(s);
        }
        rs.close();
        return lista;
	}


	@Override
	public List<Progetto> getProgettoByCodProgetto(String codice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Progetto> getProgettoByAmbito(String ambito) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Progetto> getMembroByTipo(String tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int inserisciProgetto(Progetto progetto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cancellaMembro(Progetto progetto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
