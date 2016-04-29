package ba.unsa.etf.si.app.iTravel.DAL.Repositories;

import ba.unsa.etf.si.app.iTravel.DAL.Repository;
import ba.unsa.etf.si.app.iTravel.DBModels.Termin;

public class TerminRepository extends Repository<Termin> {
	public TerminRepository()
	{
		super(Termin.class);
	}

}
