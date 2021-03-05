package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.MembroDAO;
import entity.Membro;

public class MembroDAOPostgresImpl implements MembroDAO {
	
	private Connection connection;
	private PreparedStatement getMembroByNomePS, inserisciMembroPS, getProjectManagerByCodFiscalePS, getSviluppatoreByCodFiscalePS;
	
	public MembroDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;
		getMembroByNomePS = connection.prepareStatement("SELECT * FROM membro WHERE nome LIKE ?");
		inserisciMembroPS = connection.prepareStatement("INSERT INTO membro VALUES (?, ?, ?, ?, ?)");
		getProjectManagerByCodFiscalePS = connection.prepareStatement("SELECT * FROM membro WHERE codFiscale LIKE ? AND ruolo LIKE 'ProjectManager' ");
		getSviluppatoreByCodFiscalePS = connection.prepareStatement("SELECT * FROM membro WHERE codFiscale LIKE ? AND ruolo LIKE 'Sviluppatore' ");
	}

	@Override
	public List<Membro> getAllMembri() {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	@Override
	public List<Membro> getMembroByNome(String nome) throws SQLException {
		getMembroByNomePS.setString(1, nome);
        ResultSet rs= getMembroByNomePS.executeQuery();
        List<Membro> lista = new ArrayList<Membro>();
        while(rs.next())
        {
            Membro s = new Membro(rs.getString("codFiscale")); //rs.getString(1)
            s.setNome(rs.getString("nome"));
            s.setCognome(rs.getString("cognome"));
            s.setRuolo(rs.getString("ruolo"));
            s.setSalarioMedio(rs.getInt("SalarioMedio"));
            lista.add(s);
        }
        rs.close();
        return lista;
	}

	@Override
	public List<Membro> getMembroByRuolo(String ruolo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Membro> getMembroByCognome(String cognome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	
	public List<Membro> getProjectManagerByCodFiscale(String codfiscale) throws SQLException {
		getProjectManagerByCodFiscalePS.setString(1, codfiscale);
        ResultSet rs= getProjectManagerByCodFiscalePS.executeQuery();
        List<Membro> lista = new ArrayList<Membro>();
        while(rs.next())
        {
            Membro s = new Membro(rs.getString("codFiscale")); //rs.getString(1)
            s.setNome(rs.getString("nome"));
            s.setCognome(rs.getString("cognome"));
            s.setRuolo(rs.getString("ruolo"));
            s.setSalarioMedio(rs.getInt("SalarioMedio"));
            lista.add(s);
        }
        rs.close();
        return lista;
	}

	@Override
	public List<Membro> getSviluppatoreByCodFiscale(String codfiscale) throws SQLException {
		getSviluppatoreByCodFiscalePS.setString(1, codfiscale);
        ResultSet rs= getSviluppatoreByCodFiscalePS.executeQuery();
        List<Membro> lista = new ArrayList<Membro>();
        while(rs.next())
        {
            Membro s = new Membro(rs.getString("codFiscale")); //rs.getString(1)
            s.setNome(rs.getString("nome"));
            s.setCognome(rs.getString("cognome"));
            s.setRuolo(rs.getString("ruolo"));
            s.setSalarioMedio(rs.getInt("SalarioMedio"));
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	@Override
	public List<Membro> getMembroByNomeCognome(String nome, String cognome) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Membro> getMembroBySalario(int salario){
		 return null;
	}
	
	@Override
	public int inserisciMembro(Membro membro) throws SQLException {
		inserisciMembroPS.setString(1, membro.getNome());
        inserisciMembroPS.setString(2, membro.getCognome());
        inserisciMembroPS.setString(3, membro.getCF());
        inserisciMembroPS.setString(4, membro.getRuolo());
        inserisciMembroPS.setInt(5, membro.getSalarioMedio());
        int row = inserisciMembroPS.executeUpdate();
        return row;
	}
	
	
	
	@Override
	public int cancellaMembro(Membro membro) {
		// TODO Auto-generated method stub
		return 0;
	}

}
