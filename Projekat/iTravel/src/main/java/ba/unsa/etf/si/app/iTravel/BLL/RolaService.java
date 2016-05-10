package ba.unsa.etf.si.app.iTravel.BLL;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Rola;

public class RolaService
{
	DBContext baza = new DBContext();
	
	public Rola dajRolu(Integer id)
	{
		return baza.getRolaRepository().ucitajIzBaze(id);		
	}

}
