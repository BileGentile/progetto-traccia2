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
	
	private PreparedStatement  getPartecipantiProgettoPS,InserisciArchivioPartecipantiPS;

	
	public MembroDAOPostgresImpl (Connection connection) throws SQLException{
			this.connection=connection;
			
			getPartecipantiProgettoPS = connection.prepareStatement("select codfiscale,nome, cognome,ruolo, valutazione\n"
					+ "from archiviopartecipantiprogetto natural join membro \n"
					+ "where nomeprogetto= ?;");
			
			InserisciArchivioPartecipantiPS=connection.prepareStatement("INSERT INTO partecipazioniprogetto VALUES (?,?);");
		}

	@Override
	public List<Membro> getPartecipantiProgetto(String nomeprogetto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int InserisciArchivioPartecipanti(String codiceFiscale, String codiceprogetti) throws SQLException{
		InserisciArchivioPartecipantiPS.setString(1, codiceFiscale);
		InserisciArchivioPartecipantiPS.setString(2, codiceprogetti);
        int row = InserisciArchivioPartecipantiPS.executeUpdate();
        System.out.print(row); 
        return row;
        
	}

}