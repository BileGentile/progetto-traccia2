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
	
	private PreparedStatement  getAllMembriPS, getProjectManagerByCodFiscalePS, getSviluppatoreByValutazionePS ,getSviluppatoreBySalarioESkillsEValutazionePS, getAllSviluppatoriPS, inserisciValutazionePS,getPartecipantiProgettoPS, getAllSviluppatoriProgettoPS ,InserisciArchivioPartecipantiPS;

	
	public MembroDAOPostgresImpl (Connection connection) throws SQLException{
			this.connection=connection;
			getPartecipantiProgettoPS = connection.prepareStatement("select codfiscale,nome, cognome,ruolo, valutazione\n"
					+ "from archiviopartecipantiprogetto natural join membro \n"
					+ "where nomeprogetto= ?;");
			
			InserisciArchivioPartecipantiPS=connection.prepareStatement("INSERT INTO partecipazioniprogetto VALUES (?,?);");
		}
	
////fare
//	@Override
//	public List<Membro>  getAllMembri() throws SQLException {
//        ResultSet rs= getAllMembriPS.executeQuery();
//        List<Membro> lista = new ArrayList<Membro>();
//        while(rs.next())
//        {
//            Membro s = new Membro(rs.getString("codFiscale")); //rs.getString(1)
//            s.setNome(rs.getString("nome"));
//            s.setCognome(rs.getString("cognome"));
//            s.setRuolo(rs.getString("ruolo"));
//            s.setSalarioMedio(rs.getInt("SalarioMedio"));
//            s.setValutazione(rs.getString("valutazione"));
//            lista.add(s);
//        }
//        rs.close();
//        return lista;
//	}
//	
//	@Override
//	public List<Membro>  getPartecipantiProgetto(String nomeprogetto) throws SQLException {
//		getPartecipantiProgetto.setString(1, nomeprogetto);
//        ResultSet rs= getPartecipantiProgetto.executeQuery();
//        List<Membro> lista = new ArrayList<Membro>();
//        while(rs.next())
//        {
//            Membro s = new Membro(rs.getString("codFiscale")); //rs.getString(1)
//            s.setNome(rs.getString("nome"));
//            s.setCognome(rs.getString("cognome"));
//            s.setRuolo(rs.getString("ruolo"));
//            s.setValutazione(rs.getString("valutazione"));
//            lista.add(s);
//        }
//        rs.close();
//        return lista;
//	}
//
//	@Override
//	public List<Membro> getMembroByRuolo(String ruolo) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Membro> getMembroByCognome(String cognome) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Membro> getSviluppatoreBySalarioESkillsEValutazionePS(int salario, String valutazione, String skills, String progetto) throws SQLException{
//		getSviluppatoreBySalarioESkillsEValutazionePS.setInt(1, salario);
//		getSviluppatoreBySalarioESkillsEValutazionePS.setString(2, valutazione);
//		getSviluppatoreBySalarioESkillsEValutazionePS.setString(3, skills);
//		getSviluppatoreBySalarioESkillsEValutazionePS.setString(4, progetto);
//        ResultSet rs= getSviluppatoreBySalarioESkillsEValutazionePS.executeQuery();
//        List<Membro> lista = new ArrayList<Membro>();
//        while(rs.next())
//        {
//            Membro s = new Membro(rs.getString("codFiscale")); 
//            s.setNome(rs.getString("nome"));
//            s.setCognome(rs.getString("cognome"));
//            s.setRuolo(rs.getString("ruolo"));
//            s.setSalarioMedio(rs.getInt("SalarioMedio"));
//            s.setValutazione(rs.getString("Valutazione"));
//            lista.add(s);
//        }
//        rs.close();
//        return lista;
//	}
//	
//	@Override
//	public int  inserisciValutazione(String valutazione, String codfiscale) throws SQLException {
//		inserisciValutazionePS.setString(1, valutazione);
//		inserisciValutazionePS.setString(2, codfiscale);
//        int row = inserisciValutazionePS.executeUpdate();
//        return row;
//	}
//
//	@Override
//	public List<Membro> getMembroByNome(String nome) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Membro> getAllSviluppatori() throws SQLException {
//		ResultSet rs = getAllSviluppatoriPS.executeQuery();
//	    List<Membro> lista = new ArrayList<Membro>();
//	    while(rs.next())
//	    {
//	            Membro s = new Membro(rs.getString("codFiscale"));
//	            s.setNome(rs.getString("nome"));
//	            s.setCognome(rs.getString("cognome"));
//	            s.setRuolo(rs.getString("ruolo"));
//	            s.setSalarioMedio(rs.getInt("SalarioMedio"));
//	            lista.add(s);
//	    }
//	        rs.close();
//	        return lista;
//		}
//	
//	@Override
//	public int cancellaMembro(Membro membro) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public List<Membro> getSviluppatoreByValutazione(String valutazione) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public List<Membro> getAllSviluppatoriProgetto (String codfiscale) throws SQLException {
		getAllSviluppatoriProgettoPS.setString(1, codfiscale);
        ResultSet rs= getAllSviluppatoriProgettoPS.executeQuery();
        List<Membro> lista = new ArrayList<Membro>();
        while(rs.next())
        {
            Membro s = new Membro(rs.getString("codFiscale")); 
            lista.add(s);
        }
        rs.close();
        return lista;
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