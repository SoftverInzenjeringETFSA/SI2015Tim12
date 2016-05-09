package ba.unsa.etf.si.app.iTravel.BLL;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Korisnickiracunxrola;
//import ba.unsa.etf.si.app.iTravel.DAL.SessionFactoryDB;
//import ba.unsa.etf.si.app.iTravel.DAL.Repositories.KorisnickiRacunXRolaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import antlr.collections.List;

public class ObrisiHotel {

	DBContext baza;

	public ObrisiHotel() {
		baza = DBContext.getInstance();
	}

	public void ObrisiJendaHotel(Hotel hotel) {

		baza.getHoteliRepo().obrisiIzBaze(hotel);
	}

	public void ObrisiSveHotele()
	{

		baza.getHoteliRepo().obrisiSveIzBaze();
	}

}