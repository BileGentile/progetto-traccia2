package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.ArchivioPartecipantiProgettoDAO;
import daos.SkillsDAO;
import entity.ArchivioPartecipantiProgetto;
import entity.Meeting;
import entity.Skills;

public class ArchivioPartecipantiProgettoDAOPostgresImpl implements ArchivioPartecipantiProgettoDAO {
	
	private Connection connection;
	private PreparedStatement InserisciArchivioPartecipantiPS;
	public ArchivioPartecipantiProgettoDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;
		InserisciArchivioPartecipantiPS = connection.prepareStatement("INSERT INTO ArchivioPartecipantiProgetto VALUES (?, ?,?)");
	}
	
	@Override
	public List<ArchivioPartecipantiProgetto> getPartecipantiByProgetto(String CodProgetto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int InserisciArchivioPartecipanti(ArchivioPartecipantiProgetto archivioPartecipantiProgetto)throws SQLException  {
		InserisciArchivioPartecipantiPS.setString(1,archivioPartecipantiProgetto.getCodFiscale() );
		InserisciArchivioPartecipantiPS.setString(2,archivioPartecipantiProgetto.getNomeProgetto() );
		InserisciArchivioPartecipantiPS.setString(3,archivioPartecipantiProgetto.getRuolo() );
	    int row = InserisciArchivioPartecipantiPS.executeUpdate();
	    System.out.print(row); 

		return row;
	
}

	@Override
	public int EliminaPartecipanteProgettoByCodFiscale(ArchivioPartecipantiProgetto archivioPartecipantiProgetto) throws SQLException {
		
		return 0;
	}

}