package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.spi.Stoppable;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;

public class OsobaService
{
	DBContext baza = new DBContext();

	
	public ArrayList<Osoba> dajOsobuPoJMBG(String jmbg){
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		listaKriterija.add(Restrictions.eq("jmbg",jmbg));
		ArrayList<Osoba> s=new ArrayList<Osoba>();
		s=(ArrayList<Osoba>)baza.getOsobaRepository().ucitajIzBazePoKriteriju(listaKriterija);
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
	
	private boolean daLiPostojiJMB(Osoba osoba, boolean modifikacija)
	{
		ArrayList<Criterion> listaKriterija = new ArrayList<Criterion>();
		
		listaKriterija.add(Restrictions.eq("jmbg", osoba.getJmbg()));
		List<Osoba> listaOsobaSaJmbom;
		
		if(!modifikacija)
		{	
			listaOsobaSaJmbom = baza.getOsobaRepository().ucitajIzBazePoKriteriju(listaKriterija);
		}
		else
		{		
			listaKriterija.add(Restrictions.ne("osobaId", osoba.getOsobaId()));
			listaOsobaSaJmbom = baza.getOsobaRepository().ucitajIzBazePoKriteriju(listaKriterija);
		}
		
		System.out.println(listaOsobaSaJmbom.toString());
		
		if(listaOsobaSaJmbom.size() == 0) return false;
		
		return true;

	}
	
	public String Validiraj(Osoba osoba, boolean modifikacija)
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
		if(daLiPostojiJMB(osoba, modifikacija))
		{
			rezultat = rezultat + "Uneseni JMB već postoji<br />";
		}
		if(osoba.getJmbg().length() != 13)
		{
			rezultat = rezultat + "JMB mora imati tačno 13 cifara<br />";
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
	
	public void ObrisiJednuOsobu(Osoba osoba) 
	{
		baza.getOsobaRepository().obrisiIzBaze(osoba);
	}
}
