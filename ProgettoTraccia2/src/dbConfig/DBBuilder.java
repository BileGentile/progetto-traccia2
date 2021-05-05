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
                            "(nome VARCHAR(100) not NULL, " +
                            " cognome VARCHAR(100) not NULL, " +
                            " codFiscale VARCHAR(16) CHECK (codFiscale  ~* '^[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][0-9][0-9][A-Z][0-9][0-9][A-Z][0-9][0-9][0-9][A-Z]'), " +
                            " ruolo VARCHAR CHECK (ruolo LIKE 'ProjectManager' OR  ruolo LIKE 'Sviluppatore'),"+
                            " salarioMedio INTEGER not NULL,"+
                            " valutazione VARCHAR CHECK (valutazione LIKE 'Buona' OR  valutazione LIKE 'Mediocre' OR valutazione LIKE 'Male' OR  valutazione LIKE 'NULL' )," +
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
                            "(nome VARCHAR(255) not NULL UNIQUE, " +
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
	

public int createTableMeeting() throws ConnectionException
  {
  	int result= -1;
  	
  	if(connectionExists()) {
  		try {
  			Statement st = connection.createStatement();
  			
  			if(!tableExists("Meeting")) {
  				String sql = "CREATE TABLE Meeting " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                          " codiceMeeting VARCHAR(255) NOT NULL, " +
                          " data VARCHAR(10) CHECK (data  ~* '^[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]') NOT NULL, " +
                          " oraInizio VARCHAR CHECK (oraInizio ~* '^[0-9][0-9]:[0-9][0-9]') NOT NULL,"+
                          " piattaforma VARCHAR,"+
                          " tipologia VARCHAR CHECK (tipologia LIKE 'Fisico' OR tipologia LIKE 'Virtuale')," +
                          " nomeSala VARCHAR,"+
  						" PRIMARY KEY (codiceMeetingTelematico));";
  				result = st.executeUpdate(sql);
  				st.close();
  			} else {
  				System.out.println("La tabella MeetingTelematico esiste già!");
  			}
  		} catch(SQLException ex) {
  			System.out.println("SQL Exception in creation table MeetingTelematico: "+ex);
  		}
  	} else {
  		throw new ConnectionException("A connection must exist!");
  	}
  	return result;
  }

  
}