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

        try
        {
            dbconn = DBConnection.getInstance();
            connection = dbconn.getConnection();
            builder = new DBBuilder(connection);
            builder.createTableMembro();
            builder.createTableProgetto();
            
            MembroDAO dao = null;
            ProgettoDAO daoo = null;
            
            dao = new MembroDAOPostgresImpl(connection);
            
            //TEST PER IL DATABASE, SIMULO L'INSERIMENTO DI TRE MEMBRI, TOGLI COMMENTO PER TESTARE
            
            /*Membro m1  =  new Membro("Mario", "Biondi", "BNDMRA66H06F839L", "Schifo");
            Membro m2  =  new Membro("Pino", "Verdi", "PNOVRD", "Bravo");
            Membro m3  =  new Membro("Rino", "Ceronte", "RNOCRNT", "Eccellente");
            int res =  dao.inserisciMembro(m1);
            System.out.println(res);
            int res2 = dao.inserisciMembro(m2);
            System.out.println(res2);
            int res3 = dao.inserisciMembro(m3);
            System.out.println(res3);

            List<Membro> lista = dao.getMembroByNome("P%");
            
            for(Membro mm : lista)
            {
                System.out.println(mm.toString());
            }*/

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
