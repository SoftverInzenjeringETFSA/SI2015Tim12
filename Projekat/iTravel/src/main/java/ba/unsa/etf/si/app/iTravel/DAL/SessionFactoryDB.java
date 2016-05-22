package ba.unsa.etf.si.app.iTravel.DAL;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class SessionFactoryDB {
    private static final Logger logger =
            Logger.getLogger(SessionFactoryDB.class.getName());

    private static final SessionFactory ourSessionFactory;

    static {
        try {
        	java.util.Properties properties = new Properties();
			properties.load(new FileInputStream("db.properties"));
			
            ourSessionFactory= new Configuration().addProperties(properties).configure().buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    private SessionFactoryDB(){ 	
    }
    
    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

}
