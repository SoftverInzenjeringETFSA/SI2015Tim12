package ba.unsa.etf.si.app.iTravel.BLL;

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

import java.util.List;

public class PrijavaService {
	
	DBContext baza;
	
	public PrijavaService()
	{
		baza = DBContext.getInstance();
	}
	
	public boolean ProvjeriPristupnePodatke(String username, char[] password)
	{
		ArrayList<Criterion> listaKriteriona = new ArrayList<Criterion>();
		listaKriteriona.add(Restrictions.eq("username", (String)username));
		
		KorisnickiRacun korisnickiRacun = new KorisnickiRacun();
		
		List<KorisnickiRacun> lista = baza
				 .getKorisnickiRacunRepository()
				 .ucitajIzBazePoKriteriju(listaKriteriona);
		if(lista.size() <= 0)
			return false;
		else
			korisnickiRacun = lista.get(0);

		if(korisnickiRacun == null)
			return false;
		
		if(Arrays.equals(password, korisnickiRacun.getPassword().toCharArray()))
		{
			// pristupni podaci korinika tačni
			// logovanje korisnika u aplikaciju
			return UserContext.getInstance().logUserIn(username);
		}
		
		return false;
	}

}
