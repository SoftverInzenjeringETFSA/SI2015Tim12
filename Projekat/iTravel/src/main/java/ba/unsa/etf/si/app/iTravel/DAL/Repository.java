package ba.unsa.etf.si.app.iTravel.DAL;
/*
import ba.unsa.etf.si.tim11.dbmodels.BaseDbModel;
import ba.unsa.etf.si.tim11.dbmodels.KorisnikDbModel;*/
import ba.unsa.etf.si.app.iTravel.DAL.SessionFactoryDB;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static ba.unsa.etf.si.app.iTravel.DAL.SessionFactoryDB.getSession;

import java.util.List;
import java.util.logging.Logger;

public class Repository<T>{
    private static final Logger logger =
            Logger.getLogger(Repository.class.getName());

    private static final String DELETE = "DELETE";
    private static final String FROM = "FROM";
    private static final String SPACE = " ";
    private static final String PARAMETER = "?";

    private Class<T> entityClass;
    private final Session session;
    
    /**
     * Kreiranje repositorija za datu klasu
     * @param entityClass predstavlja klasu repozitorija
     */
    public Repository(Class<T> entityClass) {
        this.entityClass = entityClass;
        session = SessionFactoryDB.getSession();
    }

    /**
     * Ucitaj sve objekte iz baze
     * @param session Hibernate sesija
     * @return Lista svih objekata
     */
    
    public List<T> ucitajSveIzBaze(Session session) {
        Transaction t = session.beginTransaction();
        String queryString = FROM + SPACE + PARAMETER;
        Query query = session.createQuery(queryString);
        query.setString(0, entityClass.getCanonicalName());
        List<T> resultObjects = query.list();
        t.commit();
        return resultObjects;
    }

    /**
     * Ucitaj jedan objekat iz baze za proslijedjeni ID
     * @param id Id objekta
     * @param session Hibernate sesija
     * @return Trazeni objekat ili null ako ga nema
     */
    public T ucitajIzBaze(Integer id) {
        Transaction t = this.session.beginTransaction();
        T resultObject = (T) this.session.get(entityClass, id);
        session.close();
        return resultObject;
    }

    /**
     * Spasavanje objekta u bazu
     * @param object Objekat koji se spasava
     * @param session Hibernate sesija
     */
    public void spasiUBazu(T object, Session session) {
        Transaction t = session.beginTransaction();
        session.save(object);
        t.commit();
    }

    /**
     * Spasavanje objekta u bazu ili azuriranje ako objekat sa istim id-em vec postoji
     * @param object Objekat koji se spasava
     * @param session Hibernate sesija
     */
    public void sacuvajIliAzurirajUBazu(T object, Session session) {
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(object);
        t.commit();
    }


    /**
     * Obrisi jedan objekat iz baze
     * @param object Objekat koji se brise
     * @param session Hibernate sesija
     */
    public void obrisiIzBaze(T object, Session session) {
        Transaction t = session.beginTransaction();
        session.delete(object);
        t.commit();
    }

    /**
     * Obrisi sve objekte
     * @param session Hibernate sesija
     */
    public void obrisiSveIzBaze(Session session) {
        Transaction t = session.beginTransaction();
        String queryString = DELETE + SPACE + FROM + PARAMETER;
        Query query = session.createQuery(queryString);
        query.setString(0, entityClass.getCanonicalName());
        query.executeUpdate();
        t.commit();
    }
}