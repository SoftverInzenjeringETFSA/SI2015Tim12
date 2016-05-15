package ba.unsa.etf.si.app.iTravel.BLL;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.RezervisaniTerminSoba;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

public class RezervisaniTerminService {

	public DBContext baza;
	public RezervisaniTerminService() {
		baza= new DBContext();
	}
	
	
	public void DodajRezervisaniTermin(RezervisaniTerminSoba s)
	{
		baza.getRezervisaniTerminSobaRepository().spasiUBazu(s);
	}
	
	public void ObrisiJenduSobuRezervacija(RezervisaniTerminSoba soba) {

		baza.getRezervisaniTerminSobaRepository().obrisiIzBaze(soba);
	}
	
}
