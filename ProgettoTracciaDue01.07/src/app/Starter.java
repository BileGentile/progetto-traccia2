package app;

import dao_impl.MembroDAOPostgresImpl;
import daos.MembroDAO;
import daos.ProgettoDAO;
import dbConfig.DBBuilder;
import dbConfig.DBConnection;
import entity.Membro;
import exceptions.ConnectionException;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;

public class Starter {
	public static void main(String[] args)
    {
        DBConnection dbconn = null;
        Connection connection = null;
        DBBuilder builder = null;
        Controller controller = null;
  
        
        
        
        try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            builder.createSequenceProgetto();
            builder.createSequenceMembri();
            controller = new Controller();
          
           
            
            MembroDAO dao = null;
            ProgettoDAO daoo = null;
           
            dao = new MembroDAOPostgresImpl(connection);
            
            //TEST PER IL DATABASE, SIMULO L'INSERIMENTO DI TRE MEMBRI, TOGLI COMMENTO PER TESTARE
            
//            Membro m1  =  new Membro("Mario", "Biondi", "MROVRD77L99K776J", "Schifo", "ProjectManager");
//            Membro m2  =  new Membro("Pino", "Verdi", "PNOVRD77L99K776J", "Bravo", "Sviluppatore");
//            Membro m3  =  new Membro("Rino", "Ceronte", "RNOVRD77L99K775P", "Eccellente", "Sviluppatore");
//            int res =  dao.inserisciMembro(m1);
//            System.out.println(res);
//            int res2 = dao.inserisciMembro(m2);
//            System.out.println(res2);
//            int res3 = dao.inserisciMembro(m3);
//            System.out.println(res3);
//        
        }
        catch (SQLException exception)
        {
            System.out.println("Errore SQLException: "+ exception.getMessage());
        }
        catch (ConnectionException ex)
        {
            System.out.println("CE: "+ex);
        }

    }

}
