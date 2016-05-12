package ba.unsa.etf.si.app.iTravel.BLL;

import java.util.ArrayList;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.app.iTravel.DAL.DBContext;
import ba.unsa.etf.si.app.iTravel.DBModels.Destinacija;
import ba.unsa.etf.si.app.iTravel.DBModels.Hotel;
import ba.unsa.etf.si.app.iTravel.DBModels.Soba;

public class SobeService
{
	DBContext baza = new DBContext();
	
	public SobeService()
	{
		
	}
	
	public void UbaciSobuUBazu(Soba soba) {
		baza.getSobaRepository().spasiUBazu(soba);

	}
	
	public void ObrisiJendaHotel(Soba soba) {

		baza.getSobaRepository().obrisiIzBaze(soba);
	}

	public void ObrisiSveSobe()
	{

		baza.getSobaRepository().obrisiSveIzBaze();
	}
	
	public Soba VratiSobaId(int id) {
		Soba sobe= new Soba();

	sobe = baza.getSobaRepository().ucitajIzBaze(id);

		return sobe;

	}

	public ArrayList<Soba> VratiSveSobe() {
		ArrayList<Soba> sobe = new ArrayList<Soba>();

		sobe = (ArrayList<Soba>) baza.getSobaRepository().ucitajSveIzBaze();

		return sobe;

	}

	public Soba VrtatiSobuPoHoteluIBrojuKreveta(int brojKreveta,int HotelID) {
		Soba soba = new Soba();

		ArrayList<Criterion> listaKriterjona = new ArrayList<Criterion>();
		listaKriterjona.add(Restrictions.eq("BrojKreveta", (int) brojKreveta));
		listaKriterjona.add(Restrictions.eq("HotelID", (int) HotelID));

		soba = baza.getSobaRepository().ucitajIzBazePoKriteriju(listaKriterjona).get(0);

		return soba;
	}
	
	public boolean ProvjeriPostojanjeSobaId(int id) {
		Soba soba =new Soba();

		soba = baza.getSobaRepository().ucitajIzBaze(id);

		if (soba == null)
			return false;

		return true;
	}

	public boolean ProvjeriPostojanjeSoba(int brojKreveta) {

		Soba soba =new Soba();
		ArrayList<Criterion> listaKriterjona = new ArrayList<Criterion>();
		listaKriterjona.add(Restrictions.eq("BrojKreveta", (int)brojKreveta));
	

		soba = baza.getSobaRepository().ucitajIzBazePoKriteriju(listaKriterjona).get(0);

		if (soba == null)
			return false;

		return true;
	}
	
	public void AzurirajiliUbaciSobu(Soba soba) {
		baza.getSobaRepository().sacuvajIliAzurirajUBazu(soba);

	}


}
