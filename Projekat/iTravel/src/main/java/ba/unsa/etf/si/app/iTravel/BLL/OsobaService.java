package ba.unsa.etf.si.app.iTravel.BLL;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Osoba;

public class OsobaService
{
	DBContext baza = new DBContext();

	public Osoba KreirajOsobu(Osoba osoba)
	{
		return baza.getOsobaRepository().spasiUBazu(osoba);
	}
}
