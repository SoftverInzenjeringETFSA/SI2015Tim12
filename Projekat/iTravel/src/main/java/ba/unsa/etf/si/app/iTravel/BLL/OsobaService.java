package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.spi.Stoppable;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;

public class OsobaService
{
	DBContext baza = new DBContext();

	
	public List<Osoba> dajOsobuPoJMBG(String jmbg){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("jmbg", (String)jmbg));
		List<Osoba> s=new ArrayList<Osoba>();
		s=baza.getOsobaRepository().ucitajIzBazePoKriteriju(listaKriterija);
		return s;
	}

	public ArrayList<Osoba> dajSveOsobe(){
		ArrayList<Osoba> k=(ArrayList<Osoba>)baza.getOsobaRepository().ucitajSveIzBaze();
		return  k;
	}
	
	public Osoba dajOsobuPoId(int idOsoba){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("osobaId", idOsoba));
		List<Osoba> k=baza.getOsobaRepository().ucitajIzBazePoKriteriju(listaKriterija);
		return k.get(0);
	}
	
	public Osoba KreirajOsobu(Osoba osoba, boolean  modifikacija)
	{
		if(modifikacija)
		{
			Osoba postojecaOsoba = baza.getOsobaRepository().ucitajIzBaze(osoba.getOsobaId());
			
			postojecaOsoba.setAdresa(osoba.getAdresa());
			postojecaOsoba.setBrojLicneKarte(osoba.getBrojLicneKarte());
			postojecaOsoba.setBrojPasosa(osoba.getBrojPasosa());
			postojecaOsoba.setBrojTelefona(osoba.getBrojTelefona());
			postojecaOsoba.setDatumRodjenja(osoba.getDatumRodjenja());
			postojecaOsoba.setEmail(osoba.getEmail());
			postojecaOsoba.setIme(osoba.getIme());
			postojecaOsoba.setPrezime(osoba.getPrezime());
			postojecaOsoba.setJmbg(osoba.getJmbg());
			
			return baza.getOsobaRepository().sacuvajIliAzurirajUBazu(postojecaOsoba);
		}
		else
		{
			return baza.getOsobaRepository().spasiUBazu(osoba);
		}
		
		//return baza.getOsobaRepository().sacuvajIliAzurirajUBazu(osoba);
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
