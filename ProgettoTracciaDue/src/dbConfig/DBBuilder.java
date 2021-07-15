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
      
   public int createSequenceProgetto() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("sequenzacodiceprogetti")) {
    				String sql = "CREATE SEQUENCE sequenzacodiceprogetti " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                            "INCREMENT 1 " +
                            " START 1000 " +
                            " MINVALUE 1000 " +
                            " MAXVALUE 99999;";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La sequenza codice progetti esiste già!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella creazione della sequenza codice progetti: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
   public int createSequenceMembri() throws ConnectionException
    {
    	int result= -1;
    	
    	if(connectionExists()) {
    		try {
    			Statement st = connection.createStatement();
    			
    			if(!tableExists("sequenzacodicemembri")) {
    				String sql = "CREATE SEQUENCE sequenzacodicemembri " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                            "INCREMENT 1 " +
                            " START 1000 " +
                            " MINVALUE 1000 " +
                            " MAXVALUE 99999;";
    				result = st.executeUpdate(sql);
    				st.close();
    			} else {
    				System.out.println("La sequenza codice membri esiste già!");
    			}
    		} catch(SQLException ex) {
    			System.out.println("SQL Exception nella creazione della sequenza codice membri: "+ex);
    		}
    	} else {
    		throw new ConnectionException("A connection must exist!");
    	}
    	return result;
    }
    
   public int createSequenceMeeting() throws ConnectionException
   {
	   int result= -1;
   	
   	if(connectionExists()) {
   		try {
   			Statement st = connection.createStatement();
   			if(!tableExists("sequenzacodicemeeting")) {
   				String sql = "CREATE SEQUENCE sequenzacodicemeeting " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
                           "INCREMENT 1 " +
                           " START 1000 " +
                           " MINVALUE 1000 " +
                           " MAXVALUE 99999;";
   				result = st.executeUpdate(sql);
   				st.close();
   			} 
   			else {
   				System.out.println("La sequenza codice meeting esiste già!");
   			}
   		} catch(SQLException ex) {
   			System.out.println("SQL Exception nella creazione della sequenza codice meeting: "+ex);
   		}
   	} else {
   		throw new ConnectionException("A connection must exist!");
   	}
   	return result;
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
    			System.out.println("SQL Exception nella crezione della tabella Membro: "+ex);
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
    			System.out.println("SQL Exception nella crezione della tabella Progetto: "+ex);
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
  			
  			if(!tableExists("Meeting")) {//DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
  				String sql = "CREATE TABLE Meeting (\n"
  						+ "codiceMeeting VARCHAR(255) NOT NULL,\n"
  						+ "data VARCHAR(200)  NOT NULL,\n"
  						+ "oraInizio VARCHAR CHECK (oraInizio ~* '^[0-9][0-9]:[0-9][0-9]:[0-9][0-9]') NOT NULL,\n"
  						+ "piattaforma VARCHAR,\n"
  						+ "tipologia VARCHAR CHECK (tipologia LIKE 'Fisico' OR tipologia LIKE 'Telematico'),\n"
  						+ "nomeSala VARCHAR,\n"
  						+ "organizzatore VARCHAR(16) CHECK (organizzatore  ~* '^[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][0-9][0-9][A-Z][0-9][0-9][A-Z][0-9][0-9][0-9][A-Z]'),\n"
  						+ "durata INTEGER NOT NULL,\n"
  						+ "PRIMARY KEY (codiceMeeting));";
  				result = st.executeUpdate(sql);
  				st.close();
  			} else {
  				System.out.println("La tabella Meeting esiste già!");
  			}
  		} catch(SQLException ex) {
  			System.out.println("SQL Exception in creation table Meeting: "+ex);
  		}
  	} else {
  		throw new ConnectionException("A connection must exist!");
  	}
  	return result;
  }

   public int createTableSkills() throws ConnectionException
{
	int result= -1;
	
	if(connectionExists()) {
		try {
			Statement st = connection.createStatement();
			
			if(!tableExists("skills")) {
				String sql = "CREATE TABLE Skills " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
						 "(skill VARCHAR(100) not NULL, " +
                        "codFiscale VARCHAR(100) not NULL, " +
                        " PRIMARY KEY (codFiscale, skill));";
				
				result = st.executeUpdate(sql);
				st.close();
			} else {
				System.out.println("La tabella Skills esiste già!");
			}
		} catch(SQLException ex) {
			System.out.println("SQL Exception nella crezione della tabella Skills: "+ex);
		}
	} else {
		throw new ConnectionException("A connection must exist!");
	}
	return result;
}

   public int createTableArchivioPartecipantiProgetto() throws ConnectionException
{
	int result= -1;
	
	if(connectionExists()) {
		try {
			Statement st = connection.createStatement();
			
			if(!tableExists("ArchivioPartecipantiProgetto")) {
				String sql = "CREATE TABLE ArchivioPartecipantiProgetto " +        //DA RIFARE CON TUTTI GLI ATTRIBUTI E CON UNA QUERY PIU' PRECISA
						 "(codFiscale VARCHAR(100) not NULL, " +
                        "NomeProgetto VARCHAR(100) not NULL, " +
						"Ruolo VARCHAR CHECK (ruolo LIKE 'ProjectManager' OR  ruolo LIKE 'Sviluppatore'),"+
                        " PRIMARY KEY (codFiscale, NomeProgetto));";
				
				result = st.executeUpdate(sql);
				st.close();
			} else {
				System.out.println("La tabella ArchivioPartecipantiProgetto esiste già!");
			}
		} catch(SQLException ex) {
			System.out.println("SQL Exception nella crezione della tabella ArchivioPartecipantiProgetto: "+ex);
		}
	} else {
		throw new ConnectionException("A connection must exist!");
	}
	return result;
}


}