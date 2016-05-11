package ba.unsa.etf.si.app.iTravel.DAL.Repositories;

import ba.unsa.etf.si.app.iTravel.DAL.Repository;
import ba.unsa.etf.si.app.iTravel.DBModels.Postavke;

public class PostavkeRepository extends Repository<Postavke>
{
	public PostavkeRepository() 
	{
		super(Postavke.class);		
	}

}
