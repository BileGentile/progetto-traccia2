package dbConfig;

import exceptions.ConnectionException;

import java.sql.*;

public class DBBuilder
{
    private Connection connection;

    public DBBuilder(Connection connection)
    {
        this.connection = connection;
    }

    public DBBuilder()
    {
        connection = null;
    }

    private boolean connectionExists() {
        return !(connection == null);
    }

    private boolean tableExists(String tbl_name) throws SQLException
    {
        DatabaseMetaData metadata = connection.getMetaData();
        ResultSet tables = metadata.getTables(null, null, tbl_name, null);
        if (tables.next())
            return true;
        return false;

    }
    
    public int createTableMembro() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("membro")) {
    				String sql = "CREATE TABLE membro " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                            "(nome VARCHAR(255) not NULL, " +
                            " cognome VARCHAR(255) not NULL, " +
                            " codFiscale VARCHAR(16) CHECK (codFiscale  ~* '^[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][0-9][0-9][A-Z][0-9][0-9][A-Z][0-9][0-9][0-9][A-Z]'), " +
                            " valutazioneAziendale VARCHAR(255), "+
                            " ruolo VARCHAR CHECK (ruolo LIKE 'ProjectManager' OR  ruolo LIKE 'Sviluppatore'),"+
    						" PRIMARY KEY (codFiscale));";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Membro esiste già!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception in creation table Membro: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }

    
    
	public int createTableProgetto() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("progetto")) {
    				String sql = "CREATE TABLE progetto " +        //DA RIVEDERE
                            "(nome VARCHAR(255) not NULL, " +
                            " tipo VARCHAR(255) not NULL  CHECK (tipo LIKE 'Ricerca di base' OR tipo LIKE 'Ricerca industruale' OR tipo LIKE 'Ricerca sperimentale' OR tipo LIKE 'Sviluppo sperimentale'), " +
                            " ambito VARCHAR(255) not NULL  CHECK (ambito LIKE 'Informatico' OR ambito LIKE 'Medico' OR ambito LIKE 'Economico'),  " +
                            " codProgetto VARCHAR(255) PRIMARY KEY, " +
                            " stato VARCHAR(255) not NULL CHECK (stato LIKE 'Completo' OR stato LIKE 'Incompleto'));";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La tabella Progetto esiste già!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception in creation table Progetto: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
	}
	
}