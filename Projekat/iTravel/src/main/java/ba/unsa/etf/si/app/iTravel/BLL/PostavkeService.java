package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.List;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Postavke;

public class PostavkeService
{
	DBContext baza = new DBContext();
	
	public boolean modulOmogucen(int idPostavke)
	{
		Postavke postavka = baza.getPostavkeRepository().ucitajIzBaze(idPostavke);
		
		return postavka.getOmoguceno();
	}
	
	public boolean spasiPromjenePostavki(boolean[] nizPostavki)
	{
		try
		{			
			for(int i = 0; i< nizPostavki.length; i++)
			{
				Postavke postavke = baza.getPostavkeRepository().ucitajIzBaze(i+1);
				postavke.setOmoguceno(nizPostavki[i]);
				baza.getPostavkeRepository().sacuvajIliAzurirajUBazu(postavke);
			}
			
			return true;		
		}
		catch (Exception e) {
			UnitOfWork.logger.error(e);
			return false;
		}

	}
	
	public boolean[] dajSvePostavke()
	{
		List<Postavke> listaPostavki = baza.getPostavkeRepository().ucitajSveIzBaze();
		
		boolean[] nizPostavki = new boolean[6];
		nizPostavki[0] = false;
		
		for (Postavke postavke : listaPostavki) {
			nizPostavki[postavke.getPostavkaId()] = postavke.getOmoguceno();
		}
		
		return nizPostavki;
	}

}
