package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.KorisnickiRacun;
import ba.unsa.etf.si.app.iTravel.DBModels.Korisnickiracunxrola;
import ba.unsa.etf.si.app.iTravel.Forms.ModifikacijaHotela;

public class KorisnickiRacunService
{
	DBContext baza = new DBContext();
	
	public KorisnickiRacun KreirajKorisnickiRacun(KorisnickiRacun korisnickiRacun, boolean modifikacija)
	{
		if(modifikacija)
		{
			KorisnickiRacun postojeciKorisnickiRac = baza.getKorisnickiRacunRepository()
														 .ucitajIzBaze(korisnickiRacun.getKorisnickiRacunId());
			
			postojeciKorisnickiRac.setUsername(korisnickiRacun.getUsername());
			
			return baza.getKorisnickiRacunRepository().sacuvajIliAzurirajUBazu(postojeciKorisnickiRac);
		}
		else
		{
			return baza.getKorisnickiRacunRepository().spasiUBazu(korisnickiRacun);
		}
		
	}
	
	public Korisnickiracunxrola KreirajRoluZaKorisnika(Korisnickiracunxrola korisnickiracunxrola, boolean modifikacija)
	{
		if(modifikacija)
		{
			Korisnickiracunxrola postojecaKorisnickaRola = baza.getKorisnickiRacunXRolaRepository()
															   .ucitajIzBaze(korisnickiracunxrola.getKorisnickiRacunXrolaId());
			
			postojecaKorisnickaRola.setRola(korisnickiracunxrola.getRola());
			
			return baza.getKorisnickiRacunXRolaRepository().sacuvajIliAzurirajUBazu(postojecaKorisnickaRola);
		}
		else
		{
			return baza.getKorisnickiRacunXRolaRepository().spasiUBazu(korisnickiracunxrola);
			
		}

	}
	
	public KorisnickiRacun dajKorisnika(int id)
	{
		return baza.getKorisnickiRacunRepository().ucitajIzBaze(id);
	}
	
	public String Validiraj(KorisnickiRacun korisnickiRacun, boolean modifikacija)
	{
		String rezultat = "";
		
		if(korisnickiRacun.getUsername().isEmpty())
		{
			rezultat = rezultat + "Polje username ne smije biti prazno<br />";
		}
		if(korisnickiRacun.getPassword().isEmpty() && !modifikacija)
		{
			rezultat = rezultat + "Polje šifra ne smije biti prazno<br />";
		}
		
		List<KorisnickiRacun> listaKorisnika = baza.getKorisnickiRacunRepository().ucitajSveIzBaze();
	
		for (KorisnickiRacun kr : listaKorisnika) 
		{
			if(modifikacija && korisnickiRacun.getKorisnickiRacunId() != null)
				continue;
			
			if(kr.getUsername().equals(korisnickiRacun.getUsername()))
			{				
				rezultat = rezultat + "Uneseni username već postoji molimo unesite drugi!<br />";
				break;
			}		
		}
		
		return rezultat;
	}

	public boolean obrisiKorisnika(int idSelektovanogKorisnika)
	{
		try {
			
			KorisnickiRacun korisnickiRacun = baza.getKorisnickiRacunRepository().ucitajIzBaze(idSelektovanogKorisnika);
			baza.getKorisnickiRacunRepository().obrisiIzBaze(korisnickiRacun);
			
			return true;
		} catch (Exception e) {
			UnitOfWork.logger.error(e);
			return false;
		}

	}
	
	public KorisnickiRacun dajKorisnickiRacunPoKorisnickiRacunID(int id){
		return baza.getKorisnickiRacunRepository().ucitajIzBaze(id);
	}
}
