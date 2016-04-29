package ba.unsa.etf.si.app.iTravel.DAL.Repositories;

import ba.unsa.etf.si.app.iTravel.DAL.Repository;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;

public class HotelRepository extends Repository<Hotel> {

	public HotelRepository()
	{
		super(Hotel.class);
	}

}
