package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.List;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.KorisnickiRacun;
import ba.unsa.etf.si.app.iTravel.DBModels.Korisnickiracunxrola;

public class KorisnickiRacunService
{
	DBContext baza = new DBContext();
	
	public KorisnickiRacun KreirajKorisnickiRacun(KorisnickiRacun korisnickiRacun)
	{
		return baza.getKorisnickiRacunRepository().spasiUBazu(korisnickiRacun);
	}
	
	public Korisnickiracunxrola KreirajRoluZaKorisnika(Korisnickiracunxrola korisnickiracunxrola)
	{
		return baza.getKorisnickiRacunXRolaRepository().spasiUBazu(korisnickiracunxrola);
	}
	
	public String Validiraj(KorisnickiRacun korisnickiRacun)
	{
		String rezultat = "";
		
		if(korisnickiRacun.getUsername().isEmpty())
		{
			rezultat = rezultat + "Polje username ne smije biti prazno<br />";
		}
		if(korisnickiRacun.getPassword().isEmpty())
		{
			rezultat = rezultat + "Polje šifra ne smije biti prazno<br />";
		}
		
		List<KorisnickiRacun> listaKorisnika = baza.getKorisnickiRacunRepository().ucitajSveIzBaze();
	
		for (KorisnickiRacun kr : listaKorisnika) 
		{
			if(kr.getUsername().equals(korisnickiRacun.getUsername()))
			{
				rezultat = rezultat + "Uneseni username već postoji molimo unesite drugi!<br />";
				break;
			}		
		}
		
		return rezultat;
	}
}
