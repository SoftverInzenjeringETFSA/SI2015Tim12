package ba.unsa.etf.si.app.iTravel.BLL;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.KorisnickiRacun;
import ba.unsa.etf.si.app.iTravel.DBModels.Korisnickiracunxrola;
//import ba.unsa.etf.si.app.iTravel.DAL.SessionFactoryDB;
//import ba.unsa.etf.si.app.iTravel.DAL.Repositories.KorisnickiRacunXRolaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class UbaciHotel {

	DBContext baza;

	public UbaciHotel() {
		baza = DBContext.getInstance();
	}

	public void UbaciHotelUBazu(Hotel hotel) {
		baza.getHoteliRepo().spasiUBazu(hotel);

	}

}
