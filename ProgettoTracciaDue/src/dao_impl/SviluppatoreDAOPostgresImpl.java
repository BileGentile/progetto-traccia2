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
		private PreparedStatement getSviluppatoreByCodFiscalePS,inserisciSviluppatorePS,inserisciSkillSviluppatorePS,getSviluppatoreBySalarioESkillsEValutazionePS, getAllSviluppatoriProgettoPS, inserisciValutazionePS,getPartecipantiProgettoPS, getSviluppatoreBySalarioESkillsPS;
	
	public SviluppatoreDAOPostgresImpl (Connection connection) throws SQLException  {
		this.connection=connection;
		getSviluppatoreByCodFiscalePS = connection.prepareStatement("SELECT * FROM sviluppatore WHERE codFiscale LIKE ? AND ruolo LIKE 'Sviluppatore' ");
		inserisciSviluppatorePS = connection.prepareStatement("INSERT INTO sviluppatore VALUES (?,?,UPPER(?), 'Sviluppatore', ?,?)");
		inserisciSkillSviluppatorePS= connection.prepareStatement("INSERT INTO associazioneskillssviluppatore (codfiscale, codskills)  SELECT ?, S.CodSkills FROM skills AS S WHERE S.nomeskill=?;");
		getSviluppatoreBySalarioESkillsEValutazionePS = connection.prepareStatement("(SELECT *\r\n"
				+ "FROM SVILUPPATORE\r\n"
				+ "WHERE  salariomedio > ?\r\n"
				+ "AND ruolo LIKE 'Sviluppatore' \r\n"
				+ "AND valutazione LIKE ? \r\n"
				+ "AND codFiscale IN ((SELECT DISTINCT codfiscale \r\n"
				+ "						FROM skills AS S \r\n"
				+ "						Where S.nomeskill LIKE ?)\r\n"
				+ "						except\r\n"
				+ "						(select codfiscale\r\n"
				+ "						from partecipazioniprogetto\r\n"
				+ "						where codprogetto like ?)));");
		
		getSviluppatoreBySalarioESkillsPS=connection.prepareStatement("(SELECT *\r\n"
				+ "FROM SVILUPPATORE\r\n"
				+ "WHERE  salariomedio > ?\r\n"
				+ "AND ruolo LIKE 'Sviluppatore' \r\n"
				+ "AND valutazione is null\r\n"
				+ "AND codFiscale IN ((SELECT DISTINCT codfiscale \r\n"
				+ "					FROM skills AS S \r\n"
				+ "					Where S.nomeskill LIKE ?)\r\n"
				+ "					except\r\n"
				+ "					(select codfiscale\r\n"
				+ "					from partecipazioniprogetto\r\n"
				+ "					where codprogetto like ?)));");
		
		getAllSviluppatoriProgettoPS=connection.prepareStatement("select distinct codfiscale\n"
				+ "from partecipazioniprogetto\n"
				+ "where codprogetto in (SELECT codprogetto\n"
				+ "					   FROM progetto \n"
				+ "					   WHERE codFiscale LIKE ?);");
		inserisciValutazionePS = connection.prepareStatement("UPDATE SVILUPPATORE SET valutazione  = ? WHERE codfiscale LIKE ?");
		getPartecipantiProgettoPS = connection.prepareStatement("select s.nome, s.cognome, s.codfiscale, s.ruolo, s.salariomedio, s.valutazione\r\n"
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
	public List<Sviluppatore> getSviluppatoreBySalarioESkillsEValutazionePS(int salario, String skills, String valutazione, String progetto) throws SQLException{
		getSviluppatoreBySalarioESkillsEValutazionePS.setInt(1, salario);
		getSviluppatoreBySalarioESkillsEValutazionePS.setString(2, valutazione);
		getSviluppatoreBySalarioESkillsEValutazionePS.setString(3, skills);
		getSviluppatoreBySalarioESkillsEValutazionePS.setString(4, progetto);
        ResultSet rs= getSviluppatoreBySalarioESkillsEValutazionePS.executeQuery();
        List<Sviluppatore> lista = new ArrayList<Sviluppatore>();
        while(rs.next())
        {
        	Sviluppatore s = new Sviluppatore(rs.getString("codFiscale")); 
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
	public List<Sviluppatore> getSviluppatoreBySalarioESkillsPS (int salario, String skills, String progetto) throws SQLException{
		getSviluppatoreBySalarioESkillsPS.setInt(1, salario);
		getSviluppatoreBySalarioESkillsPS.setString(2, skills);
		getSviluppatoreBySalarioESkillsPS.setString(3, progetto);
        ResultSet rs= getSviluppatoreBySalarioESkillsPS.executeQuery();
        List<Sviluppatore> lista = new ArrayList<Sviluppatore>();
        while(rs.next())
        {
        	Sviluppatore s = new Sviluppatore(rs.getString("codFiscale")); 
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
            s.setRuolo(rs.getString("ruolo"));
            s.setSalarioMedio(rs.getInt("SalarioMedio"));
            s.setValutazione(rs.getString("Valutazione"));
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	
	
}
