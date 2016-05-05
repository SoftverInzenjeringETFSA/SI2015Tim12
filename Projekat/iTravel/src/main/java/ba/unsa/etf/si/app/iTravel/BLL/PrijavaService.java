package ba.unsa.etf.si.app.iTravel.BLL;

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork.UserContext;
import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.KorisnickiRacun;
import ba.unsa.etf.si.app.iTravel.DBModels.Korisnickiracunxrola;
//import ba.unsa.etf.si.app.iTravel.DAL.SessionFactoryDB;
//import ba.unsa.etf.si.app.iTravel.DAL.Repositories.KorisnickiRacunXRolaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class PrijavaService {
	
	DBContext baza;
	
	public PrijavaService()
	{
		baza = DBContext.getInstance();
	}
	
	public boolean ProvjeriPristupnePodatke(String username, char[] password)
	{
		ArrayList<Criterion> listaKriterjona = new ArrayList<Criterion>();
		listaKriterjona.add(Restrictions.eq("username", (String)username));
		
		KorisnickiRacun korisnickiRacun = new KorisnickiRacun();
		
		korisnickiRacun = baza
				 .getKorisnickiRacunRepository()
				 .ucitajIzBazePoKriteriju(listaKriterjona).get(0);
		
		if(korisnickiRacun == null)
			return false;
		
		if(Arrays.equals(password, korisnickiRacun.getPassword().toCharArray()))
			return true;
		
		return false;
	}
	
	public void AutorizirajKorisnika(String username)
	{
		ArrayList<Criterion> listaKriterjona = new ArrayList<Criterion>();
		listaKriterjona.add(Restrictions.eq("username", (String)username));
		
		KorisnickiRacun korisnickiRacun = new KorisnickiRacun();
		
		korisnickiRacun = baza
				 .getKorisnickiRacunRepository()
				 .ucitajIzBazePoKriteriju(listaKriterjona).get(0);
		
		UserContext.Username = username;
		UserContext.Identitfication = korisnickiRacun.getKorisnickiRacunId();
		
		Set<Korisnickiracunxrola> skupRola = korisnickiRacun.getKorisnickiracunxrolas();
		
		for (Iterator<Korisnickiracunxrola> it = skupRola.iterator(); it.hasNext(); ) {
	        Korisnickiracunxrola r = it.next();
	        
	        if(r != null)
	        {
	        	UserContext.RolaID = r.getRola().getRolaId();
	        	break; // sad za sad predpostavljamo da imamo samo jednu rolu
	        	// za jednog korisnika
	        }
	    }
		
	}

}
