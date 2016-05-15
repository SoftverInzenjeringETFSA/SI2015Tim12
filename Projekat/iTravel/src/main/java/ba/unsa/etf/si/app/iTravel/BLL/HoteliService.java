package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;

public class HoteliService
{
	DBContext baza = new DBContext();
	
	public HoteliService()
	{
		
	}
	
	public void KreirajHotel(Hotel hotel) {
		baza.getHoteliRepo().spasiUBazu(hotel);

	}
	
	public Hotel VratiHotelId(int id) {
		Hotel hotel = new Hotel();
		hotel = baza.getHoteliRepo().ucitajIzBaze(id);
		return hotel;

	}

	public ArrayList<Hotel> VratiSveHotele() {
		ArrayList<Hotel> hoteli = new ArrayList<Hotel>();
		hoteli = (ArrayList<Hotel>) baza.getHoteliRepo().ucitajSveIzBaze();
		return hoteli;
	}

	public Hotel VrtatiHotelPoNazivu(String NazivHotela, String AdresaHotela) {
		Hotel hotel = new Hotel();

		ArrayList<Criterion> listaKriterjona = new ArrayList<Criterion>();
		listaKriterjona.add(Restrictions.eq("naziv", (String) NazivHotela));
		listaKriterjona.add(Restrictions.eq("adresa", (String) AdresaHotela));
		hotel = baza.getHoteliRepo().ucitajIzBazePoKriteriju(listaKriterjona).get(0);
		return hotel;
	}
	
	public void AzurirajHotel(Hotel hotel) 
	{
		baza.getHoteliRepo().sacuvajIliAzurirajUBazu(hotel);

	}
	
	public void ObrisiJendaHotel(Hotel hotel) 
	{

		baza.getHoteliRepo().obrisiIzBaze(hotel);
	}

	public void ObrisiSveHotele()
	{

		baza.getHoteliRepo().obrisiSveIzBaze();
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
	
	public ArrayList<Hotel> VratiHotelZaDestinaciju(String nazivDestinacije) {
		ArrayList<Hotel> hoteli = new ArrayList<Hotel>();
		
		Destinacija destinacija=new Destinacija();
		DestinacijeService d=new DestinacijeService();
		destinacija=d.VratiDestinaciju(nazivDestinacije);
		
		ArrayList<Criterion> listaKriterjona = new ArrayList<Criterion>();
		listaKriterjona.add(Restrictions.eq("destinacija", destinacija));

		hoteli.addAll(baza.getHoteliRepo().ucitajIzBazePoKriteriju(listaKriterjona));

		return hoteli;
	}


}
