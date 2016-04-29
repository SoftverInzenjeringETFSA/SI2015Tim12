package ba.unsa.etf.si.app.iTravel.DAL.Repositories;

import ba.unsa.etf.si.app.iTravel.DAL.Repository;
import ba.unsa.etf.si.app.iTravel.DBModels.Rezervacija;

public class RezervacijaRepository extends Repository<Rezervacija> {
	public RezervacijaRepository()
	{
		super(Rezervacija.class);
	}

}
