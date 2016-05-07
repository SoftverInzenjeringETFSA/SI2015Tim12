package ba.unsa.etf.si.app.iTravel.BLL;

import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;

public class ProbaService {
	
	Destinacija d;
	public ProbaService() {
		
		d= new Destinacija();
	}
	
	public void NistaNeRadi()
	{
		d.setNaziv("Dubai");
	}

}
