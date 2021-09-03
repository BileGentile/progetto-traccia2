package dao_impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daos.ProjectManagerDAO;
import entity.ProjectManager;
import entity.Skills;
import entity.Sviluppatore;

public class ProjectManagerDAOPostgresImpl implements ProjectManagerDAO {

	private Connection connection;
	private PreparedStatement getProjectManagerByCodFiscalePS,inserisciProjectManagerPS,inserisciSkillProjectManagerPS;
	
	public ProjectManagerDAOPostgresImpl(Connection connection) throws SQLException {
		this.connection=connection;
		getProjectManagerByCodFiscalePS = connection.prepareStatement("SELECT * FROM projectmanager WHERE codFiscale LIKE UPPER(?) AND ruolo LIKE 'ProjectManager' ");
		inserisciProjectManagerPS = connection.prepareStatement("INSERT INTO projectmanager VALUES (?,?,UPPER(?), 'ProjectManager', ?,?)");
		inserisciSkillProjectManagerPS=connection.prepareStatement("INSERT INTO associazioneskillsprojectmanager VALUES (?,?)");
		
	}

	@Override
	public List<ProjectManager> getProjectManagerByCodFiscale(String codfiscale) throws SQLException {
		getProjectManagerByCodFiscalePS.setString(1, codfiscale);
        ResultSet rs= getProjectManagerByCodFiscalePS.executeQuery();
        List<ProjectManager> lista = new ArrayList<ProjectManager>();
        while(rs.next())
        {
            ProjectManager s = new ProjectManager(rs.getString("codFiscale")); //rs.getString(1)
            s.setNome(rs.getString("nome"));
            s.setCognome(rs.getString("cognome"));
            s.setRuolo(rs.getString("ruolo"));
            lista.add(s);
        }
        rs.close();
        return lista;
	}
	
	@Override
	public int inserisciProjectManager(ProjectManager membro) throws SQLException {
		inserisciProjectManagerPS.setString(1, membro.getNome());
		inserisciProjectManagerPS.setString(2, membro.getCognome());
		inserisciProjectManagerPS.setString(3, membro.getCF());
		inserisciProjectManagerPS.setInt(4, membro.getSalarioMedio());
		inserisciProjectManagerPS.setString(5, membro.getValutazione());
        int row = inserisciProjectManagerPS.executeUpdate();
        System.out.print(row); 
        return row;
	}
	
	@Override
	public int inserisciSkillProjectManager(ProjectManager membro, Skills skill) throws SQLException {
		inserisciSkillProjectManagerPS.setString(1, membro.getCF());
		inserisciSkillProjectManagerPS.setString(2, skill.getCodSkill());
        int row = inserisciSkillProjectManagerPS.executeUpdate();
        System.out.print(row); 
        return row;
	}
}
