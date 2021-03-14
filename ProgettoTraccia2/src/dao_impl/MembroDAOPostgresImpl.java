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
	private PreparedStatement getAllMembriPS, inserisciMembroPS, getProjectManagerByCodFiscalePS, getSviluppatoreByCodFiscalePS, getMembroByValutazionePS , getAllSviluppatoriPS, inserisciValutazionePS;
	public MembroDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;
		getAllMembriPS = connection.prepareStatement("SELECT * FROM membro");
		inserisciMembroPS = connection.prepareStatement("INSERT INTO membro VALUES (?, ?, ?, ?, ?, ?)");
		getProjectManagerByCodFiscalePS = connection.prepareStatement("SELECT * FROM membro WHERE codFiscale LIKE UPPER(?) AND ruolo LIKE 'ProjectManager' ");
		getSviluppatoreByCodFiscalePS = connection.prepareStatement("SELECT * FROM membro WHERE codFiscale LIKE UPPER(?) AND ruolo LIKE 'Sviluppatore' ");
		getMembroByValutazionePS = connection.prepareStatement("SELECT * FROM membro WHERE Valutazione LIKE ? AND ruolo LIKE 'Sviluppatore' ");
		getAllSviluppatoriPS = connection.prepareStatement("SELECT * FROM membro WHERE ruolo LIKE 'Sviluppatore' ");
		inserisciValutazionePS = connection.prepareStatement("UPDATE membro SET valutazione  = ? WHERE codfiscale LIKE ?");
	}
	
	@Override
	public List<Membro>  getAllMembri() throws SQLException {
        ResultSet rs= getAllMembriPS.executeQuery();
        List<Membro> lista = new ArrayList<Membro>();
        while(rs.next())
        {
            Membro s = new Membro(rs.getString("codFiscale")); //rs.getString(1)
            s.setNome(rs.getString("nome"));
            s.setCognome(rs.getString("cognome"));
            s.setRuolo(rs.getString("ruolo"));
            s.setSalarioMedio(rs.getInt("SalarioMedio"));
            s.setRuolo(rs.getString("valutazione"));
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
            s.setValutazione(rs.getString("Valutazione"));
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
            s.setValutazione(rs.getString("Valutazione"));
            lista.add(s);
        }
        rs.close();
        return lista;
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
        inserisciMembroPS.setString(6, membro.getValutazione());
        int row = inserisciMembroPS.executeUpdate();
        System.out.print(row); 

        return row;
	}


	@Override
	public int  inserisciValutazione(String valutazione, String codfiscale) throws SQLException {
		inserisciValutazionePS.setString(1, valutazione);
		inserisciValutazionePS.setString(2, codfiscale);
		
        int row = inserisciValutazionePS.executeUpdate();
	
        return row;
	}


	

	@Override
	public List<Membro> getMembroByValutazione(String valutazione)  throws SQLException{
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Membro> getMembroByNome(String nome) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Membro> getAllSviluppatori() throws SQLException {
		ResultSet rs = getAllSviluppatoriPS.executeQuery();
	    List<Membro> lista = new ArrayList<Membro>();
	    while(rs.next())
	    {
	            Membro s = new Membro(rs.getString("codFiscale"));
	            s.setNome(rs.getString("nome"));
	            s.setCognome(rs.getString("cognome"));
	            s.setRuolo(rs.getString("ruolo"));
	            s.setSalarioMedio(rs.getInt("SalarioMedio"));
	            s.setValutazione(rs.getString("Valutazione"));
	            lista.add(s);
	    }
	        rs.close();
	        return lista;
		}

	
	@Override
	public int cancellaMembro(Membro membro) {
		// TODO Auto-generated method stub
		return 0;
	}


}