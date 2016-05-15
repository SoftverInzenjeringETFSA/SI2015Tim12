package ba.unsa.etf.si.app.iTravel.DAL;

import ba.unsa.etf.si.app.iTravel.DAL.SessionFactoryDB;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import java.util.List;
//import java.util.logging.Logger;

public class Repository<T>{
    /*private static final Logger logger =
            Logger.getLogger(Repository.class.getName());*/

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
    
    public List<T> ucitajSveIzBaze() {

    	Session novaSesija = SessionFactoryDB.getSession();
    	Transaction transaction = novaSesija.beginTransaction();
    	transaction.begin();
    	List<T> lista = novaSesija.createCriteria(entityClass).list();
        
    	transaction.commit();
        novaSesija.close();
        return (List<T>)lista;
        
        // BACKUP KOD
     	//List<T> lista = this.session.createCriteria(entityClass).list();
        //return (List<T>)lista;
    }

    /**
     * Ucitaj jedan objekat iz baze za proslijedjeni ID
     * @param id Id objekta
     * @param session Hibernate sesija
     * @return Trazeni objekat ili null ako ga nema
     */
    public T ucitajIzBaze(Integer id) {
    	Session novaSesija = SessionFactoryDB.getSession();
    	Transaction transaction = novaSesija.beginTransaction();
    	
    	transaction.begin();
    	T resultObject = (T) novaSesija.get(entityClass, id);
    	transaction.commit();
    	
    	novaSesija.close();
    	
    	return resultObject;
    	
        /*Transaction t = this.session.beginTransaction();
        T resultObject = (T) this.session.get(entityClass, id);
        session.clear();
        return resultObject;*/
    }

    /**
     * Spasavanje objekta u bazu
     * @param object Objekat koji se spasava
     * @param session Hibernate sesija
     */
    public T spasiUBazu(T object) {
    	
        Session novaSesija = SessionFactoryDB.getSession();
        Transaction transaction = novaSesija.beginTransaction();
        
        transaction.begin();
        novaSesija.save(object);
        transaction.commit();
        
        novaSesija.close();
        
        return object;
    	
        /*Transaction t = session.beginTransaction();
        session.save(object);
        t.commit();
        return object;*/
    }

    /**
     * Spasavanje objekta u bazu ili azuriranje ako objekat sa istim id-em vec postoji
     * @param object Objekat koji se spasava
     * @param session Hibernate sesija
     */
    public T sacuvajIliAzurirajUBazu(T object) {
        Session novaSesija = SessionFactoryDB.getSession();
        Transaction transaction = novaSesija.beginTransaction();
        
        transaction.begin();
        novaSesija.saveOrUpdate(object);
        transaction.commit();
        
        novaSesija.close();
        
        return object;
    	
    	/*Transaction t = session.beginTransaction();
        session.saveOrUpdate(object);
        t.commit();
        return object;*/
    }


    /**
     * Obrisi jedan objekat iz baze
     * @param object Objekat koji se brise
     * @param session Hibernate sesija
     */
    public void obrisiIzBaze(T object) {
    	Transaction t = session.beginTransaction();
        session.delete(object);
        t.commit();
    }

    /**
     * Obrisi sve objekte
     * @param session Hibernate sesija
     */
    public void obrisiSveIzBaze() {
        Transaction t = session.beginTransaction();
        String queryString = DELETE + SPACE + FROM + PARAMETER;
        Query query = session.createQuery(queryString);
        query.setString(0, entityClass.getCanonicalName());
        query.executeUpdate();
        t.commit();
    }
    
    /* Učitavanje iz baze po kriteriju */
    public List<T> ucitajIzBazePoKriteriju(List<Criterion> listaKriterija) {
        Session novaSesija = SessionFactoryDB.getSession();
        Transaction transaction = novaSesija.beginTransaction();
        
        transaction.begin();
    	
        Criteria kriterja = novaSesija.createCriteria(entityClass);
        
        for(Criterion k : listaKriterija)
        {
        	kriterja.add(k);
        }
        
        List rezultat = kriterja.list();
        
        transaction.commit();
       
        novaSesija.close();
       
        return rezultat;
    	
    	//Transaction transaction = session.beginTransaction();
        /*Criteria kriterja = session.createCriteria(entityClass);
        
        for(Criterion k : listaKriterija)
        {
        	kriterja.add(k);
        }
        
        List rezultat = kriterja.list();
        //session.clear();
        
        return rezultat;*/
    }
}