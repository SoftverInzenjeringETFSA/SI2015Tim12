package ba.unsa.etf.si.app.iTravel.BLL;

import ba.unsa.etf.si.app.iTravel.BLL.UnitOfWork.UserContext;
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

public class ProvjeraPostojanjaHotela {

	DBContext baza;

	public ProvjeraPostojanjaHotela() {
		baza = DBContext.getInstance();
	}

	public boolean ProvjeriPostojanjeHotelaId(int id) {
		Hotel hotel = new Hotel();

		hotel = baza.getHoteliRepo().ucitajIzBaze(id);

		if (hotel == null)
			return false;

		return true;
	}

	public boolean ProvjeriPostojanjeHotela(String NazivHotela, String AdresaHotela) {

		Hotel hotel = new Hotel();
		ArrayList<Criterion> listaKriterjona = new ArrayList<Criterion>();
		listaKriterjona.add(Restrictions.eq("naziv", (String) NazivHotela));
		listaKriterjona.add(Restrictions.eq("adresa", (String) AdresaHotela));

		hotel = baza.getHoteliRepo().ucitajIzBazePoKriteriju(listaKriterjona).get(0);

		if (hotel == null)
			return false;

		return true;
	}
}
