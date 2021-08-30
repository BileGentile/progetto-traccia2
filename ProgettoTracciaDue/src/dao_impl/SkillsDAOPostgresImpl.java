package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.SkillsDAO;
import entity.Skills;

public class SkillsDAOPostgresImpl implements SkillsDAO {
	
	private Connection connection;
	private PreparedStatement inserisciSkillsPS, getAllSkillsPS;
	
	
	public SkillsDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;

		inserisciSkillsPS = connection.prepareStatement("INSERT INTO Skills VALUES (?, nextval(?))");
		getAllSkillsPS=connection.prepareStatement("SELECT DISTINCT nomeskill FROM SKILLS;");

	}

	@Override
	public List<Skills> getAllSkills() throws SQLException {
		ResultSet rs= getAllSkillsPS.executeQuery();
        List<Skills> lista = new ArrayList<Skills>();
        while(rs.next())
        {
            Skills s = new Skills(rs.getString("nomeSkill"));
         
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	
	@Override
	public int inserisciSkills(Skills skills) throws SQLException {
		inserisciSkillsPS.setString(1, skills.getNomeSkill());
		inserisciSkillsPS.setString(2, skills.getCodSkill());
	    int row = inserisciSkillsPS.executeUpdate();
	    System.out.print(row); 
		return row;
	}
	

}