package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.SviluppatoreDAO;
import entity.Sviluppatore;
import entity.Membro;
import entity.Skills;

public class SviluppatoreDAOPostgresImpl implements SviluppatoreDAO {

	private Connection connection;
		private PreparedStatement getSviluppatoreByCodFiscalePS,getAllSviluppatoriProgettoEMeeting,inserisciSviluppatorePS,inserisciSkillSviluppatorePS, getAllSviluppatoriProgettoPS, inserisciValutazionePS, getPartecipantiProgettoPS,getSviluppatoreBySalarioESkillsEValutazioneETipologiaPS;
	
	public SviluppatoreDAOPostgresImpl (Connection connection) throws SQLException  {
		this.connection=connection;
		
		getSviluppatoreByCodFiscalePS = connection.prepareStatement("SELECT * FROM sviluppatore WHERE codFiscale LIKE ?  ");
		
		inserisciSviluppatorePS = connection.prepareStatement("INSERT INTO sviluppatore VALUES (?,?,UPPER(?), ?,?)");
		
		inserisciSkillSviluppatorePS= connection.prepareStatement("INSERT INTO associazioneskillssviluppatore (codfiscale, codskills)  SELECT ?, S.CodSkills FROM skills AS S WHERE S.nomeskill=?;");
		
		getSviluppatoreBySalarioESkillsEValutazioneETipologiaPS = connection.prepareStatement("select RISULTATO1.codfiscale, RISULTATO1.cognome, RISULTATO1.nome,RISULTATO1.salariomedio, RISULTATO1.valutazione  \r\n"
				+ "from (SELECT * \r\n"
				+ "	  FROM SVILUPPATORE\r\n"
				+ "	  WHERE  salariomedio > ? AND valutazione LIKE ?\r\n"
				+ "	  AND codFiscale IN ((SELECT DISTINCT codfiscale \r\n"
				+ "						  FROM skills AS S \r\n"
				+ "						  Where S.nomeskill LIKE ?)\r\n"
				+ "						 except\r\n"
				+ "						 (select codfiscale\r\n"
				+ "						  from partecipazioniprogetto\r\n"
				+ "						  where codprogetto like ?))) as RISULTATO1 \r\n"
				+ "	LEFT outer join \r\n"
				+ "	(SELECT PP.codfiscale, tipologia\r\n"
				+ "	from progetto as p join partecipazioniprogetto as pp on pp.codprogetto = p.codprogetto\r\n"
				+ "	where tipologia LIKE ?) as RISULTATO2 \r\n"
				+ "	on RISULTATO1.codfiscale=RISULTATO2.codfiscale\r\n"
				+ "ORDER BY tipologia ASC;");
		
		getAllSviluppatoriProgettoPS=connection.prepareStatement("select distinct codfiscale\n"
				+ "from partecipazioniprogetto\n"
				+ "where codprogetto in (SELECT codprogetto\n"
				+ "					   FROM progetto \n"
				+ "					   WHERE codFiscale LIKE ?);");
		getAllSviluppatoriProgettoEMeeting=connection.prepareStatement("(select s.codfiscale\r\n"
				+ "from partecipazioniprojectmanagermeetingfisico as pm join partecipazionisviluppatoremeetingfisico as s\r\n"
				+ " on pm.codmeeting= s.codmeeting\r\n"
				+ "where pm.codfiscale LIKE ? )\r\n"
				+ "union\r\n"
				+ "(select s.codfiscale\r\n"
				+ "from partecipazioniprojectmanagermeetingtelematico as pm join partecipazionisviluppatoremeetingtelematico as s\r\n"
				+ " on pm.codmeeting= s.codmeeting\r\n"
				+ "where pm.codfiscale LIKE ? )");
		
		inserisciValutazionePS = connection.prepareStatement("UPDATE SVILUPPATORE SET valutazione  = ? WHERE codfiscale LIKE ?");
		
		getPartecipantiProgettoPS = connection.prepareStatement("select s.nome, s.cognome, s.codfiscale, s.salariomedio, s.valutazione\r\n"
				+ "from partecipazioniprogetto join sviluppatore as s on partecipazioniprogetto.codfiscale= s.codfiscale\r\n"
				+ "where partecipazioniprogetto.codprogetto in\r\n"
				+ "(select progetto.codprogetto\r\n"
				+ "from partecipazioniprogetto join progetto \r\n"
				+ "on partecipazioniprogettO.codprogetto = progetto.codprogetto\r\n"
				+ "where progetto.nome like ?)");
		
	}

	@Override
	public List<Sviluppatore> getSviluppatoreByCodFiscale(String codfiscale) throws SQLException {
		getSviluppatoreByCodFiscalePS.setString(1, codfiscale);
        ResultSet rs= getSviluppatoreByCodFiscalePS.executeQuery();
        List<Sviluppatore> lista = new ArrayList<Sviluppatore>();
        while(rs.next())
        {
        	Sviluppatore s = new Sviluppatore(rs.getString("codFiscale")); //rs.getString(1)
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	
	@Override
	public int inserisciSviluppatore(Sviluppatore membro) throws SQLException {
		inserisciSviluppatorePS.setString(1, membro.getNome());
		inserisciSviluppatorePS.setString(2, membro.getCognome());
		inserisciSviluppatorePS.setString(3, membro.getCF());
		inserisciSviluppatorePS.setInt(4, membro.getSalarioMedio());
		inserisciSviluppatorePS.setString(5, membro.getValutazione());
        int row = inserisciSviluppatorePS.executeUpdate();
        System.out.print(row); 
        return row;
	}
	
	@Override
	public int inserisciSkillSviluppatore(Sviluppatore membro,String codskill) throws SQLException {
		inserisciSkillSviluppatorePS.setString(1, membro.getCF());
		inserisciSkillSviluppatorePS.setString(2, codskill);
        int row = inserisciSkillSviluppatorePS.executeUpdate();
        System.out.print(row); 
        return row;
	}
	
	@Override
	public List<Sviluppatore> getSviluppatoreBySalarioESkillsEValutazioneETipologiaPS(int salario, String skills, String valutazione, String progetto, String tipologia) throws SQLException{
		getSviluppatoreBySalarioESkillsEValutazioneETipologiaPS.setInt(1, salario);
		getSviluppatoreBySalarioESkillsEValutazioneETipologiaPS.setString(2, valutazione);
		getSviluppatoreBySalarioESkillsEValutazioneETipologiaPS.setString(3, skills);
		getSviluppatoreBySalarioESkillsEValutazioneETipologiaPS.setString(4, progetto);
		getSviluppatoreBySalarioESkillsEValutazioneETipologiaPS.setString(5, tipologia);
        ResultSet rs= getSviluppatoreBySalarioESkillsEValutazioneETipologiaPS.executeQuery();
        List<Sviluppatore> lista = new ArrayList<Sviluppatore>();
        while(rs.next())
        {
        	Sviluppatore s = new Sviluppatore(rs.getString("codFiscale")); 
            s.setNome(rs.getString("nome"));
            s.setCognome(rs.getString("cognome"));
            s.setSalarioMedio(rs.getInt("SalarioMedio"));
            s.setValutazione(rs.getString("Valutazione"));
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	
	public List<Sviluppatore> getAllSviluppatoriProgetto (String codfiscale) throws SQLException {
		getAllSviluppatoriProgettoPS.setString(1, codfiscale);
        ResultSet rs= getAllSviluppatoriProgettoPS.executeQuery();
        List<Sviluppatore> lista = new ArrayList<Sviluppatore>();
        while(rs.next())
        {
        	Sviluppatore s = new Sviluppatore(rs.getString("codFiscale")); 
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	
	public List<Sviluppatore> getAllSviluppatoriProgettoEMeeting (String codfiscale) throws SQLException {
		getAllSviluppatoriProgettoEMeeting.setString(1, codfiscale);
		getAllSviluppatoriProgettoEMeeting.setString(2, codfiscale);
        ResultSet rs= getAllSviluppatoriProgettoEMeeting.executeQuery();
        List<Sviluppatore> lista = new ArrayList<Sviluppatore>();
        while(rs.next())
        {
        	Sviluppatore s = new Sviluppatore(rs.getString("codFiscale")); 
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	
	@Override
	public int  inserisciValutazione(String valutazione, String codfiscale) throws SQLException {
		inserisciValutazionePS.setString(1, valutazione);
		inserisciValutazionePS.setString(2, codfiscale);
        int row = inserisciValutazionePS.executeUpdate();
        return row;
	}
	
	@Override
	public List<Sviluppatore> getPartecipantiProgettoPS(String nomeprogetto) throws SQLException{
		getPartecipantiProgettoPS.setString(1,nomeprogetto);
        ResultSet rs= getPartecipantiProgettoPS.executeQuery();
        List<Sviluppatore> lista = new ArrayList<Sviluppatore>();
        while(rs.next())
        {
        	Sviluppatore s = new Sviluppatore(rs.getString("codFiscale")); 
            s.setNome(rs.getString("nome"));
            s.setCognome(rs.getString("cognome"));
            s.setSalarioMedio(rs.getInt("SalarioMedio"));
            s.setValutazione(rs.getString("Valutazione"));
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	
	
}
