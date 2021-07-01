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
	private PreparedStatement inserisciSkillsPS;
	public SkillsDAOPostgresImpl (Connection connection) throws SQLException{
		this.connection=connection;
		inserisciSkillsPS = connection.prepareStatement("INSERT INTO Skills VALUES (?, ?)");
	}
	@Override
	public List<Skills> getAllSkills() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Skills> getSkillByCodFiscale(String codFiscale) throws SQLException {
		
		return null;
	}
	@Override
	public int inserisciSkills(Skills skills) throws SQLException {
		inserisciSkillsPS.setString(1, skills.getSkill());
	    inserisciSkillsPS.setString(2, skills.getCodFiscale());
	    int row = inserisciSkillsPS.executeUpdate();
	    System.out.print(row); 

		return row;
	
}
	
	@Override
	public int inserisciCodFiscale(String codFiscale) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}