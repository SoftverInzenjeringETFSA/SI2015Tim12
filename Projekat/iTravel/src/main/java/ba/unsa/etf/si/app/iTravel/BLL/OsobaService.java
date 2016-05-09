package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.service.spi.Stoppable;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;

public class OsobaService
{
	DBContext baza = new DBContext();

	public Osoba KreirajOsobu(Osoba osoba)
	{
		return baza.getOsobaRepository().spasiUBazu(osoba);
	}
	
	public String Validiraj(Osoba osoba)
	{
		String rezultat = "";
		
		if(osoba.getIme().isEmpty())
		{
			rezultat = rezultat + "Polje ime ne smije biti prazno<br />";
		}
		if(osoba.getPrezime().isEmpty())
		{
			rezultat = rezultat + "Polje prezime ne smije biti prazno<br />";
		}
		if(osoba.getJmbg() == null)
		{
			rezultat = rezultat + "Polje jmb ne smije biti prazno<br />";
		}
		if(osoba.getAdresa().isEmpty())
		{
			rezultat = rezultat + "Polje adresa ne smije biti prazno<br />";
		}
		if(osoba.getBrojLicneKarte().isEmpty())
		{
			rezultat = rezultat + "Polje broj licne karte ne smije biti prazno<br />";
		}
		if(osoba.getBrojTelefona().isEmpty())
		{
			rezultat = rezultat + "Polje broj telefona ne smije biti prazno<br />";
		}
		if(osoba.getEmail().isEmpty())
		{
			rezultat = rezultat + "Polje email ne smije biti prazno<br />";
		}
		
		return rezultat;
		
	}
}
