package entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao_impl.SviluppatoreDAOPostgresImpl;
import daos.SkillsDAO;
import daos.SviluppatoreDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import exceptions.ConnectionException;

public class Sviluppatore extends Membro {
	
	private String Valutazione;

	public Sviluppatore(String CF) {
		super(CF);
	}

	public Sviluppatore(String nome, String cognome, String cF, int salarioMedio, String valutazione) {
		super(nome, cognome, cF, salarioMedio);
		Valutazione = valutazione;
	}

	public String getValutazione() {
		return Valutazione;
	}

	public void setValutazione(String valutazione) {
		Valutazione = valutazione;
	}

	//METODI
	
	//verifica se il codice fiscale inserito dal project manager risulta corretto, se lo è avvia il benvenuto altrimenti da un messaggio di errore
	public List<Sviluppatore> accediS(String codiceFiscale) {
		DBConnection dbconn = null;
        Connection connection = null;
        DBBuilder builder = null;
        List<Sviluppatore> lista = null;
        try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            SviluppatoreDAO dao = null;
            dao = new SviluppatoreDAOPostgresImpl(connection);
         
            lista = dao.getSviluppatoreByCodFiscale(codiceFiscale);
           
        }
        catch (SQLException exception)
        {
            System.out.println("Errore SQLException: "+ exception.getMessage());
        }
        return lista;
	}

	//Creazione di un nuovo sviluppatore 
	public boolean RegistraS(String cognome,String nome, String codfiscale, String salario, List<String> list ){
		boolean errore=false;
		
		DBConnection dbconn = null;
        Connection connection = null;
        DBBuilder builder = null;

        try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            builder.createTableSviluppatore();
            builder.createTableSkills();
            builder.createTableAssociazioneSkillsSviluppatore();
            
            SviluppatoreDAO daoSviluppatore = null;
            SviluppatoreDAO daoSviluppatori = null;
            SkillsDAO daoSkill =null;

            daoSviluppatore = new  SviluppatoreDAOPostgresImpl(connection);
            daoSviluppatori = new SviluppatoreDAOPostgresImpl(connection);
            
            Sviluppatore m1  =  new Sviluppatore(nome, cognome, codfiscale, Integer.valueOf(salario), "NULL");
            Sviluppatore m2  =  new Sviluppatore(nome, cognome, codfiscale, Integer.valueOf(salario), "NULL");

            List<Sviluppatore> listaSConCf = daoSviluppatori.getSviluppatoreByCodFiscale(codfiscale);
            
            if(listaSConCf.isEmpty()) {
            	int res = daoSviluppatori.inserisciSviluppatore(m1);
            	int i= 0;
            		while (i<list.size()) {
            			String s1=list.get(i);         
            			int res2= daoSviluppatore.inserisciSkillSviluppatore(m1,s1);
            			i++;
        			}
            }
            else {
            	errore=true;
            }
        
        }
        
        catch (SQLException exception)
        {
            System.out.println("Errore SQLException: "+ exception.getMessage());
        } catch (ConnectionException e) {
        	  System.out.println("CE: "+e);
		}
		return errore;
        
	}

}
