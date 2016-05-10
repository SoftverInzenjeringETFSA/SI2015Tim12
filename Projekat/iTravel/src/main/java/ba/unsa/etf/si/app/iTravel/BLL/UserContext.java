package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.KorisnickiRacun;
import ba.unsa.etf.si.app.iTravel.DBModels.Korisnickiracunxrola;

public class UserContext {
	
	private static UserContext instance = null;
	private static DBContext baza;
	
	private static String Username;
	private static int Identitfication;
	private static boolean Authenticated;
	private static int RolaID;
	
	private UserContext()
	{
		baza = new DBContext();
	}
	
	public static UserContext getInstance() 
	{
		if(instance == null)
			instance = new UserContext();
		
		return instance;
	}
	
	public String getUsername()
	{
		return Username;
	}
	
	public int getIdentification()
	{
		return Identitfication;
	}
	
	public boolean isAuthenticated()
	{
		return Authenticated;
	}
	
	public int getRoleID()
	{
		return RolaID;
	}
	
	public static boolean logUserIn(String username)
	{
		if(username == Username && Authenticated == true)
			return false;
		
		Username = username; // Postavljanje usernamea
		
		ArrayList<Criterion> listaKriterjona = new ArrayList<Criterion>();
		listaKriterjona.add(Restrictions.eq("username", (String)username));
		
		KorisnickiRacun korisnickiRacun = new KorisnickiRacun();
		
		korisnickiRacun = baza
				 .getKorisnickiRacunRepository()
				 .ucitajIzBazePoKriteriju(listaKriterjona).get(0);
		
		Identitfication = korisnickiRacun.getKorisnickiRacunId(); // Postavljanje ID-ja usera
		
		Set<Korisnickiracunxrola> skupRola = korisnickiRacun.getKorisnickiracunxrolas();
		
		for (Iterator<Korisnickiracunxrola> it = skupRola.iterator(); it.hasNext(); ) {
	        Korisnickiracunxrola r = it.next();
	        
	        if(r != null)
	        {
	        	RolaID = r.getRola().getRolaId(); // Postavljanje roleID za usera
	        	break; 
	        	// sad za sad predpostavljamo da imamo samo jednu rolu
	        	// za jednog korisnika
	        }
	    }
		
		Authenticated = true; // Postavljanje parametra da je autentifikovan
		
		return true;
	}
	
	public static void logUserOut()
	{
		Username = null;
		RolaID = -1;
		Identitfication = -1;
		Authenticated = false;
	}
}
